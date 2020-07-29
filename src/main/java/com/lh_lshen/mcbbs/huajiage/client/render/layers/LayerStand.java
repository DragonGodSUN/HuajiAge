package com.lh_lshen.mcbbs.huajiage.client.render.layers;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityLoader;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerStand implements  LayerRenderer<EntityLivingBase> {
	 protected final RenderLivingBase<?> livingEntityRenderer;
//	 private static  ModelStandBase model = null;
//	 private static ResourceLocation tex = null;
//	 private static final ModelStandBase MODEL_THE_WORLD =new ModelTheWorld(); 
//	 private static final ModelStandBase MODEL_STAR_PLATINUM =new ModelStarPlatinum(); 
//	 private static final ResourceLocation TEXTRUE_THE_WORLD = new ResourceLocation(HuajiAge.MODID, StandLoader.THE_WORLD.getTexPath());
//	 private static final ResourceLocation TEXTRUE_STAR_PLATINUM = new ResourceLocation(HuajiAge.MODID, StandLoader.STAR_PLATINUM.getTexPath());
	 
	 public LayerStand(RenderLivingBase<?> livingEntityRendererIn)
	    {
	        this.livingEntityRenderer = livingEntityRendererIn;
	    }
	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
		float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		IExposedData data = entitylivingbaseIn.getCapability(CapabilityLoader.EXPOSED_DATA, null);
		if (data!=null) {
			String ex_stand = data.getStand();
			boolean istrigger = data.isTriggered();
			StandBase stand = StandLoader.getStand(ex_stand);
			String state = data.getState();
			int stage = data.getStage();
			String type = stand != null?stand.getName():StandLoader.EMPTY;
			ModelStandBase model =  stand != null?StandClientUtil.getModelByData(entitylivingbaseIn,stand) : null;

			if(model != null&&!type.equals(StandLoader.EMPTY)&& istrigger)
			{
				 GlStateManager.pushMatrix();
				 GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
				 GlStateManager.enableBlend();
				 GlStateManager.disableLighting();
				 ResourceLocation texture = StandClientUtil.getTex(stand.getName(),state);
				 if(texture!=null) {
				 livingEntityRenderer.bindTexture(texture);
				 }
				 OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);

				 model.setPosition();
				 model.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn, 1f ,stand.getSpeed()*4/3);
				 model.setPunch(limbSwing, limbSwingAmount,  ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn, 0.3f ,stand.getSpeed()*4/3);
				 model.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
				 model.effect(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
				 if(stage>0&&stand!=null) {
				 model.extraEffect(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
				 }

				GlStateManager.enableLighting();
			 	GlStateManager.disableBlend();
				GlStateManager.popMatrix();
			}
		}
	}
	
	@Override
	public boolean shouldCombineTextures() {
		
		return false;
	}


}
