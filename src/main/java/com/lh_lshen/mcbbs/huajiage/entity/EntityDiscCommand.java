package com.lh_lshen.mcbbs.huajiage.entity;

import com.lh_lshen.mcbbs.huajiage.damage_source.DamageSelfAttack;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemDiscCommand;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.UUID;

public class EntityDiscCommand extends EntityThrowable{
	private static final String TAG_LIFE = "life";
	private static final String TAG_COMMAND = "command";
	private static final String TAG_MASTER = "master";
	private static final DataParameter<Float> LIFE = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.FLOAT);
	private static final DataParameter<String> COMMAND = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.STRING);
	private static final DataParameter<String> MASTER = EntityDataManager.createKey(EntityEmeraldBullet.class,
			DataSerializers.STRING);
	public EntityDiscCommand(World worldIn) {
		super(worldIn);

	}
	public EntityDiscCommand(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn,throwerIn);

	}
	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(LIFE, 0F);
		dataManager.register(COMMAND,"null");
		dataManager.register(MASTER, "acfd894c-ad88-4b34-addf-a8d10e2a67f7");
	}
	@Override
	public void writeEntityToNBT(@Nonnull NBTTagCompound cmp) {
		super.writeEntityToNBT(cmp);
		cmp.setFloat(TAG_LIFE, getLife());
		cmp.setString(TAG_COMMAND, getCommand());
		cmp.setString(TAG_MASTER, getMatser());
	}

	@Override
	public void readEntityFromNBT(@Nonnull NBTTagCompound cmp) {
		super.readEntityFromNBT(cmp);
		setLife(cmp.getFloat(TAG_LIFE));
		setCommand(cmp.getString(TAG_COMMAND));
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
//		List<EntityLivingBase> entities = this.world.getEntitiesWithinAABB(EntityLivingBase.class, this.getEntityBoundingBox().grow(1));
//		for(EntityLivingBase entity : entities) {
//			if(entity!=thrower &&player!=null&&entity!=player&& !(entity instanceof EntityStandBase)) {
//					entity.setFire(3);
//					entity.attackEntityFrom(new DamageFivePower(player), 15f);
//					if(NBTHelper.getEntityBoolean(entity, "huajiage.de")) {
//						NBTHelper.setEntityBoolean(entity, "huajiage.de", false);
//						entity.attackEntityFrom(new DamageFivePower(player), 50f);
//						if(entity instanceof EntityDragon) {
//							((EntityDragon)entity).attackEntityFromPart(((EntityDragon)entity).dragonPartHead, new DamageFivePower(player), 60f);
//						}
//						player.heal(2f);
//						for(int i=0;i<3;i++) {
//							world.spawnEntity(new EntityLightningBolt(world, entity.posX, entity.posY, entity.posZ, true));
//						}
//					}
//			}
//		}
		if(getLife()<180) {
				setLife(getLife()+1f);
		}else {
			this.setDead();
		}

//		world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX, posY, posZ, (Math.random()-0.5)/10, (Math.random()-0.5)/10, (Math.random()-0.5)/10);
		
	}
	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.typeOfHit == RayTraceResult.Type.ENTITY) {
			boolean isThrower = result.entityHit.getUniqueID().toString().equals(getMatser());
			if(result.entityHit instanceof EntityLivingBase && !isThrower) {
				if(!this.world.isRemote) {
					String type = getCommand();
					if (type!=null) {
						switch (type){
							case "null":
								break;
							case "explosion":
								result.entityHit.world.createExplosion(null,this.posX,this.posY,this.posZ,1f,false);
								result.entityHit.attackEntityFrom(DamageSource.OUT_OF_WORLD,10f);
								break;
							case "move_up":
								result.entityHit.motionY = 3f;
								break;
							case "self_attack":
								result.entityHit.attackEntityFrom(new DamageSelfAttack(), ((EntityLivingBase) result.entityHit).getMaxHealth()/3);
								break;
						}
						this.setDead();
					}
				}
				this.playSound(SoundEvents.UI_BUTTON_CLICK, 1.0F, 1f);
				}
			}
		if(result.typeOfHit ==RayTraceResult.Type.BLOCK)
		{
			IBlockState state =world.getBlockState(result.getBlockPos());
			if(state.getCollisionBoundingBox(world, result.getBlockPos()) != null)
			{
				ItemStack stack = new ItemStack(ItemLoader.discCommand);
				EntityPlayer player = this.world.getPlayerEntityByUUID(UUID.fromString(getMatser()));
				ItemDiscCommand.setCommandType(stack,getCommand());
				if (player!=null) {
					ItemDiscCommand.setOwner(stack,player.getName(),getMatser());
				}
				if (!this.world.isRemote) {
					this.world.spawnEntity(new EntityItem(this.world,this.posX,this.posY+0.5f,this.posZ,stack));
				}
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

	public String getCommand() {
		return dataManager.get(COMMAND);
	}

	public void setCommand(String command) {
		dataManager.set(COMMAND, command);
	}
	
	public String getMatser() {
		return dataManager.get(MASTER);
	}
	
	public void setMaster(String uuid) {
		dataManager.set(MASTER, uuid);
	}

	
}
