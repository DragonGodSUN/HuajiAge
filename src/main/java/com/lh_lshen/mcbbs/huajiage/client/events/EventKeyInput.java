package com.lh_lshen.mcbbs.huajiage.client.events;

import java.util.List;
import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.client.KeyLoader;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiMovingSound;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.StandMovingSound;
import com.lh_lshen.mcbbs.huajiage.item.ItemBlancedHelmet;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageExglutenburMode;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageFiveBulletShoot;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageHelmetModeChange;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageLeftClickModeChange;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessagePlaySoundToServer;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageServerInterchange;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageStandUp;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ISoundEventListener;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketEffect;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class EventKeyInput {
	@SubscribeEvent
	public static void modeSwitch(InputEvent.KeyInputEvent evt) {

		if(KeyLoader.modeSwitch.isPressed()) 
		{
		EntityPlayer player = Minecraft.getMinecraft().player;
		if(player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() instanceof ItemBlancedHelmet)
			{
	        	HuajiAgeNetWorkHandler.sendToServer(new MessageHelmetModeChange());
			}
		}
		
	}
	@SubscribeEvent
	public static void leftClick(PlayerInteractEvent.LeftClickEmpty evt) {
		EntityPlayer player=evt.getEntityPlayer();
		ItemStack helmet = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		if ( helmet.getItem() instanceof ItemBlancedHelmet) {
		       if(helmet.getTagCompound().hasKey("lord")&&helmet.getTagCompound().hasKey("open")&&helmet.getTagCompound().getBoolean("open")) 
		       {
		    	   HuajiAgeNetWorkHandler.sendToServer(new MessageFiveBulletShoot());
	       		}
		       
		}
	}
	
}
