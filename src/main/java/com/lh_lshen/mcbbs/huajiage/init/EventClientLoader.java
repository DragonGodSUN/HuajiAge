package com.lh_lshen.mcbbs.huajiage.init;

import com.lh_lshen.mcbbs.huajiage.client.events.EventKeyInput;
import com.lh_lshen.mcbbs.huajiage.client.events.EventToolTip;
import com.lh_lshen.mcbbs.huajiage.particle.ParticleLoader;
import com.lh_lshen.mcbbs.huajiage.stand.events.EventStandOverlatRender;
import com.lh_lshen.mcbbs.huajiage.stand.events.EventViewRender;

import net.minecraftforge.common.MinecraftForge;

public class EventClientLoader {
	
	public EventClientLoader() {
		MinecraftForge.EVENT_BUS.register(EventToolTip.class);
//		MinecraftForge.EVENT_BUS.register(ParticleLoader.class);
		MinecraftForge.EVENT_BUS.register(EventKeyInput.class);
//		MinecraftForge.EVENT_BUS.register(EventStandOverlatRender.class);
//		MinecraftForge.EVENT_BUS.register(EventViewRender.class);
	}

}
