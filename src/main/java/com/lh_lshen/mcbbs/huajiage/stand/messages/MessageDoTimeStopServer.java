package com.lh_lshen.mcbbs.huajiage.stand.messages;

import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.item.ItemHeroBow;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.skill.StandSkillType;
import com.lh_lshen.mcbbs.huajiage.stand.skills.TimeStopHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

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
        	EntityPlayerMP player = ctx.getServerHandler().player;
			player.mcServer.addScheduledTask(() ->{
				TimeStopHelper.doTimeStopServer(player, message.isDio?9:5);
				ServerUtil.sendPacketToNearbyPlayersStand(player, new MessageDoTimeStopClient(player));
			});
			return null;
        }
    }
}