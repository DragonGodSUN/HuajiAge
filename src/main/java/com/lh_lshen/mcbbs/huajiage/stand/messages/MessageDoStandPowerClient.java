package com.lh_lshen.mcbbs.huajiage.stand.messages;

import com.lh_lshen.mcbbs.huajiage.api.IStandPower;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.helper.skill.TimeStopHelper;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageDoStandPowerClient implements IMessage
{
		public MessageDoStandPowerClient()
		{
			
		}
	  public MessageDoStandPowerClient(EntityPlayer player , String standName)
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
public static class Handler implements IMessageHandler<MessageDoStandPowerClient, IMessage>
{
	  public IMessage onMessage(final MessageDoStandPowerClient message, MessageContext ctx) {
	  if (ctx.side == Side.CLIENT) {
	    Minecraft minecraft = Minecraft.getMinecraft();
	    final WorldClient worldClient = minecraft.world;
	    EntityPlayer player = worldClient.getPlayerEntityByName(message.playerName);
	    EnumStandtype stand = EnumStandtype.getType(message.standName);
	    minecraft.addScheduledTask(()->{ 
	    	if(player!=null) {
	    		if(stand!=null) {
	    			this.processStandClient(worldClient , player , stand);
		    		}
	    		}
	    	});
	  	}
	    return null;
	  }
	  public void processStandClient(WorldClient world , EntityLivingBase entity ,EnumStandtype stand) {
		  IStandPower power = stand.getStandPower();
		  power.doStandCapabilityClient(world, entity);
		 
	  }
	}
}
