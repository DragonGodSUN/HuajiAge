package com.lh_lshen.mcbbs.huajiage.entity;

import java.util.List;

import javax.annotation.Nonnull;

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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.end.DragonFightManager;

public class EntityMultiKnife extends EntityThrowable{
	private static final String TAG_ROTATION = "rotation_t";
	private static final String TAG_PITCH = "pitch_t";
	private static final String TAG_LIFE = "life_t";
	private static final String TAG_EXTRA = "extra_t";

	private static final DataParameter<Float> ROTATION = EntityDataManager.createKey(EntityMultiKnife.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> PITCH = EntityDataManager.createKey(EntityMultiKnife.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> LIFE = EntityDataManager.createKey(EntityMultiKnife.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> EXTRA = EntityDataManager.createKey(EntityMultiKnife.class,
			DataSerializers.FLOAT);
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
	}

	@Override
	public void writeEntityToNBT(@Nonnull NBTTagCompound cmp) {
		super.writeEntityToNBT(cmp);

		cmp.setFloat(TAG_ROTATION, getRotation());
		cmp.setFloat(TAG_PITCH, getPitch());
		cmp.setFloat(TAG_LIFE, getLife());
		cmp.setFloat(TAG_EXTRA, getLife());

        
	}

	@Override
	public void readEntityFromNBT(@Nonnull NBTTagCompound cmp) {
		super.readEntityFromNBT(cmp);

		setRotation(cmp.getFloat(TAG_ROTATION));
		setPitch(cmp.getFloat(TAG_PITCH));
		setLife(cmp.getFloat(TAG_LIFE));
		setLife(cmp.getFloat(TAG_EXTRA));
		
	}
	@Override
	public void onUpdate() {

        List<Entity> knife=this.world.getEntitiesInAABBexcluding(this,this.getEntityBoundingBox().grow(20),null);
        List<EntityDragon> knifeD=this.world.getEntitiesWithinAABB(EntityDragon.class,this.getEntityBoundingBox().grow(10));
        int extra=knife.size();
		super.onUpdate();
		if(getLife()>0) {
			setLife(getLife()-1);
		}else {
			this.setDead();
		}
		if(this.motionX==0&&this.motionY==0&&this.motionZ==0) {
			this.setRotation(this.getPitch(), this.getRotation());
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
			result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5f+getExtra());
			}
	   
	     this.setDead();
	     }
	 }
}if(result.typeOfHit ==result.typeOfHit.BLOCK)
{
	if(world.getBlockState(result.getBlockPos()).getCollisionBoundingBox(world, result.getBlockPos())!=Block.NULL_AABB)
	{
     float f2 = MathHelper.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
     this.posX +=1.25*this.motionX / (double)f2;
     this.posY +=1.25*this.motionY/ (double)f2;
     this.posZ +=1.25*this.motionZ / (double)f2;
     this.playSound(SoundEvents.ENTITY_ARROW_HIT, 1.0F, 1.2F / (this.rand.nextFloat() * 0.2F + 0.9F));
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
	
}
