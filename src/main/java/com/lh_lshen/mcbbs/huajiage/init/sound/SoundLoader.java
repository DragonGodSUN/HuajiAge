package com.lh_lshen.mcbbs.huajiage.init.sound;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;
import java.util.List;


@Mod.EventBusSubscriber(modid=HuajiAge.MODID)
public class SoundLoader {
	private static final List<SoundEvent> SOUND_LIST = new ArrayList<>();
	public static final SoundEvent ENERGY_HIT=registerSound("energyhit");
	public static final SoundEvent CHARGE=registerSound("charge");
	public static final SoundEvent WAVE1=registerSound("wave_01");
	public static final SoundEvent STELLA=registerSound("stella");
	public static final SoundEvent EXGLUTENBUR_1=registerSound("exglutenbur_flavor1");
	public static final SoundEvent EXGLUTENBUR_2=registerSound("exglutenbur_flavor2");
	public static final SoundEvent EXGLUTENBUR_3=registerSound("exglutenbur_flavor3");
	public static final SoundEvent EXGLUTENBUR_HIT=registerSound("exglutenbur_hit");
	public static final SoundEvent ORGA_SHOT=registerSound("orga_shot");
	public static final SoundEvent ORGA_FLOWER=registerSound("orga_flower");
	public static final SoundEvent ORGA_REQUIEM_1=registerSound("orga_requiem_1");
	public static final SoundEvent ORGA_REQUIEM_2=registerSound("orga_requiem_2");
	public static final SoundEvent ORGA_REQUIEM_3=registerSound("orga_requiem_3");
	public static final SoundEvent ORGA_REQUIEM_GOLD=registerSound("orga_requiem_gold");
	public static final SoundEvent ORGA_REQUIEM_PROTECT=registerSound("orga_requiem_protect");
	public static final SoundEvent ORGA_REQUIEM_HIT=registerSound("orga_requiem_hit");
	public static final SoundEvent ORGA_RIDER=registerSound("orga_rider");
	public static final SoundEvent THE_WORLD=registerSound("the_world");
	public static final SoundEvent THE_WORLD_1=registerSound("the_world_1");
	public static final SoundEvent THE_WORLD_2=registerSound("the_world_2");
	public static final SoundEvent THE_WORLD_3=registerSound("the_world_3");
	public static final SoundEvent THE_WORLD_RE=registerSound("the_world_re");
	public static final SoundEvent ROAD_ROLLER=registerSound("road_roller");
	public static final SoundEvent DIO_FLAG=registerSound("dio_flag");
	public static final SoundEvent DIO_HIT=registerSound("dio_hit");
	public static final SoundEvent NOISE_FURNACE=registerSound("noise_furnace");
	public static final SoundEvent STAND_THE_WORLD_HIT_1=registerSound("stand_the_world_hit_1");
	public static final SoundEvent STAND_THE_WORLD_HIT_2=registerSound("stand_the_world_hit_2");
	public static final SoundEvent STAND_STAR_PLATINUM_1=registerSound("stand_star_platinum_hit_1");
	public static final SoundEvent STAND_STAR_PLATINUM_2=registerSound("stand_star_platinum_hit_2");
	public static final SoundEvent STAND_STAR_PLATINUM_3=registerSound("stand_star_platinum_hit_3");
	public static final SoundEvent STAND_STAR_PLATINUM_4=registerSound("stand_star_platinum_hit_4");
	public static final SoundEvent STAND_STAR_PLATINUM_5=registerSound("stand_star_platinum_hit_5");
	public static final SoundEvent STAR_PLATINUM_THE_WORLD_1=registerSound("star_platinum_the_world_1");
	public static final SoundEvent STAND_STAR_PLATINUM_REPEAT_1=registerSound("stand_star_platinum_repeat_1");
	public static final SoundEvent STAND_HIEROPHANT_GREEN_SHOOT_1=registerSound("stand_hierophant_green_shoot_1");
	public static final SoundEvent STAND_HIEROPHANT_GREEN_SHOOT_2=registerSound("stand_hierophant_green_shoot_2");
	public static final SoundEvent STAND_HIEROPHANT_GREEN_EMERALD_SPLASH=registerSound("stand_hierophant_green_emerald_splash");
	public static final SoundEvent STAND_KILLER_QUEEN_TRIGGER=registerSound("stand_killer_queen_trigger");
	public static final SoundEvent STAND_KILLER_QUEEN_SHOW_1=registerSound("stand_killer_queen_show_1");
	public static final SoundEvent STAND_KILLER_QUEEN_SHOW_2=registerSound("stand_killer_queen_show_2");
	public static final SoundEvent STAND_CRAZY_DIAMOND_1=registerSound("stand_crazy_diamond_1");
	public static final SoundEvent STAND_CRAZY_DIAMOND_2=registerSound("stand_crazy_diamond_2");
	public static final SoundEvent STAND_CRAZY_DIAMOND_3=registerSound("stand_crazy_diamond_3");
	public static final SoundEvent STAND_CRAZY_DIAMOND_4=registerSound("stand_crazy_diamond_4");
	public static final SoundEvent STAND_CRAZY_DIAMOND_REPAIR_1=registerSound("stand_crazy_diamond_repair_1");
	public static final SoundEvent STAND_CRAZY_DIAMOND_REPAIR_2=registerSound("stand_crazy_diamond_repair_2");
	public static final SoundEvent STAND_HERMIT_PURPLE_1=registerSound("stand_hermit_purple_1");
	public static final SoundEvent STAND_HERMIT_PURPLE_2=registerSound("stand_hermit_purple_2");
	public static final SoundEvent STAND_HERMIT_PURPLE_WAVE=registerSound("stand_hermit_purple_wave");
	public static final SoundEvent STAND_HERMIT_PURPLE_CAMERA_BROKEN=registerSound("stand_hermit_purple_camera_broken");
	public static final SoundEvent STAND_WHITE_SNAKE_1=registerSound("stand_white_snake_1");
	public static final SoundEvent STAND_WHITE_SNAKE_2=registerSound("stand_white_snake_2");
	public static final SoundEvent STAND_WHITE_SNAKE_3=registerSound("stand_white_snake_3");
	public static final SoundEvent STAND_WHITE_SNAKE_HIT_1=registerSound("stand_white_snake_hit_1");
	public static final SoundEvent STAND_WHITE_SNAKE_HIT_2=registerSound("stand_white_snake_hit_2");
	public static final SoundEvent STAND_WHITE_SNAKE_HIT_3=registerSound("stand_white_snake_hit_3");
	public static final SoundEvent STAND_WHITE_SNAKE_MAKE_DISC_1=registerSound("stand_white_snake_make_disc_1");
	public static final SoundEvent STAND_WHITE_SNAKE_MAKE_DISC_2=registerSound("stand_white_snake_make_disc_2");
	public static final SoundEvent SHEER_HEART_ATTACK=registerSound("sheer_heart_attack_target");
	public static final SoundEvent BIKE_RING_1=registerSound("bike_ring_ear0");
	public static final SoundEvent REO_CHERRY=registerSound("reo_cherry");
	public static final SoundEvent WAVE_OVERDRIVE_1=registerSound("wave_overdrive_1");
	public static final SoundEvent WAVE_OVERDRIVE_RUN=registerSound("wave_overdrive_run");
	@SubscribeEvent
	public static void register(RegistryEvent.Register<SoundEvent> event) {
		for(SoundEvent sound:SOUND_LIST){
			event.getRegistry().register(sound);

		}

	}
	private static SoundEvent registerSound(String name){
		SoundEvent event =new SoundEvent(new ResourceLocation(HuajiAge.MODID,name)).setRegistryName(HuajiAge.MODID,name);
		SOUND_LIST.add(event);
		return event;

	}
	public static SoundEvent getSoundByIndex(int index)
	{
		return SOUND_LIST.get(index);

	}
	public static SoundEvent getSound(String name)
	{
		SoundEvent s=null;
		for(int i=0;i<SOUND_LIST.size();i++) {
			if(getSoundByIndex(i).getRegistryName().toString().equals(HuajiAge.MODID+":"+name)) {
				s=getSoundByIndex(i);
			}
		}
		return s;
	}
}
