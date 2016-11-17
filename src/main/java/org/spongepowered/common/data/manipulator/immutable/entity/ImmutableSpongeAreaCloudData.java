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
package org.spongepowered.common.data.manipulator.immutable.entity;

import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.immutable.entity.ImmutableAreaCloudData;
import org.spongepowered.api.data.manipulator.mutable.entity.AreaCloudData;
import org.spongepowered.api.data.value.immutable.ImmutableListValue;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.effect.particle.ParticleType;
import org.spongepowered.api.effect.particle.ParticleTypes;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.util.Color;
import org.spongepowered.common.data.manipulator.immutable.common.AbstractImmutableData;
import org.spongepowered.common.data.manipulator.mutable.entity.SpongeAreaCloudData;
import org.spongepowered.common.data.value.immutable.ImmutableSpongeListValue;
import org.spongepowered.common.data.value.immutable.ImmutableSpongeValue;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.List;

public class ImmutableSpongeAreaCloudData extends AbstractImmutableData<ImmutableAreaCloudData, AreaCloudData> implements ImmutableAreaCloudData {

    private int duration;
    private int durationOnUse;
    private ParticleType particle;
    private float radius;
    private float radiusOnUse;
    private float radiusPerTick;
    private int reapplicationDelay;
    private int waitTime;
    private Color color;
    private List<PotionEffect> effects;
    private final ImmutableValue<Integer> durationValue;
    private final ImmutableValue<Integer> durationOnUseValue;
    private final ImmutableValue<ParticleType> particleValue;
    private final ImmutableValue<Float> radiusValue;
    private final ImmutableValue<Float> radiusOnUseValue;
    private final ImmutableValue<Float> radiusPerTickValue;
    private final ImmutableValue<Integer> reapplicationDelayValue;
    private final ImmutableValue<Integer> waitTimeValue;
    private final ImmutableValue<Color> colorValue;
    private final ImmutableListValue<PotionEffect> effectsValue;

    public ImmutableSpongeAreaCloudData() {
        this(600, 0, ParticleTypes.MOB_SPELL, 3F, 0F, 0F, 20, 20, Color.BLACK, Lists.<PotionEffect>newArrayList());
    }

    public ImmutableSpongeAreaCloudData(int duration, int durationOnUse, ParticleType particle, float radius, 
            float radiusOnUse, float radiusPerTick, int reapplicationDelay, int waitTime, Color color, List<PotionEffect> effects) {
        super(ImmutableAreaCloudData.class);
        this.duration = duration;
        this.durationOnUse = durationOnUse;
        this.particle = particle;
        this.radius = radius;
        this.radiusOnUse = radiusOnUse;
        this.radiusPerTick = radiusPerTick;
        this.reapplicationDelay = reapplicationDelay;
        this.waitTime = waitTime;
        this.color = color;
        this.effects = effects;
        this.durationValue = ImmutableSpongeValue.cachedOf(Keys.AREA_CLOUD_DURATION, 600, this.duration);
        this.durationOnUseValue = ImmutableSpongeValue.cachedOf(Keys.AREA_CLOUD_DURATION_ON_USE, 0, this.durationOnUse);
        this.particleValue = ImmutableSpongeValue.cachedOf(Keys.AREA_CLOUD_PARTICLE_TYPE, ParticleTypes.MOB_SPELL, this.particle);
        this.radiusValue = ImmutableSpongeValue.cachedOf(Keys.AREA_CLOUD_RADIUS, 3F, this.radius);
        this.radiusOnUseValue = ImmutableSpongeValue.cachedOf(Keys.AREA_CLOUD_RADIUS_ON_USE, 0F, this.radiusOnUse);
        this.radiusPerTickValue = ImmutableSpongeValue.cachedOf(Keys.AREA_CLOUD_RADIUS_PER_TICK, 0F, this.radiusPerTick);
        this.reapplicationDelayValue = ImmutableSpongeValue.cachedOf(Keys.AREA_CLOUD_REAPPLICATION_DELAY, 20, this.reapplicationDelay);
        this.waitTimeValue = ImmutableSpongeValue.cachedOf(Keys.AREA_CLOUD_WAIT_TIME, 20, this.waitTime);
        this.colorValue = ImmutableSpongeValue.cachedOf(Keys.COLOR, Color.BLACK, this.color);
        this.effectsValue = new ImmutableSpongeListValue<PotionEffect>(Keys.POTION_EFFECTS, ImmutableList.copyOf(this.effects));

        registerGetters();
    }

