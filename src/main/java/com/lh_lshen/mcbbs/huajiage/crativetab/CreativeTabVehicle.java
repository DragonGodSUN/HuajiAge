package com.lh_lshen.mcbbs.huajiage.crativetab;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabVehicle extends CreativeTabs{
 
	public CreativeTabVehicle()
	    {
	        super(HuajiAge.MODID+"."+"vehicle");
	    }

		@Override
		public ItemStack createIcon() {
			return new ItemStack(ItemLoader.vehicleKey);
		}
	
}
