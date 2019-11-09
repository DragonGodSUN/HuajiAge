package com.lh_lshen.mcbbs.huajiage.init.playsound;

import com.lh_lshen.mcbbs.huajiage.network.MessagePlaySoundClient;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class HuajiSoundPlayer {
	private static ISound currentMusic;
	@SideOnly(Side.CLIENT)
	public static void playMusic(SoundEvent sound) {
		HuajiSoundPlayer.currentMusic = PositionedSoundRecord.getMusicRecord(sound);
		Minecraft.getMinecraft().getSoundHandler().playSound(currentMusic);

	}
	@SideOnly(Side.CLIENT)
	public static void stopMusic(SoundEvent sound) {
		HuajiSoundPlayer.currentMusic = PositionedSoundRecord.getMusicRecord(sound);
		Minecraft.getMinecraft().getSoundHandler().stopSound(currentMusic);
		
	}
	@SideOnly(Side.CLIENT)
	public static void playClient(ISound sound) {
		Minecraft.getMinecraft().getSoundHandler().playSound(sound);
		
	}
	 @SideOnly(Side.CLIENT)
	    public static void playMovingSoundClient(Entity target,SoundEvent sound, SoundCategory category, float volume) {
	        playClient(new HuajiMovingSound(target, sound, category).setVolume(volume));
	    }
	 @SideOnly(Side.CLIENT)
	    public static void playClient(World world, double x, double y, double z, SoundEvent sound, SoundCategory category, float volume, float pitch) {
	        world.playSound(x, y, z,sound,category, volume, pitch, true);
	    }
    
    @SideOnly(Side.CLIENT)
    public static void playToClient(EntityLivingBase entity,SoundEvent sound) {
    	IMessage msg=new MessagePlaySoundClient(entity.getPositionVector(), sound);
        ServerUtil.sendPacketToPlayers(entity, msg);
    }
     
  }
