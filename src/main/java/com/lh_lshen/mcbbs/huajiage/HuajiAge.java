package com.lh_lshen.mcbbs.huajiage;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lh_lshen.mcbbs.huajiage.api.HuajiAgeAPI;
import com.lh_lshen.mcbbs.huajiage.api.HuajiAgeAPIImpl;
import com.lh_lshen.mcbbs.huajiage.client.ClientProxy;
import com.lh_lshen.mcbbs.huajiage.common.CommonProxy;

import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = HuajiAge.MODID, name = HuajiAge.NAME, version = HuajiAge.VERSION)
public class HuajiAge
{
    @SidedProxy(clientSide = "com.lh_lshen.mcbbs.huajiage.client.ClientProxy", 
            serverSide = "com.lh_lshen.mcbbs.huajiage.common.CommonProxy")
    public static CommonProxy proxy;
    public static final String MODID = "huajiage";
    public static final String NAME = "HUAJI Age";
    public static final String VERSION = "@VERSION@";
 
    @Instance(HuajiAge.MODID)
    public static HuajiAge instance;
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public HuajiAge() {
    		HuajiAgeAPI.setInstance(new HuajiAgeAPIImpl());
	}
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        proxy.preInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
