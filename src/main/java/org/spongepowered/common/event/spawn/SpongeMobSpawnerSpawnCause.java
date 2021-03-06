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
package org.spongepowered.common.event.spawn;

import com.google.common.base.Objects;
import org.spongepowered.api.data.manipulator.immutable.ImmutableMobSpawnerData;
import org.spongepowered.api.event.cause.entity.spawn.MobSpawnerSpawnCause;
import org.spongepowered.api.event.cause.entity.spawn.common.AbstractSpawnCause;

public class SpongeMobSpawnerSpawnCause extends AbstractSpawnCause implements MobSpawnerSpawnCause {

    private final ImmutableMobSpawnerData mobSpawnerData;

    public SpongeMobSpawnerSpawnCause(SpongeMobSpawnerSpawnCauseBuilder builder) {
        super(builder);
        this.mobSpawnerData = builder.mobSpawnerData;
    }

    @Override
    public ImmutableMobSpawnerData getMobSpawnerData() {
        return this.mobSpawnerData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SpongeMobSpawnerSpawnCause that = (SpongeMobSpawnerSpawnCause) o;
        return Objects.equal(this.spawnType, that.spawnType)
                && Objects.equal(this.mobSpawnerData, that.mobSpawnerData);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.getType());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper("MobSpawnerSpawnCause")
                .add("SpawnType", this.spawnType)
                .add("MobSpawnerData", this.mobSpawnerData)
                .toString();
    }
}
