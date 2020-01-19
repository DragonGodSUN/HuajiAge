package com.lh_lshen.mcbbs.huajiage.init;

import com.lh_lshen.mcbbs.huajiage.entity.EntitySecondFoil;
import com.lh_lshen.mcbbs.huajiage.init.events.EventKeyInput;
import com.lh_lshen.mcbbs.huajiage.init.events.EventOrga;
import com.lh_lshen.mcbbs.huajiage.init.events.EventRequiem;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.EventBus;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventLoader {
	 public static final EventBus EVENT_BUS = new EventBus();
	
	  public EventLoader()
	    {
	        MinecraftForge.EVENT_BUS.register(this);
	        MinecraftForge.EVENT_BUS.register(EventKeyInput.class);
	        MinecraftForge.EVENT_BUS.register(EventRequiem.class);
	        MinecraftForge.EVENT_BUS.register(EventOrga.class);
//	        MinecraftForge.EVENT_BUS.register(EventTimeStop.class);
//	        MinecraftForge.EVENT_BUS.register(EventStand.class);
//	        MinecraftForge.EVENT_BUS.register(EventStandOverlatRender.class);
//	        MinecraftForge.EVENT_BUS.register(EventPlayerCapability.class);
	        
//	        MinecraftForge.EVENT_BUS.register(Event.class);
//	        MinecraftForge.EVENT_BUS.register(EventPlayerCharge.class);
	        EventLoader.EVENT_BUS.register(this);
	    }

   
	  @Cancelable
	    public static class SecondHitEvent extends net.minecraftforge.event.entity.item.ItemEvent
	    {
      public SecondHitEvent(EntityItem item)
	        {
	            super(item);

	        }
	    }
	  @SubscribeEvent
	    public void SecondHit(SecondHitEvent event)
	    {
	        if (!event.getEntityItem().world.isRemote)
	        { 
	        	int j;
	        	int i=event.getEntityItem().getItem().getMaxDamage()-(event.getEntityItem().getItem().getItemDamage())+2;
	        	for(j=0;j<i;j++) {
	        		World world=event.getEntityItem().world;
	            BlockPos pos = event.getEntityItem().getPosition();
	        	EntityPlayer player=event.getEntityItem().getEntityWorld().getPlayerEntityByName(NBTHelper.getTagCompoundSafe(event.getEntityItem().getItem()).getString("owner"));
	        	EntityLivingBase target=(EntityLivingBase)world.findNearestEntityWithinAABB(EntityLivingBase.class,event.getEntityItem().getEntityBoundingBox().grow(100),event.getEntityItem());
	        	 if(player!=null){
	            Entity s = new  EntitySecondFoil(world,player,target,player.getHorizontalFacing().getAxis()); 
	            s.setPosition(pos.getX(), pos.getY(), pos.getZ());
	            world.spawnEntity(s);}
	            }
	        	
	            event.getEntityItem().setDead();
	        	
	        }
	    }
	 
	@SubscribeEvent
	     public void onHuajiPotion(LivingUpdateEvent event)
	     {
		  EntityLivingBase entity = event.getEntityLiving();
		  PotionEffect effect = entity.getActivePotionEffect(PotionLoader.potionHuajiProtection); 
	            
          if (effect != null ){
	             { EntityLiving c=(EntityLiving) event.getEntityLiving();
	                    event.getEntityLiving().getEntityWorld().spawnParticle(EnumParticleTypes.CLOUD,c.posX, c.posY,c.posZ, 0.5, 0.5, 0.5, 1);
	                
	             	}
	             
          		}
          
	     	}

	}
