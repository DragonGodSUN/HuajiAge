package com.lh_lshen.mcbbs.huajiage.client;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.IKeyConflictContext;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeyLoader {

	public static KeyBinding  modeSwitch =registerKeyInGame("key.huajiage.switch", KeyModifier.CONTROL,Keyboard.KEY_K);
	public static KeyBinding  standUp = registerKeyInGame("key.huajiage.stand_up", KeyModifier.CONTROL, Keyboard.KEY_P);
	public static KeyBinding  standSkill = registerKeyInGame("key.huajiage.stand_skill", KeyModifier.CONTROL, Keyboard.KEY_O);
	public static KeyBinding  standSwitch = registerKeyInGame("key.huajiage.stand_switch", KeyModifier.CONTROL, Keyboard.KEY_I);
	private static  final String HUAJI_KEY_GROUP = "key.category.huajiage";
	public KeyLoader(){
		
//		KeyLoader.modeSwitch=new KeyBinding("key.huajiage.switch", KeyConflictContext.IN_GAME,KeyModifier.CONTROL,Keyboard.KEY_K, HUAJI_KEY_GROUP);
//		KeyLoader.standUp=new KeyBinding("key.huajiage.stand_up", KeyConflictContext.IN_GAME,KeyModifier.ALT,Keyboard.KEY_P, "key.category.huajiage");
//		ClientRegistry.registerKeyBinding(modeSwitch);
//		ClientRegistry.registerKeyBinding(standUp);
		
	}
	
	public static KeyBinding registerKey(String description, IKeyConflictContext keyConflictContext, KeyModifier keyModifier, int keyCode, String category) 
	{
		KeyBinding key = new KeyBinding(description, keyConflictContext,keyModifier,keyCode, category);
		ClientRegistry.registerKeyBinding(key);
		return key;
	}
	
	public static KeyBinding registerKeyInGame(String description,KeyModifier keyModifier, int keyCode)
	{
		KeyBinding key =registerKey(description, KeyConflictContext.IN_GAME, keyModifier, keyCode, HUAJI_KEY_GROUP);
		return key;
	}
	
	
}
