package com.lh_lshen.mcbbs.huajiage.item;

import java.util.List;

import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;


public class ItemSingularity extends Item {
	
	public ItemSingularity()
	{
		 super();
		  this.setMaxStackSize(1);
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
		boolean flag = false;
		EnumStandtype stand = StandUtil.getType(playerIn);
		int stage = StandUtil.getStandStage(playerIn);
		if(stand!=null&&stage==0) {
			flag = true;
		}
		if(flag) {
	    if(worldIn.isRemote) {
			 HuajiSoundPlayer.playMusic(SoundLoader.CHARGE);
			 HuajiSoundPlayer.playMusic(SoundLoader.ENERGY_HIT);
			 HuajiSoundPlayer.playMusic(SoundLoader.NOISE_FURNACE);
			 playerIn.sendMessage(new TextComponentString(I18n.format("message.huajiage.singularity")));
			 }
			if(!worldIn.isRemote) {
			playerIn.inventory.getCurrentItem().shrink(1);
			playerIn.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,200));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.WITHER, 200,9));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.POISON, 200,9));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 200,5));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.HUNGER, 200,9));
			NBTHelper.setEntityInteger(playerIn, HuajiConstant.SINGULARITY, 200);
				}
			}
		
		return new ActionResult<ItemStack>(EnumActionResult.SUCCESS,playerIn.getHeldItem(handIn));
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);

	}
	

}
