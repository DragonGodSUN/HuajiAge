package com.lh_lshen.mcbbs.huajiage.util;

import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageDioHitClient;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ServerUtil {
	public static void sendPacketToPlayers(EntityLivingBase player,IMessage msg) {	
		  try { 
              int dimension = player.dimension;
       	    MinecraftServer minecraftServer = player.getServer();
       	    if(minecraftServer.getPlayerList().getPlayers()!=null) {
       	    for (EntityPlayerMP player0 : minecraftServer.getPlayerList().getPlayers()) {
         // must generate a fresh message for every player!
       	        if (dimension == player.dimension) {
       	          HuajiAgeNetWorkHandler.sendTo(player0, msg);
                      }
       	           }
       	      }
          }catch(Exception e) {e.printStackTrace();}

     }
	  public static SoundEvent getRegisteredSoundEvent(String id)
	    {
	        SoundEvent soundevent = SoundEvent.REGISTRY.getObject(new ResourceLocation(id));

	        if (soundevent == null)
	        {
	            throw new IllegalStateException("Invalid Sound requested: " + id);
	        }
	        else
	        {
	            return soundevent;
	        }
	    }
  }
