package com.lh_lshen.mcbbs.huajiage.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CapabilityLoader {
	@CapabilityInject(IExposedData.class)
	public static Capability<IExposedData> EXPOSED_DATA = null;
	
	public CapabilityLoader(FMLPreInitializationEvent event)
    {
		CapabilityManager.INSTANCE.register(IExposedData.class, new CapabilityExposedData.Storage(),
				CapabilityExposedData.Implementation.FACTORY);
		
//		 CapabilityStandHandler.register();
//    	 CapabilityStandStageHandler.register();
//    	 CapabilityStandBuffHandler.register();
    	 CapabilityStandChargeHandler.register();
    }

}
