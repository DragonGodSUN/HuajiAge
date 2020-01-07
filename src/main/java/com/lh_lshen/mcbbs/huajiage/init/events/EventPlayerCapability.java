package com.lh_lshen.mcbbs.huajiage.init.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandStageHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandStageHandler;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.messages.SyncStandChargeMessage;
import com.lh_lshen.mcbbs.huajiage.network.messages.SyncStandMessage;
import com.lh_lshen.mcbbs.huajiage.network.messages.SyncStandStageMessage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class EventPlayerCapability {
    private static final ResourceLocation STAND_TYPE = new ResourceLocation(HuajiAge.MODID, "stand");
    private static final ResourceLocation STAND_STAGE = new ResourceLocation(HuajiAge.MODID, "stage");
    private static final ResourceLocation STAND_CHARGE = new ResourceLocation(HuajiAge.MODID, "charge");
    
    /**
     * 附加 Capability 属性
     */
    @SubscribeEvent
    public static void onAttachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(STAND_TYPE, new CapabilityStandHandler());
            event.addCapability(STAND_STAGE, new CapabilityStandStageHandler());
            event.addCapability(STAND_CHARGE, new CapabilityStandChargeHandler());
        }
    }

    /**
     * 玩家跨越维度或者死亡时的属性变化
     */
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        EntityPlayer player = event.getEntityPlayer();

        StandHandler num = player.getCapability(CapabilityStandHandler.STAND_TYPE, null);
        StandHandler oldNum = event.getOriginal().getCapability(CapabilityStandHandler.STAND_TYPE, null);
        if (num != null && oldNum != null) {
            num.setStand(oldNum.getStand());
        }
        
        StandStageHandler stage = player.getCapability(CapabilityStandStageHandler.STAND_STAGE, null);
        StandStageHandler oldstage = event.getOriginal().getCapability(CapabilityStandStageHandler.STAND_STAGE, null);
        if (stage != null && oldstage != null) {
        	stage.setStage(oldstage.getStage());
        }
        
        StandChargeHandler charge = player.getCapability(CapabilityStandChargeHandler.STAND_CHARGE, null);
        StandChargeHandler oldcharge = event.getOriginal().getCapability(CapabilityStandChargeHandler.STAND_CHARGE, null);
        if (charge != null && oldcharge != null) {
        	charge.setChargeValue(oldcharge.getChargeValue());
        }
    }
    
    

    /**
     * 同步客户端服务端数据
     */
    @SubscribeEvent
    public static void playerTickEvent(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if (event.side == Side.SERVER && event.phase == Phase.END) {
            if (player.hasCapability(CapabilityStandHandler.STAND_TYPE, null)) {
                StandHandler stand = player.getCapability(CapabilityStandHandler.STAND_TYPE, null);
                if (stand != null && stand.isDirty()) {
                    HuajiAgeNetWorkHandler.HANDLER.sendTo(new SyncStandMessage(stand.getStand()), (EntityPlayerMP) player);
                    stand.setDirty(false);
                }
            }
            if (player.hasCapability(CapabilityStandStageHandler.STAND_STAGE, null)) {
            	StandStageHandler stage = player.getCapability(CapabilityStandStageHandler.STAND_STAGE, null);
                if (stage != null && stage.isDirty()) {
                    HuajiAgeNetWorkHandler.HANDLER.sendTo(new SyncStandStageMessage(stage.getStage()), (EntityPlayerMP) player);
                    stage.setDirty(false);
                }
            }
            if (player.hasCapability(CapabilityStandChargeHandler.STAND_CHARGE, null)) {
            	StandChargeHandler charge = player.getCapability(CapabilityStandChargeHandler.STAND_CHARGE, null);
                if (charge != null && charge.isDirty()) {
                    HuajiAgeNetWorkHandler.HANDLER.sendTo(new SyncStandChargeMessage(charge.getChargeValue()), (EntityPlayerMP) player);
                    charge.setDirty(false);
                }
            }

        }
    }
}