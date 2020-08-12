package com.lh_lshen.mcbbs.huajiage.entity;

import com.lh_lshen.mcbbs.huajiage.damage_source.DamageEmeraldSplash;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class EntityEmeraldBullet extends EntityThrowable{
	private static final String TAG_ROTATION = "rotation";
	private static final String TAG_ROTATION_RANDOM = "rotation_forward";
	private static final String TAG_PITCH = "pitch";
	private static final String TAG_LIFE = "life";
	private static final String TAG_DAMAGE = "damage";
	private static final String TAG_STAY = "stay";
	private static final String TAG_HUGE = "huge";

	private static final DataParameter<Float> ROTATION = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> ROTATION_RANDOM = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> PITCH = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> LIFE = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> DAMAGE = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> STAY = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Boolean> HUGE = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.BOOLEAN);
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
		dataManager.register(ROTATION_RANDOM, 0F);
		dataManager.register(PITCH, 0F);
		dataManager.register(LIFE, 0F);
		dataManager.register(DAMAGE, 5F);
		dataManager.register(STAY, 0F);
		dataManager.register(HUGE, false);
	}

	@Override
	public void writeEntityToNBT(@Nonnull NBTTagCompound cmp) {
		super.writeEntityToNBT(cmp);

		cmp.setFloat(TAG_ROTATION, getRotation());
		cmp.setFloat(TAG_ROTATION_RANDOM, getRotationRandom());
		cmp.setFloat(TAG_PITCH, getPitch());
		cmp.setFloat(TAG_LIFE, getLife());
		cmp.setFloat(TAG_DAMAGE, getDamage());
		cmp.setFloat(TAG_STAY, getStayTime());
		cmp.setBoolean(TAG_HUGE, isSplashHuge());
        
	}

	@Override
	public void readEntityFromNBT(@Nonnull NBTTagCompound cmp) {
		super.readEntityFromNBT(cmp);

		setRotation(cmp.getFloat(TAG_ROTATION));
		setRotationRandom(cmp.getFloat(TAG_ROTATION_RANDOM));
		setPitch(cmp.getFloat(TAG_PITCH));
		setLife(cmp.getFloat(TAG_LIFE));
		setDamage(cmp.getFloat(TAG_DAMAGE));
		setStayTime(cmp.getFloat(TAG_STAY));
		setSplashHuge(cmp.getBoolean(TAG_HUGE));
	}
	@Override
	public void onUpdate() {
		super.onUpdate();
		if(getLife()>0) {
			if(getStayTime()<=0) 
			{
				setLife(getLife()-1f);
			}else {
				setStayTime(getStayTime()-1f);
			}
		}else {
			this.setDead();
		}
		if(getStayTime()>0) {
			this.motionX = 0;
			this.motionY = 0;
			this.motionZ =0;
		}else{
			Vec3d v =new Vec3d(NBTHelper.getEntityFloat(this, "huajiage.motion.x"), 
											 NBTHelper.getEntityFloat(this, "huajiage.motion.y"),
											 NBTHelper.getEntityFloat(this, "huajiage.motion.z"));
			if(v.length()>0) {
			this.motionX = v.x;
			this.motionY = v.y;
			this.motionZ = v.z;
			NBTHelper.setEntityFloat(this, "huajiage.motion.x", 0);
			NBTHelper.setEntityFloat(this, "huajiage.motion.y", 0);
			NBTHelper.setEntityFloat(this, "huajiage.motion.z", 0);
			}
			
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
		if(isSplashHuge()) {
				double r1=(Math.random()-0.5)*0.2;
				double r2=(Math.random()-0.5)*0.2;
				double r3=(Math.random()-0.5)*0.2;
				if(r1>0.05) {
				world.spawnParticle(EnumParticleTypes.VILLAGER_HAPPY,posX+r1, posY+r2, posZ+r3, r1, r2, r3);
				}
		}
		
	}
	@Override
	protected void onImpact(RayTraceResult result) {
		List<Entity> group=this.world.getEntitiesInAABBexcluding(this,this.getEntityBoundingBox().grow(2),null);
		int extra=group.size();
		if(result.typeOfHit == RayTraceResult.Type.ENTITY) {
			if(result.entityHit!=null&&result.entityHit!=thrower) {
				if(!this.world.isRemote) {
				if(!(result.entityHit instanceof EntityEmeraldBullet)) {
					result.entityHit.attackEntityFrom(new DamageEmeraldSplash(this, getThrower()), getDamage()*(1+extra));
					this.setDead();
					this.world.createExplosion(this,posX, posY, posZ, 0.5f, false);
					}
				}
				this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0F, 1f);
				}
			}
		if(result.typeOfHit ==RayTraceResult.Type.BLOCK)
		{
			IBlockState state =world.getBlockState(result.getBlockPos());
			if(state.getCollisionBoundingBox(world, result.getBlockPos()) != null)
			{
			this.setDead();
			this.playSound(SoundEvents.BLOCK_GLASS_BREAK, 1.0F, 1f);
			this.world.createExplosion(this,posX, posY, posZ, 0.5f, false);
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
	
	public float getRotationRandom() {
		return dataManager.get(ROTATION_RANDOM);
	}
	
	public void setRotationRandom(float rot) {
		dataManager.set(ROTATION_RANDOM, rot);
	}
	
	public float getDamage() {
		return dataManager.get(DAMAGE);
	}
	
	public void setDamage(float damage) {
		dataManager.set(DAMAGE, damage);
	}
	
	public float getStayTime() {
		return dataManager.get(STAY);
	}
	
	public void setStayTime(float damage) {
		dataManager.set(STAY, damage);
	}
	
	public boolean isSplashHuge() {
		return dataManager.get(HUGE);
	}
	
	public void setSplashHuge(boolean isHuge) {
		dataManager.set(HUGE, isHuge);
	}



}
