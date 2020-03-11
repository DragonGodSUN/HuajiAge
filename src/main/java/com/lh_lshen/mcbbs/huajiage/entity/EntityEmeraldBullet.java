package com.lh_lshen.mcbbs.huajiage.entity;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import com.lh_lshen.mcbbs.huajiage.particle.EnumHuajiPraticle;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
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

public class EntityEmeraldBullet extends EntityThrowable{
	private static final String TAG_ROTATION = "rotation_t";
	private static final String TAG_PITCH = "pitch_t";
	private static final String TAG_LIFE = "life_t";

	private static final DataParameter<Float> ROTATION = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> PITCH = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> LIFE = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.FLOAT);
	public EntityEmeraldBullet(World worldIn) {
		super(worldIn);
		
	}
	public EntityEmeraldBullet(World worldIn,EntityLivingBase throwerIn) {
		super(worldIn,throwerIn);

	}
	@Override
	protected void entityInit() {
		super.entityInit();
		
		dataManager.register(ROTATION, 0F);
		dataManager.register(PITCH, 0F);
		dataManager.register(LIFE, 0F);
	}

	@Override
	public void writeEntityToNBT(@Nonnull NBTTagCompound cmp) {
		super.writeEntityToNBT(cmp);

		cmp.setFloat(TAG_ROTATION, getRotation());
		cmp.setFloat(TAG_PITCH, getPitch());
		cmp.setFloat(TAG_LIFE, getLife());

        
	}

	@Override
	public void readEntityFromNBT(@Nonnull NBTTagCompound cmp) {
		super.readEntityFromNBT(cmp);

		setRotation(cmp.getFloat(TAG_ROTATION));
		setPitch(cmp.getFloat(TAG_PITCH));
		setLife(cmp.getFloat(TAG_LIFE));
		
	}
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(getLife()>0) {
			setLife(getLife()-1);
		}else {
			this.setDead();
		}
		if(this.motionX==0&&this.motionY==0&&this.motionZ==0) {
			this.setRotation(this.getPitch(), this.getRotation());
		}else {
				double r1=(Math.random()-0.5)*0.2;
				double r2=(Math.random()-0.5)*0.2;
				double r3=(Math.random()-0.5)*0.2;
				if(r1>0.05) {
				world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK,posX+r1, posY+r2, posZ+r3, r1, r2, r3);
				}
		}
		
	}
	@Override
	protected void onImpact(RayTraceResult result) {
		List<Entity> knife=this.world.getEntitiesInAABBexcluding(this,this.getEntityBoundingBox().grow(3),null);
		int extra=knife.size();
		if(result.entityHit!=null) {
			if(result.entityHit!=thrower) {
				if(!world.isRemote) {
					if(result.entityHit instanceof EntityLivingBase) {
						result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5f+extra);
						}
					this.setDead();
					}
				}
			}
		if(result.typeOfHit ==result.typeOfHit.BLOCK)
		{
			if(world.getBlockState(result.getBlockPos()).getCollisionBoundingBox(world, result.getBlockPos())!=Block.NULL_AABB)
			{
			this.setDead();
			}
		}
		this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0F, 1f);
		this.world.createExplosion(this,posX, posY, posZ, 0.5f, false);
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

}
