package com.lh_lshen.mcbbs.huajiage.network.messages;

import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.init.events.EventStand;
import com.lh_lshen.mcbbs.huajiage.util.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.util.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.util.StandUtil;
import com.mojang.util.UUIDTypeAdapter;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
//This is based on TheGreyGhost's MinecraftByExample
//The link :https://github.com/TheGreyGhost/MinecraftByExample
public class MessageMovingSound implements IMessage
{
	  public MessageMovingSound(String playerName)
	  {
	    messageIsValid = true;
	    this.entityId = playerName;
	  }

	  public boolean isMessageValid() {
	    return messageIsValid;
	  }
	  public MessageMovingSound()
	  {
	    messageIsValid = false;
	  }
	  @Override
	  public void fromBytes(ByteBuf buf)
	  {
	    try {
	    	entityId = ByteBufUtils.readUTF8String(buf);
	    	
	    } catch (IndexOutOfBoundsException ioe) {
	      System.err.println(ioe);
	      return;
	    }
	    messageIsValid = true;
	  }

	  @Override
	  public void toBytes(ByteBuf buf)
	  {
	    if (!messageIsValid) return;
	    ByteBufUtils.writeUTF8String(buf, entityId);
	  }
	  
	  public String getEntityId() {
		return entityId;
	}

	private boolean messageIsValid;
  	private String entityId;
	  
public static class Handler implements IMessageHandler<MessageMovingSound, IMessage>
{
	  public IMessage onMessage(final MessageMovingSound message, MessageContext ctx) {
	    if (ctx.side != Side.CLIENT) {
	      System.err.println("Client received on wrong side:" + ctx.side);
	      return null;
	    }
	    if (!message.isMessageValid()) {
	      System.err.println("Client was invalid" + message.toString());
	      return null;
	    }
	    Minecraft minecraft = Minecraft.getMinecraft();
	    final WorldClient worldClient = minecraft.world;
	    String id = message.getEntityId();
	    minecraft.addScheduledTask(new Runnable()
	    {
	    	public void run() {
	        processMessage(worldClient, id);
	      }
	    });
	    return null;
	  }
	  void processMessage(WorldClient worldClient,String entityId)
	  {
       EntityPlayer player = worldClient.getPlayerEntityByName(entityId);
       if(player !=null) 
       {
       String stand_type =player.getCapability(CapabilityStandHandler.STAND_TYPE, null).getStand();
       if(stand_type.equals(EnumStandtype.EMPTY)) 
       {
    	   return;
       }
       StandClientUtil.standUpSound(player, stand_type);
       }
	    return;
	  }
	}
}
