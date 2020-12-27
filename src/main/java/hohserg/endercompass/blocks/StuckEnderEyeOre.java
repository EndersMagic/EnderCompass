package hohserg.endercompass.blocks;

import hohserg.endercompass.ForgeEventSubscriber;
import hohserg.endercompass.Main;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.projectile.EyeOfEnderEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = Main.ID)
public class StuckEnderEyeOre extends Block {
    @ObjectHolder(Main.ID + ":stuck_ender_eye_ore")
    public static Block stuck_ender_eye_ore;

    public StuckEnderEyeOre() {
        super(Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).harvestLevel(2).noDrops().hardnessAndResistance(3, 3).setRequiresTool().sound(SoundType.STONE));
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (event.getWorld() instanceof ServerWorld && event.getState().getBlock() instanceof StuckEnderEyeOre) {
            if (event.getWorld().getRandom().nextInt(10) < 3)
                spawnEye((ServerWorld) event.getWorld(), event.getPos());
            else
                dropItem((ServerWorld) event.getWorld(), event.getPos());
        }
    }

    private static void dropItem(ServerWorld world, BlockPos pos) {
        ItemEntity entity = new ItemEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(Items.ENDER_EYE, world.getRandom().nextInt(1) + 1));
        entity.addVelocity(world.getRandom().nextGaussian() / 10, world.getRandom().nextGaussian() / 10, world.getRandom().nextGaussian() / 10);
        world.addEntity(entity);
    }

    private static void spawnEye(ServerWorld world, BlockPos pos) {
        BlockPos strongholdPos = ForgeEventSubscriber.getStrongholdPos(world, pos, world.getChunkProvider().getChunkGenerator());
        EyeOfEnderEntity eyeofenderentity = new EyeOfEnderEntity(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
        eyeofenderentity.func_213863_b(new ItemStack(Items.ENDER_EYE));
        eyeofenderentity.moveTowards(strongholdPos);
        world.addEntity(eyeofenderentity);
    }
}
