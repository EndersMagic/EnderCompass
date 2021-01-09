package hohserg.endercompass.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import java.util.Optional;
import java.util.UUID;

public class EnderCompass extends Item {

    public static final String compassKey = "compassKey";
    public static final String findUnexplored = "findUnexplored";

    public static int getCompassKey(ItemStack stack) {
        return Optional.ofNullable(stack.getTagCompound()).map(i -> i.getInteger(compassKey)).orElse(0);
    }

    public static boolean getCompassMode(ItemStack stack) {
        return Optional.ofNullable(stack.getTagCompound()).map(i -> i.getBoolean(findUnexplored)).orElse(false);
    }

    private static void checkAndSetKey(ItemStack stack, Entity entityIn) {
        if (stack.getTagCompound() == null)
            stack.setTagCompound(new NBTTagCompound());

        if (!stack.getTagCompound().hasKey(compassKey) || stack.getTagCompound().getInteger("entityKey") != entityIn.hashCode()) {
            stack.getTagCompound().setInteger(compassKey, UUID.randomUUID().hashCode());
            stack.getTagCompound().setInteger("entityKey", entityIn.hashCode());
        }
    }

    @Override
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return getCompassMode(oldStack) != getCompassMode(newStack);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        checkAndSetKey(stack, entityIn);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        switchCompassMode(playerIn.getHeldItem(handIn));
        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    private void switchCompassMode(ItemStack stack) {
        if (stack.getTagCompound() == null)
            stack.setTagCompound(new NBTTagCompound());

        stack.getTagCompound().setBoolean(findUnexplored, !stack.getTagCompound().getBoolean(findUnexplored));
    }
}
