package com.lh_lshen.mcbbs.huajiage.client.render.tesr;

import java.util.concurrent.ExecutionException;

import org.lwjgl.opengl.GL11;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.render.model.ModelNewLandMan;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityNewLandMan;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.ResourceLocation;

public class TileEntityNewLandManRenderer extends TileEntitySpecialRenderer<TileEntityNewLandMan> {
    private static final ModelBase MODEL = new ModelNewLandMan();
    private static final ResourceLocation TEXTURE = new ResourceLocation(HuajiAge.MODID, "textures/blocks/new_land_man.png");

    @Override
    public void render(TileEntityNewLandMan te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        if (te.isRender()) {
            this.bindTexture(TEXTURE);
            GlStateManager.pushMatrix();
            GlStateManager.disableCull();
            GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            final boolean lightmapEnabled = GL11.glGetBoolean(GL11.GL_TEXTURE_2D);
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
            GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            GlStateManager.translate(x+0.5, y +1.5, z+0.5 );
            GlStateManager.rotate(180, 0, 0, 1);
            MODEL.render(null, 0, 0, 0, 0, 0, 0.0625f);
            GlStateManager.disableBlend();
            GlStateManager.enableCull();
            GlStateManager.popMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            if (lightmapEnabled) {
                GlStateManager.enableTexture2D();
            } else {
                GlStateManager.disableTexture2D();
            }
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        }
    }

    @Override
    public boolean isGlobalRenderer(TileEntityNewLandMan te) {
        return true;
    }
}
