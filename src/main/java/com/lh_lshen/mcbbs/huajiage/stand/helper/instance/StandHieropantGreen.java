package com.lh_lshen.mcbbs.huajiage.stand.helper.instance;

import java.util.List;
import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.entity.EntityEmeraldBullet;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.network.StandNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandSkillType;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDoTimeStopServer;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessagePerfromSkill;
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
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;

public class StandHieropantGreen implements IStandPower {

	@Override
	public void doStandPower(EntityLivingBase user) {
		EnumStandtype type = StandUtil.getType(user);
		int stage = StandUtil.getStandStage(user);
		if(type == null) {
			return;
		}
		if(user.ticksExisted%5==0) {
		Vec3d look = user.getLookVec();
		Vec3d shoot_point = MotionHelper.getPostionRelative2D(user, -0.55f, -0.6f);
		EntityEmeraldBullet bullet = new EntityEmeraldBullet(user.world, user);
		bullet.posX=user.posX+shoot_point.x;
		bullet.posY=user.posY+2.2f;
		bullet.posZ=user.posZ+shoot_point.z;
		bullet.setRotation(MathHelper.wrapDegrees(-user.rotationYaw));
		bullet.setPitch(user.rotationPitch);
		bullet.setLife(10*20);
		bullet.motionX=look.x*1.5;
		bullet.motionY=look.y*1.5;
		bullet.motionZ=look.z*1.5;
//		List<Entity> entityCllection = user.getEntityWorld().getEntitiesWithinAABB(Entity.class, user.getEntityBoundingBox().grow(stage>0?type.getDistance()*2:type.getDistance()));
//		if(entityCllection.size()>0) {
//		for(Entity i:entityCllection) {
//				Vec3d back = MotionHelper.getVectorEntity(user, i);
//				boolean flag_player = false;
//				boolean flag_degree = 
//						MotionHelper.getDegreeXZ(user.getLookVec(),MotionHelper.getVectorEntity(user, i))>10&&
//						MotionHelper.getDegreeXY(user.getLookVec(),MotionHelper.getVectorEntity(user, i))>10&&
//						MotionHelper.getDegreeZY(user.getLookVec(),MotionHelper.getVectorEntity(user, i))>10;
//				if(flag_degree) {
//					continue;
//				}else {
//					bullet.motionX=back.x*1.5;
//					bullet.motionY=back.y*1.5;
//					bullet.motionZ=back.z*1.5;
//				}
//			}
//		}
		user.getEntityWorld().spawnEntity(bullet);
				
				
		}
		
	}

	@Override
	public void doStandCapability(EntityLivingBase user ,boolean flag) {
		MessagePerfromSkill msg = new MessagePerfromSkill(EnumStandtype.THE_WORLD.getCost(),0,120,HuajiConstant.Tags.THE_WORLD_TIME,EnumStandSkillType.TIME_STOP);
		StandNetWorkHandler.sendToServer(msg);
		if(flag){
			StandNetWorkHandler.sendToServer(new MessageDoTimeStopServer(true));
				if(user instanceof EntityPlayer) {
					user.sendMessage(new TextComponentTranslation("message.huajiage.the_world"));
				}
			}
	}

}
