package com.lh_lshen.mcbbs.huajiage.tileentity;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityLoader {
	 public TileEntityLoader(FMLPreInitializationEvent event)
	    {
	        registerTileEntity(TileEntityHuajiBlader.class, "HuajiBlader");
	        registerTileEntity(TileEntityHuajiPolyfurnace.class, "HuajiPolyfurnace");
	    }

	    public void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id)
	    {
	        GameRegistry.registerTileEntity(tileEntityClass, HuajiAge.MODID + ":" + id);
	    }
}
