package com.lh_lshen.mcbbs.huajiage.client.events;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.Lists;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.render.tesr.TileEntityNewLandManRenderer;
import com.lh_lshen.mcbbs.huajiage.item.ItemDiscStand;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityNewLandMan;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
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
