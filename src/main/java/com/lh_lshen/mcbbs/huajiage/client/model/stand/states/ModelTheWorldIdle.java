package com.lh_lshen.mcbbs.huajiage.client.model.stand.states;// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelTheWorldIdle extends ModelStandBase {
	private final ModelRenderer head;
	private final ModelRenderer Shape1;
	private final ModelRenderer Shape2;
	private final ModelRenderer Shape3;
	private final ModelRenderer Shape4;
	private final ModelRenderer Shape5;
	private final ModelRenderer Shape6;
	private final ModelRenderer Shape7;
	private final ModelRenderer body;
	private final ModelRenderer bodydown;
	private final ModelRenderer Shape8;
	private final ModelRenderer Shape9;
	private final ModelRenderer Shape10;
	private final ModelRenderer Shape11;
	private final ModelRenderer shape12;
	private final ModelRenderer Shape13;
	private final ModelRenderer Shape14;
	private final ModelRenderer back1;
	private final ModelRenderer back2;
	private final ModelRenderer leftarm;
	private final ModelRenderer handl;
	private final ModelRenderer rightarm;
	private final ModelRenderer handr;
	private final ModelRenderer leftleg;
	private final ModelRenderer legdownl;
	private final ModelRenderer rightleg;
	private final ModelRenderer legdownr;
	private final ModelRenderer right_hands;

	public ModelTheWorldIdle() {
		textureWidth = 64;
		textureHeight = 128;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -3.5F, -6.0F, -4.0F, 7, 6, 8, 0.0F, false));

		Shape1 = new ModelRenderer(this);
		Shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Shape1, 0.4014F, 0.0F, 0.0F);
		head.addChild(Shape1);
		Shape1.cubeList.add(new ModelBox(Shape1, 0, 35, -4.5F, -8.0F, -1.0F, 9, 3, 9, 0.0F, false));

		Shape2 = new ModelRenderer(this);
		Shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Shape2, 0.0524F, 0.0F, 0.0F);
		head.addChild(Shape2);
		Shape2.cubeList.add(new ModelBox(Shape2, 0, 49, -4.5F, -7.2F, -3.0F, 9, 4, 8, 0.0F, false));

		Shape3 = new ModelRenderer(this);
		Shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Shape3, 0.0F, 1.0996F, 0.0F);
		head.addChild(Shape3);
		Shape3.cubeList.add(new ModelBox(Shape3, 0, 63, 2.8F, -7.0F, -3.0F, 3, 4, 6, 0.0F, false));

		Shape4 = new ModelRenderer(this);
		Shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Shape4, 0.0F, -1.0996F, 0.0F);
		head.addChild(Shape4);
		Shape4.cubeList.add(new ModelBox(Shape4, 0, 63, -5.8F, -7.0F, -3.0F, 3, 4, 6, 0.0F, true));

		Shape5 = new ModelRenderer(this);
		Shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Shape5, -0.1505F, 0.0F, 0.0F);
		head.addChild(Shape5);
		Shape5.cubeList.add(new ModelBox(Shape5, 0, 75, -4.5F, -4.0F, 1.0F, 9, 2, 4, 0.0F, false));

		Shape6 = new ModelRenderer(this);
		Shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Shape6, 0.0F, 0.0F, 0.7854F);
		head.addChild(Shape6);
		Shape6.cubeList.add(new ModelBox(Shape6, 0, 30, -1.0F, 0.0F, -5.0F, 2, 1, 1, 0.0F, false));

		Shape7 = new ModelRenderer(this);
		Shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Shape7, 0.0F, 0.0F, 0.7854F);
		head.addChild(Shape7);
		Shape7.cubeList.add(new ModelBox(Shape7, 0, 30, 0.0F, -1.0F, -5.0F, 1, 1, 1, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(body, 0.1746F, -0.3491F, -0.3491F);
		body.cubeList.add(new ModelBox(body, 16, 16, -4.0F, 0.0F, -2.0F, 8, 7, 4, 0.0F, false));

		bodydown = new ModelRenderer(this);
		bodydown.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bodydown, 0.0873F, 0.0F, 0.0F);
		body.addChild(bodydown);
		bodydown.cubeList.add(new ModelBox(bodydown, 19, 66, -3.5F, 7.0F, -2.0F, 7, 4, 4, 0.0F, false));

		Shape8 = new ModelRenderer(this);
		Shape8.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Shape8, 0.0873F, 0.0F, 0.0F);
		body.addChild(Shape8);
		Shape8.cubeList.add(new ModelBox(Shape8, 37, 30, 1.0F, -1.0F, -3.0F, 2, 11, 6, 0.0F, false));

		Shape9 = new ModelRenderer(this);
		Shape9.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Shape9, 0.0873F, 0.0F, 0.0F);
		body.addChild(Shape9);
		Shape9.cubeList.add(new ModelBox(Shape9, 37, 30, -3.0F, -1.0F, -3.0F, 2, 11, 6, 0.0F, true));

		Shape10 = new ModelRenderer(this);
		Shape10.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Shape10, 0.0873F, 0.0F, 0.0F);
		body.addChild(Shape10);
		Shape10.cubeList.add(new ModelBox(Shape10, 35, 49, -3.5F, 0.2F, -2.5F, 7, 4, 1, 0.0F, false));

		Shape11 = new ModelRenderer(this);
		Shape11.setRotationPoint(0.0F, 0.0F, -2.3F);
		setRotationAngle(Shape11, 0.0873F, 0.0F, 0.0F);
		body.addChild(Shape11);
		Shape11.cubeList.add(new ModelBox(Shape11, 35, 56, -1.5F, 4.2F, 0.0F, 3, 3, 1, 0.0F, false));

		shape12 = new ModelRenderer(this);
		shape12.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(shape12, 0.0873F, 0.0F, 0.0F);
		body.addChild(shape12);
		shape12.cubeList.add(new ModelBox(shape12, 16, 82, -4.0F, 10.0F, -3.5F, 8, 2, 7, 0.0F, false));

		Shape13 = new ModelRenderer(this);
		Shape13.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Shape13, 0.0873F, 0.0F, 0.7854F);
		body.addChild(Shape13);
		Shape13.cubeList.add(new ModelBox(Shape13, 0, 30, 7.0F, 8.0F, -3.5F, 2, 1, 1, 0.0F, false));

		Shape14 = new ModelRenderer(this);
		Shape14.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Shape14, 0.0873F, 0.0F, 0.7854F);
		body.addChild(Shape14);
		Shape14.cubeList.add(new ModelBox(Shape14, 0, 30, 8.0F, 7.0F, -3.5F, 1, 1, 1, 0.0F, false));

		back1 = new ModelRenderer(this);
		back1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(back1, 0.0873F, 0.0F, 0.0F);
		body.addChild(back1);
		back1.cubeList.add(new ModelBox(back1, 0, 83, 0.5F, 0.0F, 3.0F, 2, 7, 2, 0.0F, false));

		back2 = new ModelRenderer(this);
		back2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(back2, 0.0873F, 0.0F, 0.0F);
		body.addChild(back2);
		back2.cubeList.add(new ModelBox(back2, 0, 83, -2.5F, 0.0F, 3.0F, 2, 7, 2, 0.0F, true));

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(5.0F, 2.0F, 0.0F);
		setRotationAngle(leftarm, 0.6981F, 0.5236F, -0.6109F);
		body.addChild(leftarm);
		leftarm.cubeList.add(new ModelBox(leftarm, 40, 16, -1.0F, -2.0F, -2.0F, 4, 4, 5, 0.0F, false));
		leftarm.cubeList.add(new ModelBox(leftarm, 49, 27, -1.0F, 2.0F, -1.5F, 3, 4, 4, 0.0F, false));

		handl = new ModelRenderer(this);
		handl.setRotationPoint(2.0F, 7.0F, 2.0F);
		setRotationAngle(handl, -0.6109F, 0.0F, 0.0F);
		leftarm.addChild(handl);
		handl.cubeList.add(new ModelBox(handl, 48, 4, -3.2F, -1.0F, -4.0F, 4, 8, 4, 0.0F, false));

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		setRotationAngle(rightarm, -1.1345F, 0.2618F, 1.0472F);
		body.addChild(rightarm);
		rightarm.cubeList.add(new ModelBox(rightarm, 40, 16, -3.0F, -2.0F, -2.0F, 4, 4, 5, 0.0F, true));
		rightarm.cubeList.add(new ModelBox(rightarm, 49, 27, -2.5F, 2.0F, -1.5F, 3, 4, 4, 0.0F, true));

		handr = new ModelRenderer(this);
		handr.setRotationPoint(-1.0F, 6.0F, 1.0F);
		setRotationAngle(handr, -0.0873F, -0.5236F, 0.0873F);
		rightarm.addChild(handr);
		handr.cubeList.add(new ModelBox(handr, 48, 4, -2.0F, 0.0F, -2.0F, 4, 8, 4, 0.0F, true));

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(6.0F, 11.0F, 5.0F);
		setRotationAngle(leftleg, -0.5236F, -0.4363F, -0.4712F);
		leftleg.cubeList.add(new ModelBox(leftleg, 21, 100, -2.0F, 0.0F, -3.0F, 5, 7, 5, 0.0F, false));

		legdownl = new ModelRenderer(this);
		legdownl.setRotationPoint(1.1276F, 4.9224F, 1.6341F);
		setRotationAngle(legdownl, 1.6487F, 0.0F, 0.1745F);
		leftleg.addChild(legdownl);
		legdownl.cubeList.add(new ModelBox(legdownl, 0, 100, -2.2773F, -1.4722F, -3.5434F, 5, 9, 5, 0.0F, false));

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(1.0F, 11.0F, 2.0F);
		setRotationAngle(rightleg, 0.2618F, -0.2618F, -0.576F);
		rightleg.cubeList.add(new ModelBox(rightleg, 21, 100, -3.0F, 0.0F, -3.0F, 5, 7, 5, 0.0F, true));

		legdownr = new ModelRenderer(this);
		legdownr.setRotationPoint(-0.4521F, 7.6393F, 0.1241F);
		setRotationAngle(legdownr, 0.427F, 0.0872F, 0.0F);
		rightleg.addChild(legdownr);
		legdownr.cubeList.add(new ModelBox(legdownr, 0, 100, -2.5F, -1.5F, -1.5F, 5, 9, 5, 0.0F, false));

		right_hands = new ModelRenderer(this);
		right_hands.setRotationPoint(0.0F, 5.0F, 2.0F);
	}

	@Override
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

	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw, float rotatePitch, float scale, Entity entity, float power, float speed) {

	}

	@Override
	public void setPunch(float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw, float rotatePitch, float scale, Entity entity, float power, float speed) {

	}

	@Override
	public void doHandRender(float x, float y, float z, float scale, float alpha) {

	}

	@Override
	public void effect(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

	}

	@Override
	public void extraEffect(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

	}

	@Override
	public void setPosition() {
		GlStateManager.translate(-0.7F, -0.2F, 0.45F);
	}
}