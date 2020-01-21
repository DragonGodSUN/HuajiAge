package com.lh_lshen.mcbbs.huajiage.item;

import java.util.List;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandStageHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemTarot extends Item {
	private static final String DEFAULT_STAND_ID = EnumStandtype.EMPTY;
	public ItemTarot()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
		  
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		String stand=NBTHelper.getTagCompoundSafe(stack).getString(NBT.STAND_NAME.getName());
		int stage=NBTHelper.getTagCompoundSafe(stack).getInteger(NBT.STAND_STAGE.getName());
		tooltip.add(I18n.format("item.tarot:tooltips.1") +I18n.format(EnumStandtype.getLocalName(stand)));
		tooltip.add(I18n.format("item.tarot:tooltips.2") +stage);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (this.isInCreativeTab(tab)) {
			items.add(getItemData(DEFAULT_STAND_ID,0));
		}
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		ItemStack stack = playerIn.getHeldItem(handIn);
		String stand = playerIn.getCapability(CapabilityStandHandler.STAND_TYPE, null).getStand();
		int stage = playerIn.getCapability(CapabilityStandStageHandler.STAND_STAGE, null).getStage();
		boolean flag = NBTHelper.getTagCompoundSafe(stack).getString(NBT.STAND_NAME.getName()).equals(DEFAULT_STAND_ID);
		
		if(playerIn.isSneaking()) {
			if(flag && !stand.equals(DEFAULT_STAND_ID)) {
				setStandTag(playerIn, stack, stand, stage);
				playerIn.getCapability(CapabilityStandHandler.STAND_TYPE, null).setStand(DEFAULT_STAND_ID);
				playerIn.getCapability(CapabilityStandStageHandler.STAND_STAGE, null).setStage(0);
//				StandUtil.removeStandData(playerIn);
				if(worldIn.isRemote) {
				playerIn.playSound(SoundEvents.ITEM_BOTTLE_FILL_DRAGONBREATH, 1f, 1f);
				playerIn.sendMessage(new TextComponentString(I18n.format(EnumStandtype.getLocalName(stand))+I18n.format("message.huajiage.tarot.stand.store")));
				}
			}
		}else {
			String standTag = NBTHelper.getTagCompoundSafe(stack).getString(NBT.STAND_NAME.getName());
			int stageTag = NBTHelper.getTagCompoundSafe(stack).getInteger(NBT.STAND_STAGE.getName());
			if(stand.equals(DEFAULT_STAND_ID)&&!standTag.equals(DEFAULT_STAND_ID)) {
			playerIn.getCapability(CapabilityStandHandler.STAND_TYPE, null).setStand(standTag);
			playerIn.getCapability(CapabilityStandStageHandler.STAND_STAGE, null).setStage(stageTag);
			playerIn.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1f, 1f);
			playerIn.playSound(SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f);
			setStandTag(playerIn, stack, DEFAULT_STAND_ID, 0);
//			StandUtil.setStandData(playerIn, standTag);
			}else {
				if(worldIn.isRemote) {
					if(!stand.equals(DEFAULT_STAND_ID)) {
					playerIn.sendMessage(new TextComponentTranslation("message.huajiage.tarot.stand.fail_load"));
					}
					
				}
			}
//			playerIn.addPotionEffect(new PotionEffect(PotionLoader.potionStand,200));
			}
		return new ActionResult(EnumActionResult.SUCCESS, stack);
	}
	public static ItemStack getItemData(String stand_id, int stage) {
        ItemStack stack = new ItemStack(ItemLoader.tarot);
		NBTTagCompound data = NBTHelper.getTagCompoundSafe(stack);
        
        if (stand_id != null) {
        	data.setString(NBT.STAND_NAME.getName(), stand_id);
        	data.setInteger(NBT.STAND_STAGE.getName(), stage);
        }
        return stack;
    }
	public void setStandTag(EntityLivingBase entity,ItemStack stack , String stand_id, int stage) {
			NBTTagCompound data = NBTHelper.getTagCompoundSafe(stack);
	        	data.setString(NBT.STAND_NAME.getName(), stand_id);
	        	data.setInteger(NBT.STAND_STAGE.getName(), stage);
	}
	 public enum NBT {
		 	STAND_NAME("Name"),
	        STAND_STAGE("Stage");

	        private String name;

	        NBT(String name) {
	            this.name = name;
	        }

	        public String getName() {
	            return name;
	        }
	    }

}
