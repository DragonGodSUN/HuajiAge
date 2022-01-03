package com.lh_lshen.mcbbs.huajiage.damage_source;

import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import net.minecraft.util.DamageSource;

public class DamageDiscDeprive extends DamageSource {

	public DamageDiscDeprive() {
		super(HuajiConstant.DamageSource.DISC_DEPRIVE);
		setDamageIsAbsolute();
	}

}
