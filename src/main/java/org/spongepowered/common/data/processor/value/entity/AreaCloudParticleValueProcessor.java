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
package org.spongepowered.common.data.processor.value.entity;

import org.spongepowered.api.data.DataTransactionResult;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.value.ValueContainer;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.data.value.mutable.Value;
import org.spongepowered.api.effect.particle.ParticleType;
import org.spongepowered.api.effect.particle.ParticleTypes;
import org.spongepowered.common.data.processor.common.AbstractSpongeValueProcessor;
import org.spongepowered.common.data.value.immutable.ImmutableSpongeValue;
import org.spongepowered.common.data.value.mutable.SpongeValue;
import org.spongepowered.common.interfaces.entity.IMixinAreaEffectCloud;

import java.util.Optional;

public class AreaCloudParticleValueProcessor extends AbstractSpongeValueProcessor<IMixinAreaEffectCloud, ParticleType, Value<ParticleType>> {

    public AreaCloudParticleValueProcessor() {
        super(IMixinAreaEffectCloud.class, Keys.AREA_CLOUD_PARTICLE_TYPE);
    }

    @Override
    protected Value<ParticleType> constructValue(ParticleType actualValue) {
        return new SpongeValue<>(this.key, ParticleTypes.MOB_SPELL, actualValue);
    }

    @Override
    protected boolean set(IMixinAreaEffectCloud container, ParticleType value) {
    	return container.setParticleType(value);
    }

    @Override
    protected Optional<ParticleType> getVal(IMixinAreaEffectCloud container) {
        return container.getParticleType();
    }

    @Override
    protected ImmutableValue<ParticleType> constructImmutableValue(ParticleType value) {
        return ImmutableSpongeValue.cachedOf(this.key, ParticleTypes.MOB_SPELL, value);
    }

    @Override
    public DataTransactionResult removeFrom(ValueContainer<?> container) {
        return DataTransactionResult.failNoData();
    }
}
