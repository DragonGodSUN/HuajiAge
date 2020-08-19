package com.lh_lshen.mcbbs.huajiage.capability;

import java.util.concurrent.Callable;

public class StandChargeHandler  {
 	static StandChargeHandler.Factory FACTORY = new StandChargeHandler.Factory();
	private int charge = 0;
	private int max = 5000*20;
	private int buffer = 0;
	private boolean dirty;

	public void setChargeValue(int value) {
		this.charge = value;
		markDirty();
	}

	public void setMaxValue(int value) {
		this.max = value;
		markDirty();
	}

	public void setBuffer(int buffer) {
		this.buffer = buffer;
		markDirty();
	}

	public int getChargeValue() {
		return charge;
	}

	public int getMaxValue() {
		return max;
	}

	public int getBuffer() {
		return buffer;
	}

	public boolean canBeCharge() {
			return this.charge<max;
		}
	    
	public boolean canBeCost(int cost) {
			return this.charge-cost>=0;
		}

	public boolean toBeContinue() {
		return this.buffer>0;
	}

	public void bufferDecreace() {
		if(toBeContinue()) {
			buffer--;
			markDirty();
		}
	}
	    
	public void charge(int efficiency) {
		int result = charge +efficiency;
		if(canBeCharge()) {
			if(result<=max) {
			this.charge += efficiency;
			}else {
				setChargeValue(max);
			}
		}else {
			setChargeValue(max);
		}
		markDirty();
	}
	    
	public void clear() {
			setChargeValue(0);
}
	    
	public void cost(int cost) {
		int orgin = charge;
		int left = orgin - cost;
		if( left>0 ) {
			setChargeValue(left);
			}else {
				clear();
			}
		}
	    
	public void markDirty() {
		dirty = true;
	}

	public boolean isDirty() {
		return dirty;
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}

	private static class Factory implements Callable<StandChargeHandler> {
		@Override
		public StandChargeHandler call() {
			return new StandChargeHandler();
		}
	}
}
