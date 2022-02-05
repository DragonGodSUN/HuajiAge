package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

public class ItemDiscMemory extends Item {
	public ItemDiscMemory()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabJo);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(I18n.format("item.huajiage.disc_memory.tooltips.1"
				,TextFormatting.GRAY +getOwnerName(stack)));
		tooltip.add(I18n.format("item.huajiage.disc_memory.tooltips.2"
				,TextFormatting.GRAY +getOwnerType(stack)));
		tooltip.add(I18n.format("item.huajiage.disc_memory.tooltips.3"
				,TextFormatting.GRAY +getOwnerUUID(stack)));
		tooltip.add(I18n.format("item.huajiage.disc_memory.tooltips.4"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

	@Override
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if (stack.getItem() instanceof ItemDiscMemory){
			String name = target.getName();
			String uuid = target.getUniqueID().toString();
			String type = target.getClass().getName();
			if (type.equals(getOwnerType(stack)) && !(target instanceof EntityPlayer)) {
				if (!playerIn.world.isRemote){
					if (getNbtData(stack) instanceof NBTTagCompound) {
						ItemStack discMemory = ItemDiscMemory.getDiscMemory(target);
						if (!target.getName().equals(getOwnerName(stack))){
							target.setCustomNameTag(getOwnerName(stack));
						}
						target.readEntityFromNBT((NBTTagCompound) getNbtData(stack));
						stack.shrink(1);
						target.entityDropItem(discMemory,0.25f);
					}
				}
				if (playerIn.world.isRemote){
					playerIn.playSound(SoundEvents.BLOCK_COMPARATOR_CLICK, 1f, 1f);
					playerIn.world.playEvent(2003,target.getPosition().add(0,target.getEyeHeight(),0),0);
				}
			}else {
				if (playerIn.world.isRemote){
					playerIn.sendMessage(new TextComponentTranslation("message.huajiage.disc_memory.insert.fail.type"));
				}
			}

		}
		return super.itemInteractionForEntity(stack, playerIn, target, hand);
	}

	@Override
	public String getItemStackDisplayName(ItemStack stack) {
		if (!getOwnerName(stack).isEmpty()){
			return super.getItemStackDisplayName(stack)+" "+getOwnerName(stack);
		}
		return super.getItemStackDisplayName(stack);
	}

	public static String getOwnerName(ItemStack stack){
		if (stack.getItem() instanceof ItemDiscMemory) {
			return NBTHelper.getTagCompoundSafe(stack).getString(TAGS.NAME.getTag());
		}
		return "";
	}

	public static void setOwnerName(ItemStack stack, String name){
		if (stack.getItem() instanceof ItemDiscMemory) {
			NBTHelper.getTagCompoundSafe(stack).setString(TAGS.NAME.getTag(), name);
		}
	}

	public static String getOwnerType(ItemStack stack){
		if (stack.getItem() instanceof ItemDiscMemory) {
			return NBTHelper.getTagCompoundSafe(stack).getString(TAGS.TYPE.getTag());
		}
		return "";
	}

	public static void setOwnerType(ItemStack stack, String name){
		if (stack.getItem() instanceof ItemDiscMemory) {
			NBTHelper.getTagCompoundSafe(stack).setString(TAGS.TYPE.getTag(), name);
		}
	}

	public static String getOwnerUUID(ItemStack stack){
		if (stack.getItem() instanceof ItemDiscMemory) {
			return NBTHelper.getTagCompoundSafe(stack).getString(TAGS.UUID.getTag());
		}
		return "";
	}

	public static void setOwnerUUID(ItemStack stack, String uuid){
		if (stack.getItem() instanceof ItemDiscMemory) {
			NBTHelper.getTagCompoundSafe(stack).setString(TAGS.UUID.getTag(), uuid);
		}
	}

	public static NBTBase getNbtData(ItemStack stack){
		if (stack.getItem() instanceof ItemDiscMemory) {
			return NBTHelper.getTagCompoundSafe(stack).getTag(TAGS.NBT.getTag());
		}
		return new NBTTagCompound();
	}

	public static void setNbtData(ItemStack stack, NBTTagCompound nbtTagCompound){
		if (stack.getItem() instanceof ItemDiscMemory) {
			NBTHelper.getTagCompoundSafe(stack).setTag(TAGS.NBT.getTag(),nbtTagCompound);
		}
	}

	public static void setOwner(ItemStack stack, String name, String uuid, String type){
		setOwnerName(stack,name);
		setOwnerUUID(stack,uuid);
		setOwnerType(stack,type);
	}

	public static ItemStack getDiscMemory(EntityLivingBase livingBase){
		ItemStack discMemory = new ItemStack(ItemLoader.discMemory);
		NBTTagCompound nbt = new NBTTagCompound();
		livingBase.writeEntityToNBT(nbt);
		ItemDiscMemory.setOwner(discMemory,livingBase.getName(),livingBase.getUniqueID().toString(),livingBase.getClass().getName());
		setNbtData(discMemory,nbt);
		return discMemory;
	}

	public enum TAGS{
		NAME("owner_name"),
		TYPE("owner_type"),
		UUID("owner_uuid"),
		NBT("nbt_entity")
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
