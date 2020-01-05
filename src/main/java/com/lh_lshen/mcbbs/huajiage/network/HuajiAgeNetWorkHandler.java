package com.lh_lshen.mcbbs.huajiage.network;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageDioHitClient;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageExglutenburMode;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageHelmetModeChange;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageLeftClickModeChange;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageLeftClickRoadRoller;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageMovingSound;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessagePlaySoundClient;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessagePlaySoundToServer;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageServerInterchange;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageStandUp;
import com.lh_lshen.mcbbs.huajiage.network.messages.SyncStandMessage;
import com.lh_lshen.mcbbs.huajiage.network.messages.SyncStandSkillMessage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public final class  HuajiAgeNetWorkHandler {

	public static final SimpleNetworkWrapper HANDLER = new SimpleNetworkWrapper(HuajiAge.MODID);

    public static void init() {
		int id = 0;
		HANDLER.registerMessage(MessageServerInterchange.Handler.class, MessageServerInterchange.class, id++, Side.SERVER);
		HANDLER.registerMessage(MessagePlaySoundToServer.Handler.class, MessagePlaySoundToServer.class, id++, Side.SERVER);
		HANDLER.registerMessage(MessageLeftClickModeChange.Handler.class, MessageLeftClickModeChange.class, id++, Side.SERVER);
		HANDLER.registerMessage(MessageHelmetModeChange.Handler.class, MessageHelmetModeChange.class, id++, Side.SERVER);
		HANDLER.registerMessage(MessageStandUp.Handler.class, MessageStandUp.class, id++, Side.SERVER);
		HANDLER.registerMessage(MessageLeftClickRoadRoller.Handler.class, MessageLeftClickRoadRoller.class, id++, Side.SERVER);
		HANDLER.registerMessage(MessageExglutenburMode.Handler.class, MessageExglutenburMode.class, id++, Side.SERVER);
	    
		
	    HANDLER.registerMessage(MessageDioHitClient.Handler.class,MessageDioHitClient.class, id++, Side.CLIENT);
	    HANDLER.registerMessage(MessagePlaySoundClient.Handler.class,MessagePlaySoundClient.class, id++, Side.CLIENT);
	    HANDLER.registerMessage(MessageParticleGenerator.Handler.class,MessageParticleGenerator.class, id++, Side.CLIENT);
	    HANDLER.registerMessage(MessageMovingSound.Handler.class,MessageMovingSound.class, id++, Side.CLIENT);
	    HANDLER.registerMessage(SyncStandMessage.Handler.class,SyncStandMessage.class, id++, Side.CLIENT);
	    HANDLER.registerMessage(SyncStandSkillMessage.Handler.class,SyncStandSkillMessage.class, id++, Side.CLIENT);
	}
    /**
	 * Send message to all within 64 blocks that have this chunk loaded
	 */
	public static void sendToNearby(World world, BlockPos pos, IMessage toSend) {
		if(world instanceof WorldServer) {
			WorldServer ws = (WorldServer) world;

			for (EntityPlayer player : ws.playerEntities) {
				EntityPlayerMP playerMP = (EntityPlayerMP) player;

				if (playerMP.getDistanceSq(pos) < 64 * 64
						&& ws.getPlayerChunkMap().isPlayerWatchingChunk(playerMP, pos.getX() >> 4, pos.getZ() >> 4)) {
					HANDLER.sendTo(toSend, playerMP);
				}
			}

		}
	}

	public static void sendToNearby(World world, Entity e, IMessage toSend) {
		sendToNearby(world, new BlockPos(e), toSend);
	}

	public static void sendTo(EntityPlayerMP playerMP, IMessage toSend) {
		HANDLER.sendTo(toSend, playerMP);
	}

	public static void sendToServer(IMessage msg) {
		HANDLER.sendToServer(msg);
	}

	private HuajiAgeNetWorkHandler() {}

}

