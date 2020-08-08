package com.lh_lshen.mcbbs.huajiage.stand.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityLoader;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.client.KeyLoader;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.StandNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageServerInterchange;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDoStandCapabilityServer;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessagePerfromSkill;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageStandModeSwitch;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageStandUp;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class EventStandKey {
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void standUp(InputEvent.KeyInputEvent evt) {
		if(KeyLoader.standUp.isPressed()) {
			EntityPlayer player = Minecraft.getMinecraft().player;
			IExposedData data = player.getCapability(CapabilityLoader.EXPOSED_DATA, null);
			String stand_type =data.getStand();
			StandBase stand = StandLoader.getStand(stand_type);
			
			 if(stand !=null && !stand_type.equals(StandLoader.EMPTY)) {
				 HuajiSoundPlayer.playToServer(player, SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 3);

				 final int NUMBER_OF_PARTICLES = 60;
				 final double HORIZONTAL_SPREAD = 3; 
				 Vec3d targetCoordinates = player.getPositionVector();
				 HuajiAgeNetWorkHandler.sendToServer(new MessageServerInterchange(1));
				 boolean isMovingMusic = true;
				 
				 if(!ConfigHuaji.Stands.allowStandSound) {
					 isMovingMusic = false;
				 }
				if(!ConfigHuaji.Stands.allowStandSound&&ConfigHuaji.Stands.allowStandSound&&!player.isPotionActive(PotionLoader.potionStand))
				{	
					 isMovingMusic = false;
					float random = new Random().nextFloat()*100;
					switch(stand.getName())
					{
					case "thw_world" :
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
					case "star_platinum" :
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
					case "hierophant_green" :
					{	
							HuajiSoundPlayer.playToServer(player, SoundLoader.STAND_HIEROPHANT_GREEN_SHOOT_1, 1, 1);
							break;
					}
					default:
						break;
					}
				}
				StandNetWorkHandler.sendToServer(new MessageStandUp(isMovingMusic));

			 					}
						}
	  			}
	
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void performSkill(InputEvent.KeyInputEvent evt) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		int stage = StandUtil.getStandStage(player);
		if(KeyLoader.standSkill.isPressed()&&stage>0) {
			StandBase stand = StandUtil.getType(player);
			StandChargeHandler charge = StandUtil.getChargeHandler(player);
			if(null != stand) {
				int cost = stand.getCost();
				boolean flag = charge.canBeCost(cost);
				MessagePerfromSkill msg = new MessagePerfromSkill(stand.getCost());
				StandNetWorkHandler.sendToServer(msg);
				if(flag){
					StandNetWorkHandler.sendToServer(new MessageDoStandCapabilityServer());
					player.sendMessage(new TextComponentTranslation("stand.huajiage.skill"+"."+stand.getName()+"."+"start"));
					}
				
				}
			}
		}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void switchMode(InputEvent.KeyInputEvent evt) {
		EntityPlayer player = Minecraft.getMinecraft().player;
		IExposedData data = StandUtil.getStandData(player);
		if(KeyLoader.standSwitch.isPressed()&&data!=null&&data.isTriggered()) {
			StandBase stand = StandUtil.getType(player);
			if(null != stand) {
				MessageStandModeSwitch msg = new MessageStandModeSwitch();
				StandNetWorkHandler.sendToServer(msg);
			}
		}
	}
}
