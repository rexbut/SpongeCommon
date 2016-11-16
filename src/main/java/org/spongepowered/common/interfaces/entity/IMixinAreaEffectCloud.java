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
package org.spongepowered.common.interfaces.entity;

import java.util.List;
import java.util.Optional;

import org.spongepowered.api.effect.particle.ParticleType;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.util.Color;

public interface IMixinAreaEffectCloud {

	int getAge();
	void setAge(int age);
	
	int getDuration();
	void setDuration(int duration);
	
	int getDurationOnUse();
	void setDurationOnUse(int durationOnUse);
	
	Optional<ParticleType> getParticle();
	void setParticle(ParticleType particle);
	
	float getRadius();
	void setRadius(float radius);
	
	float getRadiusOnUse();
	void setRadiusOnUse(float radiusOnUse);
	
	float getRadiusPerTick();
	void setRadiusPerTick(float radiusPerTick);
	
	int getReapplicationDelay();
	void setReapplicationDelay(int reapplicationDelay);
	
	int getWaitTime();
	void setWaitTime(int waitTime);
	
	Color getColor();
	void setColor(Color color);
	
	List<PotionEffect> getEffects();
	void setEffects(List<PotionEffect> age);
}
