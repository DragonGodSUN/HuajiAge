package com.lh_lshen.mcbbs.huajiage.network.messages;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SyncStandMessage implements IMessage {
    private String stand;

    public SyncStandMessage() {
    }

    public SyncStandMessage(String power) {
        this.stand = power;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.stand = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeUTF8String(buf, stand);;
    }

    public String getStand() {
        return stand;
    }

    public static class Handler implements IMessageHandler<SyncStandMessage, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(SyncStandMessage message, MessageContext ctx) {
            if (ctx.side == Side.CLIENT) {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    EntityPlayer player = Minecraft.getMinecraft().player;
                    if (player == null) {
                        return;
                    }
                    StandHandler power = player.getCapability(CapabilityStandHandler.STAND_TYPE, null);
                    if (power != null) {
                        power.setStand(message.getStand());
                    }
                });
            }
            return null;
        }
    }
}
