package com.lh_lshen.mcbbs.huajiage.capability;

import java.util.concurrent.Callable;

import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;

public class StandBuffHandler {
	 static StandBuffHandler.Factory FACTORY = new StandBuffHandler.Factory();
	    private int ticks = 0;
	    private boolean dirty;
	    
	    public void setTime(int ticks) {
	    	this.ticks = ticks;
	        markDirty();
	    }

	    public int getTime() {
			return ticks;
		}

		public void markDirty() {
	        dirty = true;
	    }
		
		 public boolean toBeContinue() {
				return this.ticks>0;
		}
		 
		 public void decreace() {
			 if(toBeContinue()) {
				 ticks--;
				 markDirty();
			 }
		}
		
	    public boolean isDirty() {
	        return dirty;
	    }

	    public void setDirty(boolean dirty) {
	        this.dirty = dirty;
	    }

	    private static class Factory implements Callable<StandBuffHandler> {
	        @Override
	        public StandBuffHandler call() {
	            return new StandBuffHandler();
	        }
	    }
}
