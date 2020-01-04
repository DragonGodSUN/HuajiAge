package com.lh_lshen.mcbbs.huajiage.init.events;

import java.util.List;
import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.client.KeyLoader;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiMovingSound;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.init.playsound.StandMovingSound;
import com.lh_lshen.mcbbs.huajiage.item.ItemBlancedHelmet;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageExglutenburMode;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageKeyInput;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageStandUp;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;
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
	public static void keyInput(InputEvent.KeyInputEvent evt) {

		if(KeyLoader.modeSwitch.isPressed()) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		if(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemBlancedHelmet) {
            HuajiAgeNetWorkHandler.sendToServer(new MessageKeyInput());
	 			}
		}
		if(KeyLoader.standUp.isPressed()) {
			EntityPlayer player = Minecraft.getMinecraft().player;
			String stand_type =player.getCapability(CapabilityStandHandler.STAND_TYPE, null).getStand();
			 if(!stand_type.equals(EnumStandtype.EMPTY)) {
				for(int i = 0;i<3;i++) {
				 player.world.playSound(player, player.getPosition(), SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 1f, 1f);;
				}
				StandUtil.standUpSound(player, stand_type);
//				SPacketSoundEffect sound = new SPacketSoundEffect(Sound, categoryIn, xIn, yIn, zIn, volumeIn, pitchIn)
				
				Random random = new Random();
			    final int NUMBER_OF_PARTICLES = 60;
			    final double HORIZONTAL_SPREAD = 3; 
			    Vec3d targetCoordinates = player.getPositionVector();
			    for (int i = 0; i < NUMBER_OF_PARTICLES; ++i) {
			    double spawnXpos = targetCoordinates.x + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
			    double spawnYpos = targetCoordinates.y + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
			    double spawnZpos = targetCoordinates.z + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
			      player.world.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, spawnXpos, spawnYpos, spawnZpos, 0, 0, 0);
			    							}
	            HuajiAgeNetWorkHandler.sendToServer(new MessageStandUp());
			 					}
						}
	  			}
	}
