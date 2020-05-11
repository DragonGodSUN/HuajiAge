package com.lh_lshen.mcbbs.huajiage.damage_source;

import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class DamageFivePower extends EntityDamageSource{

	public DamageFivePower(Entity source) {
		super(HuajiConstant.DamageSource.VOID_BREAK, source);
		setDamageIsAbsolute();
		setDamageAllowedInCreativeMode();
	}

}
