package com.lh_lshen.mcbbs.huajiage.entity.render;

import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_ZERO;

import org.lwjgl.opengl.GL11;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.model.ModelEmeraldBullet;
import com.lh_lshen.mcbbs.huajiage.entity.EntityFivePower;
import com.lh_lshen.mcbbs.huajiage.entity.EntitySecondFoil;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemSecondFoil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class RenderFivePower extends Render<EntityFivePower> {

	public static final ResourceLocation TEX = new ResourceLocation(HuajiAge.MODID, "textures/particle/five_bullet.png");
	public RenderFivePower(RenderManager manager)
	{
		super(manager);
	}
	private static final ModelEmeraldBullet MODEL_N = new ModelEmeraldBullet();
	@Override
	public void doRender(EntityFivePower entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
        int w = 16;
        int l = 16;

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
        this.renderManager.renderEngine.bindTexture(TEX);

        bufBuilder.pos(-0.5, 0.5, 0).tex((pStartU + 0) / w, (pStartV + 0) / l).endVertex();
        bufBuilder.pos(-0.5, -0.5, 0).tex((pStartU + 0) / w, (pStartV + 16) / l).endVertex();
        bufBuilder.pos(0.5, -0.5, 0).tex((pStartU + 16) / w, (pStartV + 16) / l).endVertex();
        bufBuilder.pos(0.5, 0.5, 0).tex((pStartU + 16) / w, (pStartV + 0) / l).endVertex();
        tessellator.draw();

        GlStateManager.disableBlend();
        GlStateManager.shadeModel(GL11.GL_FLAT);
        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        GlStateManager.enableLighting();
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityFivePower entity) {
		return TEX;
	}
	

}
