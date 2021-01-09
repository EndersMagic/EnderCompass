package hohserg.endercompass.proxy;

import hohserg.endercompass.items.EnderCompass;
import hohserg.endercompass.network.PacketSyncStrongholdPos;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Optional;

public class CommonProxy {


    public Item enderCompass = new EnderCompass().setRegistryName("ender_compass").setUnlocalizedName("ender_compass").setCreativeTab(CreativeTabs.TOOLS);

    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(enderCompass);
    }


    @SubscribeEvent
    public void onPlayerEnter(EntityJoinWorldEvent event) {
        if (!event.getWorld().isRemote)
            if (event.getEntity() instanceof EntityPlayerMP)
                Optional.ofNullable(((WorldServer) event.getWorld()).getChunkProvider()
                        .getNearestStructurePos(event.getWorld(), "Stronghold", new BlockPos(event.getEntity()), false))
                        .map(pos -> new PacketSyncStrongholdPos(event.getWorld().provider.getDimension(), pos))
                        .ifPresent(p -> p.sendToPlayer((EntityPlayerMP) event.getEntity()));
    }
}
