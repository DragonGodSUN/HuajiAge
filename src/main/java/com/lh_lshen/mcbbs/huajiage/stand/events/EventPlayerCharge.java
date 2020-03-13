package com.lh_lshen.mcbbs.huajiage.stand.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class EventPlayerCharge {

	@SubscribeEvent
	public static void onPlayerCharging(LivingUpdateEvent evt) {
		EntityLivingBase entity = evt.getEntityLiving();
		
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			EnumStandtype stand = StandUtil.getType(player);
			if(player.hasCapability(CapabilityStandChargeHandler.STAND_CHARGE, null)) {
				StandChargeHandler chargeHandler = player.getCapability(CapabilityStandChargeHandler.STAND_CHARGE, null);
				if(null != stand ) 
					{
						StandHandler standHandler = player.getCapability(CapabilityStandHandler.STAND_TYPE, null);
						int mp = (int)(stand.getDuration()/5) + 20;
						chargeHandler.charge(mp);
					}else {
						chargeHandler.charge(5);
					}
					
				}
			
			}
		
	}
	
}
