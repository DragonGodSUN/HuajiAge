package com.lh_lshen.mcbbs.huajiage.capability;

public interface IExposedData {
	
//	替身
	void setStand(String standName);
	
	String getStand();
	
	void setTrigger(boolean trigger);
	
	boolean isTriggered();

	boolean isDirty();

	void setDirty(boolean dirty);

}
