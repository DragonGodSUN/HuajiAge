package com.lh_lshen.mcbbs.huajiage.client.model.stand.states;// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelKillerQueenPunch extends ModelStandBase {
	private final ModelRenderer body;
	private final ModelRenderer Shape10;
	private final ModelRenderer Shape11;
	private final ModelRenderer bodydown;
	private final ModelRenderer cloth;
	private final ModelRenderer cloth1;
	private final ModelRenderer cloth2;
	private final ModelRenderer cloth3;
	private final ModelRenderer cloth4;
	private final ModelRenderer cloth5;
	private final ModelRenderer cloth6;
	private final ModelRenderer crotch;
	private final ModelRenderer leftarm;
	private final ModelRenderer rightarm;
	private final ModelRenderer head;
	private final ModelRenderer leftleg;
	private final ModelRenderer legdownl;
	private final ModelRenderer rightleg;
	private final ModelRenderer legdownr;
	private final ModelRenderer hands_r;
	private final ModelRenderer r_hand1;
	private final ModelRenderer r_hand2;
	private final ModelRenderer r_hand3;
	private final ModelRenderer r_hand4;
	private final ModelRenderer r_hand5;
	private final ModelRenderer hands_l;
	private final ModelRenderer l_hand1;
	private final ModelRenderer l_hand2;
	private final ModelRenderer l_hand3;
	private final ModelRenderer l_hand4;
	private final ModelRenderer l_hand5;

	public ModelKillerQueenPunch() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -2.0F, 0.0F);
		setRotationAngle(body, 0.1745F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 23, -4.0F, 0.0F, -2.0F, 8, 7, 4, 0.0F, false));

		Shape10 = new ModelRenderer(this);
		Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Shape10, -0.0873F, 0.0F, 0.0F);
		body.addChild(Shape10);
		Shape10.cubeList.add(new ModelBox(Shape10, 52, 36, -3.5F, 0.2F, -2.5F, 7, 4, 1, 0.0F, false));

		Shape11 = new ModelRenderer(this);
		Shape11.setRotationPoint(0.0F, 0.0F, -2.3F);
		setRotationAngle(Shape11, -0.0873F, 0.0F, 0.0F);
		body.addChild(Shape11);
		Shape11.cubeList.add(new ModelBox(Shape11, 0, 0, -1.5F, 4.0F, 0.0F, 3, 3, 1, 0.0F, false));

		bodydown = new ModelRenderer(this);
		bodydown.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bodydown, -0.0175F, 0.0F, 0.0F);
		body.addChild(bodydown);
		bodydown.cubeList.add(new ModelBox(bodydown, 16, 45, -3.5F, 7.0F, -2.0F, 7, 4, 4, 0.0F, false));

		cloth = new ModelRenderer(this);
		cloth.setRotationPoint(0.0F, 0.0F, 0.0F);
		body.addChild(cloth);

		cloth1 = new ModelRenderer(this);
		cloth1.setRotationPoint(0.0F, 12.2987F, -4.151F);
		setRotationAngle(cloth1, -0.4978F, 0.0F, 0.0F);
		cloth.addChild(cloth1);
		cloth1.cubeList.add(new ModelBox(cloth1, 0, 4, -1.0F, -2.5F, -0.5F, 2, 3, 1, 0.0F, false));

		cloth2 = new ModelRenderer(this);
		cloth2.setRotationPoint(0.0F, 10.9128F, 0.9962F);
		setRotationAngle(cloth2, 0.6352F, 0.0F, 0.0F);
		cloth.addChild(cloth2);
		cloth2.cubeList.add(new ModelBox(cloth2, 42, 30, -4.5F, 0.0F, 1.0F, 9, 5, 1, 0.0F, false));

		cloth3 = new ModelRenderer(this);
		cloth3.setRotationPoint(-3.0F, 10.9128F, 0.9962F);
		setRotationAngle(cloth3, -0.063F, 0.0873F, 0.6109F);
		cloth.addChild(cloth3);
		cloth3.cubeList.add(new ModelBox(cloth3, 46, 49, -2.0F, -0.4128F, -4.0F, 1, 5, 6, 0.0F, false));

		cloth4 = new ModelRenderer(this);
		cloth4.setRotationPoint(3.0F, 9.9128F, 0.9962F);
		setRotationAngle(cloth4, -0.063F, -0.0873F, -0.7854F);
		cloth.addChild(cloth4);
		cloth4.cubeList.add(new ModelBox(cloth4, 32, 49, 0.5F, 0.0F, -4.0F, 1, 5, 6, 0.0F, false));

		cloth5 = new ModelRenderer(this);
		cloth5.setRotationPoint(3.8146F, 11.6044F, 0.3294F);
		setRotationAngle(cloth5, -0.5866F, -0.0873F, -0.4363F);
		cloth.addChild(cloth5);
		cloth5.cubeList.add(new ModelBox(cloth5, 0, 57, -3.8146F, 0.0F, -4.0F, 5, 5, 1, 0.0F, false));

		cloth6 = new ModelRenderer(this);
		cloth6.setRotationPoint(-3.6516F, 10.6473F, 0.8066F);
		setRotationAngle(cloth6, -0.412F, 0.1746F, 0.4363F);
		cloth.addChild(cloth6);
		cloth6.cubeList.add(new ModelBox(cloth6, 40, 49, -0.5F, 0.0F, -4.0F, 5, 5, 1, 0.0F, false));

		crotch = new ModelRenderer(this);
		crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(crotch, 0.0349F, 0.0F, 0.0F);
		cloth.addChild(crotch);
		crotch.cubeList.add(new ModelBox(crotch, 0, 15, -4.0F, 10.0F, -3.5F, 8, 2, 6, 0.0F, false));

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
		setRotationAngle(leftarm, -0.2618F, 0.0F, 0.0F);
		body.addChild(leftarm);
		leftarm.cubeList.add(new ModelBox(leftarm, 0, 48, -1.0F, -2.0F, -2.0F, 4, 4, 5, 0.0F, false));

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-6.0F, 2.0F, 0.5F);
		setRotationAngle(rightarm, -0.6109F, 0.0F, 0.0F);
		body.addChild(rightarm);
		rightarm.cubeList.add(new ModelBox(rightarm, 47, 0, -2.0F, -2.0F, -2.5F, 4, 4, 5, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -1.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -3.5F, -7.0F, -4.0F, 7, 7, 8, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 55, 55, 2.5F, -9.0F, -3.0F, 1, 2, 5, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 22, 0, -2.5F, -8.0F, -3.5F, 5, 1, 7, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 54, 44, -3.5F, -9.0F, -3.0F, 1, 2, 5, 0.0F, false));

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(2.0F, 11.0F, 3.0F);
		setRotationAngle(leftleg, -0.2003F, -0.0502F, -0.2595F);
		leftleg.cubeList.add(new ModelBox(leftleg, 37, 37, -2.0F, -2.0F, -3.5F, 5, 7, 5, 0.0F, false));

		legdownl = new ModelRenderer(this);
		legdownl.setRotationPoint(0.1482F, 6.2009F, -0.5752F);
		setRotationAngle(legdownl, 0.6109F, 0.0F, 0.0F);
		leftleg.addChild(legdownl);
		legdownl.cubeList.add(new ModelBox(legdownl, 34, 8, -1.6482F, -1.5F, -1.5F, 4, 9, 5, 0.0F, false));

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(-2.0F, 10.0F, 4.0F);
		setRotationAngle(rightleg, -0.538F, -0.0114F, 0.2482F);
		rightleg.cubeList.add(new ModelBox(rightleg, 0, 34, -3.0F, 0.0F, -3.5F, 5, 7, 5, 0.0F, false));

		legdownr = new ModelRenderer(this);
		legdownr.setRotationPoint(0.2077F, 3.6937F, 3.8524F);
		setRotationAngle(legdownr, 0.7854F, 0.0F, 0.0F);
		rightleg.addChild(legdownr);
		legdownr.cubeList.add(new ModelBox(legdownr, 24, 27, -2.7077F, -1.5F, -7.5F, 4, 9, 5, 0.0F, false));

		hands_r = new ModelRenderer(this);
		hands_r.setRotationPoint(0.0F, 0.0F, 0.0F);

		r_hand1 = new ModelRenderer(this);
		r_hand1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(r_hand1, -1.5708F, 1.5708F, 0.0F);
		hands_r.addChild(r_hand1);
		r_hand1.cubeList.add(new ModelBox(r_hand1, 38, 23, -8.0F, 19.0F, -1.0F, 8, 3, 4, 0.0F, false));

		r_hand2 = new ModelRenderer(this);
		r_hand2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(r_hand2, -1.5708F, 1.5708F, 0.0F);
		hands_r.addChild(r_hand2);
		r_hand2.cubeList.add(new ModelBox(r_hand2, 38, 23, -3.0F, 12.0F, 5.0F, 8, 3, 4, 0.0F, false));

		r_hand3 = new ModelRenderer(this);
		r_hand3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(r_hand3, -1.5708F, 1.5708F, 0.0F);
		hands_r.addChild(r_hand3);
		r_hand3.cubeList.add(new ModelBox(r_hand3, 38, 23, 0.0F, 12.0F, -8.0F, 8, 3, 4, 0.0F, false));

		r_hand4 = new ModelRenderer(this);
		r_hand4.setRotationPoint(-2.0F, 6.0F, 7.0F);
		setRotationAngle(r_hand4, -1.5708F, 1.5708F, 0.0F);
		hands_r.addChild(r_hand4);
		r_hand4.cubeList.add(new ModelBox(r_hand4, 38, 23, 4.0F, 10.0F, -8.0F, 8, 3, 4, 0.0F, false));

		r_hand5 = new ModelRenderer(this);
		r_hand5.setRotationPoint(-3.0F, 4.0F, -5.0F);
		setRotationAngle(r_hand5, -1.5708F, 1.5708F, 0.0F);
		hands_r.addChild(r_hand5);
		r_hand5.cubeList.add(new ModelBox(r_hand5, 38, 23, 0.0F, 12.0F, -8.0F, 8, 3, 4, 0.0F, false));

		hands_l = new ModelRenderer(this);
		hands_l.setRotationPoint(0.0F, 0.0F, 0.0F);

		l_hand1 = new ModelRenderer(this);
		l_hand1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(l_hand1, -1.5708F, -1.5708F, 0.0F);
		hands_l.addChild(l_hand1);
		l_hand1.cubeList.add(new ModelBox(l_hand1, 38, 23, -12.0F, 18.0F, -1.0F, 8, 3, 4, 0.0F, true));

		l_hand2 = new ModelRenderer(this);
		l_hand2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(l_hand2, -1.5708F, -1.5708F, 0.0F);
		hands_l.addChild(l_hand2);
		l_hand2.cubeList.add(new ModelBox(l_hand2, 38, 23, -4.0F, 13.0F, 5.0F, 8, 3, 4, 0.0F, true));

		l_hand3 = new ModelRenderer(this);
		l_hand3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(l_hand3, -1.5708F, -1.5708F, 0.0F);
		hands_l.addChild(l_hand3);
		l_hand3.cubeList.add(new ModelBox(l_hand3, 38, 23, 0.0F, 13.0F, -8.0F, 8, 3, 4, 0.0F, true));

		l_hand4 = new ModelRenderer(this);
		l_hand4.setRotationPoint(3.0F, 6.0F, 4.0F);
		setRotationAngle(l_hand4, -1.5708F, -1.5708F, 0.0F);
		hands_l.addChild(l_hand4);
		l_hand4.cubeList.add(new ModelBox(l_hand4, 38, 23, 0.0F, 10.0F, -8.0F, 8, 3, 4, 0.0F, true));

		l_hand5 = new ModelRenderer(this);
		l_hand5.setRotationPoint(-2.0F, 3.0F, -10.0F);
		setRotationAngle(l_hand5, -1.5708F, -1.5708F, 0.0F);
		hands_l.addChild(l_hand5);
		l_hand5.cubeList.add(new ModelBox(l_hand5, 38, 23, 0.0F, 13.0F, -8.0F, 8, 3, 4, 0.0F, true));
	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
					   float headPitch, float scale) {
//		setRotationAngles( limbSwing, limbSwingAmount, ageInTicks, headPitch,netHeadYaw, scale, entityIn);
		float off = (float) (MathHelper.cos((float) (0.1*ageInTicks))*0.1);
		head.offsetY = off;
		body.offsetY = off;
		leftleg.offsetY = off;
		rightleg.offsetY = off;

		head.rotateAngleX = headPitch * 0.017453292F;
		head.rotateAngleY = netHeadYaw * 0.017453292F;

		body.render(scale);
		head.render(scale);
		leftleg.render(scale);
		rightleg.render(scale);
		hands_r.render(scale);
		hands_l.render(scale);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw, float rotatePitch, float scale, Entity entity, float power, float speed) {

//		float p1 = MathHelper.nextFloat(new Random(),1,2);

//		l_hand1.rotateAngleY = MathHelper.cos(speed*rotateFloat+(float)(MathHelper.nextFloat(new Random(),0,2)*Math.PI)) * MathHelper.nextFloat(new Random(),1,2) * power;
//		l_hand2.rotateAngleY = MathHelper.cos(speed*rotateFloat+(float)(MathHelper.nextFloat(new Random(),0,2)*Math.PI)) * MathHelper.nextFloat(new Random(),1,2)* power;
//		l_hand3.rotateAngleY = MathHelper.cos(speed*rotateFloat+(float)(MathHelper.nextFloat(new Random(),0,2)*Math.PI)) * MathHelper.nextFloat(new Random(),1,2) * power;
//		l_hand4.rotateAngleY = MathHelper.cos(speed*rotateFloat+(float)(MathHelper.nextFloat(new Random(),0,2)*Math.PI)) * MathHelper.nextFloat(new Random(),1,2) * power;
//		l_hand5.rotateAngleY = MathHelper.cos(speed*rotateFloat+(float)(MathHelper.nextFloat(new Random(),0,2)*Math.PI)) * MathHelper.nextFloat(new Random(),1,2) * power;
//		r_hand1.rotateAngleY = -MathHelper.cos(speed*rotateFloat+(float)(MathHelper.nextFloat(new Random(),0,2)*Math.PI)) * MathHelper.nextFloat(new Random(),1,2) * power;
//		r_hand2.rotateAngleY = -MathHelper.cos(speed*rotateFloat+(float)(MathHelper.nextFloat(new Random(),0,2)*Math.PI)) * MathHelper.nextFloat(new Random(),1,2) * power;
//		r_hand3.rotateAngleY = -MathHelper.cos(speed*rotateFloat+(float)(MathHelper.nextFloat(new Random(),0,2)*Math.PI)) * MathHelper.nextFloat(new Random(),1,2) * power;
//		r_hand4.rotateAngleY = -MathHelper.cos(speed*rotateFloat+(float)(MathHelper.nextFloat(new Random(),0,2)*Math.PI)) * MathHelper.nextFloat(new Random(),1,2) * power;
//		r_hand5.rotateAngleY = -MathHelper.cos(speed*rotateFloat+(float)(MathHelper.nextFloat(new Random(),0,2)*Math.PI))* MathHelper.nextFloat(new Random(),1,2) * power;
		l_hand1.rotateAngleY =-45 + MathHelper.cos(speed*rotateFloat*1.5f) * 1.2F * power;
		l_hand2.rotateAngleY =-45 + MathHelper.cos(speed*rotateFloat*1.5f+(float)Math.PI/3) * 1.4F * power;
		l_hand3.rotateAngleY =-45 + MathHelper.cos(speed*rotateFloat*1.5f+(float)(2*Math.PI/3)) * 1.6F * power;
		l_hand4.rotateAngleY =-45 + MathHelper.cos(speed*rotateFloat*1.5f+(float)(2.5*Math.PI/3)) * 1.3F * power;
		l_hand5.rotateAngleY =-45 + MathHelper.cos(speed*rotateFloat*1.5f+(float)(3*Math.PI/3)) * 1.3F * power;
		r_hand1.rotateAngleY = 45 - MathHelper.cos(speed*rotateFloat*1.5f+(float)(3.5*Math.PI/3)) * 1.2F * power;
		r_hand2.rotateAngleY = 45 - MathHelper.cos(speed*rotateFloat*1.5f+(float)(6*Math.PI/3)) * 1.1F * power;
		r_hand3.rotateAngleY = 45 - MathHelper.cos(speed*rotateFloat*1.5f+(float)(9*Math.PI/3)) * 1.2F * power;
		r_hand4.rotateAngleY = 45 - MathHelper.cos(speed*rotateFloat*1.5f+(float)(10*Math.PI/3)) * 0.7F * power;
		r_hand5.rotateAngleY = 45 - MathHelper.cos(speed*rotateFloat*1.5f+(float)(1.5*Math.PI/3) )* 1.6F * power;

		hands_l.rotateAngleX = rotatePitch * 0.017453292F;
		hands_l.rotateAngleY = rotateYaw * 0.017453292F;
		hands_r.rotateAngleX = rotatePitch * 0.017453292F;
		hands_r.rotateAngleY = rotateYaw * 0.017453292F;

	}

	@Override
	public void setPunch(float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw, float rotatePitch, float scale, Entity entity, float power, float speed) {
//		float r = (float) Math.random();
//		float offysin =r* MathHelper.sin(r*speed*rotateFloat)  * power-0.15f;
//		float offycos =r* MathHelper.cos(r *speed*rotateFloat)  * power-0.15f;
//		l_hand1.offsetZ =offysin;
//		l_hand2.offsetZ = offycos;
//		l_hand3.offsetZ = offysin;
//		l_hand4.offsetZ = offycos;
//		l_hand5.offsetZ = offysin;
//		r_hand1.offsetZ = offycos;
//		r_hand2.offsetZ = offysin;
//		r_hand3.offsetZ = offycos;
//		r_hand4.offsetZ = offysin;
//		r_hand5.offsetZ = offycos;
//		float offxl =r* MathHelper.sin(r*speed*rotateFloat)  * power+0.2f;
//		float offxr =r* MathHelper.sin(r*speed*rotateFloat)  * power-0.2f;
//		hands_l.offsetX = offxl;
//		hands_r.offsetX = offxr;
	}

	@Override
	public void doHandRender(float x, float y, float z, float scale, float alpha) {
		GlStateManager.translate(x, y, z);
		GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
		GlStateManager.rotate(180,0,0,1);
//		GlStateManager.rotate(90f,0,1,0);
//		GlStateManager.translate(0, 0, 25);
		hands_l.render(scale);
		hands_r.render(scale);
//		l_hand1.render(scale);
//		l_hand2.render(scale);
//		l_hand3.render(scale);
////		l_hand4.render(scale);
//		l_hand5.render(scale);
//		r_hand1.render(scale);
//		r_hand2.render(scale);
////		r_hand3.render(scale);
////		r_hand4.render(scale);
//		r_hand5.render(scale);

		GlStateManager.scale(1,1,1);
	}

	@Override
	public void effect(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

	}

	@Override
	public void extraEffect(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

	}

	@Override
	public void setPosition() {
		GlStateManager.translate(0.0F, 0.0F, -0.9F);
	}
}