package com.lh_lshen.mcbbs.huajiage.explosion;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;

public abstract class HuajiExplosionCreator implements IBlockAccess, net.minecraftforge.common.capabilities.ICapabilityProvider {
	 public Explosion createExplosion(@Nullable Entity entityIn, double x, double y, double z, float strength,boolean flaming, boolean isSmoking)
	    {
	        return this.newExplosion(entityIn, x, y, z, strength, flaming, isSmoking);
	    }

	    /**
	     * returns a new explosion. Does initiation (at time of writing Explosion is not finished)
	     */
	    public Explosion newExplosion(@Nullable Entity entityIn, double x, double y, double z, float strength, boolean isFlaming, boolean isSmoking)
	    {
	        HuajiExplosion explosion = new HuajiExplosion(entityIn.world, entityIn, x, y, z, strength, isFlaming, isSmoking);
	        if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(entityIn.world, explosion)) return explosion;
	        explosion.doExplosionA();
	        explosion.doExplosionB(true);
	        return explosion;
	    }

}
