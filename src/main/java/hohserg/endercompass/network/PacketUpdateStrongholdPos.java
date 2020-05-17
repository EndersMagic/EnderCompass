package hohserg.endercompass.network;

import hohserg.endercompass.baked.FinalisedModelEnderCompass;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketUpdateStrongholdPos {
    private final String worldName;
    private final BlockPos pos;

    public PacketUpdateStrongholdPos(String worldName, BlockPos pos) {
        this.worldName = worldName;
        this.pos = pos;
    }

    public static void encode(PacketUpdateStrongholdPos pkt, PacketBuffer buf) {
        buf.writeString(pkt.worldName);
        buf.writeBlockPos(pkt.pos);
    }

    public static PacketUpdateStrongholdPos decode(PacketBuffer buf) {
        return new PacketUpdateStrongholdPos(buf.readString(), buf.readBlockPos());
    }

    public static class Handler {

        public static void handle(PacketUpdateStrongholdPos message, Supplier<NetworkEvent.Context> ctx) {
            System.out.println("received!");
            ctx.get().enqueueWork(() -> {
                System.out.println("used!");
                FinalisedModelEnderCompass.target.put(message.worldName, message.pos);
            });
        }
    }
}
