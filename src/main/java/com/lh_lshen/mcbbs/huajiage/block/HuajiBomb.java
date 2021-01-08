package com.lh_lshen.mcbbs.huajiage.block;

import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;

import net.minecraft.block.BlockTNT;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
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
	
	
//	@Override
//	public void onBlockExploded(World world, BlockPos pos, Explosion explosion) {
//		super.onBlockExploded(world, pos, explosion);
//		if (!world.isRemote)
//        {
//            EntityFireworkRocket entity = new EntityFireworkRocket(world);
//            entity.setFire(1);
//            world.spawnEntity(entity);
//        }
//	}
}
