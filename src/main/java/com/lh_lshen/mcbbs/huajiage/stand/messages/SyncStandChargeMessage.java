package com.lh_lshen.mcbbs.huajiage.stand.messages;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandStageHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandStageHandler;

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
    private int chage;
    private int max;

    public SyncStandChargeMessage() {
    	
    }
    public SyncStandChargeMessage(int value , int max) {
        this.chage = value;
        this.max = max;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.chage = buf.readInt();
        this.max = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(chage);
        buf.writeInt(max);
    }

    public int getChargeValue() {
        return chage;
    }
    
    public int getMaxValue() {
        return max;
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
//                        charge.setMaxValue(message.max);
                    }
                });
            }
            return null;
        }
    }
}
