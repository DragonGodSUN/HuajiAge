package com.lh_lshen.mcbbs.huajiage.entity;

import java.util.List;

import javax.annotation.Nonnull;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageDioHitClient;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumFacing.AxisDirection;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.end.DragonFightManager;

public class EntityRoadRoller extends EntityThrowable {
	private static final String TAG_ROTATION = "rotation_t";
	private static final String TAG_PITCH = "pitch_t";
	private static final String TAG_LIFE = "life_t";
	private static final String TAG_EXTRA = "extra_t";

	private static final DataParameter<Float> ROTATION = EntityDataManager.createKey(EntityRoadRoller.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> PITCH = EntityDataManager.createKey(EntityRoadRoller.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> LIFE = EntityDataManager.createKey(EntityRoadRoller.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> EXTRA = EntityDataManager.createKey(EntityRoadRoller.class,
			DataSerializers.FLOAT);
	public EntityRoadRoller(World worldIn) {
		super(worldIn);
		
	}
	public EntityRoadRoller(World worldIn,EntityLivingBase throwerIn) {
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
	public AxisAlignedBB getEntityBoundingBox() {
		
		return super.getEntityBoundingBox().grow(2,1.5,2);
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
		
        int extra=getEntityData().getInteger("huajiage.dio_push");
//        int time_stop = getEntityData().getInteger(HuajiConstant.TIME_STOP);
		super.onUpdate();
		if(getLife()>0) {
			setLife(getLife()-1);
		}else {
			this.setDead();
		}
//		if(time_stop>0) {
//			NBTHelper.setEntityInteger(this, HuajiConstant.TIME_STOP, time_stop-1);
//			this.motionX = 0 ;
//			this.motionY = 0 ;
//			this.motionZ = 0 ;
//		}
//		if(this.motionX==0&&this.motionY==0&&this.motionZ==0) {
//			this.setRotation(this.getPitch(), this.getRotation());
//		}
		if(thrower!=null) {
		boolean isStar =false;
		if(((EntityPlayer)thrower).hasCapability(CapabilityStandHandler.STAND_TYPE, null)&&
				((EntityPlayer)thrower).getCapability(CapabilityStandHandler.STAND_TYPE, null).getStand().equals(EnumStandtype.STAR_PLATINUM.getName())) {
			isStar = true;
		}
		if(thrower.getEntityData().getInteger("huajiage.the_world")>0) {
			if(extra>getExtra()) {
				 Vec3d targetPosition = thrower.getPositionVector();
				 MessageDioHitClient msg1 = new MessageDioHitClient(targetPosition, false); 
         	     MessageDioHitClient msg2 = new MessageDioHitClient(targetPosition, true ); 
     	     if(thrower.getEntityData().getInteger("huajiage.dio_flag")==0&&!isStar) {  
	        	 if(thrower instanceof EntityPlayer) {
	        	          ServerUtil.sendPacketToNearbyPlayers((EntityPlayer) thrower, msg1);}
	         	          thrower.getEntityData().setInteger("huajiage.dio_flag", 180);
	         	          }
	         	if(thrower.getEntityData().getInteger("huajiage.dio_flag")<140&&!isStar&&thrower.getEntityData().getInteger("huajiage.dio_flag")>0) {   
	         		if(thrower instanceof EntityPlayer) {        
	         			ServerUtil.sendPacketToNearbyPlayers((EntityPlayer) thrower, msg2);
	         		}
	         	}
	         	if(isStar) {
	         		MessageParticleGenerator pacticle = new MessageParticleGenerator(targetPosition, EnumParticleTypes.FIREWORKS_SPARK, 60, 5, 1);
	         		ServerUtil.sendPacketToNearbyPlayers(thrower,pacticle);
	         		HuajiSoundPlayer.playToNearbyClient(thrower, SoundLoader.STAND_STAR_PLATINUM_REPEAT_1, 1f);
	         		thrower.addPotionEffect(new PotionEffect(PotionLoader.potionStand,60));
	         	}
			setExtra(extra);
			}
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
		boolean flag=getExtra()>10&&ConfigHuaji.Stands.roadRolerExplosion;
		if(result.entityHit!=null) {
			if(result.entityHit!=thrower) {
			 if(!world.isRemote) {
				 if(result.entityHit instanceof EntityLivingBase) {
			result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 5f+getExtra()*2);
			}
	     this.world.createExplosion(this, posX, posY, posZ,getExtra()>5?4f:2f,flag);
	     this.setDead();
			 }
       }	
			}
		else {
			this.world.createExplosion(this, posX, posY, posZ,getExtra()>5?4f:2f,flag);
		     this.setDead();
		     }
		
	}

	@Override
	protected float getGravityVelocity() {
		if(motionX==0&&motionY==0&&motionZ==0) {
			return 0F;			
		}
		return 0.06f;
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
