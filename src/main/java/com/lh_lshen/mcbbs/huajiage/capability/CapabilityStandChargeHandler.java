package com.lh_lshen.mcbbs.huajiage.capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class CapabilityStandChargeHandler implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(StandChargeHandler.class)
    public static Capability<StandChargeHandler> STAND_CHARGE = null;
    private StandChargeHandler instance = STAND_CHARGE.getDefaultInstance();

    public static void register() {
        CapabilityManager.INSTANCE.register(StandChargeHandler.class, new Capability.IStorage<StandChargeHandler>() {
            @Override
            public void readNBT(Capability<StandChargeHandler> capability, StandChargeHandler instance, EnumFacing side, NBTBase nbt) {
//            	NBTTagCompound compound = (NBTTagCompound) nbt;
//            	 if(!compound.hasNoTags()) {
//            		 int charge = compound.getInteger("charge");
//            		 int max = compound.getInteger("max");
//            		 instance.setChargeValue(charge);
//            		 instance.setMaxValue(max);
//            	 }
            	instance.setChargeValue(((NBTTagInt)nbt).getInt());
            }

            @Override
            public NBTBase writeNBT(Capability<StandChargeHandler> capability, StandChargeHandler instance, EnumFacing side) {
//            	 NBTTagCompound compound = new NBTTagCompound();
//            	 int charge = instance.getChargeValue();
//            	 int max = instance.getMaxValue();
//            	 compound.setInteger("charge", charge);
//            	 compound.setInteger("max", max);
//            	 return compound;
            	return new NBTTagInt(instance.getChargeValue());
            }
        }, StandChargeHandler.FACTORY);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == STAND_CHARGE;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == STAND_CHARGE ? STAND_CHARGE.cast(this.instance) : null;
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
//    	 NBTTagCompound compound =nbt.getCompoundTag("standMP");
        STAND_CHARGE.getStorage().readNBT(STAND_CHARGE, this.instance, null, nbt);
    }

    @Override
    public NBTBase serializeNBT() {
//    	NBTTagCompound compound = new NBTTagCompound();
//    	compound.setTag("standMP",STAND_CHARGE.getStorage().writeNBT(STAND_CHARGE, this.instance, null));
    	NBTBase tag = STAND_CHARGE.getStorage().writeNBT(STAND_CHARGE, this.instance, null);
//    	if(tag instanceof NBTTagCompound) {
//    		return (NBTTagCompound) tag;
//    	}
    	return tag;
    }
}
