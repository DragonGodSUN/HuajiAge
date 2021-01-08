package com.lh_lshen.mcbbs.huajiage.init.loaders;

import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabHuaji;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabJO;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabVehicle;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CreativeTabLoader {
	public static CreativeTabs tabhuaji;
	public static CreativeTabs tabJo;
    public static CreativeTabs tabVehicle;

    public CreativeTabLoader(FMLPreInitializationEvent event)
    {
        tabhuaji = new CreativeTabHuaji();
        tabJo=new CreativeTabJO();
        tabVehicle=new CreativeTabVehicle();
    }

}
