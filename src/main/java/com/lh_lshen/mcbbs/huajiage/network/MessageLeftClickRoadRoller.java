package com.lh_lshen.mcbbs.huajiage.network;

import java.util.List;

import com.lh_lshen.mcbbs.huajiage.entity.EntityRoadRoller;
import com.lh_lshen.mcbbs.huajiage.item.ItemHeroBow;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageLeftClickRoadRoller implements IMessage {
    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<MessageLeftClickRoadRoller, IMessage> {
        @Override
        public IMessage onMessage(MessageLeftClickRoadRoller message , MessageContext ctx) {
        	EntityPlayerMP player = ctx.getServerHandler().player;
        	List<EntityRoadRoller> road=player.getEntityWorld().getEntitiesWithinAABB(EntityRoadRoller.class,player.getEntityBoundingBox().grow(10));
			player.mcServer.addScheduledTask(() -> {
			if(road!=null) {
        	for(EntityRoadRoller i:road) {
        	    int a= i.getEntityData().getInteger("huajiage.dio_push");
                i.getEntityData().setInteger("huajiage.dio_push",a+2);
        	      }}
			});
			return null;
        }
    }
}