package com.lh_lshen.mcbbs.huajiage.network;

import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.item.ItemBlancedHelmet;
import com.lh_lshen.mcbbs.huajiage.item.ItemHeroBow;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageKeyMode implements IMessage {
    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<MessageKeyMode, IMessage> {
        @Override
        public IMessage onMessage(MessageKeyMode message , MessageContext ctx) {
        	EntityPlayerMP player = ctx.getServerHandler().player;
        	ItemStack itemstack=ctx.getServerHandler().player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
			player.mcServer.addScheduledTask(() -> ((ItemBlancedHelmet) ItemLoader.blanceHelmet).ModeChange(itemstack, player));
			return null;
        }
    }
}