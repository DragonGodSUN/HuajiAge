package com.lh_lshen.mcbbs.huajiage.common;

import com.lh_lshen.mcbbs.huajiage.block.BlockLoader;
import com.lh_lshen.mcbbs.huajiage.client.KeyLoader;
import com.lh_lshen.mcbbs.huajiage.common.world.gen.OreGenEventHandler;
import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.entity.EntityLoader;
import com.lh_lshen.mcbbs.huajiage.entity.render.RenderPlayerLayers;
import com.lh_lshen.mcbbs.huajiage.entity.render.layers.LayerHeldTopItem;
import com.lh_lshen.mcbbs.huajiage.init.EventLoader;
import com.lh_lshen.mcbbs.huajiage.init.LootTablesLoader;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.inventroy.GuiHuajiBlenderElementLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemExpendedView;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemRoadRoller;
import com.lh_lshen.mcbbs.huajiage.item.ItemSecondFoilEntity;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class CommonProxy
{

    public void preInit(FMLPreInitializationEvent event)
    {  	 
    	 new CreativeTabLoader(event);
    	 new ItemLoader(event);
    	 new BlockLoader(event); 
    	 new EntityLoader();
    	 new TileEntityLoader(event);
    	 new GuiLoader(event);
    	 
    	 MinecraftForge.EVENT_BUS.register(PotionLoader.class);
    	 MinecraftForge.EVENT_BUS.register(SoundLoader.class);
    	 HuajiAgeNetWorkHandler.init();
    	 
    	 

    }

    public void init(FMLInitializationEvent event)
    {
    	
       LootTablesLoader.registerLootTables();
    	new CraftingLoader();
    	new EventLoader();
        new OreGenEventHandler();
//        for (RenderPlayer playerRender : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {
//        	new RenderHuajiPlayer(playerRender.getRenderManager()); 
//        	}
    }

    public void postInit(FMLPostInitializationEvent event)
    {

        
    }
    public World getClientWorld() {
        return null;
    }
  
}