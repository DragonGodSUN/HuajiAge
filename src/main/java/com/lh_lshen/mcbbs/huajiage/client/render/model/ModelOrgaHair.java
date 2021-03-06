// Date: 2019/8/30 18:18:27
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX


package com.lh_lshen.mcbbs.huajiage.client.render.model;

import javax.annotation.Nonnull;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EntityEquipmentSlot;

public class ModelOrgaHair extends ModelArmor
{
  //fields
    ModelRenderer head;
    ModelRenderer hair10;
    ModelRenderer hair9;
    ModelRenderer hair8;
    ModelRenderer hair7;
    ModelRenderer hair6;
    ModelRenderer hair5;
    ModelRenderer hair4;
    ModelRenderer hair3;
    ModelRenderer hair2;
    ModelRenderer hair1;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer charm;
  
    public ModelOrgaHair(EntityEquipmentSlot slot) {
		super(slot);
    textureWidth = 64;
    textureHeight = 64;
    
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-4F, -8F, -4F, 8, 8, 8);
      head.setRotationPoint(0F, 0F, 0F);
      head.setTextureSize(64, 64);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      hair10 = new ModelRenderer(this, 31, 40);
      hair10.addBox(-1F, -8.9F, 1.533333F, 2, 3, 5);
      hair10.setRotationPoint(0F, 0F, 0F);
      hair10.setTextureSize(64, 64);
      hair10.mirror = true;
      setRotation(hair10, 0.5576792F, 0F, 0F);
      hair9 = new ModelRenderer(this, 0, 40);
      hair9.addBox(-2.033333F, -8.666667F, -0.4666667F, 4, 3, 7);
      hair9.setRotationPoint(0F, 0F, 0F);
      hair9.setTextureSize(64, 64);
      hair9.mirror = true;
      setRotation(hair9, 0.3346075F, 0F, 0F);
      hair8 = new ModelRenderer(this, 31, 51);
      hair8.addBox(-0.8666667F, -8.533334F, -1F, 2, 2, 6);
      hair8.setRotationPoint(0F, 0F, 0F);
      hair8.setTextureSize(64, 64);
      hair8.mirror = true;
      setRotation(hair8, 0.1858931F, -0.2602503F, -0.4089647F);
      hair7 = new ModelRenderer(this, 31, 51);
      hair7.addBox(-2.066667F, -8.533334F, -1.4F, 2, 2, 6);
      hair7.setRotationPoint(0F, 0F, 0F);
      hair7.setTextureSize(64, 64);
      hair7.mirror = true;
      setRotation(hair7, 0.1487144F, 0.1487144F, 0.5205006F);
      hair6 = new ModelRenderer(this, 0, 51);
      hair6.addBox(-3F, -8.6F, -2.066667F, 6, 2, 7);
      hair6.setRotationPoint(0F, 0F, 0F);
      hair6.setTextureSize(64, 64);
      hair6.mirror = true;
      setRotation(hair6, 0.1858931F, 0F, 0F);
      hair5 = new ModelRenderer(this, 11, 4);
      hair5.addBox(-3.266667F, -8F, -5.266667F, 2, 4, 1);
      hair5.setRotationPoint(0F, 0F, 0F);
      hair5.setTextureSize(64, 64);
      hair5.mirror = true;
      setRotation(hair5, -0.1487144F, 0.0371786F, 0.6320364F);
      hair4 = new ModelRenderer(this, 10, 1);
      hair4.addBox(0.2F, -8.6F, -4.8F, 2, 4, 1);
      hair4.setRotationPoint(0F, 0F, 0F);
      hair4.setTextureSize(64, 64);
      hair4.mirror = true;
      setRotation(hair4, -0.0743572F, -0.0371786F, -0.5205006F);
      hair3 = new ModelRenderer(this, 9, 1);
      hair3.addBox(-1F, -8.633333F, -4.833333F, 2, 4, 1);
      hair3.setRotationPoint(0F, 0F, 0F);
      hair3.setTextureSize(64, 64);
      hair3.mirror = true;
      setRotation(hair3, -0.0743572F, 0F, 0F);
      hair2 = new ModelRenderer(this, 33, 0);
      hair2.addBox(-4F, -8F, -4.2F, 7, 6, 8);
      hair2.setRotationPoint(0F, 0F, 0F);
      hair2.setTextureSize(64, 64);
      hair2.mirror = true;
      setRotation(hair2, 0F, 0F, 0F);
      hair1 = new ModelRenderer(this, 11, 0);
      hair1.addBox(-1F, -6F, -4.5F, 1, 1, 1);
      hair1.setRotationPoint(0F, 0F, 0F);
      hair1.setTextureSize(64, 64);
      hair1.mirror = true;
      setRotation(hair1, 0F, 0F, 0F);
      body = new ModelRenderer(this, 16, 16);
      body.addBox(-4F, 0F, -2F, 8, 12, 4);
      body.setRotationPoint(0F, 0F, 0F);
      body.setTextureSize(64, 64);
      body.mirror = true;
      setRotation(body, 0F, 0F, 0F);
      rightarm = new ModelRenderer(this, 40, 16);
      rightarm.addBox(-3F, -2F, -2F, 4, 12, 4,0.20f);
      rightarm.setRotationPoint(-5F, 2F, 0F);
      rightarm.setTextureSize(64, 64);
      rightarm.mirror = true;
      setRotation(rightarm, 0F, 0F, 0F);
      leftarm = new ModelRenderer(this, 40, 16);
      leftarm.addBox(-1F, -2F, -2F, 4, 12, 4,0.20f);
      leftarm.setRotationPoint(5F, 2F, 0F);
      leftarm.setTextureSize(64, 64);
      leftarm.mirror = false;
      setRotation(leftarm, 0F, 0F, 0F);
      
      this.head.addChild(hair1);
      this.head.addChild(hair2);
      this.head.addChild(hair3);
      this.head.addChild(hair4);
      this.head.addChild(hair5);
      this.head.addChild(hair6);
      this.head.addChild(hair7);
      this.head.addChild(hair8);
      this.head.addChild(hair9);
      this.head.addChild(hair10);
  }
  
  @Override
 	public void render(@Nonnull Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
 		
 		head.showModel = slot == EntityEquipmentSlot.HEAD;
        body.showModel = slot == EntityEquipmentSlot.CHEST;
        rightarm.showModel = slot == EntityEquipmentSlot.CHEST;
        leftarm.showModel = slot == EntityEquipmentSlot.CHEST;
 		
 		bipedHeadwear.showModel = false;

 		bipedHead = head;
 		bipedBody=body;
 		bipedLeftArm=leftarm;
 		bipedRightArm=rightarm;
 		
 		

 		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
// 		if(entity instanceof EntityPlayer) {
// 			EntityPlayer p=(EntityPlayer)entity;
// 			ItemStack item=new ItemStack(ItemLoader.infiniteCharm);
// 			ItemStack items=new ItemStack(ItemLoader.orgaFlag);
// 			if(p.inventory.hasItemStack(item)){
// 				if(!(p.getHeldItemMainhand().getItem()==ItemLoader.infiniteCharm)&&!(p.getHeldItemOffhand().getItem()==ItemLoader.infiniteCharm))
// 					 if(NBTHelper.getEntityBoolean(p, "huajiage.orga.suit")) {
// 						 Minecraft.getMinecraft().getRenderItem().renderItem(items, TransformType.HEAD);  
// 			       }else {
// 			    	  Minecraft.getMinecraft().getRenderItem().renderItem(item, TransformType.HEAD);  
// 		        }
// 			}
// 		}
 	}
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  
}