    @Override
    protected void registerGetters() {
        registerFieldGetter(Keys.AREA_CLOUD_DURATION, () -> this.duration);
        registerKeyValue(Keys.AREA_CLOUD_DURATION, () -> this.durationValue);

        registerFieldGetter(Keys.AREA_CLOUD_DURATION_ON_USE, () -> this.durationOnUse);
        registerKeyValue(Keys.AREA_CLOUD_DURATION_ON_USE, () -> this.durationOnUseValue);

        registerFieldGetter(Keys.AREA_CLOUD_PARTICLE_TYPE, () -> this.particle);
        registerKeyValue(Keys.AREA_CLOUD_PARTICLE_TYPE, () -> this.particleValue);

        registerFieldGetter(Keys.AREA_CLOUD_RADIUS, () -> this.radius);
        registerKeyValue(Keys.AREA_CLOUD_RADIUS, () -> this.radiusValue);

        registerFieldGetter(Keys.AREA_CLOUD_RADIUS_ON_USE, () -> this.radiusOnUse);
        registerKeyValue(Keys.AREA_CLOUD_RADIUS_ON_USE, () -> this.radiusOnUseValue);

        registerFieldGetter(Keys.AREA_CLOUD_RADIUS_PER_TICK, () -> this.radiusPerTick);
        registerKeyValue(Keys.AREA_CLOUD_RADIUS_PER_TICK, () -> this.radiusPerTickValue);

        registerFieldGetter(Keys.AREA_CLOUD_REAPPLICATION_DELAY, () -> this.reapplicationDelay);
        registerKeyValue(Keys.AREA_CLOUD_REAPPLICATION_DELAY, () -> this.reapplicationDelayValue);

        registerFieldGetter(Keys.AREA_CLOUD_WAIT_TIME, () -> this.waitTime);
        registerKeyValue(Keys.AREA_CLOUD_WAIT_TIME, () -> this.waitTimeValue);

        registerFieldGetter(Keys.COLOR, () -> this.color);
        registerKeyValue(Keys.COLOR, () -> this.colorValue);

        registerFieldGetter(Keys.POTION_EFFECTS, () -> this.effects);
        registerKeyValue(Keys.POTION_EFFECTS, () -> this.effectsValue);
    }

    @Override
    public ImmutableValue<Integer> duration() {
        return this.durationValue;
    }

    @Override
    public ImmutableValue<Integer> durationOnUse() {
        return this.durationOnUseValue;
    }

    @Override
    public ImmutableValue<ParticleType> particleType() {
        return this.particleValue;
    }

    @Override
    public ImmutableValue<Float> radius() {
        return this.radiusValue;
    }

    @Override
    public ImmutableValue<Float> radiusOnUse() {
        return this.radiusOnUseValue;
    }

    @Override
    public ImmutableValue<Float> radiusPerTick() {
        return this.radiusPerTickValue;
    }

    @Override
    public ImmutableValue<Integer> reapplicationDelay() {
        return this.reapplicationDelayValue;
    }

    @Override
    public ImmutableValue<Integer> waitTime() {
        return this.waitTimeValue;
    }

    @Override
    public ImmutableValue<Color> color() {
        return this.colorValue;
    }

    @Override
    public ImmutableListValue<PotionEffect> effects() {
        return this.effectsValue;
    }

    @Override
    public AreaCloudData asMutable() {
        return new SpongeAreaCloudData(this.duration, this.durationOnUse, this.particle, this.radius, this.radiusOnUse, 
                this.radiusPerTick, this.reapplicationDelay, this.waitTime, this.color, this.effects);
    }

    @Override
    public DataContainer toContainer() {
        return super.toContainer()
                .set(Keys.AREA_CLOUD_DURATION, this.duration)
                .set(Keys.AREA_CLOUD_DURATION_ON_USE, this.durationOnUse)
                .set(Keys.AREA_CLOUD_PARTICLE_TYPE, this.particle)
                .set(Keys.AREA_CLOUD_RADIUS, this.radius)
                .set(Keys.AREA_CLOUD_RADIUS_ON_USE, this.radiusOnUse)
                .set(Keys.AREA_CLOUD_RADIUS_PER_TICK, this.radiusPerTick)
                .set(Keys.AREA_CLOUD_REAPPLICATION_DELAY, this.reapplicationDelay)
                .set(Keys.AREA_CLOUD_WAIT_TIME, this.waitTime)
                .set(Keys.COLOR, this.color)
                .set(Keys.POTION_EFFECTS, this.effects);
    }
}
