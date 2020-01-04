package com.lh_lshen.mcbbs.huajiage.network.messages;

import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.init.events.EventStand;
import com.lh_lshen.mcbbs.huajiage.item.ItemHeroBow;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.util.EnumStandtype;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageStandUp implements IMessage {
    @Override
    public void toBytes(ByteBuf buf) {
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    }

    public static class Handler implements IMessageHandler<MessageStandUp, IMessage> {
        @Override
        public IMessage onMessage(MessageStandUp message , MessageContext ctx) {
        	EntityPlayerMP player = ctx.getServerHandler().player;
        	String standType = player.getCapability(CapabilityStandHandler.STAND_TYPE, null).getStand();
        	EnumStandtype stand = EnumStandtype.getType(standType);
        	if(stand==null)
        		return null;
			player.mcServer.addScheduledTask(() -> {
				if(!player.isPotionActive(PotionLoader.potionStand)) {
					player.getFoodStats().setFoodLevel(player.getFoodStats().getFoodLevel()-1);
//					MessageMovingSound moving_sound = new MessageMovingSound(player.getName());
//					HuajiAgeNetWorkHandler.sendToNearby(player.world, player, moving_sound);
					player.addPotionEffect(new PotionEffect(PotionLoader.potionStand,stand.getDuration()));
//					System.out.println(player.getName());
					}else {
						player.removePotionEffect(PotionLoader.potionStand);;
					}
			});
			return null;
        }
    }
}