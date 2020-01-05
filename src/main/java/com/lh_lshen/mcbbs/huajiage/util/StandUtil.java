package com.lh_lshen.mcbbs.huajiage.util;

import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.client.model.ModelTheWorld;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.init.playsound.StandMovingSound;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;

public class StandUtil {
	public static EnumStandtype getType(EntityLivingBase entity) {
		   if (entity.hasCapability(CapabilityStandHandler.STAND_TYPE, null)) {
	           StandHandler stand = entity.getCapability(CapabilityStandHandler.STAND_TYPE, null);
	    return EnumStandtype.getType(stand.getStand());
		   }
		return null;
	}

	public static void standPower(EntityLivingBase entity) {
			  if(!entity.isPotionActive(PotionLoader.potionStand)) {
				  entity.addPotionEffect(new PotionEffect(PotionLoader.potionStand,60,0));
				  HuajiSoundPlayer.playToNearbyClient(entity, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,0.5f);
				  ServerUtil.sendPacketToNearbyPlayers(entity, new MessageParticleGenerator(entity.getPositionVector(), EnumParticleTypes.FIREWORKS_SPARK,60,3,1));
			  }
			  if(entity.isPotionActive(PotionLoader.potionStand)&&entity.getActivePotionEffect(PotionLoader.potionStand).getDuration()<20) {
				  entity.addPotionEffect(new PotionEffect(PotionLoader.potionStand,60,0));
			  }
			  
	}
	
}
