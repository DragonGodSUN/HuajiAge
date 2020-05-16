package com.lh_lshen.mcbbs.huajiage.entity;

import java.util.List;

import javax.annotation.Nonnull;

import com.lh_lshen.mcbbs.huajiage.damage_source.DamageEmeraldSplash;
import com.lh_lshen.mcbbs.huajiage.damage_source.DamageFivePower;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityFivePower extends EntityThrowable{
	private static final String TAG_LIFE = "life";
	private static final String TAG_DE = "de";
	private static final DataParameter<Float> LIFE = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Boolean> DE = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.BOOLEAN);
	public EntityFivePower(World worldIn) {
		super(worldIn);
		
	}
	public EntityFivePower(World worldIn,EntityLivingBase throwerIn) {
		super(worldIn,throwerIn);

	}
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(LIFE, 0F);
		dataManager.register(DE, false);
	}
	@Override
	public void writeEntityToNBT(@Nonnull NBTTagCompound cmp) {
		super.writeEntityToNBT(cmp);
		cmp.setFloat(TAG_LIFE, getLife());
		cmp.setBoolean(TAG_DE, getDe());
	}

	@Override
	public void readEntityFromNBT(@Nonnull NBTTagCompound cmp) {
		super.readEntityFromNBT(cmp);
		setLife(cmp.getFloat(TAG_LIFE));
		setDe(cmp.getBoolean(TAG_DE));
	}
	@Override
	public void onUpdate() {
		super.onUpdate();
		List<EntityLivingBase> entities = this.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().grow(1));
		for(EntityLivingBase entity : entities) {
			if(entity!=thrower) {
				entity.setFire(3);
				if(thrower instanceof EntityPlayer) {
				entity.attackEntityFrom(new DamageFivePower(thrower), 15f);
				}else {
				entity.attackEntityFrom(DamageSource.OUT_OF_WORLD , 15f);
				}
			}
		}
		if(getLife()<180) {
				setLife(getLife()+1f);
		}else {
			this.setDead();
		}
		double r = Math.random()-0.5;
		for(int i=0;i<3;i++) {
		world.spawnParticle(EnumParticleTypes.FLAME, posX+r, posY+r, posZ+r, (Math.random()-0.5)/10, (Math.random()-0.5)/10, (Math.random()-0.5)/10);
		}
		
	}
	@Override
	public boolean hasNoGravity() {
		return true;
	}
	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.typeOfHit == RayTraceResult.Type.ENTITY) {
			if(result.entityHit!=null&&result.entityHit!=thrower) {
				if(!this.world.isRemote) {
				if(!(result.entityHit instanceof EntityFivePower)) {
					result.entityHit.attackEntityFrom(new DamageEmeraldSplash(this, getThrower()), 10f);
					this.world.createExplosion(this,posX, posY, posZ, 1f, false);
					}
				}
				this.playSound(SoundEvents.ENTITY_GENERIC_EXPLODE, 1.0F, 1f);
				}
			}
		if(result.typeOfHit ==RayTraceResult.Type.BLOCK)
		{
			IBlockState state =world.getBlockState(result.getBlockPos());
			if(state.getCollisionBoundingBox(world, result.getBlockPos()) != null)
			{
			this.setDead();
			}
		}
	}
	public float getLife() {
		return dataManager.get(LIFE);
	}
	
	public void setLife(float timeTick) {
		dataManager.set(LIFE, timeTick);
	}
	
	public  boolean getDe() {
		return dataManager.get(DE);
	}
	
	public void setDe(boolean de) {
		dataManager.set(DE, de);
	}

	
}
