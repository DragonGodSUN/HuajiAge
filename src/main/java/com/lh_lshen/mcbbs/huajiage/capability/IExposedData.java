package com.lh_lshen.mcbbs.huajiage.capability;

public interface IExposedData {
	
//	替身
	void setStand(String standName);
	
	String getStand();
	void setStage(int stage);

	int getStage();
	
	void setTrigger(boolean trigger);
	boolean isTriggered();

	void setHandDisplay(boolean display);
	boolean isHandDisplay();

	String getState();
	void setState(String state);

	String getModel();
	void setModel(String model);

	boolean isDirty();
	void setDirty(boolean dirty);

}
