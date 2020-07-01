package com.lh_lshen.mcbbs.huajiage.init.sound;

import java.util.ArrayList;
import java.util.List;

import com.ibm.icu.util.RangeValueIterator.Element;

import net.minecraft.util.SoundEvent;

public class SoundStand {
	public static final List<SoundEvent> STAND_SOUND_LIST = new ArrayList<>();
	
	public static final List<SoundEvent> HIEROPANT_SOUND_LIST = getGroup(new ArrayList<SoundEvent>(),
			SoundLoader.STAND_HIEROPHANT_GREEN_SHOOT_1 , SoundLoader.STAND_HIEROPHANT_GREEN_SHOOT_2);
	
	public static final List<SoundEvent> WORLD_SOUND_LIST = getGroup(new ArrayList<SoundEvent>(),
			SoundLoader.STAND_THE_WORLD_HIT_1 , SoundLoader.STAND_THE_WORLD_HIT_2);
	
	public static final List<SoundEvent> STAR_SOUND_LIST = getGroup(new ArrayList<SoundEvent>(),
			SoundLoader.STAND_STAR_PLATINUM_1 , SoundLoader.STAND_STAR_PLATINUM_2,
			SoundLoader.STAND_STAR_PLATINUM_3,SoundLoader.STAND_STAR_PLATINUM_4);
	
	public static final List<SoundEvent> ORGA_SOUND_LIST = getGroup(new ArrayList<SoundEvent>(),
			SoundLoader.ORGA_REQUIEM_2 ,SoundLoader.ORGA_REQUIEM_3);
	
	public static final List<SoundEvent> KILLER_QUEEN_SOUND_LIST = getGroup(new ArrayList<SoundEvent>());
	
	private static List<SoundEvent> getGroup(List<SoundEvent> list ,SoundEvent... elements){
		List<SoundEvent> new_list = list;
		for(SoundEvent element : elements) {
			new_list.add(element);
		}
		return new_list;
	}
}
