package com.lh_lshen.mcbbs.huajiage.init.loaders;

import com.lh_lshen.mcbbs.huajiage.client.events.EventKeyInput;
import com.lh_lshen.mcbbs.huajiage.client.events.EventPlayerRotation;
import com.lh_lshen.mcbbs.huajiage.client.events.EventToolTip;
import net.minecraftforge.common.MinecraftForge;

public class EventClientLoader {
	
	public EventClientLoader() {
		MinecraftForge.EVENT_BUS.register(EventToolTip.class);
//		MinecraftForge.EVENT_BUS.register(ParticleLoader.class);
		MinecraftForge.EVENT_BUS.register(EventKeyInput.class);
		MinecraftForge.EVENT_BUS.register(EventPlayerRotation.class);
//		MinecraftForge.EVENT_BUS.register(EventStandOverlatRender.class);
//		MinecraftForge.EVENT_BUS.register(EventViewRender.class);
	}

}
