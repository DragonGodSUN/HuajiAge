package com.lh_lshen.mcbbs.huajiage.client.model.stand.states;// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelStarPlatinumIdle extends ModelStandBase {
	private final ModelRenderer body;
	private final ModelRenderer Shape11;
	private final ModelRenderer bodydown;
	private final ModelRenderer cloth1;
	private final ModelRenderer cloth2;
	private final ModelRenderer crotch;
	private final ModelRenderer leftarm;
	private final ModelRenderer handl;
	private final ModelRenderer armorl;
	private final ModelRenderer rightarm;
	private final ModelRenderer handr;
	private final ModelRenderer armorr;
	private final ModelRenderer scarf;
	private final ModelRenderer scarf2;
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
	private final ModelRenderer hands;

	public ModelStarPlatinumIdle() {
		textureWidth = 64;
		textureHeight = 128;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(body, 0.0F, 0.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 16, 16, -4.0F, 0.0F, -2.0F, 8, 7, 4, 0.0F, false));

		Shape11 = new ModelRenderer(this);
		Shape11.setRotationPoint(0.0F, 0.0F, -2.3F);
		setRotationAngle(Shape11, -0.0873F, 0.0F, 0.0F);
		body.addChild(Shape11);
		Shape11.cubeList.add(new ModelBox(Shape11, 35, 56, -1.5F, 4.0F, 0.0F, 3, 3, 1, 0.0F, false));

		bodydown = new ModelRenderer(this);
		bodydown.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bodydown, -0.0175F, 0.0F, 0.0F);
		body.addChild(bodydown);
		bodydown.cubeList.add(new ModelBox(bodydown, 19, 66, -3.5F, 7.0F, -2.0F, 7, 4, 4, 0.0F, false));

		cloth1 = new ModelRenderer(this);
		cloth1.setRotationPoint(0.0F, 11.8157F, -4.3943F);
		setRotationAngle(cloth1, -0.0614F, 0.0F, 0.0F);
		body.addChild(cloth1);
		cloth1.cubeList.add(new ModelBox(cloth1, 48, 67, -2.0F, -1.2338F, 0.4723F, 4, 8, 1, 0.0F, false));

		cloth2 = new ModelRenderer(this);
		cloth2.setRotationPoint(0.0F, 10.9128F, 0.9962F);
		setRotationAngle(cloth2, 0.4606F, 0.0F, 0.0F);
		body.addChild(cloth2);
		cloth2.cubeList.add(new ModelBox(cloth2, 48, 56, -2.5F, 0.0F, 1.0F, 5, 9, 1, 0.0F, false));

		crotch = new ModelRenderer(this);
		crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(crotch, 0.0349F, 0.0F, 0.0F);
		body.addChild(crotch);
		crotch.cubeList.add(new ModelBox(crotch, 16, 82, -4.0F, 10.0F, -3.5F, 8, 2, 6, 0.0F, false));

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(5.0F, 3.0F, 0.0F);
		setRotationAngle(leftarm, 0.2618F, -0.0873F, -0.4363F);
		body.addChild(leftarm);
		leftarm.cubeList.add(new ModelBox(leftarm, 40, 16, -1.0F, -2.0F, -2.0F, 4, 4, 5, 0.0F, false));
		leftarm.cubeList.add(new ModelBox(leftarm, 46, 103, -1.0F, 2.0F, -1.5F, 3, 3, 4, 0.0F, false));

		handl = new ModelRenderer(this);
		handl.setRotationPoint(0.2142F, 7.7366F, 0.4486F);
		setRotationAngle(handl, -1.7453F, -0.2618F, 0.6981F);
		leftarm.addChild(handl);
		handl.cubeList.add(new ModelBox(handl, 48, 4, -2.0F, -2.0F, -4.4052F, 4, 8, 4, 0.0F, false));

		armorl = new ModelRenderer(this);
		armorl.setRotationPoint(2.0F, -2.5F, 0.9F);
		leftarm.addChild(armorl);
		armorl.cubeList.add(new ModelBox(armorl, 0, 74, -2.0F, -0.5F, -3.0F, 4, 1, 6, 0.0F, false));

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(-5.0F, 3.0F, 0.0F);
		setRotationAngle(rightarm, 0.1745F, 0.4363F, 0.5236F);
		body.addChild(rightarm);
		rightarm.cubeList.add(new ModelBox(rightarm, 40, 16, -3.0F, -2.0F, -2.0F, 4, 4, 5, 0.0F, true));
		rightarm.cubeList.add(new ModelBox(rightarm, 46, 103, -2.5F, 2.0F, -1.5F, 3, 3, 4, 0.0F, true));

		handr = new ModelRenderer(this);
		handr.setRotationPoint(-1.3226F, 5.0458F, 0.1748F);
		setRotationAngle(handr, -1.4835F, 0.0873F, -0.5236F);
		rightarm.addChild(handr);
		handr.cubeList.add(new ModelBox(handr, 48, 4, -2.0F, -3.0864F, -1.6742F, 4, 8, 4, 0.0F, true));

		armorr = new ModelRenderer(this);
		armorr.setRotationPoint(5.0F, -2.0F, 1.0F);
		rightarm.addChild(armorr);
		armorr.cubeList.add(new ModelBox(armorr, 0, 74, -9.0F, -1.0F, -3.1F, 4, 1, 6, 0.0F, true));

		scarf = new ModelRenderer(this);
		scarf.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(scarf, 0.5817F, 0.0F, 0.0F);
		body.addChild(scarf);
		scarf.cubeList.add(new ModelBox(scarf, 24, 35, -5.0F, -1.0F, -4.0F, 10, 2, 10, 0.0F, false));

		scarf2 = new ModelRenderer(this);
		scarf2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(scarf2, 0.2471F, 0.0F, 0.0F);
		body.addChild(scarf2);
		scarf2.cubeList.add(new ModelBox(scarf2, 28, 35, -4.5F, -1.0F, -4.5F, 9, 3, 9, 0.0F, false));

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -3.5F, -6.0F, -4.0F, 7, 6, 8, 0.0F, false));

		hair1 = new ModelRenderer(this);
		hair1.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(hair1, 0.6093F, -0.1487F, 0.0349F);
		head.addChild(hair1);
		hair1.cubeList.add(new ModelBox(hair1, 0, 35, -4.0F, -8.0F, 0.5F, 3, 3, 6, 0.0F, false));

		hair2 = new ModelRenderer(this);
		hair2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(hair2, 0.1267F, 0.0F, 0.0F);
		head.addChild(hair2);
		hair2.cubeList.add(new ModelBox(hair2, 0, 49, -3.9F, -8.3F, -2.5F, 8, 4, 8, 0.0F, false));

		hair3 = new ModelRenderer(this);
		hair3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(hair3, 0.5729F, 0.0F, 0.0F);
		head.addChild(hair3);
		hair3.cubeList.add(new ModelBox(hair3, 0, 35, -2.0F, -9.0F, 0.0F, 4, 4, 8, 0.0F, false));

		hair4 = new ModelRenderer(this);
		hair4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(hair4, 0.4363F, 0.2269F, 0.2094F);
		head.addChild(hair4);
		hair4.cubeList.add(new ModelBox(hair4, 0, 49, 0.2F, -8.0F, 0.0F, 3, 4, 7, 0.0F, false));

		hair5 = new ModelRenderer(this);
		hair5.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(hair5, 0.4363F, -0.2269F, -0.2094F);
		head.addChild(hair5);
		hair5.cubeList.add(new ModelBox(hair5, 0, 49, -3.2F, -8.0F, 0.0F, 2, 4, 7, 0.0F, false));

		hair6 = new ModelRenderer(this);
		hair6.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(hair6, 0.6109F, 0.1396F, -0.0349F);
		head.addChild(hair6);
		hair6.cubeList.add(new ModelBox(hair6, 0, 35, 1.0F, -8.0F, 0.5F, 3, 3, 6, 0.0F, false));

		hat = new ModelRenderer(this);
		hat.setRotationPoint(0.0F, 0.0F, 0.0F);
		head.addChild(hat);
		hat.cubeList.add(new ModelBox(hat, 0, 118, -4.0F, -6.5F, -4.5F, 8, 2, 8, 0.0F, false));

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(2.0F, 12.0F, 1.0F);
		setRotationAngle(leftleg, -0.0258F, -0.1374F, -0.1723F);
		leftleg.cubeList.add(new ModelBox(leftleg, 21, 100, -2.0F, 0.0F, -3.5F, 5, 7, 5, 0.0F, false));

		legdownl = new ModelRenderer(this);
		legdownl.setRotationPoint(0.4313F, 5.9635F, -0.0644F);
		setRotationAngle(legdownl, 0.5236F, 0.0F, 0.0F);
		leftleg.addChild(legdownl);
		legdownl.cubeList.add(new ModelBox(legdownl, 0, 100, -2.5F, -0.5F, -2.5F, 5, 9, 5, 0.0F, false));

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(-2.0F, 12.0F, 0.0F);
		setRotationAngle(rightleg, -0.1017F, 0.1631F, 0.1609F);
		rightleg.cubeList.add(new ModelBox(rightleg, 21, 100, -3.0F, 0.0F, -2.5F, 5, 7, 5, 0.0F, true));

		legdownr = new ModelRenderer(this);
		legdownr.setRotationPoint(1.6154F, 6.7528F, 1.5678F);
		setRotationAngle(legdownr, 0.9506F, 0.1601F, 0.1487F);
		rightleg.addChild(legdownr);
		legdownr.cubeList.add(new ModelBox(legdownr, 0, 100, -4.1154F, -1.9128F, -2.5F, 5, 9, 5, 0.0F, true));

		hands = new ModelRenderer(this);
		hands.setRotationPoint(-7.0F, -4.0F, -8.0F);
		setRotationAngle(hands, -1.5708F, 0.0F, 0.0F);
		hands.cubeList.add(new ModelBox(hands, 48, 4, -8.3226F, -19.0405F, -1.4994F, 4, 8, 4, 0.0F, true));
		hands.cubeList.add(new ModelBox(hands, 48, 4, 18.3226F, -12.0405F, -1.4994F, 4, 8, 4, 0.0F, false));
		hands.cubeList.add(new ModelBox(hands, 48, 4, -13.3226F, -13.0405F, 6.5006F, 4, 8, 4, 0.0F, true));
		hands.cubeList.add(new ModelBox(hands, 48, 4, 24.3226F, -20.0405F, 4.5006F, 4, 8, 4, 0.0F, false));
		hands.cubeList.add(new ModelBox(hands, 48, 4, -8.3226F, -19.0405F, 11.5006F, 4, 8, 4, 0.0F, true));
		hands.cubeList.add(new ModelBox(hands, 48, 4, 18.3226F, -17.0405F, 11.5006F, 4, 8, 4, 0.0F, false));
	}

	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
					   float headPitch, float scale) {
		setRotationAngles( limbSwing, limbSwingAmount, ageInTicks, headPitch,netHeadYaw, scale, entityIn);
		float off = (float) (MathHelper.cos((float) (0.1*ageInTicks))*0.1);
		head.offsetY = off;
		body.offsetY = off;
		leftleg.offsetY = off;
		rightleg.offsetY = off;
		hands.offsetY = off;

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
//		GlStateManager.enableBlend();
		float off = (float) (MathHelper.cos((float) (0.15*ageInTicks)));
		GlStateManager.color(1,1,1,0.5f*off);
		hands.render(scale*6/5);
		GlStateManager.color(1,1,1,1);
//		GlStateManager.disableBlend();
	}

	@Override
	public void setPosition() {
		GlStateManager.translate(0.0F, -0.7F, 0.5F);
	}
}