package com.lh_lshen.mcbbs.huajiage.damage_source;

import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.world.storage.loot.functions.SetAttributes;

public class DamageHopeHit extends EntityDamageSource {

	public DamageHopeHit(Entity source) {
		super(HuajiConstant.DamageSource.HOPE_HIT, source);
		setDamageIsAbsolute();

	}

}
