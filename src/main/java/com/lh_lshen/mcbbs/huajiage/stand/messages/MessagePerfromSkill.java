package com.lh_lshen.mcbbs.huajiage.stand.messages;

import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.item.ItemHeroBow;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.skill.StandSkillType;
import com.lh_lshen.mcbbs.huajiage.stand.skills.TimeStopHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessagePerfromSkill implements IMessage {
	private int cost;
	private float damage;
	private float distance;
	private int duration;
	private String typeId;
	public MessagePerfromSkill() {
	}
	public MessagePerfromSkill(int cost,float damage,float distance,int duration,StandSkillType type) {
		this.cost=cost;
		this.damage=damage;
		this.distance=distance;
		this.duration=duration;
		this.typeId=type.getId();
	}
    @Override
    public void toBytes(ByteBuf buf) {
    	buf.writeInt(cost);
    	buf.writeFloat(damage);
    	buf.writeFloat(distance);
    	buf.writeInt(duration);
    	ByteBufUtils.writeUTF8String(buf, typeId);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
    	this.cost=buf.readInt();
    	this.damage=buf.readFloat();
    	this.distance=buf.readFloat();
    	this.duration=buf.readInt();
    	this.typeId=ByteBufUtils.readUTF8String(buf);
    }

    public static class Handler implements IMessageHandler<MessagePerfromSkill, IMessage> {
        @Override
        public IMessage onMessage(MessagePerfromSkill message , MessageContext ctx) {
        	EntityPlayerMP player = ctx.getServerHandler().player;
        	EnumStandtype stand = StandUtil.getType(player);
        	StandChargeHandler charge = StandUtil.getChargeHandler(player);
        	if (ctx.side == Side.SERVER) {
            	FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() ->{
				if(null==stand) {
					return;
				}else {
					StandSkillType skill = StandSkillType.getTypeWithStringId(message.typeId);
					switch(skill) {
					case TIME_STOP:
					{
						boolean flag = charge.canBeCost(message.cost);
						if(flag) {
						TimeStopHelper.setEntityTimeStopRange(player, message.distance);
						TimeStopHelper.setTimeStop(player, message.duration);
						charge.cost(message.cost);
						}else {
							player.sendMessage(new TextComponentTranslation("message.huajiage.stand_skill.cost_lack"));
						}
						break;
					}
					default:
						break;
					}
					
				}
				
            		});
            	}
			return null;
        }
    }
}