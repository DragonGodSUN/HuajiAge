package com.lh_lshen.mcbbs.huajiage.stand.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityLoader;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.GlStateManager.FogMode;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.EntityViewRenderEvent.FOVModifier;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogColors;
import net.minecraftforge.client.event.EntityViewRenderEvent.FogDensity;
import net.minecraftforge.client.event.EntityViewRenderEvent.RenderFogEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = HuajiAge.MODID, value = Side.CLIENT)
public class EventViewRender {
	@SubscribeEvent
	public static void TimeStopRender1(FogColors evt) {
	if(evt.getEntity() instanceof EntityLivingBase) {
			if(StandUtil.getStandBuffTime((EntityLivingBase) evt.getEntity())>0) {
		    	evt.setRed(85);
		    	evt.setGreen(85);
		    	evt.setBlue(85);
	    	}
		}
	}
    
	@SubscribeEvent
	public static void TimeStopRender2(RenderFogEvent evt) {
	if(evt.getEntity() instanceof EntityLivingBase) {
		if(StandUtil.getStandBuffTime((EntityLivingBase) evt.getEntity())>0) {
				GlStateManager.disableFog();
				GlStateManager.setFog(FogMode.EXP);
//				GlStateManager.color(0.5f, 0.5f, 0.5f);
//				GlStateManager.
				GlStateManager.setFogDensity(0.05f);
				GlStateManager.enableFog();
			}
		}
	}
	
	@SubscribeEvent
	public static void TimeStopRender3(FOVModifier evt) {
		if(evt.getEntity() instanceof EntityLivingBase) {
				if(StandUtil.getStandBuffTime((EntityLivingBase) evt.getEntity())>0) {
				evt.setFOV(evt.getFOV()+25f);
	    		}
		}
	}
	 @SubscribeEvent
	    public static void TimeStopRender4(RenderGameOverlayEvent event) {
		 	Minecraft mc = Minecraft.getMinecraft();
		 	boolean flag =  StandUtil.getStandBuffTime(mc.player)>0 ;
	        if (event.getType() == RenderGameOverlayEvent.ElementType.VIGNETTE && flag ) {
	        	ScaledResolution scaledRes = new ScaledResolution(mc);
    			GlStateManager.enableBlend();
    			GlStateManager.enableDepth();;
    			GlStateManager.depthMask(false);
    			 GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	             GlStateManager.color(0.25F, 0.25F, 0.25F, 1.0F);
//	             GlStateManager.disableAlpha();
	             mc.getTextureManager().bindTexture(new ResourceLocation(HuajiAge.MODID+":"+"textures/misc/time_stop_view.png"));
	             Tessellator tessellator = Tessellator.getInstance();
	             BufferBuilder bufferbuilder = tessellator.getBuffer();
	             bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
	             bufferbuilder.pos(0.0D, (double)scaledRes.getScaledHeight(), -90.0D).tex(0.0D, 1.0D).endVertex();
	             bufferbuilder.pos((double)scaledRes.getScaledWidth(), (double)scaledRes.getScaledHeight(), -90.0D).tex(1.0D, 1.0D).endVertex();
	             bufferbuilder.pos((double)scaledRes.getScaledWidth(), 0.0D, -90.0D).tex(1.0D, 0.0D).endVertex();
	             bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).endVertex();
	             tessellator.draw();
	             GlStateManager.depthMask(true);
//	             GlStateManager.enableDepth();
//	             GlStateManager.enableAlpha();
	             GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	             GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	        }
        }
	
}
