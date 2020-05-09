package com.lh_lshen.mcbbs.huajiage.entity;

import java.util.List;
import java.util.Random;

import javax.annotation.Nonnull;

import com.lh_lshen.mcbbs.huajiage.DamageSource.DamageEmeraldSplash;
import com.lh_lshen.mcbbs.huajiage.particle.EnumHuajiPraticle;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import ibxm.Player;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
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

public class EntityFivePower extends EntityThrowable{

	public EntityFivePower(World worldIn) {
		super(worldIn);
		
	}
	public EntityFivePower(World worldIn,EntityLivingBase throwerIn) {
		super(worldIn,throwerIn);

	}
	@Override
	public void onUpdate() {
		super.onUpdate();
		double r = Math.random()-0.5;
		for(int i=0;i<5;i++) {
		world.spawnParticle(EnumParticleTypes.FLAME, posX+r, posY+r, posZ+r, (Math.random()-0.5)/10, (Math.random()-0.5)/10, (Math.random()-0.5)/10);
		}
	}
	@Override
	public boolean hasNoGravity() {
		return true;
	}
	@Override
	protected void onImpact(RayTraceResult result) {
		if(result.typeOfHit == RayTraceResult.Type.ENTITY) {
			if(result.entityHit!=null&&result.entityHit!=thrower) {
				if(!this.world.isRemote) {
				if(!(result.entityHit instanceof EntityFivePower)) {
					result.entityHit.attackEntityFrom(new DamageEmeraldSplash(this, getThrower()), 10f);
					this.setDead();
					this.world.createExplosion(this,posX, posY, posZ, 1f, false);
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
	

}
