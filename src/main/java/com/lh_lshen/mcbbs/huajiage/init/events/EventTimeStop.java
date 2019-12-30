package com.lh_lshen.mcbbs.huajiage.init.events;

import java.util.List;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageDioHitClient;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.ProjectileImpactEvent.Fireball;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventTimeStop {
	 @SubscribeEvent
     public static void onTimeStop(LivingUpdateEvent evt)
     {
		 EntityLivingBase target =evt.getEntityLiving(); 
		  if(NBTHelper.getEntityInteger(target,HuajiConstant.TIME_STOP)<=0&&NBTHelper.getEntityInteger(target,HuajiConstant.DIO_HIT)<=0&&NBTHelper.getEntityInteger(target,HuajiConstant.DIO_FLAG)<=0) 
		  {
			  return;
		  }
    	if(NBTHelper.getEntityInteger(target,HuajiConstant.TIME_STOP)>0) {	
    		target.getEntityData().setInteger(HuajiConstant.TIME_STOP,NBTHelper.getEntityInteger(target,HuajiConstant.TIME_STOP)-1);
    	if(target instanceof EntityPlayer) {
    		if(ConfigHuaji.Stands.allowTimeStopPlayer&&NBTHelper.getEntityInteger(target,HuajiConstant.THE_WORLD)<=0) {
    			int t=NBTHelper.getEntityInteger(target,HuajiConstant.TIME_STOP);
//    			if(!target.isPotionActive(MobEffects.SLOWNESS)||target.getActivePotionEffect(MobEffects.SLOWNESS).getAmplifier()<9) {
//   			    target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,t,9));
    		double tx=target.getEntityData().getDouble("huajiage.time_stop.x");
    		double ty=target.getEntityData().getDouble("huajiage.time_stop.y");
    		double tz=target.getEntityData().getDouble("huajiage.time_stop.z");
    		Vec3d pos=new Vec3d(tx, ty, tz);
    			if(((EntityPlayer)target).getPositionVector()!=pos) 
    			{
    				{((EntityPlayer)target).setPositionAndUpdate(tx,ty,tz);}
    				}
    			if(!target.isPotionActive(MobEffects.BLINDNESS)) {
    			target.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,t));}
//    			}
    										}
    							}
    		else {
    		evt.setCanceled(true);
    		}
    	}
    		if(NBTHelper.getEntityInteger(target,HuajiConstant.DIO_FLAG)>0) {	
        		target.getEntityData().setInteger(HuajiConstant.DIO_FLAG,NBTHelper.getEntityInteger(target,HuajiConstant.DIO_FLAG)-1);
        		}
    		if(NBTHelper.getEntityInteger(target,HuajiConstant.TIME_STOP)==0&&NBTHelper.getEntityInteger(target,HuajiConstant.DIO_HIT)>0) {	
        		target.getEntityData().setInteger(HuajiConstant.DIO_HIT,NBTHelper.getEntityInteger(target,HuajiConstant.DIO_HIT)-1);
        		if(target.ticksExisted%10==0) {
        			EntityPlayer p=target.getEntityWorld().getClosestPlayerToEntity(target, 100);
        			if(p!=null&&p!=target) {
        				target.attackEntityFrom(new EntityDamageSource(HuajiConstant.DIO_ATTACK, p), NBTHelper.getEntityInteger(target,HuajiConstant.DIO_HIT_EXTRA)+10);
                      }else {
                    	target.attackEntityFrom(new DamageSource(HuajiConstant.DIO_ATTACK), NBTHelper.getEntityInteger(target,HuajiConstant.DIO_HIT_EXTRA)+10);
                      }
        		}
        	}else {
        		 target.getEntityData().setInteger(HuajiConstant.DIO_HIT_EXTRA,0);
        	}
    		

     }
	 @SubscribeEvent
     public static void onTheWorldHit(AttackEntityEvent evt)
     {
	  EntityPlayer player = evt.getEntityPlayer();
	  Entity hit =evt.getTarget();
	  Vec3d targetPosition = player.getPositionVector();
	  if(NBTHelper.getEntityInteger(player,HuajiConstant.THE_WORLD)>0) {
		  player.heal(3f);
		  if(player.getCapability(CapabilityStandHandler.STAND_TYPE, null).getStand().equals(EnumStandtype.THE_WORLD.getName()))
		  {
			  EventStand.standPower(player);
		  }
 	      MessageDioHitClient msg1 = new MessageDioHitClient(targetPosition, false); 
 	      MessageDioHitClient msg2 =new MessageDioHitClient(targetPosition, true); 
         if(NBTHelper.getEntityInteger(player,HuajiConstant.DIO_FLAG)==0) {     
        	          ServerUtil.sendPacketToPlayers(player, msg1);
         	          player.getEntityData().setInteger(HuajiConstant.DIO_FLAG, 180);
         	          }
         if(NBTHelper.getEntityInteger(player,HuajiConstant.DIO_FLAG)<140&&NBTHelper.getEntityInteger(player,HuajiConstant.DIO_FLAG)>0) {   
        	          ServerUtil.sendPacketToPlayers(player, msg2);
         }
          if(hit instanceof EntityLivingBase) {  
             ((EntityLivingBase) hit).getEntityData().setInteger(HuajiConstant.DIO_HIT, 120);
            int a= NBTHelper.getEntityInteger(((EntityLivingBase) hit),HuajiConstant.DIO_HIT_EXTRA);
            ((EntityLivingBase) hit).getEntityData().setInteger(HuajiConstant.DIO_HIT_EXTRA,a+2);
               }
          evt.setCanceled(true);
            }
     }

  @SubscribeEvent
     public static void onTheWorld(LivingUpdateEvent evt)
     {
	  EntityLivingBase eater =evt.getEntityLiving(); 
//	  if(eater.getEntityData().getInteger(HuajiConstant.THE_WORLD)<=0) {
//		  return;
//	  }
    	if(eater.getEntityData().getInteger(HuajiConstant.THE_WORLD)>0) {
    		int t=eater.getEntityData().getInteger(HuajiConstant.THE_WORLD);
    		eater.getEntityData().setInteger(HuajiConstant.THE_WORLD, t-1);
    		List<Entity> arrows=eater.getEntityWorld().getEntitiesWithinAABB(Entity.class,eater.getEntityBoundingBox().grow(100,100,100));
    		if(arrows!=null) {
    			for(Entity i:arrows) {
    				
    				if(i instanceof IProjectile) {
    					if(i.getEntityData().getInteger(HuajiConstant.TIME_STOP)==0) {
    					i.getEntityData().setDouble("huajiage.v_x", i.motionX);
    					i.getEntityData().setDouble("huajiage.v_y", i.motionY);
    					i.getEntityData().setDouble("huajiage.v_z", i.motionZ);
    					i.getEntityData().setInteger(HuajiConstant.TIME_STOP,t);
    					}
    					
    					if(t>1&&!(i.updateBlocked)) {
    					i.motionX=0;
    					i.motionY=0;
    					i.motionZ=0;}
    					if(t==1) {
    						i.motionX=i.getEntityData().getDouble("huajiage.v_x");
        					i.motionY=i.getEntityData().getDouble("huajiage.v_y");
        					i.motionZ=i.getEntityData().getDouble("huajiage.v_z");
    					}
    				}
    				if(i instanceof EntityFireball) {
    					if(i.getEntityData().getInteger(HuajiConstant.TIME_STOP)==0) {
    					i.getEntityData().setDouble("huajiage.v_x", i.motionX);
    					i.getEntityData().setDouble("huajiage.v_y", i.motionY);
    					i.getEntityData().setDouble("huajiage.v_z", i.motionZ);
    					i.getEntityData().setInteger(HuajiConstant.TIME_STOP,t);
    					i.updateBlocked=true;
    					}
    					
    					if(t>1) {
    					i.motionX=0;
    					i.motionY=0;
    					i.motionZ=0;}
    					if(t==1) {
    						i.motionX=i.getEntityData().getDouble("huajiage.v_x");
        					i.motionY=i.getEntityData().getDouble("huajiage.v_y");
        					i.motionZ=i.getEntityData().getDouble("huajiage.v_z");
        					i.updateBlocked=false;
    					}
    				}
    				if(i instanceof EntityItem) {
    					if(i.getEntityData().getInteger(HuajiConstant.TIME_STOP)==0) {
    						Vec3d v=((EntityItem)i).getPositionVector();
    						i.getEntityData().setDouble("huajiage.v_x", i.motionX);
        					i.getEntityData().setDouble("huajiage.v_y", i.motionY);
        					i.getEntityData().setDouble("huajiage.v_z", i.motionZ);
    						i.getEntityData().setInteger(HuajiConstant.TIME_STOP,t);
    					}
    					if(t>1) {
        					i.motionX=0;
        					i.motionY=0;
        					i.motionZ=0;
        					i.setNoGravity(true);}
        					if(t==1) {
        						i.motionX=(( EntityItem)i).getEntityData().getDouble("huajiage.v_x");
            					i.motionY=(( EntityItem)i).getEntityData().getDouble("huajiage.v_y");
            					i.motionZ=(( EntityItem)i).getEntityData().getDouble("huajiage.v_z");
            					i.setNoGravity(false);
        					}
    				}
	    				if(i instanceof EntityLivingBase) {
	    					if(i!=eater&&i.getEntityData().getInteger(HuajiConstant.TIME_STOP)==0) {
	    					i.getEntityData().setInteger(HuajiConstant.TIME_STOP, t);
	    					if(i instanceof EntityPlayer) {
	    					i.getEntityData().setDouble("huajiage.time_stop.x", i.posX);
	    					i.getEntityData().setDouble("huajiage.time_stop.y", i.posY);
	    					i.getEntityData().setDouble("huajiage.time_stop.z", i.posZ);
	    					   }
	    					}
	    				}
	    			}
	    		}
    		}
     	}
	 
}
