package com.lh_lshen.mcbbs.huajiage.stand.messages;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageDoStandCapabilityServer implements IMessage {

	public MessageDoStandCapabilityServer() {
	}

    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<MessageDoStandCapabilityServer, IMessage> {
        @Override
        public IMessage onMessage(MessageDoStandCapabilityServer message , MessageContext ctx) {
    	if (ctx.side == Side.SERVER) {
        	FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->{
        		EntityPlayerMP player = ctx.getServerHandler().player;
        		String stand = player.getCapability(CapabilityStandHandler.STAND_TYPE, null).getStand();
				if(!stand.equals(StandLoader.EMPTY))
				{
					StandLoader.getStand(stand).doStandCapability(player);
				}
				});
        	}
			return null;
        }
    }
}