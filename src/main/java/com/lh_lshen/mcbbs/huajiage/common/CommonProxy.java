package com.lh_lshen.mcbbs.huajiage.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lh_lshen.mcbbs.huajiage.block.BlockLoader;
import com.lh_lshen.mcbbs.huajiage.block.mutiblock.MutiBlockLoader;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityLoader;
import com.lh_lshen.mcbbs.huajiage.common.world.gen.OreGenEventHandler;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.entity.EntityLoader;
import com.lh_lshen.mcbbs.huajiage.init.EventLoader;
import com.lh_lshen.mcbbs.huajiage.init.LootNBTFuntion;
import com.lh_lshen.mcbbs.huajiage.init.LootTablesLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.StandNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityLoader;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.functions.LootFunctionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class CommonProxy
{
	public static final Gson GSON = new GsonBuilder().registerTypeAdapter(ResourceLocation.class, new ResourceLocation.Serializer()).create();
	public static final ScriptEngine NASHORN_ENGINE = new ScriptEngineManager().getEngineByName("nashorn");

    public void preInit(FMLPreInitializationEvent event)
    {  	 
    	 new CreativeTabLoader(event);
    	 new StandLoader();
    	 new ItemLoader(event);
    	 new BlockLoader(event);
    	 new EntityLoader();
    	 new TileEntityLoader(event);
    	 new GuiLoader(event);
    	 new CapabilityLoader(event);

    	 MinecraftForge.EVENT_BUS.register(PotionLoader.class);
    	 MinecraftForge.EVENT_BUS.register(SoundLoader.class);
    	 HuajiAgeNetWorkHandler.init();
    	 StandNetWorkHandler.init();
    	
    	 

    }

    public void init(FMLInitializationEvent event)
    {
    	LootTablesLoader.registerLootTables();
       	LootFunctionManager.registerFunction(new LootNBTFuntion.Serializer());
	   	new MutiBlockLoader();
		new CraftingLoader();
		new EventLoader();
	    new OreGenEventHandler();
//	    StandLoader.reloadStands();



//        for (RenderPlayer playerRender : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {
//        	new RenderHuajiPlayer(playerRender.getRenderManager()); 
//        	}
    }

    public void postInit(FMLPostInitializationEvent event)
    {

    }
//    public World getClientWorld() {
//        return null;
//    }

	public static class ModsLoader{
		public static boolean isTouhouMaidLoaded(){
			return Loader.isModLoaded("touhou_little_maid");
		}

	}

  
}