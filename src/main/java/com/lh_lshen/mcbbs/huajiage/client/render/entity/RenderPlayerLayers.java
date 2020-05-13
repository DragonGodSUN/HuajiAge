package com.lh_lshen.mcbbs.huajiage.client.render.entity;

import com.lh_lshen.mcbbs.huajiage.client.render.layers.LayerCharmDisplay;
import com.lh_lshen.mcbbs.huajiage.client.render.layers.LayerHeldTopItem;
import com.lh_lshen.mcbbs.huajiage.client.render.layers.LayerLordPower;
import com.lh_lshen.mcbbs.huajiage.client.render.layers.LayerStand;
import com.lh_lshen.mcbbs.huajiage.item.ItemRoadRoller;
import com.lh_lshen.mcbbs.huajiage.item.ItemSecondFoilEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.item.ItemStack;

public class RenderPlayerLayers extends net.minecraft.client.renderer.entity.RenderPlayer{

	public RenderPlayerLayers(RenderManager renderManager) {
		super(renderManager);
		this.addLayers();
	}
	 
    private void addLayers()
    {
    	for (RenderPlayer playerRender : Minecraft.getMinecraft().getRenderManager().getSkinMap().values()) {
		        	playerRender.addLayer(new LayerCharmDisplay());
//		        	playerRender.addLayer(new LayerHeldTopItem(playerRender));
		        	playerRender.addLayer(new LayerStand(playerRender));  
		        	playerRender.addLayer(new LayerLordPower(playerRender));
    	}

    }

}
