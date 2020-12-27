package hohserg.endercompass;

import hohserg.endercompass.network.NetworkHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Optional;

@Mod.EventBusSubscriber(modid = Main.ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventSubscriber {

    @SubscribeEvent
    public static void onPlayerEnter(EntityJoinWorldEvent event) {
        if (!event.getWorld().isRemote)
            if (event.getEntity() instanceof ServerPlayerEntity) {
                Optional.ofNullable(getStrongholdPos(event.getWorld(), event.getEntity().getPosition(), ((ServerWorld) event.getWorld()).getChunkProvider().getChunkGenerator()))
                        .map(pos -> NetworkHandler.packet(1).writeString(event.getWorld().getDimensionKey().toString()).writePos(pos))
                        .ifPresent(p -> p.sendToPlayer((ServerPlayerEntity) event.getEntity()));
            }
    }

    public static BlockPos getStrongholdPos(World world, BlockPos pos, ChunkGenerator chunkGenerator) {
        return chunkGenerator.func_235956_a_((ServerWorld) world, Structure.STRONGHOLD, pos, 100, false);
    }
}