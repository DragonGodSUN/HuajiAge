package com.lh_lshen.mcbbs.huajiage.client.render.entity;

import com.lh_lshen.mcbbs.huajiage.entity.EntityHeroArrow;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class RenderHeroArrow extends RenderSnowball<EntityHeroArrow> {
	public RenderHeroArrow(RenderManager manager)
	{
		super(manager,ItemLoader.huajiStarPoly, Minecraft.getMinecraft().getRenderItem());
	}
//	@Override
//	public void doRender(EntityHeroArrow entity, double x, double y, double z, float entityYaw, float partialTicks) {
//		super.doRender(entity, x, y, z, entityYaw, partialTicks);
//		GlStateManager.disableLighting();
//		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
//		GlStateManager.enableLighting();
//	}
}
