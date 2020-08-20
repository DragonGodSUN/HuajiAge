package com.lh_lshen.mcbbs.huajiage.crativetab;

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
