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
package org.spongepowered.common.data.processor.multi.entity;

import com.google.common.collect.ImmutableMap;

import net.minecraft.entity.EntityAreaEffectCloud;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataTransactionResult;
import org.spongepowered.api.data.key.Key;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.immutable.entity.ImmutableAreaCloudData;
import org.spongepowered.api.data.manipulator.mutable.entity.AreaCloudData;
import org.spongepowered.api.effect.particle.ParticleType;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.util.Color;
import org.spongepowered.common.data.manipulator.mutable.entity.SpongeAreaCloudData;
import org.spongepowered.common.data.processor.common.AbstractEntityDataProcessor;
import org.spongepowered.common.interfaces.entity.IMixinAreaEffectCloud;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AreaCloudDataProcessor extends AbstractEntityDataProcessor<EntityAreaEffectCloud, AreaCloudData, ImmutableAreaCloudData> {

    public AreaCloudDataProcessor() {
        super(EntityAreaEffectCloud.class);
    }

    @Override
    protected boolean doesDataExist(EntityAreaEffectCloud dataHolder) {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected boolean set(EntityAreaEffectCloud dataHolder, Map<Key<?>, Object> keyValues) {
    	final int age = (int) keyValues.get(Keys.AGE);
    	final int duration = (int) keyValues.get(Keys.AREA_CLOUD_DURATION);
    	final int durationOnUse = (int) keyValues.get(Keys.AREA_CLOUD_DURATION_ON_USE);
    	final ParticleType particle = (ParticleType) keyValues.get(Keys.AREA_CLOUD_PARTICLE);
    	final float radius = (float) keyValues.get(Keys.AREA_CLOUD_RADIUS);
        final float radiusOnUse = (float) keyValues.get(Keys.AREA_CLOUD_RADIUS_ON_USE);
        final float radiusPerTick = (float) keyValues.get(Keys.AREA_CLOUD_RADIUS_PER_TICK);
        final int reapplicationDelay = (int) keyValues.get(Keys.AREA_CLOUD_REAPPLICATION_DELAY);
        final Color color = (Color) keyValues.get(Keys.COLOR);
		final List<PotionEffect> effects = (List<PotionEffect>) keyValues.get(Keys.POTION_EFFECTS);

		IMixinAreaEffectCloud entity = (IMixinAreaEffectCloud) dataHolder;
		entity.setAge(age);
		entity.setDuration(duration);
		entity.setDurationOnUse(durationOnUse);
		entity.setParticle(particle);
		entity.setRadius(radius);
		entity.setRadiusOnUse(radiusOnUse);
		entity.setRadiusPerTick(radiusPerTick);
		entity.setReapplicationDelay(reapplicationDelay);
		entity.setColor(color);
		entity.setEffects(effects);
        return true;
    }

    @Override
    protected Map<Key<?>, ?> getValues(EntityAreaEffectCloud dataHolder) {
    	IMixinAreaEffectCloud entity = (IMixinAreaEffectCloud) dataHolder;
        return ImmutableMap.<Key<?>, Object>builder()
                .put(Keys.AGE, entity.getAge())
                .put(Keys.AREA_CLOUD_DURATION, entity.getDuration())
                .put(Keys.AREA_CLOUD_DURATION_ON_USE, entity.getDurationOnUse())
                .put(Keys.AREA_CLOUD_PARTICLE, entity.getParticle())
                .put(Keys.AREA_CLOUD_RADIUS, entity.getRadius())
                .put(Keys.AREA_CLOUD_RADIUS_ON_USE, entity.getRadiusOnUse())
                .put(Keys.AREA_CLOUD_RADIUS_PER_TICK, entity.getRadiusPerTick())
                .put(Keys.AREA_CLOUD_REAPPLICATION_DELAY, entity.getReapplicationDelay())
                .put(Keys.COLOR, entity.getColor())
                .put(Keys.POTION_EFFECTS, entity.getEffects())
                .build();
    }

    @Override
    protected AreaCloudData createManipulator() {
        return new SpongeAreaCloudData();
    }

    @Override
    public Optional<AreaCloudData> fill(DataContainer container, AreaCloudData areaCloudData) {
        if (container.contains(Keys.AGE)) {
        	areaCloudData.set(Keys.AGE, container.getInt(Keys.AGE.getQuery()).get());
        }
        if (container.contains(Keys.AREA_CLOUD_DURATION)) {
        	areaCloudData.set(Keys.AREA_CLOUD_DURATION, container.getInt(Keys.AREA_CLOUD_DURATION.getQuery()).get());
        }
        if (container.contains(Keys.AREA_CLOUD_DURATION_ON_USE)) {
        	areaCloudData.set(Keys.AREA_CLOUD_DURATION_ON_USE, container.getInt(Keys.AREA_CLOUD_DURATION_ON_USE.getQuery()).get());
        }
        if (container.contains(Keys.AREA_CLOUD_PARTICLE)) {
        	areaCloudData.set(Keys.AREA_CLOUD_PARTICLE, container.getObject(Keys.AREA_CLOUD_PARTICLE.getQuery(), ParticleType.class).get());
        }
        if (container.contains(Keys.AREA_CLOUD_RADIUS)) {
        	areaCloudData.set(Keys.AREA_CLOUD_RADIUS, container.getFloat(Keys.AREA_CLOUD_RADIUS.getQuery()).get());
        }
        if (container.contains(Keys.AREA_CLOUD_RADIUS_ON_USE)) {
        	areaCloudData.set(Keys.AREA_CLOUD_RADIUS_ON_USE, container.getFloat(Keys.AREA_CLOUD_RADIUS_ON_USE.getQuery()).get());
        }
        if (container.contains(Keys.AREA_CLOUD_RADIUS_PER_TICK)) {
        	areaCloudData.set(Keys.AREA_CLOUD_RADIUS_PER_TICK, container.getFloat(Keys.AREA_CLOUD_RADIUS_PER_TICK.getQuery()).get());
        }
        if (container.contains(Keys.AREA_CLOUD_REAPPLICATION_DELAY)) {
        	areaCloudData.set(Keys.AREA_CLOUD_REAPPLICATION_DELAY, container.getInt(Keys.AREA_CLOUD_REAPPLICATION_DELAY.getQuery()).get());
        }
        if (container.contains(Keys.COLOR)) {
        	areaCloudData.set(Keys.COLOR, container.getObject(Keys.COLOR.getQuery(), Color.class).get());
        }
        if (container.contains(Keys.POTION_EFFECTS)) {
        	areaCloudData.set(Keys.POTION_EFFECTS, container.getObjectList(Keys.POTION_EFFECTS.getQuery(), PotionEffect.class).get());
        }
        return Optional.of(areaCloudData);
    }

    @Override
    public DataTransactionResult remove(DataHolder dataHolder) {
        return DataTransactionResult.failNoData();
    }
}
