package com.lh_lshen.mcbbs.huajiage.inventroy.slot;

import com.lh_lshen.mcbbs.huajiage.block.HuajiPolyfurnace;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityHuajiPolyfurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotHuajiPolyOutput extends Slot {

	private InventoryPlayer player;
	private int removeCount;
	public SlotHuajiPolyOutput(InventoryPlayer player,IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
		this.player=player;
	}

	@Override
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return super.getItemStackLimit(stack);
	}
	@Override
	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack) {
		this.onCrafting(stack);
		super.onTake(thePlayer, stack);
		return stack;
	}
	@Override
	public ItemStack decrStackSize(int amount) {
		if(this.getHasStack()) {
			this.removeCount+=Math.min(amount,this.getStack().getCount());
		}
        return super.decrStackSize(amount);
	}
}
