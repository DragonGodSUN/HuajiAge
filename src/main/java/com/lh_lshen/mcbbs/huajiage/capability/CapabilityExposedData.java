package com.lh_lshen.mcbbs.huajiage.capability;

import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import java.util.concurrent.Callable;

public class CapabilityExposedData {
	    public static class Storage implements Capability.IStorage<IExposedData>
	    {
	        @Override
	        public NBTBase writeNBT(Capability<IExposedData> capability, IExposedData instance, EnumFacing side)
	        {
	        	
	        	NBTTagCompound cmp_stand = new NBTTagCompound();
	        	
	        	cmp_stand.setString("stand_name", instance.getStand());
	        	cmp_stand.setBoolean("stand_put", instance.isTriggered());
				cmp_stand.setBoolean("stand_hand", instance.isHandDisplay());
	        	cmp_stand.setInteger("stand_stage", instance.getStage());
				cmp_stand.setString("stand_state", instance.getState());
				cmp_stand.setString("stand_model.json", instance.getModel());
	        	
	            return cmp_stand;
	        }

	        @Override
	        public void readNBT(Capability<IExposedData> capability, IExposedData instance, EnumFacing side,
	                NBTBase nbt)
	        {
	        	NBTTagCompound cmp = (NBTTagCompound) nbt;
	        	
	        	String stand = cmp.getString("stand_name");
	        	boolean is_triggered = cmp.getBoolean("stand_put");
				boolean is_hand_display = cmp.getBoolean("stand_hand");
	        	int stage = cmp.getInteger("stand_stage");
	        	String state = cmp.getString("stand_state");
	        	String model = cmp.getString("stand_model.json");
	        	
	        	instance.setStand(stand);
	        	instance.setTrigger(is_triggered);
	        	instance.setHandDisplay(is_hand_display);
	        	instance.setStage(stage);
	        	instance.setState(state);
	        	instance.setModel(model);
	        }
	        
	    }
	    
	    public static class Implementation implements IExposedData{
	    	static Implementation.Factory FACTORY = new Implementation.Factory();
	    	String standName = StandLoader.EMPTY;
	    	boolean isTriggered = false;
	    	boolean isHandDisplay = true;
	    	boolean dirty =false;
	    	int stage = 0;
	    	String state = States.DEFAULT.getName();
	    	String modelID = StandLoader.EMPTY;
	    	
	    	@Override
	    	public void setStand(String standName) {
	    		this.standName = standName;
	    		markDirty();
	    	}
	    	
	    	@Override
	    	public String getStand() {
	    		return this.standName;
	    	}
	    	
	    	@Override
	    	public void setTrigger(boolean trigger) {
	    		this.isTriggered = trigger;
	    		markDirty();
	    	}
	    	@Override
	    	public boolean isTriggered() {
	    		return isTriggered;
	    	}

	    	public void markDirty() {
		        dirty = true;
		    }
	    	@Override
	    	public boolean isDirty() {
	  	        return dirty;
	  	    }
	    	@Override
	  	    public void setDirty(boolean dirty) {
	  	        this.dirty = dirty;
	  	    }

			@Override
			public boolean isHandDisplay() {
				return isHandDisplay;
			}
			@Override
			public void setHandDisplay(boolean handDisplay) {
				isHandDisplay = handDisplay;
				markDirty();
			}

			@Override
			public String getState() {
				return state;
			}

			@Override
			public void setState(String state) {
				this.state = state;
				markDirty();
			}

			@Override
			public String getModel() {
				return modelID;
			}

			@Override
			public void setModel(String model) {
				this.modelID = model;
				markDirty();
			}

			@Override
	    	public void setStage(int stage) {
	    		this.stage = stage;
	    		markDirty();
	    	}
	    	@Override
	    	public int getStage() {
	    		return stage;
	    	}
	    	private static class Factory implements Callable<IExposedData> {
	    		@Override
	    		public Implementation call() {
	    			return new Implementation();
	    		}
	    	}
	    }
	    
	    public static class ProviderPlayer implements ICapabilitySerializable<NBTTagCompound>
	    {
//	    	 private IExposedData instance = CapabilityLoader.EXPOSED_DATA.getDefaultInstance();
	    	 private IExposedData data = new Implementation();
	         private IStorage<IExposedData> storage = CapabilityLoader.EXPOSED_DATA.getStorage();
			@Override
			public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
				 return capability.equals(CapabilityLoader.EXPOSED_DATA);
			}
	
			@Override
			public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
				return hasCapability(capability, facing)?CapabilityLoader.EXPOSED_DATA.cast(data):null;
			}
	
			@Override
			public NBTTagCompound serializeNBT() {
				NBTTagCompound compound = new NBTTagCompound();
	            compound.setTag("stand_data", storage.writeNBT(CapabilityLoader.EXPOSED_DATA, data, null));
	            return compound;
			}
	
			@Override
			public void deserializeNBT(NBTTagCompound nbt) {
				NBTTagCompound cmp = nbt.getCompoundTag("stand_data");
				storage.readNBT(CapabilityLoader.EXPOSED_DATA, data, null, cmp);
			}
			
    }

    public static enum States{
		DEFAULT("default"),
		IDLE("idle"),
		PUNCH("punch"),
		PROTECT("protect");

		States(String name) {
			this.name=name;
		}
		String name;

		public String getName() {
			return name;
		}
	}

}
