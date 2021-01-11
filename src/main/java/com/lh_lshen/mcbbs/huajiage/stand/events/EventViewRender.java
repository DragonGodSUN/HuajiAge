package com.lh_lshen.mcbbs.huajiage.stand.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.EntityViewRenderEvent.FOVModifier;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = HuajiAge.MODID, value = Side.CLIENT)
public class EventViewRender {
	static int ticks = 0;

	@SubscribeEvent
	public static void TimeStopRenderTest(TickEvent.RenderTickEvent evt) {
		Minecraft mc = Minecraft.getMinecraft();
		int t0 = (int)(ConfigHuaji.Stands.timeStopEffect*100);
		boolean hasTag = mc.player!=null
				&& StandUtil.getChargeHandler(mc.player).getBuffTag().equals(HuajiConstant.BuffTags.TIME_STOP);
		boolean flag = hasTag && StandUtil.getStandBuffTime(mc.player)>0;
		if(flag){
			ticks++;
			if (ticks < t0) {
				if (!mc.entityRenderer.isShaderActive()) {
					mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/invert.json"));
				}
			}
			if (ticks == t0 && mc.entityRenderer.isShaderActive()) {
				mc.entityRenderer.stopUseShader();
			}
			if (ticks > t0 && StandUtil.getStandBuffTime(mc.player)>10) {
				if (!mc.entityRenderer.isShaderActive()) {
					mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/desaturate.json"));
				}
			}
			if (StandUtil.getStandBuffTime(mc.player)==20) {
				mc.player.playSound(SoundLoader.THE_WORLD_RE,1f,1f);
			}
			if (StandUtil.getStandBuffTime(mc.player)==10 && mc.entityRenderer.isShaderActive()) {
				mc.entityRenderer.stopUseShader();
				mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/pencil.json"));
			}

			if (StandUtil.getStandBuffTime(mc.player)<=3 && mc.entityRenderer.isShaderActive()){
				mc.entityRenderer.stopUseShader();
			}

		}else {
			ticks = 0;
		}
	}

//	@SubscribeEvent
//	public static void TimeStopRender2(EntityViewRenderEvent.RenderFogEvent evt) {
//	if(evt.getEntity() instanceof EntityLivingBase) {
//		if(ConfigHuaji.Stands.allowFogTimeStop&&StandUtil.getStandBuffTime((EntityLivingBase) evt.getEntity())>0) {
//				GlStateManager.disableFog();
//				GlStateManager.setFog(GlStateManager.FogMode.EXP);
////				GlStateManager.color(0.5f, 0.5f, 0.5f);
////				GlStateManager.
//				GlStateManager.setFogDensity(ConfigHuaji.Stands.timeStopFog);
//				GlStateManager.enableFog();
//			}
//		}
//	}
	
	@SubscribeEvent
	public static void TimeStopRender2(FOVModifier evt) {
		boolean hasTag = evt.getEntity()!=null
				&& StandUtil.getChargeHandler((EntityLivingBase) evt.getEntity()).getBuffTag().equals(HuajiConstant.BuffTags.TIME_STOP);
		if(evt.getEntity() instanceof EntityLivingBase && hasTag) {
			int time = StandUtil.getStandBuffTime((EntityLivingBase) evt.getEntity());
			int t0 = (int)(ConfigHuaji.Stands.timeStopEffect*100);
			float fov = evt.getFOV();
			if(time>0) {
				if(ticks<t0){
					evt.setFOV(fov+30f);
				} else {
					evt.setFOV(fov+10f);
				}
			}
		}
	}
	 @SubscribeEvent
	    public static void TimeStopRender4(RenderGameOverlayEvent event) {
		 	Minecraft mc = Minecraft.getMinecraft();
		 	boolean hasTag = mc.player!=null
				 && StandUtil.getChargeHandler(mc.player).getBuffTag().equals(HuajiConstant.BuffTags.TIME_STOP);
		 	boolean flag = hasTag && StandUtil.getStandBuffTime(mc.player)>0 ;
		 	ScaledResolution scaledRes = new ScaledResolution(mc);
	        if (event.getType() == RenderGameOverlayEvent.ElementType.VIGNETTE  && ConfigHuaji.Stands.allowMaskTimeStop && flag ) {
				double scale =ConfigHuaji.Stands.timeStopScale;
//        		GlStateManager.enableBlend();
				renderView(mc,"textures/misc/time_stop_view.png");
				renderElement(mc, "textures/misc/gear_1.png", 0, 0, (float)(0.25f*scale), (float)(0.25f*scale));
				renderElement(mc, "textures/misc/gear_2.png", (int)(scaledRes.getScaledWidth()*4/scale)-512,  (int)(scaledRes.getScaledHeight()*4/scale)-512, (float)(0.25f*scale), (float)(0.25f*scale));
//	        	GlStateManager.disableBlend();
	        }
	        
        }
	 
	 public static void renderView(Minecraft mc , String tex) {
	 	ScaledResolution scaledRes = new ScaledResolution(mc);
		GlStateManager.enableDepth();
		GlStateManager.depthMask(false);
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		GlStateManager.color(0.25F, 0.25F, 0.25F, 1.0F);
		mc.getTextureManager().bindTexture(ConfigHuaji.Stands.useTimeStopNoiseMask ?new ResourceLocation(HuajiAge.MODID,tex):new ResourceLocation("textures/misc/vignette.png"));
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder bufferbuilder = tessellator.getBuffer();
		bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX);
		bufferbuilder.pos(0.0D, (double)scaledRes.getScaledHeight(), -90.0D).tex(0.0D, 1.0D).endVertex();
		bufferbuilder.pos((double)scaledRes.getScaledWidth(), (double)scaledRes.getScaledHeight(), -90.0D).tex(1.0D, 1.0D).endVertex();
		bufferbuilder.pos((double)scaledRes.getScaledWidth(), 0.0D, -90.0D).tex(1.0D, 0.0D).endVertex();
      	bufferbuilder.pos(0.0D, 0.0D, -90.0D).tex(0.0D, 0.0D).endVertex();
      	tessellator.draw();
      	GlStateManager.depthMask(true);
      	GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      	GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	 }
	 public static void renderElement(Minecraft mc , String tex , int x , int y ,float scaleX ,float scaleY) {
		 GlStateManager.pushMatrix();
         GlStateManager.disableDepth();
         GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.ZERO, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
         GlStateManager.color(0.3F, 0.3F, 0.3F, 1.0F);
         mc.getTextureManager().bindTexture(new ResourceLocation(HuajiAge.MODID,tex));
         GlStateManager.scale(scaleX, scaleY, 0);
         GlStateManager.translate(x, y, 0);
         Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 512, 512, 512, 512);
         GlStateManager.scale(1.0f, 1.0f, 1.0f);
         GlStateManager.enableDepth();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
       	GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
       	GlStateManager.popMatrix();
	 }
}
