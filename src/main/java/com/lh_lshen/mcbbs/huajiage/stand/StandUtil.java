package com.lh_lshen.mcbbs.huajiage.stand;

import com.google.common.collect.Lists;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.api.HuajiAgeAPI;
import com.lh_lshen.mcbbs.huajiage.api.IStand;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.client.model.custom.ModelStandJson;
import com.lh_lshen.mcbbs.huajiage.client.resources.CustomResourceLoader;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.loaders.CapabilityLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.stand.custom.StandCustom;
import com.lh_lshen.mcbbs.huajiage.stand.custom.StandCustomInfo;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

;

public class StandUtil {
	private static final StandBase[] ArrowStand = {StandLoader.THE_WORLD,StandLoader.STAR_PLATINUM,StandLoader.HIEROPHANT_GREEN,
			StandLoader.KILLER_QUEEN};
	public static StandBase getType(EntityLivingBase entity) {

		IExposedData data = StandUtil.getStandData(entity);
		if(data!= null){
			String stand = data.getStand();
			StandBase base = StandLoader.getStand(stand);
			return base;
		}
		return null;
	}

	public static StandHandler getStandHandler(EntityLivingBase entity) {
		   if (entity.hasCapability(CapabilityStandHandler.STAND_HANDLER, null)) {
			   StandHandler charge = entity.getCapability(CapabilityStandHandler.STAND_HANDLER,null);
	    return charge;
		   }
		return null;
	}
	
	public static int getCharge(EntityLivingBase entity, StandHandler charge) {
		if(null != charge) {
			return charge.getChargeValue();
		}
		return 0;
	}
	
	public static int getChargeMax(EntityLivingBase entity, StandHandler charge) {
		if(null != charge) {
			return charge.getMaxValue();
		}
		return 10000;
	}

	public static void setChargeMax(EntityLivingBase entity, StandHandler charge, int max) {
		if(null != charge) {
			charge.setMaxValue(max);
		}
	}

	public static void setChargeMax(EntityLivingBase entity,int max) {
		StandHandler chargeHandler = getStandHandler(entity);
		setChargeMax(entity,chargeHandler,max);
	}

//	public static StandBuffHandler getStandBuffHandler(EntityLivingBase entity) {
//		if (entity.hasCapability(CapabilityStandBuffHandler.STAND_BUFF, null)) {
//			StandBuffHandler buff = entity.getCapability(CapabilityStandBuffHandler.STAND_BUFF, null);
//			return buff;
//		}
//		return null;
//	}
	
	public static int getStandStage(EntityLivingBase entity) {
	    IExposedData data = StandUtil.getStandData(entity);
	    if(data!=null) {
		int stage = data.getStage();
		return stage;
		}
	    return 0;
	}

	public static IExposedData getStandData(EntityLivingBase entity) {
		if (entity.hasCapability(CapabilityLoader.EXPOSED_DATA, null)) {
			IExposedData data = entity.getCapability(CapabilityLoader.EXPOSED_DATA,null);
			return data;
		}
		return null;
	}

	public static String getStandState(EntityLivingBase entity) {
		IExposedData data = StandUtil.getStandData(entity);
		if (data!=null) {
			return data.getState();
		}
		return "default";
	}

	public static void setStandState(EntityLivingBase entity, String state) {
		IExposedData data = StandUtil.getStandData(entity);
		StandBase stand = StandUtil.getType(entity);
		if (data!=null && stand != null) {
			if(stand.chaeckState(state)){
				data.setState(state);
			}
		}
	}
	
	public static int getStandBuffTime(EntityLivingBase entity) {
	    StandHandler chargeHandler = StandUtil.getStandHandler(entity);
	    if(chargeHandler!=null && chargeHandler.getBuffer()>0) {
			return chargeHandler.getBuffer();
		}
		return 0;
	}
	
	public static void standEffectLoad(EntityLivingBase entity, boolean isLoadEffect) {
		  if(!entity.isPotionActive(PotionLoader.potionStand)) {
			  StandBase stand = StandUtil.getType(entity);
			  if(stand!=null) 
			  {
				  entity.addPotionEffect(new PotionEffect(PotionLoader.potionStand,60,0));
				  if (isLoadEffect) {
					  HuajiSoundPlayer.playToNearbyClient(entity, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,0.5f);
					  ServerUtil.sendPacketToNearbyPlayers(entity, new MessageParticleGenerator(entity.getPositionVector(), EnumParticleTypes.FIREWORKS_SPARK,60,3,1));
				  }
			  }
		  }
			  if(entity.isPotionActive(PotionLoader.potionStand)&&entity.getActivePotionEffect(PotionLoader.potionStand).getDuration()<20) {
				  entity.addPotionEffect(new PotionEffect(PotionLoader.potionStand,60,0));
			  }
			  
	}
	public static String getLocalName(String name) {
		for(IStand stand : HuajiAgeAPI.getStandList()) {
			StandBase type = (StandBase) stand;
    		if(name.equals(type.getName())) {
    			return type.getLocalName();
    			}
    		}return HuajiConstant.StandType.STAND_EMPTY;
    	}
	
	public static ResourceLocation getDiscTex(StandBase stand) {
		if(stand instanceof StandCustom){
			StandCustom custom = (StandCustom) stand;
			StandCustomInfo info = custom.getInfo();
			return new ResourceLocation(HuajiAge.MODID,"textures/items/"+info.getDisc().getPath()+".png");
		}
		return new ResourceLocation(HuajiAge.MODID,"textures/items/disc/disc_"+stand.getName()+".png");
	}
	
	public static List<StandBase> getArrowStands() {
		ArrayList<StandBase> standBases = new ArrayList<>(Arrays.asList(ArrowStand));
		standBases.addAll(getTagStands(EnumStandTag.StandTags.ARROW.getName()));
		return standBases;
	
	}

	public static List<StandBase> getCustomStands() {
		List<StandBase> list = Lists.newArrayList();
		for(String key : StandResourceLoader.CUSTOM_STAND_SERVER.keySet()){
			StandBase stand = StandLoader.getStand(key);
			if (stand!=null) {
				list.add(stand);
			}
		}
		return list;

	}

	public static List<StandBase> getTagStands(String tag) {
		List<StandBase> list = Lists.newArrayList();
		for(String key : StandResourceLoader.CUSTOM_STAND_SERVER.keySet()){
			StandBase stand = StandLoader.getStand(key);
			if (stand!=null && stand instanceof StandCustom) {
				if (((StandCustom) stand).getInfo().hasTag(tag)) {
					list.add(stand);
				}
			}
		}
		return list;
	}

	public static List<ModelStandJson> getTagModels(String tag) {
		List<ModelStandJson> list = Lists.newArrayList();
		for(String key : CustomResourceLoader.STAND_MODEL.getModelIdSet()){
			CustomResourceLoader.STAND_MODEL.getInfo(key).ifPresent((info)->
			{
				if(info.hasTag(tag)){
					list.add(CustomResourceLoader.STAND_MODEL.getModel(key).get());
				}
			});
		}
		return list;
	}
	
    public static StandBase getTypeWithIndex(int index) {
    	int id = index;
    	List<StandBase> stand_list_get = getArrowStands();
    	if(id >= 0 || id >= stand_list_get.size()) {
        return stand_list_get.get(id%stand_list_get.size());
    	}
    	return null;
    }

    public static boolean isStandMatch(EntityLivingBase user,StandBase stand){
		IExposedData data = StandUtil.getStandData(user);
		if (data!=null){
		StandBase standBase = StandLoader.getStand(data.getStand());
			return standBase!=null && standBase.equals(stand);
		}
		return false;
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
