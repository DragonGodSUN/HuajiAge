package com.lh_lshen.mcbbs.huajiage.stand.resource;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.api.IStandRes;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.HAModelFactory;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.stand.StandStates;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class StandRes implements IStandRes {
	protected String name;
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
	public ResourceLocation getTextureByData(EntityLivingBase user) {
		StandBase stand = StandUtil.getType(user);
		String state = StandUtil.getStandState(user);
		if (stand!=null&&state!=null) {
			StandStateBase state_base = StandStates.getStandState(stand.getName(),state);
			if(state_base!=null){
				return HAModelFactory.getTexture(state_base.getModelID());
			}
		}
		return new ResourceLocation(HuajiAge.MODID,"textures/entity/entity_the_world_default.png");
	}

	@Override
	public ResourceLocation getTextureByData(String state) {
		StandStateBase state_base = StandStates.getStandState(name,state);
		if(state_base!=null){
			return HAModelFactory.getTexture(state_base.getModelID());
		}
		return new ResourceLocation(HuajiAge.MODID,"textures/entity/entity_the_world_default.png");
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
		StandBase stand = StandUtil.getType(user);
		String state = StandUtil.getStandState(user);
		if (stand!=null&&state!=null) {
			StandStateBase state_base = StandStates.getStandState(stand.getName(),state);
			if(state_base!=null){
				return StandClientUtil.getModelByID(state_base.getModelID());
			}
		}
		return StandClientUtil.getModelByID(HuajiConstant.StandModels.DEFAULT_MODEL_ID);
	}


}
