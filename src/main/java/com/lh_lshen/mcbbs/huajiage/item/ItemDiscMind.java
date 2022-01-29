package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemDiscMind extends Item {
	public ItemDiscMind()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabJo);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("item.huajiage.disc_mind.tooltips.1")
				+TextFormatting.GRAY +getOwnerName(stack));
		tooltip.add(I18n.format("item.huajiage.disc_mind.tooltips.2")
				+ TextFormatting.GRAY +getOwnerUUID(stack));
	
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if (stack.getItem() instanceof ItemDiscMind){
				String name = target.getName();
				String uuid = target.getUniqueID().toString();
				if (name.equals(getOwnerName(stack)) && uuid.equals(getOwnerUUID(stack))){
					boolean isDeprived = NBTHelper.getEntityBoolean(target,"disc_deprive");
					if (isDeprived){
						NBTHelper.setEntityBoolean(target,"disc_deprive",false);
						if (!playerIn.world.isRemote){
							stack.shrink(1);
						}
						if (playerIn.world.isRemote){
							playerIn.playSound(SoundEvents.BLOCK_COMPARATOR_CLICK, 1f, 1f);
							playerIn.world.playEvent(2005,target.getPosition(),0);
						}
					}else {
						if (playerIn.world.isRemote){
							playerIn.sendMessage(new TextComponentTranslation("item.huajiage.disc_mind:mind_exist"));
						}
					}
			}
		}
		return super.itemInteractionForEntity(stack, playerIn, target, hand);
	}

	public static String getOwnerName(ItemStack stack){
		if (stack.getItem() instanceof ItemDiscMind) {
			return NBTHelper.getTagCompoundSafe(stack).getString(TAGS.NAME.getTag());
		}
		return "";
	}

	public static void setOwnerName(ItemStack stack, String name){
		if (stack.getItem() instanceof ItemDiscMind) {
			NBTHelper.getTagCompoundSafe(stack).setString(TAGS.NAME.getTag(), name);
		}
	}

	public static String getOwnerUUID(ItemStack stack){
		if (stack.getItem() instanceof ItemDiscMind) {
			return NBTHelper.getTagCompoundSafe(stack).getString(TAGS.UUID.getTag());
		}
		return "";
	}

	public static void setOwnerUUID(ItemStack stack, String uuid){
		if (stack.getItem() instanceof ItemDiscMind) {
			NBTHelper.getTagCompoundSafe(stack).setString(TAGS.UUID.getTag(), uuid);
		}
	}

	public static void setOwner(ItemStack stack, String name, String uuid){
		setOwnerName(stack,name);
		setOwnerUUID(stack,uuid);
	}

	public enum TAGS{
		NAME("owner_name"),
		UUID("owner_uuid")
		;
		private String tag;

		TAGS(String key) {
			tag = key;
		}
		public String getTag() {
			return tag;
		}

	}
}
