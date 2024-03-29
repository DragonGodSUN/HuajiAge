package com.lh_lshen.mcbbs.huajiage.block;

import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class OreHuaji extends Block {
	public OreHuaji()
    {
        super(Material.ROCK);
        this.setHardness(15F);
        this.setHarvestLevel("pickaxe", 3);
        this.setLightLevel(1.0f);
        this.setSoundType(blockSoundType.STONE);
        this.setCreativeTab(CreativeTabLoader.tabhuaji);
    }
	@Override
	   public Item getItemDropped(IBlockState state, Random rand, int fortune)
	    {
			return ItemLoader.huajiFragmnet;
		   
	    }
	 @Override
	    public int getExpDrop(IBlockState state, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
	        Random rand = world instanceof World ? ((World)world).rand : new Random();
	        return MathHelper.getInt(rand, 3, 7) * fortune;
	    }
	 @Override
	    public int quantityDropped(IBlockState state, int fortune, Random random) {
	        return 1 + random.nextInt(2 + fortune*3 );
	    }
}
