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
package org.spongepowered.common.data.manipulator.mutable.entity;

import com.google.common.collect.Lists;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.immutable.entity.ImmutableAreaCloudData;
import org.spongepowered.api.data.manipulator.mutable.entity.AreaCloudData;
import org.spongepowered.api.data.value.mutable.ListValue;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.effect.particle.ParticleType;
import org.spongepowered.api.effect.particle.ParticleTypes;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.util.Color;
import org.spongepowered.common.data.ImmutableDataCachingUtil;
import org.spongepowered.common.data.manipulator.mutable.common.AbstractData;
import org.spongepowered.common.data.value.mutable.SpongeListValue;
import org.spongepowered.common.data.value.mutable.SpongeValue;

import java.util.List;

public class SpongeAreaCloudData extends AbstractData<AreaCloudData, ImmutableAreaCloudData> implements AreaCloudData {

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

    public SpongeAreaCloudData() {
        this(600, 0, ParticleTypes.MOB_SPELL, 3F, 0F, 0F, 20, 20, Color.BLACK, Lists.<PotionEffect>newArrayList());
    }

    public SpongeAreaCloudData(int duration, int durationOnUse, ParticleType particle, float radius, float radiusOnUse, 
            float radiusPerTick, int reapplicationDelay, int waitTime, Color color, List<PotionEffect> effects) {
        super(AreaCloudData.class);
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
        registerGettersAndSetters();
    }

    @Override
    protected void registerGettersAndSetters() {
        registerFieldGetter(Keys.AREA_CLOUD_DURATION, () -> this.duration);
        registerFieldSetter(Keys.AREA_CLOUD_DURATION, (duration) -> this.duration = duration);
        registerKeyValue(Keys.AREA_CLOUD_DURATION, this::duration);

        registerFieldGetter(Keys.AREA_CLOUD_DURATION_ON_USE, () -> this.durationOnUse);
        registerFieldSetter(Keys.AREA_CLOUD_DURATION_ON_USE, (durationOnUse) -> this.durationOnUse = durationOnUse);
        registerKeyValue(Keys.AREA_CLOUD_DURATION_ON_USE, this::durationOnUse);

        registerFieldGetter(Keys.AREA_CLOUD_PARTICLE_TYPE, () -> this.particle);
        registerFieldSetter(Keys.AREA_CLOUD_PARTICLE_TYPE, (particle) -> this.particle = particle);
        registerKeyValue(Keys.AREA_CLOUD_PARTICLE_TYPE, this::particleType);

        registerFieldGetter(Keys.AREA_CLOUD_RADIUS, () -> this.radius);
        registerFieldSetter(Keys.AREA_CLOUD_RADIUS, (radius) -> this.radius = radius);
        registerKeyValue(Keys.AREA_CLOUD_RADIUS, this::radius);

        registerFieldGetter(Keys.AREA_CLOUD_RADIUS_ON_USE, () -> this.radiusOnUse);
        registerFieldSetter(Keys.AREA_CLOUD_RADIUS_ON_USE, (radiusOnUse) -> this.radiusOnUse = radiusOnUse);
        registerKeyValue(Keys.AREA_CLOUD_RADIUS_ON_USE, this::radiusOnUse);

        registerFieldGetter(Keys.AREA_CLOUD_RADIUS_PER_TICK, () -> this.radiusPerTick);
        registerFieldSetter(Keys.AREA_CLOUD_RADIUS_PER_TICK, (radiusPerTick) -> this.radiusPerTick = radiusPerTick);
        registerKeyValue(Keys.AREA_CLOUD_RADIUS_PER_TICK, this::radiusPerTick);

        registerFieldGetter(Keys.AREA_CLOUD_REAPPLICATION_DELAY, () -> this.reapplicationDelay);
        registerFieldSetter(Keys.AREA_CLOUD_REAPPLICATION_DELAY, (reapplicationDelay) -> this.reapplicationDelay = reapplicationDelay);
        registerKeyValue(Keys.AREA_CLOUD_REAPPLICATION_DELAY, this::reapplicationDelay);

        registerFieldGetter(Keys.AREA_CLOUD_WAIT_TIME, () -> this.waitTime);
        registerFieldSetter(Keys.AREA_CLOUD_WAIT_TIME, (waitTime) -> this.waitTime = waitTime);
        registerKeyValue(Keys.AREA_CLOUD_WAIT_TIME, this::waitTime);

        registerFieldGetter(Keys.COLOR, () -> this.color);
        registerFieldSetter(Keys.COLOR, (color) -> this.color = color);
        registerKeyValue(Keys.COLOR, this::color);

        registerFieldGetter(Keys.POTION_EFFECTS, () -> this.effects);
        registerFieldSetter(Keys.POTION_EFFECTS, (effects) -> this.effects = effects);
        registerKeyValue(Keys.POTION_EFFECTS, this::effects);
    }

    @Override
    public Value<Integer> duration() {
        return new SpongeValue<>(Keys.AREA_CLOUD_DURATION, 600, this.duration);
    }

    @Override
    public Value<Integer> durationOnUse() {
        return new SpongeValue<>(Keys.AREA_CLOUD_DURATION_ON_USE, 0, this.durationOnUse);
    }

    @Override
    public Value<ParticleType> particleType() {
        return new SpongeValue<>(Keys.AREA_CLOUD_PARTICLE_TYPE, ParticleTypes.MOB_SPELL, this.particle);
    }

    @Override
    public Value<Float> radius() {
        return new SpongeValue<>(Keys.AREA_CLOUD_RADIUS, 3F, this.radius);
    }

    @Override
    public Value<Float> radiusOnUse() {
        return new SpongeValue<>(Keys.AREA_CLOUD_RADIUS_ON_USE, 0F, this.radiusOnUse);
    }

    @Override
    public Value<Float> radiusPerTick() {
        return new SpongeValue<>(Keys.AREA_CLOUD_RADIUS_PER_TICK, 0F, this.radiusPerTick);
    }

    @Override
    public Value<Integer> reapplicationDelay() {
        return new SpongeValue<>(Keys.AREA_CLOUD_REAPPLICATION_DELAY, 20, this.reapplicationDelay);
    }

    @Override
    public Value<Integer> waitTime() {
        return new SpongeValue<>(Keys.AREA_CLOUD_WAIT_TIME, 10, this.waitTime);
    }

    @Override
    public Value<Color> color() {
        return new SpongeValue<>(Keys.COLOR, Color.BLACK, this.color);
    }

    @Override
    public ListValue<PotionEffect> effects() {
        return new SpongeListValue<>(Keys.POTION_EFFECTS, Lists.<PotionEffect>newArrayList(), this.effects);
    }

    @Override
    public AreaCloudData copy() {
        return new SpongeAreaCloudData(this.duration, this.durationOnUse, this.particle, this.radius, this.radiusOnUse, 
                this.radiusPerTick, this.reapplicationDelay, this.waitTime, this.color, this.effects);
    }

    @Override
    public ImmutableAreaCloudData asImmutable() {
        return ImmutableDataCachingUtil.getManipulator(ImmutableAreaCloudData.class, this.duration, this.durationOnUse, 
                this.particle, this.radius, this.radiusOnUse, this.radiusPerTick, this.reapplicationDelay, this.color, this.effects);
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
