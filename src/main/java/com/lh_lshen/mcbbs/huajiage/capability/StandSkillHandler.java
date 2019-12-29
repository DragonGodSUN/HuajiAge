package com.lh_lshen.mcbbs.huajiage.capability;

import java.util.concurrent.Callable;

import com.lh_lshen.mcbbs.huajiage.util.EnumStandtype;

import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;

public class StandSkillHandler {
	 static StandSkillHandler.Factory FACTORY = new StandSkillHandler.Factory();
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

	    private static class Factory implements Callable<StandSkillHandler> {
	        @Override
	        public StandSkillHandler call() {
	            return new StandSkillHandler();
	        }
	    }
}
