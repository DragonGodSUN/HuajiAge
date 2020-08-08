package com.lh_lshen.mcbbs.huajiage.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityStandChargeHandler implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(StandChargeHandler.class)
    public static Capability<StandChargeHandler> STAND_CHARGE = null;
    private StandChargeHandler instance = STAND_CHARGE.getDefaultInstance();

    public static void register() {
        CapabilityManager.INSTANCE.register(StandChargeHandler.class, new Storage(), StandChargeHandler.FACTORY);
    }

    public static class Storage implements Capability.IStorage<StandChargeHandler>{
        @Override
        public NBTBase writeNBT(Capability<StandChargeHandler> capability, StandChargeHandler instance, EnumFacing side) {
            int value = instance.getChargeValue();
            NBTTagCompound tag = new NBTTagCompound();
            tag.setInteger("charge", value);
            return tag;
        }

        @Override
        public void readNBT(Capability<StandChargeHandler> capability, StandChargeHandler instance, EnumFacing side, NBTBase nbt) {
            if (nbt instanceof NBTTagCompound) {
                instance.setChargeValue((((NBTTagCompound) nbt).getInteger("charge")));
            }
        }
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
        if(nbt instanceof NBTTagCompound){
            NBTTagCompound compound = ((NBTTagCompound)nbt).getCompoundTag("charge_data");
            STAND_CHARGE.getStorage().readNBT(STAND_CHARGE, this.instance, null, compound);
        }
    }

    @Override
    public NBTBase serializeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setTag("charge_data",STAND_CHARGE.getStorage().writeNBT(STAND_CHARGE, this.instance, null));
        return compound;

    }
}
