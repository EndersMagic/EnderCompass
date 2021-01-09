package hohserg.endercompass.client.render.model.baked;

import hohserg.endercompass.items.EnderCompass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;

import java.util.HashMap;
import java.util.Map;

public class ModelEnderCompass extends BakedModelDelegate {

    private static Map<Integer, FinalisedModelEnderCompass> modelCache = new HashMap<>();

    public ModelEnderCompass(IBakedModel base) {
        super(base, GenericItemOverrideList.fromLambda(
                (originalModel, stack, world, entity) ->
                        modelCache.computeIfAbsent(EnderCompass.getCompassKey(stack), key ->
                                new FinalisedModelEnderCompass(originalModel, stack, entity == null ? Minecraft.getMinecraft().player : entity))));
    }
}
