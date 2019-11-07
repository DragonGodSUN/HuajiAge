package com.lh_lshen.mcbbs.huajiage.block;

import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTNT;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class HuajiBomb extends BlockTNT {

	public HuajiBomb() {
		super();
		this.setSoundType(blockSoundType.SAND);
		this.setCreativeTab(CreativeTabLoader.tabhuaji);
	}
	@Override
	public void explode(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase igniter)
    {
        if (!worldIn.isRemote)
        {
            if (((Boolean)state.getValue(EXPLODE)).booleanValue())
            {
              
               worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 10f, true);
            }
        }
    } 
	@Override
	public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
    {
        if (!worldIn.isRemote)
        {
            EntityFireworkRocket entity = new EntityFireworkRocket(worldIn);
            entity.setFire(1);
            worldIn.spawnEntity(entity);
        }
    }
}
