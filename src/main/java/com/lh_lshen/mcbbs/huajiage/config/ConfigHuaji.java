package com.lh_lshen.mcbbs.huajiage.config;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;

import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Config.LangKey;
import net.minecraftforge.common.config.Config.Name;
import net.minecraftforge.common.config.Config.RangeDouble;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid=HuajiAge.MODID,name=HuajiAge.NAME)
public class ConfigHuaji {
	@LangKey("config.huaji_age.huaji_config")
	@Name("Huaji Age")
	public static HuajiConfig Huaji=new HuajiConfig();
	
	@LangKey("config.huaji_age.stands_config")
	@Name("Stands")
	public static StandConfig Stands=new StandConfig();

	public static class HuajiConfig{
		@Config.Comment("The Terrain destruction of the big explosion created by the big hero's bow")
		@LangKey("config.huaji_age.huaji_config.hero_explode")
		public boolean heroExplode=false;

		@Config.Comment("Dose The Infinite Charm Change mode when dress Orga suit?")
		@LangKey("config.huaji_age.huaji_config.orga_suit")
		public boolean orgaSuit=true;
		
		@Config.Comment("Use the music of orga flower?")
		@LangKey("config.huaji_age.huaji_config.orga.flower")
		public boolean useOrgaFlower=false;
		
		@Config.Comment("The X position of Wave Knife Using on HUD")
		@LangKey("config.huaji_age.huaji_config.wave_knife_HUD_x")
		@RangeDouble(min = 0, max = 1f)
		public double WaveHUDx = 0f;
		
		@Config.Comment("The Y position of Wave Knife Using on HUD")
		@LangKey("config.huaji_age.huaji_config.wave_knife_HUD_y")
		@RangeDouble(min = 0, max = 1f)
		public double WaveHUDy = 0.2f;
		
	}
	public static class StandConfig{
		@Config.Comment("The Terrain destruction of the big explosion created by road roller")
		@LangKey("config.huaji_age.huaji_config.road_roller_explosion")
		public boolean roadRolerExplosion=true;
		
		@Config.Comment("Can THE WORLD stop the time of players?")
		@LangKey("config.huaji_age.huaji_config.the_world")
		public boolean allowTimeStopPlayer=true;
		
		@Config.Comment("Can THE WORLD Destory blocks?")
		@LangKey("config.huaji_age.huaji_config.the_world.blocks")
		public boolean allowTheWorldDestory=true;
		
		@Config.Comment("To punish the stand users who out of time")
		@LangKey("config.huaji_age.huaji_config.stand.punish.out_time")
		public boolean allowStandPunish=false;

		@Config.Comment("To glow the stand users who idle its stand")
		@LangKey("config.huaji_age.huaji_config.stand.punish.glow")
		public boolean allowStandGlow=false;
		
		@Config.Comment("Need some tips to notice you how to use STANDs?")
		@LangKey("config.huaji_age.huaji_config.stand.tip")
		public boolean allowStandTip=true;
		
		@Config.Comment("Need some tips to notice you when you lost STANDs?")
		@LangKey("config.huaji_age.huaji_config.stand.tip.lost")
		public boolean allowStandLostTip=false;
		
		@Config.Comment("Need the moving sound for you stand?")
		@LangKey("config.huaji_age.huaji_config.stand.moving_sound")
		public boolean allowStandMovingSound=true;
		
		@Config.Comment("Need the sound playing for you stand?")
		@LangKey("config.huaji_age.huaji_config.stand.moving_sound")
		public boolean allowStandSound=true;
		
		@Config.Comment("Need the fog of time stop?")
		@LangKey("config.huaji_age.huaji_config.stand.fog_time_stop")
		public boolean allowFogTimeStop=false;
		
		@Config.Comment("Need the mask of time stop?")
		@LangKey("config.huaji_age.huaji_config.stand.mask_time_stop")
		public boolean allowMaskTimeStop=true;
		
		@Config.Comment("Use the noise version mask of time stop?")
		@LangKey("config.huaji_age.huaji_config.stand.mask_time_stop_noise")
		public boolean useTimeStopNoiseMask=true;
		
		@Config.Comment("Use the HUAJI splash replace the image of emerald splash?")
		@LangKey("config.huaji_age.huaji_config.stand.huaji_splash")
		public boolean useHuajiSplash=false;
		
		@Config.Comment("The flight height of the multi knife")
		@LangKey("config.huaji_age.huaji_config.height_knife")
		@RangeDouble(min = -0.5f, max = 0.5f)
		public double knifeHeight = -0.25f;
		
		@Config.Comment("The chance for weak up stand fail")
		@LangKey("config.huaji_age.huaji_config.stand.weak_up_fail")
		@RangeDouble(min =0, max =1)
		public double chanceStandFail = 0.3;
		
		@Config.Comment("The X position of Stand on HUD")
		@LangKey("config.huaji_age.huaji_config.stand_HUD_x")
		@RangeDouble(min = 0, max = 1f)
		public double StandHUDx = 0f;
		
		@Config.Comment("The Y position of Stand on HUD")
		@LangKey("config.huaji_age.huaji_config.stand_HUD_y")
		@RangeDouble(min = 0, max = 1.0f)
		public double StandHUDy = 0.7f;
		
		@Config.Comment("The scale of effect icon of time stop")
		@LangKey("config.huaji_age.huaji_config.icon_time_stop")
		@RangeDouble(min = 0, max = 5.0)
		public double TimeStopScale = 1.0;
		
		@Config.Comment("The density of fog in time stop")
		@LangKey("config.huaji_age.huaji_config.density_fog_time_stop")
		@RangeDouble(min = 0, max = 0.001f)
		public float TimeStopFog = 0.0005f;
		
		
	}
	 @Mod.EventBusSubscriber(modid = HuajiAge.MODID)
	    public static class ConfigSyncHandler {
	        @SubscribeEvent
	        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
	            if (event.getModID().equals(HuajiAge.MODID)) {
	                ConfigManager.sync(HuajiAge.MODID, Config.Type.INSTANCE);
	                HuajiAge.LOGGER.info(I18n.format("huajiage.welcome.1",HuajiAge.VERSION));
	            }
	        }
	    }
}
