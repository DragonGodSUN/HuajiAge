package com.lh_lshen.mcbbs.huajiage.network.messages;

import java.util.Random;

import com.ibm.icu.impl.duration.impl.DataRecord.EUnitVariant;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
//This is based on TheGreyGhost's MinecraftByExample
//The link :https://github.com/TheGreyGhost/MinecraftByExample
public class MessageParticleGenerator implements IMessage
{
	  public MessageParticleGenerator(Vec3d i_targetCoordinates,EnumParticleTypes particle)
	  {
	    targetCoordinates = i_targetCoordinates;
	    messageIsValid = true;
	    this.particleName = particle.getParticleName();
	    this.particle =particle;
	  }

	  public Vec3d getTargetCoordinates() {
	    return targetCoordinates;
	  }

	  public boolean isMessageValid() {
	    return messageIsValid;
	  }

	  // for use by the message handler only.
	  public MessageParticleGenerator()
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
	      String p= ByteBufUtils.readUTF8String(buf);
	      targetCoordinates = new Vec3d(x, y, z);
	      particleName = p;
	      particle = EnumParticleTypes.getByName(p);

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
	    ByteBufUtils.writeUTF8String(buf, particleName);

	  }

	  @Override
	  public String toString()
	  {
	    return "MessageDioHitClient[targetCoordinates=" + String.valueOf(targetCoordinates)+ "]";
	  }

	  private Vec3d targetCoordinates;
	  private boolean messageIsValid;
	  private EnumParticleTypes particle;
	  private String particleName;
public static class Handler implements IMessageHandler<MessageParticleGenerator, IMessage>
{
	  public IMessage onMessage(final MessageParticleGenerator message, MessageContext ctx) {
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
	  void processMessage(WorldClient worldClient, MessageParticleGenerator message)
	  {
	    processEffectParticle(worldClient, message.particle, message.targetCoordinates, 1);
	    return;
	  }
	  public static void processEffectParticle(WorldClient client,EnumParticleTypes particle ,Vec3d pos ,int type) {
		switch(type) {
			case 1:{
			Random random = new Random();
		    final int NUMBER_OF_PARTICLES = 60;
		    final double HORIZONTAL_SPREAD = 3; 
		    Vec3d targetCoordinates = pos;
		    for (int i = 0; i < NUMBER_OF_PARTICLES; ++i) {
		      double spawnXpos = targetCoordinates.x + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
		      double spawnYpos = targetCoordinates.y + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
		      double spawnZpos = targetCoordinates.z + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
		      client.spawnParticle(particle, spawnXpos, spawnYpos, spawnZpos, 0, 0, 0);
					}
		    break;
				}
		  	}
	  	}
	}
}

