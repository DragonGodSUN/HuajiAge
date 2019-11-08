package com.lh_lshen.mcbbs.huajiage.data;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.wrapper.InvWrapper;
/**
 *  This class is transformed from @Lothrazar's Cyclic
 * https://github.com/Lothrazar/Cyclic/blob/develop/src/main/java/com/lothrazar/cyclicmagic/block/core/TileEntityBaseMachine.java
 * Follow The MIT License
 * @return
 */
public class InvWrapperRestricted extends InvWrapper {

	  private List<Integer> slotsAllowedInsert;
	  private List<Integer> slotsAllowedExtract;

	  public InvWrapperRestricted(ISidedInventory inv) {
	    super(inv);
	    slotsAllowedInsert = new ArrayList<Integer>();
	    slotsAllowedExtract = new ArrayList<Integer>();
	  }

	  public List<Integer> getSlotsExtract() {
	    return slotsAllowedExtract;
	  }

	  public void setSlotsExtract(List<Integer> slotsExport) {
	    this.slotsAllowedExtract = slotsExport;
	  }

	  public List<Integer> getSlotsInsert() {
	    return slotsAllowedInsert;
	  }

	  public void setSlotsInsert(List<Integer> slotsImport) {
	    this.slotsAllowedInsert = slotsImport;
	  }

	  public boolean canInsert(int slot) {
	    return this.getSlotsInsert().contains(slot);
	  }

	  public boolean canExtract(int slot) {
	    return this.getSlotsExtract().contains(slot);
	  }

	  @Override
	  @Nonnull
	  public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
	    if (canInsert(slot) == false) {
	      return stack;
	    }
	    return super.insertItem(slot, stack, simulate);
	  }

	  @Override
	  @Nonnull
	  public ItemStack extractItem(int slot, int amount, boolean simulate) {
	    if (canExtract(slot) == false) {
	      return ItemStack.EMPTY;
	    }
	    return super.extractItem(slot, amount, simulate);
	  }
	}
