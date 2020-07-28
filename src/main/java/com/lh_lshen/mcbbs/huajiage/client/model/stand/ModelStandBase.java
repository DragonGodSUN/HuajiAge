package com.lh_lshen.mcbbs.huajiage.client.model.stand;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;

public abstract class ModelStandBase extends ModelBase {

	public abstract void setRotationAngles(float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw,
	          float rotatePitch, float scale, Entity entity ,float power ,float speed) ;
	
	public abstract void setPunch(float limbSwing, float limbSwingAmount, float rotateFloat, float rotateYaw,
	          float rotatePitch, float scale, Entity entity ,float power ,float speed) ;
	
	public abstract void doHandRender(float x ,float y ,float z ,float scale ,float alpha) ;
	
	public abstract void effect(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scale);
	
	public abstract void extraEffect(Entity entityIn, float limbSwing, float limbSwingAmount, 
			 float ageInTicks, float netHeadYaw, float headPitch, float scale);
	
	public abstract void setPosition();


}

