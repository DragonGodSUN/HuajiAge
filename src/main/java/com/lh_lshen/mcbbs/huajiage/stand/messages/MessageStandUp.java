package com.lh_lshen.mcbbs.huajiage.stand.messages;

import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityLoader;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.init.events.EventStand;
import com.lh_lshen.mcbbs.huajiage.item.ItemHeroBow;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.entity.EntityStandBase;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageStandUp implements IMessage {
	private boolean isMoving;
	
	public MessageStandUp() {
	}
	
	public MessageStandUp(boolean isMoving) {
		this.isMoving = isMoving;
	}
    @Override
    public void toBytes(ByteBuf buf) {
    	buf.writeBoolean(isMoving);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    	this.isMoving = buf.readBoolean();
    }
    
    public static class Handler implements IMessageHandler<MessageStandUp, IMessage> {
        @Override
        public IMessage onMessage(MessageStandUp message , MessageContext ctx) {
        	if (ctx.side == Side.SERVER) {
        	EntityPlayerMP player = ctx.getServerHandler().player;
        	StandHandler standHandler = player.getCapability(CapabilityStandHandler.STAND_TYPE, null);
        	String standType = standHandler.getStand();
        	StandBase stand = StandLoader.getStand(standType);
        	IExposedData data = player.getCapability(CapabilityLoader.EXPOSED_DATA, null);
        	StandChargeHandler charge = StandUtil.getChargeHandler(player);
        	if(stand==null || data==null || charge==null)
        	{
        		return null;
        	}
            	FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->{
				if(!data.isTriggered()) {
					if(charge.canBeCost(stand.getCost()/10)) {
						charge.cost(stand.getCost()/10);
						player.addPotionEffect(new PotionEffect(PotionLoader.potionStand,stand.getDuration()));
						data.setStand(standType);
						data.setTrigger(true);
//					    ServerUtil.sendPacketToNearbyPlayersStand(player, new SyncExposedStandDataMessage(standType, true, player.getName()));
						if(message.isMoving) {
								EntityStandBase standBase = new EntityStandBase(player.world, player, StandLoader.getStand(data.getStand()));
								standBase.setUser(player.getUniqueID().toString());
								standBase.setUserName(player.getName());
								standBase.setPosition(player.posX, player.posY, player.posZ);
								standBase.setType(data.getStand());
								player.world.spawnEntity(standBase);
							}
						}else {
							player.sendMessage(new TextComponentTranslation("message.huajiage.stand_stand_up.cost_lack"));
						}
					}else {
						player.removePotionEffect(PotionLoader.potionStand);
						data.setTrigger(false);
					}
            		});
            	}
			return null;
        }
    }
}