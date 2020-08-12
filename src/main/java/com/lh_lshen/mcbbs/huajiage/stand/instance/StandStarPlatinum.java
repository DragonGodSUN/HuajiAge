package com.lh_lshen.mcbbs.huajiage.stand.instance;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityExposedData;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.helper.TimeStopHelper;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDoStandPowerClient;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandRes;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandResLoader;
import com.lh_lshen.mcbbs.huajiage.stand.states.default_set.StateStarPlatinumDefault;
import com.lh_lshen.mcbbs.huajiage.stand.states.idle.StateStarPlatinumIdle;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class StandStarPlatinum extends StandBase {

	public StandStarPlatinum() {
		super();
	}
	public StandStarPlatinum(String name ,float speed ,float damage ,int duration ,float distance ,int cost,int charge,
			String texPath,String localName, boolean displayHand) {
			super(name, speed, damage, duration, distance, cost, charge, texPath, localName, displayHand);
			initState(new StateStarPlatinumDefault(name,CapabilityExposedData.States.DEFAULT.getName(),isHandDisplay(),true));
			addState(CapabilityExposedData.States.IDLE.getName(),new StateStarPlatinumIdle(name,CapabilityExposedData.States.IDLE.getName(),true,false));
//			addState(CapabilityExposedData.States.PROTECT.getName());
	}
	@Override
	public StandRes getBindingRes() {
		return StandResLoader.STAR_PLATINUM_RES;
	}

	@Override
	public void doStandPower(EntityLivingBase user) {
		super.doStandPower(user);
	}

	@Override
	public void doStandCapability(EntityLivingBase user) {
		TimeStopHelper.setTimeStop(user, 5*20);
		TimeStopHelper.setEntityTimeStopRange(user,120);
		TimeStopHelper.extraEffects(user, 5);
		if(user instanceof EntityPlayer) {
		ServerUtil.sendPacketToNearbyPlayersStand(user, new MessageDoStandPowerClient((EntityPlayer) user,StandLoader.STAR_PLATINUM.getName()));
		}
	}

	@Override
	public void doStandCapabilityClient(WorldClient world, EntityLivingBase user) {
		TimeStopHelper.doTimeStopClient(world, user.getPositionVector(), StandLoader.STAR_PLATINUM);
	}


}
