package com.lh_lshen.mcbbs.huajiage.client.render.entity;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.render.model.ModelSheerHeartAttack;
import com.lh_lshen.mcbbs.huajiage.entity.EntitySheerHeartAttack;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSheerHeartAttack extends RenderLiving<EntitySheerHeartAttack>{

	 private static final ResourceLocation TEXTURE = new ResourceLocation(HuajiAge.MODID, "textures/entity/entity_sheer_heart_attack.png");

	public RenderSheerHeartAttack(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelSheerHeartAttack(), 0.6f);
    }

	@Override
	protected ResourceLocation getEntityTexture(EntitySheerHeartAttack entity) {
		return TEXTURE;
	}

	

}
