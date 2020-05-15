package com.lh_lshen.mcbbs.huajiage.network.messages;

import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.init.events.EventStand;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
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
	  public MessageMovingSound(String playerName,String stand_tpye)
	  {
	    messageIsValid = true;
	    this.entityId = playerName;
	    this.stand = stand_tpye;
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
	    	stand = ByteBufUtils.readUTF8String(buf);
	    	
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
	    ByteBufUtils.writeUTF8String(buf, stand);
	  }
	  
	  public String getEntityId() {
		return entityId;
	}
	  
	  public String getStandId() {
			return stand;
		}

	private boolean messageIsValid;
  	private String entityId;
  	private String stand;
	  
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
	    String playerId = message.getEntityId();
	    String standId = message.getStandId();
	    minecraft.addScheduledTask(new Runnable()
	    {
	    	public void run() {
	        processMessage(worldClient, playerId,standId);
	      }
	    });
	    return null;
	  }
	  void processMessage(WorldClient worldClient,String entityId,String standType)
	  {
       EntityPlayer player = worldClient.getPlayerEntityByName(entityId);
       if(player !=null) 
	       {
//	       String stand_type =player.getCapability(CapabilityStandHandler.STAND_TYPE, null).getStand();
	       if(!standType.equals(StandLoader.EMPTY)) 
		       {
		       StandClientUtil.standUpSound(Minecraft.getMinecraft(),player, standType);
		       }else {
		    	   System.out.println("null stand");
		       }
	       }else {
	    	   System.out.println("null player");
	       }
	    return;
	  }
	}
}
