package com.lh_lshen.mcbbs.huajiage.init.loaders;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.common.GuiHandlerRegistry;
import com.lh_lshen.mcbbs.huajiage.inventroy.GuiHuajiBlenderElementLoader;
import com.lh_lshen.mcbbs.huajiage.inventroy.GuiHuajiPolyElementLoader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GuiLoader {
    public GuiLoader(FMLPreInitializationEvent event)
    {
	    NetworkRegistry.INSTANCE.registerGuiHandler(HuajiAge.instance, GuiHandlerRegistry.getInstance());
		GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHuajiBlenderElementLoader(), GuiHuajiBlenderElementLoader.getGuiID());
		GuiHandlerRegistry.getInstance().registerGuiHandler(new GuiHuajiPolyElementLoader(), GuiHuajiPolyElementLoader.getGuiID());
    
    }

        
    }
   
