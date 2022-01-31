package com.lh_lshen.mcbbs.huajiage.damage_source;

import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class DamageStandHit extends EntityDamageSource {

	public DamageStandHit(Entity source) {
		super(HuajiConstant.DamageSource.STAND_PUNCH_DAMAGE,source);
		setDamageBypassesArmor();
	}

}
