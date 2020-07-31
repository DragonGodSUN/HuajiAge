package com.lh_lshen.mcbbs.huajiage.item;

import com.github.tartaricacid.touhoulittlemaid.entity.passive.EntityMaid;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandStageHandler;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Optional;

public class ItemDoublePoleBall extends Item {
	public ItemDoublePoleBall()
	{
		 super();
		  this.setMaxStackSize(1);
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
	}

	public static NBTTagCompound getMaidTag(ItemStack stack){
		return NBTHelper.getTagCompoundSafe(stack).getCompoundTag("MaidData");
	}

	public void setMaidTag(ItemStack stack, NBTTagCompound tag){
		NBTTagCompound compound = NBTHelper.getTagCompoundSafe(stack);
		for(String key : tag.getKeySet())
		{
		compound.setTag(key,tag.getTag(key));
		}
		stack.setTagCompound(compound);
	}

	public static String getMaidModel(ItemStack stack){
		return NBTHelper.getTagCompoundSafe(stack).getString("MaidModel");
	}

	public void setMaidModel(ItemStack stack, String model){
		NBTHelper.getTagCompoundSafe(stack).setString("MaidModel",model);
	}

	@Override
	@Optional.Method(modid = "touhou_little_maid")
	public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer playerIn, EntityLivingBase target, EnumHand hand) {
		if (!playerIn.world.isRemote && playerIn.isSneaking()) {
			if(stack.getItem() == this && target.isEntityAlive() && target instanceof EntityMaid){
				EntityMaid maid = (EntityMaid) target;
				setMaidModel(playerIn.getHeldItem(hand),maid.getModelId()+"_default");
				playerIn.sendMessage(new TextComponentTranslation("message.huajiage.ball.load"));
				playerIn.sendMessage(new TextComponentTranslation(maid.getModelId()));
				return true;
			}
		}
		return super.itemInteractionForEntity(stack, playerIn, target, hand);
	}

	@Override
	@Optional.Method(modid = "touhou_little_maid")
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
	 if(!worldIn.isRemote){
	 ItemStack stack = playerIn.getHeldItem(handIn);
	 StandHandler standHandler = playerIn.getCapability(CapabilityStandHandler.STAND_TYPE, null);
	 StandStageHandler standStageHandler = StandUtil.getStandStageHandler(playerIn);
	 IExposedData data = StandUtil.getStandData(playerIn);
	 if(standHandler == null || standStageHandler == null || data ==null){
		 return super.onItemRightClick(worldIn,playerIn,handIn);
	 }
		 if (!playerIn.isSneaking() && stack.getItem() == this) {
			 StandBase stand = StandUtil.getType(playerIn);
			 if(stand == null && !getMaidModel(stack).equals("")) {
				standHandler.setStand(StandLoader.MAID.getName());
				standStageHandler.setStage(1);
				data.setModel(getMaidModel(stack));
				HuajiSoundPlayer.playToNearbyClient(playerIn, SoundEvents.ENTITY_PLAYER_LEVELUP, 1f);
				playerIn.inventory.getCurrentItem().shrink(1);

			 }else if( StandLoader.MAID.equals(stand)&&!getMaidModel(stack).equals("")){
				 playerIn.dropItem(ItemDiscStand.getItemData(new ItemStack(ItemLoader.disc),StandLoader.MAID.getName(),1,getMaidModel(stack)),true);
				 data.setModel(getMaidModel(stack));
				 HuajiSoundPlayer.playToNearbyClient(playerIn, SoundEvents.ENTITY_PLAYER_LEVELUP, 1f);
				 playerIn.inventory.getCurrentItem().shrink(1);
			 }else{
				 playerIn.sendMessage(new TextComponentTranslation("message.huajiage.tarot.stand.fail_load"));
				}
		 }
	 }
		return new ActionResult(EnumActionResult.SUCCESS,playerIn.getHeldItem(handIn));
	 	}
	
}
