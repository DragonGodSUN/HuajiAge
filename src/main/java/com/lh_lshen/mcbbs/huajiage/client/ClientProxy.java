package com.lh_lshen.mcbbs.huajiage.client;

import com.lh_lshen.mcbbs.huajiage.common.CommonProxy;
import com.lh_lshen.mcbbs.huajiage.init.EventClientLoader;

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
	        new EventClientLoader();
	        new KeyLoader();
	        new LayerRenderLoader();
	       
	    }

	    @Override
	    public void postInit(FMLPostInitializationEvent event)
	    {
	        super.postInit(event);
	    }
	    

}
