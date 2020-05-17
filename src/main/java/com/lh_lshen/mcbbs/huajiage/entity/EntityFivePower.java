package com.lh_lshen.mcbbs.huajiage.entity;

import java.util.List;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.lh_lshen.mcbbs.huajiage.damage_source.DamageEmeraldSplash;
import com.lh_lshen.mcbbs.huajiage.damage_source.DamageFivePower;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityFivePower extends EntityThrowable{
	private static final String TAG_LIFE = "life";
	private static final String TAG_DE = "de";
	private static final String TAG_MASTER = "master";
	private static final DataParameter<Float> LIFE = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Boolean> DE = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<String> MASTER = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.STRING);
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
		dataManager.register(MASTER, "acfd894c-ad88-4b34-addf-a8d10e2a67f7");
	}
	@Override
	public void writeEntityToNBT(@Nonnull NBTTagCompound cmp) {
		super.writeEntityToNBT(cmp);
		cmp.setFloat(TAG_LIFE, getLife());
		cmp.setBoolean(TAG_DE, isDe());
		cmp.setString(TAG_MASTER, getMatser());
	}

	@Override
	public void readEntityFromNBT(@Nonnull NBTTagCompound cmp) {
		super.readEntityFromNBT(cmp);
		setLife(cmp.getFloat(TAG_LIFE));
		setDe(cmp.getBoolean(TAG_DE));
		setMaster(cmp.getString(TAG_MASTER));
	}
	@Override
	public void onUpdate() {
		super.onUpdate();
		UUID uuid =UUID.fromString(getMatser());
		EntityPlayer player = world.getPlayerEntityByUUID(uuid);
		if(player==null) {
			this.setDead();
		}
		List<EntityLivingBase> entities = this.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().grow(1));
		for(EntityLivingBase entity : entities) {
			if(entity!=thrower&&player!=null&&entity!=player) {
				if(!isDe()) {
					entity.setFire(3);
					if(thrower instanceof EntityPlayer) {
					entity.attackEntityFrom(new DamageFivePower(player), 15f);
					}else {
					entity.attackEntityFrom(DamageSource.OUT_OF_WORLD , 15f);
					}
					if(NBTHelper.getEntityBoolean(entity, "huajiage.de")) {
						NBTHelper.setEntityBoolean(entity, "huajiage.de", false);
						entity.attackEntityFrom(new DamageFivePower(player), 50f);
						player.heal(2f);
						for(int i=0;i<3;i++) {
							world.spawnEntity(new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ, true));
						}
					}
				}else {
					NBTHelper.setEntityBoolean(entity, "huajiage.de", true);
					((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,60));
					((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,60,2));
					((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS,60,2));
					((EntityLivingBase)entity).addPotionEffect(new PotionEffect(MobEffects.GLOWING,60));
				}
			}
		}
		if(getLife()<180) {
				setLife(getLife()+1f);
		}else {
			this.setDead();
		}
		double r = Math.random()-0.5;
		if(!isDe()) {
			
		for(int i=0;i<3;i++) {
			world.spawnParticle(EnumParticleTypes.FLAME, posX+r, posY+r, posZ+r, (Math.random()-0.5)/10, (Math.random()-0.5)/10, (Math.random()-0.5)/10);
			}
		}else {
			world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX+r, posY+r, posZ+r, (Math.random()-0.5)/10, (Math.random()-0.5)/10, (Math.random()-0.5)/10);
		}
		
	}
	@Override
	public boolean hasNoGravity() {
		return true;
	}
	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.typeOfHit == RayTraceResult.Type.ENTITY) {
			if(result.entityHit!=null&&result.entityHit instanceof EntityLivingBase&&result.entityHit!=thrower) {
				if(!this.world.isRemote) {
				if(!(result.entityHit instanceof EntityFivePower)) {
					if(!isDe()) {
						result.entityHit.attackEntityFrom(new DamageEmeraldSplash(this, getThrower()), 10f);
						this.world.createExplosion(this,posX, posY, posZ, 1f, false);
						if(NBTHelper.getEntityBoolean(result.entityHit, "huajiage.de")) {
							NBTHelper.setEntityBoolean(result.entityHit, "huajiage.de", false);
							result.entityHit.attackEntityFrom(new DamageEmeraldSplash(this, getThrower()), 10f);
							thrower.heal(2f);
							for(int i=0;i<3;i++) {
								world.spawnEntity(new EntityLightningBolt(world, result.entityHit.posX, result.entityHit.posY, result.entityHit.posZ, true));
							}
						}
					}else {
						NBTHelper.setEntityBoolean(result.entityHit, "huajiage.de", true);
						((EntityLivingBase)result.entityHit).addPotionEffect(new PotionEffect(MobEffects.BLINDNESS,60));
						((EntityLivingBase)result.entityHit).addPotionEffect(new PotionEffect(MobEffects.SLOWNESS,60,2));
						((EntityLivingBase)result.entityHit).addPotionEffect(new PotionEffect(MobEffects.WEAKNESS,60,2));
						((EntityLivingBase)result.entityHit).addPotionEffect(new PotionEffect(MobEffects.GLOWING,60));
						}
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
	
	public  boolean isDe() {
		return dataManager.get(DE);
	}
	
	public void setDe(boolean de) {
		dataManager.set(DE, de);
	}
	
	public String getMatser() {
		return dataManager.get(MASTER);
	}
	
	public void setMaster(String uuid) {
		dataManager.set(MASTER, uuid);
	}

	
}
