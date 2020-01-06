package com.lh_lshen.mcbbs.huajiage.init.events;

import java.util.List;
import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.client.KeyLoader;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiMovingSound;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.init.playsound.StandMovingSound;
import com.lh_lshen.mcbbs.huajiage.item.ItemBlancedHelmet;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageExglutenburMode;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageHelmetModeChange;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessagePlaySoundToServer;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageServerInterchange;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageStandUp;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;
import com.lh_lshen.mcbbs.huajiage.util.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.util.StandUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ISoundEventListener;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.network.play.server.SPacketEffect;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EventKeyInput {
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void modeSwitch(InputEvent.KeyInputEvent evt) {

		if(KeyLoader.modeSwitch.isPressed()) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		if(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemBlancedHelmet) {
            HuajiAgeNetWorkHandler.sendToServer(new MessageHelmetModeChange());
	 			}
		}
	}
	
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void standUp(InputEvent.KeyInputEvent evt) {
		if(KeyLoader.standUp.isPressed()) {
			EntityPlayer player = Minecraft.getMinecraft().player;
			StandHandler standHandler = player.getCapability(CapabilityStandHandler.STAND_TYPE, null);
			String stand_type =standHandler.getStand();
			EnumStandtype stand = EnumStandtype.getType(stand_type);
			
			 if(!stand_type.equals(EnumStandtype.EMPTY)) {
				 HuajiSoundPlayer.playToServer(player, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 3);

				if(ConfigHuaji.Stands.allowStandSound&&ConfigHuaji.Stands.allowStandMovingSound&&!player.isPotionActive(PotionLoader.potionStand)) 
				{
					HuajiAgeNetWorkHandler.sendToServer(new MessageServerInterchange(2));
					
				}else if(ConfigHuaji.Stands.allowStandSound&&!player.isPotionActive(PotionLoader.potionStand))
				{	
					float random = new Random().nextFloat()*100;
					switch(stand)
					{
					case THE_WORLD :
					{
						if(random<50) 
							{
								HuajiSoundPlayer.playToServer(player, SoundLoader.STAND_THE_WORLD_HIT_1, 1, 1);
							}
						if(random>50) 
							{
								HuajiSoundPlayer.playToServer(player, SoundLoader.STAND_THE_WORLD_HIT_2, 1, 1);
							}
							break;
					}
					case STAR_PLATINUM :
					{	if(random<25) 
							{
								HuajiSoundPlayer.playToServer(player, SoundLoader.STAND_STAR_PLATINUM_1, 1, 1);
							}else if(random<50) 
							{
								HuajiSoundPlayer.playToServer(player, SoundLoader.STAND_STAR_PLATINUM_2, 1, 1);
							}else if(random<75) 
							{
								HuajiSoundPlayer.playToServer(player, SoundLoader.STAND_STAR_PLATINUM_3 ,1, 1);
							}else if(random<100) 
							{
								HuajiSoundPlayer.playToServer(player, SoundLoader.STAND_STAR_PLATINUM_4, 1, 1);
							}
							break;
					}	
					default:
						break;
					}
				}
			    final int NUMBER_OF_PARTICLES = 60;
			    final double HORIZONTAL_SPREAD = 3; 
			    Vec3d targetCoordinates = player.getPositionVector();
	            HuajiAgeNetWorkHandler.sendToServer(new MessageStandUp());
	            HuajiAgeNetWorkHandler.sendToServer(new MessageServerInterchange(1));
			 					}
						}
	  			}
	}
