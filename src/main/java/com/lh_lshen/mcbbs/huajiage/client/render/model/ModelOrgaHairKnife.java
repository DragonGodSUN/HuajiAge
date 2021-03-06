package com.lh_lshen.mcbbs.huajiage.client.render.model;

import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class ModelOrgaHairKnife extends ModelBase
{
  
  
  @Override
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
		float headPitch, float scale) {
	  ItemStack item=new ItemStack(ItemLoader.orgaHairKnife);
	  Minecraft.getMinecraft().getRenderItem().renderItem(item, TransformType.GROUND);
	  super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
  }
 
  
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
  }

}
