package com.lh_lshen.mcbbs.huajiage.network.messages;

import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
//This is based on TheGreyGhost's MinecraftByExample
//The link :https://github.com/TheGreyGhost/MinecraftByExample
public class MessageOrgaRequiemClient implements IMessage
{
	  public MessageOrgaRequiemClient(Vec3d i_targetCoordinates)
	  {
	    targetCoordinates = i_targetCoordinates;
	    messageIsValid = true;
	  }

	  public Vec3d getTargetCoordinates() {
	    return targetCoordinates;
	  }

	  public boolean isMessageValid() {
	    return messageIsValid;
	  }

	  // for use by the message handler only.
	  public MessageOrgaRequiemClient()
	  {
	    messageIsValid = false;
	  }

	  /**
	   * Called by the network code once it has received the message bytes over the network.
	   * Used to read the ByteBuf contents into your member variables
	   * @param buf
	   */
	  @Override
	  public void fromBytes(ByteBuf buf)
	  {
	    try {
	      double x = buf.readDouble();
	      double y = buf.readDouble();
	      double z = buf.readDouble();
	      targetCoordinates = new Vec3d(x, y, z);


	    } catch (IndexOutOfBoundsException ioe) {
	      System.err.println("Exception while reading TargetEffectMessageToClient: " + ioe);
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

	  }

	  @Override
	  public String toString()
	  {
	    return "MessageOrgaRequiemClient[targetCoordinates=" + String.valueOf(targetCoordinates) + "]";
	  }

	  private Vec3d targetCoordinates;
	  private boolean messageIsValid;
public static class Handler implements IMessageHandler<MessageOrgaRequiemClient, IMessage>
{
	  public IMessage onMessage(final MessageOrgaRequiemClient message, MessageContext ctx) {
	    if (ctx.side != Side.CLIENT) {
	      System.err.println("MessageOrgaRequiemClient received on wrong side:" + ctx.side);
	      return null;
	    }
	    if (!message.isMessageValid()) {
	      System.err.println("MessageOrgaRequiemClient was invalid" + message.toString());
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
	  void processMessage(WorldClient worldClient, MessageOrgaRequiemClient message)
	  {
	    Random random = new Random();
	    final int NUMBER_OF_PARTICLES = 200;
	    final double HORIZONTAL_SPREAD = 2; 
	    Vec3d targetCoordinates = message.getTargetCoordinates();
	    for (int i = 0; i < NUMBER_OF_PARTICLES; ++i) {
	     
	      double spawnXpos = targetCoordinates.x + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
	      double spawnYpos = targetCoordinates.y + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
	      double spawnZpos = targetCoordinates.z + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
	      worldClient.spawnParticle(EnumParticleTypes.FIREWORKS_SPARK, spawnXpos, spawnYpos, spawnZpos, 0, 0, 0);
	      
	    }
	    return;
	  }
	}
}
