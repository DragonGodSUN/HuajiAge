package com.lh_lshen.mcbbs.huajiage.damage_source;

import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class DamageRequiem extends EntityDamageSource{

	public DamageRequiem(Entity source) {
		super(HuajiConstant.DamageSource.REQUIEM_DAMAGE, source);
		setDamageIsAbsolute();
	}

}
