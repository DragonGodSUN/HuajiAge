package com.lh_lshen.mcbbs.huajiage.client.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.render.tesr.TileEntityNewLandManRenderer;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityNewLandMan;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = HuajiAge.MODID, value = Side.CLIENT)
public final class HAModelRegistryEvent {
	
	 @SubscribeEvent
	    public static void register(ModelRegistryEvent event) {
		 ClientRegistry.bindTileEntitySpecialRenderer(TileEntityNewLandMan.class, new TileEntityNewLandManRenderer());
 	     }

}
