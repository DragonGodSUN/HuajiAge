package com.lh_lshen.mcbbs.huajiage.capability;

import java.util.concurrent.Callable;

import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;

public class StandChargeHandler {
	 static StandChargeHandler.Factory FACTORY = new StandChargeHandler.Factory();
	    private int charge = 0;
	    private int max = 5000*20;
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
			return this.charge<max;
		}
	    
	    public boolean canBeCost(int cost) {
			return this.charge-cost>0;
		}
	    
	    public void charge(int efficiency) {
	    	int result = charge +efficiency;
	    	if(canBeCharge()) {
	    		if(result<=max) {
				this.charge += efficiency;
				}else {
					setChargeValue(max);
				}
			markDirty();
			}
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
