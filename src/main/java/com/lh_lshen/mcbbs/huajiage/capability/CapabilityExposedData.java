package com.lh_lshen.mcbbs.huajiage.capability;

import java.util.concurrent.Callable;

import com.lh_lshen.mcbbs.huajiage.stand.EnumStandtype;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapabilityExposedData {
	    public static class Storage implements Capability.IStorage<IExposedData>
	    {
	        @Override
	        public NBTBase writeNBT(Capability<IExposedData> capability, IExposedData instance, EnumFacing side)
	        {
	        	
	        	NBTTagCompound cmp_stand = new NBTTagCompound();
	        	
	        	cmp_stand.setString("stand_name", instance.getStand());
	        	cmp_stand.setBoolean("stand_put", instance.isTriggered());
	        	cmp_stand.setInteger("stand_stage", instance.getStage());
	        	
	            return cmp_stand;
	        }

	        @Override
	        public void readNBT(Capability<IExposedData> capability, IExposedData instance, EnumFacing side,
	                NBTBase nbt)
	        {
	        	NBTTagCompound cmp = (NBTTagCompound) nbt;
	        	
	        	String stand = cmp.getString("stand_name");
	        	boolean istriggered = cmp.getBoolean("stand_put");
	        	int stage = cmp.getInteger("stand_stage");
	        	
	        	instance.setStand(stand);
	        	instance.setTrigger(istriggered);
	        	instance.setStage(stage);
	        }
	        
	    }
	    
	    public static class Implementation implements IExposedData{
	    	static Implementation.Factory FACTORY = new Implementation.Factory();
	    	String standName = StandLoader.EMPTY;
	    	boolean istriggered = false;
	    	boolean dirty =false;
	    	int stage = 0;
	    	
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
	    		this.istriggered = trigger;
	    		markDirty();
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
	    	public boolean isTriggered() {
	    		return istriggered;
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

}
