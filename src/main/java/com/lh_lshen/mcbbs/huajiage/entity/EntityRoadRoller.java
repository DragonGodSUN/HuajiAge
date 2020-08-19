package com.lh_lshen.mcbbs.huajiage.entity;

import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.entity.EntityStandBase;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDioHitClient;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IProjectile;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class EntityRoadRoller extends EntityThrowable {
	private static final String TAG_ROTATION = "rotation_t";
	private static final String TAG_PITCH = "pitch_t";
	private static final String TAG_LIFE = "life_t";
	private static final String TAG_DAMAGE = "damage_t";
	private static final String TAG_EXTRA = "extra_t";
	private static final String TAG_TYPE = "type_t";

	private static final DataParameter<Float> ROTATION = EntityDataManager.createKey(EntityRoadRoller.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> PITCH = EntityDataManager.createKey(EntityRoadRoller.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> LIFE = EntityDataManager.createKey(EntityRoadRoller.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> DAMAGE = EntityDataManager.createKey(EntityRoadRoller.class,
			DataSerializers.FLOAT);
	private static final DataParameter<Float> EXTRA = EntityDataManager.createKey(EntityRoadRoller.class,
			DataSerializers.FLOAT);
	private static final DataParameter<String> TYPE = EntityDataManager.createKey(EntityRoadRoller.class,
			DataSerializers.STRING);
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
		dataManager.register(DAMAGE, 0F);
		dataManager.register(EXTRA, 0F);
		dataManager.register(TYPE, enumTYPE.ROAD_ROLLER.getName());
	}

	@Override
	public void writeEntityToNBT(@Nonnull NBTTagCompound cmp) {
		super.writeEntityToNBT(cmp);

		cmp.setFloat(TAG_ROTATION, getRotation());
		cmp.setFloat(TAG_PITCH, getPitch());
		cmp.setFloat(TAG_LIFE, getLife());
		cmp.setFloat(TAG_DAMAGE, getDamage());
		cmp.setFloat(TAG_EXTRA, getExtra());
		cmp.setString(TAG_TYPE, getType());

        
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
		setLife(cmp.getFloat(TAG_DAMAGE));
		setExtra(cmp.getFloat(TAG_EXTRA));
		setType(cmp.getString(TAG_TYPE));
		
	}
	@Override
	public void onUpdate() {
		super.onUpdate();
        int extra=getEntityData().getInteger("huajiage.dio_push");
        List<Entity> list = this.world.getEntitiesWithinAABB(Entity.class, this.getEntityBoundingBox());
        boolean flag=getExtra()>10&&ConfigHuaji.Stands.roadRolerExplosion;
        if(list!=null) {
        for(Entity entity : list) {
        	if(entity!=null && !(entity instanceof IProjectile) && entity != thrower && !(entity instanceof EntityStandBase)) {
   			 if(!world.isRemote) {
				 if(entity instanceof EntityLivingBase) {
					 if(entity instanceof EntityDragon) {
						 EntityDragon d = (EntityDragon) entity;
						 d.attackEntityFromPart(d.dragonPartHead, DamageSource.causeExplosionDamage(thrower),  getDamage()+getExtra()*2);
					 }else {
					 entity.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), getDamage()+getExtra()*2);
					 }
				 }
	     this.world.createExplosion(this, posX, posY, posZ,getExtra()>5?4f:2f,flag);
	     this.setDead();
   			 		}
        		}
        	}
        }
        
//        int time_stop = getEntityData().getInteger(HuajiConstant.TIME_STOP);
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
		IExposedData data = StandUtil.getStandData(thrower);
		if( data!=null && data.getStand().equals(StandLoader.STAR_PLATINUM.getName())) {
			isStar = true;
		}
		if(thrower.getEntityData().getInteger("huajiage.the_world")>0) {
			if(extra>getExtra()) {
				 Vec3d targetPosition = thrower.getPositionVector();
				 MessageDioHitClient msg1 = new MessageDioHitClient(targetPosition, false); 
         	     MessageDioHitClient msg2 = new MessageDioHitClient(targetPosition, true ); 
     	     if(thrower.getEntityData().getInteger("huajiage.dio_flag")==0&&!isStar) {  
	        	 if(thrower instanceof EntityPlayer) {
	        	          ServerUtil.sendPacketToNearbyPlayersStand((EntityPlayer) thrower, msg1);}
	         	          thrower.getEntityData().setInteger("huajiage.dio_flag", 180);
	         	          }
	         	if(thrower.getEntityData().getInteger("huajiage.dio_flag")<140&&!isStar&&thrower.getEntityData().getInteger("huajiage.dio_flag")>0) {   
	         		if(thrower instanceof EntityPlayer) {        
	         			ServerUtil.sendPacketToNearbyPlayersStand((EntityPlayer) thrower, msg2);
	         		}
	         	}
	         	if(isStar) {
	         		MessageParticleGenerator particle = new MessageParticleGenerator(targetPosition, EnumParticleTypes.FIREWORKS_SPARK, 60, 5, 1);
	         		ServerUtil.sendPacketToNearbyPlayers(thrower,particle);
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
					 result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), getDamage()+getExtra()*2);
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
	
	public float getDamage() {
		return dataManager.get(DAMAGE);
	}
	
	public void setDamage(float damage) {
		dataManager.set(DAMAGE, damage);
	}
	
	public float getExtra() {
		return dataManager.get(EXTRA);
	}
	
	public void setExtra(float damage) {
		dataManager.set(EXTRA, damage);
	}
	
	public String getType() {
		return dataManager.get(TYPE);
	}
	
	public void setType(String type) {
		dataManager.set(TYPE, type);
	}
	
	public enum enumTYPE {
		ROAD_ROLLER("road_roller"),
		CAR("car");
		enumTYPE(String name){
			this.name=name;
		}
		String name;
		public String getName() {
			return name;
		}
	}
	
}
