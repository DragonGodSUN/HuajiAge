package com.lh_lshen.mcbbs.huajiage.network;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketGuiShortOverride implements IMessage, IMessageHandler<PacketGuiShortOverride, IMessage> {

	  public int fieldId, value;

	  public PacketGuiShortOverride() {}

	  public PacketGuiShortOverride(int f, int v) {
	    this.fieldId = f;
	    this.value = v;
	  }

	  @Override
	  public void fromBytes(ByteBuf buf) {
	    fieldId = buf.readInt();
	    value = buf.readInt();
	  }

	  @Override
	  public void toBytes(ByteBuf buf) {
	    //not writeShort as the legacy vanilla system does
	    buf.writeInt(fieldId);
	    buf.writeInt(value);
	  }

	  @Override
	  public IMessage onMessage(PacketGuiShortOverride message, MessageContext ctx) {
	    final IThreadListener mainThread = Minecraft.getMinecraft();
	    mainThread.addScheduledTask(new Runnable() {

	      @Override
	      public void run() {
	  	    Minecraft minecraft = Minecraft.getMinecraft();
	        EntityPlayer player = minecraft.player;
	        if (player.openContainer != null) {
	          player.openContainer.updateProgressBar(message.fieldId, message.value);
	        }
	      }
	    });
	    return null;
	  }
	}
