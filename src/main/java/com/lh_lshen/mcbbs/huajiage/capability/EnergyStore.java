package com.lh_lshen.mcbbs.huajiage.capability;

import net.minecraftforge.energy.EnergyStorage;
/**
 * This class's author is @Lothrazar
 * https://github.com/Lothrazar/Cyclic/blob/develop/src/main/java/com/lothrazar/cyclicmagic/block/core/TileEntityBaseMachine.java
 * Follow The MIT License
 * @return
 */
public class EnergyStore extends EnergyStorage{
	  public EnergyStore(int capAndIO) {
		    this(capAndIO, capAndIO, capAndIO);
		  }

		  public EnergyStore(int capacity, int maxReceive, int maxExtract) {
		    super(capacity, maxReceive, maxExtract, 0);//current empty
		  }

		  public void setEnergyStored(int en) {
		    if (en < 0) {
		      en = 0;
		    }
		    this.energy = Math.min(en, this.capacity);
		  }

		  public int emptyCapacity() {
		    return this.capacity - this.energy;
		  }

		  @Override
		  public int receiveEnergy(int maxReceive, boolean simulate) {
		    if (!canReceive() ||
		        getMaxEnergyStored() <= getEnergyStored()) {
		      return 0;
		    }
		    int energyReceived = Math.min(getMaxEnergyStored() - getEnergyStored(), Math.min(this.maxReceive, maxReceive));
		    if (!simulate) {
		      int newEnergy = energyReceived + getEnergyStored();
		      setEnergyStored(Math.min(getMaxEnergyStored(), newEnergy));
		    }
		    return energyReceived;
		  }

		  @Override
		  public int extractEnergy(int maxExtract, boolean simulate) {
		    if (!canExtract()) {
		      return 0;
		    }
		    int energyExtracted = Math.min(getEnergyStored(), Math.min(this.maxExtract, maxExtract));
		    if (!simulate) {
		      setEnergyStored(getEnergyStored() - energyExtracted);
		    }
		    return energyExtracted;
		  }
}
