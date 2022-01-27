package com.lh_lshen.mcbbs.huajiage.client.render.entity;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.render.model.ModelEmeraldBullet;
import com.lh_lshen.mcbbs.huajiage.entity.EntityDiscCommand;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class RenderDiscCommand extends Render<EntityDiscCommand> {

	public static final ResourceLocation TEX_0 = new ResourceLocation(HuajiAge.MODID, "textures/items/disc/disc_null.png");
	public RenderDiscCommand(RenderManager manager)
	{
		super(manager);
	}
	private static final ModelEmeraldBullet MODEL_N = new ModelEmeraldBullet();
	@Override
	public void doRender(EntityDiscCommand entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
        int w = 64;
        int l = 64;

        double pStartU = 0;
        double pStartV = 0;
        GlStateManager.disableLighting();
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.shadeModel(GL11.GL_SMOOTH);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
                GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0f);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);

        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate((float) (this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * this.renderManager.playerViewX,
                1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(180F, 0.0F, 1.0F, 0.0F);

        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder bufBuilder = tessellator.getBuffer();

        bufBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
//        if(entity.isDe()) {
//        this.renderManager.renderEngine.bindTexture(TEX_1);
//        }else {
    	this.renderManager.renderEngine.bindTexture(TEX_0);
//        }

        bufBuilder.pos(-0.5, 0.5, 0).tex((pStartU + 0) / w, (pStartV + 0) / l).endVertex();
        bufBuilder.pos(-0.5, -0.5, 0).tex((pStartU + 0) / w, (pStartV + 64) / l).endVertex();
        bufBuilder.pos(0.5, -0.5, 0).tex((pStartU + 64) / w, (pStartV + 64) / l).endVertex();
        bufBuilder.pos(0.5, 0.5, 0).tex((pStartU + 64) / w, (pStartV + 0) / l).endVertex();
        tessellator.draw();

        GlStateManager.disableBlend();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.enableLighting();
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityDiscCommand entity) {
		return null;
	}
	

}
