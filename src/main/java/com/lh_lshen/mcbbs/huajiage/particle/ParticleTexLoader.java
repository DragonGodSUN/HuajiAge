package com.lh_lshen.mcbbs.huajiage.particle;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ParticleTexLoader {
	 @SubscribeEvent
	  public void stitcherEventPre(TextureStitchEvent.Pre event) {
	    ResourceLocation flameH = new ResourceLocation("huajiage:entity/flame_huaji");
	    event.getMap().registerSprite(flameH);
	  }

}
