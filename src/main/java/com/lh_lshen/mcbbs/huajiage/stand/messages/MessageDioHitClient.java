package com.lh_lshen.mcbbs.huajiage.stand.messages;

import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;

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
public class MessageDioHitClient implements IMessage
{
	  public MessageDioHitClient(Vec3d i_targetCoordinates,boolean flag)
	  {
	    targetCoordinates = i_targetCoordinates;
	    messageIsValid = true;
	    this.flag=flag;
	  }

	  public Vec3d getTargetCoordinates() {
	    return targetCoordinates;
	  }

	  public boolean isMessageValid() {
	    return messageIsValid;
	  }

	  // for use by the message handler only.
	  public MessageDioHitClient()
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
	      boolean f=buf.readBoolean();
	      targetCoordinates = new Vec3d(x, y, z);
	      flag=f;

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
	    buf.writeBoolean(flag);

	  }

	  @Override
	  public String toString()
	  {
	    return "MessageDioHitClient[targetCoordinates=" + String.valueOf(targetCoordinates) +"flag="+String.valueOf(flag)+ "]";
	  }

	  private Vec3d targetCoordinates;
	  private boolean messageIsValid;
	  private boolean flag;
public static class Handler implements IMessageHandler<MessageDioHitClient, IMessage>
{
	  public IMessage onMessage(final MessageDioHitClient message, MessageContext ctx) {
	    if (ctx.side != Side.CLIENT) {
	      System.err.println("MessageDioHitClient received on wrong side:" + ctx.side);
	      return null;
	    }
	    if (!message.isMessageValid()) {
	      System.err.println("MessageDioHitClient was invalid" + message.toString());
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
	  void processMessage(WorldClient worldClient, MessageDioHitClient message)
	  {
	    Random random = new Random();
	    final int NUMBER_OF_PARTICLES = 50;
	    final double HORIZONTAL_SPREAD = 2; 
	    Vec3d targetCoordinates = message.getTargetCoordinates();
	    boolean flag=message.flag;
	    for (int i = 0; i < NUMBER_OF_PARTICLES; ++i) {
	     
	      double spawnXpos = targetCoordinates.x + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
	      double spawnYpos = targetCoordinates.y + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
	      double spawnZpos = targetCoordinates.z + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
	      worldClient.spawnParticle(EnumParticleTypes.LAVA, spawnXpos, spawnYpos, spawnZpos, 0, 0, 0);
	    }
	    worldClient.playSound(targetCoordinates.x , targetCoordinates.y,targetCoordinates.z,flag?SoundLoader.DIO_HIT:SoundLoader.DIO_FLAG, SoundCategory.VOICE, 2f, 1f, true);
       
	    return;
	  }
	}
}
