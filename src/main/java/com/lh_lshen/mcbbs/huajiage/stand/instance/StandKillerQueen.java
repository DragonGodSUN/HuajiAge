package com.lh_lshen.mcbbs.huajiage.stand.instance;

import com.lh_lshen.mcbbs.huajiage.capability.*;
import com.lh_lshen.mcbbs.huajiage.entity.EntitySheerHeartAttack;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.helper.StandPowerHelper;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDoStandPowerClient;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandRes;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandResLoader;
import com.lh_lshen.mcbbs.huajiage.stand.states.default_set.StateKillerQueenDefault;
import com.lh_lshen.mcbbs.huajiage.stand.states.various.StateKillerQueenPunch;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;

public class StandKillerQueen extends StandBase {
	
	public StandKillerQueen() {
		super();
	}
	public StandKillerQueen(String name ,float speed ,float damage ,int duration ,float distance ,int cost,int charge,
			String texPath,String localName, boolean displayHand) {
			super(name, speed, damage, duration, distance, cost, charge, texPath, localName, displayHand);
			initState(new StateKillerQueenDefault(name,CapabilityExposedData.States.DEFAULT.getName(),isHandDisplay(),false));
			addState(CapabilityExposedData.States.PUNCH.getName(),new StateKillerQueenPunch(name,CapabilityExposedData.States.PUNCH.getName(),false,true));
	}
	@Override
	public StandRes getBindingRes() {
		return StandResLoader.KILLER_QUEEN_RES;
	}
	@Override
	public void doStandPower(EntityLivingBase user) {
		StandBase type = StandUtil.getType(user);
		int stage = StandUtil.getStandStage(user);
		boolean isPunch = CapabilityExposedData.States.PUNCH.getName().equals(StandUtil.getStandState(user));
		if(type == null) {
			return;
		}else if(isPunch){
			StandPowerHelper.rangePunchAttack(user,45,getDamage()*(1+stage/2),2);
		}else{
			StandPowerHelper.MPCharge(user,(int)(getCharge()/3));
		}
		
	}

	@Override
	public void doStandCapability(EntityLivingBase user) {
		if(user instanceof EntityPlayer) {
		EntitySheerHeartAttack attack = new EntitySheerHeartAttack(user.world);
		Vec3d vec = user.getLookVec();
//		int number = user.world.getEntitiesWithinAABB(EntitySheerHeartAttack.class, user.)
		attack.setTamedBy((EntityPlayer) user);
		attack.setDamage(15f);
		attack.motionX=vec.x*0.5;
		attack.motionY=vec.y*0.5;
		attack.motionZ=vec.z*0.5;
		attack.setPosition(user.posX, user.posY+0.5f, user.posZ);
		user.world.spawnEntity(attack);
		ServerUtil.sendPacketToNearbyPlayersStand(user, new MessageDoStandPowerClient((EntityPlayer) user,StandLoader.KILLER_QUEEN.getName()));
		}
	}

	@Override
	public void doStandCapabilityClient(WorldClient world, EntityLivingBase user) {
			world.playSound(user.getPositionVector().x,user.getPositionVector().y,user.getPositionVector().z, SoundLoader.STAND_KILLER_QUEEN_TRIGGER, SoundCategory.PLAYERS, 2f, 1f,true);
	}

}
