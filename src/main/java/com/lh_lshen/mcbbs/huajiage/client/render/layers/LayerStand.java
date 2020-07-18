package com.lh_lshen.mcbbs.huajiage.client.render.layers;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityLoader;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
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
		
         		String ex_stand = entitylivingbaseIn.getCapability(CapabilityLoader.EXPOSED_DATA, null).getStand();
				boolean istrigger = entitylivingbaseIn.getCapability(CapabilityLoader.EXPOSED_DATA, null).isTriggered();
				StandBase stand = StandLoader.getStand(ex_stand); 
//				IStandPower stand_power = stand!=null?stand:null;
				int stage = StandUtil.getStandStage(entitylivingbaseIn);
//				boolean potion = entitylivingbaseIn.isPotionActive(PotionLoader.potionStand);
				
				String type = stand != null?stand.getName():StandLoader.EMPTY;
				ModelStandBase model =  stand != null?StandClientUtil.getModelByData(entitylivingbaseIn) : null;
			    	
			    	if(model != null&&!type.equals(StandLoader.EMPTY)&& istrigger) 
			    	{
	 					GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	 		            GlStateManager.enableBlend();
	 		            GlStateManager.disableLighting();
	 		            ResourceLocation texture = StandClientUtil.getTex(stand.getName());
	 		            if(texture!=null) {
	 		            livingEntityRenderer.bindTexture(texture);
	 		            }
	 					OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
	 					
	 					GlStateManager.pushMatrix();
	 					
	 					model.setPosition();
	 		            model.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn, 1f ,stand.getSpeed()*4/3);
	 		            model.setPunch(limbSwing, limbSwingAmount,  ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn, 0.3f ,stand.getSpeed()*4/3);
	 		            model.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	 		            model.effect(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	 		            if(stage>0&&stand!=null) {
	 		            model.extraEffect(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	 		            }

	 					GlStateManager.disableBlend();
						GlStateManager.enableLighting();
			            GlStateManager.popMatrix();
			    	}
	}
	
	@Override
	public boolean shouldCombineTextures() {
		
		return false;
	}


}
