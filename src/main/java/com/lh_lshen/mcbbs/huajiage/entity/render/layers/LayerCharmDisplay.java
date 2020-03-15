package com.lh_lshen.mcbbs.huajiage.entity.render.layers;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerCharmDisplay implements  LayerRenderer<EntityLivingBase> {
	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) 
	{
		if(entitylivingbaseIn instanceof AbstractClientPlayer) 
			{
				AbstractClientPlayer p=(AbstractClientPlayer) entitylivingbaseIn;
	 			ItemStack item = new ItemStack(ItemLoader.infiniteCharm);
	 			ItemStack item_flag = new ItemStack(ItemLoader.orgaFlag);
	 			ItemStack off_item = p.getHeldItemOffhand();
	 			ItemStack main_item = p.getHeldItemMainhand();
	 			NBTTagCompound nbt_off = off_item.getTagCompound();
	 			NBTTagCompound nbt_main = main_item.getTagCompound();
	 			boolean flag = off_item.equals(item)&&!nbt_off.isEmpty()&&nbt_off.hasKey("orga")&&nbt_off.getBoolean("orga") 
 									   || main_item.equals(item)&&!nbt_main.isEmpty()&&nbt_main.hasKey("orga")&&nbt_main.getBoolean("orga");
	 			if(flag) 
		 			{
		 				Minecraft.getMinecraft().getRenderItem().renderItem(item_flag, TransformType.HEAD);  
		 			}
	 			else if(p.inventory.hasItemStack(item)&&!main_item.equals(item)&&!off_item.equals(item))
		 			{
		 				if(NBTHelper.getEntityBoolean(p, "huajiage.orga.suit")) 
			 				{
			 					Minecraft.getMinecraft().getRenderItem().renderItem(item_flag, TransformType.HEAD);  
			 				}else 
			 				{
			 					Minecraft.getMinecraft().getRenderItem().renderItem(item, TransformType.HEAD);  
			 				}
		 			}
	 			else if(off_item.equals(item) || main_item.equals(item))
		 			{
		 				Minecraft.getMinecraft().getRenderItem().renderItem(item, TransformType.HEAD);  
		 			}
			 }
	}


	@Override
	public boolean shouldCombineTextures() {
		
		return false;
	}

}
