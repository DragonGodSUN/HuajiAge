package com.lh_lshen.mcbbs.huajiage.network.messages;

import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.item.ItemHeroBow;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageLeftClickModeChange implements IMessage {
    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<MessageLeftClickModeChange, IMessage> {
        @Override
        public IMessage onMessage(MessageLeftClickModeChange message , MessageContext ctx) {
        	EntityPlayerMP player = ctx.getServerHandler().player;
        	ItemStack itemstack=ctx.getServerHandler().player.getHeldItemMainhand();
        if(itemstack.getItem()==ItemLoader.heroBow) {
			player.mcServer.addScheduledTask(() -> ((ItemHeroBow) ItemLoader.heroBow).ModeChange(itemstack, player));
					}
			return null;
        }
    }
}