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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class StandClientUtil {
	public static void standUpSound(EntityPlayer player ,String stand_type ) 
	{
		Random random = new Random();
		EnumStandtype stand = EnumStandtype.getType(stand_type);
		switch(stand)
		{
		case THE_WORLD :
			StandMovingSound sound_hit = new StandMovingSound(player, SoundLoader.STAND_THE_WORLD_HIT_1, SoundCategory.NEUTRAL);
			StandMovingSound back_hits = new StandMovingSound(player, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.NEUTRAL);
			StandMovingSound back_hits_double = new StandMovingSound(player, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, SoundCategory.NEUTRAL);
			if(random.nextDouble()>0.5) 
				{
					sound_hit.setSound( SoundLoader.STAND_THE_WORLD_HIT_2);
				}
			if(!player.isPotionActive(PotionLoader.potionStand))
				{
				sound_hit.setVolume(1f);
				Minecraft.getMinecraft().getSoundHandler().playSound(sound_hit);
				back_hits.setVolume(0.5f);
				back_hits.setBackSound(0.5f);
				back_hits.setLoop();
				Minecraft.getMinecraft().getSoundHandler().playSound(back_hits);
				back_hits_double.setVolume(0.7f);
				back_hits_double.setBackSound(0.5f);
				back_hits_double.setLoop();
				Minecraft.getMinecraft().getSoundHandler().playSound(back_hits_double);
				}
				break;
			}
		
		}

	
    public static void standRender(EntityLivingBase entity) {
		EnumStandtype type = StandUtil.getType(entity);
		if(type == null) {
			return;
		}
		ResourceLocation STAND_TEX = new ResourceLocation(HuajiAge.MODID,type.getTex());
		switch(type) {
		case THE_WORLD:
			ModelTheWorld THE_WORLD_MODEL = (ModelTheWorld) type.newModel();
			Minecraft.getMinecraft().getTextureManager().bindTexture(STAND_TEX);
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
			THE_WORLD_MODEL.setRotationAngles(0, 0, entity.ticksExisted, 0, 0, 1, entity ,0.5f,type.getSpeed());
			if(entity.getActivePotionEffect(PotionLoader.potionStand).getDuration()<40) {
				THE_WORLD_MODEL.doHandRender(0, -1f, -0.75f, 1f,0.3f);
			}else {
				THE_WORLD_MODEL.doHandRender(0, -1f, -0.75f, 1f,0.6f);
			}
			break;
		}
		
	}
}