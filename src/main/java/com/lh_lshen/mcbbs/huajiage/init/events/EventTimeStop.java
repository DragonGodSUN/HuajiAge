package com.lh_lshen.mcbbs.huajiage.init.events;

import java.util.List;

import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.network.MessageDioHitClient;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventTimeStop {
	 @SubscribeEvent
     public static void onTimeStop(LivingUpdateEvent evt)
     {
	  EntityLivingBase target =evt.getEntityLiving(); 
    		if(NBTHelper.getEntityInteger(target,"huajiage.time_stop")>0) {	
    		target.getEntityData().setInteger("huajiage.time_stop",NBTHelper.getEntityInteger(target,"huajiage.time_stop")-1);
    		if(target instanceof EntityPlayer) {
    			if(ConfigHuaji.Huaji.allowTimeStopPlayer) {
    			int t=NBTHelper.getEntityInteger(target,"huajiage.time_stop");
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
    		}else {
    		evt.setCanceled(true);
    		}
    		}
    		if(NBTHelper.getEntityInteger(target,"huajiage.dio_flag")>0) {	
        		target.getEntityData().setInteger("huajiage.dio_flag",NBTHelper.getEntityInteger(target,"huajiage.dio_flag")-1);
        		}
    		if(NBTHelper.getEntityInteger(target,"huajiage.time_stop")==0&&NBTHelper.getEntityInteger(target,"huajiage.dio_hit")>0) {	
        		target.getEntityData().setInteger("huajiage.dio_hit",NBTHelper.getEntityInteger(target,"huajiage.dio_hit")-1);
        		if(target.ticksExisted%10==0) {
        			EntityPlayer p=target.getEntityWorld().getClosestPlayerToEntity(target, 100);
        			if(p!=null&&p!=target) {
        				target.attackEntityFrom(new EntityDamageSource("huajiage.dio.hit", p), NBTHelper.getEntityInteger(target,"huajiage.dio_hit_extra")+10);
                      }else {
                    	target.attackEntityFrom(new DamageSource("huajiage.dio.hit"), NBTHelper.getEntityInteger(target,"huajiage.dio_hit_extra")+10);
                      }
        			
        		}
        	}else {
        		 target.getEntityData().setInteger("huajiage.dio_hit_extra",0);
        	}
    		

     }
	 @SubscribeEvent
     public static void onTheWorldHit(AttackEntityEvent evt)
     {
	  EntityPlayer player = evt.getEntityPlayer();
	  Entity hit =evt.getTarget();
	  Vec3d targetPosition = player.getPositionVector();
	  if(NBTHelper.getEntityInteger(player,"huajiage.the_world")>0) {
		  player.heal(3f);
//            try { 
//                int dimension = player.dimension;
//         	    MinecraftServer minecraftServer = player.getServer();
//         	    if(minecraftServer.getPlayerList().getPlayers()!=null) {
//         	    for (EntityPlayerMP player0 : minecraftServer.getPlayerList().getPlayers()) {
//        
         	    MessageDioHitClient msg1 = new MessageDioHitClient(targetPosition, false); 
         	    MessageDioHitClient msg2 =new MessageDioHitClient(targetPosition, true); 
//         	    // must generate a fresh message for every player!
//         	        if (dimension == player.dimension) {
         if(NBTHelper.getEntityInteger(player,"huajiage.dio_flag")==0) {     
//         	          HuajiAgeNetWorkHandler.sendTo(player0, msg1);
        	          ServerUtil.sendPacketToPlayers(player, msg1);
         	          player.getEntityData().setInteger("huajiage.dio_flag", 180);
         	          }
         if(NBTHelper.getEntityInteger(player,"huajiage.dio_flag")<140&&NBTHelper.getEntityInteger(player,"huajiage.dio_flag")>0) {   
        	          ServerUtil.sendPacketToPlayers(player, msg2);
         }
        
         	        
//         	        }
//         	      }
//         	    }
//                 }catch(Exception e) {e.printStackTrace();}
          if(hit instanceof EntityLivingBase) {  
             ((EntityLivingBase) hit).getEntityData().setInteger("huajiage.dio_hit", 120);
            int a= NBTHelper.getEntityInteger(((EntityLivingBase) hit),"huajiage.dio_hit_extra");
            ((EntityLivingBase) hit).getEntityData().setInteger("huajiage.dio_hit_extra",a+2);
               }
//          if(hit instanceof EntityRoadRoller) {  
//
//             int a= ((EntityRoadRoller) hit).getEntityData().getInteger("huajiage.dio_push");
//             ((EntityRoadRoller) hit).getEntityData().setInteger("huajiage.dio_push",a+2);
//                }
            }
             }

	  
	
	/* @SubscribeEvent
     public void onTimeStopItem(EntityEvent evt)
     {
	  Entity target =evt.getEntity();
	  EntityLivingBase tp=null;
	  List<Entity> targetp =target.getEntityWorld().getEntitiesWithinAABB(Entity.class, target.getEntityBoundingBox().grow(100)); 
    		for(Entity b:targetp){
    			if(b instanceof EntityLivingBase) {
    			if(b.getEntityData().getInteger("the_world")>0) {
    				tp=(EntityLivingBase) b;
    			   }
    			}
    		}
    	{if(target instanceof EntityArrow) {
    		if(target.getEntityData().getBoolean("time_stop")) {
    		if(tp!=null) {
    			(( EntityArrow)target).motionX=0;
    			(( EntityArrow)target).motionY=0;
    			(( EntityArrow)target).motionZ=0;
    			(( EntityArrow)target).setNoGravity(true);
    			}
    		else {
    			target.getEntityData().setBoolean("time_stop",false);
    				Double v_x=target.getEntityData().getDouble("v_x");
    				Double v_y=target.getEntityData().getDouble("v_y");
    				Double v_z=target.getEntityData().getDouble("v_z");
    				(( EntityArrow)target).setNoGravity(false);
    				(( EntityArrow)target).motionX=v_x;
        			(( EntityArrow)target).motionY=v_y;
        			(( EntityArrow)target).motionZ=v_z;
        			}
    			}
    			
    		  }
    		if(target instanceof EntityItem) {
    			if(NBTHelper.getEntityInteger(target,"time_stop")>0) {
    	    		
    	    		Double v_x=target.getEntityData().getDouble("v_x");
    	    		Double v_y=target.getEntityData().getDouble("v_y");
    	    		Double v_z=target.getEntityData().getDouble("v_z");
    			while(target.ticksExisted%2==0) {
    				target.getEntityData().setInteger("time_stop",NBTHelper.getEntityInteger(target,"time_stop")-2);
    				(( EntityItem)target).setPosition(v_x,v_y,v_z);
        			}
        			if(NBTHelper.getEntityInteger(target,"time_stop")<=2) {
    				((EntityItem)target).setNoGravity(false);
        		  }
        		}
    		  }
    		}
    		

     }
     */
//  @SubscribeEvent
//   public static void onTheWorldPlayer(AttackEntityEvent evt)
//     {
//	  EntityLivingBase p=evt.getEntityPlayer();
//	  if(NBTHelper.getEntityInteger(p,"huajiage.time_stop")>0) 
//	  {
//		  evt.setCanceled(true);
//	  }
//		 
//     }
  @SubscribeEvent
     public static void onTheWorld(LivingUpdateEvent evt)
     {
	  EntityLivingBase eater =evt.getEntityLiving(); 
    	if(eater.getEntityData().getInteger("huajiage.the_world")>0) {
    		int t=eater.getEntityData().getInteger("huajiage.the_world");
    		eater.getEntityData().setInteger("huajiage.the_world", t-1);
    		List<Entity> arrows=eater.getEntityWorld().getEntitiesWithinAABB(Entity.class,eater.getEntityBoundingBox().grow(100,100,100));
    		if(arrows!=null) {
    			for(Entity i:arrows) {
    				
    				if(i instanceof IProjectile) {
    					if(i.getEntityData().getInteger("huajiage.time_stop")==0) {
    					i.getEntityData().setDouble("huajiage.v_x", i.motionX);
    					i.getEntityData().setDouble("huajiage.v_y", i.motionY);
    					i.getEntityData().setDouble("huajiage.v_z", i.motionZ);
    					i.getEntityData().setInteger("huajiage.time_stop",t);
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
    					if(i.getEntityData().getInteger("huajiage.time_stop")==0) {
    					i.getEntityData().setDouble("huajiage.v_x", i.motionX);
    					i.getEntityData().setDouble("huajiage.v_y", i.motionY);
    					i.getEntityData().setDouble("huajiage.v_z", i.motionZ);
    					i.getEntityData().setInteger("huajiage.time_stop",t);
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
    					if(i.getEntityData().getInteger("huajiage.time_stop")==0) {
    						Vec3d v=((EntityItem)i).getPositionVector();
    						i.getEntityData().setDouble("huajiage.v_x", i.motionX);
        					i.getEntityData().setDouble("huajiage.v_y", i.motionY);
        					i.getEntityData().setDouble("huajiage.v_z", i.motionZ);
    						i.getEntityData().setInteger("huajiage.time_stop",t);
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
    					if(i!=eater&&i.getEntityData().getInteger("huajiage.time_stop")==0) {
    					i.getEntityData().setInteger("huajiage.time_stop", t);
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
