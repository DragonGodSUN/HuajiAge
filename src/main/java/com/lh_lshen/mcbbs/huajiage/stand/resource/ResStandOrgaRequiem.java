package com.lh_lshen.mcbbs.huajiage.stand.resource;

import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelOrgaRequiem;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
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

public class ResStandOrgaRequiem extends StandRes{
	public ResStandOrgaRequiem() {
	}
	public ResStandOrgaRequiem(String name) {
		super(name);
	}
	@Override
	public ResourceLocation getTexture() {
		return HuajiConstant.StandTex.TEXTRUE_ORGA_REQUIEM;
	}

	@Override
	public void doSoundPlay(Minecraft mc ,Entity entity,EntityLivingBase user) {
		List<SoundEvent> sounds = SoundStand.ORGA_SOUND_LIST;
		int size = sounds.size();
		int index = (int) MathHelper.nextFloat(new Random(), 0, size);
		if(index<size) {
			SoundEvent sound = sounds.get(index);
			mc.getSoundHandler().playSound(HuajiSoundPlayer.getMovingSound(entity, sound, SoundCategory.NEUTRAL, 0.6f));
			HuajiMovingSound hits = new HuajiMovingSound(entity, SoundEvents.ENTITY_PARROT_FLY, SoundCategory.NEUTRAL);
			hits.setVolume(1.0f);
			hits.setLoop();
			mc.getSoundHandler().playSound(hits);
			}
	}

	@Override
	public void doStandRender(EntityLivingBase entity) {
		ResourceLocation STAND_TEX = getTexture();
		ModelStandBase model = getStandModel();
		Minecraft.getMinecraft().getTextureManager().bindTexture(STAND_TEX);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
		model.effect(entity, 0, 0, entity.ticksExisted, 0, 0, 1f);
	}

	@Override
	public ModelStandBase getStandModel() {
		return new ModelOrgaRequiem();
	}

	@Override
	public ModelStandBase getStandModelByData(EntityLivingBase entity) {
		return new ModelOrgaRequiem();
	}
}
