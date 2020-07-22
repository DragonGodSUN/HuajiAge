package com.lh_lshen.mcbbs.huajiage.init.events;

import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandStates;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventPlayerFlying {
	public static final List<UUID> FLYING_PLAYERS = new ArrayList<>();
	@SubscribeEvent
	public static void playerUpdate(TickEvent.PlayerTickEvent event) {
		PlayerCapabilities caps = event.player.capabilities;
		EntityPlayer player = event.player;
		ItemStack item = player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
		IExposedData data = StandUtil.getStandData(player);
		boolean flag = item.getItem() == ItemLoader.blanceHelmet ;
		boolean isLord = flag&&NBTHelper.getTagCompoundSafe(item).hasKey("lord")&&NBTHelper.getTagCompoundSafe(item).getBoolean("lord");
		if(!player.world.isRemote) {
//五五开头盔
			if(!caps.allowFlying) {
				if(isLord) {
					caps.allowFlying=true;
					player.sendPlayerAbilities();
					FLYING_PLAYERS.add(player.getUniqueID());
				}
			}
//			if(FLYING_PLAYERS.contains(player.getUniqueID())&&!isLord) {
//					if(caps.allowFlying && !player.isSpectator() && !player.isCreative()) {
//						caps.allowFlying=false;
//						caps.isFlying=false;
//						player.sendPlayerAbilities();
//					 }
//				FLYING_PLAYERS.removeIf(uuid -> uuid.toString().equals(player.getUniqueID().toString()));
//			}
//替身
			if (data!=null) {
				StandStateBase state_base = StandStates.getStandState(data.getStand(),data.getState());
				if(!caps.allowFlying) {
					if(state_base!=null&&state_base.hasExtraData("fly")) {
						caps.allowFlying=true;
						player.sendPlayerAbilities();
						FLYING_PLAYERS.add(player.getUniqueID());
					}
				}
				if(FLYING_PLAYERS.contains(player.getUniqueID())&&!isLord
						&&state_base!=null&&!state_base.hasExtraData("fly")) {
					if(caps.allowFlying && !player.isSpectator() && !player.isCreative()) {
						caps.allowFlying=false;
						caps.isFlying=false;
						player.sendPlayerAbilities();
					}
					FLYING_PLAYERS.removeIf(uuid -> uuid.toString().equals(player.getUniqueID().toString()));
				}
			}

		}
			
	}
	
   @SubscribeEvent
    public static void playerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        if(FLYING_PLAYERS.contains(event.player.getUniqueID())) {
            FLYING_PLAYERS.removeIf(uuid -> uuid.toString().equals(event.player.getUniqueID().toString()));
        }
    }
	
}
