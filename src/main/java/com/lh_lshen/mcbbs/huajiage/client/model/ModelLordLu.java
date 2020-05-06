package com.lh_lshen.mcbbs.huajiage.client.model;
// Made with Blockbench
// Paste this code into your mod.
// Make sure to generate all required imports

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLordLu extends ModelBase {
	private final ModelRenderer core;
	private final ModelRenderer wing;
	private final ModelRenderer wings_1;
	private final ModelRenderer wing_1;
	private final ModelRenderer wing_2;
	private final ModelRenderer wings_2;
	private final ModelRenderer wing_3;
	private final ModelRenderer wing_4;
	private final ModelRenderer wings_3;
	private final ModelRenderer wing_5;
	private final ModelRenderer wing_6;
	private final ModelRenderer wings_4;
	private final ModelRenderer wing_7;
	private final ModelRenderer wing_8;
	private final ModelRenderer wing_bones;
	private final ModelRenderer wing_bone_1;
	private final ModelRenderer wing_bone_2;
	private final ModelRenderer wing_bone_3;
	private final ModelRenderer wing_bone_4;
	private final ModelRenderer wing_bone_5;
	private final ModelRenderer wing_bone_6;
	private final ModelRenderer wing_bone_7;
	private final ModelRenderer wing_bone_8;

	public ModelLordLu() {
		textureWidth = 128;
		textureHeight = 128;

		core = new ModelRenderer(this);
		core.setRotationPoint(0.0F, -1.0F, 7.0F);
		core.cubeList.add(new ModelBox(core, 0, 27, -10.0F, -10.0F, 0.0F, 21, 21, 0, 0.0F, false));

		wing = new ModelRenderer(this);
		wing.setRotationPoint(0.4F, -1.0F, 8.0F);

		wings_1 = new ModelRenderer(this);
		wings_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		wing.addChild(wings_1);

		wing_1 = new ModelRenderer(this);
		wing_1.setRotationPoint(-0.6145F, -14.9912F, 0.0F);
		setRotationAngle(wing_1, 0.0F, 0.0F, -1.5708F);
		wings_1.addChild(wing_1);
		wing_1.cubeList.add(new ModelBox(wing_1, 86, 2, -7.7855F, -2.0088F, 0.0F, 20, 5, 0, 0.0F, false));

		wing_2 = new ModelRenderer(this);
		wing_2.setRotationPoint(0.3855F, 18.0088F, 0.0F);
		setRotationAngle(wing_2, 0.0F, 0.0F, 1.5708F);
		wings_1.addChild(wing_2);
		wing_2.cubeList.add(new ModelBox(wing_2, 86, 7, -10.7855F, -2.0088F, 0.0F, 20, 5, 0, 0.0F, false));

		wings_2 = new ModelRenderer(this);
		wings_2.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(wings_2, 0.0F, 0.0F, -1.5708F);
		wing.addChild(wings_2);

		wing_3 = new ModelRenderer(this);
		wing_3.setRotationPoint(-0.6145F, -14.9912F, 0.0F);
		setRotationAngle(wing_3, 0.0F, 0.0F, -1.5708F);
		wings_2.addChild(wing_3);
		wing_3.cubeList.add(new ModelBox(wing_3, 86, 12, -7.7855F, -2.0088F, 0.0F, 20, 5, 0, 0.0F, false));

		wing_4 = new ModelRenderer(this);
		wing_4.setRotationPoint(0.3855F, 18.0088F, 0.0F);
		setRotationAngle(wing_4, 0.0F, 0.0F, 1.5708F);
		wings_2.addChild(wing_4);
		wing_4.cubeList.add(new ModelBox(wing_4, 86, 17, -10.7855F, -2.0088F, 0.0F, 20, 5, 0, 0.0F, false));

		wings_3 = new ModelRenderer(this);
		wings_3.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(wings_3, 0.0F, 0.0F, -2.3562F);
		wing.addChild(wings_3);

		wing_5 = new ModelRenderer(this);
		wing_5.setRotationPoint(-0.6145F, -14.9912F, 0.0F);
		setRotationAngle(wing_5, 0.0F, 0.0F, -1.5708F);
		wings_3.addChild(wing_5);
		wing_5.cubeList.add(new ModelBox(wing_5, 86, 22, -7.7855F, -2.0088F, 0.0F, 20, 5, 0, 0.0F, false));

		wing_6 = new ModelRenderer(this);
		wing_6.setRotationPoint(0.3855F, 18.0088F, 0.0F);
		setRotationAngle(wing_6, 0.0F, 0.0F, 1.5708F);
		wings_3.addChild(wing_6);
		wing_6.cubeList.add(new ModelBox(wing_6, 86, 27, -10.7855F, -2.0088F, 0.0F, 20, 5, 0, 0.0F, false));

		wings_4 = new ModelRenderer(this);
		wings_4.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(wings_4, 0.0F, 0.0F, -0.7854F);
		wing.addChild(wings_4);

		wing_7 = new ModelRenderer(this);
		wing_7.setRotationPoint(-0.6145F, -14.9912F, 0.0F);
		setRotationAngle(wing_7, 0.0F, 0.0F, -1.5708F);
		wings_4.addChild(wing_7);
		wing_7.cubeList.add(new ModelBox(wing_7, 86, 32, -7.7855F, -2.0088F, 0.0F, 20, 5, 0, 0.0F, false));

		wing_8 = new ModelRenderer(this);
		wing_8.setRotationPoint(0.3855F, 18.0088F, 0.0F);
		setRotationAngle(wing_8, 0.0F, 0.0F, 1.5708F);
		wings_4.addChild(wing_8);
		wing_8.cubeList.add(new ModelBox(wing_8, 86, 37, -10.7855F, -2.0088F, 0.0F, 20, 5, 0, 0.0F, false));

		wing_bones = new ModelRenderer(this);
		wing_bones.setRotationPoint(0.4F, -1.0F, 8.0F);
		setRotationAngle(wing_bones, 0.0F, 0.0F, 0.3927F);

		wing_bone_1 = new ModelRenderer(this);
		wing_bone_1.setRotationPoint(0.0F, 0.0F, 3.0F);
		setRotationAngle(wing_bone_1, 0.0F, 0.0F, -0.3927F);
		wing_bones.addChild(wing_bone_1);
		wing_bone_1.cubeList.add(new ModelBox(wing_bone_1, 0, 52, -5.4F, -23.0F, 0.0F, 10, 1, 0, 0.0F, false));
		wing_bone_1.cubeList.add(new ModelBox(wing_bone_1, 3, 52, -4.4F, -20.0F, 0.0F, 8, 1, 0, 0.0F, false));
		wing_bone_1.cubeList.add(new ModelBox(wing_bone_1, 8, 52, -3.4F, -17.0F, 0.0F, 6, 1, 0, 0.0F, false));

		wing_bone_2 = new ModelRenderer(this);
		wing_bone_2.setRotationPoint(0.0F, 0.0F, 3.0F);
		setRotationAngle(wing_bone_2, 0.0F, 0.0F, 0.3927F);
		wing_bones.addChild(wing_bone_2);
		wing_bone_2.cubeList.add(new ModelBox(wing_bone_2, 0, 52, -5.4F, -23.0F, 0.0F, 10, 1, 0, 0.0F, false));
		wing_bone_2.cubeList.add(new ModelBox(wing_bone_2, 3, 52, -4.4F, -20.0F, 0.0F, 8, 1, 0, 0.0F, false));
		wing_bone_2.cubeList.add(new ModelBox(wing_bone_2, 6, 55, -3.4F, -17.0F, 0.0F, 6, 1, 0, 0.0F, false));

		wing_bone_3 = new ModelRenderer(this);
		wing_bone_3.setRotationPoint(0.0F, 0.0F, 3.0F);
		setRotationAngle(wing_bone_3, 0.0F, 0.0F, 1.1781F);
		wing_bones.addChild(wing_bone_3);
		wing_bone_3.cubeList.add(new ModelBox(wing_bone_3, 0, 55, -5.4F, -23.0F, 0.0F, 10, 1, 0, 0.0F, false));
		wing_bone_3.cubeList.add(new ModelBox(wing_bone_3, 3, 52, -4.4F, -20.0F, 0.0F, 8, 1, 0, 0.0F, false));
		wing_bone_3.cubeList.add(new ModelBox(wing_bone_3, 6, 55, -3.4F, -17.0F, 0.0F, 6, 1, 0, 0.0F, false));

		wing_bone_4 = new ModelRenderer(this);
		wing_bone_4.setRotationPoint(0.0F, 0.0F, 3.0F);
		setRotationAngle(wing_bone_4, 0.0F, 0.0F, 1.9635F);
		wing_bones.addChild(wing_bone_4);
		wing_bone_4.cubeList.add(new ModelBox(wing_bone_4, 0, 52, -5.4F, -23.0F, 0.0F, 10, 1, 0, 0.0F, false));
		wing_bone_4.cubeList.add(new ModelBox(wing_bone_4, 3, 55, -4.4F, -20.0F, 0.0F, 8, 1, 0, 0.0F, false));
		wing_bone_4.cubeList.add(new ModelBox(wing_bone_4, 6, 55, -3.4F, -17.0F, 0.0F, 6, 1, 0, 0.0F, false));

		wing_bone_5 = new ModelRenderer(this);
		wing_bone_5.setRotationPoint(0.0F, 0.0F, 3.0F);
		setRotationAngle(wing_bone_5, 0.0F, 0.0F, 2.7489F);
		wing_bones.addChild(wing_bone_5);
		wing_bone_5.cubeList.add(new ModelBox(wing_bone_5, 0, 55, -5.4F, -23.0F, 0.0F, 10, 1, 0, 0.0F, false));
		wing_bone_5.cubeList.add(new ModelBox(wing_bone_5, 3, 55, -4.4F, -20.0F, 0.0F, 8, 1, 0, 0.0F, false));
		wing_bone_5.cubeList.add(new ModelBox(wing_bone_5, 6, 55, -3.4F, -17.0F, 0.0F, 6, 1, 0, 0.0F, false));

		wing_bone_6 = new ModelRenderer(this);
		wing_bone_6.setRotationPoint(0.0F, 0.0F, 3.0F);
		setRotationAngle(wing_bone_6, 0.0F, 0.0F, -2.7489F);
		wing_bones.addChild(wing_bone_6);
		wing_bone_6.cubeList.add(new ModelBox(wing_bone_6, 0, 55, -5.4F, -23.0F, 0.0F, 10, 1, 0, 0.0F, false));
		wing_bone_6.cubeList.add(new ModelBox(wing_bone_6, 3, 55, -4.4F, -20.0F, 0.0F, 8, 1, 0, 0.0F, false));
		wing_bone_6.cubeList.add(new ModelBox(wing_bone_6, 8, 52, -3.4F, -17.0F, 0.0F, 6, 1, 0, 0.0F, false));

		wing_bone_7 = new ModelRenderer(this);
		wing_bone_7.setRotationPoint(0.0F, 0.0F, 3.0F);
		setRotationAngle(wing_bone_7, 0.0F, 0.0F, -1.9635F);
		wing_bones.addChild(wing_bone_7);
		wing_bone_7.cubeList.add(new ModelBox(wing_bone_7, 0, 52, -5.4F, -23.0F, 0.0F, 10, 1, 0, 0.0F, false));
		wing_bone_7.cubeList.add(new ModelBox(wing_bone_7, 3, 55, -4.4F, -20.0F, 0.0F, 8, 1, 0, 0.0F, false));
		wing_bone_7.cubeList.add(new ModelBox(wing_bone_7, 8, 52, -3.4F, -17.0F, 0.0F, 6, 1, 0, 0.0F, false));

		wing_bone_8 = new ModelRenderer(this);
		wing_bone_8.setRotationPoint(0.0F, 0.0F, 3.0F);
		setRotationAngle(wing_bone_8, 0.0F, 0.0F, -1.1781F);
		wing_bones.addChild(wing_bone_8);
		wing_bone_8.cubeList.add(new ModelBox(wing_bone_8, 0, 55, -5.4F, -23.0F, 0.0F, 10, 1, 0, 0.0F, false));
		wing_bone_8.cubeList.add(new ModelBox(wing_bone_8, 3, 52, -4.4F, -20.0F, 0.0F, 8, 1, 0, 0.0F, false));
		wing_bone_8.cubeList.add(new ModelBox(wing_bone_8, 8, 52, -3.4F, -17.0F, 0.0F, 6, 1, 0, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		core.render(f5);
		wing.render(f5);
		wing_bones.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	public void setWingRotation( float f1, float f2) {
		wing.rotateAngleZ=f1;
		wing_bones.rotateAngleZ=f2;
		
	}
}