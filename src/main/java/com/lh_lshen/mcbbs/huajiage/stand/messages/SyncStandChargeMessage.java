package com.lh_lshen.mcbbs.huajiage.stand.messages;

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

public class SyncStandChargeMessage implements IMessage {
    private int charge;
    private int max;
    private int buffer;
    private String buffTag;

    public SyncStandChargeMessage() {
    	
    }
    public SyncStandChargeMessage(int value, int max, int buffer, String buffTag) {
        this.charge = value;
        this.max = max;
        this.buffer = buffer;
        this.buffTag = buffTag;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.charge = buf.readInt();
        this.max = buf.readInt();
        this.buffer = buf.readInt();
        this.buffTag = ByteBufUtils.readUTF8String(buf);

    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(charge);
        buf.writeInt(max);
        buf.writeInt(buffer);
        ByteBufUtils.writeUTF8String(buf,buffTag);
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

    public String getBuffTag() {
        return buffTag;
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
                    StandHandler charge = player.getCapability(CapabilityStandHandler.STAND_HANDLER, null);
                    if (charge != null) {
                        charge.setChargeValue(message.getChargeValue());
                        charge.setMaxValue(message.getMaxValue());
                        charge.setBuffer(message.getBuffer());
                        charge.setBuffTag(message.getBuffTag());
                    }
                });
            }
            return null;
        }
    }
}
