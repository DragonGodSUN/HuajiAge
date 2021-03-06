// Date: 2019/9/25 19:24:06
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX






package com.lh_lshen.mcbbs.huajiage.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMuliKnife extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
  
  public ModelMuliKnife()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Shape1 = new ModelRenderer(this, 0, 27);
      Shape1.addBox(-4.6F, 0F, -3F, 2, 2, 3);
      Shape1.setRotationPoint(3F, 16F-17f, 0F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0.8179294F, 0F, 0F);
      
      Shape2 = new ModelRenderer(this, 0, 27);
      Shape2.addBox(-0.3F, 0F, -3F, 2, 2, 3);
      Shape2.setRotationPoint(0F, 16F-17f, 0F);
      Shape2.setTextureSize(64, 32); 
      Shape2.mirror = false;
      
      setRotation(Shape2, 0.8179294F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 17);
      Shape3.addBox(0F, 0F, 0F, 2, 3, 2);
      Shape3.setRotationPoint(-1F, 15.33333F-17f, -1.6F);
      Shape3.setTextureSize(64, 32);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 12, 23);
      Shape4.addBox(-1F, 0F, 0F, 2, 7, 2);
      Shape4.setRotationPoint(0F, 17F-17f, -1F);
      Shape4.setTextureSize(64, 32);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 0, 0);
      Shape5.addBox(-0.5F, 0F, 0F, 1, 12, 2);
      Shape5.setRotationPoint(0F, 3.866667F-17f, -1.5F);
      Shape5.setTextureSize(64, 32);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 0, 0);
      Shape6.addBox(0F, 0F, -1F, 1, 11, 3);
      Shape6.setRotationPoint(-0.5F, 7F-17f, -2.5F);
      Shape6.setTextureSize(64, 32);
      Shape6.mirror = true;
      setRotation(Shape6, 0.1487144F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 0, 0);
      Shape7.addBox(0F, -0.7666667F, 0.3F, 1, 5, 2);
      Shape7.setRotationPoint(-0.5F, 3.4F-17f, -2F);
      Shape7.setTextureSize(64, 32);
      Shape7.mirror = true;
      setRotation(Shape7, -0.4461433F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 0, 0);
      Shape8.addBox(0F, -0.1333333F, 0F, 1, 1, 2);
      Shape8.setRotationPoint(-0.5F, 2.333333F-17f, -0.8666667F);
      Shape8.setTextureSize(64, 32);
      Shape8.mirror = true;
      setRotation(Shape8, -0.8179294F, 0F, 0F);
  }
  
  @Override
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
		float headPitch, float scale) {
	
    setRotationAngles( limbSwing, limbSwingAmount, ageInTicks, headPitch,netHeadYaw, scale);
    Shape1.render(scale);
    Shape2.render(scale);
    Shape3.render(scale);
    Shape4.render(scale);
    Shape5.render(scale);
    Shape6.render(scale);
    Shape7.render(scale);
    Shape8.render(scale);
	
	super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
  }
 
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
  }

}
