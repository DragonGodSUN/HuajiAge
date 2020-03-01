package com.lh_lshen.mcbbs.huajiage.stand.helper.instance;

import java.util.List;
import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.network.StandNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDoTimeStopServer;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessagePerfromSkill;
import com.lh_lshen.mcbbs.huajiage.stand.skill.StandSkillType;
import com.lh_lshen.mcbbs.huajiage.util.MotionHelper;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

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

public class StandStarPlatinum implements IStandPower {

	@Override
	public void doStandPower(EntityLivingBase user) {
		EnumStandtype type = StandUtil.getType(user);
		if(type == null) {
			return;
		}
		List<Entity> entityCllection = user.getEntityWorld().getEntitiesWithinAABB(Entity.class, user.getEntityBoundingBox().grow(type.getDistance()));
		if(entityCllection.size()<=0) {
			return;
		}
		int stage = StandUtil.getStandStage(user);
		for(Entity i:entityCllection) {

				Vec3d back = MotionHelper.getVectorEntityEye(user, i);
				boolean flag_player = false;
				boolean flag_degree = MotionHelper.getDegreeXZ(user.getLookVec(),MotionHelper.getVectorEntityEye(user, i))>(type.getName().equals(EnumStandtype.STAR_PLATINUM.getName())?120:90);
				
				if(flag_degree) {
					continue;
				}
				
				if(user instanceof EntityPlayer) {
					flag_player=true;
				}
				
				
				  if(i instanceof EntityLivingBase) {
					  EntityLivingBase target=(EntityLivingBase)i;
					  
					  if(target instanceof EntityDragon) {
						  EntityDragon dragon =(EntityDragon)target;
						  if(user instanceof EntityPlayer) {
						  dragon.attackEntityFromPart(dragon.dragonPartBody, DamageSource.causePlayerDamage((EntityPlayer) user), type.getDamage()*type.getSpeed());
						  }else {
						  dragon.attackEntityFromPart(dragon.dragonPartBody, DamageSource.ANVIL, type.getDamage()*type.getSpeed());  
						  }
					  }
					  
					  if(target!=user) {
						  float random = new Random().nextFloat()*100;
						  if(random<20&&target.hurtTime <= 0&&stage>0) {
							  HuajiSoundPlayer.playToNearbyClient(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.25f);
							  HuajiSoundPlayer.playToNearbyClient(target, SoundLoader.STAND_STAR_PLATINUM_5, 0.3f);
							  target.attackEntityFrom(flag_player? DamageSource.causePlayerDamage((EntityPlayer) user):DamageSource.ANVIL,
									 type.getDamage()*10);
						  }
						  
						  	if(NBTHelper.getEntityInteger(target, HuajiConstant.TIME_STOP)>0&&NBTHelper.getEntityInteger(target, HuajiConstant.DIO_HIT)<60) {
								  NBTHelper.setEntityInteger(target, HuajiConstant.DIO_HIT, 60);
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
						  user.world.playEvent(2001, target.getPosition().add(0, target.getPositionEyes(target.ticksExisted).y-target.getPosition().getY(), 0), Blocks.OBSIDIAN.getStateId(Blocks.OBSIDIAN.getDefaultState()));
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
	public void doStandCapability(EntityLivingBase user ,boolean flag) {
		MessagePerfromSkill msg = new MessagePerfromSkill(EnumStandtype.STAR_PLATINUM.getCost(),0,50,5*20,StandSkillType.TIME_STOP);
		StandNetWorkHandler.sendToServer(msg);
		if(flag){
			StandNetWorkHandler.sendToServer(new MessageDoTimeStopServer(false));
				if(user instanceof EntityPlayer) {
					user.sendMessage(new TextComponentTranslation("message.huajiage.the_world_star"));
				}
			}
	}

}
