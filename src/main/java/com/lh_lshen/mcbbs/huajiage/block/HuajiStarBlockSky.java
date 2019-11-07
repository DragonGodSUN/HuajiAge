package com.lh_lshen.mcbbs.huajiage.block;

import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemTool;

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
