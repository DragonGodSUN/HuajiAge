package com.lh_lshen.mcbbs.huajiage.stand.messages;

import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.item.ItemHeroBow;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandSkillType;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.helper.skill.TimeStopHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageDoTimeStopServer implements IMessage {
	private boolean isDio;

	public MessageDoTimeStopServer() {
	}
	
	public MessageDoTimeStopServer(boolean isDio) {
		this.isDio = isDio;
	}

    @Override
    public void toBytes(ByteBuf buf) {
    	buf.writeBoolean(isDio);

    }

    @Override
    public void fromBytes(ByteBuf buf) {
    	this.isDio=buf.readBoolean();

    }

    public static class Handler implements IMessageHandler<MessageDoTimeStopServer, IMessage> {
        @Override
        public IMessage onMessage(MessageDoTimeStopServer message , MessageContext ctx) {
    	if (ctx.side == Side.SERVER) {
        	FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->{
        		EntityPlayerMP player = ctx.getServerHandler().player;
        		String stand = player.getCapability(CapabilityStandHandler.STAND_TYPE, null).getStand();
				TimeStopHelper.doTimeStopServer(player, message.isDio?9:5);
				if(!stand.equals(EnumStandtype.EMPTY))
				{
					ServerUtil.sendPacketToNearbyPlayersStand(player, new MessageDoTimeStopClient(player,stand));
				}
				});
        	}
			return null;
        }
    }
}