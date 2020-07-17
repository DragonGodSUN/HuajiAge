package com.lh_lshen.mcbbs.huajiage.stand.messages;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

import java.util.List;

public class MessageStandModeSwitch implements IMessage {
	public MessageStandModeSwitch() {
	}

    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<MessageStandModeSwitch, IMessage> {
        @Override
        public IMessage onMessage(MessageStandModeSwitch message , MessageContext ctx) {
        	EntityPlayerMP player = ctx.getServerHandler().player;
        	StandBase stand = StandUtil.getType(player);
			List<String> states = stand.getStates();
        	String state = StandUtil.getStandState(player);
        	if (ctx.side == Side.SERVER) {
            	FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->{
				if(null==stand || null==states) {
					return;
				}else {
					boolean flag = !states.isEmpty();
					if(flag) {
						if(state==null){
							player.sendMessage(new TextComponentTranslation("message.huajiage.stand_mode.empty_null"));
						}
						int index = states.indexOf(state)+1;
						if((index+1)<=states.size()){
							StandUtil.setStandState(player,states.get(index));
						}else {
							StandUtil.setStandState(player,states.get(0));
						}
						String state_new = StandUtil.getStandState(player);
						String stand_state_format="state.huajiage"+"."+state_new;
						TextComponentTranslation state_trans = new TextComponentTranslation(stand_state_format);
						player.connection.sendPacket(new SPacketSoundEffect(SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, player.posX, player.posY, player.posZ,0.5f, 1f));
						player.sendMessage(new TextComponentTranslation("message.huajiage.stand_mode.switch",state_new));
					}else {
						player.sendMessage(new TextComponentTranslation("message.huajiage.stand_mode.empty"));
					}
				}
            		});
            	}
			return null;
        }
    }
}