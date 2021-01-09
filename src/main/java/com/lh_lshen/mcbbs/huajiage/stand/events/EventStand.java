package com.lh_lshen.mcbbs.huajiage.stand.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.loaders.CapabilityLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.stand.StandStates;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class EventStand {

	 @SubscribeEvent
	  public static void onStand(LivingUpdateEvent evt)
	  {
		  EntityLivingBase stand_owner =evt.getEntityLiving();
			  if(!stand_owner.isPotionActive(PotionLoader.potionStand)) {
			  return;
		  }
		  if(stand_owner.isPotionActive(PotionLoader.potionStand)) {
			  StandBase type = StandUtil.getType(stand_owner);
			  if(type != null) {
			  type.doStandPower(stand_owner);
			  }
		  }
 	  }
	 @SubscribeEvent
	  public static void standUpgrade(LivingUpdateEvent evt)
	  {
		  EntityLivingBase stand_owner =evt.getEntityLiving();
		  int t = NBTHelper.getEntityInteger(stand_owner, HuajiConstant.Tags.SINGULARITY);
		  if(t>0) {
			  NBTHelper.setEntityInteger(stand_owner, HuajiConstant.Tags.SINGULARITY, t-1);
			  if(stand_owner.ticksExisted%10==0) {
			  stand_owner.attackEntityFrom(new DamageSource(HuajiConstant.DamageSource.SINGULARITY_DAMAGE), 18);
			  }
			  if(stand_owner.getHealth()<=0) {
				  return;
			  }
		  }
		  if(t==3) {
//			  StandStageHandler stageHandler = StandUtil.getStandStageHandler(stand_owner);
			  IExposedData data = StandUtil.getStandData(stand_owner);
			  if (data != null) {
				  data.setStage(1);
				  HuajiSoundPlayer.playToNearbyClient(stand_owner, SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 2f);
				  ServerUtil.sendPacketToNearbyPlayers(stand_owner, new MessageParticleGenerator(stand_owner.getPositionVector(), EnumParticleTypes.FIREWORKS_SPARK, 120, 3, 1));
			  }
		  }
		  if(t==1) {
			  stand_owner.attackEntityFrom(DamageSource.OUT_OF_WORLD, 999999);
			  if(stand_owner instanceof EntityPlayer&&((EntityPlayer)stand_owner).isCreative()) {
				  stand_owner.setDead();
			  }
		  }
	  }
	 
	 @SubscribeEvent
	  public static void standPotion(LivingUpdateEvent evt)
	  {
		 EntityLivingBase stand_owner =evt.getEntityLiving();
		 IExposedData data = stand_owner.getCapability(CapabilityLoader.EXPOSED_DATA, null);
		 if(data!=null && !data.getStand().equals(StandLoader.EMPTY)) {
		 	StandStateBase state_base = StandStates.getStandState(data.getStand(),data.getState());
			 boolean flag = data.isTriggered()&&state_base!=null;
			 if(flag&&!stand_owner.world.isRemote) {
				 if( !stand_owner.isPotionActive(PotionLoader.potionStand) || stand_owner.isPotionActive(PotionLoader.potionStand)&&stand_owner.getActivePotionEffect(PotionLoader.potionStand).getDuration()<=5) {
					 state_base.doTaskOutOfTime(stand_owner);
					 }
//					 stand_owner.addPotionEffect(new PotionEffect(PotionLoader.potionStand , 5*20  ));
//					 stand_owner.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS , 5*20, 1 ));
//					 stand_owner.addPotionEffect(new PotionEffect(MobEffects.HUNGER , 5*20 , ConfigHuaji.Stands.allowStandPunish? 24 : 49 ));
//					 if(ConfigHuaji.Stands.allowStandPunish) {
//					 stand_owner.addPotionEffect(new PotionEffect(MobEffects.WITHER , 5*20 , 1 ));
//					 }
				 }
		  if(!flag&&!stand_owner.world.isRemote) {
			    if(stand_owner.isPotionActive(PotionLoader.potionStand)) {
				   stand_owner.removePotionEffect(PotionLoader.potionStand);
			 }
			 }
		 }
		 
	  	}
	 
	  @SubscribeEvent
	  public static void onBreaking(PlayerEvent.BreakSpeed evt)
	  {
		  if(ConfigHuaji.Stands.allowTheWorldDestory) {
			  StandBase type = StandUtil.getType(evt.getEntityLiving());
			  IExposedData data = StandUtil.getStandData(evt.getEntityLiving());
			  boolean isIdle = data.getState().equals(CapabilityExposedData.States.IDLE.getName());
				if(type == null || data==null) {
					return;
				}
			  if(NBTHelper.getEntityInteger(evt.getEntityLiving(), HuajiConstant.Tags.THE_WORLD)>0) {
				StandUtil.standEffectLoad(evt.getEntityLiving(),false);
			  }
			  float op =evt.getOriginalSpeed();
			  if(evt.getEntityPlayer().isPotionActive(PotionLoader.potionStand)) {
				  evt.setNewSpeed((isIdle?0.1f:1f)*op*25*type.getSpeed());
			  }else {
				  evt.setNewSpeed(op);
			  }
		  }
	  }
	  
	  @SubscribeEvent
	  public static void StandBuff(LivingUpdateEvent evt) {
		  EntityLivingBase entity = evt.getEntityLiving();
		  if(entity instanceof EntityPlayer) {
		  StandChargeHandler chargeHandler = StandUtil.getChargeHandler(entity);
		  if(chargeHandler.getBuffer()>0) {
		  chargeHandler.bufferDecreace();
		  }else {
			  return;
		  }
	  }
	}
	  
//	  @SubscribeEvent
//	  public static void StandSoundPlay(LivingHurtEvent evt) {
//		EntityLivingBase attacker = (EntityLivingBase) evt.getSource().getTrueSource();
//		if(attacker != null) {
//			if(attacker instanceof EntityPlayer) {
//				EntityPlayer player = (EntityPlayer) attacker;
//				String stand_type = player.getCapability(CapabilityStandHandler.STAND_TYPE, null).getStand();
//					if(stand_type.equals(EnumStandtype.EMPTY)) {
//						return;
//					}
//				}
//			}
//		  
//	  }
//	  
//	  ================================Tools==========================================================
	  
//	  	@SideOnly(Side.CLIENT)
//		private static void setLightmapDisabled(boolean disabled)
//		{
//			GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
//
//			if (disabled)
//			{
//				GlStateManager.disableTexture2D();
//			}
//			else
//			{
//				GlStateManager.enableTexture2D();
//			}
//
//			GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
//		}

	
	}
