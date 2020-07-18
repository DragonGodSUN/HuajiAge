package com.lh_lshen.mcbbs.huajiage.client.model.stand;

import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelKillerQueen extends ModelStandBase {
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
	private final ModelRenderer la_up;
	private final ModelRenderer la_down;
	private final ModelRenderer rightarm;
	private final ModelRenderer ra_up;
	private final ModelRenderer ra_down;
	private final ModelRenderer head;
	private final ModelRenderer leftleg;
	private final ModelRenderer legdownl;
	private final ModelRenderer rightleg;
	private final ModelRenderer legdownr;

	public ModelKillerQueen() {
		textureWidth = 128;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, -1.0F, 0.0F);
		setRotationAngle(body, -0.1746F, 0.0F, 0.0F);
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
		cloth1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(cloth1, -0.1487F, 0.0F, 0.0F);
		cloth.addChild(cloth1);
		cloth1.cubeList.add(new ModelBox(cloth1, 0, 4, -1.0F, 11.9723F, -1.6662F, 2, 3, 1, 0.0F, false));

		cloth2 = new ModelRenderer(this);
		cloth2.setRotationPoint(0.0F, 9.9128F, 0.9962F);
		setRotationAngle(cloth2, 0.5479F, 0.0F, 0.0F);
		cloth.addChild(cloth2);
		cloth2.cubeList.add(new ModelBox(cloth2, 42, 30, -4.5F, 0.0F, 1.0F, 9, 5, 1, 0.0F, false));

		cloth3 = new ModelRenderer(this);
		cloth3.setRotationPoint(-3.0F, 10.9128F, 0.9962F);
		setRotationAngle(cloth3, -0.063F, 0.0873F, 1.1345F);
		cloth.addChild(cloth3);
		cloth3.cubeList.add(new ModelBox(cloth3, 46, 49, -2.0F, -0.4128F, -4.0F, 1, 5, 6, 0.0F, false));

		cloth4 = new ModelRenderer(this);
		cloth4.setRotationPoint(3.0F, 9.9128F, 0.9962F);
		setRotationAngle(cloth4, -0.063F, -0.0873F, -0.7854F);
		cloth.addChild(cloth4);
		cloth4.cubeList.add(new ModelBox(cloth4, 32, 49, 0.5F, 0.0F, -4.0F, 1, 5, 6, 0.0F, false));

		cloth5 = new ModelRenderer(this);
		cloth5.setRotationPoint(3.0F, 9.9128F, 0.9962F);
		setRotationAngle(cloth5, -0.2375F, -0.0873F, -0.4363F);
		cloth.addChild(cloth5);
		cloth5.cubeList.add(new ModelBox(cloth5, 0, 57, -3.5F, 0.0F, -4.0F, 5, 5, 1, 0.0F, false));

		cloth6 = new ModelRenderer(this);
		cloth6.setRotationPoint(-3.0F, 9.9128F, 0.9962F);
		setRotationAngle(cloth6, -0.2375F, 0.0873F, 0.6981F);
		cloth.addChild(cloth6);
		cloth6.cubeList.add(new ModelBox(cloth6, 40, 49, -1.5F, 0.0F, -4.0F, 5, 5, 1, 0.0F, false));

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

		la_up = new ModelRenderer(this);
		la_up.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(la_up, -0.6109F, 0.6109F, 0.2618F);
		leftarm.addChild(la_up);
		la_up.cubeList.add(new ModelBox(la_up, 14, 53, 0.0F, 1.0F, -0.5F, 3, 6, 4, 0.0F, false));

		la_down = new ModelRenderer(this);
		la_down.setRotationPoint(0.0F, 7.0F, 1.5F);
		setRotationAngle(la_down, 0.1745F, 0.0873F, 0.6981F);
		la_up.addChild(la_down);
		la_down.cubeList.add(new ModelBox(la_down, 20, 15, 0.0F, -4.0F, -6.5F, 3, 4, 8, 0.0F, false));

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-6.0F, 2.0F, 0.5F);
		setRotationAngle(rightarm, -0.6109F, 0.0F, 0.0F);
		body.addChild(rightarm);
		rightarm.cubeList.add(new ModelBox(rightarm, 47, 0, -2.0F, -2.0F, -2.5F, 4, 4, 5, 0.0F, false));

		ra_up = new ModelRenderer(this);
		ra_up.setRotationPoint(1.0F, 1.0F, -0.5F);
		setRotationAngle(ra_up, -0.2618F, -0.2618F, 0.1745F);
		rightarm.addChild(ra_up);
		ra_up.cubeList.add(new ModelBox(ra_up, 52, 9, -2.0F, 1.0F, -1.0F, 3, 6, 4, 0.0F, false));

		ra_down = new ModelRenderer(this);
		ra_down.setRotationPoint(1.0F, 5.0F, -1.0F);
		setRotationAngle(ra_down, -0.9599F, -0.0873F, 0.0873F);
		ra_up.addChild(ra_down);
		ra_down.cubeList.add(new ModelBox(ra_down, 38, 23, -1.0F, -3.0F, 0.0F, 8, 3, 4, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, -1.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -3.5F, -7.0F, -4.0F, 7, 7, 8, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 55, 55, 2.5F, -9.0F, -3.0F, 1, 2, 5, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 22, 0, -2.5F, -8.0F, -3.5F, 5, 1, 7, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 54, 44, -3.5F, -9.0F, -3.0F, 1, 2, 5, 0.0F, false));

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(2.0F, 12.0F, -1.0F);
		setRotationAngle(leftleg, -0.2876F, -0.3992F, -0.4341F);
		leftleg.cubeList.add(new ModelBox(leftleg, 37, 37, -2.0F, 0.0F, -3.5F, 5, 7, 5, 0.0F, false));

		legdownl = new ModelRenderer(this);
		legdownl.setRotationPoint(1.1629F, 10.25F, 1.1822F);
		setRotationAngle(legdownl, 0.5871F, -0.0501F, 0.0259F);
		leftleg.addChild(legdownl);
		legdownl.cubeList.add(new ModelBox(legdownl, 34, 8, -2.6629F, -4.5F, -2.1822F, 4, 9, 5, 0.0F, false));

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(-2.0F, 10.0F, 0.0F);
		setRotationAngle(rightleg, -0.3635F, 0.3377F, 0.2482F);
		rightleg.cubeList.add(new ModelBox(rightleg, 0, 34, -3.0F, 1.0F, -3.5F, 5, 7, 5, 0.0F, false));

		legdownr = new ModelRenderer(this);
		legdownr.setRotationPoint(0.6913F, 9.3405F, 1.5427F);
		setRotationAngle(legdownr, 0.9506F, 0.2473F, 0.0614F);
		rightleg.addChild(legdownr);
		legdownr.cubeList.add(new ModelBox(legdownr, 24, 27, -1.6913F, -4.3405F, -2.5427F, 4, 9, 5, 0.0F, false));
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
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
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw,
			float rotatePitch, float scale, Entity entity, float power, float speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPunch(float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw, float rotatePitch,
			float scale, Entity entity, float power, float speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doHandRender(float x, float y, float z, float scale, float alpha) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void effect(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void extraEffect(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPosition() {
		GlStateManager.translate(0.9F, -0.1F, -0.8F);
		
	}
}