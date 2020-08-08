package com.lh_lshen.mcbbs.huajiage.stand.messages;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SyncStandChargeMessage implements IMessage {
    private int charge;
    private int max;
    private int buffer;

    public SyncStandChargeMessage() {
    	
    }
    public SyncStandChargeMessage(int value, int max, int buffer) {
        this.charge = value;
        this.max = max;
        this.buffer = buffer;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.charge = buf.readInt();
        this.max = buf.readInt();
        this.buffer = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(charge);
        buf.writeInt(max);
        buf.writeInt(buffer);
    }

    public int getChargeValue() {
        return charge;
    }
    
    public int getMaxValue() {
        return max;
    }

    public int getBuffer() {
        return buffer;
    }

    public static class Handler implements IMessageHandler<SyncStandChargeMessage, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(SyncStandChargeMessage message, MessageContext ctx) {
            if (ctx.side == Side.CLIENT) {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    EntityPlayer player = Minecraft.getMinecraft().player;
                    if (player == null) {
                        return;
                    }
                    StandChargeHandler charge = player.getCapability(CapabilityStandChargeHandler.STAND_CHARGE, null);
                    if (charge != null) {
                        charge.setChargeValue(message.getChargeValue());
                        charge.setMaxValue(message.max);
                        charge.setBuffer(message.buffer);
                    }
                });
            }
            return null;
        }
    }
}
