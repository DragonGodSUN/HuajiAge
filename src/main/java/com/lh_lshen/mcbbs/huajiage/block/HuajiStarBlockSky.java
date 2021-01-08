package com.lh_lshen.mcbbs.huajiage.block;

import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class HuajiStarBlockSky extends Block {
	public HuajiStarBlockSky()
    {
        super(Material.IRON);
        this.setHardness(15F);
        this.setLightLevel(1.0f);
        this.setSoundType(blockSoundType.GLASS);
        this.setCreativeTab(CreativeTabLoader.tabhuaji);
    }
	
}
