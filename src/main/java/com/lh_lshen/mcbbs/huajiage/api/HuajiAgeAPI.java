package com.lh_lshen.mcbbs.huajiage.api;

import java.util.List;
//A test structure leaned from @Tartaric_Acid and Sonwnee, follow the MIT License
//Learn More : https://github.com/TartaricAcid/TouhouLittleMaid
public class HuajiAgeAPI {
	private static IHuajiAgeAPI INSTANCE = null;
	
	 public static void setInstance(IHuajiAgeAPI instance) {
	        INSTANCE = instance;
    }
	 
	public static void registerStand(IStand stand) {
        INSTANCE.registerStand(stand);
    }

    public static List<IStand> getStandList() {
        return INSTANCE.getStandList();
    }

    public static void registerStandState(IStandState state) {
        INSTANCE.registerStandState(state);
    }

    public static List<IStandState> getStandStateList() {
        return INSTANCE.getStandStateList();
    }

    public static void registerMultiBlock(IMultiBlock multiBlock) {
        INSTANCE.registerMultiBlock(multiBlock);
    }

    public static List<IMultiBlock> getMultiBlockList() {
        return INSTANCE.getMultiBlockList();
    }

	public static void standClear() {
        INSTANCE.standClear();
    }

    public static void statesClear() {
        INSTANCE.statesClear();
    }

	
	public interface IHuajiAgeAPI{
		
		 void registerMultiBlock(IMultiBlock multiBlock);

         List<IMultiBlock> getMultiBlockList();
         
         void registerStand(IStand stand);
         
         List<IStand> getStandList();

        void registerStandState(IStandState state);

        List<IStandState> getStandStateList();

        void standClear();

        void statesClear();

	}
	
}

	