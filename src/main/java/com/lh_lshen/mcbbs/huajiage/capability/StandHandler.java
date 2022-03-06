package com.lh_lshen.mcbbs.huajiage.capability;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.Callable;

public class StandHandler {
 	static StandHandler.Factory FACTORY = new StandHandler.Factory();
	private int charge = 0;
	private int max = 5000*20;
	private int buffer = 0;
	public String buffTag = "empty";
	public Map<String,Float> recorder = Maps.newHashMap();
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

	public void setBuffTag(String buffTag) {
		this.buffTag = buffTag;
		markDirty();
	}

	public void setRecorder(Map<String,Float> recorder) {
		this.recorder = recorder;
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

	public String getBuffTag() {
		return buffTag;
	}

	public Map<String, Float> getRecorder() {
		return recorder;
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
	    
	public void zero() {
			setChargeValue(0);
}
	    
	public void cost(int cost) {
		int orgin = charge;
		int left = orgin - cost;
		if( left>0 ) {
			setChargeValue(left);
			}else {
				zero();
			}
		}

	public void addTarget(String uuid, float value){
		this.recorder.put(uuid,value);
		markDirty();
	}

	public void removeTarget(String uuid){
		this.recorder.remove(uuid);
		markDirty();
	}

	public void addTargetValue(String uuid, float value_ex){
		if (recorder.containsKey(uuid)) {
			float value = this.recorder.get(uuid);
			this.recorder.put(uuid,value + value_ex);
		}else {
			addTarget(uuid,value_ex);
		}
		markDirty();
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
