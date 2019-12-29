package com.lh_lshen.mcbbs.huajiage.network.messages;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandSkillHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandSkillHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class SyncStandSkillMessage implements IMessage {
    private int stage;

    public SyncStandSkillMessage() {
    	
    }
    public SyncStandSkillMessage(int stage) {
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

    public static class Handler implements IMessageHandler<SyncStandSkillMessage, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(SyncStandSkillMessage message, MessageContext ctx) {
            if (ctx.side == Side.CLIENT) {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    EntityPlayer player = Minecraft.getMinecraft().player;
                    if (player == null) {
                        return;
                    }
                    StandSkillHandler state = player.getCapability(CapabilityStandSkillHandler.STAND_SKILL, null);
                    if (state != null) {
                        state.setStage(message.getStage());
                    }
                });
            }
            return null;
        }
    }
}
