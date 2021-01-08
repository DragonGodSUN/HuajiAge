package com.lh_lshen.mcbbs.huajiage.stand.messages;

import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
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
				IExposedData data = StandUtil.getStandData(player);
				if(data != null && !data.getStand().equals(StandLoader.EMPTY))
				{
					StandLoader.getStand(data.getStand()).doStandCapability(player);
				}
				});
        	}
			return null;
        }
    }
}