package com.lh_lshen.mcbbs.huajiage.stand.messages;

import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.skills.TimeStopHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
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
		  this.pos = player.getPositionVector();
		  this.standName = standName;
	  }

	  @Override
	  public void fromBytes(ByteBuf buf)
	  {
		  this.playerName = ByteBufUtils.readUTF8String(buf);
		  this.standName = ByteBufUtils.readUTF8String(buf);
		  double x = buf.readDouble();
		  double y = buf.readDouble();
		  double z = buf.readDouble();
		  
		  this.pos = new Vec3d(x, y, z);
		  
	  }

	  @Override
	  public void toBytes(ByteBuf buf)
	  {
		  ByteBufUtils.writeUTF8String(buf, playerName);
		  ByteBufUtils.writeUTF8String(buf, standName);
		  buf.writeDouble(pos.x);
		  buf.writeDouble(pos.y);
		  buf.writeDouble(pos.z);
	  }

	  private String playerName;
	  private Vec3d pos;
	  private String standName;
public static class Handler implements IMessageHandler<MessageDoTimeStopClient, IMessage>
{
	  public IMessage onMessage(final MessageDoTimeStopClient message, MessageContext ctx) {
	  if (ctx.side == Side.CLIENT) {
	    Minecraft minecraft = Minecraft.getMinecraft();
	    final WorldClient worldClient = minecraft.world;
	    EntityPlayer player = worldClient.getPlayerEntityByName(message.playerName);
	    Vec3d pos = message.pos;
	    EnumStandtype stand = EnumStandtype.getType(message.standName);
	    minecraft.addScheduledTask(()->{ 
	    	if(player!=null) {
	    		if(stand!=null) {
	    			this.processStandClient(worldClient , pos , stand);
		    		}
	    	this.processClient(player);
	    		}
	    	});
	  	}
	    return null;
	  }
	  public void processStandClient(WorldClient world , Vec3d pos ,EnumStandtype stand) {
		  TimeStopHelper.doTimeStopClient(world, pos, stand);
	  }
	  public void processClient(EntityPlayer player) {
		  
	  }
	}
}
