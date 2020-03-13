package com.lh_lshen.mcbbs.huajiage.stand.helper.instance;

import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLivingBase;

public interface IStandPower {
	
	void doStandPower(EntityLivingBase user);
	
	void doStandCapability(EntityLivingBase user);
	
	void doStandCapabilityClient(WorldClient world ,EntityLivingBase user);
}
