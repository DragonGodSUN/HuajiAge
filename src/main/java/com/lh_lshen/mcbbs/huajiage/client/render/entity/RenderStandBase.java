package com.lh_lshen.mcbbs.huajiage.client.render.entity;

import com.lh_lshen.mcbbs.huajiage.stand.entity.EntityStandBase;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderStandBase extends Render<EntityStandBase> {
	public RenderStandBase(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityStandBase entity) {
		return null;
	}
}
