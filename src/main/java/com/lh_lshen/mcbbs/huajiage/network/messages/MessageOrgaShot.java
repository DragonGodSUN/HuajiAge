package com.lh_lshen.mcbbs.huajiage.network.messages;

import java.util.Random;
import java.util.UUID;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.ClientProxy;
import com.lh_lshen.mcbbs.huajiage.common.CommonProxy;
import com.lh_lshen.mcbbs.huajiage.init.playsound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemHeroBow;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
//This is based on TheGreyGhost's MinecraftByExample
//The link :https://github.com/TheGreyGhost/MinecraftByExample
public class MessageOrgaShot implements IMessage{

    public MessageOrgaShot(Vec3d i_targetCoordinates)
    {
      targetCoordinates = i_targetCoordinates;
      messageIsValid = true;
    }

    public Vec3d getTargetCoordinates() {
      return targetCoordinates;
    }

   

    public boolean isMessageValid() {
      return messageIsValid;
    }

    // for use by the message handler only.
    public MessageOrgaShot()
    {
      messageIsValid = false;
    }

    /**
     * Called by the network code once it has received the message bytes over the network.
     * Used to read the ByteBuf contents into your member variables
     * @param buf
     */
    @Override
    public void fromBytes(ByteBuf buf)
    {
      try {
        double x = buf.readDouble();
        double y = buf.readDouble();
        double z = buf.readDouble();
        targetCoordinates = new Vec3d(x, y, z);

        // these methods may also be of use for your code:
        // for Itemstacks - ByteBufUtils.readItemStack()
        // for NBT tags ByteBufUtils.readTag();
        // for Strings: ByteBufUtils.readUTF8String();

      } catch (IndexOutOfBoundsException ioe) {
        System.err.println("Exception while reading AirStrikeMessageToServer: " + ioe);
        return;
      }
      messageIsValid = true;
    }

    /**
     * Called by the network code.
     * Used to write the contents of your message member variables into the ByteBuf, ready for transmission over the network.
     * @param buf
     */
    @Override
    public void toBytes(ByteBuf buf)
    {
      buf.writeDouble(targetCoordinates.x);
      buf.writeDouble(targetCoordinates.y);
      buf.writeDouble(targetCoordinates.z);

      // these methods may also be of use for your code:
      // for Itemstacks - ByteBufUtils.writeItemStack()
      // for NBT tags ByteBufUtils.writeTag();
      // for Strings: ByteBufUtils.writeUTF8String();
    }

    


    @Override
    public String toString()
    {
      return String.valueOf(targetCoordinates);
    }

    private Vec3d targetCoordinates;

    private boolean messageIsValid;
    }