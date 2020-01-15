package com.lh_lshen.mcbbs.huajiage.capability;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CapabilitityLoader {
	
	public CapabilitityLoader(FMLPreInitializationEvent event)
    {
		 CapabilityStandHandler.register();
    	 CapabilityStandStageHandler.register();
    	 CapabilityStandChargeHandler.register();
    }

}
