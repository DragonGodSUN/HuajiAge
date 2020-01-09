package com.lh_lshen.mcbbs.huajiage.stand;

import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStarPlatinum;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelTheWorld;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiMovingSound;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.init.playsound.StandMovingSound;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;

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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
public class StandClientUtil {
//	@SideOnly(Side.CLIENT)
	public static void standUpSound(Minecraft mc ,Entity user ,String stand_type ) 
	{
		Random random = new Random();
		EnumStandtype stand = EnumStandtype.getType(stand_type);
		switch(stand)
		{
		case THE_WORLD :
		{
//			StandMovingSound sound_hit = new StandMovingSound(user, SoundLoader.STAND_THE_WORLD_HIT_1, SoundCategory.NEUTRAL);
//			StandMovingSound back_hits = new StandMovingSound(user, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.NEUTRAL);
			if(random.nextDouble()>0.5) 
				{
				mc.getSoundHandler().playSound(HuajiSoundPlayer.getMovingSound(user,  SoundLoader.STAND_THE_WORLD_HIT_1, SoundCategory.NEUTRAL, 1f));
				}else {
				mc.getSoundHandler().playSound(HuajiSoundPlayer.getMovingSound(user,  SoundLoader.STAND_THE_WORLD_HIT_2, SoundCategory.NEUTRAL, 1f));
				}
				HuajiMovingSound back_hits_double = new HuajiMovingSound(user, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, SoundCategory.NEUTRAL);
				back_hits_double.setVolume(0.7f);
				back_hits_double.setLoop();
				mc.getSoundHandler().playSound(back_hits_double);
//			if(!player.isPotionActive(PotionLoader.potionStand))
//				{
//		if(sound_hit.getSound()!=null) {
//				sound_hit.setVolume(1f);
//				Minecraft.getMinecraft().getSoundHandler().playSound(sound_hit);

//				back_hits.setVolume(0.5f);
//				back_hits.setBackSound(0.5f);
//				back_hits.setLoop();
//				Minecraft.getMinecraft().getSoundHandler().playSound(back_hits);
				
//			}
//				}
				break;
			}
		case STAR_PLATINUM :
		{
//			StandMovingSound sound_hit = new StandMovingSound(user, SoundLoader.STAND_STAR_PLATINUM_1, SoundCategory.NEUTRAL);
//			StandMovingSound back_hits = new StandMovingSound(user, SoundEvents.BLOCK_STONE_HIT, SoundCategory.NEUTRAL);
			if(random.nextDouble()<0.25) 
				{
				mc.getSoundHandler().playSound(HuajiSoundPlayer.getMovingSound(user,  SoundLoader.STAND_STAR_PLATINUM_1, SoundCategory.NEUTRAL, 1f));
				}else if(random.nextDouble()<0.5) 
				{
				mc.getSoundHandler().playSound(HuajiSoundPlayer.getMovingSound(user,  SoundLoader.STAND_STAR_PLATINUM_2, SoundCategory.NEUTRAL, 1f));
				}else if(random.nextDouble()<0.75)
				{
				mc.getSoundHandler().playSound(HuajiSoundPlayer.getMovingSound(user,  SoundLoader.STAND_STAR_PLATINUM_3, SoundCategory.NEUTRAL, 1f));
				}else
				{
					Minecraft.getMinecraft().getSoundHandler().playSound(HuajiSoundPlayer.getMovingSound(user,  SoundLoader.STAND_STAR_PLATINUM_4, SoundCategory.NEUTRAL, 1f));
				}
				HuajiMovingSound back = new HuajiMovingSound(user, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, SoundCategory.NEUTRAL);
				back.setVolume(0.7f);
				back.setLoop();
				mc.getSoundHandler().playSound(back);
//			if(!player.isPotionActive(PotionLoader.potionStand))
//				{
//		if(sound_hit.getSound()!=null) {
				
//				back_hits.setVolume(0.6f);
//				back_hits.setBackSound(0.6f);
//				back_hits.setLoop();
//				Minecraft.getMinecraft().getSoundHandler().playSound(back_hits);
				
//		}
//				}
				break;
			}
				
		default:
//			System.out.println();
				break;
			}
		
		}

	
    public static void standRender(EntityLivingBase entity) {
		EnumStandtype type = StandUtil.getType(entity);
		if(type != null) {
		ResourceLocation STAND_TEX = new ResourceLocation(HuajiAge.MODID,type.getTexPath());
		switch(type) {
		case THE_WORLD:
		case STAR_PLATINUM:
			ModelStandBase model = (ModelStandBase) type.newModel();
			Minecraft.getMinecraft().getTextureManager().bindTexture(STAND_TEX);
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
			model.setRotationAngles(0, 0, entity.ticksExisted, 0, 0, 1, entity ,0.5f,type.getSpeed());
			if(entity.getActivePotionEffect(PotionLoader.potionStand).getDuration()<40) {
				model.doHandRender(0, -1f, -0.75f, 1f,0.3f);
			}else {
				model.doHandRender(0, -1f, -0.75f, 1f,0.6f);
			}
			break;
//			ModelStarPlatinum STAR_PLATINUM = (ModelStarPlatinum) type.newModel();
//			Minecraft.getMinecraft().getTextureManager().bindTexture(STAND_TEX);
//			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
//			STAR_PLATINUM.setRotationAngles(0, 0, entity.ticksExisted, 0, 0, 1, entity ,0.5f,type.getSpeed());
//			if(entity.getActivePotionEffect(PotionLoader.potionStand).getDuration()<40) {
//				STAR_PLATINUM.doHandRender(0, -1f, -0.75f, 1f,0.3f);
//			}else {
//				STAR_PLATINUM.doHandRender(0, -1f, -0.75f, 1f,0.6f);
//			}
//			break;
		default:
			break;
			}
		}
		
	}
}
