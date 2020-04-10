package hohserg.endercompass.network;

import codechicken.lib.packet.ICustomPacketHandler;
import codechicken.lib.packet.PacketCustom;
import net.minecraft.client.Minecraft;
import net.minecraft.network.play.INetHandlerPlayClient;
import hohserg.endercompass.client.render.model.baked.FinalisedModelEnderCompass;

public class ClientPacketHandler implements ICustomPacketHandler.IClientPacketHandler {

    @Override
    public void handlePacket(PacketCustom packetCustom, Minecraft minecraft, INetHandlerPlayClient iNetHandlerPlayClient) {
        if (packetCustom.getType() == 1) {
            FinalisedModelEnderCompass.target.put(packetCustom.readInt(), packetCustom.readPos());
        }
    }
}
