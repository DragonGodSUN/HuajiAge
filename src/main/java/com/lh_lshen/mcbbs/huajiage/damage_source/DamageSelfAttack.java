package com.lh_lshen.mcbbs.huajiage.damage_source;

import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import net.minecraft.util.DamageSource;

public class DamageSelfAttack extends DamageSource {

	public DamageSelfAttack() {
		super(HuajiConstant.DamageSource.SELF_ATTACK);
		setDamageAllowedInCreativeMode();
	}

}
