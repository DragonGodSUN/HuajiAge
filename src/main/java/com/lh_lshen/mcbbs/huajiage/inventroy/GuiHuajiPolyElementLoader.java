package com.lh_lshen.mcbbs.huajiage.inventroy;
/*
 * This main code of this part is transformed from the ExampleMod made by TheGreyGhost
 * If you want to learn more about GUI and Containers or other useful information about Minecraft Modding
 * please enter the following site
 * https://github.com/TheGreyGhost/MinecraftByExample
 * */
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.gui.GuiHuajiBlader;
import com.lh_lshen.mcbbs.huajiage.client.gui.GuiHuajiPoly;
import com.lh_lshen.mcbbs.huajiage.common.GuiHandlerRegistry;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityHuajiPolyfurnace;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityHuajiPolyfurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GuiHuajiPolyElementLoader implements IGuiHandler {
	private static final int HUAJI_POLY_FURNANCE =43;
	public static int getGuiID() {return HUAJI_POLY_FURNANCE;}

	// Gets the server side element for the given gui id this should return a container
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID != getGuiID()) {
			System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
		}

		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if (tileEntity instanceof TileEntityHuajiPolyfurnace) {
			TileEntityHuajiPolyfurnace tileEntityHuajiBlader = (TileEntityHuajiPolyfurnace) tileEntity;
			return new ContainerHuajiPolyfurnace(player.inventory, tileEntityHuajiBlader);
		}
		return null;
	}

	// Gets the client side element for the given gui id this should return a gui
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID != getGuiID()) {
			System.err.println("Invalid ID: expected " + getGuiID() + ", received " + ID);
		}

		BlockPos xyz = new BlockPos(x, y, z);
		TileEntity tileEntity = world.getTileEntity(xyz);
		if (tileEntity instanceof TileEntityHuajiPolyfurnace) {
			TileEntityHuajiPolyfurnace tileEntityHuajiBlader = (TileEntityHuajiPolyfurnace) tileEntity;
			return new GuiHuajiPoly(player.inventory, tileEntityHuajiBlader);
		}
		return null;
	}

}