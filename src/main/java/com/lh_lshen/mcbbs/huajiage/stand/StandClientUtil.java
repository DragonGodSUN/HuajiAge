package com.lh_lshen.mcbbs.huajiage.stand;

import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelHierophantGreen;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStarPlatinum;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelTheWorld;
import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
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
				break;
			}
		case STAR_PLATINUM :
		{
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
		EnumStandtype type = StandUtil.getType(entity);
		if(type != null) {
		ResourceLocation STAND_TEX = new ResourceLocation(HuajiAge.MODID,type.getTexPath());
		switch(type) {
		case THE_WORLD:
		case STAR_PLATINUM:
			ModelStandBase model = getModel(type.getName());
			Minecraft.getMinecraft().getTextureManager().bindTexture(STAND_TEX);
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
			model.setRotationAngles(0, 0, entity.ticksExisted, 0, -1, 1, entity ,0.5f,(float) (type.getSpeed()*1.5));
			if(entity.getActivePotionEffect(PotionLoader.potionStand).getDuration()<40) {
				model.doHandRender(0, -1f, -0.75f, 1f,0.3f);
			}else {
				model.doHandRender(0, -1f, -0.75f, 1f,0.6f);
			}
			break;
		default:
			break;
			}
		}
		
	}
}
