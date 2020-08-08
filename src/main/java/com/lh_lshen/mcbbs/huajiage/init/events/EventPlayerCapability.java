package com.lh_lshen.mcbbs.huajiage.init.events;
//A test structure leaned from @Tartaric_Acid and Sonwnee, follow the MIT License
//Learn More : https://github.com/TartaricAcid/TouhouLittleMaid

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.*;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.network.StandNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.messages.*;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class EventPlayerCapability {
    private static final ResourceLocation STAND_CHARGE = new ResourceLocation(HuajiAge.MODID, "charge");
    private static final ResourceLocation EXPOSED_DATA = new ResourceLocation(HuajiAge.MODID, "expose_data");
    
    /**
     * 附加 Capability 属性
     */
    @SubscribeEvent
    public static void onAttachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(STAND_CHARGE, new CapabilityStandChargeHandler());

            ICapabilitySerializable<NBTTagCompound> provider = new CapabilityExposedData.ProviderPlayer();
            event.addCapability(EXPOSED_DATA, provider);
        }
    }

    /**
     * 玩家跨越维度或者死亡时的属性变化
     */
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        EntityPlayer player = event.getEntityPlayer();
//     Stand Charge~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        StandChargeHandler charge = player.getCapability(CapabilityStandChargeHandler.STAND_CHARGE, null);
        StandChargeHandler oldcharge = event.getOriginal().getCapability(CapabilityStandChargeHandler.STAND_CHARGE, null);
        if (charge != null && oldcharge !=null) {
        	charge.setChargeValue(oldcharge.getChargeValue());
        	charge.setMaxValue(oldcharge.getMaxValue());
        }

//     Stand Expose~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Capability<IExposedData> capability = CapabilityLoader.EXPOSED_DATA;
        IStorage<IExposedData> storage = capability.getStorage();
        if (event.getOriginal().hasCapability(capability, null) && event.getEntityPlayer().hasCapability(capability, null))
        {
            NBTBase nbt = storage.writeNBT(capability, event.getOriginal().getCapability(capability, null), null);
            if(nbt instanceof NBTTagCompound){
                ((NBTTagCompound) nbt).setBoolean("stand_put",false);
            }
            storage.readNBT(capability, event.getEntityPlayer().getCapability(capability, null), null, nbt);
        }

    }
    
    /**
     * 玩家跨越维度时的属性通知
     */
    @SubscribeEvent
    public static void travelToDimension(EntityTravelToDimensionEvent evt) {
    	Entity entity = evt.getEntity();
		if(entity instanceof EntityPlayer) {
		EntityPlayer player = (EntityPlayer) entity;
		StandBase stand = StandUtil.getType(player);
			if(stand != null) {
				StandChargeHandler chargeHandler = player.getCapability(CapabilityStandChargeHandler.STAND_CHARGE, null);
				IExposedData data = player.getCapability(CapabilityLoader.EXPOSED_DATA, null);

				if(chargeHandler!=null) chargeHandler.setDirty(true);
				if(data!=null) data.setDirty(true);
				
			}
			if(stand != null && ConfigHuaji.Stands.allowStandLostTip) {
				player.sendMessage(new TextComponentTranslation("message.huajiage.stand.lost"));
			}
		}
	}

    /**
     * 玩家加入世界时进行数据更新
     */
    @SubscribeEvent
    public static void onPlayerJoin(EntityJoinWorldEvent evt) {
        if (!evt.getWorld().isRemote && evt.getEntity() instanceof EntityLivingBase)
        {
            EntityLivingBase player = (EntityLivingBase) evt.getEntity();

            StandChargeHandler chargeHandler = player.getCapability(CapabilityStandChargeHandler.STAND_CHARGE, null);
            IExposedData data = player.getCapability(CapabilityLoader.EXPOSED_DATA, null);

            if(chargeHandler!=null) chargeHandler.setDirty(true);
            if(data!=null) data.setDirty(true);

        }

    }

    /**
     * 同步客户端服务端数据
     */
    @SubscribeEvent
    public static void playerTickEvent(TickEvent.PlayerTickEvent event) {
        EntityPlayer player = event.player;
        if (event.side == Side.SERVER && event.phase == Phase.END) {
            
            if (player.hasCapability(CapabilityStandChargeHandler.STAND_CHARGE, null)) {
            	StandChargeHandler charge = player.getCapability(CapabilityStandChargeHandler.STAND_CHARGE, null);
                if (charge != null && charge.isDirty()) {
                	StandNetWorkHandler.HANDLER.sendTo(new SyncStandChargeMessage(charge.getChargeValue(),charge.getMaxValue(), charge.getBuffer())
                            , (EntityPlayerMP) player);
                    charge.setDirty(false);
                }
            }
            
            if (player.hasCapability(CapabilityLoader.EXPOSED_DATA, null)) {
                IExposedData data = player.getCapability(CapabilityLoader.EXPOSED_DATA, null);
                if (data != null && data.isDirty()) {
                    StandNetWorkHandler.HANDLER.sendTo( new SyncExposedStandDataMessage(data.getStand(), data.getStage(), data.isTriggered(), data.isHandDisplay(), data.getState(), data.getModel(), player.getUniqueID().toString(),true), (EntityPlayerMP) player);
                    ServerUtil.sendPacketToNearbyPlayersStand(player, new SyncExposedStandDataMessage(data.getStand(), data.getStage(), data.isTriggered(), data.isHandDisplay(), data.getState(), data.getModel(), player.getUniqueID().toString(),false));
                    data.setDirty(false);
                }
            }
            
            
        }
    }

}