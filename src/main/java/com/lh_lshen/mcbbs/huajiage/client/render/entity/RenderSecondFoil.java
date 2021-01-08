package com.lh_lshen.mcbbs.huajiage.client.render.entity;

import com.lh_lshen.mcbbs.huajiage.entity.EntitySecondFoil;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class RenderSecondFoil extends RenderSnowball<EntitySecondFoil> {


	public RenderSecondFoil(RenderManager manager)
	{
		super(manager,ItemLoader.secondFoilEntity, Minecraft.getMinecraft().getRenderItem());
	}

	

}
