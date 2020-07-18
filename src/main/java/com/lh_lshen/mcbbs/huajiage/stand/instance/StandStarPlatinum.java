package com.lh_lshen.mcbbs.huajiage.stand.instance;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.helper.StandPowerHelper;
import com.lh_lshen.mcbbs.huajiage.stand.helper.TimeStopHelper;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDoStandPowerClient;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandRes;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandResLoader;
import com.lh_lshen.mcbbs.huajiage.stand.states.default_set.StateStarPlatinumDefault;
import com.lh_lshen.mcbbs.huajiage.stand.states.default_set.StateTheWorldDefault;
import com.lh_lshen.mcbbs.huajiage.stand.states.idle.StateStarPlatinumIdle;
import com.lh_lshen.mcbbs.huajiage.util.HAMathHelper;
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
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.Vec3d;

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
		TimeStopHelper.setEntityTimeStopRange(user,120);
		TimeStopHelper.setTimeStop(user, 5*20);
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
