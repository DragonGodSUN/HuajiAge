package com.lh_lshen.mcbbs.huajiage.damage_source;

import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSource;

public class DamageOverdrive extends EntityDamageSource{

	public DamageOverdrive(Entity source) {
		super(HuajiConstant.DamageSource.OVERDRIVE_HIT, source);
		setDamageBypassesArmor();
	}

}
