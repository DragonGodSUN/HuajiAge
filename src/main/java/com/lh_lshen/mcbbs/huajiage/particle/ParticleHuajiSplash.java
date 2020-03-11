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

public class ParticleHuajiSplash extends Particle
{
	public static final ParticleHuajiSplash.Factory FACTORY = new ParticleHuajiSplash.Factory();
    private static final ResourceLocation TEXTURE = new ResourceLocation(HuajiAge.MODID, "textures/particle/huaji.png");
    float particleScaleOverTime;

    public ParticleHuajiSplash(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
			double ySpeedIn, double zSpeedIn) {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
        this.motionX *= 0.30000001192092896D;
        this.motionY = Math.random() * 0.20000000298023224D + 0.10000000149011612D;
        this.motionZ *= 0.30000001192092896D;
        this.particleRed = 1.0F;
        this.particleGreen = 1.0F;
        this.particleBlue = 1.0F;
//        this.setParticleTextureIndex(19);
        this.setSize(0.01F, 0.01F);
        this.particleMaxAge = (int)(8.0D / (Math.random() * 0.8D + 0.2D));
        this.particleGravity = 0.0F;
        this.motionX = xSpeedIn;
        this.motionY = ySpeedIn;
        this.motionZ = zSpeedIn;
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
      GlStateManager.color(1.0f, 1.0f, 1.0f, 0.5f);
      GlStateManager.disableBlend();
      GlStateManager.enableLighting();
      GlStateManager.popMatrix();
  }

    public void onUpdate()
    {
    	 this.prevPosX = this.posX;
         this.prevPosY = this.posY;
         this.prevPosZ = this.posZ;
         this.motionY -= (double)this.particleGravity;
         this.move(this.motionX, this.motionY, this.motionZ);
         this.motionX *= 0.9800000190734863D;
         this.motionY *= 0.9800000190734863D;
         this.motionZ *= 0.9800000190734863D;
         int i = 60 - this.particleMaxAge;
         float f = (float)i * 0.001F;
         this.setSize(f, f);
//         this.setParticleTextureIndex(19 + i % 4);

         if (this.particleMaxAge-- <= 0)
         {
             this.setExpired();
         }
    }


    @SideOnly(Side.CLIENT)
    public static class Factory implements IParticleFactory
        {
            public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_)
            {
                return new ParticleHuajiSplash(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
            }
        }

}
