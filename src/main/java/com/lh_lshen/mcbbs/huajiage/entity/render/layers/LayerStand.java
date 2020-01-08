package com.lh_lshen.mcbbs.huajiage.entity.render.layers;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.client.model.ModelMuliKnife;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelTheWorld;
import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.data.StandUserWorldSavedData;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import com.lh_lshen.mcbbs.huajiage.util.StandUtil;

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
	 private static  ModelStandBase model = null;
	 private static final ModelStandBase MODEL_THE_WORLD =new ModelTheWorld(); 
	 private static ResourceLocation tex = null;
	 private static final ResourceLocation TEXTRUE_THE_WORLD = new ResourceLocation(HuajiAge.MODID, EnumStandtype.THE_WORLD.getTexPath());
	 public LayerStand(RenderLivingBase<?> livingEntityRendererIn)
	    {
	        this.livingEntityRenderer = livingEntityRendererIn;
	    }
	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
				EnumStandtype stand = null;
				if( ! entitylivingbaseIn.isPotionActive(PotionLoader.potionStand)) {
					return;
				}else {
					PotionEffect potion = entitylivingbaseIn.getActivePotionEffect(PotionLoader.potionStand);
					stand = EnumStandtype.getTypeById(potion.getAmplifier());
				}
				
				String type = stand != null?stand.getName():EnumStandtype.EMPTY;
				
					if(stand ==null || type == null || type.equals(EnumStandtype.EMPTY) ){
						return;
					}
					
			    	if(stand!=null ) {
			    		if(type!=EnumStandtype.THE_WORLD.getName()) {
				    		model = stand.newModel();
				    		tex = stand.getTex();
			    		}else {
			    			model = MODEL_THE_WORLD;
			    			tex =TEXTRUE_THE_WORLD;
			    		}
			    	}
			    	
			    	if(stand != null&&model  != null&&tex != null) 
			    	{
	 					GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	 		            GlStateManager.enableBlend();
	 		            GlStateManager.disableLighting();
	 		            GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	 					livingEntityRenderer.bindTexture(tex);
	 					OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
	 					
	 					GlStateManager.pushMatrix();
	 		            GlStateManager.translate(0.0F, -0.2F, -0.75F);
	 		            model.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entitylivingbaseIn, 1 ,stand.getSpeed()*4/3);
	 					model.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
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
