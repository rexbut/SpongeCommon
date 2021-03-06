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
package org.spongepowered.common.registry.type.effect;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import org.spongepowered.api.effect.sound.SoundType;
import org.spongepowered.api.effect.sound.SoundTypes;
import org.spongepowered.api.registry.AlternateCatalogRegistryModule;
import org.spongepowered.api.registry.util.RegisterCatalog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public final class SoundRegistryModule implements AlternateCatalogRegistryModule<SoundType> {

    @RegisterCatalog(SoundTypes.class)
    private final Map<String, SoundType> soundMappings = Maps.newHashMap();

    @Override
    public Map<String, SoundType> provideCatalogMap() {
        Map<String, SoundType> soundTypeMap = new HashMap<>();
        for (Map.Entry<String, SoundType> entry : this.soundMappings.entrySet()) {
            soundTypeMap.put(entry.getKey().replace("minecraft:", ""), entry.getValue());
        }
        return soundTypeMap;
    }

    @Override
    public void registerDefaults() {
        for (ResourceLocation key: SoundEvent.REGISTRY.getKeys()) {
            this.soundMappings.put(key.toString().replace('.', '_'), (SoundType) SoundEvent.REGISTRY.getObject(key));
        }
    }

    @Override
    public Optional<SoundType> getById(String id) {
        return Optional.ofNullable(this.soundMappings.get(checkNotNull(id).toLowerCase(Locale.ENGLISH)));
    }

    @Override
    public Collection<SoundType> getAll() {
        return ImmutableList.copyOf(this.soundMappings.values());
    }
}
