package com.lh_lshen.mcbbs.huajiage.init.events;

import com.lh_lshen.mcbbs.huajiage.client.KeyLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemBlancedHelmet;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.MessageKeyMode;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventKeyInput {
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void keyInput(InputEvent.KeyInputEvent evt) {

		if(KeyLoader.swich1.isPressed()) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		 if(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemBlancedHelmet) {
            HuajiAgeNetWorkHandler.sendToServer(new MessageKeyMode());
		 }
		}
	  }
}
