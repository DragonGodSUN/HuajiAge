package com.lh_lshen.mcbbs.huajiage.init.events;

import java.util.List;
import java.util.Random;

import com.ibm.icu.impl.duration.impl.DataRecord.EUnitVariant;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.client.model.ModelTheWorld;
import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.entity.EntityRoadRoller;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.init.playsound.StandMovingSound;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.util.MotionHelper;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventStand {
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void renderStandFirst(RenderHandEvent event)
	{
		World world = Minecraft.getMinecraft().world;
		EntityPlayer player = Minecraft.getMinecraft().player;
		ItemStack stack = player.getHeldItemMainhand();
		int perspective = Minecraft.getMinecraft().gameSettings.thirdPersonView;
		boolean f1 = Minecraft.getMinecraft().gameSettings.hideGUI;

		if (stack.getItem() != ItemLoader.roadRoller &&player.isPotionActive(PotionLoader.potionStand) && perspective == 0 && !f1)
		{
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			EventStand.setLightmapDisabled(false);

			standRender(player);
			
			EventStand.setLightmapDisabled(true);

			if (perspective == 0)
			{
				event.setCanceled(true);
			}

			GlStateManager.disableBlend();
			GlStateManager.scale(1, 1, 1);
			GlStateManager.popMatrix();

		}
	}
	 @SubscribeEvent
	  public static void onTheWorldStand(LivingUpdateEvent evt)
	  {
		  EntityLivingBase eater =evt.getEntityLiving();
			  if(!eater.isPotionActive(PotionLoader.potionStand)) {
			  return;
		  }
		  if(eater.isPotionActive(PotionLoader.potionStand)) {
			  doStandPower(eater);
		  }
 	  }
	  @SubscribeEvent
	  public static void onBreaking(PlayerEvent.BreakSpeed evt)
	  {
		  if(ConfigHuaji.Stands.allowTheWorldDestory) {
			  EnumStandtype type = getType(evt.getEntityLiving());
				if(type == null) {
					return;
				}
			  if(NBTHelper.getEntityInteger(evt.getEntityLiving(), HuajiConstant.THE_WORLD)>0) {
				EventStand.standPower(evt.getEntityLiving());
			  }
			  float op =evt.getOriginalSpeed();
			  if(evt.getEntityPlayer().isPotionActive(PotionLoader.potionStand)) {
				  evt.setNewSpeed(op*25*type.getSpeed());
			  }else {
				  evt.setNewSpeed(op);
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
//	  ================================Stand Settings==========================================================
	  
	  
	  
	  public static void standPower(EntityLivingBase entity) {
			  if(!entity.isPotionActive(PotionLoader.potionStand)) {
				  entity.addPotionEffect(new PotionEffect(PotionLoader.potionStand,60,0));
				  HuajiSoundPlayer.playToNearbyClient(entity, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,0.5f);
				  ServerUtil.sendPacketToNearbyPlayers(entity, new MessageParticleGenerator(entity.getPositionVector(), EnumParticleTypes.FIREWORKS_SPARK));
			  }
			  if(entity.isPotionActive(PotionLoader.potionStand)&&entity.getActivePotionEffect(PotionLoader.potionStand).getDuration()<20) {
				  entity.addPotionEffect(new PotionEffect(PotionLoader.potionStand,60,0));
			  }
			  
	}
	  
	  
	  
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
	
	
	
    public static EnumStandtype getType(EntityLivingBase entity) {
    	   if (entity.hasCapability(CapabilityStandHandler.STAND_TYPE, null)) {
               StandHandler stand = entity.getCapability(CapabilityStandHandler.STAND_TYPE, null);
        return EnumStandtype.getType(stand.getStand());
    	   }
    	return null;
    }
    
    
    
	private static void standRender(EntityLivingBase entity) {
		EnumStandtype type = getType(entity);
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
	
	
	
	private static void doStandPower(EntityLivingBase eater) {
		EnumStandtype type = getType(eater);
		if(type == null) {
			return;
		}
		List<Entity> entityCllection = eater.getEntityWorld().getEntitiesWithinAABB(Entity.class, eater.getEntityBoundingBox().grow(type.getDistance()));
		if(entityCllection == null) {
			return;
		}
		switch(type) {
		case THE_WORLD:{
					for(Entity i:entityCllection) {
							  if(i instanceof IProjectile || i instanceof EntityFireball) {
								  Vec3d back = MotionHelper.getVectorEntityEye(eater, i);
								  if(!(i.motionX==0 && i.motionY==0 && i.motionZ==0)) {
									  	  i.motionX=(type.getDamage()/10)*back.x;
										  i.motionY=(type.getDamage()/10)*back.y;
										  i.motionZ=(type.getDamage()/10)*back.z;
									  }
								}

							  if(i instanceof EntityLivingBase) {
								  EntityLivingBase target=(EntityLivingBase)i;
									  if(target!=eater) {
										  	Vec3d back = MotionHelper.getVectorEntity(eater, target);
										  	if(NBTHelper.getEntityInteger(target, HuajiConstant.TIME_STOP)>0&&NBTHelper.getEntityInteger(target, HuajiConstant.DIO_HIT)<60) {
												  NBTHelper.setEntityInteger(target, HuajiConstant.DIO_HIT, 60);
											  }else {
												  if(eater instanceof EntityPlayer) {
													  EntityPlayer player =(EntityPlayer) eater;
													  target.attackEntityFrom(DamageSource.causePlayerDamage(player), 10f);
											  		}else {
											  		  target.attackEntityFrom(DamageSource.causeIndirectDamage(eater, eater), type.getDamage());
											  		}
											  }
										  if(eater.ticksExisted%3==0) {
											  HuajiSoundPlayer.playToNearbyClient(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.25f);
										  }
										  if(eater.ticksExisted%2==0) {
										  eater.world.playEvent(2001, target.getPosition().add(0, target.getPositionEyes(target.ticksExisted).y-target.getPosition().getY(), 0), Blocks.OBSIDIAN.getStateId(Blocks.OBSIDIAN.getStateFromMeta(0)));
										  }
										  target.motionX=back.x;
										  target.motionY=back.y;
										  target.motionZ=back.z;
														  }
												  }
											}
						break;
						}
				}
		}
	
	
	
		
		
	
	}
