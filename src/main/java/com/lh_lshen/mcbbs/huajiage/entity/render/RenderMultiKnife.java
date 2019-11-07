package com.lh_lshen.mcbbs.huajiage.entity.render;

import javax.annotation.Nonnull;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.model.ModelMuliKnife;
import com.lh_lshen.mcbbs.huajiage.entity.EntityMultiKnife;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class RenderMultiKnife extends Render<EntityMultiKnife>
{

	private static final ModelMuliKnife model = new ModelMuliKnife();
	private static final ResourceLocation MULI_KNIFE = new ResourceLocation(HuajiAge.MODID, "textures/entity/entity_multi_knife.png");
	
	public RenderMultiKnife(RenderManager renderManager) {
		super(renderManager);
	}

	 @Override
	    public void doRender(EntityMultiKnife entity, double x, double y, double z, float entityYaw, float partialTicks)
	    {
		 super.doRender(entity, x, y, z, entityYaw, partialTicks);
		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		GlStateManager.color(1F, 1F, 1F, 1F);
		GlStateManager.translate(x, y, z);
		GlStateManager.rotate(entity.getRotation(), 0F, 1F, 0F);
		GlStateManager.rotate(90F, 1F, 0F, 0F);
		GlStateManager.rotate(entity.getPitch(), 1F, 0F, 0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(MULI_KNIFE);
		GlStateManager.scale(1F, -1F, -1F);
		model.render(entity, partialTicks, partialTicks, partialTicks, partialTicks, partialTicks, 0.03f);
		GlStateManager.color(1F, 1F, 1F);
		GlStateManager.scale(1F, -1F, -1F);
		GlStateManager.enableRescaleNormal();
		GlStateManager.popMatrix();
	    
	    }
	@Override
	protected ResourceLocation getEntityTexture(EntityMultiKnife entity) {
		return MULI_KNIFE;
	}

}
