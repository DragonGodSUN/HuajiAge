package com.lh_lshen.mcbbs.huajiage.api;

import java.util.List;
//A test structure leaned from @Tartaric_Acid and Sonwnee, follow the MIT License
//Learn More : https://github.com/TartaricAcid/TouhouLittleMaid
public class HuajiAgeAPI {
	private static IHuajiAgeAPI INSTANCE = null;
	
	 public static void setInstance(IHuajiAgeAPI instance) {
	        INSTANCE = instance;
    }
	 
	public static void registerStand(IStandPower stand) {
        INSTANCE.registerStand(stand);
    }

    public static List<IStandPower> getStandList() {
        return INSTANCE.getStandList();
    }
	 
    public static void registerMultiBlock(IMultiBlock multiBlock) {
        INSTANCE.registerMultiBlock(multiBlock);
    }

    public static List<IMultiBlock> getMultiBlockList() {
        return INSTANCE.getMultiBlockList();
    }
	
	public interface IHuajiAgeAPI{
		
		 void registerMultiBlock(IMultiBlock multiBlock);

         List<IMultiBlock> getMultiBlockList();
         
         void registerStand(IStandPower stand);
         
         List<IStandPower> getStandList();
         
	}
	
}

	