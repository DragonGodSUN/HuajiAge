package com.lh_lshen.mcbbs.huajiage.init.loaders;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.potion.*;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PotionLoader {

//	 public static Potion potionHuajiProtection;
	 public static Potion potionfive;
	 public static Potion potionFlowerHope;
	 public static Potion potionOrgaTarget;
	 public static Potion potionRequiem;
	 public static Potion potionRequiemTarget;
	 public static Potion potionStand;
	 public static Potion potionRepair;
	 public static Potion potionOverdrive;


	    @SubscribeEvent
	    public static void onPotionRegistration(RegistryEvent.Register<Potion> event) {
//	    	potionHuajiProtection=new PotionHuajiProtection() ;
	    	potionfive=new PotionFiveBuff() ;
	    	potionFlowerHope=new PotionFlowerHope() ;
	    	potionOrgaTarget=new PotionOrgaTarget() ;
	    	potionRequiem=new PotionRequiem() ;
	    	potionRequiemTarget=new PotionRequiemTarget() ;
	    	potionStand=new PotionStand();
	    	potionRepair=new PotionRepairEffect();
	    	potionOverdrive=new PotionOverdrive();


	        event.getRegistry().registerAll(
//	        			potionHuajiProtection.setRegistryName(HuajiAge.MODID, "potion_huaji"),
						potionfive.setRegistryName(HuajiAge.MODID, "potion_huaji_five_buff"),
						potionFlowerHope.setRegistryName(HuajiAge.MODID, "potion_huaji_flower"),
						potionOrgaTarget.setRegistryName(HuajiAge.MODID, "potion_huaji_orga_target"),
						potionRequiem.setRegistryName(HuajiAge.MODID, "potion_huaji_requiem"),
						potionRequiemTarget.setRegistryName(HuajiAge.MODID, "potion_huaji_requiem_target"),
						potionStand.setRegistryName(HuajiAge.MODID, "potion_huaji_stand"),
						potionRepair.setRegistryName(HuajiAge.MODID, "potion_huaji_repair"),
						potionOverdrive.setRegistryName(HuajiAge.MODID, "potion_huaji_overdrive")
	        		);

	    }

}
