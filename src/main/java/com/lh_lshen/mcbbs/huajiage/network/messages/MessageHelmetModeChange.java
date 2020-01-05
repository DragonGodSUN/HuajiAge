package com.lh_lshen.mcbbs.huajiage.network.messages;

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

public class MessageHelmetModeChange implements IMessage {
    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<MessageHelmetModeChange, IMessage> {
        @Override
        public IMessage onMessage(MessageHelmetModeChange message , MessageContext ctx) {
        	EntityPlayerMP player = ctx.getServerHandler().player;
        	ItemStack itemstack_head=ctx.getServerHandler().player.getItemStackFromSlot(EntityEquipmentSlot.HEAD);
			player.mcServer.addScheduledTask(() -> ((ItemBlancedHelmet) ItemLoader.blanceHelmet).ModeChange(itemstack_head, player));
			return null;
        }
    }
}