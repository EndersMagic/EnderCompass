package hohserg.endercompass.network;

import codechicken.lib.packet.ICustomPacketHandler;
import codechicken.lib.packet.PacketCustom;
import hohserg.endercompass.baked.FinalisedModelEnderCompass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.play.IClientPlayNetHandler;

public class ClientPacketHandler implements ICustomPacketHandler.IClientPacketHandler {
    @Override
    public void handlePacket(PacketCustom packetCustom, Minecraft minecraft, IClientPlayNetHandler iClientPlayNetHandler) {
        if (packetCustom.getType() == 1) {
            FinalisedModelEnderCompass.target.put(packetCustom.readString(), packetCustom.readPos());
        }
    }
}
