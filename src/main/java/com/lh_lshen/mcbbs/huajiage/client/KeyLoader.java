package com.lh_lshen.mcbbs.huajiage.client;

import com.sun.jna.platform.win32.WinUser.KEYBDINPUT;

import org.lwjgl.input.Keyboard;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import scala.swing.event.MouseDragged;

public class KeyLoader {

	public static KeyBinding  modeSwitch;
	public static KeyBinding  standUp;
	public KeyLoader(){
		
		KeyLoader.modeSwitch=new KeyBinding("key.huajiage.switch", KeyConflictContext.IN_GAME,KeyModifier.CONTROL,Keyboard.KEY_K, "key.category.huajiage");
		KeyLoader.standUp=new KeyBinding("key.huajiage.stand_up", KeyConflictContext.IN_GAME,KeyModifier.ALT,Keyboard.KEY_P, "key.category.huajiage");
		ClientRegistry.registerKeyBinding(modeSwitch);
		ClientRegistry.registerKeyBinding(standUp);
		
	}
	
	
}
