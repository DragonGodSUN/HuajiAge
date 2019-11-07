package com.lh_lshen.mcbbs.huajiage.tileentity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.lh_lshen.mcbbs.huajiage.capability.EnergyStore;

import net.minecraft.client.renderer.EnumFaceDirection.Constants;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
/**
 * This class is transformed from @Lothrazar's Cyclic
 * https://github.com/Lothrazar/Cyclic/blob/develop/src/main/java/com/lothrazar/cyclicmagic/block/core/TileEntityBaseMachine.java
 * Follow The MIT License
 * @return
 */
public class TileEntityMachineEnergy  extends TileEntityMachineBase implements IInventory {

	  protected static final int SPEED_FUELED = 8;
	  private static final int MAX_SPEED = 10;
	  public static final int MENERGY = 5*1000 * 1000;
	  public static final String NBT_TIMER = "Timer";
	  public static final String NBT_REDST = "redstone";
	  public static final String NBT_SIZE = "size";
	  public static final String NBTPLAYERID = "uuid";
	  public static final String NBT_SPEED = "speed";
	  private static final String NBT_ENERGY = "ENERGY";
	  public static final String NBT_UHASH = "uhash";
	  public static final String NBT_UNAME = "uname";
	  protected NonNullList<ItemStack> inv;
	  private int energyCost = 0;
	  /**
	   * speed > 0
	   */
	  protected int speed = 1;
	  protected int timer;
	  protected int renderParticles = 0;
	  protected int needsRedstone = 0;

	  protected EnergyStore energyStorage;
	  private boolean setRenderGlobally;
	  private boolean hasEnergy;

	  public TileEntityMachineEnergy() {
	    super();
	  }


	  protected void initEnergy(EnergyStore store, int energyCost) {
	    this.energyStorage = store;
	    this.hasEnergy = true;
	    this.setEnergyCost(energyCost);
	  }

	  public int getEnergyMax() {
	    if (energyStorage == null) {
	      return 0;
	    }
	    return this.energyStorage.getMaxEnergyStored();
	  }

	  public int getEnergyCurrent() {
	    if (this.energyStorage == null) {
	      return 0;
	    }
	    return this.energyStorage.getEnergyStored();
	  }

	  public void setEnergyCurrent(int f) {
	    this.energyStorage.setEnergyStored(f);
	  }

	  public int getEnergyCost() {
	    return this.energyCost;
	  }

	  public void consumeEnergy() {
	    //only drain on server, if we have enough and if not free
	    if (!world.isRemote && this.getEnergyCost() > 0 &&
	        this.getEnergyCurrent() >= this.getEnergyCost()) {
	      this.energyStorage.extractEnergy(this.getEnergyCost(), false);
	      //it drained, notify client 
	      this.markDirty();
	    }
	  }

	  public int[] getFieldArray(int length) {
	    return IntStream.rangeClosed(0, length - 1).toArray();
	  }

	  public boolean isDoingWork() {
	    return super.isRunning() && this.hasEnoughEnergy();
	  }

	  public boolean updateEnergyIsBurning() {
	    if (this.getEnergyCost() > 0) {
	      if (this.hasEnoughEnergy()) {
	        this.consumeEnergy();
	      }
	      else {
	        // dont run, dont count down, just stop now 
	        return false;
	      }
	    }
	    return true;
	  }

	  /**
	   * much energy code helped out and referenced and inspired by @Ellpeck and then I tweaked it to fit my needs
	   * https://github.com/Ellpeck/ActuallyAdditions/blob/9bed6f7ea59e8aa23fa3ba540d92cd61a04dfb2f/src/main/java/de/ellpeck/actuallyadditions/mod/util/WorldUtil.java#L151
	   */
	  public boolean hasEnoughEnergy() {
	    if (this.getEnergyCost() == 0) {
	      return true;
	    }
	    return this.getEnergyCurrent() >= this.getEnergyCost();
	  }

