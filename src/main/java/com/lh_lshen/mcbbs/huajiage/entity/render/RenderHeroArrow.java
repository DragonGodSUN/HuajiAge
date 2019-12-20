package com.lh_lshen.mcbbs.huajiage.entity.render;

import com.lh_lshen.mcbbs.huajiage.entity.EntityHeroArrow;
import com.lh_lshen.mcbbs.huajiage.entity.EntitySecondFoil;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemSecondFoil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

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
