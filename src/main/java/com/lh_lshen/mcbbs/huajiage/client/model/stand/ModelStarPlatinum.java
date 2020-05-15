// Date: 2020/1/5 20:18:27
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX
package com.lh_lshen.mcbbs.huajiage.client.model.stand;

import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class ModelStarPlatinum extends ModelStandBase
{
  //fields
	private final ModelRenderer body;
	private final ModelRenderer Shape10;
	private final ModelRenderer Shape11;
	private final ModelRenderer armorl;
	private final ModelRenderer armorr;
	private final ModelRenderer bodydown;
	private final ModelRenderer cloth1;
	private final ModelRenderer cloth2;
	private final ModelRenderer crotch;
	private final ModelRenderer leftarm;
	private final ModelRenderer rightarm;
	private final ModelRenderer scarf;
	private final ModelRenderer scarf2;
	private final ModelRenderer left_hands;
	private final ModelRenderer handl1;
	private final ModelRenderer handl2;
	private final ModelRenderer handl3;
	private final ModelRenderer handl4;
	private final ModelRenderer handl5;
	private final ModelRenderer right_hands;
	private final ModelRenderer handr1;
	private final ModelRenderer handr2;
	private final ModelRenderer handr3;
	private final ModelRenderer handr4;
	private final ModelRenderer handr5;
	private final ModelRenderer head;
	private final ModelRenderer hair1;
	private final ModelRenderer hair2;
	private final ModelRenderer hair3;
	private final ModelRenderer hair4;
	private final ModelRenderer hair5;
	private final ModelRenderer hair6;
	private final ModelRenderer hat;
	private final ModelRenderer leftleg;
	private final ModelRenderer legdownl;
	private final ModelRenderer rightleg;
	private final ModelRenderer legdownr;
  
  public ModelStarPlatinum()
  {
    textureWidth = 64;
    textureHeight = 128;
    
    body = new ModelRenderer(this);
	body.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(body, -0.0873F, 0.0F, 0.0F);
	body.cubeList.add(new ModelBox(body, 16, 16, -4.0F, 0.0F, -2.0F, 8, 7, 4, 0.0F, false));

	Shape10 = new ModelRenderer(this);
	Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(Shape10, -0.0873F, 0.0F, 0.0F);
	body.addChild(Shape10);
	Shape10.cubeList.add(new ModelBox(Shape10, 35, 49, -3.5F, 0.2F, -2.5F, 7, 4, 1, 0.0F, false));

	Shape11 = new ModelRenderer(this);
	Shape11.setRotationPoint(0.0F, 0.0F, -2.3F);
	setRotation(Shape11, -0.0873F, 0.0F, 0.0F);
	body.addChild(Shape11);
	Shape11.cubeList.add(new ModelBox(Shape11, 35, 56, -1.5F, 4.0F, 0.0F, 3, 3, 1, 0.0F, false));

	armorl = new ModelRenderer(this);
	armorl.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(armorl, 0.5236F, 0.0F, 0.0F);
	body.addChild(armorl);
	armorl.cubeList.add(new ModelBox(armorl, 0, 74, 5.0F, -1.0F, -3.0F, 4, 1, 6, 0.0F, false));

	armorr = new ModelRenderer(this);
	armorr.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(armorr, 0.5236F, 0.0F, 0.0F);
	body.addChild(armorr);
	armorr.cubeList.add(new ModelBox(armorr, 0, 74, -9.0F, -1.0F, -3.0F, 4, 1, 6, 0.0F, true));

	bodydown = new ModelRenderer(this);
	bodydown.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(bodydown, -0.0175F, 0.0F, 0.0F);
	body.addChild(bodydown);
	bodydown.cubeList.add(new ModelBox(bodydown, 19, 66, -3.5F, 7.0F, -2.0F, 7, 4, 4, 0.0F, false));

	cloth1 = new ModelRenderer(this);
	cloth1.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(cloth1, -0.1487F, 0.0F, 0.0F);
	body.addChild(cloth1);
	cloth1.cubeList.add(new ModelBox(cloth1, 48, 67, -2.0F, 11.0F, -1.9F, 4, 8, 1, 0.0F, false));

	cloth2 = new ModelRenderer(this);
	cloth2.setRotationPoint(0.0F, 10.9128F, 0.9962F);
	setRotation(cloth2, 0.2861F, 0.0F, 0.0F);
	body.addChild(cloth2);
	cloth2.cubeList.add(new ModelBox(cloth2, 48, 56, -2.5F, 0.0F, 1.0F, 5, 9, 1, 0.0F, false));

	crotch = new ModelRenderer(this);
	crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(crotch, 0.0349F, 0.0F, 0.0F);
	body.addChild(crotch);
	crotch.cubeList.add(new ModelBox(crotch, 16, 82, -4.0F, 10.0F, -3.5F, 8, 2, 6, 0.0F, false));

	leftarm = new ModelRenderer(this);
	leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
	setRotation(leftarm, 0.5236F, 0.0F, 0.0F);
	body.addChild(leftarm);
	leftarm.cubeList.add(new ModelBox(leftarm, 40, 16, -1.0F, -2.0F, -2.0F, 4, 4, 5, 0.0F, false));

	rightarm = new ModelRenderer(this);
	rightarm.setRotationPoint(-5.0F, 2.0F, 0.0F);
	setRotation(rightarm, 0.5236F, 0.0F, 0.0F);
	body.addChild(rightarm);
	rightarm.cubeList.add(new ModelBox(rightarm, 40, 16, -3.0F, -2.0F, -2.0F, 4, 4, 5, 0.0F, true));

	scarf = new ModelRenderer(this);
	scarf.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(scarf, 0.4072F, 0.0F, 0.0F);
	body.addChild(scarf);
	scarf.cubeList.add(new ModelBox(scarf, 24, 35, -5.0F, -1.0F, -4.0F, 10, 2, 10, 0.0F, false));

	scarf2 = new ModelRenderer(this);
	scarf2.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(scarf2, 0.0726F, 0.0F, 0.0F);
	body.addChild(scarf2);
	scarf2.cubeList.add(new ModelBox(scarf2, 28, 35, -4.5F, -1.0F, -4.5F, 9, 3, 9, 0.0F, false));

	left_hands = new ModelRenderer(this);
	left_hands.setRotationPoint(0.0F, 0.0F, 0.0F);

	handl1 = new ModelRenderer(this);
	handl1.setRotationPoint(5.0F, 2.0F, 0.0F);
	setRotation(handl1, -1.5708F, 0.0F, 0.0F);
	left_hands.addChild(handl1);
	handl1.cubeList.add(new ModelBox(handl1, 48, 4, 8.0F, 10.0F, 3.0F, 4, 8, 4, 0.0F, false));

	handl2 = new ModelRenderer(this);
	handl2.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(handl2, -1.5708F, 0.0F, 0.0F);
	left_hands.addChild(handl2);
	handl2.cubeList.add(new ModelBox(handl2, 48, 4, 9.0F, 3.0F, 0.0F, 4, 8, 4, 0.0F, false));

	handl3 = new ModelRenderer(this);
	handl3.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(handl3, -1.5708F, 0.0F, 0.0F);
	left_hands.addChild(handl3);
	handl3.cubeList.add(new ModelBox(handl3, 48, 4, 7.0F, -5.0F, 4.0F, 4, 8, 4, 0.0F, false));

	handl4 = new ModelRenderer(this);
	handl4.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(handl4, -1.5708F, 0.0F, 0.0F);
	left_hands.addChild(handl4);
	handl4.cubeList.add(new ModelBox(handl4, 48, 4, 15.0F, -11.0F, -1.0F, 4, 8, 4, 0.0F, false));

	handl5 = new ModelRenderer(this);
	handl5.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(handl5, -1.5708F, 0.0F, 0.0F);
	left_hands.addChild(handl5);
	handl5.cubeList.add(new ModelBox(handl5, 48, 4, 11.0F, -3.0F, -4.0F, 4, 8, 4, 0.0F, false));

	right_hands = new ModelRenderer(this);
	right_hands.setRotationPoint(0.0F, 0.0F, 0.0F);

	handr1 = new ModelRenderer(this);
	handr1.setRotationPoint(5.0F, 2.0F, 0.0F);
	setRotation(handr1, -1.5708F, 0.0F, 0.0F);
	right_hands.addChild(handr1);
	handr1.cubeList.add(new ModelBox(handr1, 48, 4, -14.0F, 11.0F, -1.0F, 4, 8, 4, 0.0F, true));

	handr2 = new ModelRenderer(this);
	handr2.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(handr2, -1.5708F, 0.0F, 0.0F);
	right_hands.addChild(handr2);
	handr2.cubeList.add(new ModelBox(handr2, 48, 4, -14.0F, -5.0F, -4.0F, 4, 8, 4, 0.0F, true));

	handr3 = new ModelRenderer(this);
	handr3.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(handr3, -1.5708F, 0.0F, 0.0F);
	right_hands.addChild(handr3);
	handr3.cubeList.add(new ModelBox(handr3, 48, 4, -17.0F, 8.0F, 1.0F, 4, 8, 4, 0.0F, true));

	handr4 = new ModelRenderer(this);
	handr4.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(handr4, -1.5708F, 0.0F, 0.0F);
	right_hands.addChild(handr4);
	handr4.cubeList.add(new ModelBox(handr4, 48, 4, -20.0F, -10.0F, 2.0F, 4, 8, 4, 0.0F, true));

	handr5 = new ModelRenderer(this);
	handr5.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(handr5, -1.5708F, 0.0F, 0.0F);
	right_hands.addChild(handr5);
	handr5.cubeList.add(new ModelBox(handr5, 48, 4, -13.0F, 2.0F, 6.0F, 4, 8, 4, 0.0F, true));

	head = new ModelRenderer(this);
	head.setRotationPoint(0.0F, 0.0F, 0.0F);
	head.cubeList.add(new ModelBox(head, 0, 0, -3.5F, -6.0F, -4.0F, 7, 6, 8, 0.0F, false));

	hair1 = new ModelRenderer(this);
	hair1.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(hair1, 0.6093F, -0.1487F, 0.0349F);
	head.addChild(hair1);
	hair1.cubeList.add(new ModelBox(hair1, 0, 35, -4.0F, -8.0F, 0.5F, 3, 3, 6, 0.0F, false));

	hair2 = new ModelRenderer(this);
	hair2.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(hair2, 0.1267F, 0.0F, 0.0F);
	head.addChild(hair2);
	hair2.cubeList.add(new ModelBox(hair2, 0, 49, -3.9F, -8.3F, -2.5F, 8, 4, 8, 0.0F, false));

	hair3 = new ModelRenderer(this);
	hair3.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(hair3, 0.5729F, 0.0F, 0.0F);
	head.addChild(hair3);
	hair3.cubeList.add(new ModelBox(hair3, 0, 35, -2.0F, -9.0F, 0.0F, 4, 4, 8, 0.0F, false));

	hair4 = new ModelRenderer(this);
	hair4.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(hair4, 0.4363F, 0.2269F, 0.2094F);
	head.addChild(hair4);
	hair4.cubeList.add(new ModelBox(hair4, 0, 49, 0.2F, -8.0F, 0.0F, 3, 4, 7, 0.0F, false));

	hair5 = new ModelRenderer(this);
	hair5.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(hair5, 0.4363F, -0.2269F, -0.2094F);
	head.addChild(hair5);
	hair5.cubeList.add(new ModelBox(hair5, 0, 49, -3.2F, -8.0F, 0.0F, 2, 4, 7, 0.0F, false));

	hair6 = new ModelRenderer(this);
	hair6.setRotationPoint(0.0F, 0.0F, 0.0F);
	setRotation(hair6, 0.6109F, 0.1396F, -0.0349F);
	head.addChild(hair6);
	hair6.cubeList.add(new ModelBox(hair6, 0, 35, 1.0F, -8.0F, 0.5F, 3, 3, 6, 0.0F, false));

	hat = new ModelRenderer(this);
	hat.setRotationPoint(0.0F, 0.0F, 0.0F);
	head.addChild(hat);
	hat.cubeList.add(new ModelBox(hat, 0, 118, -4.0F, -6.5F, -4.5F, 8, 2, 8, 0.0F, false));

	leftleg = new ModelRenderer(this);
	leftleg.setRotationPoint(2.0F, 11.0F, 0.0F);
	setRotation(leftleg, -0.5494F, -0.3992F, -0.4341F);
	leftleg.cubeList.add(new ModelBox(leftleg, 21, 100, -2.0F, 0.0F, -3.5F, 5, 7, 5, 0.0F, false));

	legdownl = new ModelRenderer(this);
	legdownl.setRotationPoint(-0.8371F, -0.75F, -1.3178F);
	setRotation(legdownl, 1.1107F, -0.1374F, 0.2004F);
	leftleg.addChild(legdownl);
	legdownl.cubeList.add(new ModelBox(legdownl, 0, 100, -0.5F, 3.5F, -7.0F, 5, 9, 5, 0.0F, false));

	rightleg = new ModelRenderer(this);
	rightleg.setRotationPoint(-2.0F, 11.0F, 0.0F);
	setRotation(rightleg, -0.3635F, 0.2504F, 0.2482F);
	rightleg.cubeList.add(new ModelBox(rightleg, 21, 100, -3.0F, 0.0F, -3.5F, 5, 7, 5, 0.0F, true));

	legdownr = new ModelRenderer(this);
	legdownr.setRotationPoint(0.6913F, -0.6595F, 1.0427F);
	setRotation(legdownr, 0.427F, 0.0728F, 0.0614F);
	rightleg.addChild(legdownr);
	legdownr.cubeList.add(new ModelBox(legdownr, 0, 100, -2.5F, 5.5F, -6.0F, 5, 9, 5, 0.0F, true));
  }
  
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
    setRotationAngles( limbSwing, limbSwingAmount, ageInTicks, headPitch,netHeadYaw, scale, entityIn);
    float off = (float) (MathHelper.cos((float) (0.1*ageInTicks))*0.1);
    head.offsetY = off;
    body.offsetY = off;
    leftleg.offsetY = off;
    rightleg.offsetY = off;
    
    head.rotateAngleX = headPitch * 0.017453292F;
    head.rotateAngleY = netHeadYaw * 0.017453292F;
    
    head.render(scale);
    body.render(scale);
    leftleg.render(scale);
    rightleg.render(scale);
    left_hands.render(scale);
    right_hands.render(scale);
    
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  

	@Override
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
	  
	  left_hands.rotateAngleX = rotatePitch * 0.017453292F;
	  left_hands.rotateAngleY = rotateYaw * 0.017453292F;
	  right_hands.rotateAngleX = rotatePitch * 0.017453292F;
	  right_hands.rotateAngleY = rotateYaw * 0.017453292F;
	}

	@Override
	public void doHandRender(float x, float y, float z, float scale, float alpha) {
		GlStateManager.translate(x, y, z);
	  	GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
	  	left_hands.render(scale);
	  	right_hands.render(scale);
	
	}

	public void setPunch(float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw,
	          float rotatePitch, float scale, Entity entity ,float power ,float speed)
	  {
	    super.setRotationAngles(limbSwing, limbSwingAmount, rotateFloat, rotateYaw, rotatePitch, scale, entity);
	    float r = (float) Math.random();
	    float offysin =r* MathHelper.sin(r*speed*rotateFloat)  * power-0.15f;
	    float offycos =r* MathHelper.cos(r *speed*rotateFloat)  * power-0.15f;
	    handl1.offsetZ =offysin;
	    handl2.offsetZ = offycos;
	    handl3.offsetZ = offysin;
	    handl4.offsetZ = offycos;
	    handl5.offsetZ = offysin;
	    handr1.offsetZ = offycos;
	    handr2.offsetZ = offysin;
	    handr3.offsetZ = offycos;
	    handr4.offsetZ = offysin;
	    handr5.offsetZ = offycos;
	    float offxl =r* MathHelper.sin(r*speed*rotateFloat)  * power+0.2f;
	    float offxr =r* MathHelper.sin(r*speed*rotateFloat)  * power-0.2f;
	    left_hands.offsetX = offxl;
	    right_hands.offsetX = offxr;
	    
	  }

	@Override
	public void setPostion() {
		GlStateManager.translate(0.0F, -0.2F, -0.75F);		
	}

	@Override
	public void effect(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void extraEffect(Entity entityIn, float limbSwing, float limbSwingAmount, 
			float ageInTicks, float netHeadYaw, float headPitch, float scale){
		ModelStandBase model = new ModelStarPlatinum();
		model.setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entityIn, 1 ,StandLoader.STAR_PLATINUM.getSpeed());
		model.doHandRender(0, 0, 0,(float)(scale*1.3), 0.5f);
	}
}
