package com.lh_lshen.mcbbs.huajiage.util;

import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.init.playsound.StandMovingSound;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;

public class StandUtil {
	public static void standUpSound(EntityPlayer player ,String stand_type ) 
	{
		Random random = new Random();
		EnumStandtype stand = EnumStandtype.getType(stand_type);
//		System.out.println("wryyy");
		switch(stand)
		{
		case THE_WORLD :
			StandMovingSound sound_hit = new StandMovingSound(player, SoundLoader.STAND_THE_WORLD_HIT_1, SoundCategory.PLAYERS);
			StandMovingSound back_hits = new StandMovingSound(player, SoundEvents.ENTITY_PLAYER_ATTACK_SWEEP, SoundCategory.PLAYERS);
			StandMovingSound back_hits_double = new StandMovingSound(player, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, SoundCategory.PLAYERS);
			if(random.nextDouble()>0.5) 
				{
					sound_hit.setSound( SoundLoader.STAND_THE_WORLD_HIT_2);
				}
			if(!player.isPotionActive(PotionLoader.potionStand))
				{
				sound_hit.setVolume(1f);
				Minecraft.getMinecraft().getSoundHandler().playSound(sound_hit);
				back_hits.setVolume(0.5f);
				back_hits.setBackSound(0.5f);
				back_hits.setLoop();
				Minecraft.getMinecraft().getSoundHandler().playSound(back_hits);
				back_hits_double.setVolume(0.7f);
				back_hits_double.setBackSound(0.5f);
				back_hits_double.setLoop();
				Minecraft.getMinecraft().getSoundHandler().playSound(back_hits_double);
				}
				break;
			}
		
		}
}
