package com.lh_lshen.mcbbs.huajiage.stand.helper.instance;

import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;

import net.minecraft.entity.EntityLivingBase;

public interface IStandPower {
	
	void doStandPower(EntityLivingBase user);
	
	void doStandCapability(EntityLivingBase user ,boolean flag);
	
}
