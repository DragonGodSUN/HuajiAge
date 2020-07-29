package com.lh_lshen.mcbbs.huajiage.stand;

import com.lh_lshen.mcbbs.huajiage.api.HuajiAgeAPI;
import com.lh_lshen.mcbbs.huajiage.api.IStand;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.stand.instance.*;

import java.util.ArrayList;
import java.util.List;

public class StandLoader {
	public static final List<StandBase> STAND_LIST = new ArrayList<>();
	public static final String EMPTY = "empty";
//	public static final StandBase STAND_EMPTY = new StandBase("empty",0,0,0,0,0,"",HuajiConstant.StandType.STAND_EMPTY);
	public static final StandTheWorld THE_WORLD = new StandTheWorld("the_world",1.2f,10f,200,2f,60000,75,"textures/entity/entity_the_world_default.png",HuajiConstant.StandType.STAND_THE_WORLD, false);
	public static final StandStarPlatinum STAR_PLATINUM = new StandStarPlatinum("star_platinum",1.5f,15f,275,2f,50000,95,"textures/entity/entity_star_platinum_default.png",HuajiConstant.StandType.STAND_STAR_PLATINUM, false);
	public static final StandHierophantGreen HIEROPHANT_GREEN = new StandHierophantGreen("hierophant_green",1.0f,5f,200,20f,70000,80,"textures/entity/entity_hierophant_green_default.png",HuajiConstant.StandType.STAND_HIEROPANT_GREEN, true);
	public static final StandOrgaRequiem ORGA_REQUIEM = new StandOrgaRequiem("orga_requiem",0.04f,0f,650,50f,80000,70,"textures/entity/entity_orga_requiem_default.png",HuajiConstant.StandType.STAND_ORGA_REQUIEM, true);
	public static final StandKillerQueen KILLER_QUEEN = new StandKillerQueen("killer_queen",0.6f,8f,300,100f,60000,80,"textures/entity/entity_killer_queen_default.png",HuajiConstant.StandType.STAND_KILLER_QUEEN, true);
	
	public StandLoader() {
		registerStand(THE_WORLD);
		registerStand(STAR_PLATINUM);
		registerStand(HIEROPHANT_GREEN);
		registerStand(ORGA_REQUIEM);
		registerStand(KILLER_QUEEN);
		for(StandBase stand:STAND_LIST) {
			HuajiAgeAPI.registerStand(stand);
		}
	}
	
	private static void registerStand(StandBase stand){
		STAND_LIST.add(stand);
	}
	
	public static StandBase getStandByIndex(int index)
	{
		List<IStand> list = HuajiAgeAPI.getStandList();
		if (list.get(index) instanceof StandBase) {
			StandBase stand = (StandBase) list.get(index);
			return stand;
		} else {
			return null;
		}

	}
	public static StandBase getStand(String name)
	{
		StandBase s=null;
		List<IStand> list = HuajiAgeAPI.getStandList();
		if (list!=null) {
			for(int i=0;i<list.size();i++) {
				if(getStandByIndex(i).getName().equals(name)) {
					s=getStandByIndex(i);
				}
			}
		}
		return s;
	}

}
