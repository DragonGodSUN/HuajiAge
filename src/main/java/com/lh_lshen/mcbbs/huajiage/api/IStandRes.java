package com.lh_lshen.mcbbs.huajiage.api;

import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public interface IStandRes {

	String getName();
	
	ResourceLocation getTextrue();
	
	void doSoundPlay(Minecraft mc ,Entity user );
	
	void doStandRender(EntityLivingBase entity);
	
	ModelStandBase getStandModel();
	
}
