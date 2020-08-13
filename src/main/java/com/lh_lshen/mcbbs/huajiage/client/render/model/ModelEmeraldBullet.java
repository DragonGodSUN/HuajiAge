package com.lh_lshen.mcbbs.huajiage.client.render.model;

import com.lh_lshen.mcbbs.huajiage.entity.EntityEmeraldBullet;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModelEmeraldBullet extends ModelBase
{
  @Override
  public void render(Entity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
		float headPitch, float scale) {
	  ItemStack item=new ItemStack(Items.EMERALD);
	  if(entityIn instanceof EntityEmeraldBullet){
          if (ForgeRegistries.ITEMS.containsKey(new ResourceLocation(((EntityEmeraldBullet) entityIn).getType()))) {
              Item itemR = ForgeRegistries.ITEMS.getValue(new ResourceLocation(((EntityEmeraldBullet) entityIn).getType()));
              item = new ItemStack(itemR);
          }
      }
	  Minecraft.getMinecraft().getRenderItem().renderItem(item, TransformType.GROUND);
	  setRotationAngles( limbSwing, limbSwingAmount, ageInTicks, headPitch,netHeadYaw, scale);
	  super.render(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
  }
 
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
  }

}
