package com.lh_lshen.mcbbs.huajiage.stand;

import java.util.List;
import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelHierophantGreen;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStarPlatinum;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelTheWorld;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiMovingSound;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundStand;
import com.lh_lshen.mcbbs.huajiage.init.playsound.StandMovingSound;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.helper.instance.StandBase;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
public class StandClientUtil {
//	@SideOnly(Side.CLIENT)
	public static void standUpSound(Minecraft mc ,Entity user ,String stand_type ) 
	{
		Random random = new Random();
//		List<StandBase> stand = StandLoader.STAND_LIST;
		switch(stand_type)
		{
		case "the_world" :
		{
			List<SoundEvent> sounds = SoundStand.WORLD_SOUND_LIST;
			int size = sounds.size();
			int index = (int) MathHelper.nextFloat(new Random(), 0, size);
			if(index<size) {
				SoundEvent sound = sounds.get(index);
				mc.getSoundHandler().playSound(HuajiSoundPlayer.getMovingSound(user, sound, SoundCategory.NEUTRAL, 1f));
				}
				HuajiMovingSound back_hits_double = new HuajiMovingSound(user, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, SoundCategory.NEUTRAL);
				back_hits_double.setVolume(0.7f);
				back_hits_double.setLoop();
				mc.getSoundHandler().playSound(back_hits_double);
				break;
			}
		case "star_platinum" :
		{
			List<SoundEvent> sounds = SoundStand.STAR_SOUND_LIST;
			int size = sounds.size();
			int index = (int) MathHelper.nextFloat(new Random(), 0, size);
			if(index<size) {
					SoundEvent sound = sounds.get(index);
					Minecraft.getMinecraft().getSoundHandler().playSound(HuajiSoundPlayer.getMovingSound(user, sound, SoundCategory.NEUTRAL, 1f));
				}
				HuajiMovingSound back = new HuajiMovingSound(user, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, SoundCategory.NEUTRAL);
				back.setVolume(0.7f);
				back.setLoop();
				mc.getSoundHandler().playSound(back);
				break;
			}
		case "hierophant_green" :
			{
				List<SoundEvent> sounds = SoundStand.HIEROPANT_SOUND_LIST;
				int size = sounds.size();
				int index = (int) MathHelper.nextFloat(new Random(), 0, size);
				if(index<size) {
					SoundEvent sound = sounds.get(index);
					mc.getSoundHandler().playSound(HuajiSoundPlayer.getMovingSound(user, sound, SoundCategory.NEUTRAL, 1f));
					HuajiMovingSound hits = new HuajiMovingSound(user, SoundEvents.WEATHER_RAIN, SoundCategory.NEUTRAL);
					hits.setVolume(1.0f);
					hits.setLoop();
					mc.getSoundHandler().playSound(hits);
				}
				break;
			}	
		default:
				break;
				
			}
		
		}
	public static ModelStandBase getModel(String name) {
		switch(name) {
		case("the_world"):
		return new ModelTheWorld();
		case("star_platinum"):
		return new ModelStarPlatinum();
		case("hierophant_green"):
		return new ModelHierophantGreen();
		}
		return new ModelTheWorld();
	}
	
	public static ResourceLocation getTex(String name) {
		switch(name) {
		case("the_world"):
		return HuajiConstant.StandTex.TEXTRUE_THE_WORLD;
		case("star_platinum"):
		return HuajiConstant.StandTex.TEXTRUE_STAR_PLATINUM;
		case("hierophant_green"):
		return HuajiConstant.StandTex.TEXTRUE_HIEROPANT_GREEN;
		}
		return HuajiConstant.StandTex.TEXTRUE_THE_WORLD;
	}
	
    public static void standRender(EntityLivingBase entity) {
		StandBase type = StandUtil.getType(entity);
		if(type != null) {
		ResourceLocation STAND_TEX = new ResourceLocation(HuajiAge.MODID,type.getTexPath());
		ModelStandBase model = getModel(type.getName());
		Minecraft.getMinecraft().getTextureManager().bindTexture(STAND_TEX);
		switch(type.getName()) {
		case "the_world":
		case "star_platinum":
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
			model.setRotationAngles(0, 0, entity.ticksExisted, 0, -1, 1, entity ,0.5f,(float) (type.getSpeed()*1.5));
			if(entity.getActivePotionEffect(PotionLoader.potionStand).getDuration()<40) {
				model.effect(entity, 0, 0, entity.ticksExisted, 0, 0, 1f);
				model.doHandRender(0, -1f, -0.75f, 1f,0.3f);
			}else {
				model.effect(entity, 0, 0, entity.ticksExisted, 0, 0, 1f);
				model.doHandRender(0, -1f, -0.75f, 1f,0.6f);
			}
			break;
		 case "hierophant_green":
			 model.effect(entity, 0, 0, entity.ticksExisted, 0, 0, 1f);
			break;
		default:
			break;
			}
		}
		
	}
}
