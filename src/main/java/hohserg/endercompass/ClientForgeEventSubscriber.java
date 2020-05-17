package hohserg.endercompass;

import hohserg.endercompass.items.EnderCompass;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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
        }
    }
}
