package com.lh_lshen.mcbbs.huajiage.network.messages;

import com.lh_lshen.mcbbs.huajiage.entity.EntityFivePower;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.audio.Sound;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
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
    @Override
    public void toBytes(ByteBuf buf) {

    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<MessageFiveBulletShoot, IMessage> {
        @Override
        public IMessage onMessage(MessageFiveBulletShoot message , MessageContext ctx) {
        	if (ctx.side == Side.SERVER) {
    	EntityPlayerMP player = ctx.getServerHandler().player;
    	World world = player.getServerWorld();
    	Vec3d face = player.getLookVec();
    	FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> {
		EntityFivePower bullet = new EntityFivePower(world, player);
		bullet.setPosition(player.posX, player.posY+player.eyeHeight-0.2f, player.posZ);
		bullet.shoot(player, player.rotationPitch, player.prevRotationYawHead, 0.5f, 1.0f, 1.0f);
		world.spawnEntity(bullet);
		player.connection.sendPacket(new SPacketSoundEffect(SoundEvents.ENTITY_BLAZE_SHOOT, 
				SoundCategory.PLAYERS, player.posX, player.getEyeHeight()+player.posY, player.posZ, 1f, 1f));
            		});
    		}
			return null;
        }
    }
}