package com.lh_lshen.mcbbs.huajiage.init.events;

import java.util.List;
import java.util.Random;

import com.ibm.icu.impl.duration.impl.DataRecord.EUnitVariant;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityLoader;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.StandBuffHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandStageHandler;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelTheWorld;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.entity.EntityRoadRoller;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.StandMovingSound;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.network.StandNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.messages.SyncExposedStandDataMessage;
import com.lh_lshen.mcbbs.huajiage.util.MotionHelper;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityMultiPart;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.end.DragonFightManager;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class EventStand {
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void renderStandFirst(RenderHandEvent event)
	{
		World world = Minecraft.getMinecraft().world;
		EntityPlayer player = Minecraft.getMinecraft().player;
		ItemStack stack = player.getHeldItemMainhand();
		StandBase stand =StandUtil.getType(player);
		int perspective = Minecraft.getMinecraft().gameSettings.thirdPersonView;
		boolean f1 = Minecraft.getMinecraft().gameSettings.hideGUI;

		if (stand!=null && stack.getItem() != ItemLoader.roadRoller &&player.isPotionActive(PotionLoader.potionStand) && perspective == 0 && !f1)
		{
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			EventStand.setLightmapDisabled(false);

			StandClientUtil.standRender(player);
			
			EventStand.setLightmapDisabled(true);

			if (perspective == 0 && !stand.isHandDisplay())
			{
				event.setCanceled(true);
			}

			GlStateManager.disableBlend();
			GlStateManager.scale(1, 1, 1);
			GlStateManager.popMatrix();

		}
	}
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
			  StandStageHandler stageHandler = StandUtil.getStandStageHandler(stand_owner);
			  stageHandler.setStage(1);
			  HuajiSoundPlayer.playToNearbyClient(stand_owner, SoundEvents.UI_TOAST_CHALLENGE_COMPLETE, 2f);
			  ServerUtil.sendPacketToNearbyPlayers(stand_owner, new MessageParticleGenerator(stand_owner.getPositionVector(), EnumParticleTypes.FIREWORKS_SPARK, 120, 3, 1));
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
			 boolean flag = data.isTriggered();

		 if(flag) {
			 if( !stand_owner.isPotionActive(PotionLoader.potionStand) || stand_owner.isPotionActive(PotionLoader.potionStand)&&stand_owner.getActivePotionEffect(PotionLoader.potionStand).getDuration()<=5) {
					 stand_owner.addPotionEffect(new PotionEffect(PotionLoader.potionStand , 5*20  ));
					 stand_owner.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS , 5*20, 1 ));
					 stand_owner.addPotionEffect(new PotionEffect(MobEffects.HUNGER , 5*20 , ConfigHuaji.Stands.allowStandPunish? 24 : 49 ));
					 if(ConfigHuaji.Stands.allowStandPunish) {
					 stand_owner.addPotionEffect(new PotionEffect(MobEffects.WITHER , 5*20 , 1 ));
					 }
				 	}
				 }
		  if(!flag) {
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
				if(type == null) {
					return;
				}
			  if(NBTHelper.getEntityInteger(evt.getEntityLiving(), HuajiConstant.Tags.THE_WORLD)>0) {
				StandUtil.standEffectLoad(evt.getEntityLiving());
			  }
			  float op =evt.getOriginalSpeed();
			  if(evt.getEntityPlayer().isPotionActive(PotionLoader.potionStand)) {
				  evt.setNewSpeed(op*25*type.getSpeed());
			  }else {
				  evt.setNewSpeed(op);
			  }
		  }
	  }
	  
	  @SubscribeEvent
	  public static void StandBuff(LivingUpdateEvent evt) {
		  EntityLivingBase entity = evt.getEntityLiving();
		  if(entity instanceof EntityPlayer) {
		  StandBuffHandler buff = StandUtil.getStandBuffHandler(entity);
		  if(buff.getTime()>0) {
		  buff.decreace();
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
	  
	  	@SideOnly(Side.CLIENT)
		private static void setLightmapDisabled(boolean disabled)
		{
			GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
	
			if (disabled)
			{
				GlStateManager.disableTexture2D();
			}
			else
			{
				GlStateManager.enableTexture2D();
			}
	
			GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
		}

	
	}
