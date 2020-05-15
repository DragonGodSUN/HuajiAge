package com.lh_lshen.mcbbs.huajiage.stand.messages;

import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.item.ItemHeroBow;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.helper.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.helper.skill.TimeStopHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessagePerfromSkill implements IMessage {
	private int cost;
	public MessagePerfromSkill() {
	}
	public MessagePerfromSkill(int cost) {
		this.cost=cost;

	}
    @Override
    public void toBytes(ByteBuf buf) {
    	buf.writeInt(cost);

    }

    @Override
    public void fromBytes(ByteBuf buf) {
    	this.cost=buf.readInt();

    }

    public static class Handler implements IMessageHandler<MessagePerfromSkill, IMessage> {
        @Override
        public IMessage onMessage(MessagePerfromSkill message , MessageContext ctx) {
        	EntityPlayerMP player = ctx.getServerHandler().player;
        	StandBase stand = StandUtil.getType(player);
        	StandChargeHandler charge = StandUtil.getChargeHandler(player);
        	if (ctx.side == Side.SERVER) {
            	FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->{
				if(null==stand) {
					return;
				}else {
					boolean flag = charge.canBeCost(message.cost);
					if(flag) {
					charge.cost(message.cost);
					}else {
						player.sendMessage(new TextComponentTranslation("message.huajiage.stand_skill.cost_lack"));
					}
				}
            		});
            	}
			return null;
        }
    }
}