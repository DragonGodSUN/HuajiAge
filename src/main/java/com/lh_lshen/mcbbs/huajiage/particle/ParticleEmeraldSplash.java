package com.lh_lshen.mcbbs.huajiage.particle;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.IParticleFactory;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleSplash;
import net.minecraft.client.particle.ParticleWaterWake;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ParticleEmeraldSplash extends ParticleWaterWake {
	public static final ParticleEmeraldSplash.Factory FACTORY = new ParticleEmeraldSplash.Factory();
//    private static final ResourceLocation TEXTURE = new ResourceLocation(HuajiAge.MODID, "textures/particle/");
	public ParticleEmeraldSplash(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
			double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		this.particleRed = 0.62f;
		this.particleGreen = 1.0f;
		this.particleBlue = 0.62f;
		
	}
	 public int getBrightnessForRender(float partialTick)
	    {
	        int i = super.getBrightnessForRender(partialTick);
	        int j = 240;
	        int k = i >> 16 & 255;
	        return 240 | k << 16;
	    }
//	@Override
//	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX,
//			float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
//		GlStateManager.pushMatrix();
//		super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
//
//		GlStateManager.disableBlend();
//		GlStateManager.popMatrix();
//	}
	
	 @SideOnly(Side.CLIENT)
	    public static class Factory implements IParticleFactory
	        {
	            public Particle createParticle(int particleID, World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_)
	            {
	                return new ParticleEmeraldSplash(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
	            }
	        }

}
