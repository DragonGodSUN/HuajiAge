package com.lh_lshen.mcbbs.huajiage.inventroy;

import java.awt.List;
import java.util.ArrayList;

import com.lh_lshen.mcbbs.huajiage.inventroy.slot.SlotHuajiPolyFuel;
import com.lh_lshen.mcbbs.huajiage.inventroy.slot.SlotHuajiPolyOutput;
import com.lh_lshen.mcbbs.huajiage.network.HuajiAgeNetWorkHandler;
import com.lh_lshen.mcbbs.huajiage.network.messages.PacketGuiShortOverride;
import com.lh_lshen.mcbbs.huajiage.recipelist.HuajiPolyRecipeList;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityHuajiPolyfurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ContainerHuajiPolyfurnace extends Container {

	private final TileEntityHuajiPolyfurnace  tilientity;
	private int burnTime,currentBurnTime,cookTime,totalCookTime,ItemPool;
	private int[] tileMap= {ItemPool,currentBurnTime,cookTime,totalCookTime};
	
	public ContainerHuajiPolyfurnace(InventoryPlayer player,TileEntityHuajiPolyfurnace tilientity) {
		
		this.tilientity = tilientity;
		addSlotToContainer(new Slot(tilientity, 0, 44, 25));
		addSlotToContainer(new SlotHuajiPolyFuel(tilientity, 1, 44, 48));
		addSlotToContainer(new SlotHuajiPolyOutput(player, tilientity, 2, 151, 25));
		final int SLOT_X_SPACING = 18;
		final int SLOT_Y_SPACING = 18;
		
		final int HOTBAR_XPOS = 16;
		final int HOTBAR_YPOS = 129;
		for(int x=0;x<9;x++) {
			int slotNumber = x;
			addSlotToContainer(new Slot(player, slotNumber, HOTBAR_XPOS + SLOT_X_SPACING * x, HOTBAR_YPOS));
		}
		final int PLAYER_INVENTORY_XPOS = 16;
		final int PLAYER_INVENTORY_YPOS = 71;
		for(int y=0;y<3;y++) {
			for(int x=0;x<9;x++) {
				int xpos = PLAYER_INVENTORY_XPOS + x * SLOT_X_SPACING;
				int ypos = PLAYER_INVENTORY_YPOS + y * SLOT_Y_SPACING;
				this.addSlotToContainer(new Slot(player, x+(y*9)+9,xpos,ypos));
			}
		}
		
	}

@Override
public void addListener(IContainerListener listener) {
	super.addListener(listener);
	listener.sendAllWindowProperties(this, this.tilientity);
}
@Override
public void detectAndSendChanges() {
	super.detectAndSendChanges();
	if (tilientity != null) {
	for(int i=0;i<this.listeners.size();i++) {
		IContainerListener listener=(IContainerListener)this.listeners.get(i);
		for(int j=0;j<tileMap.length;j++) {
			int p=j+1;
		 if (this.tileMap[j] != this.tilientity.getField(p)) {
	          if ((this.tilientity.getField(p) > Short.MAX_VALUE ||
	              this.tilientity.getField(p) < Short.MIN_VALUE)
	              && listener instanceof EntityPlayerMP) {
	            //minecraft truncates int into short
	           HuajiAgeNetWorkHandler.sendTo(
	                ((EntityPlayerMP) listener), new PacketGuiShortOverride(p, this.tilientity.getField(p)));
	          }
	          else {
	        	  listener.sendWindowProperty(this, p, this.tilientity.getField(p));
	          }
	        }
		 }
	}
	
		for(int j=0;j<tileMap.length;j++) {
			tileMap[j]=this.tilientity.getField(j+1);
		}
	}
	 	
}
	@SideOnly(Side.CLIENT)
	@Override
	public void updateProgressBar(int id, int data) {
		this.tilientity.setField(id, data);
//		 switch (id)
//	        {
//	        case 1:
//	        	this.ItemPool = data;
//	        	break;
//	        case 2:
//	            this.currentBurnTime = data;
//	            break;
//	        case 3:
//	            this.cookTime = data;
//	            break;
//	        case 4:
//	            this.totalCookTime = data;
//	            break;
//	        default:
//	            break;
//	        }
	}
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return this.tilientity.isUsableByPlayer(playerIn);
	}
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
//		ItemStack stack = ItemStack.EMPTY;
//		Slot slot=(Slot)this.inventorySlots.get(index);
//		
//		if(slot !=null&&slot.getHasStack()) {
//			ItemStack stack1=slot.getStack();
//			stack=stack1.copy();
//			if(index==2) {
//				if(!this.mergeItemStack(stack1, 3, 39, true))return ItemStack.EMPTY;
//				slot.onSlotChange(stack1, stack);
//			}
//			else if(index !=0&&index!=1&&index!=2) {
//				slot =(Slot)this.inventorySlots.get(index+1);
//				if(!HuajiRecipeList.instance().getSmeltingResult(stack1).isEmpty()) {
//					if(!this.mergeItemStack(stack1, 0, 1, false)) {
//						return ItemStack.EMPTY;
//					}else if(TileEntityHuajiPolyfurnace.getItemBurnTime(stack1)>0) {
//						if(!this.mergeItemStack(stack1, 1, 2, false))return ItemStack.EMPTY;
//					}else if(index>=3&&index<30) {
//						if(!this.mergeItemStack(stack1, 30, 39, false))return ItemStack.EMPTY;
//					}else if(index>=30&&index<40&&!this.mergeItemStack(stack1, 3, 30, false)) {
//						return ItemStack.EMPTY;
//					}
//				}
//			}
//			else if(!this.mergeItemStack(stack1, 3, 39, false)) {
//				return ItemStack.EMPTY;
//			}
//			if(stack1.isEmpty()) {
//				slot.putStack(ItemStack.EMPTY);
//			}else {
//				slot.onSlotChanged();
//			}
//			if(stack1.getCount()==stack.getCount()) {
//				return ItemStack.EMPTY;
//			}
//			slot.onTake(playerIn, stack1);
//		}
//		return stack;
		 ItemStack itemstack = ItemStack.EMPTY;
	        Slot slot = this.inventorySlots.get(index);

	        if (slot != null && slot.getHasStack())
	        {
	            ItemStack itemstack1 = slot.getStack();
	            itemstack = itemstack1.copy();

	            if (index == 2)
	            {
	                if (!this.mergeItemStack(itemstack1, 3, 39, true))
	                {
	                    return ItemStack.EMPTY;
	                }

	                slot.onSlotChange(itemstack1, itemstack);
	            }
	            else if (index != 1 && index != 0)
	            {
	                if (!HuajiPolyRecipeList.instance().getSmeltingResult(itemstack1).isEmpty())
	                {
	                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
	                    {
	                        return ItemStack.EMPTY;
	                    }
	                }
	                else if (TileEntityHuajiPolyfurnace.getItemBurnTime(itemstack1)>0)
	                {
	                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
	                    {
	                        return ItemStack.EMPTY;
	                    }
	                }
	                else if (index >= 3 && index < 30)
	                {
	                    if (!this.mergeItemStack(itemstack1, 30, 39, false))
	                    {
	                        return ItemStack.EMPTY;
	                    }
	                }
	                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false))
	                {
	                    return ItemStack.EMPTY;
	                }
	            }
	            else if (!this.mergeItemStack(itemstack1, 3, 39, false))
	            {
	                return ItemStack.EMPTY;
	            }

	            if (itemstack1.isEmpty())
	            {
	                slot.putStack(ItemStack.EMPTY);
	            }
	            else
	            {
	                slot.onSlotChanged();
	            }

	            if (itemstack1.getCount() == itemstack.getCount())
	            {
	                return ItemStack.EMPTY;
	            }

	            slot.onTake(playerIn, itemstack1);
	        }

	        return itemstack;
	    }

}
