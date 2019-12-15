package com.lh_lshen.mcbbs.huajiage.entity.render.layers;

import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
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
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerCharmDisplay implements  LayerRenderer<AbstractClientPlayer> {
	 protected final RenderLivingBase<?> livingEntityRenderer;

	    public LayerCharmDisplay(RenderLivingBase<?> livingEntityRendererIn)
	    {
	        this.livingEntityRenderer = livingEntityRendererIn;
	    }
	@Override
	public void doRenderLayer(AbstractClientPlayer entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
//		 boolean flag = entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT;
//	        ItemStack itemstack = flag ? entitylivingbaseIn.getHeldItemOffhand() : entitylivingbaseIn.getHeldItemMainhand();
//	        ItemStack itemstack1 = flag ? entitylivingbaseIn.getHeldItemMainhand() : entitylivingbaseIn.getHeldItemOffhand();
//	 
	 			EntityPlayer p=entitylivingbaseIn;
	 			ItemStack item=new ItemStack(ItemLoader.infiniteCharm);
	 			ItemStack items=new ItemStack(ItemLoader.orgaFlag);
	 			if(p.inventory.hasItemStack(item)){
	 				if(!(p.getHeldItemMainhand().getItem()==ItemLoader.infiniteCharm)&&!(p.getHeldItemOffhand().getItem()==ItemLoader.infiniteCharm))
	 					 if(NBTHelper.getEntityBoolean(p, "huajiage.orga.suit")) {
	 						 Minecraft.getMinecraft().getRenderItem().renderItem(items, TransformType.HEAD);  
	 			       }else {
	 			    	  Minecraft.getMinecraft().getRenderItem().renderItem(item, TransformType.HEAD);  
	 		        }
	 			}

//	        if (!itemstack.isEmpty()&&itemstack.getItem()==ItemLoader.roadRoller || !itemstack1.isEmpty()&&itemstack1.getItem()==ItemLoader.roadRoller )
//	        {
//	            GlStateManager.pushMatrix();
//	            
//	            this.renderHeldItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
//	            this.renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
//	            GlStateManager.popMatrix();
//	        }
		
	}

//    private void renderHeldItem(EntityLivingBase player, ItemStack stack, ItemCameraTransforms.TransformType type, EnumHandSide handSide)
//    {
//        if (!stack.isEmpty())
//        {
//
//
//            ((ModelBiped)this.livingEntityRenderer.getMainModel()).postRenderArm(0.0625F, handSide);
//            GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
//            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
//            boolean flag = handSide == EnumHandSide.LEFT;
//            GlStateManager.translate((float)(flag ? -1 : 1) / 16.0F, 0.125F, -0.625F);
//            Minecraft.getMinecraft().getItemRenderer().renderItemSide(player, stack, type, flag);
//
//        }
//    }
	@Override
	public boolean shouldCombineTextures() {
		
		return false;
	}

}
