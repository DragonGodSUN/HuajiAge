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
	private final ModelRenderer body;
	private final ModelRenderer w_1;
	private final ModelRenderer w_2;

	public ModelSheerHeartAttack() {
		textureWidth = 64;
		textureHeight = 64;

		head = new ModelRenderer(this);
		head.setRotationPoint(1.0F, 21.0F, -6.0F);
		head.cubeList.add(new ModelBox(head, 0, 0, -3.0F, -6.0F, -2.0F, 4, 5, 3, 0.0F, false));

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 24.0F, 0.0F);
		body.cubeList.add(new ModelBox(body, 0, 0, -5.0F, -12.0F, -5.0F, 10, 9, 14, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 28, -2.0F, -13.0F, 0.0F, 4, 1, 4, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 0, 37, -8.0F, -7.0F, -3.0F, 3, 4, 9, 0.0F, false));
		body.cubeList.add(new ModelBox(body, 34, 0, 5.0F, -7.0F, -3.0F, 3, 4, 9, 0.0F, false));

		w_1 = new ModelRenderer(this);
		w_1.setRotationPoint(6.0F, 22.0F, 0.0F);
		w_1.cubeList.add(new ModelBox(w_1, 19, 26, -4.0F, -1.0F, -4.0F, 4, 3, 11, 0.0F, false));

		w_2 = new ModelRenderer(this);
		w_2.setRotationPoint(-6.0F, 22.0F, 0.0F);
		w_2.cubeList.add(new ModelBox(w_2, 0, 23, 0.0F, -1.0F, -4.0F, 4, 3, 11, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		head.render(f5);
		body.render(f5);
		w_1.render(f5);
		w_2.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}