package com.lh_lshen.mcbbs.huajiage.stand.messages;

import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.stand.StandStates;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.SoundCategory;
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
			IExposedData data = StandUtil.getStandData(player);
        	String state = StandUtil.getStandState(player);
        	if (ctx.side == Side.SERVER) {
            	FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->{
				if(null==stand || null==states || null==data) {
					return;
				}else {
					boolean flag = !states.isEmpty();
					if(flag) {
						if(state==null){
							player.sendMessage(new TextComponentTranslation("message.huajiage.stand_mode.empty_null"));
							return;
						}
//						int index = states.indexOf(state)+1;
//						if((index+1)<=states.size()){
//							StandUtil.setStandState(player,states.get(index));
//						}else {
//							StandUtil.setStandState(player,states.get(0));
//						}
						switchState(player,data,stand,1);

						String state_new = StandUtil.getStandState(player);
						StandStateBase stateBase = StandStates.getStandState(stand.getName(),state_new);
//						for(String s : stateBase.getExtraDatas()){
//							player.sendMessage(new TextComponentString(s));
//						}
//						if(stateBase.getExtraDatas().isEmpty()){
//							player.sendMessage(new TextComponentString("empty"));
//						}

						if(stateBase!=null){
						data.setHandDisplay(stateBase.isHandPlay());
//							player.sendMessage(new TextComponentString(stateBase.getStateName()+stateBase.isHandPlay()));
						}
						String stand_state_format="stand.state.huajiage"+"."+state_new;
						TextComponentTranslation state_trans = new TextComponentTranslation(stand_state_format);
						player.connection.sendPacket(new SPacketSoundEffect(SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.PLAYERS, player.posX, player.posY, player.posZ,0.5f, 1f));
						player.sendMessage(new TextComponentTranslation("message.huajiage.stand_mode.switch",state_trans.getUnformattedComponentText()));
					}else {
						player.sendMessage(new TextComponentTranslation("message.huajiage.stand_mode.empty"));
					}
				}
            		});
            	}
			return null;
        }
    }

    private static void switchState(EntityPlayer player, IExposedData data, StandBase stand, int i){
		String state = StandUtil.getStandState(player);
		List<String> states = stand.getStates();
		if(states!=null&&!state.isEmpty()){
			int index = states.indexOf(state)+i;
			if((index+1)<=states.size()){
				StandStateBase base = StandStates.getStandState(stand.getName(),states.get(index));
				if (base!=null) {
					if(base.getStage()<=data.getStage()){
						StandUtil.setStandState(player,states.get(index));
					}else{
						switchState(player,data,stand,i+1);
					}
//					player.sendMessage(new TextComponentString(base.getStage()+"-"+data.getStage()));
				}
			}else {
					StandUtil.setStandState(player,states.get(0));
			}
		}
	}
}