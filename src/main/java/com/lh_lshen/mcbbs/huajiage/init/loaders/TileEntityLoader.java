package com.lh_lshen.mcbbs.huajiage.init.loaders;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;

import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityHuajiBlender;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityHuajiPolyfurnace;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityNewLandMan;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityLoader {
	 public TileEntityLoader(FMLPreInitializationEvent event)
	    {
	        registerTileEntity(TileEntityHuajiBlender.class, "HuajiBlader");
	        registerTileEntity(TileEntityHuajiPolyfurnace.class, "HuajiPolyfurnace");
	        registerTileEntity(TileEntityNewLandMan.class, "NewLandMan");
	    }

	    public void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id)
	    {
	        GameRegistry.registerTileEntity(tileEntityClass, HuajiAge.MODID + ":" + id);
	    }
}
