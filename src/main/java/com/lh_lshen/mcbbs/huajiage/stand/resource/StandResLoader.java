package com.lh_lshen.mcbbs.huajiage.stand.resource;

import java.util.ArrayList;
import java.util.List;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.api.HuajiAgeAPI;
import com.lh_lshen.mcbbs.huajiage.api.IStandPower;
import com.lh_lshen.mcbbs.huajiage.api.IStandRes;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandHierophantGreen;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandStarPlatinum;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandTheWorld;
import com.lh_lshen.mcbbs.huajiage.stand.resource.ResStandHierophantGreen;
import com.lh_lshen.mcbbs.huajiage.stand.resource.ResStandStarPlatinum;
import com.lh_lshen.mcbbs.huajiage.stand.resource.ResStandTheWorld;

import net.minecraft.stats.StatList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

public class StandResLoader {
	public static final List<StandRes> STAND_RES_LIST = new ArrayList<>();
//	public static final StandBase STAND_EMPTY = new StandBase("empty",0,0,0,0,0,"",HuajiConstant.StandType.STAND_EMPTY);
	public static final StandRes THE_WORLD_RES = new ResStandTheWorld("the_world");
	public static final StandRes STAR_PLATINUM_RES = new ResStandStarPlatinum("star_platinum");
	public static final StandRes HIEROPHANT_GREEN_RES = new ResStandHierophantGreen("hierophant_green");
	public static final StandRes ORGA_REQUIEM_RES = new ResStandOrgaRequiem("orga_requiem");
	
	public StandResLoader() {
		register(THE_WORLD_RES);
		register(STAR_PLATINUM_RES);
		register(HIEROPHANT_GREEN_RES);
		register(ORGA_REQUIEM_RES);
	}
	
	private static void register(StandRes stand){
		STAND_RES_LIST.add(stand);
	}
	
	public static StandRes getResByIndex(int index)
	{
		return STAND_RES_LIST.get(index);
		
	}
	public static StandRes getStand(String name)
	{
		StandRes s=null;
		for(int i=0;i<STAND_RES_LIST.size();i++) {
			if(getResByIndex(i).getName().toString().equals(name)) {
				s=getResByIndex(i);
			}
		}
		return s;
	}

}
