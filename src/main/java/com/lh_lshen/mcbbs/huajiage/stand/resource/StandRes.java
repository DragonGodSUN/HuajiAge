package com.lh_lshen.mcbbs.huajiage.stand.resource;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.api.IStandRes;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
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

	@Deprecated
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
	public ModelStandBase getStandModelByData(EntityLivingBase user) {
		String stand = StandUtil.getType(user).getName();
		String state = StandUtil.getStandState(user);
		String id = HuajiAge.MODID+":"+stand;
		String id_state = HuajiAge.MODID+":"+stand+"_"+state;
		switch (state){
			case "default":
				return StandClientUtil.getModelByID(id,false);
			default:
				return StandClientUtil.getModelByID(id_state,false);
		}
	}


}
