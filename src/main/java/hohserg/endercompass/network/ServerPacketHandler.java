package hohserg.endercompass.network;

import codechicken.lib.packet.ICustomPacketHandler;
import codechicken.lib.packet.PacketCustom;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.play.IServerPlayNetHandler;

public class ServerPacketHandler implements ICustomPacketHandler.IServerPacketHandler {
    @Override
    public void handlePacket(PacketCustom packetCustom, ServerPlayerEntity serverPlayerEntity, IServerPlayNetHandler iServerPlayNetHandler) {

    }
}
