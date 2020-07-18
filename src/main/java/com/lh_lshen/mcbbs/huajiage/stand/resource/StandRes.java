package com.lh_lshen.mcbbs.huajiage.stand.resource;

import com.lh_lshen.mcbbs.huajiage.api.IStandRes;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class StandRes implements IStandRes {
	private String name;
	public StandRes() {
	}
	public StandRes(String name) {
		this.name=name;
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public ResourceLocation getTexture() {
		return null;
	}

	@Override
	public void doSoundPlay(Minecraft mc, Entity user) {

	}

	@Override
	public void doSoundPlay(Minecraft mc, Entity entity ,EntityLivingBase user) {

	}

	@Override
	public void doStandRender(EntityLivingBase entity) {
		
	}

	@Override
	public ModelStandBase getStandModel() {
		return null;
	}

	@Override
	public ModelStandBase getStandModelByData(EntityLivingBase entity) {
		return null;
	}


}
