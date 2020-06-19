package com.lh_lshen.mcbbs.huajiage.item;

import java.util.Random;

import com.google.common.util.concurrent.CycleDetectingLockFactory.Policies;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.resources.I18n;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class ItemOrgaRunning extends Item {
	public ItemOrgaRunning()
	{
		 super();
		  this.setMaxStackSize(1);
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
	}
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
	 if(!worldIn.isRemote){
	 if(playerIn.getCapability(CapabilityStandHandler.STAND_TYPE, null).getStand().equals(StandLoader.EMPTY)) {
		 
	   double chance = Math.random();
	   int standNumber = (int) MathHelper.nextFloat(new Random(), 0, 100);
	   StandBase type = StandUtil.getTypeWithIndex(standNumber);
	   if(chance>=ConfigHuaji.Stands.chanceStandFail) {
		   if(type !=null) {
			   playerIn.getCapability(CapabilityStandHandler.STAND_TYPE, null).setStand(StandLoader.ORGA_REQUIEM.getName());
			   HuajiSoundPlayer.playToNearbyClient(playerIn, SoundEvents.ENTITY_PLAYER_LEVELUP, 1f);
			   playerIn.addPotionEffect(new PotionEffect(MobEffects.SPEED ,500,6));
			   HuajiSoundPlayer.playToNearbyClient(playerIn, SoundLoader.ORGA_SHOT , 1f);
			   playerIn.sendMessage(new TextComponentTranslation("huajiage.orga.1"));
			   }
		   }
	   playerIn.inventory.getCurrentItem().shrink(1);
	   
		}else {
 		playerIn.sendMessage(new TextComponentTranslation("message.huajiage.tarot.stand.fail_load"));
		}
 	}
		return new ActionResult(EnumActionResult.SUCCESS,playerIn.getHeldItem(handIn));
	 	}
	
}
