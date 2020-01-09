package com.lh_lshen.mcbbs.huajiage.stand.messages;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandStageHandler;
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

public class SyncStandStageMessage implements IMessage {
    private int stage;

    public SyncStandStageMessage() {
    	
    }
    public SyncStandStageMessage(int stage) {
        this.stage = stage;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.stage = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(stage);
    }

    public int getStage() {
        return stage;
    }

    public static class Handler implements IMessageHandler<SyncStandStageMessage, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(SyncStandStageMessage message, MessageContext ctx) {
            if (ctx.side == Side.CLIENT) {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    EntityPlayer player = Minecraft.getMinecraft().player;
                    if (player == null) {
                        return;
                    }
                    StandStageHandler state = player.getCapability(CapabilityStandStageHandler.STAND_STAGE, null);
                    if (state != null) {
                        state.setStage(message.getStage());
                    }
                });
            }
            return null;
        }
    }
}
