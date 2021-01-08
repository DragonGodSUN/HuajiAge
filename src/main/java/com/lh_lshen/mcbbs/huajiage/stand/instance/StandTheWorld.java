package com.lh_lshen.mcbbs.huajiage.stand.instance;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityExposedData;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.helper.TimeStopHelper;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDoStandPowerClient;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandRes;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandResLoader;
import com.lh_lshen.mcbbs.huajiage.stand.states.default_set.StateTheWorldDefault;
import com.lh_lshen.mcbbs.huajiage.stand.states.idle.StateTheWorldIdle;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class StandTheWorld extends StandBase {

	public StandTheWorld() {
		super();
	}
	public StandTheWorld(String name ,float speed ,float damage ,int duration ,float distance ,int cost,int charge,
			String texPath,String localName, boolean displayHand) {
			super(name, speed, damage, duration, distance, cost, charge, texPath, localName, displayHand);
			initState(new StateTheWorldDefault(name,CapabilityExposedData.States.DEFAULT.getName(),isHandDisplay(),true));
			addState(CapabilityExposedData.States.IDLE.getName(),new StateTheWorldIdle(name,CapabilityExposedData.States.IDLE.getName(),true,false));
//			addState(CapabilityExposedData.States.PROTECT.getName(),new StateTheWorldIdle(name,CapabilityExposedData.States.IDLE.getName(),true));
	}
	@Override
	public StandRes getBindingRes() {
		return StandResLoader.THE_WORLD_RES;
	}

	@Override
	public void doStandPower(EntityLivingBase user) {
		super.doStandPower(user);
	}

	@Override
	public void doStandCapability(EntityLivingBase user) {
		TimeStopHelper.setTimeStop(user, HuajiConstant.Tags.THE_WORLD_TIME+20);
		TimeStopHelper.setEntityTimeStopRange(user,120);
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
