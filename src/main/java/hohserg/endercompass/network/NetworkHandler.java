package hohserg.endercompass.network;

import codechicken.lib.packet.PacketCustom;
import codechicken.lib.packet.PacketCustomChannelBuilder;
import net.minecraft.util.ResourceLocation;

import static hohserg.endercompass.Main.ID;

public class NetworkHandler {

    public static ResourceLocation name = new ResourceLocation(ID, ID);

    public static void init() {
        PacketCustomChannelBuilder.named(name).assignClientHandler(() -> ClientPacketHandler::new).assignServerHandler(() -> ServerPacketHandler::new).build();
    }

    public static PacketCustom packet(int id){
        return new PacketCustom(name,id);
    }
}
