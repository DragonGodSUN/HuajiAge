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
		public boolean heroExplode=true;

		@Config.Comment("Dose The Infinite Charm Change mode when dress Orga suit?")
		@LangKey("config.huaji_age.huaji_config.orga_suit")
		public boolean orgaSuit=true;
		
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
		
		@Config.Comment("Need some tips to notice you how to use STANDs?")
		@LangKey("config.huaji_age.huaji_config.stand.tip")
		public boolean allowStandTip=true;
		
		@Config.Comment("The flight height of the multi knife")
		@LangKey("config.huaji_age.huaji_config.height_knife")
		@RangeDouble(min = 0, max = 0.5f)
		public double knifeHeight = -0.25f;
		
		@Config.Comment("The X position of Stand on HUD")
		@LangKey("config.huaji_age.huaji_config.stand_HUD_x")
		@RangeDouble(min = 0, max = 1f)
		public double StandHUDx = 0f;
		
		@Config.Comment("The Y position of Stand on HUD")
		@LangKey("config.huaji_age.huaji_config.stand_HUD_y")
		@RangeDouble(min = 0, max = 1.0f)
		public double StandHUDy = 0.7f;
		
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
