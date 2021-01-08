package com.lh_lshen.mcbbs.huajiage.network.messages;

import com.lh_lshen.mcbbs.huajiage.entity.EntityFivePower;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageFiveBulletShoot implements IMessage {
	public MessageFiveBulletShoot() {
		// TODO Auto-generated constructor stub
	}
//	public MessageFiveBulletShoot(String uuid) {
//		this.uuid=uuid;
//	}
	
    @Override
    public void toBytes(ByteBuf buf) {
//    	ByteBufUtils.writeUTF8String(buf, uuid);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
//    	this.uuid = ByteBufUtils.readUTF8String(buf);
    }
//    private String getUUID() {
//		return uuid;
//	}
//    private String getName() {
//  		return uuid;
//  	}
//    private String uuid;
//    private String name;
    public static class Handler implements IMessageHandler<MessageFiveBulletShoot, IMessage> {
        @Override
        public IMessage onMessage(MessageFiveBulletShoot message , MessageContext ctx) {
        	if (ctx.side == Side.SERVER) {
    	EntityPlayerMP player = ctx.getServerHandler().player;
    	World world = player.getServerWorld();
    	Vec3d face = player.getLookVec();
    	FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
//    	EntityPlayer player_u = world.getPlayerEntityByUUID(UUID.fromString(message.getUUID()));
    	if(player!=null) {
		EntityFivePower bullet = new EntityFivePower(world, player);
		boolean de = Math.random()>0.5;
		bullet.setMaster(player.getUniqueID().toString());
		bullet.setDe(de);
		bullet.setPosition(player.posX, player.posY+player.eyeHeight-0.2f, player.posZ);
		bullet.shoot(player, player.rotationPitch, player.prevRotationYawHead, 0.5f, 1.0f, 1.0f);
		world.spawnEntity(bullet);
		player.connection.sendPacket(new SPacketSoundEffect(SoundEvents.ENTITY_BLAZE_SHOOT, 
				SoundCategory.PLAYERS, player.posX, player.getEyeHeight()+player.posY, player.posZ, 1f, 1f));
				    	}
        		});
    	}
			return null;
        }
    }
}