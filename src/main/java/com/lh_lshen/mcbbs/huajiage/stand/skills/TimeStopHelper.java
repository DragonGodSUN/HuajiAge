package com.lh_lshen.mcbbs.huajiage.stand.skills;

import java.util.List;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.Mod;

public class TimeStopHelper {

	public static List<Entity> getTagetsInRange(Entity entity ,double distance) {
		List<Entity> targets=entity.getEntityWorld().getEntitiesWithinAABB(Entity.class,entity.getEntityBoundingBox().grow(distance));
		return targets;
	}
	
	public static void setTimeStop(Entity entity ,int ticks) {
		NBTHelper.setEntityInteger(entity, HuajiConstant.THE_WORLD, ticks);
	}
	
	public static void setEntityTimeStopRange(Entity entity ,double distance) {
		NBTHelper.setEntityDouble(entity, HuajiConstant.TIME_STOP_RANGE, distance);
	}
	
	public static void doTimeStopServer(EntityLivingBase entity,int time) {
		StandHandler standHandler = entity.getCapability(CapabilityStandHandler.STAND_TYPE, null);
		EnumStandtype stand =EnumStandtype.getType(standHandler.getStand());
			if(null ==stand) {
				return;
			}
        	double rand=Math.random()*100;
        	 if(!stand.equals(EnumStandtype.STAR_PLATINUM)) {
        		 entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,time*20,0));
        		 entity.addPotionEffect(new PotionEffect(MobEffects.STRENGTH,time*20,4));
        		 entity.addPotionEffect(new PotionEffect(MobEffects.SPEED,time*20,6));
        		 entity.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,time*20,4));
        		 entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,time*20,2));
        		 entity.heal(5f);
        		 if(rand<30d&&entity instanceof EntityPlayer) {
        			 ((EntityPlayer)entity).inventory.addItemStackToInventory(new ItemStack(ItemLoader.roadRoller));
        		 }
            }else {
            	 entity.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION,time*20,0));
        		 entity.addPotionEffect(new PotionEffect(MobEffects.STRENGTH,time*20,1));
        		 entity.addPotionEffect(new PotionEffect(MobEffects.SPEED,time*20,2));
        		 entity.addPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST,time*20,1));
            }
		}
	
	public static void doTimeStopClient(EntityLivingBase entity,EnumStandtype stand) {
//		StandHandler standHandler = entity.getCapability(CapabilityStandHandler.STAND_TYPE, null);
//		EnumStandtype stand =EnumStandtype.getType(standHandler.getStand());
		double rand=Math.random()*100;
        if(!stand.getName().equals(EnumStandtype.STAR_PLATINUM.getName())) {
	        if(rand<25) { 	
	        	entity.playSound(SoundLoader.THE_WORLD, 5f,1f);
	        }else if(rand<50){
	        	entity.playSound(SoundLoader.THE_WORLD_1, 5f,1f);
	        }else if(rand<75){
	        	entity.playSound(SoundLoader.THE_WORLD_2, 5f,1f);
	        }else {
	        	entity.playSound(SoundLoader.THE_WORLD_3, 5f,1f);
	        }
        }else {
        	entity.playSound(SoundLoader.STAR_PLATINUM_THE_WORLD_1, 5f,1f);
        }
	}
	
}
