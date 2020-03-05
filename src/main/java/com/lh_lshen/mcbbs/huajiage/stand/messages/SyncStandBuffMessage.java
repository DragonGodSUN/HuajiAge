package com.lh_lshen.mcbbs.huajiage.stand.messages;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandBuffHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandBuffHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SyncStandBuffMessage implements IMessage {
    private int time;

    public SyncStandBuffMessage() {
    	
    }
    public SyncStandBuffMessage(int time) {
        this.time = time;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.time = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(time);
    }

    public int getTicks() {
        return time;
    }

    public static class Handler implements IMessageHandler<SyncStandBuffMessage, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(SyncStandBuffMessage message, MessageContext ctx) {
            if (ctx.side == Side.CLIENT) {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    EntityPlayer player = Minecraft.getMinecraft().player;
                    if (player == null) {
                        return;
                    }
                    StandBuffHandler ticks = player.getCapability(CapabilityStandBuffHandler.STAND_BUFF, null);
                    if (ticks != null) {
                    	ticks.setTime(message.getTicks());
                    }
                });
            }
            return null;
        }
    }
}
