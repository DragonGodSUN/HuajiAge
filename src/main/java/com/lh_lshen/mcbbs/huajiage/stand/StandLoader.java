package com.lh_lshen.mcbbs.huajiage.stand;

import java.util.ArrayList;
import java.util.List;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.api.HuajiAgeAPI;
import com.lh_lshen.mcbbs.huajiage.api.IStandPower;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.stand.helper.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.helper.instance.StandHierophantGreen;
import com.lh_lshen.mcbbs.huajiage.stand.helper.instance.StandStarPlatinum;
import com.lh_lshen.mcbbs.huajiage.stand.helper.instance.StandTheWorld;

import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class StandLoader {
	public static final List<StandBase> STAND_LIST = new ArrayList<>();
	public static final String EMPTY = "empty";
//	public static final StandBase STAND_EMPTY = new StandBase("empty",0,0,0,0,0,"",HuajiConstant.StandType.STAND_EMPTY);
	public static final StandTheWorld THE_WORLD = new StandTheWorld("the_world",1.2f,10f,200,2f,60000,"textures/entity/entity_the_world.png",HuajiConstant.StandType.STAND_THE_WORLD);
	public static final StandStarPlatinum STAR_PLATINUM = new StandStarPlatinum("star_platinum",1.5f,15f,275,2f,50000,"textures/entity/entity_star_platinum.png",HuajiConstant.StandType.STAND_STAR_PLATINUM);
	public static final StandHierophantGreen HIEROPHANT_GREEN = new StandHierophantGreen("hierophant_green",1.0f,5f,200,20f,70000,"textures/entity/entity_hierophant_green.png",HuajiConstant.StandType.STAND_HIEROPANT_GREEN);
	
	public StandLoader() {
		registerStand(THE_WORLD);
		registerStand(STAR_PLATINUM);
		registerStand(HIEROPHANT_GREEN);
		for(StandBase stand:STAND_LIST) {
			HuajiAgeAPI.registerStand(stand);
		}
	}
	
	private static void registerStand(StandBase stand){
		STAND_LIST.add(stand);
	}
	
	public static StandBase getStandByIndex(int index)
	{
		return STAND_LIST.get(index);
		
	}
	public static StandBase getStand(String name)
	{
		StandBase s=null;
		for(int i=0;i<STAND_LIST.size();i++) {
			if(getStandByIndex(i).getName().toString().equals(name)) {
				s=getStandByIndex(i);
			}
		}
		return s;
	}

}
