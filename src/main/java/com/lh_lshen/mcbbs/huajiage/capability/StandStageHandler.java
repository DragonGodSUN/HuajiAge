package com.lh_lshen.mcbbs.huajiage.capability;

import java.util.concurrent.Callable;

import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;

public class StandStageHandler {
	 static StandStageHandler.Factory FACTORY = new StandStageHandler.Factory();
	    private int stage = 0;
	    private boolean dirty;
	    
	    public void setStage(int stage) {
	    	this.stage = stage;
	        markDirty();
	    }

	    public int getStage() {
			return stage;
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

	    private static class Factory implements Callable<StandStageHandler> {
	        @Override
	        public StandStageHandler call() {
	            return new StandStageHandler();
	        }
	    }
}
