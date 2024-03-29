package com.lh_lshen.mcbbs.huajiage.entity;

import com.lh_lshen.mcbbs.huajiage.damage_source.DamageEmeraldSplash;
import com.lh_lshen.mcbbs.huajiage.util.HAMathHelper;
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
	private static final String TAG_TYPE = "type";
	private static final String TAG_TARGET = "target";

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
	private static final DataParameter<String> TYPE = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.STRING);
	private static final DataParameter<String> TARGET = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.STRING);
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
		dataManager.register(TYPE,"emerald");
		dataManager.register(TARGET,"a723e52d-a1b5-4121-838b-65fa63bfc161");

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
		cmp.setString(TAG_TYPE, getType());

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
		setType(cmp.getString(TAG_TYPE));
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
		List<EntityLivingBase> entities=this.world.getEntitiesWithinAABB(EntityLivingBase.class,this.getEntityBoundingBox().grow(20),null);
		for (EntityLivingBase e : entities){
			if (e.getUniqueID().toString().equals(getTarget())){
				Vec3d v = HAMathHelper.getVector(this.getPositionVector(), e.getPositionVector());
				NBTHelper.setEntityFloat(this, EntityEmeraldBullet.TAGS_ENTITY.MOTION_X.getTag(), (float) v.x);
				NBTHelper.setEntityFloat(this, EntityEmeraldBullet.TAGS_ENTITY.MOTION_Y.getTag(), (float) v.y);
				NBTHelper.setEntityFloat(this, EntityEmeraldBullet.TAGS_ENTITY.MOTION_Z.getTag(), (float) v.z);
			}
		}

		if(getStayTime()>0) {
			this.motionX = 0;
			this.motionY = 0;
			this.motionZ =0;
		}else{
			Vec3d v =new Vec3d( NBTHelper.getEntityFloat(this,TAGS_ENTITY.MOTION_X.getTag()),
								NBTHelper.getEntityFloat(this, TAGS_ENTITY.MOTION_Y.getTag()),
								NBTHelper.getEntityFloat(this, TAGS_ENTITY.MOTION_Z.getTag()));
			if(v.length()>0) {
			this.motionX = v.x;
			this.motionY = v.y;
			this.motionZ = v.z;
			NBTHelper.setEntityFloat(this, TAGS_ENTITY.MOTION_X.getTag(), 0);
			NBTHelper.setEntityFloat(this, TAGS_ENTITY.MOTION_Y.getTag(), 0);
			NBTHelper.setEntityFloat(this, TAGS_ENTITY.MOTION_Z.getTag(), 0);
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

	public String getType() {
		return dataManager.get(TYPE);
	}

	public void setType(String type) {
		dataManager.set(TYPE, type);
	}

	public String getTarget() {
		return dataManager.get(TARGET);
	}

	public void setTarget(String type) {
		dataManager.set(TARGET, type);
	}


	public enum TAGS_ENTITY{
		MOTION_X("huajiage.motion.x"),
		MOTION_Y("huajiage.motion.y"),
		MOTION_Z("huajiage.motion.z")
		;
		TAGS_ENTITY(String tag) {
			this.tag = tag;
		}
		private final String tag;

		public String getTag() {
			return tag;
		}
	}

}
