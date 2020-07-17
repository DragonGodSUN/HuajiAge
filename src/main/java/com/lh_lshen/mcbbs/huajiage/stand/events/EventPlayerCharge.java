package com.lh_lshen.mcbbs.huajiage.stand.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.*;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;

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
			StandBase stand = StandUtil.getType(player);
			if(player.hasCapability(CapabilityStandChargeHandler.STAND_CHARGE, null)) {
				IExposedData data = StandUtil.getStandData(player);
				StandChargeHandler chargeHandler = player.getCapability(CapabilityStandChargeHandler.STAND_CHARGE, null);
				if(null != stand && null !=data && null !=chargeHandler )
					{
						StandHandler standHandler = player.getCapability(CapabilityStandHandler.STAND_TYPE, null);
						int mp =stand.getCharge();
						chargeHandler.charge(mp+(CapabilityExposedData.States.IDLE.equals(data.getState())?mp:0));
					}else {
						chargeHandler.charge(5);
					}
					
				}
			
			}
		
	}
	
}
