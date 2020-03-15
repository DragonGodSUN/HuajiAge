package com.lh_lshen.mcbbs.huajiage.item;

import com.google.common.util.concurrent.CycleDetectingLockFactory.Policies;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemArrowRequiem extends Item {
	public ItemArrowRequiem()
	{
		 super();
		  this.setMaxStackSize(1);
		  this.setCreativeTab(CreativeTabLoader.tabJo);
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
	 if(playerIn.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemOrgaArmorBase &&
		playerIn.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() instanceof ItemOrgaArmorBase &&
		playerIn.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() instanceof ItemOrgaArmorBase &&
		playerIn.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() instanceof ItemOrgaArmorBase) {
	    if(worldIn.isRemote) {
	     Minecraft.getMinecraft().getSoundHandler().stopSounds();
		 HuajiSoundPlayer.playMusic(SoundLoader.ORGA_REQUIEM_1);
    	 playerIn.sendMessage(new TextComponentTranslation("message.huaji.orga.awake.1"));
	    }
			if(!worldIn.isRemote) {
			playerIn.inventory.getCurrentItem().shrink(1);;
			playerIn.dropItem(ItemLoader.orgaRequiem, 1).setPickupDelay(120);
			playerIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,120,6));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,120));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.GLOWING,120));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,120,7));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.WEAKNESS,120,6));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.NAUSEA,120));
			playerIn.addPotionEffect(new PotionEffect(MobEffects.WITHER,120,2));
			if(playerIn.isPotionActive(PotionLoader.potionFlowerHope)) {
				playerIn.removePotionEffect(PotionLoader.potionFlowerHope);
			}
			}
			
			
		}
		
		return new ActionResult(EnumActionResult.SUCCESS,playerIn.getHeldItem(handIn));
	}

}
