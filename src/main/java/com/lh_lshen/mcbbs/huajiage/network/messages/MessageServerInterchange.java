package com.lh_lshen.mcbbs.huajiage.network.messages;

import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.item.ItemBlancedHelmet;
import com.lh_lshen.mcbbs.huajiage.item.ItemHeroBow;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageServerInterchange implements IMessage {
	public MessageServerInterchange() {
	}
	public MessageServerInterchange(int type) {
		this.type = type;
	}
    @Override
    public void toBytes(ByteBuf buf) {
    	buf.writeInt(type);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    	this.type = buf.readInt();
    }
    private int type;
    public static class Handler implements IMessageHandler<MessageServerInterchange, IMessage> {
        @Override
        public IMessage onMessage(MessageServerInterchange message , MessageContext ctx) {
        	EntityPlayerMP player = ctx.getServerHandler().player;
			player.mcServer.addScheduledTask(()->{
			switch(message.type) {
			case 1:
				HuajiAgeNetWorkHandler.sendToNearby(player.world, player, new MessageParticleGenerator(player.getPositionVector(), EnumParticleTypes.FIREWORKS_SPARK, 60, 3, 1));
			break;
			case 2:
				HuajiAgeNetWorkHandler.sendToNearby(player.world, player, new MessageMovingSound(player.getName()));
			break;
			}
			}) ;
			return null;
        }
    }
}