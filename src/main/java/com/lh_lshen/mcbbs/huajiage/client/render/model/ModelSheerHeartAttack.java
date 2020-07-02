package com.lh_lshen.mcbbs.huajiage.client.render.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

public class ModelSheerHeartAttack extends ModelBase {
	private final ModelRenderer head;
	private final ModelRenderer ear;
	private final ModelRenderer ear_1;
	private final ModelRenderer ear_2;
	private final ModelRenderer body;
	private final ModelRenderer w_1;
	private final ModelRenderer w_2;

	public ModelSheerHeartAttack() {
		textureWidth = 128;
		textureHeight = 128;

		head = new ModelRenderer(this);
		head.setRotationPoint(1.0F, 21.0F, -5.0F);
		head.cubeList.add(new ModelBox(head, 34, 0, -3.5F, -5.0F, -2.0F, 5, 5, 3, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 0, 61, -3.0F, -4.1F, -1.8F, 4, 4, 3, 0.0F, false));

		ear = new ModelRenderer(this);
		ear.setRotationPoint(-1.0F, -5.0F, 0.0F);
		setRotationAngle(ear, -0.4363F, 0.0F, 0.0F);
		head.addChild(ear);

		ear_1 = new ModelRenderer(this);
		ear_1.setRotationPoint(-2.5F, 0.0F, 0.0F);
		setRotationAngle(ear_1, -0.1745F, 0.4363F, -0.5236F);
		ear.addChild(ear_1);
		ear_1.cubeList.add(new ModelBox(ear_1, 58, 5, -0.5F, -1.0F, 0.0F, 1, 2, 1, 0.0F, false));

		ear_2 = new ModelRenderer(this);
		ear_2.setRotationPoint(2.5F, 0.0F, 0.0F);
		setRotationAngle(ear_2, -0.1745F, -0.4363F, 0.5236F);
		ear.addChild(ear_2);
		ear_2.cubeList.add(new ModelBox(ear_2, 58, 5, -0.5F, -1.0F, 0.0F, 1, 2, 1, 0.0F, true));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -5.0F, -11.0F, -5.0F, 10, 9, 14, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 36, 27, -2.0F, -12.0F, 0.0F, 4, 1, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 17, 42, -8.0F, -6.0F, -3.0F, 3, 4, 11, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 37, 12, 5.0F, -6.0F, -3.0F, 3, 4, 11, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 18, 25, 4.0F, -8.0F, -2.0F, 3, 2, 12, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 23, -7.0F, -8.0F, -2.0F, 3, 2, 12, 0.0F, false));

		w_1 = new ModelRenderer(this);
		w_1.setRotationPoint(6.0F, 22.0F, 0.0F);
		w_1.cubeList.add(new ModelBox(w_1, 37, 37, -2.0F, -3.0F, -4.0F, 3, 5, 11, 0.0F, false));
		w_1.cubeList.add(new ModelBox(w_1, 0, 4, -1.5F, -3.5F, -4.5F, 3, 2, 2, 0.0F, false));

		w_2 = new ModelRenderer(this);
		w_2.setRotationPoint(-6.0F, 22.0F, 0.0F);
		w_2.cubeList.add(new ModelBox(w_2, 0, 37, -1.0F, -3.0F, -4.0F, 3, 5, 11, 0.0F, false));
		w_2.cubeList.add(new ModelBox(w_2, 0, 0, -1.5F, -3.5F, -4.5F, 3, 2, 2, 0.0F, false));
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
	    head.rotateAngleX = headPitch * 0.017453292F;
	    head.rotateAngleY = netHeadYaw * 0.017453292F;
		head.render(scale);
		body.render(scale);
		w_1.render(scale);
		w_2.render(scale);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}