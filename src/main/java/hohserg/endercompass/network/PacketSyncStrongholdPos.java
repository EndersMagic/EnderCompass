package hohserg.endercompass.network;

import hohserg.elegant.networking.api.ElegantPacket;
import hohserg.elegant.networking.api.ServerToClientPacket;
import hohserg.endercompass.client.render.model.baked.FinalisedModelEnderCompass;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;

@ElegantPacket
public class PacketSyncStrongholdPos implements ServerToClientPacket {
    public final int dimension;
    public final BlockPos pos;

    public PacketSyncStrongholdPos(int dimension, BlockPos pos) {
        this.dimension = dimension;
        this.pos = pos;
    }

    @Override
    public void onReceive(Minecraft mc) {
        FinalisedModelEnderCompass.target.put(dimension, pos);
    }
}
