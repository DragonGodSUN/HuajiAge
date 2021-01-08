package com.lh_lshen.mcbbs.huajiage.stand.resource;

import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelTheWorld;
import com.lh_lshen.mcbbs.huajiage.init.loaders.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiMovingSound;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundStand;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;

import java.util.List;
import java.util.Random;

public class ResStandTheWorld extends StandRes{
	public ResStandTheWorld() {
	}
	
	public ResStandTheWorld(String name) {
		super(name);
	}
//	@Override
//	public ResourceLocation getTexture() {
//		return HuajiConstant.StandTex.TEXTRUE_THE_WORLD;
//	}

	@Override
	public void doSoundPlay(Minecraft mc ,Entity entity,EntityLivingBase user) {
		List<SoundEvent> sounds = SoundStand.WORLD_SOUND_LIST;
		IExposedData data = StandUtil.getStandData(user);
		int size = sounds.size();
		int index = (int) MathHelper.nextFloat(new Random(), 0, size);
		if(null!=data&&index<size) {
			SoundEvent sound = sounds.get(index);
			mc.getSoundHandler().playSound(HuajiSoundPlayer.getMovingSound(entity, sound, SoundCategory.NEUTRAL, 1f));
			}
			HuajiMovingSound back_hits_double = new HuajiMovingSound(entity, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, SoundCategory.NEUTRAL);

			back_hits_double.setVolume(0.7f);
			back_hits_double.setLoop();
			mc.getSoundHandler().playSound(back_hits_double);
	}

	@Override
	public void doStandRender(EntityLivingBase entity) {
		ResourceLocation STAND_TEX = getTextureByData(entity);
		ModelStandBase model = getStandModelByData(entity);
		Minecraft.getMinecraft().getTextureManager().bindTexture(STAND_TEX);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
		model.setRotationAngles(0, 0, entity.ticksExisted, 0, -1, 1, entity ,0.5f,(float) (StandLoader.THE_WORLD.getSpeed()*1.5));
		if(entity.getActivePotionEffect(PotionLoader.potionStand).getDuration()<40) {
			model.effect(entity, 0, 0, entity.ticksExisted, 0, 0, 1f);
			model.renderFirst(0, -1f, -0.75f, 1f,0.3f);
		}else {
			model.effect(entity, 0, 0, entity.ticksExisted, 0, 0, 1f);
			model.renderFirst(0, -1f, -0.75f, 1f,0.6f);
		}
//		IExposedData data = StandUtil.getStandData(entity);
//		String stand = data.getStand();
//		String state = data.getState();
//		List<StandStateBase> list = StandStates.getStandStateListByStand(stand);
//		for(StandStateBase stand_state:list){
//			if(stand_state.getStateName().equals(state)){
//				stand_state.renderFirst(entity);
//				break;
//			}
//		}
	}

	@Override
	public ModelStandBase getStandModel() {
		return new ModelTheWorld();
	}

}
