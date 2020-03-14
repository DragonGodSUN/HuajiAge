package com.lh_lshen.mcbbs.huajiage.stand.helper.instance;

import java.util.List;
import java.util.Random;

import com.jcraft.jorbis.Block;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.entity.EntityEmeraldBullet;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundStand;
import com.lh_lshen.mcbbs.huajiage.network.StandNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandSkillType;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDoStandCapabilityServer;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDoStandPowerClient;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessagePerfromSkill;
import com.lh_lshen.mcbbs.huajiage.util.MotionHelper;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import net.minecraft.block.state.IBlockState;
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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

public class StandHieropantGreen implements IStandPower {

	@Override
	public void doStandPower(EntityLivingBase user) {
		EnumStandtype type = StandUtil.getType(user);
		int stage = StandUtil.getStandStage(user);
		if(type == null) {
			return;
		}
		if(!user.world.isRemote) {
			if(stage>0&&user.ticksExisted%5==0||user.ticksExisted%8==0) {
			Vec3d look = user.getLookVec();
			Vec3d shoot_point = MotionHelper.getPostionRelative2D(user, -0.55f, -0.6f);
			EntityEmeraldBullet bullet = new EntityEmeraldBullet(user.world, user);
			bullet.setPosition(user.posX+shoot_point.x, user.posY+2.2f, user.posZ+shoot_point.z);
//			bullet.posX=user.posX+shoot_point.x;
//			bullet.posY=user.posY+2.2f;
//			bullet.posZ=user.posZ+shoot_point.z;
			bullet.setRotation(user.rotationYaw);
			bullet.setPitch(user.rotationPitch);
			float r =(float) Math.random()*360;
			bullet.setRotationRandom(r);
			bullet.setLife(10*20);
			bullet.setDamage(stage>0?EnumStandtype.HIEROPHANT_GREEN.getDamage()+2:EnumStandtype.HIEROPHANT_GREEN.getDamage());
			bullet.shoot(user, user.rotationPitch, user.rotationYaw, 0, 1.5f, 0.2f);
			user.getEntityWorld().spawnEntity(bullet);
			}
		}
		
	}

	@Override
	public void doStandCapability(EntityLivingBase user) {
		Vec3d look = user.getLookVec();
		Vec3d dist = new Vec3d(user.posX+5*look.x, user.posY+5*look.y, user.posZ+5*look.z);
		List<EntityLivingBase> entityCllection = user.getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, user.getEntityBoundingBox().grow(EnumStandtype.HIEROPHANT_GREEN.getDistance()));
		if(entityCllection.size()>0) {
		for(EntityLivingBase i:entityCllection) {
			if(i!=user) {
				Vec3d vec = MotionHelper.getVectorEntityEye(user, i);
				float dgree1 = (float) MotionHelper.getDegreeXZ(look, vec);
				float dgree2 = (float) MotionHelper.getDegreeXY(look, vec);
				float dgree3 = (float) MotionHelper.getDegreeXZ(look, vec);
				if(dgree1<15&&dgree2<15&&dgree3<15) {
					dist = i.getPositionVector();
					doEmeraldSlash(dist, user);
					break;
					}
				}
			}
		}
		if(dist!=null){
			doEmeraldSlash(dist, user);
		}
		
		if(user instanceof EntityPlayer) {
			ServerUtil.sendPacketToNearbyPlayersStand(user, new MessageDoStandPowerClient((EntityPlayer) user,EnumStandtype.HIEROPHANT_GREEN.getName()));
			}
	}

	@Override
	public void doStandCapabilityClient(WorldClient world, EntityLivingBase user) {

			world.playSound(user.getPositionVector().x,user.getPositionVector().y,user.getPositionVector().z, SoundLoader.STAND_HIEROPHANT_GREEN_EMERALD_SPLASH, SoundCategory.PLAYERS, 5f, 1f,true);
	
	}

	private void doEmeraldSlash(Vec3d dist , EntityLivingBase user) {
		if(dist!=null) {
		for(int i = 0;i<100;i++) {
			float rx = MathHelper.nextFloat(new Random(), -20, 20);
			float ry = MathHelper.nextFloat(new Random(), 0, 20);
			float rz = MathHelper.nextFloat(new Random(), -20, 20);
			Vec3d pos = new Vec3d(user.posX+rx, user.posY+ry, user.posZ+rz);
			Vec3d v = MotionHelper.getVector(pos, dist);
//			if(!isBlocked(user.world, pos)) {
			
				EntityEmeraldBullet bullet = new EntityEmeraldBullet(user.world, user);
				bullet.setPosition(pos.x, pos.y, pos.z);
				NBTHelper.setEntityFloat(bullet, "huajiage.motion.x", (float) v.x);
				NBTHelper.setEntityFloat(bullet, "huajiage.motion.y", (float) v.y);
				NBTHelper.setEntityFloat(bullet, "huajiage.motion.z", (float) v.z);
				bullet.setSplashHuge(true);
				bullet.setLife(360f);
				bullet.setStayTime(50);
				bullet.rotationYaw = 360*rx/20;
				bullet.rotationPitch = 360*ry/20;
				bullet.setRotation(bullet.rotationYaw);
				bullet.setPitch(bullet.rotationPitch);
				bullet.setDamage(5f);
				user.world.spawnEntity(bullet);
//			}
			}
		}
	}
}
