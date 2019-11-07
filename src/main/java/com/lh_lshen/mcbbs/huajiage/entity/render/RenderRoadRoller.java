package com.lh_lshen.mcbbs.huajiage.entity.render;

import javax.annotation.Nonnull;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.model.ModelMuliKnife;
import com.lh_lshen.mcbbs.huajiage.client.model.ModelRoadRoller;
import com.lh_lshen.mcbbs.huajiage.entity.EntityMultiKnife;
import com.lh_lshen.mcbbs.huajiage.entity.EntityRoadRoller;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderRoadRoller extends Render<EntityRoadRoller>
{

	private static final ModelRoadRoller model = new ModelRoadRoller();

	
	public RenderRoadRoller(RenderManager renderManager) {
		super(renderManager);
	}

	 @Override
	    public void doRender(EntityRoadRoller entity, double x, double y, double z, float entityYaw, float partialTicks)
	    {
		 super.doRender(entity, x, y, z, entityYaw, partialTicks);
		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(entity.getRotation(), 0F, 1F, 0F);
		GlStateManager.rotate(180F, 1F, 0F, 0F);
		GlStateManager.rotate(90F, 0F, -1F, 0F);
		GlStateManager.rotate(-MathHelper.abs(entity.getPitch()), 0F, 0F, 1F);
		GlStateManager.scale(1F, -1F, -1F);
		model.render(entity, partialTicks, partialTicks, partialTicks, partialTicks, partialTicks,1f);
		GlStateManager.scale(1F, -1F, -1F);
		GlStateManager.enableRescaleNormal();
		GlStateManager.popMatrix();
	    
	    }

	@Override
	protected ResourceLocation getEntityTexture(EntityRoadRoller entity) {
		// TODO Auto-generated method stub
		return null;
	}


}
