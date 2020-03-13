package com.lh_lshen.mcbbs.huajiage.stand.messages;

import java.util.List;

import com.lh_lshen.mcbbs.huajiage.entity.EntityRoadRoller;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.item.ItemHeroBow;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

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
        	if (ctx.side == Side.SERVER) {
    		EntityPlayerMP player = ctx.getServerHandler().player;
        	List<EntityRoadRoller> road=player.getEntityWorld().getEntitiesWithinAABB(EntityRoadRoller.class,player.getEntityBoundingBox().grow(20));
        	FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->{
			if(road!=null) {
			if(NBTHelper.getEntityBoolean(player, HuajiConstant.Tags.THE_WORLD)) {
	        	for(EntityRoadRoller i:road) {
	        	    int a= i.getEntityData().getInteger("huajiage.dio_push");
	                i.getEntityData().setInteger("huajiage.dio_push",a+2);
	        	      }}}
            		});
            	}
			return null;
        }
    }
}