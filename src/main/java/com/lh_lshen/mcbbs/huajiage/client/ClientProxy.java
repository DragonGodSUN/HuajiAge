package com.lh_lshen.mcbbs.huajiage.client;

import com.lh_lshen.mcbbs.huajiage.common.CommonProxy;
import com.lh_lshen.mcbbs.huajiage.item.ItemRoadRoller;
import com.lh_lshen.mcbbs.huajiage.item.ItemSecondFoilEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	 @Override
	    public void preInit(FMLPreInitializationEvent event)
	    {
	        super.preInit(event);
	        new ItemRenderLoader();
	        new EntityRenderLoader();
	        

	       
	    }

	    @Override
	    public void init(FMLInitializationEvent event)
	    {
	        super.init(event);
	        new KeyLoader();
	        new LayerRenderLoader();
	       
	    }

	    @Override
	    public void postInit(FMLPostInitializationEvent event)
	    {
	        super.postInit(event);
	    }
	    

}
