package com.lh_lshen.mcbbs.huajiage.client.model.stand;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHierophantGreen extends ModelStandBase {
	private final ModelRenderer head;
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
	private final ModelRenderer leftarm;
	private final ModelRenderer leftarmd;
	private final ModelRenderer rightarm;
	private final ModelRenderer rightarmd;
	private final ModelRenderer leftleg;
	private final ModelRenderer legdownl;
	private final ModelRenderer rightleg;
	private final ModelRenderer legdownr;
	private final ModelRenderer extra;

	public ModelHierophantGreen() {
		textureWidth = 64;
		textureHeight = 128;

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 0.0F, -1.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -3.0F, -5.8F, -2.0F, 6, 6, 6, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 28, 0, -4.0F, -4.0F, 1.0F, 1, 2, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 28, 0, 3.0F, -4.0F, 1.0F, 1, 2, 2, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 28, 5, -1.5F, -6.5F, -1.5F, 3, 1, 3, 0.0F, false));
		head.cubeList.add(new ModelBox(head, 35, 114, -3.5F, -6.0F, -2.5F, 7, 7, 7, 0.0F, false));

		hat_part_3 = new ModelRenderer(this);
		hat_part_3.setRotationPoint(2.0F, -3.0F, -4.0F);
		setRotationAngle(hat_part_3, -0.1745F, 0.0F, 0.0F);
		head.addChild(hat_part_3);
		hat_part_3.cubeList.add(new ModelBox(hat_part_3, 56, 107, -3.5F, 0.5F, 1.5F, 3, 3, 2, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(body, 0.1745F, 0.1745F, 0.0F);
		body.cubeList.add(new ModelBox(body, 16, 16, -4.0F, 0.0F, -2.0F, 8, 6, 4, 0.0F, false));

		Shape1 = new ModelRenderer(this);
		Shape1.setRotationPoint(0.0F, 0.0F, -2.3F);
		setRotationAngle(Shape1, 0.0F, 0.0F, 0.0F);
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
		crotch.cubeList.add(new ModelBox(crotch, 16, 82, -4.0F, 7.0655F, -2.8764F, 8, 2, 6, 0.0F, false));

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
		part5.cubeList.add(new ModelBox(part5, 48, 79, -1.5F, -1.0F, -3.5473F, 4, 2, 4, 0.0F, false));

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

		leftarm = new ModelRenderer(this);
		leftarm.setRotationPoint(0.0F, 6.0F, 0.0F);
		setRotationAngle(leftarm, -0.0873F, 0.0F, -0.9599F);
		leftarm.cubeList.add(new ModelBox(leftarm, 40, 16, 3.0F, 1.0872F, -1.9962F, 4, 6, 4, 0.0F, false));

		leftarmd = new ModelRenderer(this);
		leftarmd.setRotationPoint(2.6753F, 5.2814F, -3.4392F);
		setRotationAngle(leftarmd, 2.0071F, 3.0543F, -1.6581F);
		leftarm.addChild(leftarmd);
		leftarmd.cubeList.add(new ModelBox(leftarmd, 48, 4, -2.0F, -3.5F, -2.0F, 4, 7, 4, 0.0F, false));

		rightarm = new ModelRenderer(this);
		rightarm.setRotationPoint(0.0F, 4.0F, 0.0F);
		setRotationAngle(rightarm, -0.7854F, 0.0F, 1.309F);
		rightarm.cubeList.add(new ModelBox(rightarm, 40, 16, -4.1335F, 1.8434F, 1.2756F, 4, 6, 4, 0.0F, true));

		rightarmd = new ModelRenderer(this);
		rightarmd.setRotationPoint(-1.6845F, 7.7366F, 2.1856F);
		setRotationAngle(rightarmd, -2.0944F, 0.2618F, -0.2618F);
		rightarm.addChild(rightarmd);
		rightarmd.cubeList.add(new ModelBox(rightarmd, 48, 4, -2.3983F, -2.8966F, -1.1802F, 4, 7, 4, 0.0F, true));

		leftleg = new ModelRenderer(this);
		leftleg.setRotationPoint(2.0F, 10.0F, 0.0F);
		setRotationAngle(leftleg, -0.5494F, -0.3992F, -0.4341F);
		leftleg.cubeList.add(new ModelBox(leftleg, 21, 100, -2.0F, 0.0F, -3.5F, 5, 7, 5, 0.0F, false));

		legdownl = new ModelRenderer(this);
		legdownl.setRotationPoint(1.8266F, 3.8994F, 3.2559F);
		setRotationAngle(legdownl, 1.8088F, -0.1374F, -0.4105F);
		leftleg.addChild(legdownl);
		legdownl.cubeList.add(new ModelBox(legdownl, 0, 100, -4.5885F, -4.6434F, -2.9332F, 4, 9, 5, 0.0F, false));

		rightleg = new ModelRenderer(this);
		rightleg.setRotationPoint(-2.0F, 10.0F, 2.0F);
		setRotationAngle(rightleg, -0.2762F, 0.2504F, 0.1609F);
		rightleg.cubeList.add(new ModelBox(rightleg, 21, 100, -3.0F, 0.0F, -3.5F, 5, 7, 5, 0.0F, true));

		legdownr = new ModelRenderer(this);
		legdownr.setRotationPoint(-0.9457F, 10.0163F, 1.7692F);
		setRotationAngle(legdownr, 0.6015F, 0.0728F, 0.0614F);
		rightleg.addChild(legdownr);
		legdownr.cubeList.add(new ModelBox(legdownr, 0, 100, -1.5F, -4.5F, -2.5F, 4, 9, 5, 0.0F, true));

		extra = new ModelRenderer(this);
		extra.setRotationPoint(-0.5F, 4.5F, -6.5F);
		setRotationAngle(extra, 0.4363F, 0.0F, 0.4363F);
		extra.cubeList.add(new ModelBox(extra, 0, 36, -5.4063F, -0.6944F, 0.2277F, 10, 3, 3, 0.0F, false));
		extra.cubeList.add(new ModelBox(extra, 0, 44, -4.8289F, 0.4842F, 1.2F, 10, 1, 1, 0.0F, false));
	}

	@Override
	public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale) {
		float off = (float) (MathHelper.cos((float) (0.1*ageInTicks))*0.1);
		 head.offsetY = off;
		 body.offsetY = off;
		 leftleg.offsetY = off;
		 rightleg.offsetY = off;
		 leftarm.offsetY = off;
		 rightarm.offsetY =off;
		 extra.offsetY = off;
		    
	    head.rotateAngleX = headPitch * 0.017453292F;
	    head.rotateAngleY = netHeadYaw * 0.017453292F;
		
		head.render(scale);
		body.render(scale);
		leftarm.render(scale);
		rightarm.render(scale);
		leftleg.render(scale);
		rightleg.render(scale);
		extra.render(scale);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw,
			float rotatePitch, float scale, Entity entity, float power, float speed) {
		
	}

	@Override
	public void setPunch(float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw, float rotatePitch,
			float scale, Entity entity, float power, float speed) {
			
	}

	@Override
	public void doHandRender(float x, float y, float z, float scale, float alpha) {
		
	}

	@Override
	public void setPostion() {
		GlStateManager.translate(0.5F, -1.0F, 0.75F);		
	}
}
