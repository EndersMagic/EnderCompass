package hohserg.endercompass.worldgen;

import hohserg.endercompass.Main;
import hohserg.endercompass.proxy.CommonProxy;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class OreGeneration implements IWorldGenerator {
    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimensionType() == DimensionType.OVERWORLD) {
            BlockPos pos = new BlockPos(chunkX << 4, 70, chunkZ << 4);
            BlockPos strongholdPos = CommonProxy.getStrongholdPos(((WorldServer) world), pos);
            if (strongholdPos != null && pos.distanceSq(strongholdPos) < 10000) {
                BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos(pos);
                for (int i = 0; i < 50; i++) {
                    int j = random.nextInt(16) + pos.getX();
                    int k = random.nextInt(16) + pos.getZ();
                    int l = random.nextInt(64) + 4;
                    mutableBlockPos.setPos(j, l, k);
                    if (world.getBlockState(mutableBlockPos).getBlock() == Blocks.STONE)
                        world.setBlockState(mutableBlockPos, Main.proxy.stuckEnderEyeOre.getDefaultState());
                }
            }
        }
    }
}
