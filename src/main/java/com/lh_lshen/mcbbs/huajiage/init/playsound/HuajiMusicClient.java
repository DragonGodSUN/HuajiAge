package com.lh_lshen.mcbbs.huajiage.init.playsound;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HuajiMusicClient {
	private static ISound currentMusic;
	public HuajiMusicClient() {

	}

	@SideOnly(Side.CLIENT)
	public static void playMusic(SoundEvent sound) {
		HuajiMusicClient.currentMusic = PositionedSoundRecord.getMusicRecord(sound);
		Minecraft.getMinecraft().getSoundHandler().playSound(currentMusic);

	}
	@SideOnly(Side.CLIENT)
	public static void stopMusic(SoundEvent sound) {
		HuajiMusicClient.currentMusic = PositionedSoundRecord.getMusicRecord(sound);
		Minecraft.getMinecraft().getSoundHandler().stopSound(currentMusic);

	}
     
  }
