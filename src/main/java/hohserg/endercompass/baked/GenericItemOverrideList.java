package hohserg.endercompass.baked;

import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class GenericItemOverrideList {
    public static ItemOverrideList fromLambda(HandleItemState handleItemState) {
        return new ItemOverrideList() {
            @Override
            public IBakedModel getModelWithOverrides(IBakedModel originalModel, ItemStack stack, @Nullable World world, @Nullable LivingEntity entity) {
                return handleItemState.apply(originalModel, stack, world, entity);
            }
        };
    }

    public interface HandleItemState {
        IBakedModel apply(IBakedModel originalModel, ItemStack stack, World world, LivingEntity entity);
    }
}
