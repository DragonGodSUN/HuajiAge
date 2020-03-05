package com.lh_lshen.mcbbs.huajiage.entity.render.layers;

import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerHeldTopItem implements  LayerRenderer<EntityLivingBase> {
	 protected final RenderLivingBase<?> livingEntityRenderer;

	    public LayerHeldTopItem(RenderLivingBase<?> livingEntityRendererIn)
	    {
	        this.livingEntityRenderer = livingEntityRendererIn;
	    }
	@Override
	public void doRenderLayer(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount,
			float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		 boolean flag = entitylivingbaseIn.getPrimaryHand() == EnumHandSide.RIGHT;
	        ItemStack itemstack = entitylivingbaseIn.getHeldItemOffhand();
	        ItemStack itemstack1 = entitylivingbaseIn.getHeldItemMainhand();
//	       
	        Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(ItemLoader.orgaFlag), TransformType.HEAD);  
	        if (!itemstack.isEmpty()|| !itemstack1.isEmpty())
	        {
	        	GlStateManager.pushMatrix();
	            this.renderHeldItem(entitylivingbaseIn, itemstack1, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
	            this.renderHeldItem(entitylivingbaseIn, itemstack, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
	            GlStateManager.popMatrix();
	        }
		
	}

    private void renderHeldItem(EntityLivingBase player, ItemStack stack, ItemCameraTransforms.TransformType type, EnumHandSide handSide)
    {
        if (!stack.isEmpty())
        {
        	GlStateManager.pushMatrix();
        	ModelBase main = this.livingEntityRenderer.getMainModel();
        	if(main instanceof ModelBiped) {
            ((ModelBiped)main).postRenderArm(0.0625F, handSide);
            GlStateManager.scale(0.625,0.625, 0.625);
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
//            GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            boolean flag = handSide == EnumHandSide.LEFT;
            GlStateManager.translate((float)(flag ? -1 : 1) / 16.0F, 0.125F, -0.625F);
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(player, stack, type, flag);
            }
            GlStateManager.popMatrix();

        }
    }
	@Override
	public boolean shouldCombineTextures() {
		
		return false;
	}

}
