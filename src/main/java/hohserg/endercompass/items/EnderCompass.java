package hohserg.endercompass.items;

import hohserg.endercompass.ForgeEventSubscriber;
import hohserg.endercompass.Main;
import net.minecraft.command.impl.TeleportCommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.registries.ObjectHolder;

import java.util.UUID;

public class EnderCompass extends Item {
    @ObjectHolder(Main.ID + ":ender_compass")
    public static Item ender_compass;

    public EnderCompass() {
        super(new Item.Properties().group(ItemGroup.TOOLS));
    }

    public static int getCompassKey(ItemStack stack) {
        return stack.getOrCreateTag().getInt("compassKey");
    }

    private static void checkAndSetKey(ItemStack stack, Entity entityIn) {
        if (!stack.getOrCreateTag().contains("compassKey") || stack.getOrCreateTag().getInt("entityKey") != entityIn.hashCode()) {
            stack.getOrCreateTag().putInt("compassKey", UUID.randomUUID().hashCode());
            stack.getOrCreateTag().putInt("entityKey", entityIn.hashCode());
        }
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }

    @Override
    public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        checkAndSetKey(stack, entityIn);
    }
    /*

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (playerIn.isCreative() && !worldIn.isRemote) {
            BlockPos strongholdPos = ForgeEventSubscriber.getStrongholdPos(worldIn, playerIn.getPosition(), ((ServerWorld) worldIn).getChunkProvider().getChunkGenerator());
            if (strongholdPos != null)
                ((ServerWorld) worldIn).getServer().getCommandManager().handleCommand(playerIn.getCommandSource(),"/tp @a "+strongholdPos.getX()+" "+strongholdPos.getY()+" "+strongholdPos.getZ());
                //playerIn.setPositionAndUpdate(strongholdPos.getX(), strongholdPos.getY(), strongholdPos.getZ());
                //playerIn.setPosition(strongholdPos.getX(), strongholdPos.getY(), strongholdPos.getZ());
        }
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }*/
}
