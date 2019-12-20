package com.lh_lshen.mcbbs.huajiage.entity;

import java.util.ArrayList;

import javax.annotation.Nullable;

import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.explosion.HuajiExplosion;
import com.lh_lshen.mcbbs.huajiage.explosion.HuajiExplosionCreator;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityHeroArrow extends EntityThrowable {

	public EntityHeroArrow(World worldIn) {
		super(worldIn);
		this.isImmuneToFire();
		this.spawnRunningParticles();
		
	}
	public EntityHeroArrow(World worldIn, EntityLivingBase throwerIn)
	    {
	        super(worldIn, throwerIn);
	    }
	 public float getBrightness()
	    {
	        return 1.0F;
	    }

	    @SideOnly(Side.CLIENT)
	    public int getBrightnessForRender()
	    {
	        return 15728880;
	    }
	  
	@Override
	public void onEntityUpdate() {
	if(world.isRemote) {
		double r=(Math.random()-0.5)*0.5;
		for(int i=0;i<5;i++) {
			this.world.spawnParticle(EnumParticleTypes.LAVA,false,posX+r,posY,posZ+r,0,0,0.01);	
			}
			this.world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK,true,posX,posY,posZ,0,0,0.03);	
		}
	}
	@Override
	protected void onImpact(RayTraceResult result)
    {
		if(!world.isRemote ) {
        world.createExplosion(null, posX, posY, posZ, 50f, ConfigHuaji.Huaji.heroExplode);
        this.setDead();
		  }
		}
	
	    
	}
        
      

    
	

