package com.lh_lshen.mcbbs.huajiage.client;

import com.lh_lshen.mcbbs.huajiage.init.loaders.EntityLoader;

public class EntityRenderLoader {
	 public EntityRenderLoader()
	    {
	        EntityLoader.registerRenders();
//	        try
//	            {
//	        	if(Launch.classLoader.getClassBytes("net.minecraft.world.World") != null) {
//	                Field field = RenderManager.class.getDeclaredField("playerRenderer");
//	                field.setAccessible(true);
//	                field.set(FMLClientHandler.instance().getClient().getRenderManager(), new RenderPlayer(FMLClientHandler.instance().getClient().getRenderManager()));
//	               
//	            }
//	        	}
//	            catch (Exception e)
//	            {
//	                e.printStackTrace();
//	            }
	        }
	    
}
