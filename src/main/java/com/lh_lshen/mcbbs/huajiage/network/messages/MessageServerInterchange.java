package com.lh_lshen.mcbbs.huajiage.network.messages;

import com.lh_lshen.mcbbs.huajiage.init.loaders.CapabilityLoader;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

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
        	if (ctx.side == Side.SERVER) {
        	EntityPlayerMP player = ctx.getServerHandler().player;
			IExposedData data = player.getCapability(CapabilityLoader.EXPOSED_DATA, null);
        	FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->{
			switch(message.type) {
			case 1:
				HuajiAgeNetWorkHandler.sendToNearby(player.world, player, new MessageParticleGenerator(player.getPositionVector(), EnumParticleTypes.FIREWORKS_SPARK, 60, 3, 1));
			break;
			case 2:
				String stand_type = data.getStand();
				if(stand_type!=null&&!stand_type.equals(StandLoader.EMPTY)) {
					HuajiAgeNetWorkHandler.sendToNearby(player.world, player.getPosition(), new MessageMovingSound(player.getName(),stand_type));
				}
			break;
			}
            		}) ;
            	}
			return null;
        }
    }
}