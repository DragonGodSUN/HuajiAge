package com.lh_lshen.mcbbs.huajiage.entity.render;

import static org.lwjgl.opengl.GL11.GL_ONE;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_ZERO;

import org.lwjgl.opengl.GL11;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
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
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderFireball;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class RenderFivePower extends Render<EntityFivePower> {

	public static final ResourceLocation TEX = new ResourceLocation(HuajiAge.MODID, "textures/particle/five_bullet.png");
	public RenderFivePower(RenderManager manager)
	{
		super(manager);
	}
	@Override
	public void doRender(EntityFivePower entity, double x, double y, double z, float entityYaw, float partialTicks) {
		 Vec3d playerVec3d = new Vec3d(-TileEntityRendererDispatcher.staticPlayerX,
	                -TileEntityRendererDispatcher.staticPlayerY + Minecraft.getMinecraft().player.getEyeHeight(),
	                -TileEntityRendererDispatcher.staticPlayerZ);
	        float yaw = TileEntityRendererDispatcher.instance.entityYaw;
	        float pitch = TileEntityRendererDispatcher.instance.entityPitch;
	        GlStateManager.enableBlend();
	        OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);
	        GlStateManager.disableTexture2D();
	        GlStateManager.resetColor();
            GlStateManager.pushMatrix();
            GlStateManager.translate(playerVec3d.x, playerVec3d.y, playerVec3d.z);
            GlStateManager.translate(x+ 0.5f, y - 1f, z + 0.5f);
            GlStateManager.rotate(-yaw, 0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(pitch, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(180, 0.0F, 0.0F, 1.0F);
            Minecraft.getMinecraft().getTextureManager().bindTexture(TEX);
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
            buffer.pos(0.25, 0.25, 0).tex(1, 1).endVertex();
            buffer.pos(0.25, -0.25, 0).tex(1, 0).endVertex();
            buffer.pos(-0.25, -0.25, 0).tex(0, 0).endVertex();
            buffer.pos(-0.25, 0.25, 0).tex(0, 1).endVertex();
            tessellator.draw();
            GlStateManager.popMatrix();
            GlStateManager.resetColor();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityFivePower entity) {
		return TEX;
	}
	

}
