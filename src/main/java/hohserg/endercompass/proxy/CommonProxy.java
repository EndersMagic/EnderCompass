package hohserg.endercompass.proxy;

import hohserg.endercompass.blocks.StuckEnderEyeOre;
import hohserg.endercompass.items.EnderCompass;
import hohserg.endercompass.network.PacketSyncStrongholdPos;
import hohserg.endercompass.worldgen.OreGeneration;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Optional;

public class CommonProxy {


    public Item enderCompass = new EnderCompass().setRegistryName("ender_compass").setUnlocalizedName("ender_compass").setCreativeTab(CreativeTabs.TOOLS);
    public Block stuckEnderEyeOre = new StuckEnderEyeOre().setRegistryName("stuck_ender_eye_ore").setUnlocalizedName("stuck_ender_eye_ore").setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
        GameRegistry.registerWorldGenerator(new OreGeneration(),10);
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(enderCompass);
        e.getRegistry().register(new ItemBlock(stuckEnderEyeOre).setRegistryName("stuck_ender_eye_ore"));
    }

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> e) {
        e.getRegistry().register(stuckEnderEyeOre);
    }


    @SubscribeEvent
    public void onPlayerEnter(EntityJoinWorldEvent event) {
        if (!event.getWorld().isRemote)
            if (event.getEntity() instanceof EntityPlayerMP)
                Optional.ofNullable(getStrongholdPos((WorldServer) event.getWorld(), new BlockPos(event.getEntity())))
                        .map(pos -> new PacketSyncStrongholdPos(event.getWorld().provider.getDimension(), pos))
                        .ifPresent(p -> p.sendToPlayer((EntityPlayerMP) event.getEntity()));
    }

    public static BlockPos getStrongholdPos(WorldServer world, BlockPos pos) {
        return world.getChunkProvider()
                .getNearestStructurePos(world, "Stronghold", pos, false);
    }
}
