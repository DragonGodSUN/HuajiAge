package com.lh_lshen.mcbbs.huajiage.block;

import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class HuajiStarBlock extends Block {
	public HuajiStarBlock()
    {
        super(Material.IRON);
        this.setHardness(5F);
        this.setLightLevel(1.0f);
        this.setCreativeTab(CreativeTabLoader.tabhuaji);
        this.setSoundType(blockSoundType.GLASS);
        this.setCreativeTab(CreativeTabLoader.tabhuaji);
    }

}
