package com.lh_lshen.mcbbs.huajiage.client.render.model;
// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelNewLandMan extends ModelBase {
	private final ModelRenderer head;
	private final ModelRenderer body;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer basePlate;

	public ModelNewLandMan() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 3.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 14, 34, -1.0F, -7.0F, -1.0F, 2, 7, 2, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 2.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 13, -5.0F, 0.0F, -1.5F, 12, 3, 3, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 28, 17, 1.0F, 3.0F, -1.0F, 2, 7, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 0, -3.0F, 3.0F, -1.0F, 2, 7, 2, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 19, -5.0F, 10.0F, -1.0F, 8, 2, 2, 0.0F, false));

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		setRotationAngle(rightArm, 0.0873F, 0.0F, -2.5307F);
		rightArm.cubeList.add(new ModelBox(rightArm, 0, 23, -2.0F, -2.0F, -1.0F, 2, 12, 2, 0.0F, false));

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-6.0F, 5.0F, 0.0F);
		setRotationAngle(leftArm, 0.0F, 0.0F, -2.7925F);
		leftArm.cubeList.add(new ModelBox(leftArm, 20, 20, -1.0F, 1.0F, -1.0F, 2, 12, 2, 0.0F, false));

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(1.9F, 13.0F, 0.0F);
		setRotationAngle(rightLeg, 0.0F, 0.0F, -0.3491F);
		rightLeg.cubeList.add(new ModelBox(rightLeg, 28, 28, -1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F, false));

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(-4.9F, 12.0F, 0.0F);
		setRotationAngle(leftLeg, 0.0F, 0.0F, -0.2618F);
		leftLeg.cubeList.add(new ModelBox(leftLeg, 8, 23, -1.0F, 0.0F, -1.0F, 2, 11, 2, 0.0F, false));

		basePlate = new ModelRenderer(this);
		basePlate.setRotationPoint(0.0F, 24.0F, 0.0F);
		basePlate.cubeList.add(new ModelBox(basePlate, 0, 0, -6.0F, -1.0F, -6.0F, 12, 1, 12, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		body.render(f5);
		rightArm.render(f5);
		leftArm.render(f5);
		rightLeg.render(f5);
		leftLeg.render(f5);
		basePlate.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}