package com.lh_lshen.mcbbs.huajiage.entity.render.layers;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityLoader;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.client.model.ModelMuliKnife;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStarPlatinum;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelTheWorld;
import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.data.StandUserWorldSavedData;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.helper.instance.IStandPower;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHandSide;
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
	 private static final ResourceLocation TEXTRUE_THE_WORLD = new ResourceLocation(HuajiAge.MODID, EnumStandtype.THE_WORLD.getTexPath());
	 private static final ResourceLocation TEXTRUE_STAR_PLATINUM = new ResourceLocation(HuajiAge.MODID, EnumStandtype.STAR_PLATINUM.getTexPath());
	 
	 public LayerStand(RenderLivingBase<?> livingEntityRendererIn)
	    {
	        this.livingEntityRenderer = livingEntityRendererIn;
	    }
	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		
         		String ex_stand = entitylivingbaseIn.getCapability(CapabilityLoader.EXPOSED_DATA, null).getStand();
				boolean istrigger = entitylivingbaseIn.getCapability(CapabilityLoader.EXPOSED_DATA, null).isTriggered();
				EnumStandtype stand = EnumStandtype.getType(ex_stand); 
				IStandPower stand_power = stand!=null?stand.getStandPower():null;
				int stage = StandUtil.getStandStage(entitylivingbaseIn);
//				boolean potion = entitylivingbaseIn.isPotionActive(PotionLoader.potionStand);
				
				String type = stand != null?stand.getName():EnumStandtype.EMPTY;
				ModelStandBase model =  stand != null?StandClientUtil.getModel(type) : null;	
			    	
			    	if(model != null&&!type.equals(EnumStandtype.EMPTY)&& istrigger) 
			    	{
	 					GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	 		            GlStateManager.enableBlend();
	 		            GlStateManager.disableLighting();
//	 		            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
//	 					if(type.equals(EnumStandtype.THE_WORLD.getName())) {
//	 		            livingEntityRenderer.bindTexture(TEXTRUE_THE_WORLD);
//	 		            }else {
// 		            	livingEntityRenderer.bindTexture(TEXTRUE_STAR_PLATINUM);
//	 		            }
	 		            ResourceLocation texture = StandClientUtil.getTex(stand.getName());
	 		            if(texture!=null) {
	 		            livingEntityRenderer.bindTexture(texture);
	 		            }
	 					OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
	 					
	 					GlStateManager.pushMatrix();
//	 		            GlStateManager.translate(0.0F, -0.2F, -0.75F);
	 					model.setPostion();
	 		            model.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn, 1f ,stand.getSpeed()*4/3);
	 		            model.setPunch(limbSwing, limbSwingAmount,  ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn, 0.3f ,stand.getSpeed()*4/3);
	 		            model.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	 		            if(stage>0&&stand_power!=null) {
	 		            stand_power.extraEffects(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
	 		            }
 //	 		            model.doHandRender(0, 0, 0, scale*2, 0.5f);
//	 		           if(type.equals(EnumStandtype.THE_WORLD.getName())) {
// 		        	   MODEL_THE_WORLD.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn, 1 ,stand.getSpeed()*4/3);
//	 		           MODEL_THE_WORLD.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
//	 		           }else {
// 		        	   MODEL_STAR_PLATINUM.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn, 1 ,stand.getSpeed()*4/3);
// 		        	   MODEL_STAR_PLATINUM.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
//	 		           }
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
