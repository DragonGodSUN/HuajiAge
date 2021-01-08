package com.lh_lshen.mcbbs.huajiage.stand.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.helper.TimeStopHelper;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDioHitClient;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class EventTimeStop {
	 @SubscribeEvent
     public static void onTimeStop(LivingUpdateEvent evt)
     {
		 EntityLivingBase target =evt.getEntityLiving(); 
		  if(NBTHelper.getEntityInteger(target,HuajiConstant.Tags.TIME_STOP)<=0&&NBTHelper.getEntityInteger(target,HuajiConstant.Tags.DIO_HIT)<=0&&NBTHelper.getEntityInteger(target,HuajiConstant.Tags.DIO_FLAG)<=0) 
		  {
			  return;
		  }
    	if(NBTHelper.getEntityInteger(target,HuajiConstant.Tags.TIME_STOP)>0) {	
    		target.getEntityData().setInteger(HuajiConstant.Tags.TIME_STOP,NBTHelper.getEntityInteger(target,HuajiConstant.Tags.TIME_STOP)-1);
    	if(target instanceof EntityPlayer) {
    		if(ConfigHuaji.Stands.allowTimeStopPlayer&&NBTHelper.getEntityInteger(target,HuajiConstant.Tags.THE_WORLD)<=0) {
			int t=NBTHelper.getEntityInteger(target,HuajiConstant.Tags.TIME_STOP);
    		double tx=target.getEntityData().getDouble("huajiage.time_stop.x");
    		double ty=target.getEntityData().getDouble("huajiage.time_stop.y");
    		double tz=target.getEntityData().getDouble("huajiage.time_stop.z");
    		Vec3d pos=new Vec3d(tx, ty, tz);
    			if(((EntityPlayer)target).getPositionVector()!=pos) 
    			{
    				((EntityPlayer)target).setPositionAndUpdate(tx,ty,tz);
				}
    			if(!target.isPotionActive(MobEffects.BLINDNESS)) 
    			{
    				target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,t));
    			}
    										}
			}else {
    		evt.setCanceled(true);
    		}
    	}
    		if(NBTHelper.getEntityInteger(target,HuajiConstant.Tags.DIO_FLAG)>0) {	
        		target.getEntityData().setInteger(HuajiConstant.Tags.DIO_FLAG,NBTHelper.getEntityInteger(target,HuajiConstant.Tags.DIO_FLAG)-1);
        		}
    		if(NBTHelper.getEntityInteger(target,HuajiConstant.Tags.TIME_STOP)==0&&NBTHelper.getEntityInteger(target,HuajiConstant.Tags.DIO_HIT)>0) {	
        		target.getEntityData().setInteger(HuajiConstant.Tags.DIO_HIT,NBTHelper.getEntityInteger(target,HuajiConstant.Tags.DIO_HIT)-1);
        		if(target.ticksExisted%10==0) {
        			EntityPlayer p=target.getEntityWorld().getClosestPlayerToEntity(target, 100);
        			if(p!=null&&p!=target) {
        				if(target instanceof EntityDragon) {
        					EntityDragon d = (EntityDragon) target;
        					d.attackEntityFromPart(d.dragonPartHead,new EntityDamageSource(HuajiConstant.DamageSource.STAND_PUNCH_DAMAGE, p), NBTHelper.getEntityInteger(target,HuajiConstant.Tags.DIO_HIT_EXTRA)+10);
        				}else {
        				target.attackEntityFrom(new EntityDamageSource(HuajiConstant.DamageSource.STAND_PUNCH_DAMAGE, p).setIsThornsDamage(), NBTHelper.getEntityInteger(target,HuajiConstant.Tags.DIO_HIT_EXTRA)+10);
        				}
    				}else {
                    	target.attackEntityFrom(new DamageSource(HuajiConstant.DamageSource.STAND_PUNCH_DAMAGE), NBTHelper.getEntityInteger(target,HuajiConstant.Tags.DIO_HIT_EXTRA)+10);
                      }
        		}
        	}else {
        		 target.getEntityData().setInteger(HuajiConstant.Tags.DIO_HIT_EXTRA,0);
        	}
    		

     }
	 @SubscribeEvent
     public static void onTheWorldHit(AttackEntityEvent evt)
     {
	  EntityPlayer player = evt.getEntityPlayer();
	  Entity hit =evt.getTarget();
	  Vec3d targetPosition = player.getPositionVector();
	  IExposedData data = StandUtil.getStandData(player);
	  if(data != null && NBTHelper.getEntityInteger(player,HuajiConstant.Tags.THE_WORLD)>0) {
		  player.heal(3f);
		  boolean star =false ;
		  if(data.getStand().equals(StandLoader.STAR_PLATINUM.getName()))
		  {
			  star = true;
		  }
		  StandUtil.standEffectLoad(player);
		  if(!star) {
	 	      MessageDioHitClient msg1 = new MessageDioHitClient(targetPosition, false); 
	 	      MessageDioHitClient msg2 =new MessageDioHitClient(targetPosition, true); 
	         if(NBTHelper.getEntityInteger(player,HuajiConstant.Tags.DIO_FLAG)==0) {     
	        	          ServerUtil.sendPacketToPlayersStand(player, msg1);
	         	          player.getEntityData().setInteger(HuajiConstant.Tags.DIO_FLAG, 180);
	         	          }
	         if(NBTHelper.getEntityInteger(player,HuajiConstant.Tags.DIO_FLAG)<140&&NBTHelper.getEntityInteger(player,HuajiConstant.Tags.DIO_FLAG)>0) {   
	        	          ServerUtil.sendPacketToPlayersStand(player, msg2);
	         }
         }else {
        	 MessageParticleGenerator pacticle = new MessageParticleGenerator(targetPosition, EnumParticleTypes.FIREWORKS_SPARK, 60, 5, 1);
        	 ServerUtil.sendPacketToNearbyPlayers(player,pacticle);
        	 HuajiSoundPlayer.playToNearbyClient(player, SoundLoader.STAND_STAR_PLATINUM_REPEAT_1, 1f);
         }
          if(hit instanceof EntityLivingBase ) {  
              hit.getEntityData().setInteger(HuajiConstant.Tags.DIO_HIT, 120);
              int a= NBTHelper.getEntityInteger(hit,HuajiConstant.Tags.DIO_HIT_EXTRA);
        	  hit.getEntityData().setInteger(HuajiConstant.Tags.DIO_HIT_EXTRA,a+2);
               }
          evt.setCanceled(true);
            }
     }

  @SubscribeEvent
     public static void onTheWorld(LivingUpdateEvent evt)
     {
	  EntityLivingBase eater =evt.getEntityLiving(); 
//	  if(eater.getEntityData().getInteger(HuajiConstant.Tags.THE_WORLD)<=0) {
//		  return;
//	  }
		 if (eater.getEntityData().getInteger(HuajiConstant.Tags.THE_WORLD) > 0) {
			 int t = eater.getEntityData().getInteger(HuajiConstant.Tags.THE_WORLD);
			 eater.getEntityData().setInteger(HuajiConstant.Tags.THE_WORLD, t - 1);
			 int range = (int) eater.getEntityData().getDouble(HuajiConstant.Tags.TIME_STOP_RANGE);
			 List<Entity> arrows = range > 0 ? TimeStopHelper.getTagetsInRange(eater, range) : TimeStopHelper.getTagetsInRange(eater, 100);
			 if (!arrows.isEmpty()) {
				 for (Entity i : arrows) {

					 if (i instanceof IProjectile || i instanceof EntityFireball || i instanceof EntityTNTPrimed || i instanceof EntityShulkerBullet) {
						 if (i.getEntityData().getInteger(HuajiConstant.Tags.TIME_STOP) == 0) {
							 setMotionTag(i);
							 i.getEntityData().setInteger(HuajiConstant.Tags.TIME_STOP, t);
						 }

						 if (t > 1) {
							 i.motionX = 0;
							 i.motionY = 0;
							 i.motionZ = 0;
							 if (!(i instanceof IProjectile)) {
								 i.updateBlocked = true;
							 }
						 }
						 if (t == 1) {
							 triggerMotion(i);
							 if (!(i instanceof IProjectile)) {
								 i.updateBlocked = false;
							 }
						 }
					 }

					 if (i instanceof EntityItem) {
						 if (i.getEntityData().getInteger(HuajiConstant.Tags.TIME_STOP) == 0) {

							 setMotionTag(i);
							 i.getEntityData().setInteger(HuajiConstant.Tags.TIME_STOP, t);
						 }
						 if (t > 1) {
							 i.motionX = 0;
							 i.motionY = 0;
							 i.motionZ = 0;
							 i.rotationYaw = 0;
							 i.setNoGravity(true);
						 }
						 if (t == 1) {
							 triggerMotion(i);
							 ((EntityItem) i).rotationYaw = 0;
							 i.setNoGravity(false);
						 }
					 }

					 if (i instanceof EntityLivingBase) {
						 if (i != eater && i.getEntityData().getInteger(HuajiConstant.Tags.TIME_STOP) == 0) {
							 i.getEntityData().setInteger(HuajiConstant.Tags.TIME_STOP, t);
							 if (i instanceof EntityPlayer) {
								 setPosTag(i);
							 }
						 }
					 }

				 }
			 }
		 }
		 if (eater.getEntityData().getInteger(HuajiConstant.Tags.THE_WORLD) == 0) {
			 int range = (int) eater.getEntityData().getDouble(HuajiConstant.Tags.TIME_STOP_RANGE);
		 	if(range>50){
				TimeStopHelper.setEntityTimeStopRange(eater,50);
			}
		 }
	 }


  
  
  	private static void setPosTag(Entity entity) {
		NBTHelper.setEntityDouble(entity, "huajiage.time_stop.x", entity.posX);
		NBTHelper.setEntityDouble(entity, "huajiage.time_stop.y", entity.posY);
		NBTHelper.setEntityDouble(entity, "huajiage.time_stop.z", entity.posZ);
	}
  	private static void setMotionTag(Entity entity) {
		NBTHelper.setEntityDouble(entity, "huajiage.v_x", entity.motionX);
		NBTHelper.setEntityDouble(entity, "huajiage.v_y", entity.motionY);
		NBTHelper.setEntityDouble(entity, "huajiage.v_z", entity.motionZ);
	}
  	private static void triggerMotion(Entity entity) {
  		entity.motionX=entity.getEntityData().getDouble("huajiage.v_x");
		entity.motionY=entity.getEntityData().getDouble("huajiage.v_y");
		entity.motionZ=entity.getEntityData().getDouble("huajiage.v_z");
	}
	 
}
