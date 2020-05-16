package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ItemWaveKnife extends ItemSword {
 public static final Item.ToolMaterial WAVE = EnumHelper.addToolMaterial("WAVE", 3,600, 16.0F, 4.0F, 20);
	public ItemWaveKnife()
	{
		 super(WAVE);
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
	}
	
	

}
