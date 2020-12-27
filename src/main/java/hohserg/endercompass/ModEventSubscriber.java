package hohserg.endercompass;

import hohserg.endercompass.blocks.StuckEnderEyeOre;
import hohserg.endercompass.items.EnderCompass;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Main.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
    @SubscribeEvent
    public static void onRegisterItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new EnderCompass().setRegistryName("ender_compass"));
        event.getRegistry().register(new BlockItem(StuckEnderEyeOre.stuck_ender_eye_ore, new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)).setRegistryName("stuck_ender_eye_ore"));
    }

    @SubscribeEvent
    public static void onRegisterBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new StuckEnderEyeOre().setRegistryName("stuck_ender_eye_ore"));
    }

    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        //DeferredWorkQueue.runLater(OreGenFeature::init);
    }
}
