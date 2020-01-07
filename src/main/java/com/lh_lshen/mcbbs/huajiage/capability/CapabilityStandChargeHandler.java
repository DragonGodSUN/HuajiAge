package com.lh_lshen.mcbbs.huajiage.capability;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagString;
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
            	instance.setChargeValue(((NBTTagInt)nbt).getInt());
            }

            @Override
            public NBTBase writeNBT(Capability<StandChargeHandler> capability, StandChargeHandler instance, EnumFacing side) {
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
        STAND_CHARGE.getStorage().readNBT(STAND_CHARGE, this.instance, null, nbt);
    }

    @Override
    public NBTBase serializeNBT() {
        return STAND_CHARGE.getStorage().writeNBT(STAND_CHARGE, this.instance, null);
    }
}
