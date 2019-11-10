package com.lh_lshen.mcbbs.huajiage.init.playsound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;

import net.minecraft.init.Bootstrap;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;


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
	public static final SoundEvent ORGA_FLOWER=registerSound("orga_flower");
	public static final SoundEvent ORGA_REQUIEM_1=registerSound("orga_requiem_1");
	public static final SoundEvent ORGA_REQUIEM_2=registerSound("orga_requiem_2");
	public static final SoundEvent ORGA_REQUIEM_GOLD=registerSound("orga_requiem_gold");
	public static final SoundEvent ORGA_REQUIEM_HIT=registerSound("orga_requiem_hit");
	public static final SoundEvent THE_WORLD=registerSound("the_world");
	public static final SoundEvent THE_WORLD_1=registerSound("the_world_1");
	public static final SoundEvent THE_WORLD_2=registerSound("the_world_2");
	public static final SoundEvent THE_WORLD_3=registerSound("the_world_3");
	public static final SoundEvent ROAD_ROLLER=registerSound("road_roller");
	public static final SoundEvent DIO_FLAG=registerSound("dio_flag");
	public static final SoundEvent DIO_HIT=registerSound("dio_hit");
	public static final SoundEvent NOISE_FURNACE=registerSound("noise_furnace");

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
