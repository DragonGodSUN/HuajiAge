package com.lh_lshen.mcbbs.huajiage.stand.messages;

import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.client.render.layers.LayerStand;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SyncStandUserMessage implements IMessage {
    private String stand;
    private String name;

    public SyncStandUserMessage() {
    }

    public SyncStandUserMessage(String stand,String name) {
        this.stand = stand;
        this.name = name;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.stand = ByteBufUtils.readUTF8String(buf);
        this.name = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, stand);
        ByteBufUtils.writeUTF8String(buf, name);
    }

    public String getStand() {
        return stand;
    }
    
    public String getName() {
        return name;
    }

    public static class Handler implements IMessageHandler<SyncStandUserMessage, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(SyncStandUserMessage message, MessageContext ctx) {
            if (ctx.side == Side.CLIENT) {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    EntityPlayer player = Minecraft.getMinecraft().world.getPlayerEntityByName(message.name);
                    if (player == null) {
                    	System.out.println("null player");
                        return;
                    }
                    StandHandler stand = player.getCapability(CapabilityStandHandler.STAND_TYPE, null);
                    if (stand != null) {
                        stand.setStand(message.getStand());
                    }else {
                    	System.out.println("null stand");
                    }
                });
            }
            return null;
        }
    }
}
