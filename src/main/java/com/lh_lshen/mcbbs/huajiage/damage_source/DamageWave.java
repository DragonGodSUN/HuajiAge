package com.lh_lshen.mcbbs.huajiage.damage_source;

import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class DamageWave extends EntityDamageSource{

	public DamageWave(Entity source) {
		super(HuajiConstant.DamageSource.WAVE_HIT, source);
		setDamageBypassesArmor();
		setIsThornsDamage();
	}

}
