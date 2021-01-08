package com.lh_lshen.mcbbs.huajiage.crativetab;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabJO extends CreativeTabs{
 
	public CreativeTabJO()
	    {
	        super(HuajiAge.MODID+"."+"jo");
	    }

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemLoader.tarot);
		}
	
}
