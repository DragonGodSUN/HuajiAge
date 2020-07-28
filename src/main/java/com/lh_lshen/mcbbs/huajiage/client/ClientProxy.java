package com.lh_lshen.mcbbs.huajiage.client;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.HAModelFactory;
import com.lh_lshen.mcbbs.huajiage.common.CommonProxy;
import com.lh_lshen.mcbbs.huajiage.init.EventClientLoader;
import com.lh_lshen.mcbbs.huajiage.particle.ParticleLoader;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandResLoader;

import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {

	 @Override
	    public void preInit(FMLPreInitializationEvent event)
	    {
	        super.preInit(event);
	        new HAModelFactory();
	        new ItemRenderLoader();
	        new EntityRenderLoader();
	        new StandResLoader();
	        OBJLoader.INSTANCE.addDomain(HuajiAge.MODID);
	        

	       
	    }

	    @Override
	    public void init(FMLInitializationEvent event)
	    {
	        super.init(event);
	        new EventClientLoader();
	        new ParticleLoader();
	        new KeyLoader();
	        new LayerRenderLoader();
	       
	    }

	    @Override
	    public void postInit(FMLPostInitializationEvent event)
	    {
	        super.postInit(event);
	    }
	    

}
