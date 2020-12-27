package hohserg.endercompass;

import hohserg.endercompass.items.EnderCompass;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Main.ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientForgeEventSubscriber {

    @SubscribeEvent
    public static void onTooltip(ItemTooltipEvent event) {
        if (event.getItemStack().getItem() instanceof EnderCompass) {
            String localized = I18n.format("item.endercompass.ender_compass");
            if (localized.equals("item.endercompass.ender_compass"))
                localized = "Ender Compass";
            event.getToolTip().clear();
            event.getToolTip().add(new StringTextComponent(localized));
        }else if(event.getItemStack().getItem()== stuck_ender_eye_ore){
            String localized = I18n.format("item.endercompass.stuck_ender_eye_ore");
            if (localized.equals("item.endercompass.stuck_ender_eye_ore"))
                localized = "Stuck Ender Eye";
            event.getToolTip().clear();
            event.getToolTip().add(new StringTextComponent(localized));
        }
    }
    @ObjectHolder(Main.ID + ":stuck_ender_eye_ore")
    public static Item stuck_ender_eye_ore;
}
