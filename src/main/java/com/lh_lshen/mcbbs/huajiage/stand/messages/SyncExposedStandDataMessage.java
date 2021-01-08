package com.lh_lshen.mcbbs.huajiage.stand.messages;

import com.lh_lshen.mcbbs.huajiage.init.loaders.CapabilityLoader;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.UUID;

public class SyncExposedStandDataMessage implements IMessage {
	private String user;
	private boolean isUser;
    public NBTTagCompound nbt;

    public SyncExposedStandDataMessage() {
    }

    public SyncExposedStandDataMessage(String stand ,int stage, boolean trigger , boolean hand ,String state, String model , String user, boolean isUser) {
//        this.stand = stand;
//        this.isTriggered = trigger;
        this.user = user;
        this.isUser = isUser;
        NBTTagCompound tags = new NBTTagCompound();
        tags.setString("stand_name", stand);
        tags.setBoolean("stand_put", trigger);
        tags.setBoolean("stand_hand", hand);
        tags.setInteger("stand_stage", stage);
        tags.setString("stand_state", state);
        tags.setString("stand_model.json", model);
        nbt =tags;
    }


    @Override
    public void fromBytes(ByteBuf buf)
    {
        nbt = ByteBufUtils.readTag(buf);
        user = ByteBufUtils.readUTF8String(buf);
        isUser = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeTag(buf, nbt);
        ByteBufUtils.writeUTF8String(buf, user);
        buf.writeBoolean(isUser);
    }
    
    public String getStandUser() {
        return user;
    }

    public static class Handler implements IMessageHandler<SyncExposedStandDataMessage, IMessage> {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(SyncExposedStandDataMessage message, MessageContext ctx) {
            if (ctx.side == Side.CLIENT) {
                Minecraft.getMinecraft().addScheduledTask(() -> {
                    EntityPlayer user = Minecraft.getMinecraft().player;
                    EntityPlayer player =message.isUser ?
                         user:Minecraft.getMinecraft().world.getPlayerEntityByUUID(UUID.fromString(message.user));
                    StandBase stand = StandUtil.getType(user);
                    NBTTagCompound nbt = message.nbt;

                    if (player == null ) {
                        return;
                    }

                    IExposedData data = player.getCapability(CapabilityLoader.EXPOSED_DATA, null);
                    if (data != null ) {
                    	 IStorage<IExposedData> storage = CapabilityLoader.EXPOSED_DATA.getStorage();
	                    	 if(stand!=null) {
	                    	 storage.readNBT(CapabilityLoader.EXPOSED_DATA, data, null, nbt);
	                    	 }else {
                    		 nbt.setBoolean("stand_put", false);
                    		 storage.readNBT(CapabilityLoader.EXPOSED_DATA, data, null, nbt);
	                    	 }
                    	}
                });
            }
            return null;
        }
    }
}
