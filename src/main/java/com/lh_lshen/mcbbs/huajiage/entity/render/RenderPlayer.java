package com.lh_lshen.mcbbs.huajiage.entity.render;

import com.lh_lshen.mcbbs.huajiage.client.LayerHeldTopItem;
import com.lh_lshen.mcbbs.huajiage.item.ItemExpendedView;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemSecondFoilEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RenderPlayer extends net.minecraft.client.renderer.entity.RenderPlayer{

	public RenderPlayer(RenderManager renderManager) {
		super(renderManager);
		this.addLayers();
	}
	 
    private void addLayers()
    {
    	ItemStack itemR=Minecraft.getMinecraft().player.getHeldItemMainhand();
    	ItemStack itemL=Minecraft.getMinecraft().player.getHeldItemOffhand();
        if (itemR.getItem() instanceof ItemSecondFoilEntity||itemL.getItem() instanceof ItemSecondFoilEntity)
        {
        	this.addLayer(new LayerHeldTopItem(this));     
        }

        



    }

}
