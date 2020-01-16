package com.lh_lshen.mcbbs.huajiage.stand.messages;

import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.skills.TimeStopHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageDoTimeStopClient implements IMessage
{
		public MessageDoTimeStopClient()
		{
			
		}
	  public MessageDoTimeStopClient(EntityPlayer player , String standName)
	  {
		  this.playerName = player.getName();
		  this.standName = standName;
	  }

	  @Override
	  public void fromBytes(ByteBuf buf)
	  {
		  this.playerName = ByteBufUtils.readUTF8String(buf);
		  this.standName = ByteBufUtils.readUTF8String(buf);
	  }

	  @Override
	  public void toBytes(ByteBuf buf)
	  {
		  ByteBufUtils.writeUTF8String(buf, playerName);
		  ByteBufUtils.writeUTF8String(buf, standName);
	  }

	  private String playerName;
	  private String standName;
public static class Handler implements IMessageHandler<MessageDoTimeStopClient, IMessage>
{
	  public IMessage onMessage(final MessageDoTimeStopClient message, MessageContext ctx) {
	  if (ctx.side == Side.CLIENT) {
	    Minecraft minecraft = Minecraft.getMinecraft();
	    final WorldClient worldClient = minecraft.world;
	    EntityPlayer player = worldClient.getPlayerEntityByName(message.playerName);
	    EnumStandtype stand = EnumStandtype.getType(message.standName);
	    minecraft.addScheduledTask(()->{ 
	    	if(player!=null) {
	    		if(stand!=null) {
	    			this.processStandClient(player, stand);
		    		}
	    	this.processClient(player);
	    		}
	    	});
	  	}
	    return null;
	  }
	  public void processStandClient(EntityPlayer player,EnumStandtype stand) {
		  TimeStopHelper.doTimeStopClient(player, stand);
	  }
	  public void processClient(EntityPlayer player) {
		  
	  }
	}
}
