package com.lh_lshen.mcbbs.huajiage.particle;

import org.lwjgl.opengl.GL11;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleHeart;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ParticleHuaji extends Particle
{
	public static final ParticleHuaji.Factory FACTORY = new ParticleHuaji.Factory();
    private static final ResourceLocation TEXTURE = new ResourceLocation(HuajiAge.MODID, "textures/particle/huaji.png");
    float particleScaleOverTime;

    protected ParticleHuaji(World worldIn, double p_i1211_2_, double p_i1211_4_, double p_i1211_6_, double p_i1211_8_, double p_i1211_10_, double p_i1211_12_)
    {
        this(worldIn, p_i1211_2_, p_i1211_4_, p_i1211_6_, p_i1211_8_, p_i1211_10_, p_i1211_12_, 2.0F);
    }

    protected ParticleHuaji(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double p_i46354_8_, double p_i46354_10_, double p_i46354_12_, float scale)
    {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
        this.motionX *= 0.009999999776482582D;
        this.motionY *= 0.009999999776482582D;
        this.motionZ *= 0.009999999776482582D;
        this.motionY += 0.1D;
        this.particleScale *= 0.75F;
        this.particleScale *= scale;
        this.particleScaleOverTime = this.particleScale;
        this.particleMaxAge = 16;
//        Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
    }
    @Override
    public int getFXLayer()
    {
      return 3;
    }
    /**
     * Renders the particle
     */
  @Override
  public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
  {
	  GlStateManager.pushMatrix();
      GlStateManager.disableLighting();
      Minecraft.getMinecraft().getTextureManager().bindTexture(TEXTURE);
      GlStateManager.enableBlend();
      GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);

      buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
      float x = (float) (this.prevPosX + (this.posX - this.prevPosX) * (double) partialTicks - interpPosX);
      float y = (float) (this.prevPosY + (this.posY - this.prevPosY) * (double) partialTicks - interpPosY);
      float z = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * (double) partialTicks - interpPosZ);
      buffer.pos((double) (x - rotationX * 0.1F - rotationXY * 0.1F), (double) (y - rotationZ * 0.1F), (double) (z - rotationYZ * 0.1F - rotationXZ * 0.1F)).tex(1, 1).endVertex();
      buffer.pos((double) (x - rotationX * 0.1F + rotationXY * 0.1F), (double) (y + rotationZ * 0.1F), (double) (z - rotationYZ * 0.1F + rotationXZ * 0.1F)).tex(1, 0).endVertex();
      buffer.pos((double) (x + rotationX * 0.1F + rotationXY * 0.1F), (double) (y + rotationZ * 0.1F), (double) (z + rotationYZ * 0.1F + rotationXZ * 0.1F)).tex(0, 0).endVertex();
      buffer.pos((double) (x + rotationX * 0.1F - rotationXY * 0.1F), (double) (y - rotationZ * 0.1F), (double) (z + rotationYZ * 0.1F - rotationXZ * 0.1F)).tex(0, 1).endVertex();

      Tessellator.getInstance().draw();
      GlStateManager.disableBlend();
      GlStateManager.enableLighting();
      GlStateManager.popMatrix();
  }

    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if (this.particleAge++ >= this.particleMaxAge)
        {
            this.setExpired();
        }

        this.move(this.motionX, this.motionY, this.motionZ);

        if (this.posY == this.prevPosY)
        {
            this.motionX *= 1.1D;
            this.motionZ *= 1.1D;
        }

        this.motionX *= 0.8600000143051147D;
        this.motionY *= 0.8600000143051147D;
        this.motionZ *= 0.8600000143051147D;

        if (this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
    }


    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory
        {
            public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_)
            {
                return new ParticleHuaji(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            }
        }

}
