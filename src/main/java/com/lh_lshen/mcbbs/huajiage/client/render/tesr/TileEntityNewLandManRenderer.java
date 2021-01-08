package com.lh_lshen.mcbbs.huajiage.client.render.tesr;


import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.render.model.ModelNewLandMan;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityNewLandMan;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

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
//            GlStateManager.disableLighting();
//            GlStateManager.pushMatrix();
//            GlStateManager.enableRescaleNormal();
//            GlStateManager.shadeModel(GL11.GL_SMOOTH);
//            GlStateManager.enableBlend();
//            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
//                    GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
//            GlStateManager.alphaFunc(GL11.GL_GREATER, 0f);
//            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
//            GlStateManager.translate(x, y+1, z);
//            GlStateManager.rotate(-Minecraft.getMinecraft().getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
//            GlStateManager.rotate((float) (Minecraft.getMinecraft().getRenderManager().options.thirdPersonView == 2 ? -1 : 1)
//                            * Minecraft.getMinecraft().getRenderManager().playerViewX,1.0F, 0.0F, 0.0F);
//            GlStateManager.rotate(180F, 0.0F, 1.0F, 0.0F);
//            Tessellator tessellator = Tessellator.getInstance();
//            BufferBuilder bufBuilder = tessellator.getBuffer();
//
//            bufBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
//            Minecraft.getMinecraft().renderEngine.bindTexture(StandUtil.getDiscTex(StandLoader.ORGA_REQUIEM));
//
//            bufBuilder.pos(-1, 1, 0).tex((0 + 0) /16 , (0 + 0) /16).endVertex();
//            bufBuilder.pos(-1, -1, 0).tex((0 + 0) /16 , (0 + 16) /16).endVertex();
//            bufBuilder.pos(1, -1, 0).tex((0 + 16) /16 , (0 + 16) /16).endVertex();
//            bufBuilder.pos(1, 1, 0).tex((0 + 16) /16 , (0 + 0) /16).endVertex();
//            tessellator.draw();

//            MODEL.render(null, 0, 0, 0, 0, 0, 0.0625f);

//            GlStateManager.disableBlend();
//            GlStateManager.shadeModel(GL11.GL_FLAT);
//            GlStateManager.disableRescaleNormal();
//            GlStateManager.popMatrix();
//            GlStateManager.enableLighting();
        }
    }

    @Override
    public boolean isGlobalRenderer(TileEntityNewLandMan te) {
        return false;
    }
}
