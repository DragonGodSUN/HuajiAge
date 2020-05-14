package com.lh_lshen.mcbbs.huajiage.stand.messages;

import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.api.IStandPower;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.item.ItemHeroBow;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
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
				if(!stand.equals(EnumStandtype.EMPTY))
				{
					IStandPower power = EnumStandtype.getType(stand).getStandPower();
					power.doStandCapability(player);
				}
				});
        	}
			return null;
        }
    }
}