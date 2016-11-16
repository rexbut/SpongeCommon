/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.common.mixin.core.entity;

import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumParticleTypes;

import java.util.List;
import java.util.Optional;

import javax.annotation.Nullable;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.effect.particle.ParticleType;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.entity.AreaEffectCloud;
import org.spongepowered.api.util.Color;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.common.effect.particle.SpongeParticleType;
import org.spongepowered.common.interfaces.entity.IMixinAreaEffectCloud;

import com.google.common.collect.ImmutableList;

@Mixin(EntityAreaEffectCloud.class)
public abstract class MixinEntityAreaEffectCloud extends MixinEntity implements AreaEffectCloud, IMixinAreaEffectCloud {
	
	@Shadow private boolean colorSet;
	@Shadow private int durationOnUse;
	@Shadow private float radiusOnUse;
	@Shadow private float radiusPerTick;
	@Shadow private int reapplicationDelay;
	@Shadow private int waitTime;
	@Shadow private PotionType potion;
	@Shadow private List<net.minecraft.potion.PotionEffect> effects;
	
	@Shadow public abstract void shadow$addEffect(net.minecraft.potion.PotionEffect effect);
	@Shadow public abstract void shadow$setParticle(EnumParticleTypes particleIn);
	@Shadow public abstract EnumParticleTypes shadow$getParticle();
	@Shadow public abstract int shadow$getColor();
	@Shadow public abstract void setColor(int colorIn);
	
	@Override
	public int getAge() {
		return ((EntityAreaEffectCloud) (Object) this).ticksExisted;
	}
	
	@Override
	public void setAge(int age) {
		((EntityAreaEffectCloud) (Object) this).ticksExisted = age;
	}
	
	@Override
	public int getDurationOnUse() {
		return this.durationOnUse;
	}
	
	@Override
	public void setDurationOnUse(int durationOnUse) {
		this.durationOnUse = durationOnUse;
	}
	
	@Override
	public Optional<ParticleType> getParticle() {
		EnumParticleTypes particle = this.shadow$getParticle();
		for (ParticleType type : Sponge.getGame().getRegistry().getAllOf(ParticleType.class)) {
			if (((SpongeParticleType) type).equals(particle)) {
				return Optional.of(type);
			}
		}
		return Optional.empty();
	}
	
	@Override
	public void setParticle(ParticleType particle) {
		SpongeParticleType type = (SpongeParticleType) particle;
		if (type.getInternalType() != null) {
			this.shadow$setParticle(type.getInternalType());
		}
	}
	
	@Override
	public float getRadiusOnUse() {
		return this.radiusOnUse;
	}
	
	@Override
	public float getRadiusPerTick() {
		return this.radiusPerTick;
	}
	
	@Override
	public int getReapplicationDelay() {
		return this.reapplicationDelay;
	}
	
	@Override
	public void setReapplicationDelay(int reapplicationDelay) {
		this.reapplicationDelay = reapplicationDelay;
	}

	@Override
	public int getWaitTime() {
		return this.waitTime;
	}

	@Override
	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	@Override
	public Color getColor() {
		return Color.ofRgb(this.shadow$getColor());
	}

	@Override
	public void setColor(@Nullable Color color){
		if (color == null) {
			this.setColor(PotionUtils.getPotionColorFromEffectList(PotionUtils.mergeEffects(this.potion, this.effects)));
			this.colorSet = false;
		} else {
			this.setColor(color.getRgb());
		}
	}

	@Override
	public List<PotionEffect> getEffects() {
		ImmutableList.Builder<PotionEffect> effects = ImmutableList.builder();
		for (net.minecraft.potion.PotionEffect effect : this.effects) {
			effects.add((PotionEffect) effect);
		}
		return effects.build();
	}

	@Override
	public void setEffects(List<PotionEffect> effects) {
		this.effects.clear();
		if (!this.colorSet) {
			this.setColor(Color.BLACK.getRgb());
			this.colorSet = false;
		}
		
		for (PotionEffect effect : effects) {
			this.shadow$addEffect((net.minecraft.potion.PotionEffect) effect);
		}
	}
}