	  protected boolean updateTimerIsZero() {
	    timer -= this.getSpeed();
	    if (timer < 0) {
	      timer = 0;
	    }
	    return timer == 0;
	  }


	  @Override
	  public int getFieldCount() {
	    return 0;
	  }

	  @Override
	  public boolean hasCustomName() {
	    return false;
	  }

	  @Override
	  public ITextComponent getDisplayName() {
	    return null;
	  }

	  @Override
	  public int getInventoryStackLimit() {
	    return 64;
	  }

	  @Override
	  public void openInventory(EntityPlayer player) {}

	  @Override
	  public void closeInventory(EntityPlayer player) {}

	  @Override
	  public boolean isEmpty() {
	    return false;
	  }

	  @Override
	  public boolean isUsableByPlayer(EntityPlayer player) {
	    return true;
	  }


	  @Override
	  public void readFromNBT(NBTTagCompound compound) {
	    speed = compound.getInteger(NBT_SPEED);
	    needsRedstone = compound.getInteger(NBT_REDST);
	    timer = compound.getInteger(NBT_TIMER);
	    if (this.hasEnergy && compound.hasKey(NBT_ENERGY)) {
	      CapabilityEnergy.ENERGY.readNBT(energyStorage, null, compound.getTag(NBT_ENERGY));
	    }
	    super.readFromNBT(compound);
	  }

	  @Override
	  public NBTTagCompound writeToNBT(NBTTagCompound compound) {
	    compound.setInteger(NBT_SPEED, speed);
	    compound.setInteger(NBT_TIMER, timer);
	    compound.setInteger(NBT_REDST, needsRedstone);
	    if (hasEnergy && energyStorage != null) {
	      compound.setTag(NBT_ENERGY, CapabilityEnergy.ENERGY.writeNBT(energyStorage, null));
	    }
	    return super.writeToNBT(compound);
	  }


	  @Override
	  public boolean receiveClientEvent(int id, int value) {
	    if (id >= 0 && id < this.getFieldCount()) {
	      this.setField(id, value);
	      return true;
	    }
	    else {
	      return super.receiveClientEvent(id, value);
	    }
	  }


	  public int[] getFieldOrdinals() {
	    return new int[0];
	  }

	  public int getSpeed() {
	    if (this.getEnergyCost() == 0) {
	      return this.speed;// does not use fuel. use NBT saved speed value
	    }
	    else {
	      if (this.getEnergyCurrent() == 0) {
	        return 0; // do not run without fuel
	      }
	      else {
	        return Math.max(this.speed, 1);// i have fuel, use what it says eh
	      }
	    }
	  }

	  public void setSpeed(int value) {
	    if (value < 0) {
	      value = 0;
	    }
	    speed = Math.min(value, MAX_SPEED);
	  }

	  @Override
	  public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability, EnumFacing facing) {
	    if (capability == CapabilityEnergy.ENERGY
	        && this.energyStorage != null) {
	      return true;
	    }
	    return super.hasCapability(capability, facing);
	  }

	  @SuppressWarnings("unchecked")
	  @Override
	  public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing) {
	    if (this.hasEnergy && capability == CapabilityEnergy.ENERGY) {
	      return CapabilityEnergy.ENERGY.cast(energyStorage);
	    }
	    return super.getCapability(capability, facing);
	  }


	  /**
	   * https://shadowfacts.net/tutorials/forge-modding-1112/dynamic-tileentity-rendering/
	   */

	  public void setEnergyCost(int energyCost) {
	    this.energyCost = energyCost;
	  }

	  @Override
	  public boolean isRunning() {
	    this.getEnergyCost();
	    this.hasEnoughEnergy();
	    return super.isRunning();
	  }

	@Override
	public void clear() {
	
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public ItemStack getStackInSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ItemStack decrStackSize(int index, int count) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ItemStack removeStackFromSlot(int index) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		// TODO Auto-generated method stub
		return super.isValid();
	}


	@Override
	public int getField(int id) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setField(int id, int value) {
		// TODO Auto-generated method stub
		
	}
	}
