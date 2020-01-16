package com.lh_lshen.mcbbs.huajiage.stand.skill;

import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.client.KeyLoader;
import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.init.playsound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.StandNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageServerInterchange;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.entity.EntityStandBase;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDoTimeStopServer;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessagePerfromSkill;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageStandUp;
import com.lh_lshen.mcbbs.huajiage.stand.skills.TimeStopHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

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

@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class EventStandKey {
	
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
			EnumStandtype stand = StandUtil.getType(player);
			StandChargeHandler charge = StandUtil.getChargeHandler(player);
			if(null != stand) {
				int cost = stand.getCost();
				switch(stand) {
				case THE_WORLD:{
					boolean flag = charge.canBeCost(cost);
					MessagePerfromSkill msg = new MessagePerfromSkill(cost,0,120,HuajiConstant.THE_WORLD_TIME,StandSkillType.TIME_STOP);
					StandNetWorkHandler.sendToServer(msg);
					if(flag){
					StandNetWorkHandler.sendToServer(new MessageDoTimeStopServer(true));
//					TimeStopHelper.doTimeStopClient(player);
					player.sendMessage(new TextComponentTranslation("message.huajiage.the_world"));
					}
					break;
				}
				case STAR_PLATINUM:{
					boolean flag = charge.canBeCost(cost);
					MessagePerfromSkill msg = new MessagePerfromSkill(cost,0,50,5*20,StandSkillType.TIME_STOP);
					StandNetWorkHandler.sendToServer(msg);
					if(flag) {
					StandNetWorkHandler.sendToServer(new MessageDoTimeStopServer(false));
//					TimeStopHelper.doTimeStopClient(player);
					player.sendMessage(new TextComponentTranslation("message.huajiage.the_world_star"));
					}
					break;
				}
				default:
					break;
					}
				}
			}
		}
}
