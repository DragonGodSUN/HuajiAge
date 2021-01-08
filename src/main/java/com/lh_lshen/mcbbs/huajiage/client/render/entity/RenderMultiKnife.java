package com.lh_lshen.mcbbs.huajiage.client.render.entity;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.render.model.ModelMuliKnife;
import com.lh_lshen.mcbbs.huajiage.entity.EntityMultiKnife;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMultiKnife extends Render<EntityMultiKnife>
{

	private static final ModelMuliKnife MODEL_N = new ModelMuliKnife();
	private static final ResourceLocation MULI_KNIFE = new ResourceLocation(HuajiAge.MODID, "textures/entity/entity_multi_knife.png");
	private static final ResourceLocation MULI_KNIFE_LIGHT = new ResourceLocation(HuajiAge.MODID, "textures/entity/entity_multi_knife_shiny.png");
	
	public RenderMultiKnife(RenderManager renderManager) {
		super(renderManager);
	}

	@Override
	    public void doRender(EntityMultiKnife entity, double x, double y, double z, float entityYaw, float partialTicks)
	    {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.disableLighting();
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(entity.getRotation(), 0F, 1F, 0F);
		GlStateManager.rotate(90F, 1F, 0F, 0F);
		GlStateManager.rotate(entity.getPitch(), 1F, 0F, 0F);

		if(entity.getLight()) {
			Minecraft.getMinecraft().renderEngine.bindTexture(MULI_KNIFE_LIGHT);
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
			}
		else {
		Minecraft.getMinecraft().renderEngine.bindTexture(MULI_KNIFE);
		}
		GlStateManager.scale(1F, -1F, -1F);
		MODEL_N.render(entity, partialTicks, partialTicks, partialTicks, partialTicks, partialTicks, 0.03f);
		GlStateManager.color(1F, 1F, 1F);
		GlStateManager.enableLighting();
		GlStateManager.scale(1F, -1F, -1F);
		GlStateManager.enableRescaleNormal();
		GlStateManager.popMatrix();

	    }
	@Override
	protected ResourceLocation getEntityTexture(EntityMultiKnife entity) {
		return MULI_KNIFE;
	}


}
