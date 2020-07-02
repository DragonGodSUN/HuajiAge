package com.lh_lshen.mcbbs.huajiage.entity;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.lh_lshen.mcbbs.huajiage.damage_source.DamageEmeraldSplash;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.particle.EnumHuajiPraticle;
import com.lh_lshen.mcbbs.huajiage.util.HAMathHelper;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import ibxm.Player;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFollow;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumFacing.AxisDirection;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.end.DragonFightManager;

public class EntitySheerHeartAttack extends EntityTameable{

	private static final String TAG_LIFE = "life";
	private static final String TAG_DAMAGE = "damage";
	private static final String TAG_JUMP_TICK = "jump_tick";

	private static final DataParameter<Float> LIFE = EntityDataManager.createKey(EntitySheerHeartAttack.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> DAMAGE = EntityDataManager.createKey(EntitySheerHeartAttack.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> JUMP_TICK = EntityDataManager.createKey(EntitySheerHeartAttack.class,
			DataSerializers.FLOAT);

	public EntitySheerHeartAttack(World worldIn) {
		super(worldIn);
		setSize(0.6f, 0.8f);
		
	}
	@Override
	protected void entityInit() {
		super.entityInit();
		
		dataManager.register(LIFE, 0F);
		dataManager.register(DAMAGE, 5F);
		dataManager.register(JUMP_TICK, 40F);

	}
	
	@Override
	protected void initEntityAI() {
		super.initEntityAI();
		
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIAttackMelee(this, 0.5D, false));
		this.tasks.addTask(4, new EntityAIFollowOwner(this, 1.0d, 5, 50));
		this.tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.4D));
		this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 0.4D));
		this.tasks.addTask(7, new EntityAIWatchClosest(this, EntityMob.class, 10.0F));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
//        this.tasks.addTask(8, new EntityAIfo
		
        this.targetTasks.addTask(1, new EntityAIOwnerHurtTarget(this));
        this.targetTasks.addTask(2, new EntityAIOwnerHurtByTarget(this));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityMob.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntitySlime.class, true));
        this.targetTasks.addTask(4, new EntityAIHurtByTarget(this, true));
	}
	
    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(999);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(30);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(50f);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6d);
    }
	@Override
	public void writeEntityToNBT(@Nonnull NBTTagCompound cmp) {
		super.writeEntityToNBT(cmp);
		cmp.setFloat(TAG_LIFE, getLife());
		cmp.setFloat(TAG_DAMAGE, getDamage());
		cmp.setFloat(TAG_JUMP_TICK, getJump());
        
	}

	@Override
	public void readEntityFromNBT(@Nonnull NBTTagCompound cmp) {
		super.readEntityFromNBT(cmp);
		setLife(cmp.getFloat(TAG_LIFE));
		setLife(cmp.getFloat(TAG_DAMAGE));
		setJump(cmp.getFloat(TAG_JUMP_TICK));
	}
	@Override
	public void onUpdate() {
		super.onUpdate();
		EntityLivingBase entity = getAttackTarget();
		if(entity!=null&&HAMathHelper.getDistance(this.getPositionVector(), entity.getPositionVector())<8) {
//			if(getJump()>0) {
//				JumpTickDecreace(25);
//			}
//			if(getJumpBegin()) {
			if(getOwner() instanceof EntityPlayer) {
				world.playSound((EntityPlayer) getOwner(), getOwner().getPosition(),SoundLoader.STAND_KILLER_QUEEN_TRIGGER, SoundCategory.NEUTRAL, 2f, 1f);
			}
			Vec3d vec = HAMathHelper.getVectorEntityEye(this, entity);
			this.motionX = vec.x*0.8;
			this.motionY = vec.y*0.8;
			this.motionZ = vec.z*0.8;
//			}
		}
//		else if(getJump()<40f) {
//			setJump(40f);
//		}
	}
	@Override
	protected void collideWithEntity(Entity entityIn) {
		super.collideWithEntity(entityIn);
		if (entityIn == this.getAttackTarget()) {
			List<EntityLivingBase> list = world.getEntitiesWithinAABB(EntityLivingBase.class, entityIn.getEntityBoundingBox().grow(3));
			entityIn.attackEntityFrom(DamageSource.causeExplosionDamage(getOwner()), getDamage());
			world.createExplosion(this, this.posX, this.posY, this.posZ, 2f, false);
			if(list!=null) {
				for(EntityLivingBase entity : list) {
					if(entity!=getOwner()&&!(entity instanceof EntityTameable)) {
						float distance = (float) HAMathHelper.getDistance(this.getPositionVector(), entity.getPositionEyes(0));
						float damage = getDamage()-distance*15;
						entity.attackEntityFrom(DamageSource.causeExplosionDamage(getOwner()),damage>0?damage:0);
					}
				}
			}
			this.setDead();
		}
	}
	@Override
	public EnumActionResult applyPlayerInteraction(EntityPlayer player, Vec3d vec, EnumHand hand) {
		if(player==getOwner()) {
			this.setDead();
			player.heal(10f);
			player.playSound(SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 1f, 1f);
			player.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,300,1));
		}

		
		return super.applyPlayerInteraction(player, vec, hand);
	}
	
	public boolean getJumpBegin() {
		return (int)getJump()<=0;
	}
	
	public void JumpTickDecreace(float point) {
		dataManager.set(LIFE, getJump()-point);
	}
	
	public float getLife() {
		return dataManager.get(LIFE);
	}
	
	public void setLife(float timeTick) {
		dataManager.set(LIFE, timeTick);
	}
	
	
	public float getDamage() {
		return dataManager.get(DAMAGE);
	}
	
	public void setDamage(float damage) {
		dataManager.set(DAMAGE, damage);
	}
	
	public float getJump() {
		return dataManager.get(JUMP_TICK);
	}
	
	public void setJump(float ticks) {
		dataManager.set(JUMP_TICK, ticks);
	}
	
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_IRONGOLEM_ATTACK;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_WITHER_HURT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WITHER_DEATH;
    }

    @Override
    protected float getSoundVolume() {
        return 0.6f;
    }

    @Override
    protected void playStepSound(BlockPos pos, Block blockIn) {
        this.playSound(SoundEvents.ENTITY_IRONGOLEM_STEP, 0.5F, 1.0F);
    }
    
	@Override
	public EntityAgeable createChild(EntityAgeable ageable) {
		return null;
	}
	
	   @Override
	    public boolean isBreedingItem(ItemStack stack) {
	        return false;
	    }

	    @Override
	    public boolean canMateWith(@Nonnull EntityAnimal otherAnimal) {
	        return false;
	    }
	    @Override
	    public boolean shouldAttackEntity(EntityLivingBase target, EntityLivingBase owner) {
	    	// TODO Auto-generated method stub
	    	return target!=getOwner()&&!(target instanceof EntityTameable);
	    }
	
}
