package com.lh_lshen.mcbbs.huajiage.network.messages;

import io.netty.buffer.ByteBuf;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
//This is based on TheGreyGhost's MinecraftByExample
//The link :https://github.com/TheGreyGhost/MinecraftByExample
public class TargetOrgaShotEffectMessageToClient implements IMessage
{
	  public TargetOrgaShotEffectMessageToClient(Vec3d i_targetCoordinates)
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
	  public TargetOrgaShotEffectMessageToClient()
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
	      System.err.println("Exception while reading TargetEffectMessageToClient: " + ioe);
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
	    if (!messageIsValid) return;
	    buf.writeDouble(targetCoordinates.x);
	    buf.writeDouble(targetCoordinates.y);
	    buf.writeDouble(targetCoordinates.z);

	    // these methods may also be of use for your code:
	    // for Itemstacks - ByteBufUtils.writeItemStack()
	    // for NBT tags ByteBufUtils.writeTag();
	    // for Strings: ByteBufUtils.writeUTF8String();
//	    System.out.println("TargetEffectMessageToClient:toBytes length=" + buf.readableBytes());  // debugging only
	  }

	  @Override
	  public String toString()
	  {
	    return "TargetEffectMessageToClient[targetCoordinates=" + String.valueOf(targetCoordinates) + "]";
	  }

	  private Vec3d targetCoordinates;
	  private boolean messageIsValid;
	}
