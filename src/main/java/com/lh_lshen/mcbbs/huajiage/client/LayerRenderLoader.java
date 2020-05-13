package com.lh_lshen.mcbbs.huajiage.client;

import com.lh_lshen.mcbbs.huajiage.client.render.entity.RenderPlayerLayers;
import com.lh_lshen.mcbbs.huajiage.client.render.layers.LayerCharmDisplay;
import com.lh_lshen.mcbbs.huajiage.client.render.layers.LayerLordPower;
import com.lh_lshen.mcbbs.huajiage.client.render.layers.LayerStand;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;

public class LayerRenderLoader {
	 public LayerRenderLoader()
	    {
		 for (RenderPlayer playerRender : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {
			 	playerRender.addLayer(new LayerCharmDisplay());
	        	playerRender.addLayer(new LayerStand(playerRender));  
	        	playerRender.addLayer(new LayerLordPower(playerRender));
	        	}

	    }
	}
