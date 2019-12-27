package com.lh_lshen.mcbbs.huajiage.init.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.messages.SyncStandMessage;

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

    /**
     * 附加 Capability 属性
     */
    @SubscribeEvent
    public static void onAttachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(STAND_TYPE, new CapabilityStandHandler());
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

        }
    }
}