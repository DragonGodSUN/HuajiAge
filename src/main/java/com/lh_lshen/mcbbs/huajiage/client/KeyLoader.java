package com.lh_lshen.mcbbs.huajiage.client;

import com.sun.jna.platform.win32.WinUser.KEYBDINPUT;

import org.lwjgl.input.Keyboard;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import scala.swing.event.MouseDragged;

public class KeyLoader {

	public static KeyBinding  swich1;
	public KeyLoader(){
		
		KeyLoader.swich1=new KeyBinding("key.huaji.swich_1", KeyConflictContext.IN_GAME,KeyModifier.CONTROL,Keyboard.KEY_K, "key.category.huajiage");
		ClientRegistry.registerKeyBinding(swich1);
		
	}
	
	
}
