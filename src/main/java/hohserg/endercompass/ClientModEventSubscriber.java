package hohserg.endercompass;

import hohserg.endercompass.baked.ModelEnderCompass;
import hohserg.endercompass.items.EnderCompass;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.stream.Collectors;

import static hohserg.endercompass.items.EnderCompass.ender_compass;

@Mod.EventBusSubscriber(modid = Main.ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventSubscriber {

    @SubscribeEvent
    public static void onBakeModel(ModelBakeEvent event) {
        ModelResourceLocation resource = new ModelResourceLocation(ender_compass.getRegistryName(), "inventory");
        IBakedModel existingModel = event.getModelRegistry().get(resource);
        event.getModelRegistry().put(resource, new ModelEnderCompass(existingModel));
    }
}