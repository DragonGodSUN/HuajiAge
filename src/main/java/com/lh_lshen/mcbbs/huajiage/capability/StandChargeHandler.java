package com.lh_lshen.mcbbs.huajiage.capability;

import java.util.concurrent.Callable;

import com.lh_lshen.mcbbs.huajiage.util.EnumStandtype;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;

public class StandChargeHandler {
	 static StandChargeHandler.Factory FACTORY = new StandChargeHandler.Factory();
	    private int charge = 0;
	    private int max = 5000;
	    private boolean dirty;
	    
	    public void setChargeValue(int value) {
	    	this.charge = value;
	        markDirty();
	    }
	    
	    public void setMaxValue(int value) {
	    	this.max = value;
	        markDirty();
	    }

	    public int getChargeValue() {
			return charge;
		}
	    
	    public int getMaxValue() {
			return max;
		}

	    public boolean canBeCharge() {
			return this.charge<=max;
		}
	    
	    public void charge() {
	    	if(canBeCharge()) {
			this.charge++;
			markDirty();
			}
		}
	    
	    public void clear() {
			setChargeValue(0);
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
