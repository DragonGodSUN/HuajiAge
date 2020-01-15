package com.lh_lshen.mcbbs.huajiage.stand;

import java.util.Random;
import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityLoader;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandStageHandler;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandStageHandler;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelTheWorld;
import com.lh_lshen.mcbbs.huajiage.data.StandUserWorldSavedData;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.init.playsound.StandMovingSound;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

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

	public static StandChargeHandler getChargeHandler(EntityLivingBase entity) {
		   if (entity.hasCapability(CapabilityStandChargeHandler.STAND_CHARGE, null)) {
			   StandChargeHandler charge = entity.getCapability(CapabilityStandChargeHandler.STAND_CHARGE,null);
	    return charge;
		   }
		return null;
	}
	
	public static int getCharge(EntityLivingBase entity,StandChargeHandler charge) {
		if(null != charge) {
			return charge.getChargeValue();
		}
		return 0;
	}
	
	public static int getChargeMax(EntityLivingBase entity,StandChargeHandler charge) {
		if(null != charge) {
			return charge.getMaxValue();
		}
		return 3000;
	}
	
	public static StandStageHandler getStandStageHandler(EntityLivingBase entity) {
		   if (entity.hasCapability(CapabilityStandStageHandler.STAND_STAGE, null)) {
			   StandStageHandler stage = entity.getCapability(CapabilityStandStageHandler.STAND_STAGE,null);
	    return stage;
		   }
		return null;
	}
	
	public static int getStandStage(EntityLivingBase entity) {
	    StandStageHandler stageHandler = StandUtil.getStandStageHandler(entity);
	    if(stageHandler!=null) {
		int stage = stageHandler.getStage();
		return stage;
		}
	    return 0;
	}
	
	public static void standPower(EntityLivingBase entity) {
			  if(!entity.isPotionActive(PotionLoader.potionStand)) {
				  IExposedData data = entity.getCapability(CapabilityLoader.EXPOSED_DATA, null);
				  StandHandler standHandler = entity.getCapability(CapabilityStandHandler.STAND_TYPE, null);
				  data.setStand(standHandler.getStand());
				  data.setTrigger(true);
				  entity.addPotionEffect(new PotionEffect(PotionLoader.potionStand,60,0));
				  HuajiSoundPlayer.playToNearbyClient(entity, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,0.5f);
				  ServerUtil.sendPacketToNearbyPlayers(entity, new MessageParticleGenerator(entity.getPositionVector(), EnumParticleTypes.FIREWORKS_SPARK,60,3,1));
			  }
			  if(entity.isPotionActive(PotionLoader.potionStand)&&entity.getActivePotionEffect(PotionLoader.potionStand).getDuration()<20) {
				  entity.addPotionEffect(new PotionEffect(PotionLoader.potionStand,60,0));
			  }
			  
	}
	
	public static void setStandData(EntityPlayer player, EnumStandtype stand) {
		if(stand.getName()!=null) 
			{
				StandUtil.setStandData(player, stand.getName());
			 }
	}
	
	public static void setStandData(EntityPlayer player, String stand) {
		UUID uuid = player.getUniqueID();
		StandUserWorldSavedData data = StandUserWorldSavedData.getGlobal(player.world);
		data.add(stand, uuid);
	}
	
	public static void removeStandData(EntityPlayer player) {
		UUID uuid = player.getUniqueID();
		StandUserWorldSavedData data = StandUserWorldSavedData.getGlobal(player.world);
		data.remove(uuid);;
	}
	
	
}
