package com.lh_lshen.mcbbs.huajiage.capability;

import java.util.concurrent.Callable;

import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;

public class StandHandler {
	 static StandHandler.Factory FACTORY = new StandHandler.Factory();
	    private String stand = EnumStandtype.EMPTY;
	    private boolean dirty;
	    
	    public void setStand(String stand) {
	    	this.stand = stand;
	        markDirty();
	    }

	    public String getStand() {
	        return this.stand;
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

	    private static class Factory implements Callable<StandHandler> {
	        @Override
	        public StandHandler call() {
	            return new StandHandler();
	        }
	    }
}
