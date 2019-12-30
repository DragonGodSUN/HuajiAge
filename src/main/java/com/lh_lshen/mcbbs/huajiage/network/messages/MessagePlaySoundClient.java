package com.lh_lshen.mcbbs.huajiage.network.messages;

import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
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
public class MessagePlaySoundClient implements IMessage
{
	  public MessagePlaySoundClient(Vec3d i_targetCoordinates,SoundEvent sound)
	  {
	    targetCoordinates = i_targetCoordinates;
	    messageIsValid = true;
	    this.sound=sound;
	  }

	  public Vec3d getTargetCoordinates() {
	    return targetCoordinates;
	  }

	  public boolean isMessageValid() {
	    return messageIsValid;
	  }

	  // for use by the message handler only.
	  public MessagePlaySoundClient()
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
	      targetCoordinates = new Vec3d(x, y, z);
	      sound=s;

	    } catch (IndexOutOfBoundsException ioe) {
	      System.err.println("Exception while reading TargetEffectMessageToClient: "+"wryyyyyyyyyyyyyyyyy" + ioe);
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
	  }

	  @Override
	  public String toString()
	  {
	    return "MessagePlaySoundClient[targetCoordinates=" + String.valueOf(targetCoordinates) +"flag="+String.valueOf(sound)+ "]";
	  }

		private Vec3d targetCoordinates;
		private boolean messageIsValid;
		private SoundEvent sound;
		public static class Handler implements IMessageHandler<MessagePlaySoundClient, IMessage>
		{
			  public IMessage onMessage(final MessagePlaySoundClient message, MessageContext ctx) {
			    if (ctx.side != Side.CLIENT) {
			      System.err.println("MessagePlaySoundClient received on wrong side:" + ctx.side);
			      return null;
			    }
			    if (!message.isMessageValid()) {
			      System.err.println("MessagePlaySoundClient was invalid" + message.toString());
			      return null;
			    }
		
			    Minecraft minecraft = Minecraft.getMinecraft();
			    final WorldClient worldClient = minecraft.world;
			    minecraft.addScheduledTask(new Runnable()
			    {
			      public void run() {
			        processMessage(worldClient, message);
			      }
			    });
			    
			    return null;
			  }
		
			  void processMessage(WorldClient worldClient, MessagePlaySoundClient message)
			  {
		
			    Vec3d targetCoordinates = message.getTargetCoordinates();
			    SoundEvent sounds=message.sound;
			   
			    worldClient.playSound(targetCoordinates.x , targetCoordinates.y,targetCoordinates.z,sounds, SoundCategory.PLAYERS, 1f, 1f, true);
		       
			    return;
			  }
			}
		}
