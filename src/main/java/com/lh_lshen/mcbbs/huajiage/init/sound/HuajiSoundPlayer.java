package com.lh_lshen.mcbbs.huajiage.init.sound;

import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessagePlaySoundClient;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessagePlaySoundToServer;
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
	 
	    public static HuajiMovingSound getMovingSound(Entity target,SoundEvent sound, SoundCategory category, float volume) {
		 HuajiMovingSound soundm =new HuajiMovingSound(target, sound, category); 
		 return soundm.setVolume(volume);
	    }
	    
//	    public static HuajiMovingSound getStandSound(Entity target,SoundEvent sound, SoundCategory category, float volume) {
//			 StandMovingSound soundm =new StandMovingSound(target, sound, category); 
//			 return soundm.setVolume(volume);
//		    }
	    
	 @SideOnly(Side.CLIENT)
	    public static void playClient(World world, double x, double y, double z, SoundEvent sound, SoundCategory category, float volume, float pitch) {
	        world.playSound(x, y, z,sound,category, volume, pitch, true);
	    }
    

	 public static void playToClient(EntityLivingBase entity,SoundEvent sound,float volume) {
    	IMessage msg=new MessagePlaySoundClient(entity.getPositionVector(), sound, volume);
        ServerUtil.sendPacketToPlayers(entity, msg);
    }
	 
    public static void playToNearbyClient(EntityLivingBase entity,SoundEvent sound,float volume) {
    	IMessage msg=new MessagePlaySoundClient(entity.getPositionVector(), sound, volume);
        ServerUtil.sendPacketToNearbyPlayers(entity, msg);
    }
    
    public static void playToServer(EntityLivingBase entity,SoundEvent sound,float volume,int loop) {
    	IMessage msg=new MessagePlaySoundToServer(entity.getPositionVector(), sound, volume, loop);
        HuajiAgeNetWorkHandler.sendToServer(msg);
    }
     
  }
