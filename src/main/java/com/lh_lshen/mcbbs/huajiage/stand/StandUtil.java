package com.lh_lshen.mcbbs.huajiage.stand;

import java.util.ArrayList;
import java.util.List;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandBuffHandler;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandStageHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandBuffHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandStageHandler;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;

public class StandUtil {
	private static StandBase[] ArrowStand = {StandLoader.THE_WORLD,StandLoader.STAR_PLATINUM,StandLoader.HIEROPHANT_GREEN};
	public static StandBase getType(EntityLivingBase entity) {
		   if (entity.hasCapability(CapabilityStandHandler.STAND_TYPE, null)) {
	           StandHandler stand = entity.getCapability(CapabilityStandHandler.STAND_TYPE, null);
	    return StandLoader.getStand(stand.getStand());
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
	
	public static StandBuffHandler getStandBuffHandler(EntityLivingBase entity) {
		   if (entity.hasCapability(CapabilityStandBuffHandler.STAND_BUFF, null)) {
			   StandBuffHandler buff = entity.getCapability(CapabilityStandBuffHandler.STAND_BUFF,null);
	    return buff;
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
	
	public static int getStandBuffTime(EntityLivingBase entity) {
	    StandBuffHandler BuffHandler = StandUtil.getStandBuffHandler(entity);
	    if(BuffHandler!=null && BuffHandler.getTime()>0) {
		int ticks = BuffHandler.getTime();
		return ticks;
		}
	    return 0;
	}
	
	public static void standEffectLoad(EntityLivingBase entity) {
		  if(!entity.isPotionActive(PotionLoader.potionStand)) {
			  StandBase stand = StandUtil.getType(entity);
			  if(stand!=null) 
			  {
				  entity.addPotionEffect(new PotionEffect(PotionLoader.potionStand,60,0));
				  HuajiSoundPlayer.playToNearbyClient(entity, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,0.5f);
				  ServerUtil.sendPacketToNearbyPlayers(entity, new MessageParticleGenerator(entity.getPositionVector(), EnumParticleTypes.FIREWORKS_SPARK,60,3,1));
	  		  }
		  }
			  if(entity.isPotionActive(PotionLoader.potionStand)&&entity.getActivePotionEffect(PotionLoader.potionStand).getDuration()<20) {
				  entity.addPotionEffect(new PotionEffect(PotionLoader.potionStand,60,0));
			  }
			  
	}
	public static String getLocalName(String name) {
		for(StandBase type : StandLoader.STAND_LIST) {
    		if(name.equals(type.getName())) {
    			return type.getLocalName();
    			}
    		}return HuajiConstant.StandType.STAND_EMPTY;
    	}
	
	public static ResourceLocation getDiscTex(StandBase stand) {
		return new ResourceLocation(HuajiAge.MODID,"textures/items/disc_"+stand.getName()+".png");
	}
	
	public static List<StandBase> getArrowStands() {
    	List<StandBase> list = new ArrayList<StandBase>();
    	for(StandBase stand : ArrowStand) {
    		list.add(stand);
    	}
		return list;
	
	}
	
    public static StandBase getTypeWithIndex(int index) {
    	int id = index;
    	List<StandBase> stand_list_get = getArrowStands();
    	if(id>=0 && id<stand_list_get.size() || id>=stand_list_get.size()) {
        return stand_list_get.get(id%stand_list_get.size());
    	}
    	return null;
    }
//	public static class StandTypeHelper {
//		public static StandSkillType getStandSkillType(EntityLivingBase entity) {
//			
//			
//			return null;
//		}
		
//	}
	
//	public static void setStandData(EntityPlayer player, EnumStandtype stand) {
//		if(stand.getName()!=null) 
//			{
//				StandUtil.setStandData(player, stand.getName());
//			 }
//	}
	
//	public static void setStandData(EntityPlayer player, String stand) {
//		UUID uuid = player.getUniqueID();
//		StandUserWorldSavedData data = StandUserWorldSavedData.getGlobal(player.world);
//		data.add(stand, uuid);
//	}
//	
//	public static void removeStandData(EntityPlayer player) {
//		UUID uuid = player.getUniqueID();
//		StandUserWorldSavedData data = StandUserWorldSavedData.getGlobal(player.world);
//		data.remove(uuid);;
//	}
	
	
}
