package com.lh_lshen.mcbbs.huajiage.client.model.stand.states;// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHierophantGreenIdle extends ModelStandBase {
	private final ModelRenderer head;
	private final ModelRenderer hat;
	private final ModelRenderer hat_part_3;
	private final ModelRenderer body;
	private final ModelRenderer Shape1;
	private final ModelRenderer Shape2;
	private final ModelRenderer bodydown;
	private final ModelRenderer crotch;
	private final ModelRenderer part1;
	private final ModelRenderer part1_1;
	private final ModelRenderer part1_2;
	private final ModelRenderer part1_4;
	private final ModelRenderer part2;
	private final ModelRenderer part1_3;
	private final ModelRenderer part1_5;
	private final ModelRenderer part1_6;
	private final ModelRenderer part3;
	private final ModelRenderer part4;
	private final ModelRenderer part5;
	private final ModelRenderer part6;
	private final ModelRenderer part6_1;
	private final ModelRenderer part6_2;
	private final ModelRenderer rightarm;
	private final ModelRenderer rightarmd;
	private final ModelRenderer leftarm;
	private final ModelRenderer leftarmd;
	private final ModelRenderer leftleg;
	private final ModelRenderer legdownl;
	private final ModelRenderer rightleg;
	private final ModelRenderer legdownr;
	private final ModelRenderer strips;
	private final ModelRenderer strip1;
	private final ModelRenderer strip2;
	private final ModelRenderer strip3;
	private final ModelRenderer strip4;
	private final ModelRenderer strip5;
	private final ModelRenderer strip6;
	private final ModelRenderer strip7;
	private final ModelRenderer strip8;
	private final ModelRenderer strip9;
	private final ModelRenderer strip10;
	private final ModelRenderer strip11;
