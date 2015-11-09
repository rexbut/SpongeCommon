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
package org.spongepowered.common.registry;

import org.spongepowered.api.block.BlockSnapshotBuilder;
import org.spongepowered.api.block.BlockStateBuilder;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.tileentity.TileEntityType;
import org.spongepowered.api.block.trait.BooleanTrait;
import org.spongepowered.api.block.trait.EnumTrait;
import org.spongepowered.api.block.trait.IntegerTrait;
import org.spongepowered.api.data.type.Art;
import org.spongepowered.api.data.type.BannerPatternShape;
import org.spongepowered.api.data.type.BigMushroomType;
import org.spongepowered.api.data.type.BrickType;
import org.spongepowered.api.data.type.Career;
import org.spongepowered.api.data.type.CoalType;
import org.spongepowered.api.data.type.CookedFish;
import org.spongepowered.api.data.type.DirtType;
import org.spongepowered.api.data.type.DisguisedBlockType;
import org.spongepowered.api.data.type.DoublePlantType;
import org.spongepowered.api.data.type.DyeColor;
import org.spongepowered.api.data.type.Fish;
import org.spongepowered.api.data.type.GoldenApple;
import org.spongepowered.api.data.type.Hinge;
import org.spongepowered.api.data.type.LogAxis;
import org.spongepowered.api.data.type.NotePitch;
import org.spongepowered.api.data.type.PistonType;
import org.spongepowered.api.data.type.PlantType;
import org.spongepowered.api.data.type.PortionType;
import org.spongepowered.api.data.type.PrismarineType;
import org.spongepowered.api.data.type.Profession;
import org.spongepowered.api.data.type.QuartzType;
import org.spongepowered.api.data.type.RailDirection;
import org.spongepowered.api.data.type.SandType;
import org.spongepowered.api.data.type.SandstoneType;
import org.spongepowered.api.data.type.ShrubType;
import org.spongepowered.api.data.type.SkullType;
import org.spongepowered.api.data.type.SlabType;
import org.spongepowered.api.data.type.StairShape;
import org.spongepowered.api.data.type.StoneType;
import org.spongepowered.api.data.type.TreeType;
import org.spongepowered.api.data.type.WallType;
import org.spongepowered.api.data.value.ValueBuilder;
import org.spongepowered.api.effect.particle.ParticleType;
import org.spongepowered.api.effect.sound.SoundType;
import org.spongepowered.api.entity.EntitySnapshotBuilder;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.living.player.gamemode.GameMode;
import org.spongepowered.api.event.cause.entity.damage.DamageType;
import org.spongepowered.api.event.cause.entity.damage.source.BlockDamageSourceBuilder;
import org.spongepowered.api.item.Enchantment;
import org.spongepowered.api.item.FireworkEffectBuilder;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStackBuilder;
import org.spongepowered.api.item.merchant.TradeOfferBuilder;
import org.spongepowered.api.potion.PotionEffectBuilder;
import org.spongepowered.api.potion.PotionEffectType;
import org.spongepowered.api.scoreboard.ScoreboardBuilder;
import org.spongepowered.api.scoreboard.TeamBuilder;
import org.spongepowered.api.scoreboard.Visibility;
import org.spongepowered.api.scoreboard.critieria.Criterion;
import org.spongepowered.api.scoreboard.displayslot.DisplaySlot;
import org.spongepowered.api.scoreboard.objective.ObjectiveBuilder;
import org.spongepowered.api.scoreboard.objective.displaymode.ObjectiveDisplayMode;
import org.spongepowered.api.statistic.StatisticBuilder;
import org.spongepowered.api.text.format.TextColor;
import org.spongepowered.api.text.selector.SelectorType;
import org.spongepowered.api.util.rotation.Rotation;
import org.spongepowered.api.world.DimensionType;
import org.spongepowered.api.world.GeneratorType;
import org.spongepowered.api.world.WorldBuilder;
import org.spongepowered.api.world.biome.BiomeType;
import org.spongepowered.api.world.difficulty.Difficulty;
import org.spongepowered.api.world.explosion.ExplosionBuilder;
import org.spongepowered.api.world.gen.PopulatorType;
import org.spongepowered.api.world.weather.Weather;
import org.spongepowered.common.Sponge;
import org.spongepowered.common.block.SpongeBlockSnapshotBuilder;
import org.spongepowered.common.block.SpongeBlockStateBuilder;
import org.spongepowered.common.data.value.SpongeValueBuilder;
import org.spongepowered.common.entity.SpongeEntitySnapshotBuilder;
import org.spongepowered.common.event.cause.entity.damage.SpongeBlockDamageSourceBuilder;
import org.spongepowered.common.item.SpongeFireworkBuilder;
import org.spongepowered.common.item.SpongeItemStackBuilder;
import org.spongepowered.common.item.merchant.SpongeTradeOfferBuilder;
import org.spongepowered.common.potion.SpongePotionBuilder;
import org.spongepowered.common.registry.factory.MessageSinkFactoryModule;
import org.spongepowered.common.registry.factory.ResourcePackFactoryModule;
import org.spongepowered.common.registry.factory.SelectorFactoryModule;
import org.spongepowered.common.registry.factory.TextFactoryModule;
import org.spongepowered.common.registry.factory.TimingsFactoryModule;
import org.spongepowered.common.registry.type.ArgumentRegistryModule;
import org.spongepowered.common.registry.type.ArtRegistryModule;
import org.spongepowered.common.registry.type.BannerPatternShapeRegistryModule;
import org.spongepowered.common.registry.type.BigMushroomRegistryModule;
import org.spongepowered.common.registry.type.BiomeTypeRegistryModule;
import org.spongepowered.common.registry.type.BlockTypeRegistryModule;
import org.spongepowered.common.registry.type.BrickTypeRegistryModule;
import org.spongepowered.common.registry.type.CareerRegistryModule;
import org.spongepowered.common.registry.type.ChatTypeRegistryModule;
import org.spongepowered.common.registry.type.CoalTypeRegistryModule;
import org.spongepowered.common.registry.type.CookedFishRegistryModule;
import org.spongepowered.common.registry.type.CriteriaRegistryModule;
import org.spongepowered.common.registry.type.DamageSourceRegistryModule;
import org.spongepowered.common.registry.type.DamageTypeRegistryModule;
import org.spongepowered.common.registry.type.DifficultyRegistryModule;
import org.spongepowered.common.registry.type.DirtTypeRegistryModule;
import org.spongepowered.common.registry.type.DisguisedBlockTypeRegistryModule;
import org.spongepowered.common.registry.type.DisplaySlotRegistryModule;
import org.spongepowered.common.registry.type.DoublePlantTypeRegistryModule;
import org.spongepowered.common.registry.type.DyeColorRegistryModule;
import org.spongepowered.common.registry.type.EnchantmentRegistryModule;
import org.spongepowered.common.registry.type.EntityTypeRegistryModule;
import org.spongepowered.common.registry.type.FishRegistryModule;
import org.spongepowered.common.registry.type.GameModeRegistryModule;
import org.spongepowered.common.registry.type.GeneratorRegistryModule;
import org.spongepowered.common.registry.type.GoldenAppleRegistryModule;
import org.spongepowered.common.registry.type.HingeRegistryModule;
import org.spongepowered.common.registry.type.ItemTypeRegistryModule;
import org.spongepowered.common.registry.type.LogAxisRegistryModule;
import org.spongepowered.common.registry.type.NotePitchRegistryModule;
import org.spongepowered.common.registry.type.ObjectiveDisplayModeRegistryModule;
import org.spongepowered.common.registry.type.ParticleRegistryModule;
import org.spongepowered.common.registry.type.PistonTypeRegistryModule;
import org.spongepowered.common.registry.type.PlantTypeModuleRegistry;
import org.spongepowered.common.registry.type.PopulatorTypeRegistryModule;
import org.spongepowered.common.registry.type.PortionTypeRegistryModule;
import org.spongepowered.common.registry.type.PotionEffectTypeRegistryModule;
import org.spongepowered.common.registry.type.PrismarineRegistryModule;
import org.spongepowered.common.registry.type.ProfessionRegistryModule;
import org.spongepowered.common.registry.type.PropertyRegistryModule;
import org.spongepowered.common.registry.type.QuartzTypeRegistryModule;
import org.spongepowered.common.registry.type.RailDirectionRegistryModule;
import org.spongepowered.common.registry.type.RotationRegistryModule;
import org.spongepowered.common.registry.type.SandTypeRegistryModule;
import org.spongepowered.common.registry.type.SandstoneTypeRegistryModule;
import org.spongepowered.common.registry.type.SelectorTypeRegistryModule;
import org.spongepowered.common.registry.type.ShrubTypeRegistryModule;
import org.spongepowered.common.registry.type.SkullTypeRegistryModule;
import org.spongepowered.common.registry.type.SlabTypeRegistryModule;
import org.spongepowered.common.registry.type.SoundRegistryModule;
import org.spongepowered.common.registry.type.StairShapeRegistryModule;
import org.spongepowered.common.registry.type.StoneTypeRegistryModule;
import org.spongepowered.common.registry.type.TextColorsRegistryModule;
import org.spongepowered.common.registry.type.TextStyleRegistryModule;
import org.spongepowered.common.registry.type.TileEntityTypeRegistryModule;
import org.spongepowered.common.registry.type.TreeTypeRegistryModule;
import org.spongepowered.common.registry.type.VisibilityRegistryModule;
import org.spongepowered.common.registry.type.WallTypeRegistryModule;
import org.spongepowered.common.registry.type.WeatherRegistryModule;
import org.spongepowered.common.registry.type.block.BooleanTraitRegistryModule;
import org.spongepowered.common.registry.type.block.EnumTraitRegistryModule;
import org.spongepowered.common.registry.type.block.IntegerTraitRegistryModule;
import org.spongepowered.common.registry.type.world.DimensionRegistryModule;
import org.spongepowered.common.scoreboard.builder.SpongeObjectiveBuilder;
import org.spongepowered.common.scoreboard.builder.SpongeScoreboardBuilder;
import org.spongepowered.common.scoreboard.builder.SpongeTeamBuilder;
import org.spongepowered.common.world.SpongeExplosionBuilder;
import org.spongepowered.common.world.SpongeWorldBuilder;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public final class CommonModuleRegistry {

    public static CommonModuleRegistry getInstance() {
        return Holder.INSTANCE;
    }

    public void registerDefaultModules() {
        SpongeGameRegistry registry = Sponge.getSpongeRegistry();
        registerFactories();
        registerDefaultSuppliers(registry);
        registerCommonModules(registry);
    }

    private void registerFactories() {
        final List<FactoryRegistry<?, ?>> factoryRegistries = new ArrayList<>();
        factoryRegistries.add(new MessageSinkFactoryModule());
        factoryRegistries.add(new ResourcePackFactoryModule());
        factoryRegistries.add(new SelectorFactoryModule());
        factoryRegistries.add(new SelectorFactoryModule());
        factoryRegistries.add(new TextFactoryModule());
        factoryRegistries.add(new TimingsFactoryModule());

        try {
            Field modifierField = Field.class.getDeclaredField("modifiers");
            modifierField.setAccessible(true);
            for (FactoryRegistry<?, ?> registry : factoryRegistries) {
                RegistryHelper.setFactory(registry.getFactoryOwner(), registry.provideFactory());
                registry.initialize();
            }
        } catch (Exception e) {
            Sponge.getLogger().error("Could not initialize a factory!", e);
        }
    }

    private void registerDefaultSuppliers(SpongeGameRegistry registry) {
        registry.registerBuilderSupplier(ItemStackBuilder.class, SpongeItemStackBuilder::new);
        registry.registerBuilderSupplier(TradeOfferBuilder.class, SpongeTradeOfferBuilder::new);
        registry.registerBuilderSupplier(FireworkEffectBuilder.class, SpongeFireworkBuilder::new);
        registry.registerBuilderSupplier(PotionEffectBuilder.class, SpongePotionBuilder::new);
        registry.registerBuilderSupplier(ObjectiveBuilder.class, SpongeObjectiveBuilder::new);
        registry.registerBuilderSupplier(TeamBuilder.class, SpongeTeamBuilder::new);
        registry.registerBuilderSupplier(ScoreboardBuilder.class, SpongeScoreboardBuilder::new);
        registry.registerBuilderSupplier(StatisticBuilder.class, () -> {throw new UnsupportedOperationException();});
        registry.registerBuilderSupplier(WorldBuilder.class, SpongeWorldBuilder::new);
        registry.registerBuilderSupplier(ExplosionBuilder.class, SpongeExplosionBuilder::new);
        registry.registerBuilderSupplier(ValueBuilder.class, SpongeValueBuilder::new);
        registry.registerBuilderSupplier(BlockStateBuilder.class, SpongeBlockStateBuilder::new);
        registry.registerBuilderSupplier(BlockSnapshotBuilder.class, SpongeBlockSnapshotBuilder::new);
        registry.registerBuilderSupplier(EntitySnapshotBuilder.class, SpongeEntitySnapshotBuilder::new);
        registry.registerBuilderSupplier(BlockDamageSourceBuilder.class, SpongeBlockDamageSourceBuilder::new);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    protected void registerCommonModules(SpongeGameRegistry registry) {
        registry.registerModule(new ArgumentRegistryModule());
        registry.registerModule(Art.class, new ArtRegistryModule());
        registry.registerModule(BannerPatternShape.class, new BannerPatternShapeRegistryModule());
        registry.registerModule(BooleanTrait.class, BooleanTraitRegistryModule.getInstance());
        registry.registerModule(BigMushroomType.class, new BigMushroomRegistryModule());
        registry.registerModule(BiomeType.class, new BiomeTypeRegistryModule());
        registry.registerModule(BlockType.class, BlockTypeRegistryModule.getInstance());
        registry.registerModule(BrickType.class, new BrickTypeRegistryModule());
        registry.registerModule(Career.class, CareerRegistryModule.getInstance());
        registry.registerModule(new ChatTypeRegistryModule());
        registry.registerModule(CoalType.class, new CoalTypeRegistryModule());
        registry.registerModule(CookedFish.class, new CookedFishRegistryModule());
        registry.registerModule(Criterion.class, new CriteriaRegistryModule());
        registry.registerModule(new DamageSourceRegistryModule());
        registry.registerModule(DamageType.class, new DamageTypeRegistryModule());
        registry.registerModule(Difficulty.class, new DifficultyRegistryModule());
        registry.registerModule(DimensionType.class, DimensionRegistryModule.getInstance());
        registry.registerModule(DirtType.class, new DirtTypeRegistryModule());
        registry.registerModule(DisguisedBlockType.class, new DisguisedBlockTypeRegistryModule());
        registry.registerModule(DisplaySlot.class, new DisplaySlotRegistryModule());
        registry.registerModule(DoublePlantType.class, new DoublePlantTypeRegistryModule());
        registry.registerModule(DyeColor.class, new DyeColorRegistryModule());
        registry.registerModule(Enchantment.class, new EnchantmentRegistryModule());
        registry.registerModule((Class<EnumTrait<?>>) (Class) EnumTrait.class, EnumTraitRegistryModule.getInstance());
        registry.registerModule(EntityType.class, EntityTypeRegistryModule.getInstance());
        registry.registerModule(Fish.class, new FishRegistryModule());
        registry.registerModule(GameMode.class, new GameModeRegistryModule());
        registry.registerModule(GeneratorType.class, new GeneratorRegistryModule());
        registry.registerModule(GoldenApple.class, new GoldenAppleRegistryModule());
        registry.registerModule(Hinge.class, new HingeRegistryModule());
        registry.registerModule(IntegerTrait.class, IntegerTraitRegistryModule.getInstance());
        registry.registerModule(ItemType.class, ItemTypeRegistryModule.getInstance());
        registry.registerModule(LogAxis.class, new LogAxisRegistryModule());
        registry.registerModule(NotePitch.class, new NotePitchRegistryModule());
        registry.registerModule(ObjectiveDisplayMode.class, new ObjectiveDisplayModeRegistryModule());
        registry.registerModule(ParticleType.class, new ParticleRegistryModule());
        registry.registerModule(PistonType.class, new PistonTypeRegistryModule());
        registry.registerModule(PlantType.class, new PlantTypeModuleRegistry());
        registry.registerModule(PopulatorType.class, new PopulatorTypeRegistryModule());
        registry.registerModule(PortionType.class, new PortionTypeRegistryModule());
        registry.registerModule(PotionEffectType.class, new PotionEffectTypeRegistryModule());
        registry.registerModule(PrismarineType.class, new PrismarineRegistryModule());
        registry.registerModule(Profession.class, ProfessionRegistryModule.getInstance());
        registry.registerModule(new PropertyRegistryModule());
        registry.registerModule(QuartzType.class, new QuartzTypeRegistryModule());
        registry.registerModule(RailDirection.class, new RailDirectionRegistryModule());
        registry.registerModule(Rotation.class, new RotationRegistryModule());
        registry.registerModule(SandstoneType.class, new SandstoneTypeRegistryModule());
        registry.registerModule(SandType.class, new SandTypeRegistryModule());
        registry.registerModule(SelectorType.class, new SelectorTypeRegistryModule());
        registry.registerModule(ShrubType.class, new ShrubTypeRegistryModule());
        registry.registerModule(SkullType.class, new SkullTypeRegistryModule());
        registry.registerModule(SlabType.class, new SlabTypeRegistryModule());
        registry.registerModule(SoundType.class, new SoundRegistryModule());
        registry.registerModule(StairShape.class, new StairShapeRegistryModule());
        registry.registerModule(StoneType.class, new StoneTypeRegistryModule());
        registry.registerModule(TextColor.class, new TextColorsRegistryModule());
        registry.registerModule(new TextStyleRegistryModule());
        registry.registerModule(TileEntityType.class, new TileEntityTypeRegistryModule());
        registry.registerModule(TreeType.class, new TreeTypeRegistryModule());
        registry.registerModule(Visibility.class, new VisibilityRegistryModule());
        registry.registerModule(WallType.class, new WallTypeRegistryModule());
        registry.registerModule(Weather.class, new WeatherRegistryModule());
    }

    private CommonModuleRegistry() { }

    private static final class Holder {
        private static final CommonModuleRegistry INSTANCE = new CommonModuleRegistry();
    }

}
