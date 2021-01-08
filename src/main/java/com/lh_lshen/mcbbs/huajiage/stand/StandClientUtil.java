package com.lh_lshen.mcbbs.huajiage.stand;

import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.HAModelFactory;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelTheWorld;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class StandClientUtil {
//	@SideOnly(Side.CLIENT)

	public static void standUpSound(Minecraft mc ,Entity user ,String stand_type ) 
	{
		StandBase stand = StandLoader.getStand(stand_type);
		if(stand!=null && stand.getBindingRes()!=null) {
			stand.getBindingRes().doSoundPlay(mc, user);
			}
		}

	public static void standUpSound(Minecraft mc ,Entity entity ,EntityLivingBase user )
	{
		StandBase stand = StandUtil.getType(user);
		if(stand!=null) {
			stand.getBindingRes().doSoundPlay(mc, entity,user);
		}
	}
	public static ModelStandBase getModel(String stand_type) {
//		switch(name) {
//		case("the_world"):
//		return new ModelTheWorld();
//		case("star_platinum"):
//		return new ModelStarPlatinum();
//		case("hierophant_green"):
//		return new ModelHierophantGreen();
//		}
//		return new ModelTheWorld();
		StandBase stand = StandLoader.getStand(stand_type);
		if(stand!=null ) {
		return stand.getBindingRes().getStandModel();
		}
		return new ModelTheWorld();
	}

	public static ModelStandBase getModelByData(EntityLivingBase user, StandBase stand) {
		if(stand!=null) {
			return stand.getBindingRes().getStandModelByData(user);
		}
		return new ModelTheWorld();
	}
	
	public static ResourceLocation getTex(String stand_type) {
//		switch(name) {
//		case("the_world"):
//		return HuajiConstant.StandTex.TEXTRUE_THE_WORLD;
//		case("star_platinum"):
//		return HuajiConstant.StandTex.TEXTRUE_STAR_PLATINUM;
//		case("hierophant_green"):
//		return HuajiConstant.StandTex.TEXTRUE_HIEROPANT_GREEN;
//		}
		StandBase stand = StandLoader.getStand(stand_type);
		return stand.getBindingRes().getTextureByData("default");
	}

	public static ResourceLocation getTex(String stand_type, String state) {
		StandBase stand = StandLoader.getStand(stand_type);
		return stand.getBindingRes().getTextureByData(state);
	}

	public static ResourceLocation getTexByID(EntityLivingBase entity) {
		IExposedData data = StandUtil.getStandData(entity);
		if(data!=null){
			String stand = data.getStand();
			String state = data.getState();
			if(!data.getModel().equals(StandLoader.EMPTY)){
				return HAModelFactory.getTexture(data.getModel());
			}
			return getTex(stand,state);
		}
		return new ResourceLocation(HuajiConstant.StandModels.DEFAULT_MODEL_ID);
	}
	
    public static void standRender(EntityLivingBase entity) {
		StandBase stand = StandUtil.getType(entity);
		if(stand != null) {
			stand.getBindingRes().doStandRender(entity);
//		ResourceLocation STAND_TEX = new ResourceLocation(HuajiAge.MODID,type.getTexPath());
//		ModelStandBase model = getModel(type.getName());
//		Minecraft.getMinecraft().getTextureManager().bindTexture(STAND_TEX);
//		switch(type.getName()) {
//		case "the_world":
//		case "star_platinum":
//			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
//			model.setRotationAngles(0, 0, entity.ticksExisted, 0, -1, 1, entity ,0.5f,(float) (type.getSpeed()*1.5));
//			if(entity.getActivePotionEffect(PotionLoader.potionStand).getDuration()<40) {
//				model.effect(entity, 0, 0, entity.ticksExisted, 0, 0, 1f);
//				model.doHandRender(0, -1f, -0.75f, 1f,0.3f);
//			}else {
//				model.effect(entity, 0, 0, entity.ticksExisted, 0, 0, 1f);
//				model.doHandRender(0, -1f, -0.75f, 1f,0.6f);
//			}
//			break;
//		 case "hierophant_green":
//			 model.effect(entity, 0, 0, entity.ticksExisted, 0, 0, 1f);
//			break;
//		default:
//			break;
//			}
		}
	}
	public static ModelStandBase getModelByID(String id){
		if(HAModelFactory.hasModel(id)){
			return HAModelFactory.newInstance(id);
		}
		return new ModelTheWorld();
	}

	public static Pair<String,Number> getRepeatSounds(String sound){
		if (sound != null && !sound.equals("") && sound.contains("-")) {
			String[] objects = sound.split("-");
			return new MutablePair<>(objects[0], NumberUtils.createNumber(objects[1]));
		}
		return null;
	}
}
