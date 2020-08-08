package com.lh_lshen.mcbbs.huajiage.client.events;

import com.lh_lshen.mcbbs.huajiage.client.KeyLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemBlancedHelmet;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageFiveBulletShoot;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageHelmetModeChange;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class EventKeyInput {
	@SubscribeEvent
	public static void modeSwitch(InputEvent.KeyInputEvent evt) {

		if(KeyLoader.modeSwitch.isPressed()) 
		{
		EntityPlayer player = Minecraft.getMinecraft().player;
		if(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemBlancedHelmet)
			{
	        	HuajiAgeNetWorkHandler.sendToServer(new MessageHelmetModeChange());
			}
		}
		
	}
	@SubscribeEvent
	public static void leftClick(PlayerInteractEvent.LeftClickEmpty evt) {
		EntityPlayer player=evt.getEntityPlayer();
		ItemStack helmet = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		if ( helmet.getItem() instanceof ItemBlancedHelmet) {
		       if(helmet.getTagCompound().hasKey("lord")&&helmet.getTagCompound().hasKey("open")&&helmet.getTagCompound().getBoolean("open")) 
		       {
		    	   HuajiAgeNetWorkHandler.sendToServer(new MessageFiveBulletShoot());
	       		}
		       
		}
	}
	
}
