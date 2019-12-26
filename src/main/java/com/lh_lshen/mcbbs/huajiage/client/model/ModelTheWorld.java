// Date: 2019/12/23 18:06:27
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX
package com.lh_lshen.mcbbs.huajiage.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTheWorld extends ModelBase
{
  //fields
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer head;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer body;
    ModelRenderer bodydown;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer back1;
    ModelRenderer back2;
    ModelRenderer leftleg;
    ModelRenderer legdownl;
    ModelRenderer rightleg;
    ModelRenderer legdownr;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    public ModelRenderer handl1;
    public ModelRenderer handl2;
    public ModelRenderer handl3;
    public ModelRenderer handl4;
    public ModelRenderer handl5;
    public ModelRenderer handr1;
    public ModelRenderer handr2;
    public ModelRenderer handr3;
    public ModelRenderer handr4;
    public ModelRenderer handr5;
  
  public ModelTheWorld()
  {
    textureWidth = 64;
    textureHeight = 128;
    
      Shape6 = new ModelRenderer(this, 0, 30);
      Shape6.addBox(-1F, 0F, -5F, 2, 1, 1);
      Shape6.setRotationPoint(0F, 0F, 0F);
      Shape6.setTextureSize(64, 128);
      setRotation(Shape6, 0F, 0F, 0.7853982F);
      Shape7 = new ModelRenderer(this, 0, 30);
      Shape7.addBox(0F, -1F, -5F, 1, 1, 1);
      Shape7.setRotationPoint(0F, 0F, 0F);
      Shape7.setTextureSize(64, 128);
      setRotation(Shape7, 0F, 0F, 0.7853982F);
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-3.5F, -6F, -4F, 7, 6, 8);
      head.setRotationPoint(0F, 0F, 0F);
      head.setTextureSize(64, 128);
      setRotation(head, 0F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 0, 35);
      Shape1.addBox(-4.5F, -8F, -1F, 9, 3, 9);
      Shape1.setRotationPoint(0F, 0F, 0F);
      Shape1.setTextureSize(64, 128);
      setRotation(Shape1, 0.4014257F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 49);
      Shape2.addBox(-4.5F, -7.2F, -3F, 9, 4, 8);
      Shape2.setRotationPoint(0F, 0F, 0F);
      Shape2.setTextureSize(64, 128);
      setRotation(Shape2, 0.0523599F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 63);
      Shape3.addBox(2.8F, -7F, -3F, 3, 4, 6);
      Shape3.setRotationPoint(0F, 0F, 0F);
      Shape3.setTextureSize(64, 128);
      setRotation(Shape3, 0F, 1.099557F, 0F);
      Shape4 = new ModelRenderer(this, 0, 63);
      Shape4.mirror = true;
      Shape4.addBox(-5.8F, -7F, -3F, 3, 4, 6);
      Shape4.setRotationPoint(0F, 0F, 0F);
      Shape4.setTextureSize(64, 128);
      setRotation(Shape4, 0F, -1.099557F, 0F);
      Shape5 = new ModelRenderer(this, 0, 75);
      Shape5.addBox(-4.5F, -4F, 1F, 9, 2, 4);
      Shape5.setRotationPoint(0F, 0F, 0F);
      Shape5.setTextureSize(64, 128);
      setRotation(Shape5, -0.1504955F, 0F, 0F);
      body = new ModelRenderer(this, 16, 16);
      body.addBox(-4F, 0F, -2F, 8, 7, 4);
      body.setRotationPoint(0F, 0F, 0F);
      body.setTextureSize(64, 128);
      setRotation(body, 0.0872665F, 0F, 0F);
      bodydown = new ModelRenderer(this, 19, 66);
      bodydown.addBox(-3.5F, 7F, -2F, 7, 4, 4);
      bodydown.setRotationPoint(0F, 0F, 0F);
      bodydown.setTextureSize(64, 128);
      setRotation(bodydown, 0.0872665F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 37, 30);
      Shape8.addBox(1F, -1F, -3F, 2, 11, 6);
      Shape8.setRotationPoint(0F, 0F, 0F);
      Shape8.setTextureSize(64, 128);
      setRotation(Shape8, 0.0872665F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 37, 30);
      Shape9.mirror = true;
      Shape9.addBox(-3F, -1F, -3F, 2, 11, 6);
      Shape9.setRotationPoint(0F, 0F, 0F);
      Shape9.setTextureSize(64, 128);
      setRotation(Shape9, 0.0872665F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 35, 49);
      Shape10.addBox(-3.5F, 0.2F, -2.5F, 7, 4, 1);
      Shape10.setRotationPoint(0F, 0F, 0F);
      Shape10.setTextureSize(64, 128);
      setRotation(Shape10, 0.0872665F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 35, 56);
      Shape11.addBox(-1.5F, 4.2F, 0F, 3, 3, 1);
      Shape11.setRotationPoint(0F, 0F, -2.3F);
      Shape11.setTextureSize(64, 128);
      setRotation(Shape11, 0.0872665F, 0F, 0F);
      shape12 = new ModelRenderer(this, 16, 82);
      shape12.addBox(-4F, 10F, -3.5F, 8, 2, 7);
      shape12.setRotationPoint(0F, 0F, 0F);
      shape12.setTextureSize(64, 128);
      setRotation(shape12, 0.0872665F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 0, 30);
      Shape13.addBox(7F, 8F, -3.5F, 2, 1, 1);
      Shape13.setRotationPoint(0F, 0F, 0F);
      Shape13.setTextureSize(64, 128);
      setRotation(Shape13, 0.0872665F, 0F, 0.7853982F);
      Shape14 = new ModelRenderer(this, 0, 30);
      Shape14.addBox(8F, 7F, -3.5F, 1, 1, 1);
      Shape14.setRotationPoint(0F, 0F, 0F);
      Shape14.setTextureSize(64, 128);
      setRotation(Shape14, 0.0872665F, 0F, 0.7853982F);
      back1 = new ModelRenderer(this, 0, 83);
      back1.addBox(0.5F, 0F, 3F, 2, 7, 2);
      back1.setRotationPoint(0F, 0F, 0F);
      back1.setTextureSize(64, 128);
      setRotation(back1, 0.0872665F, 0F, 0F);
      back2 = new ModelRenderer(this, 0, 83);
      back2.mirror = true;
      back2.addBox(-2.5F, 0F, 3F, 2, 7, 2);
      back2.setRotationPoint(0F, 0F, 0F);
      back2.setTextureSize(64, 128);
      setRotation(back2, 0.0872665F, 0F, 0F);
      leftleg = new ModelRenderer(this, 21, 100);
      leftleg.addBox(-2F, 0F, -3F, 5, 7, 5);
      leftleg.setRotationPoint(2F, 11F, 0F);
      leftleg.setTextureSize(64, 128);
      setRotation(leftleg, -0.6981317F, -0.4363323F, -0.4712389F);
      legdownl = new ModelRenderer(this, 0, 100);
      legdownl.addBox(-0.5F, 3.5F, -5F, 5, 9, 5);
      legdownl.setRotationPoint(2F, 11F, 0F);
      legdownl.setTextureSize(64, 128);
      setRotation(legdownl, 0.1652315F, -0.1745329F, -0.1745329F);
      rightleg = new ModelRenderer(this, 21, 100);
      rightleg.mirror = true;
      rightleg.addBox(-3F, 0F, -3F, 5, 7, 5);
      rightleg.setRotationPoint(-2F, 11F, 0F);
      rightleg.setTextureSize(64, 128);
      setRotation(rightleg, -0.6981317F, 0.4363323F, 0.4712389F);
      legdownr = new ModelRenderer(this, 0, 100);
      legdownr.addBox(-4.5F, 3.5F, -5F, 5, 9, 5);
      legdownr.setRotationPoint(-2F, 11F, 0F);
      legdownr.setTextureSize(64, 128);
      setRotation(legdownr, 0.1652315F, 0.1745329F, 0.1745329F);
      rightarm = new ModelRenderer(this, 40, 16);
      rightarm.mirror = true;
      rightarm.addBox(-3F, -2F, -2F, 4, 4, 5);
      rightarm.setRotationPoint(-5F, 2F, 0F);
      rightarm.setTextureSize(64, 128);
      setRotation(rightarm, 0.5235988F, 0F, 0F);
      leftarm = new ModelRenderer(this, 40, 16);
      leftarm.addBox(-1F, -2F, -2F, 4, 4, 5);
      leftarm.setRotationPoint(5F, 2F, 0F);
      leftarm.setTextureSize(64, 128);
      setRotation(leftarm, 0.5235988F, 0F, 0F);
      handl1 = new ModelRenderer(this, 48, 4);
      handl1.addBox(-1F, 4F, -1F, 4, 8, 4);
      handl1.setRotationPoint(5F, 2F, 0F);
      handl1.setTextureSize(64, 128);
      setRotation(handl1, -1.570796F, 0F, 0F);
      handl2 = new ModelRenderer(this, 48, 4);
      handl2.addBox(6F, 9F, -3F, 4, 8, 4);
      handl2.setRotationPoint(0F, 0F, 0F);
      handl2.setTextureSize(64, 128);
      setRotation(handl2, -1.570796F, 0F, 0F);
      handl3 = new ModelRenderer(this, 48, 4);
      handl3.addBox(13F, -5F, 4F, 4, 8, 4);
      handl3.setRotationPoint(0F, 0F, 0F);
      handl3.setTextureSize(64, 128);
      setRotation(handl3, -1.570796F, 0F, 0F);
      handl4 = new ModelRenderer(this, 48, 4);
      handl4.addBox(12F, -2F, -1F, 4, 8, 4);
      handl4.setRotationPoint(0F, 0F, 0F);
      handl4.setTextureSize(64, 128);
      setRotation(handl4, -1.570796F, 0F, 0F);
      handl5 = new ModelRenderer(this, 48, 4);
      handl5.addBox(8F, 6F, 3F, 4, 8, 4);
      handl5.setRotationPoint(0F, 0F, 0F);
      handl5.setTextureSize(64, 128);
      setRotation(handl5, -1.570796F, 0F, 0F);
      handr1 = new ModelRenderer(this, 48, 4);
      handr1.mirror = true;
      handr1.addBox(-15F, 7F, -3F, 4, 8, 4);
      handr1.setRotationPoint(5F, 2F, 0F);
      handr1.setTextureSize(64, 128);
      setRotation(handr1, -1.570796F, 0F, 0F);
      handr2 = new ModelRenderer(this, 48, 4);
      handr2.mirror = true;
      handr2.addBox(-15F, 0F, -3F, 4, 8, 4);
      handr2.setRotationPoint(0F, 0F, 0F);
      handr2.setTextureSize(64, 128);
      setRotation(handr2, -1.570796F, 0F, 0F);
      handr3 = new ModelRenderer(this, 48, 4);
      handr3.mirror = true; 
      handr3.addBox(-9F, -8F, 3F, 4, 8, 4);
      handr3.setRotationPoint(0F, 0F, 0F);
      handr3.setTextureSize(64, 128);
      setRotation(handr3, -1.570796F, 0F, 0F);
      handr4 = new ModelRenderer(this, 48, 4);
      handr4.mirror = true;
      handr4.addBox(-16F, 7F, 2F, 4, 8, 4);
      handr4.setRotationPoint(0F, 0F, 0F);
      handr4.setTextureSize(64, 128);
      setRotation(handr4, -1.570796F, 0F, 0F);
      handr5 = new ModelRenderer(this, 48, 4);
      handr5.mirror = true;
      handr5.addBox(-11F, 0F, 4F, 4, 8, 4);
      handr5.setRotationPoint(0F, 0F, 0F);
      handr5.setTextureSize(64, 128);
      setRotation(handr5, -1.570796F, 0F, 0F);
  }
  
  
  @Override
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
		float headPitch, float scale) {
    setRotationAngles( limbSwing, limbSwingAmount, ageInTicks, headPitch,netHeadYaw, scale, entityIn);
    Shape6.render(scale);
    Shape7.render(scale);
    head.render(scale);
    Shape1.render(scale);
    Shape2.render(scale);
    Shape3.render(scale);
    Shape4.render(scale);
    Shape5.render(scale);
    body.render(scale);
    bodydown.render(scale);
    Shape8.render(scale);
    Shape9.render(scale);
    Shape10.render(scale);
    Shape11.render(scale);
    shape12.render(scale);
    Shape13.render(scale);
    Shape14.render(scale);
    back1.render(scale);
    back2.render(scale);
    leftleg.render(scale);
    legdownl.render(scale);
    rightleg.render(scale);
    legdownr.render(scale);
    rightarm.render(scale);
    leftarm.render(scale);
	    handl1.render(scale);
	    handl2.render(scale);
	    handl3.render(scale);
	    handl4.render(scale);
	    handl5.render(scale);
	    handr1.render(scale);
	    handr2.render(scale);
	    handr3.render(scale);
	    handr4.render(scale);
	    handr5.render(scale);
    super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw,
          float rotatePitch, float scale, Entity entity ,float power ,float speed)
  {
    super.setRotationAngles(limbSwing, limbSwingAmount, rotateFloat, rotateYaw, rotatePitch, scale, entity);
    handl1.rotateAngleY = MathHelper.cos(speed*rotateFloat) * 1.2F * power;
    handl2.rotateAngleY = MathHelper.cos(speed*rotateFloat+(float)Math.PI/3) * 1.4F * power;
    handl3.rotateAngleY = MathHelper.cos(speed*rotateFloat+(float)(2*Math.PI/3)) * 1.6F * power;
    handl4.rotateAngleY = MathHelper.cos(speed*rotateFloat+(float)(2.5*Math.PI/3)) * 1.3F * power;
    handl5.rotateAngleY = MathHelper.cos(speed*rotateFloat+(float)(3*Math.PI/3)) * 1.7F * power;
    handr1.rotateAngleY = MathHelper.cos(speed*rotateFloat+(float)(3.5*Math.PI/3)) * 1.2F * power;
    handr2.rotateAngleY = MathHelper.cos(speed*rotateFloat+(float)(6*Math.PI/3)) * 1.1F * power;
    handr3.rotateAngleY = MathHelper.cos(speed*rotateFloat+(float)(8*Math.PI/3)) * 1.4F * power;
    handr4.rotateAngleY = MathHelper.cos(speed*rotateFloat+(float)(10*Math.PI/3)) * 1.5F * power;
    handr5.rotateAngleY = MathHelper.cos(speed*rotateFloat+(float)(1.5*Math.PI/3) )* 1.6F * power;
  }
  public void doHandRender(float x ,float y ,float z ,float scale ,float alpha) {
	  	GlStateManager.translate(x, y, z);
	  	GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
	  	handl1.render(scale);
	    handl2.render(scale);
	    handl3.render(scale);
	    handl4.render(scale);
	    handl5.render(scale);
	    handr1.render(scale);
	    handr2.render(scale);
	    handr3.render(scale);
	    handr4.render(scale);
	    handr5.render(scale);
  }

}
