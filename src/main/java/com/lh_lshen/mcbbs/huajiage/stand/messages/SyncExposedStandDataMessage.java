package com.lh_lshen.mcbbs.huajiage.stand.messages;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityLoader;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;

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

public class SyncExposedStandDataMessage implements IMessage {
	private String user;
//    private String stand;
//    private boolean isTriggered;
    public NBTTagCompound nbt;

    public SyncExposedStandDataMessage() {
    }

    public SyncExposedStandDataMessage(String stand ,int stage, boolean trigger ,String user) {
//        this.stand = stand;
//        this.isTriggered = trigger;
        this.user = user;
        NBTTagCompound tags = new NBTTagCompound();
        tags.setString("stand_name", stand);
        tags.setBoolean("stand_put", trigger);
        tags.setInteger("stand_stage", stage);
        nbt =tags;
    }


    @Override
    public void fromBytes(ByteBuf buf)
    {
        nbt = ByteBufUtils.readTag(buf);
        user = ByteBufUtils.readUTF8String(buf);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        ByteBufUtils.writeTag(buf, nbt);
        ByteBufUtils.writeUTF8String(buf, user);
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
                    EntityPlayer player = Minecraft.getMinecraft().world.getPlayerEntityByName(message.getStandUser());
                    EntityPlayer user = Minecraft.getMinecraft().player;
                    EnumStandtype stand = StandUtil.getType(user);
                    NBTTagCompound nbt = message.nbt;
                    if (player == null ) {
                        return;
                    }else {
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
                    }
                });
            }
            return null;
        }
    }
}
