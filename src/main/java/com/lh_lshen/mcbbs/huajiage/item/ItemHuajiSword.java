package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;

import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ItemHuajiSword extends ItemSword {
 public static final Item.ToolMaterial HUAJI = EnumHelper.addToolMaterial("HUAJI", 3,1200, 16.0F, 4.0F, 20);
	public ItemHuajiSword()
	{
		 super(HUAJI);
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
	}

}
