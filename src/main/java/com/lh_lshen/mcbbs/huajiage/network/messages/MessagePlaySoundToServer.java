package com.lh_lshen.mcbbs.huajiage.network.messages;

import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
//This is based on TheGreyGhost's MinecraftByExample
//The link :https://github.com/TheGreyGhost/MinecraftByExample
public class MessagePlaySoundToServer implements IMessage
{
	  public MessagePlaySoundToServer(Vec3d i_targetCoordinates,SoundEvent sound,float volume,int loop)
	  {
	    targetCoordinates = i_targetCoordinates;
	    messageIsValid = true;
	    this.sound=sound;
	    this.volume = volume;
	    this.loop = loop;
	  }

	  public boolean isMessageValid() {
	    return messageIsValid;
	  }
	  public MessagePlaySoundToServer()
	  {
	    messageIsValid = false;
	  }

	  @Override
	  public void fromBytes(ByteBuf buf)
	  {
	    try {
	      double x = buf.readDouble();
	      double y = buf.readDouble();
	      double z = buf.readDouble();
	      SoundEvent s=ByteBufUtils.readRegistryEntry(buf, ForgeRegistries.SOUND_EVENTS);
	      volume = buf.readFloat();
	      loop = buf.readInt();
	      targetCoordinates = new Vec3d(x, y, z);
	      sound = s;

	    } catch (IndexOutOfBoundsException ioe) {
	      System.err.println("Exception while reading : "+"wryyyyyyyyyyyyyyyyy" + ioe);
	      return;
	    }
	    messageIsValid = true;
	  }

	  @Override
	  public void toBytes(ByteBuf buf)
	  {
	    if (!messageIsValid) return;
	    buf.writeDouble(targetCoordinates.x);
	    buf.writeDouble(targetCoordinates.y);
	    buf.writeDouble(targetCoordinates.z);
	    ByteBufUtils.writeRegistryEntry(buf, sound);
	    buf.writeFloat(volume);
	    buf.writeInt(loop);
	  }
	  
		private Vec3d targetCoordinates;
		private boolean messageIsValid;
		private SoundEvent sound;
		private float volume;
		private int loop;
		public static class Handler implements IMessageHandler<MessagePlaySoundToServer, IMessage>
		{
			  public IMessage onMessage(final MessagePlaySoundToServer message, MessageContext ctx) {
			    EntityPlayerMP player = ctx.getServerHandler().player;
			    World world = player.world;
			    player.mcServer.addScheduledTask(()->
			    {
			    Vec3d targetCoordinates = message.targetCoordinates;
			    SoundEvent sounds=message.sound;
			    float volume = message.volume;
			    for(int i =0 ;i<message.loop;i++) {
			    HuajiAgeNetWorkHandler.sendToNearby(world, player, new MessagePlaySoundClient(targetCoordinates, sounds, volume));
			    }
//			    world.playSound(targetCoordinates.x , targetCoordinates.y,targetCoordinates.z,sounds, SoundCategory.PLAYERS, message.volume, 1f, true);
			    return;
			      });
			    return null;
			  }
		  } 
	}
