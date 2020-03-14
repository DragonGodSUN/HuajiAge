package com.lh_lshen.mcbbs.huajiage.DamageSource;

import net.minecraft.entity.Entity;
import net.minecraft.util.EntityDamageSourceIndirect;

public class DamageEmeraldSplash extends EntityDamageSourceIndirect {

	public DamageEmeraldSplash(Entity source, Entity indirectEntityIn) {
		super("explosion", source, indirectEntityIn);
		setExplosion();
		setProjectile();
		setIsThornsDamage();
	}

}
