package com.lh_lshen.mcbbs.huajiage.client.render.entity;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.render.model.ModelEmeraldBullet;
import com.lh_lshen.mcbbs.huajiage.client.render.model.ModelMuliKnife;
import com.lh_lshen.mcbbs.huajiage.client.render.model.ModelOrgaHairKnife;
import com.lh_lshen.mcbbs.huajiage.entity.EntityEmeraldBullet;
import com.lh_lshen.mcbbs.huajiage.entity.EntityMultiKnife;
import com.lh_lshen.mcbbs.huajiage.entity.EntityOrgaHairKnife;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderOrgaHairKnife extends Render<EntityOrgaHairKnife>
{

	private static final ModelOrgaHairKnife MODEL_N = new ModelOrgaHairKnife();
//	private static final ResourceLocation MULI_KNIFE = new ResourceLocation(HuajiAge.MODID, "textures/entity/entity_multi_knife.png");
//	private static final ResourceLocation MULI_KNIFE_LIGHT = new ResourceLocation(HuajiAge.MODID, "textures/entity/entity_multi_knife_shiny.png");
	
	public RenderOrgaHairKnife(RenderManager renderManager) {
		super(renderManager);
	}

	 @Override
	    public void doRender(EntityOrgaHairKnife entity, double x, double y, double z, float entityYaw, float partialTicks)
	    {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.disableLighting();
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(45F, 0F, 1F, 0F); 
		GlStateManager.rotate(90F, 1F, 0F, 0F); 
		GlStateManager.rotate(entity.getRotation(), 0F, 1F, 0F);
		GlStateManager.rotate(entity.getPitch(), 1F, 0F, 0F);
		GlStateManager.rotate(entity.getRotationRandom(), 0F, 0F, 1F);
//		GlStateManager.rotate(90F, 0F, 0F, 0F);
		MODEL_N.render(entity, 0, 0, entity.ticksExisted, entityYaw, entity.rotationPitch, 0.3f);
		GlStateManager.color(1F, 1F, 1F);
		GlStateManager.enableLighting();
		GlStateManager.scale(1F, -1F, -1F);
		GlStateManager.enableRescaleNormal();
		GlStateManager.popMatrix();

	    }
	@Override
	protected ResourceLocation getEntityTexture(EntityOrgaHairKnife entity) {
		return null;
	}

}
