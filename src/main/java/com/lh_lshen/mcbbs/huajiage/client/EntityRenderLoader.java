package com.lh_lshen.mcbbs.huajiage.client;

import java.lang.reflect.Field;
import java.util.Map;

import com.lh_lshen.mcbbs.huajiage.entity.EntityLoader;

import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.fml.client.FMLClientHandler;

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
