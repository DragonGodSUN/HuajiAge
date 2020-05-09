package com.lh_lshen.mcbbs.huajiage.entity.render.layers;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.client.model.ModelLordLu;
import com.lh_lshen.mcbbs.huajiage.item.ItemBlancedHelmet;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
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
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerLordPower implements  LayerRenderer<EntityLivingBase> {
	 protected final RenderLivingBase<?> livingEntityRenderer;
	 public LayerLordPower(RenderLivingBase<?> livingEntityRendererIn)
	    {
	        this.livingEntityRenderer = livingEntityRendererIn;
	    }
	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) 
	{
		if(entitylivingbaseIn instanceof AbstractClientPlayer) 
			{
				AbstractClientPlayer p=(AbstractClientPlayer) entitylivingbaseIn;
	 			ItemStack helmet = p.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
	 			if(helmet.getItem() instanceof ItemBlancedHelmet && helmet.getTagCompound().hasKey("lord") &&
	 					!p.onGround) {
	 				GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		            GlStateManager.enableBlend();	
		            GlStateManager.disableLighting();
	 				GlStateManager.pushMatrix();
	 				GlStateManager.translate(0, 0, 0.5);
	 				ModelLordLu lord = new ModelLordLu();
	 				livingEntityRenderer.bindTexture(new ResourceLocation(HuajiAge.MODID,"textures/entity/lord_lu_power.png"));
	 				OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
	 				lord.setWingRotation(-ageInTicks/20, ageInTicks/10);
	 				lord.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	 				GlStateManager.disableBlend();
					GlStateManager.enableLighting();
		            GlStateManager.popMatrix();
	 			}
			 }
	}


	@Override
	public boolean shouldCombineTextures() {
		
		return false;
	}

}
