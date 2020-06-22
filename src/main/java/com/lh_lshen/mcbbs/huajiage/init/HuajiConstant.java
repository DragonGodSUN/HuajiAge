package com.lh_lshen.mcbbs.huajiage.init;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStarPlatinum;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelTheWorld;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;

import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;

public  class HuajiConstant {
	public  class Tags{
//		Util
		public static final String PLAYER_UUID=HuajiAge.MODID+"."+"player_uuid";
		public static final String PLAYER_NAME=HuajiAge.MODID+"."+"player_name";
//	Stand Events
		public static final String SINGULARITY=HuajiAge.MODID+"."+"singularity";
//	Dio Bread
		public static final String TIME_STOP=HuajiAge.MODID+"."+"time_stop";
		public static final String TIME_STOP_RANGE=HuajiAge.MODID+"."+"time_stop_range";
		public static final String THE_WORLD=HuajiAge.MODID+"."+"the_world";
		public static final String THE_WORLD_RECORD=HuajiAge.MODID+"."+"the_world_record";
		public static final String DIO_FLAG=HuajiAge.MODID+"."+"dio_flag";
		public static final String DIO_HIT=HuajiAge.MODID+"."+"dio_hit";
		public static final String DIO_HIT_EXTRA=HuajiAge.MODID+"."+"dio_hit_extra";
		
		public static final int THE_WORLD_TIME=9*20;
		
//	Requiem
		public static final String REQUIEM=HuajiAge.MODID+"."+"requiem";
		public static final String REQUIEM_OWNER=HuajiAge.MODID+"."+"requiem_owner";
	}
	
//	Stand type
	public class StandType{
		public static final String STAND_TYPE=HuajiAge.MODID+"."+"stand_type";
		public static final String STAND_THE_WORLD="stand"+"."+HuajiAge.MODID+"."+"the_world";
		public static final String STAND_STAR_PLATINUM="stand"+"."+HuajiAge.MODID+"."+"star_platinum";
		public static final String STAND_HIEROPANT_GREEN="stand"+"."+HuajiAge.MODID+"."+"hierophant_green";
		public static final String STAND_ORGA_REQUIEM="stand"+"."+HuajiAge.MODID+"."+"orga_requiem";
		public static final String STAND_EMPTY="stand"+"."+HuajiAge.MODID+"."+"empty";
	}
	
	public static class StandTex{
		public static final ResourceLocation TEXTRUE_THE_WORLD = new ResourceLocation(HuajiAge.MODID, StandLoader.THE_WORLD.getTexPath());
		public static final ResourceLocation TEXTRUE_STAR_PLATINUM = new ResourceLocation(HuajiAge.MODID, StandLoader.STAR_PLATINUM.getTexPath());
		public static final ResourceLocation TEXTRUE_HIEROPANT_GREEN = new ResourceLocation(HuajiAge.MODID, StandLoader.HIEROPHANT_GREEN.getTexPath());
		public static final ResourceLocation TEXTRUE_ORGA_REQUIEM = new ResourceLocation(HuajiAge.MODID, StandLoader.ORGA_REQUIEM.getTexPath());
		
	}
	
//	DamageSource
	public class DamageSource{
	public static final String SECOND=HuajiAge.MODID+"."+"second";
	public static final String STELLA=HuajiAge.MODID+"."+"stella";
	public static final String KDJL=HuajiAge.MODID+"."+"KeDaiJinLa";
	public static final String ORGA_SHOT=HuajiAge.MODID+"."+"orga.shot";
	public static final String HOPE_FLOWER=HuajiAge.MODID+"."+"hope_flower";
	public static final String REQUIEM_BACK=HuajiAge.MODID+"."+"requiem.back";
	public static final String REQUIEM_DAMAGE=HuajiAge.MODID+"."+"requiem.hit";
	public static final String DIO_DAMAGE=HuajiAge.MODID+"."+"dio.hit";
	public static final String SINGULARITY_DAMAGE=HuajiAge.MODID+"."+"singularity";
	public static final String VOID_BREAK=HuajiAge.MODID+"."+"void_break";
	public static final String WAVE_HIT=HuajiAge.MODID+"."+"wave_hit";
	}
	
 
	public static class StandModels {
//		public final static ModelStandBase STAND_THE_WORLD = new ModelTheWorld();
//		public final static ModelStandBase STAND_STAR_PLATINUM = new ModelStarPlatinum();
		
		public final static String THE_WORLD_TEX_PATH = "textures/entity/entity_the_world.png";
		public final static String STAR_PLATINUM_TEX_PATH = "textures/entity/entity_star_platinum.png";
		
	}
}