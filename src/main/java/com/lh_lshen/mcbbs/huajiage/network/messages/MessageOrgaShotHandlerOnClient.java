package com.lh_lshen.mcbbs.huajiage.network.messages;

import java.util.Random;

import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
//This is based on @TheGreyGhost's MinecraftByExample
//The link :https://github.com/TheGreyGhost/MinecraftByExample
public class MessageOrgaShotHandlerOnClient implements IMessageHandler<TargetOrgaShotEffectMessageToClient, IMessage>
{
	  /**
	   * Called when a message is received of the appropriate type.
	   * CALLED BY THE NETWORK THREAD, NOT THE CLIENT THREAD
	   * @param message The message
	   */
	  public IMessage onMessage(final TargetOrgaShotEffectMessageToClient message, MessageContext ctx) {
	    if (ctx.side != Side.CLIENT) {
	      System.err.println("TargetEffectMessageToClient received on wrong side:" + ctx.side);
	      return null;
	    }
	    if (!message.isMessageValid()) {
	      System.err.println("TargetEffectMessageToClient was invalid" + message.toString());
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

	  void processMessage(WorldClient worldClient, TargetOrgaShotEffectMessageToClient message)
	  {
	    Random random = new Random();
	    final int NUMBER_OF_PARTICLES = 150;
	    final double HORIZONTAL_SPREAD = 2; 
	    Vec3d targetCoordinates = message.getTargetCoordinates();
	    for (int i = 0; i < NUMBER_OF_PARTICLES; ++i) {
	     
	      double spawnXpos = targetCoordinates.x + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
	      double spawnYpos = targetCoordinates.y + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
	      double spawnZpos = targetCoordinates.z + (2*random.nextDouble() - 1) * HORIZONTAL_SPREAD;
	      worldClient.spawnParticle(EnumParticleTypes.SPELL_INSTANT, spawnXpos, spawnYpos, spawnZpos, 0, 0, 0);
	      
	    }
       worldClient.playSound(targetCoordinates.x , targetCoordinates.y,targetCoordinates.z,SoundLoader.ORGA_SHOT, SoundCategory.VOICE, 2f, 1f, true);
	    return;
	  }
	}
