package com.lh_lshen.mcbbs.huajiage.stand.resource;

import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelHierophantGreen;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiMovingSound;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundStand;
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

public class ResStandHierophantGreen extends StandRes{
	public ResStandHierophantGreen() {
	}
	public ResStandHierophantGreen(String name) {
		super(name);
	}
//	@Override
//	public ResourceLocation getTexture() {
//		return HuajiConstant.StandTex.TEXTRUE_HIEROPANT_GREEN;
//	}

	@Override
	public void doSoundPlay(Minecraft mc ,Entity entity,EntityLivingBase user) {
		List<SoundEvent> sounds = SoundStand.HIEROPANT_SOUND_LIST;
		int size = sounds.size();
		int index = (int) MathHelper.nextFloat(new Random(), 0, size);
		if(index<size) {
			SoundEvent sound = sounds.get(index);
			mc.getSoundHandler().playSound(HuajiSoundPlayer.getMovingSound(entity, sound, SoundCategory.NEUTRAL, 1f));
			HuajiMovingSound hits = new HuajiMovingSound(entity, SoundEvents.WEATHER_RAIN, SoundCategory.NEUTRAL);
			hits.setVolume(1.0f);
			hits.setLoop();
			mc.getSoundHandler().playSound(hits);
			}
	}

	@Override
	public void doStandRender(EntityLivingBase entity) {
		ResourceLocation STAND_TEX = getTextureByData(entity);
		ModelStandBase model = getStandModelByData(entity);
		Minecraft.getMinecraft().getTextureManager().bindTexture(STAND_TEX);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
		model.effect(entity, 0, 0, entity.ticksExisted, 0, 0, 1f);
	}

	@Override
	public ModelStandBase getStandModel() {
		return new ModelHierophantGreen();
	}
//	@Override
//	public ModelStandBase getStandModelByData(EntityLivingBase user) {
//		String state = StandUtil.getStandState(user);
//		switch (state){
//			case "default":return new ModelHierophantGreen();
//			case "idle":return new ModelHierophantGreenIdle();
//		}
//		return new ModelHierophantGreen();
//	}

}
