package com.lh_lshen.mcbbs.huajiage.stand.helper.instance;

import java.util.List;
import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.api.IStandPower;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.network.StandNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.helper.skill.TimeStopHelper;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDoStandCapabilityServer;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDoStandPowerClient;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessagePerfromSkill;
import com.lh_lshen.mcbbs.huajiage.util.MotionHelper;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;

public class StandTheWorld extends StandBase {
	
	 public StandTheWorld() {
	 	}
	 
	 public StandTheWorld(String name, float speed, float damage, int duration, float distance, int cost,
				String texPath, String localName) {
			super(name, speed, damage, duration, distance, cost, texPath, localName);
	 	}

	@Override
	public void doStandPower(EntityLivingBase user) {
		StandBase type = StandUtil.getType(user);
		int stage = StandUtil.getStandStage(user);
		if(type == null) {
			return;
		}
		List<Entity> entityCllection = user.getEntityWorld().getEntitiesWithinAABB(Entity.class, user.getEntityBoundingBox().grow(stage>0?type.getDistance()+1f:type.getDistance()));
		if(entityCllection.size()<=0) {
			return;
		}
		
		for(Entity i:entityCllection) {

				Vec3d back = MotionHelper.getVectorEntityEye(user, i);
				boolean flag_player = false;
				boolean flag_degree = MotionHelper.getDegreeXZ(user.getLookVec(),MotionHelper.getVectorEntityEye(user, i))>(type.getName().equals(StandLoader.STAR_PLATINUM.getName())?120:90);
				
				if(flag_degree) {
					continue;
				}
				
				if(user instanceof EntityPlayer) {
					flag_player=true;
				}
				
				if(i instanceof EntityDragon) {
					EntityDragon dragon =(EntityDragon)i;
					if(user instanceof EntityPlayer) {
						dragon.attackEntityFromPart(dragon.dragonPartBody, DamageSource.causePlayerDamage((EntityPlayer) user),stage>0?type.getDamage()*3f:type.getDamage()*2f);
					}else {
						dragon.attackEntityFromPart(dragon.dragonPartBody, DamageSource.ANVIL, type.getDamage()*type.getSpeed());  
					}
				}
				
				  if(i instanceof EntityLivingBase) {
					  
					  EntityLivingBase target=(EntityLivingBase)i;
					  
					  
					  if(target!=user) {
						  float random = new Random().nextFloat()*100;
						  if(random<20&&target.hurtTime <= 0&&stage>0) {
							  HuajiSoundPlayer.playToNearbyClient(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.25f);
							  HuajiSoundPlayer.playToNearbyClient(target, SoundLoader.DIO_HIT, 0.75f);
								  if(NBTHelper.getEntityInteger(target, HuajiConstant.Tags.DIO_HIT)<120) 
								  {
								  NBTHelper.setEntityInteger(target, HuajiConstant.Tags.DIO_HIT, 120);
								  }

						  }
						  
						  	if(NBTHelper.getEntityInteger(target, HuajiConstant.Tags.TIME_STOP)>0&&NBTHelper.getEntityInteger(target, HuajiConstant.Tags.DIO_HIT)<60) {
								  NBTHelper.setEntityInteger(target, HuajiConstant.Tags.DIO_HIT, 60);
							  }else {
								  float health = target.getHealth();
								  if(flag_player) {
									  EntityPlayer player =(EntityPlayer) user;
									  target.attackEntityFrom(DamageSource.causePlayerDamage(player), type.getDamage());
							  		}else {
							  		  target.attackEntityFrom(DamageSource.ANVIL, type.getDamage());
							  		}
							  }
						  	
							  
						  if(user.ticksExisted%2==0) {
						  user.world.playEvent(2001, target.getPosition().add(0, target.getPositionEyes(target.ticksExisted).y-target.getPosition().getY(), 0), Blocks.OBSIDIAN.getStateId(Blocks.OBSIDIAN.getStateFromMeta(0)));
						  }
						  
						  target.motionX=back.x;
						  target.motionY=back.y;
						  target.motionZ=back.z;
										  }
					  
			  	}else if(i instanceof EntityItem || i instanceof EntityXPOrb ){
			  		
			  		continue;
			  		
			  	}else if(MotionHelper.getAABBSize(i.getEntityBoundingBox())>2){

			  		user.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,50,5));
			  		continue;
			  	}else {
					  	  i.motionX=(type.getDamage()/10)*back.x;
						  i.motionY=(type.getDamage()/10)*back.y;
						  i.motionZ=(type.getDamage()/10)*back.z;
			  	}	
			}
		
	}

	@Override
	public void doStandCapability(EntityLivingBase user) {
		TimeStopHelper.setEntityTimeStopRange(user,120);
		TimeStopHelper.setTimeStop(user, HuajiConstant.Tags.THE_WORLD_TIME);
		TimeStopHelper.extraEffects(user, 9);
		if(user instanceof EntityPlayer) {
		ServerUtil.sendPacketToNearbyPlayersStand(user, new MessageDoStandPowerClient((EntityPlayer) user,StandLoader.THE_WORLD.getName()));
		}
	}
	
	@Override
	public void doStandCapabilityClient(WorldClient world, EntityLivingBase user) {
		TimeStopHelper.doTimeStopClient(world, user.getPositionVector(), StandLoader.THE_WORLD);
	}


}
