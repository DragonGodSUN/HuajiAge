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
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
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
    private static final ResourceLocation STAND_BUFF = new ResourceLocation(HuajiAge.MODID, "buff");
    
    private static final ResourceLocation EXPOSED_DATA = new ResourceLocation(HuajiAge.MODID, "expose_data");
    
    /**
     * 附加 Capability 属性
     */
    @SubscribeEvent
    public static void onAttachCapability(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof EntityPlayer) {
            event.addCapability(STAND_TYPE, new CapabilityStandHandler());
            event.addCapability(STAND_STAGE, new CapabilityStandStageHandler());
            event.addCapability(STAND_CHARGE, new CapabilityStandChargeHandler());
            event.addCapability(STAND_BUFF, new CapabilityStandBuffHandler());
            
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
//     Stand Type~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        StandHandler stand = player.getCapability(CapabilityStandHandler.STAND_TYPE, null);
        StandHandler oldStand = event.getOriginal().getCapability(CapabilityStandHandler.STAND_TYPE, null);
        if (stand != null && oldStand != null ) {
        	stand.setStand(oldStand.getStand());
        }
        
    
//     Stand Stage~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        StandStageHandler stage = player.getCapability(CapabilityStandStageHandler.STAND_STAGE, null);
        StandStageHandler oldstage = event.getOriginal().getCapability(CapabilityStandStageHandler.STAND_STAGE, null);
        if (stage != null && oldstage != null) {
        	stage.setStage(oldstage.getStage());
        }
        
//     Stand Charge~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        StandChargeHandler charge = player.getCapability(CapabilityStandChargeHandler.STAND_CHARGE, null);
        StandChargeHandler oldcharge = event.getOriginal().getCapability(CapabilityStandChargeHandler.STAND_CHARGE, null);
        if (charge != null && oldcharge !=null) {
        	charge.setChargeValue(oldcharge.getChargeValue());
        	charge.setMaxValue(oldcharge.getMaxValue());
        }
        
//      Stand Buff~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        StandBuffHandler buff = player.getCapability(CapabilityStandBuffHandler.STAND_BUFF, null);
        StandBuffHandler oldbuff = event.getOriginal().getCapability(CapabilityStandBuffHandler.STAND_BUFF, null);
        if (buff != null && oldbuff !=null) {
        	buff.setTime(oldbuff.getTime());
        }
        
//     Stand Expose~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        if(!event.isWasDeath()) {
        	Capability<IExposedData> capability = CapabilityLoader.EXPOSED_DATA;
        	IStorage<IExposedData> storage = capability.getStorage();
	        if (event.getOriginal().hasCapability(capability, null) && event.getEntityPlayer().hasCapability(capability, null))
	        {
	            NBTBase nbt = storage.writeNBT(capability, event.getOriginal().getCapability(capability, null), null);
	            storage.readNBT(capability, event.getEntityPlayer().getCapability(capability, null), null, nbt);
	        }
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
				StandHandler standHandler = player.getCapability(CapabilityStandHandler.STAND_TYPE, null);
				StandStageHandler stageHandler = player.getCapability(CapabilityStandStageHandler.STAND_STAGE, null);
				StandChargeHandler chargeHandler = player.getCapability(CapabilityStandChargeHandler.STAND_CHARGE, null);
				StandBuffHandler buffHandler = player.getCapability(CapabilityStandBuffHandler.STAND_BUFF, null);
				IExposedData data = player.getCapability(CapabilityLoader.EXPOSED_DATA, null);
				
				if(standHandler!=null) standHandler.setDirty(true);
				if(stageHandler!=null) stageHandler.setDirty(true);
				if(chargeHandler!=null) chargeHandler.setDirty(true);
				if(buffHandler!=null)buffHandler.setDirty(true);
				if(data!=null) data.setDirty(true);
				
			}
			if(stand != null && ConfigHuaji.Stands.allowStandLostTip) {
				player.sendMessage(new TextComponentTranslation("message.huajiage.stand.lost"));
			}
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
                IExposedData data = player.getCapability(CapabilityLoader.EXPOSED_DATA, null);
                if (stand != null && stand.isDirty()) {
                    StandNetWorkHandler.HANDLER.sendTo(new SyncStandMessage(stand.getStand()), (EntityPlayerMP) player);
//                    ServerUtil.sendPacketToNearbyPlayersStand(player, new SyncStandUserMessage(stand.getStand(), player.getName()));
                    if (data != null ) {
                    	data.setStand(stand.getStand());
                    	data.setTrigger(false);
                    	data.setDirty(true);
                    }
                    stand.setDirty(false);
                }
            }
            
            if (player.hasCapability(CapabilityStandStageHandler.STAND_STAGE, null)) {
            	StandStageHandler stage = player.getCapability(CapabilityStandStageHandler.STAND_STAGE, null);
                IExposedData data = player.getCapability(CapabilityLoader.EXPOSED_DATA, null);
                if (stage != null && stage.isDirty()) {
                	StandNetWorkHandler.HANDLER.sendTo(new SyncStandStageMessage(stage.getStage()), (EntityPlayerMP) player);
                    if (data != null ) {
                        data.setStage(stage.getStage());
                        data.setDirty(true);
                    }
                    stage.setDirty(false);
                }
            }
            
            if (player.hasCapability(CapabilityStandChargeHandler.STAND_CHARGE, null)) {
            	StandChargeHandler charge = player.getCapability(CapabilityStandChargeHandler.STAND_CHARGE, null);
                if (charge != null && charge.isDirty()) {
                	StandNetWorkHandler.HANDLER.sendTo(new SyncStandChargeMessage(charge.getChargeValue(),charge.getMaxValue()), (EntityPlayerMP) player);
                    charge.setDirty(false);
                }
            }
            
            if (player.hasCapability(CapabilityStandBuffHandler.STAND_BUFF, null)) {
            	StandBuffHandler buff = player.getCapability(CapabilityStandBuffHandler.STAND_BUFF, null);
                if (buff != null && buff.isDirty()) {
                	StandNetWorkHandler.HANDLER.sendTo(new SyncStandBuffMessage(buff.getTime()),(EntityPlayerMP) player);
                	buff.setDirty(false);
                }
            }
            
            
            if (player.hasCapability(CapabilityLoader.EXPOSED_DATA, null)) {
                IExposedData data = player.getCapability(CapabilityLoader.EXPOSED_DATA, null);
                if (data != null && data.isDirty()) {
                    ServerUtil.sendPacketToNearbyPlayersStand(player, new SyncExposedStandDataMessage(data.getStand(), data.getStage(), data.isTriggered(), data.isHandDisplay(), data.getState(), data.getModel(), player.getName()));
                    data.setDirty(false);
                }
            }
            
            
        }
    }

}