package hohserg.endercompass.blocks;

import hohserg.endercompass.Main;
import hohserg.endercompass.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Main.ID)
public class StuckEnderEyeOre extends Block {

    public StuckEnderEyeOre() {
        super(Material.ROCK);
        setHarvestLevel("pickaxe", 2);
        setResistance(3);
        setHardness(3);
        setSoundType(SoundType.STONE);
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
    }

    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event) {
        if (!event.getWorld().isRemote && event.getState().getBlock() instanceof StuckEnderEyeOre) {
            if (event.getWorld().rand.nextInt(10) < 3)
                spawnEye(event.getWorld(), event.getPos());
            else
                dropItem(event.getWorld(), event.getPos());
        }
    }

    private static void dropItem(World world, BlockPos pos) {
        EntityItem entity = new EntityItem(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(Items.ENDER_EYE, world.rand.nextInt(1) + 1));
        entity.addVelocity(world.rand.nextGaussian() / 10, world.rand.nextGaussian() / 10, world.rand.nextGaussian() / 10);
        world.spawnEntity(entity);
    }

    private static void spawnEye(World world, BlockPos pos) {
        BlockPos strongholdPos = CommonProxy.getStrongholdPos((WorldServer) world, pos);
        if (strongholdPos != null) {
            EntityEnderEye eyeofenderentity = new EntityEnderEye(world, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
            eyeofenderentity.moveTowards(strongholdPos);
            world.spawnEntity(eyeofenderentity);
        }
    }
}
