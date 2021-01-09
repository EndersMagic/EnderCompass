package hohserg.endercompass.proxy;

import hohserg.endercompass.client.render.model.baked.ModelEnderCompass;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {

    @SubscribeEvent
    public void registerModels(ModelRegistryEvent e) {
        ModelLoader.setCustomModelResourceLocation(enderCompass, 0, new ModelResourceLocation(enderCompass.getRegistryName(), "inventory"));
    }

    @SubscribeEvent
    public void onModelBake(ModelBakeEvent e) {
        ModelResourceLocation resource = new ModelResourceLocation(enderCompass.getRegistryName(), "inventory");
        IBakedModel existingModel = e.getModelRegistry().getObject(resource);
        e.getModelRegistry().putObject(resource, new ModelEnderCompass(existingModel));
    }
}
