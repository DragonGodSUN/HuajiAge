package com.lh_lshen.mcbbs.huajiage.particle;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ParticleLoader {
	public ParticleLoader() {
		for(EnumHuajiPraticle particle : EnumHuajiPraticle.values()) {
			Minecraft.getMinecraft().effectRenderer.registerParticle(particle.getId(), particle.getParticle());
		}
	}
	

}
