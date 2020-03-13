package com.lh_lshen.mcbbs.huajiage.crativetab;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CreativeTabHuaji extends CreativeTabs{
 
	public CreativeTabHuaji()
	    {
	        super(HuajiAge.MODID+"."+"huaji");
	    }

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemLoader.huaji);
		}
	
}
