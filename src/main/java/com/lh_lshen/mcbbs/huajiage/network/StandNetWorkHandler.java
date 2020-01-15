package com.lh_lshen.mcbbs.huajiage.network;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageExglutenburMode;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageHelmetModeChange;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageLeftClickModeChange;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageMovingSound;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageParticleGenerator;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessagePlaySoundClient;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessagePlaySoundToServer;
import com.lh_lshen.mcbbs.huajiage.network.messages.MessageServerInterchange;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDioHitClient;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDoTimeStopClient;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDoTimeStopServer;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageLeftClickRoadRoller;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessagePerfromSkill;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageStandUp;
import com.lh_lshen.mcbbs.huajiage.stand.messages.SyncExposedStandDataMessage;
import com.lh_lshen.mcbbs.huajiage.stand.messages.SyncStandChargeMessage;
import com.lh_lshen.mcbbs.huajiage.stand.messages.SyncStandMessage;
import com.lh_lshen.mcbbs.huajiage.stand.messages.SyncStandStageMessage;
import com.lh_lshen.mcbbs.huajiage.stand.messages.SyncStandUserMessage;

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

public final class  StandNetWorkHandler {

	public static final SimpleNetworkWrapper HANDLER = new SimpleNetworkWrapper(HuajiAge.MODID+"STAND");

    public static void init() {
		int id = 0;
		HANDLER.registerMessage(MessageStandUp.Handler.class, MessageStandUp.class, id++, Side.SERVER);
		HANDLER.registerMessage(MessageLeftClickRoadRoller.Handler.class, MessageLeftClickRoadRoller.class, id++, Side.SERVER);
		HANDLER.registerMessage(MessagePerfromSkill.Handler.class, MessagePerfromSkill.class, id++, Side.SERVER);
		HANDLER.registerMessage(MessageDoTimeStopServer.Handler.class, MessageDoTimeStopServer.class, id++, Side.SERVER);
		
	    HANDLER.registerMessage(MessageDioHitClient.Handler.class,MessageDioHitClient.class, id++, Side.CLIENT);
	    HANDLER.registerMessage(MessageDoTimeStopClient.Handler.class,MessageDoTimeStopClient.class, id++, Side.CLIENT);
	    HANDLER.registerMessage(SyncStandMessage.Handler.class,SyncStandMessage.class, id++, Side.CLIENT);
	    HANDLER.registerMessage(SyncStandStageMessage.Handler.class,SyncStandStageMessage.class, id++, Side.CLIENT);
	    HANDLER.registerMessage(SyncStandChargeMessage.Handler.class,SyncStandChargeMessage.class, id++, Side.CLIENT);
	    HANDLER.registerMessage(SyncExposedStandDataMessage.Handler.class,SyncExposedStandDataMessage.class, id++, Side.CLIENT);
	    HANDLER.registerMessage(SyncStandUserMessage.Handler.class,SyncStandUserMessage.class, id++, Side.CLIENT);
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

	private StandNetWorkHandler() {}

}

