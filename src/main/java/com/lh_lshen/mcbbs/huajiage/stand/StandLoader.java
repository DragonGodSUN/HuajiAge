package com.lh_lshen.mcbbs.huajiage.stand;

import java.util.ArrayList;
import java.util.List;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.api.HuajiAgeAPI;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.stand.helper.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.helper.instance.StandHieropantGreen;
import com.lh_lshen.mcbbs.huajiage.stand.helper.instance.StandStarPlatinum;
import com.lh_lshen.mcbbs.huajiage.stand.helper.instance.StandTheWorld;

import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class StandLoader {
	private static final List<StandBase> STAND_LIST = new ArrayList<>();
	public static final StandBase THE_WORLD = registerStand("the_world",1.2f,10f,200,2f,60000,"textures/entity/entity_the_world.png",HuajiConstant.StandType.STAND_THE_WORLD);
	public static final StandBase STAR_PLATINUM = registerStand("star_platinum",1.5f,15f,275,2f,50000,"textures/entity/entity_star_platinum.png",HuajiConstant.StandType.STAND_STAR_PLATINUM);
	public static final StandBase HIEROPHANT_GREEN = registerStand("hierophant_green",1.0f,5f,200,20f,70000,"textures/entity/entity_hierophant_green.png",HuajiConstant.StandType.STAND_HIEROPANT_GREEN);
	
	public StandLoader() {
		for(StandBase stand:STAND_LIST) {
			HuajiAgeAPI.registerStand(stand);
		}
	}
	
	private static StandBase registerStand(String name ,float speed ,float damage ,int duration ,float distance ,int cost,
			String texPath,String localName){
		StandBase stand =new StandBase(name, speed, damage, duration, distance, cost, texPath, localName);
		STAND_LIST.add(stand);
		return stand;
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
