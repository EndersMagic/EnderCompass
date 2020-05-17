package hohserg.endercompass;

import hohserg.endercompass.network.ECPacketHandler;
import hohserg.endercompass.network.PacketUpdateStrongholdPos;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
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
                System.out.println("test1");
                Optional.ofNullable(((ServerWorld) event.getWorld()).getChunkProvider().getChunkGenerator()
                        .findNearestStructure(event.getWorld(), "Stronghold", new BlockPos(event.getEntity()), 100, false))
                        .map(pos -> new PacketUpdateStrongholdPos(event.getWorld().dimension.getType().toString(), pos))
                        .ifPresent(p -> ECPacketHandler.sendTo(p, (ServerPlayerEntity) event.getEntity()));
            }
    }
}