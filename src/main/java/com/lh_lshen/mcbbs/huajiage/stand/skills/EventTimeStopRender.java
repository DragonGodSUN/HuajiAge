package com.lh_lshen.mcbbs.huajiage.stand.skills;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

//@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class EventTimeStopRender {

//	@SubscribeEvent
//	public static void TimeStopEffect(LivingUpdateEvent evt) {
//		EntityLivingBase entity = evt.getEntityLiving();
//		int time = (int) entity.getEntityWorld().getWorldTime();
//		int ticks = NBTHelper.getEntityInteger(entity, HuajiConstant.THE_WORLD_RECORD);
//		int stop_time = NBTHelper.getEntityInteger(entity, HuajiConstant.THE_WORLD);
//		if(stop_time>10) 
//		{
//			if(ticks==0) 
//			{
//				NBTHelper.setEntityInteger(entity, HuajiConstant.THE_WORLD_RECORD, time);
//			}
//			if(time < 13000) {
//				entity.getEntityWorld().setWorldTime(18000);
//			}
//		}else if(stop_time>5) 
//		{
//			entity.getEntityWorld().setWorldTime(ticks);
//		}else if(stop_time>0) {
//			int newTime = (int) entity.getEntityWorld().getWorldTime();
//			NBTHelper.setEntityInteger(entity, HuajiConstant.THE_WORLD_RECORD, newTime);
//			}
//	}

}
