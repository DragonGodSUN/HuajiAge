package com.lh_lshen.mcbbs.huajiage.network.messages;

import com.lh_lshen.mcbbs.huajiage.item.ItemExglutenbur;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageExglutenburMode implements IMessage {
	private boolean next;
	
	public MessageExglutenburMode() {

		}
    public MessageExglutenburMode(boolean next) {
		this.next = next;
	}

	@Override
    public void toBytes(ByteBuf buf) {
		buf.writeBoolean(next);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    	next = buf.readBoolean();
    }

    public static class Handler implements IMessageHandler<MessageExglutenburMode, IMessage> {
        @Override
        public IMessage onMessage(MessageExglutenburMode message , MessageContext ctx) {
        	if(ctx.side ==Side.SERVER) {
    		EntityPlayerMP player = ctx.getServerHandler().player;
    		ItemStack itemstack = player.getHeldItemMainhand();
    		if(itemstack.getItem()==ItemLoader.exglutenbur) {
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->{
	        	ItemExglutenbur item =(ItemExglutenbur) ItemLoader.exglutenbur;
	            int index = item.flavor(itemstack) + 4 + (message.next ? 1 : -1);
	            index %= 4;
	            item.setFlavor(itemstack,index);
//	            ((ItemExglutenbur) ItemLoader.exglutenbur).ModeChange(itemstack,player);
        			});
    			}
    		}
			return null;
        }
    }
}