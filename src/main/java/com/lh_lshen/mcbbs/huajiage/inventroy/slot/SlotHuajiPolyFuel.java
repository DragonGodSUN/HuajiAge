package com.lh_lshen.mcbbs.huajiage.inventroy.slot;

import com.lh_lshen.mcbbs.huajiage.block.HuajiPolyfurnace;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityHuajiPolyfurnace;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotHuajiPolyFuel extends Slot {

	public SlotHuajiPolyFuel(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntityHuajiPolyfurnace.getItemBurnTime(stack)>0;
	}
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return super.getItemStackLimit(stack);
	}
}
