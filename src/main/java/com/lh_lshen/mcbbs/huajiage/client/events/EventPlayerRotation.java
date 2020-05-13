package com.lh_lshen.mcbbs.huajiage.client.events;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventPlayerRotation {
	
	@SubscribeEvent
	public static void playerRotation(RenderPlayerEvent.Pre evt) {
		EntityPlayer player = evt.getEntityPlayer();
		RenderPlayer render = evt.getRenderer();
	}
}
