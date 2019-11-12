package com.lh_lshen.mcbbs.huajiage.common;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.inventroy.GuiHuajiBlenderElementLoader;
import com.lh_lshen.mcbbs.huajiage.inventroy.GuiHuajiPolyElementLoader;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;

public class GuiLoader {
    public GuiLoader(FMLPreInitializationEvent event)
    {
	    NetworkRegistry.INSTANCE.registerGuiHandler(HuajiAge.instance, GuiHandlerRegistry.getInstance());
		GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHuajiBlenderElementLoader(), GuiHuajiBlenderElementLoader.getGuiID());
		GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHuajiPolyElementLoader(), GuiHuajiPolyElementLoader.getGuiID());
    
    }

        
    }
   
