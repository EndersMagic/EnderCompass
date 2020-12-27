package hohserg.endercompass.worldgen;

import hohserg.endercompass.ForgeEventSubscriber;
import hohserg.endercompass.Main;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ReplaceBlockConfig;
import net.minecraft.world.gen.feature.ReplaceBlockFeature;
import net.minecraft.world.gen.placement.Height4To32;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static hohserg.endercompass.blocks.StuckEnderEyeOre.stuck_ender_eye_ore;

@Mod.EventBusSubscriber(modid = Main.ID)
public class OreGeneration {

    private static ConfiguredFeature oreConfiguredFeature;

    private static ConfiguredFeature oreConfiguredFeature() {
        if (oreConfiguredFeature == null) {
            oreConfiguredFeature = new ReplaceBlockFeature(ReplaceBlockConfig.field_236604_a_) {
                @Override
                public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, ReplaceBlockConfig config) {
                    BlockPos strongholdPos = ForgeEventSubscriber.getStrongholdPos(null, pos, generator);
                    if (strongholdPos != null && pos.distanceSq(strongholdPos) < 10000)
                        return super.generate(reader, generator, rand, pos, config);
                    else
                        return false;
                }
            }.withConfiguration(new ReplaceBlockConfig(Blocks.STONE.getDefaultState(), stuck_ender_eye_ore.getDefaultState()))
                    .withPlacement(new Height4To32(NoPlacementConfig.CODEC) {
                        @Override
                        public Stream<BlockPos> getPositions(Random random, NoPlacementConfig config, BlockPos pos) {
                            return IntStream.range(0, 8 + random.nextInt(3)).mapToObj((count) -> {
                                int j = random.nextInt(16) + pos.getX();
                                int k = random.nextInt(16) + pos.getZ();
                                int l = random.nextInt(64) + 4;
                                return new BlockPos(j, l, k);
                            });
                        }
                    }.configure(IPlacementConfig.NO_PLACEMENT_CONFIG));

        }

        return oreConfiguredFeature;
    }

    @SubscribeEvent
    public static void registerOreAtBiomes(BiomeLoadingEvent event) {
        if (!event.getCategory().equals(Biome.Category.NETHER) && !event.getCategory().equals(Biome.Category.THEEND))
            event.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, oreConfiguredFeature());

    }

}