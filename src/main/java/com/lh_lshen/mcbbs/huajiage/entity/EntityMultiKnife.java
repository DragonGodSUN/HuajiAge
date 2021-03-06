package com.lh_lshen.mcbbs.huajiage.entity;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import com.lh_lshen.mcbbs.huajiage.damage_source.DamageEmeraldSplash;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumFacing.AxisDirection;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.end.DragonFightManager;

public class EntityMultiKnife extends EntityThrowable{
	private static final String TAG_ROTATION = "rotation_t";
	private static final String TAG_PITCH = "pitch_t";
	private static final String TAG_LIFE = "life_t";
	private static final String TAG_EXTRA = "extra_t";
	private static final String TAG_LIGHT = "light_t";

	private static final DataParameter<Float> ROTATION = EntityDataManager.createKey(EntityMultiKnife.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> PITCH = EntityDataManager.createKey(EntityMultiKnife.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> LIFE = EntityDataManager.createKey(EntityMultiKnife.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> EXTRA = EntityDataManager.createKey(EntityMultiKnife.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Boolean> LIGHT = EntityDataManager.createKey(EntityMultiKnife.class,
			DataSerializers.BOOLEAN);
	public EntityMultiKnife(World worldIn) {
		super(worldIn);
		
	}
	public EntityMultiKnife(World worldIn,EntityLivingBase throwerIn) {
		super(worldIn,throwerIn);

	}
	@Override
	protected void entityInit() {
		super.entityInit();
		
		dataManager.register(ROTATION, 0F);
		dataManager.register(PITCH, 0F);
		dataManager.register(LIFE, 0F);
		dataManager.register(EXTRA, 0F);
		dataManager.register(LIGHT, false);
	}

	@Override
	public void writeEntityToNBT(@Nonnull NBTTagCompound cmp) {
		super.writeEntityToNBT(cmp);

		cmp.setFloat(TAG_ROTATION, getRotation());
		cmp.setFloat(TAG_PITCH, getPitch());
		cmp.setFloat(TAG_LIFE, getLife());
		cmp.setFloat(TAG_EXTRA, getExtra());
		cmp.setBoolean(TAG_LIGHT, getLight());
        
	}

	@Override
	public void readEntityFromNBT(@Nonnull NBTTagCompound cmp) {
		super.readEntityFromNBT(cmp);

		setRotation(cmp.getFloat(TAG_ROTATION));
		setPitch(cmp.getFloat(TAG_PITCH));
		setLife(cmp.getFloat(TAG_LIFE));
		setExtra(cmp.getFloat(TAG_EXTRA));
		
	}
	@Override
	public void onUpdate() {

        List<Entity> knife=this.world.getEntitiesInAABBexcluding(this,this.getEntityBoundingBox().grow(20),null);
        int extra=knife.size();
		super.onUpdate();
		if(getLife()>0) {
			setLife(getLife()-1);
		}else {
			this.setDead();
		}
		if(this.motionX==0&&this.motionY==0&&this.motionZ==0) {
			this.setRotation(this.getPitch(), this.getRotation());
		}else {
			if(getLight()) {
				double r1=(Math.random()-0.5)*0.2;
				double r2=(Math.random()-0.5)*0.2;
				double r3=(Math.random()-0.5)*0.2;
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,posX+r1, posY+r2, posZ+r3, r1, r2, r3);
				for(int i=0;i<2;i++) {
					world.spawnParticle(EnumParticleTypes.LAVA,posX+r1, posY+r2, posZ+r3, r1, r2, r3);
				}
			}
		}
		if(thrower!=null) {
		if(thrower.getEntityData().getInteger("huajiage.the_world")>0) {
			setExtra(extra);
		  }
		}
//		if(knifeD!=null) {
//			for(EntityDragon d: knifeD) {
//				 d.attackEntityFromPart(d.dragonPartBody,DamageSource.causeThrownDamage(this, this.getThrower()), 5f+getExtra());
//			}
//			this.setDead();
//		}
		
	}
	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.entityHit!=null) {
			if(result.entityHit!=thrower) {
				if(!world.isRemote) {
					if(result.entityHit instanceof EntityLivingBase) {
						if(getLight()) {
							result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()),3f);
							result.entityHit.setFire(6);
								world.playSound(posX, posY, posZ, SoundEvents.ENTITY_FIREWORK_LARGE_BLAST, SoundCategory.BLOCKS, 0.5f, 0.5f, true);
							}
						result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5f+getExtra());
							if(result.entityHit instanceof EntityDragon) {
								EntityDragon dragon = (EntityDragon) result.entityHit;
								dragon.attackEntityFromPart(dragon.dragonPartHead, new DamageEmeraldSplash(this, this.getThrower()), 5f+getExtra());
							}
						}
					this.setDead();
					}
				}
			}
		if(result.typeOfHit ==result.typeOfHit.BLOCK)
		{
			if(world.getBlockState(result.getBlockPos()).getCollisionBoundingBox(world, result.getBlockPos())!=Block.NULL_AABB)
			{
			float f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
			this.posX +=1.25*this.motionX / (double)f2;
			this.posY +=1.25*this.motionY/ (double)f2;
			this.posZ +=1.25*this.motionZ / (double)f2;
			if(!getLight())
			{
				this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
			}else {
				this.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
			}
			
			this.inGround = true;
			motionX=0;
			motionY=0;
			motionZ=0;
			}
		}
	}
	@Override
	protected float getGravityVelocity() {
		return 0F;
	}
	public float getRotation() {
		return dataManager.get(ROTATION);
	}
	
	public void setRotation(float rot) {
		dataManager.set(ROTATION, rot);
	}
	
	public float getPitch() {
		return dataManager.get(PITCH);
	}
	
	public void setPitch(float rot) {
		dataManager.set(PITCH, rot);
	}
	public float getLife() {
		return dataManager.get(LIFE);
	}
	
	public void setLife(float timeTick) {
		dataManager.set(LIFE, timeTick);
	}
	public float getExtra() {
		return dataManager.get(EXTRA);
	}
	
	public void setExtra(float damage) {
		dataManager.set(EXTRA, damage);
	}
	
	public boolean getLight() {
		return dataManager.get(LIGHT);
	}
	public void setLight(Boolean light) {
		dataManager.set(LIGHT, light);
	}
}
