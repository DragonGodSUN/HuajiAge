package com.lh_lshen.mcbbs.huajiage.stand.messages;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.init.loaders.CapabilityLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandTag;
import com.lh_lshen.mcbbs.huajiage.stand.StandStates;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.entity.EntityStandBase;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

import java.util.Objects;

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
        	IExposedData data = player.getCapability(CapabilityLoader.EXPOSED_DATA, null);
        	StandBase stand = StandUtil.getType(player);
        	StandHandler charge = StandUtil.getStandHandler(player);
        	if(data==null || stand==null || charge==null)
        	{
        		return null;
        	}
            	FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->{
				data.setState(CapabilityExposedData.States.DEFAULT.getName());
				if(!data.isTriggered()) {
					if(charge.canBeCost(1000)) {
						charge.setMaxValue(stand.getMaxMP());
						charge.cost(1000);
						player.addPotionEffect(new PotionEffect(PotionLoader.potionStand,stand.getDuration()));
						data.setTrigger(true);
						data.setHandDisplay(stand.isHandDisplay());
						player.sendMessage(new TextComponentTranslation(stand.getLocalName()));
//					    ServerUtil.sendPacketToNearbyPlayersStand(player, new SyncExposedStandDataMessage(standType, true, player.getName()));
						if(message.isMoving) {
								EntityStandBase standBase = new EntityStandBase(player.world, player, StandLoader.getStand(data.getStand()));
								standBase.setUser(player.getUniqueID().toString());
								standBase.setUserName(player.getName());
								standBase.setPosition(player.posX, player.posY, player.posZ);
								standBase.setType(data.getStand());
								if(Objects.requireNonNull(StandStates.getStandState(stand.getName(), data.getState())).hasExtraData(EnumStandTag.StateTags.RIDE.getName())){
									standBase.setTamedBy(player);
									standBase.setEntity(true);
								}
								player.world.spawnEntity(standBase);
							}
						}else {
							player.sendMessage(new TextComponentTranslation("message.huajiage.stand_stand_up.cost_lack"));
						}
					}else {
						charge.setMaxValue(stand.getMaxMP());
						player.removePotionEffect(PotionLoader.potionStand);
						data.setTrigger(false);
					}
            		});
            	}
			return null;
        }
    }
}