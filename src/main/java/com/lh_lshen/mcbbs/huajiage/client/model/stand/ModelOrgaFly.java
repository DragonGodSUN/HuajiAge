package com.lh_lshen.mcbbs.huajiage.client.model.stand;// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelOrgaFly extends ModelStandBase {
	private final ModelRenderer head;
	private final ModelRenderer hair1;
	private final ModelRenderer hair2;
	private final ModelRenderer hair3;
	private final ModelRenderer hair4;
	private final ModelRenderer hair5;
	private final ModelRenderer hair6;
	private final ModelRenderer hair7;
	private final ModelRenderer hair8;
	private final ModelRenderer hair9;
	private final ModelRenderer hair10;
	private final ModelRenderer body;
	private final ModelRenderer leftArm;
	private final ModelRenderer lb_1;
	private final ModelRenderer lb_2;
	private final ModelRenderer rightArm;
	private final ModelRenderer rb_1;
	private final ModelRenderer rb_2;
	private final ModelRenderer rb_3;
	private final ModelRenderer leftLeg;
	private final ModelRenderer ll_1;
	private final ModelRenderer ll_2;
	private final ModelRenderer rightLeg;
	private final ModelRenderer rl_1;
	private final ModelRenderer rl_2;
	private final ModelRenderer Extra;
	private final ModelRenderer hair_p_1;
	private final ModelRenderer hair_p_2;
	private final ModelRenderer hair_p_3;
	private final ModelRenderer hair_p_4;
	private final ModelRenderer hair_p_5;
	private final ModelRenderer hair_p_6;
	private final ModelRenderer hair_p_7;

	public ModelOrgaFly() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 22.0F, 0.0F);
		setRotationAngle(head, 1.4835F, 0.0F, -0.3491F);
		head.cubeList.add(new ModelBox(head, 0, 0, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, true));

		hair1 = new ModelRenderer(this);
		hair1.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(hair1);
		hair1.cubeList.add(new ModelBox(hair1, 11, 0, -1.0F, -6.0F, -4.5F, 1, 1, 1, 0.0F, true));

		hair2 = new ModelRenderer(this);
		hair2.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(hair2);
		hair2.cubeList.add(new ModelBox(hair2, 33, 8, -4.0F, -8.0F, -4.2F, 7, 6, 0, 0.0F, true));

		hair3 = new ModelRenderer(this);
		hair3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(hair3, -0.0744F, 0.0F, 0.0F);
		head.addChild(hair3);
		hair3.cubeList.add(new ModelBox(hair3, 9, 1, -1.0F, -8.6333F, -4.8333F, 2, 4, 1, 0.0F, true));

		hair4 = new ModelRenderer(this);
		hair4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(hair4, -0.0744F, -0.0372F, -0.5205F);
		head.addChild(hair4);
		hair4.cubeList.add(new ModelBox(hair4, 10, 1, 0.2F, -8.6F, -4.8F, 2, 4, 1, 0.0F, true));

		hair5 = new ModelRenderer(this);
		hair5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(hair5, -0.1487F, 0.0372F, 0.632F);
		head.addChild(hair5);
		hair5.cubeList.add(new ModelBox(hair5, 11, 4, -3.2667F, -8.0F, -5.2667F, 2, 4, 1, 0.0F, true));

		hair6 = new ModelRenderer(this);
		hair6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(hair6, 0.1859F, 0.0F, 0.0F);
		head.addChild(hair6);
		hair6.cubeList.add(new ModelBox(hair6, 0, 51, -3.0F, -8.6F, -2.0667F, 6, 2, 7, 0.0F, true));

		hair7 = new ModelRenderer(this);
		hair7.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(hair7, 0.1487F, 0.1487F, 0.5205F);
		head.addChild(hair7);
		hair7.cubeList.add(new ModelBox(hair7, 31, 51, -2.0667F, -8.5333F, -1.4F, 2, 2, 6, 0.0F, true));

		hair8 = new ModelRenderer(this);
		hair8.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(hair8, 0.1859F, -0.2603F, -0.409F);
		head.addChild(hair8);
		hair8.cubeList.add(new ModelBox(hair8, 31, 51, -0.8667F, -8.5333F, -1.0F, 2, 2, 6, 0.0F, true));

		hair9 = new ModelRenderer(this);
		hair9.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(hair9, 0.3346F, 0.0F, 0.0F);
		head.addChild(hair9);
		hair9.cubeList.add(new ModelBox(hair9, 0, 40, -2.0333F, -8.6667F, -0.4667F, 4, 3, 7, 0.0F, true));

		hair10 = new ModelRenderer(this);
		hair10.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(hair10, 0.5577F, 0.0F, 0.0F);
		head.addChild(hair10);
		hair10.cubeList.add(new ModelBox(hair10, 31, 40, -1.0F, -8.9F, 1.5333F, 2, 3, 5, 0.0F, true));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 22.0F, 0.0F);
		setRotationAngle(body, 1.5708F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 16, 16, -3.3572F, -0.0668F, -1.2369F, 8, 12, 4, 0.0F, true));

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-4.0F, 2.0F, 0.0F);
		setRotationAngle(leftArm, 0.0F, 0.0F, 0.8727F);
		body.addChild(leftArm);

		lb_1 = new ModelRenderer(this);
		lb_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(lb_1, 0.0F, 0.0F, -0.3491F);
		leftArm.addChild(lb_1);
		lb_1.cubeList.add(new ModelBox(lb_1, 40, 16, -3.0F, -2.0F, -2.0F, 4, 8, 4, 0.0F, false));

		lb_2 = new ModelRenderer(this);
		lb_2.setRotationPoint(-1.0F, 5.0F, 0.0F);
		setRotationAngle(lb_2, 0.0F, 0.0F, -0.5236F);
		lb_1.addChild(lb_2);
		lb_2.cubeList.add(new ModelBox(lb_2, 48, 34, -2.0F, -1.0F, -2.0F, 4, 7, 4, 0.0F, false));

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(5.0F, 3.0F, 3.0F);
		body.addChild(rightArm);

		rb_1 = new ModelRenderer(this);
		rb_1.setRotationPoint(1.0F, -3.0F, -2.0F);
		setRotationAngle(rb_1, 3.1416F, -0.0873F, 1.0472F);
		rightArm.addChild(rb_1);
		rb_1.cubeList.add(new ModelBox(rb_1, 40, 16, -1.5F, -3.0F, -2.0F, 4, 6, 4, 0.0F, true));

		rb_2 = new ModelRenderer(this);
		rb_2.setRotationPoint(1.0F, 2.0F, 0.0F);
		setRotationAngle(rb_2, 0.6109F, -1.658F, 0.4363F);
		rb_1.addChild(rb_2);
		rb_2.cubeList.add(new ModelBox(rb_2, 48, 34, -1.5F, 0.0F, -2.0F, 4, 7, 4, 0.0F, true));

		rb_3 = new ModelRenderer(this);
		rb_3.setRotationPoint(1.0F, 2.0F, 0.0F);
		setRotationAngle(rb_3, 0.6109F, -1.658F, 0.4363F);
		rb_1.addChild(rb_3);
		rb_3.cubeList.add(new ModelBox(rb_3, 58, 27, -0.5F, 7.0F, 0.5F, 2, 3, 1, 0.0F, true));

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(-1.9F, 11.0F, 0.0F);
		setRotationAngle(leftLeg, 0.0F, 0.0F, -0.1745F);
		body.addChild(leftLeg);

		ll_1 = new ModelRenderer(this);
		ll_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(ll_1, 0.3491F, 1.6581F, 1.0472F);
		leftLeg.addChild(ll_1);
		ll_1.cubeList.add(new ModelBox(ll_1, 0, 16, -2.0F, 0.0F, -2.0F, 4, 6, 4, 0.0F, false));

		ll_2 = new ModelRenderer(this);
		ll_2.setRotationPoint(0.0F, 11.5F, 0.0F);
		setRotationAngle(ll_2, 0.5236F, -0.2618F, 0.0F);
		ll_1.addChild(ll_2);
		ll_2.cubeList.add(new ModelBox(ll_2, 48, 49, -2.1F, -5.5F, 1.0F, 4, 9, 4, 0.0F, false));

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(2.9F, 11.0F, 0.0F);
		body.addChild(rightLeg);

		rl_1 = new ModelRenderer(this);
		rl_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		rightLeg.addChild(rl_1);
		rl_1.cubeList.add(new ModelBox(rl_1, 0, 16, -2.0F, 0.0F, -2.0F, 4, 7, 4, 0.0F, true));

		rl_2 = new ModelRenderer(this);
		rl_2.setRotationPoint(0.0F, 7.0F, 0.0F);
		setRotationAngle(rl_2, 0.2618F, 0.0F, 0.0F);
		rl_1.addChild(rl_2);
		rl_2.cubeList.add(new ModelBox(rl_2, 48, 49, -1.9F, -1.0F, -2.0F, 4, 9, 4, 0.0F, true));

		Extra = new ModelRenderer(this);
		Extra.setRotationPoint(0.0F, 22.0F, -14.0F);
		setRotationAngle(Extra, 1.5708F, 0.0F, 0.0F);

		hair_p_1 = new ModelRenderer(this);
		hair_p_1.setRotationPoint(-8.5F, 1.0F, -11.0F);
		setRotationAngle(hair_p_1, -0.9599F, 0.0873F, 0.0F);
		Extra.addChild(hair_p_1);
		hair_p_1.cubeList.add(new ModelBox(hair_p_1, 41, 9, -2.5F, -3.0F, 0.0F, 5, 6, 0, 0.0F, false));

		hair_p_2 = new ModelRenderer(this);
		hair_p_2.setRotationPoint(2.5F, 1.0F, -11.0F);
		setRotationAngle(hair_p_2, -0.9599F, -0.3491F, 0.0F);
		Extra.addChild(hair_p_2);
		hair_p_2.cubeList.add(new ModelBox(hair_p_2, 41, 9, -2.5F, -3.0F, 0.0F, 5, 6, 0, 0.0F, false));

		hair_p_3 = new ModelRenderer(this);
		hair_p_3.setRotationPoint(10.5F, 2.0F, -7.0F);
		setRotationAngle(hair_p_3, -0.9599F, -1.5708F, 0.0F);
		Extra.addChild(hair_p_3);
		hair_p_3.cubeList.add(new ModelBox(hair_p_3, 41, 9, -2.5F, -3.0F, 0.0F, 5, 6, 0, 0.0F, false));

		hair_p_4 = new ModelRenderer(this);
		hair_p_4.setRotationPoint(10.5F, 3.0F, 6.0F);
		setRotationAngle(hair_p_4, -0.9599F, -2.618F, 0.0F);
		Extra.addChild(hair_p_4);
		hair_p_4.cubeList.add(new ModelBox(hair_p_4, 41, 9, -2.5F, -3.0F, 0.0F, 5, 6, 0, 0.0F, false));

		hair_p_5 = new ModelRenderer(this);
		hair_p_5.setRotationPoint(1.5F, 3.0F, 13.0F);
		setRotationAngle(hair_p_5, -0.9599F, 2.1817F, 0.0F);
		Extra.addChild(hair_p_5);
		hair_p_5.cubeList.add(new ModelBox(hair_p_5, 41, 9, -2.5F, -3.0F, 0.0F, 5, 6, 0, 0.0F, false));

		hair_p_6 = new ModelRenderer(this);
		hair_p_6.setRotationPoint(-10.5F, 3.0F, 9.0F);
		setRotationAngle(hair_p_6, -0.9599F, 1.309F, 0.0F);
		Extra.addChild(hair_p_6);
		hair_p_6.cubeList.add(new ModelBox(hair_p_6, 41, 9, -2.5F, -3.0F, 0.0F, 5, 6, 0, 0.0F, false));

		hair_p_7 = new ModelRenderer(this);
		hair_p_7.setRotationPoint(-15.5F, 3.0F, -3.0F);
		setRotationAngle(hair_p_7, -0.9599F, 0.6109F, 0.0F);
		Extra.addChild(hair_p_7);
		hair_p_7.cubeList.add(new ModelBox(hair_p_7, 41, 9, -2.5F, -3.0F, 0.0F, 5, 6, 0, 0.0F, false));
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
		   		float headPitch, float scale) {
		setRotationAngles( limbSwing, limbSwingAmount, ageInTicks, headPitch,netHeadYaw, scale, entityIn);
		float off = (float) (MathHelper.cos((float) (0.1*ageInTicks))*0.15);
		head.offsetY = off;
		body.offsetY = off;
		Extra.offsetY = off;

		Extra.rotateAngleZ = ageInTicks/2;

//		head.rotateAngleX += headPitch * 0.017453292F;
//		head.rotateAngleY += netHeadYaw * 0.017453292F;

		head.render(scale);
		body.render(scale);
		Extra.render(scale);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw, float rotatePitch, float scale, Entity entity, float power, float speed) {
		super.setRotationAngles(limbSwing, limbSwingAmount, rotateFloat, rotateYaw, rotatePitch, scale, entity);
		Extra.rotateAngleZ = rotateFloat/2;
	}

	@Override
	public void setPunch(float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw, float rotatePitch, float scale, Entity entity, float power, float speed) {


	}

	@Override
	public void renderFirst(float x, float y, float z, float scale, float alpha) {
		GlStateManager.rotate(180,0,0,1);
		GlStateManager.translate(x,y,z);
		GlStateManager.rotate(-35,1,0,0);
		head.render(scale);
		body.render(scale);
		Extra.render(scale);

	}

	@Override
	public void effect(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

	}

	@Override
	public void extraEffect(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

	}

	@Override
	public void setPosition() {
		GlStateManager.translate(0.0F, -0.9F, 0.0F);
	}
}