//	private final ModelRenderer strip12;

	public ModelHierophantGreenIdle() {
		textureWidth = 64;
		textureHeight = 128;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, -1.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -3.0F, -5.8F, -2.0F, 6, 6, 6, 0.0F, false));

		hat = new ModelRenderer(this);
		hat.setRotationPoint(0.0F, 24.0F, 1.0F);
		head.addChild(hat);
		hat.cubeList.add(new ModelBox(hat, 35, 114, -3.5F, -30.0F, -3.5F, 7, 7, 7, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 28, 0, -4.0F, -28.0F, 0.0F, 1, 2, 2, 0.0F, false));
		hat.cubeList.add(new ModelBox(hat, 28, 0, 3.0F, -28.0F, 0.0F, 1, 2, 2, 0.0F, true));
		hat.cubeList.add(new ModelBox(hat, 28, 5, -1.5F, -30.5F, -2.5F, 3, 1, 3, 0.0F, false));

		hat_part_3 = new ModelRenderer(this);
		hat_part_3.setRotationPoint(2.0F, -27.0F, -5.0F);
		setRotationAngle(hat_part_3, -0.1745F, 0.0F, 0.0F);
		hat.addChild(hat_part_3);
		hat_part_3.cubeList.add(new ModelBox(hat_part_3, 54, 107, -3.5F, 0.5F, 1.5F, 3, 3, 2, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(body, 1.3963F, 0.0F, 0.2618F);
		body.cubeList.add(new ModelBox(body, 16, 16, -4.0F, 0.0F, -2.0F, 8, 6, 4, 0.0F, false));

		Shape1 = new ModelRenderer(this);
		Shape1.setRotationPoint(0.0F, 0.0F, -2.3F);
		body.addChild(Shape1);
		Shape1.cubeList.add(new ModelBox(Shape1, 35, 56, -1.5F, 4.0F, 0.0F, 3, 3, 1, 0.0F, false));

		Shape2 = new ModelRenderer(this);
		Shape2.setRotationPoint(0.1307F, -2.2291F, -3.4244F);
		setRotationAngle(Shape2, -0.0873F, 0.0F, 0.0F);
		body.addChild(Shape2);
		Shape2.cubeList.add(new ModelBox(Shape2, 54, 30, -1.5F, 4.0F, 0.0F, 3, 2, 2, 0.0F, false));

		bodydown = new ModelRenderer(this);
		bodydown.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(bodydown, -0.0175F, 0.0F, 0.0F);
		body.addChild(bodydown);
		bodydown.cubeList.add(new ModelBox(bodydown, 19, 66, -3.5F, 6.0246F, -1.6873F, 7, 5, 4, 0.0F, false));

		crotch = new ModelRenderer(this);
		crotch.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(crotch, 0.0349F, 0.0F, 0.0F);
		body.addChild(crotch);
		crotch.cubeList.add(new ModelBox(crotch, 16, 82, -4.0F, 7.5F, -2.8764F, 8, 2, 6, 0.0F, false));

		part1 = new ModelRenderer(this);
		part1.setRotationPoint(0.0F, -0.5208F, -2.9544F);
		setRotationAngle(part1, 0.0F, 0.0F, -0.7854F);
		body.addChild(part1);

		part1_1 = new ModelRenderer(this);
		part1_1.setRotationPoint(-3.9035F, -2.0107F, 2.8264F);
		setRotationAngle(part1_1, 0.0F, 0.0F, -0.3491F);
		part1.addChild(part1_1);
		part1_1.cubeList.add(new ModelBox(part1_1, 48, 54, -0.5F, -3.0F, -3.0F, 1, 6, 6, 0.0F, false));

		part1_2 = new ModelRenderer(this);
		part1_2.setRotationPoint(-5.2936F, -2.0295F, 2.5669F);
		setRotationAngle(part1_2, 0.0F, 0.0F, -0.6109F);
		part1.addChild(part1_2);
		part1_2.cubeList.add(new ModelBox(part1_2, 48, 54, -0.5F, -3.0F, -3.0F, 1, 7, 6, 0.0F, false));

		part1_4 = new ModelRenderer(this);
		part1_4.setRotationPoint(-3.2071F, -2.7071F, 3.0F);
		setRotationAngle(part1_4, 0.0F, 0.0F, -0.2618F);
		part1.addChild(part1_4);
		part1_4.cubeList.add(new ModelBox(part1_4, 48, 69, -2.2784F, -2.1619F, -2.5456F, 1, 3, 5, 0.0F, false));

		part2 = new ModelRenderer(this);
		part2.setRotationPoint(0.0F, -0.5208F, -2.9544F);
		setRotationAngle(part2, 0.0F, 0.0F, 0.7854F);
		body.addChild(part2);

		part1_3 = new ModelRenderer(this);
		part1_3.setRotationPoint(3.9035F, -2.0107F, 2.8264F);
		setRotationAngle(part1_3, 0.0F, 0.0F, 0.3491F);
		part2.addChild(part1_3);
		part1_3.cubeList.add(new ModelBox(part1_3, 48, 54, -0.5F, -3.0F, -3.0F, 1, 6, 6, 0.0F, false));

		part1_5 = new ModelRenderer(this);
		part1_5.setRotationPoint(5.2936F, -2.0295F, 2.5669F);
		setRotationAngle(part1_5, 0.0F, 0.0F, 0.6109F);
		part2.addChild(part1_5);
		part1_5.cubeList.add(new ModelBox(part1_5, 48, 54, -0.5F, -3.0F, -3.0F, 1, 7, 6, 0.0F, false));

		part1_6 = new ModelRenderer(this);
		part1_6.setRotationPoint(3.2071F, -2.7071F, 3.0F);
		setRotationAngle(part1_6, 0.0F, 0.0F, 0.2618F);
		part2.addChild(part1_6);
		part1_6.cubeList.add(new ModelBox(part1_6, 48, 69, 1.2784F, -2.1619F, -2.5456F, 1, 3, 5, 0.0F, false));

		part3 = new ModelRenderer(this);
		part3.setRotationPoint(2.0872F, 4.8422F, -1.8075F);
		body.addChild(part3);
		part3.cubeList.add(new ModelBox(part3, 35, 62, -3.5F, -2.0F, 4.0F, 3, 2, 1, 0.0F, false));

		part4 = new ModelRenderer(this);
		part4.setRotationPoint(-1.9052F, 4.7968F, 2.3473F);
		setRotationAngle(part4, 0.0F, 0.0F, -0.5236F);
		body.addChild(part4);
		part4.cubeList.add(new ModelBox(part4, 48, 79, -2.5F, -1.0F, -3.5473F, 4, 2, 4, 0.0F, false));

		part5 = new ModelRenderer(this);
		part5.setRotationPoint(1.9052F, 4.7968F, 2.3473F);
		setRotationAngle(part5, 0.0F, 0.0F, 0.5236F);
		body.addChild(part5);
		part5.cubeList.add(new ModelBox(part5, 48, 79, -1.5F, -1.0F, -3.5473F, 4, 2, 4, 0.0F, true));

		part6 = new ModelRenderer(this);
		part6.setRotationPoint(0.0F, -1.9696F, 0.3472F);
		body.addChild(part6);
		part6.cubeList.add(new ModelBox(part6, 0, 64, -2.0F, 2.9696F, 1.6528F, 4, 1, 1, 0.0F, false));

		part6_1 = new ModelRenderer(this);
		part6_1.setRotationPoint(-4.1129F, 4.4536F, -3.5367F);
		setRotationAngle(part6_1, 0.0F, 0.0F, -0.7854F);
		part6.addChild(part6_1);
		part6_1.cubeList.add(new ModelBox(part6_1, 0, 74, 2.0F, -2.0F, 0.0F, 1, 3, 6, 0.0F, true));

		part6_2 = new ModelRenderer(this);
		part6_2.setRotationPoint(4.1129F, 4.4536F, -3.5367F);
		setRotationAngle(part6_2, 0.0F, 0.0F, 0.7854F);
		part6.addChild(part6_2);
		part6_2.cubeList.add(new ModelBox(part6_2, 0, 74, -3.0F, -2.0F, 0.0F, 1, 3, 6, 0.0F, true));

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(4.0667F, 4.2177F, 1.4699F);
		setRotationAngle(rightarm, 0.0F, -0.7854F, -1.6581F);
		body.addChild(rightarm);
		rightarm.cubeList.add(new ModelBox(rightarm, 40, 16, -2.9332F, -0.5783F, -1.8622F, 4, 6, 4, 0.0F, false));

		rightarmd = new ModelRenderer(this);
		rightarmd.setRotationPoint(-0.0667F, 5.0783F, -0.1378F);
		setRotationAngle(rightarmd, 0.0F, -0.4363F, 0.2618F);
		rightarm.addChild(rightarmd);
		rightarmd.cubeList.add(new ModelBox(rightarmd, 48, 4, -2.5F, 0.0F, -1.5F, 4, 7, 4, 0.0F, false));

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(-4.0667F, 4.2177F, 1.4699F);
		setRotationAngle(leftarm, 0.0F, 1.3963F, 1.6581F);
		body.addChild(leftarm);
		leftarm.cubeList.add(new ModelBox(leftarm, 40, 16, -1.0668F, -0.5783F, -1.8622F, 4, 6, 4, 0.0F, true));

		leftarmd = new ModelRenderer(this);
		leftarmd.setRotationPoint(0.0667F, 5.0783F, -0.1378F);
		setRotationAngle(leftarmd, 0.0F, 0.6981F, -0.3491F);
		leftarm.addChild(leftarmd);
		leftarmd.cubeList.add(new ModelBox(leftarmd, 48, 4, -1.5F, 0.0F, -1.5F, 4, 7, 4, 0.0F, true));

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(-2.0F, 10.0F, 1.0F);
		setRotationAngle(leftleg, 0.0F, 0.0F, -0.0873F);
		body.addChild(leftleg);
		leftleg.cubeList.add(new ModelBox(leftleg, 21, 100, -3.0F, 0.0F, -3.5F, 5, 7, 5, 0.0F, true));

		legdownl = new ModelRenderer(this);
		legdownl.setRotationPoint(0.0F, 7.0F, -0.5F);
		leftleg.addChild(legdownl);
		legdownl.cubeList.add(new ModelBox(legdownl, 0, 100, -2.0F, -0.5F, -2.5F, 4, 9, 5, 0.0F, true));

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(2.0F, 10.0F, 1.0F);
		setRotationAngle(rightleg, -1.309F, 0.2618F, 0.0F);
		body.addChild(rightleg);
		rightleg.cubeList.add(new ModelBox(rightleg, 21, 100, -2.0F, 0.0F, -3.5F, 5, 7, 5, 0.0F, false));

		legdownr = new ModelRenderer(this);
		legdownr.setRotationPoint(0.0F, 7.0F, -0.5F);
		setRotationAngle(legdownr, 2.1817F, 0.0F, 0.0F);
		rightleg.addChild(legdownr);
		legdownr.cubeList.add(new ModelBox(legdownr, 0, 100, -2.2F, -0.5F, -2.5F, 4, 9, 5, 0.0F, false));

		strips = new ModelRenderer(this);
		strips.setRotationPoint(-2.0F, 5.0F, 25.0F);
		setRotationAngle(strips, 0.0873F, 0.0F, 0.0F);

		strip1 = new ModelRenderer(this);
		strip1.setRotationPoint(2.0F, 1.0F, 1.0F);
		strips.addChild(strip1);
		strip1.cubeList.add(new ModelBox(strip1, 44, 36, -3.0F, -3.0F, -1.0F, 3, 3, 7, 0.0F, false));

		strip2 = new ModelRenderer(this);
		strip2.setRotationPoint(-1.0F, -2.0F, 7.0F);
		setRotationAngle(strip2, 0.3491F, 0.5236F, -0.1745F);
		strip1.addChild(strip2);
		strip2.cubeList.add(new ModelBox(strip2, 2, 48, -1.0F, -0.5F, -1.0F, 2, 2, 12, 0.0F, false));

		strip3 = new ModelRenderer(this);
		strip3.setRotationPoint(1.0F, 0.0F, 11.0F);
		setRotationAngle(strip3, 0.3491F, 0.8727F, 0.6109F);
		strip2.addChild(strip3);
		strip3.cubeList.add(new ModelBox(strip3, 2, 48, -1.0F, -0.5F, -1.0F, 2, 2, 12, 0.0F, false));

		strip4 = new ModelRenderer(this);
		strip4.setRotationPoint(1.3493F, -0.2779F, 10.1046F);
		setRotationAngle(strip4, 0.3491F, 1.4835F, 0.6981F);
		strip3.addChild(strip4);
		strip4.cubeList.add(new ModelBox(strip4, 2, 48, -1.0F, -0.5F, -1.0F, 2, 2, 12, 0.0F, false));

		strip5 = new ModelRenderer(this);
		strip5.setRotationPoint(1.1632F, 1.1236F, 11.0628F);
		setRotationAngle(strip5, 0.3491F, 1.4835F, 0.6981F);
		strip4.addChild(strip5);
		strip5.cubeList.add(new ModelBox(strip5, 2, 48, -1.0F, -0.5F, -1.0F, 2, 2, 12, 0.0F, false));

		strip6 = new ModelRenderer(this);
		strip6.setRotationPoint(1.8064F, 0.502F, 11.423F);
		setRotationAngle(strip6, 0.3491F, 1.4835F, 0.6981F);
		strip5.addChild(strip6);
		strip6.cubeList.add(new ModelBox(strip6, 2, 48, -1.0F, -0.5F, -1.0F, 2, 2, 12, 0.0F, false));

		strip7 = new ModelRenderer(this);
		strip7.setRotationPoint(-0.3493F, -0.2217F, 10.7624F);
		setRotationAngle(strip7, 0.3491F, -1.309F, 0.6981F);
		strip6.addChild(strip7);
		strip7.cubeList.add(new ModelBox(strip7, 2, 48, -1.0F, -0.5F, -1.0F, 2, 2, 12, 0.0F, false));

		strip8 = new ModelRenderer(this);
		strip8.setRotationPoint(-0.2159F, 0.0822F, 11.4868F);
		setRotationAngle(strip8, -0.4363F, -0.6109F, 0.6981F);
		strip7.addChild(strip8);
		strip8.cubeList.add(new ModelBox(strip8, 2, 48, -1.0F, -0.5F, -1.0F, 2, 2, 12, 0.0F, false));

		strip9 = new ModelRenderer(this);
		strip9.setRotationPoint(-0.5709F, 0.8538F, 10.9519F);
		setRotationAngle(strip9, -0.4363F, -0.6109F, 0.6981F);
		strip8.addChild(strip9);
		strip9.cubeList.add(new ModelBox(strip9, 2, 48, -1.0F, -0.5F, -1.0F, 2, 2, 12, 0.0F, false));

		strip10 = new ModelRenderer(this);
		strip10.setRotationPoint(0.2116F, -0.0739F, 11.2227F);
		setRotationAngle(strip10, -0.6981F, -0.7854F, -0.3491F);
		strip9.addChild(strip10);
		strip10.cubeList.add(new ModelBox(strip10, 2, 48, -1.0F, -0.5F, -1.0F, 2, 2, 12, 0.0F, false));

		strip11 = new ModelRenderer(this);
		strip11.setRotationPoint(-0.2968F, 1.3778F, 10.9551F);
		setRotationAngle(strip11, -0.6981F, -1.7453F, -2.0944F);
		strip10.addChild(strip11);
		strip11.cubeList.add(new ModelBox(strip11, 2, 48, -1.0F, -0.5F, -1.0F, 2, 2, 12, 0.0F, false));

//		strip12 = new ModelRenderer(this);
//		strip12.setRotationPoint(0.7131F, 0.8792F, 10.7107F);
//		setRotationAngle(strip12, -0.6109F, 0.4363F, 1.1345F);
//		strip11.addChild(strip12);
//		strip12.cubeList.add(new ModelBox(strip12, 2, 48, -1.0F, -0.5F, -1.0F, 2, 2, 12, 0.0F, false));
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
					   float headPitch, float scale) {
		float off = (float) (MathHelper.cos((float) (0.1*ageInTicks))*0.1);

		head.   offsetY = off;
		body.   offsetY = off;
		strips. offsetY = off;
		strip2. offsetY = off*0.5f;
//		strip3. offsetY = -off*0.5f;
		strip4. offsetY = -off*0.5f;
//		strip5. offsetY = off*0.5f;
		strip6. offsetY = off*0.5f;
//		strip7. offsetY = -off*0.5f;
		strip8. offsetY = -off*0.5f;
//		strip9. offsetY = off*0.5f;
		strip10.offsetY = off*0.5f;
//		strip12.offsetY += off*0.5f;

		head.rotateAngleX = headPitch * 0.017453292F;
		head.rotateAngleY = netHeadYaw * 0.017453292F;

		head.render(scale);
		body.render(scale);


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
		strips.render(scale);
	}

	@Override
	public void setPosition() {
		GlStateManager.translate(0.8F, -0.75F, -0.7F);
	}
}