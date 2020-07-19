package com.lh_lshen.mcbbs.huajiage.stand.resource;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelKillerQueen;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.states.ModelKillerQueenPunch;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiMovingSound;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundStand;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
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

public class ResStandKillerQueen extends StandRes{
	public ResStandKillerQueen() {
	}
	public ResStandKillerQueen(String name) {
		super(name);
	}
	@Override
	public ResourceLocation getTexture() {
		return HuajiConstant.StandTex.TEXTRUE_KILLER_QUEEN;
	}

	@Override
	public void doSoundPlay(Minecraft mc ,Entity entity,EntityLivingBase user) {
		List<SoundEvent> sounds = SoundStand.KILLER_QUEEN_SOUND_LIST;
		int size = sounds.size();
		int index = (int) MathHelper.nextFloat(new Random(), 0, size);
		if(index<size) {
			SoundEvent sound = sounds.get(index);
			mc.getSoundHandler().playSound(HuajiSoundPlayer.getMovingSound(entity, sound, SoundCategory.NEUTRAL, 0.6f));
			HuajiMovingSound hits = new HuajiMovingSound(user, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, SoundCategory.NEUTRAL);
			hits.setVolume(0.7f);
			hits.setLoop();
			mc.getSoundHandler().playSound(hits);
			}
	}

	@Override
	public void doStandRender(EntityLivingBase entity) {
		IExposedData data = StandUtil.getStandData(entity);
		boolean isPunch = CapabilityExposedData.States.PUNCH.getName().equals(data.getState());
		if (isPunch) {
			ResourceLocation STAND_TEX = getTexture();
			ModelStandBase model = getStandModelByData(entity);
			Minecraft.getMinecraft().getTextureManager().bindTexture(STAND_TEX);
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
			model.setRotationAngles(0, 0, entity.ticksExisted, 0, -1, 1, entity ,0.4f,1f);
			model.effect(entity, 0, 0, entity.ticksExisted, 0, 0, 1f);
			if(entity.getActivePotionEffect(PotionLoader.potionStand).getDuration()<40) {
				model.doHandRender(0, -1f, -0.75f, 1f,0.3f);
			}else {
				model.doHandRender(0, -1f, -0.75f, 1f,0.45f);
			}
			GlStateManager.translate(0,0,5);
		}
	}

	@Override
	public ModelStandBase getStandModel() {
		return new ModelKillerQueen();
	}

	@Override
	public ModelStandBase getStandModelByData(EntityLivingBase user) {
		String state = StandUtil.getStandState(user);
		switch (state){
			case "default":return new ModelKillerQueen();
			case "punch":return new ModelKillerQueenPunch();
		}
		return new ModelKillerQueen();
	}

}
