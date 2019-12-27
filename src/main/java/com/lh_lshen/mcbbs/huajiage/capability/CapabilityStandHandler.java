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

public class CapabilityStandHandler implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(StandHandler.class)
    public static Capability<StandHandler> STAND_TYPE = null;
    private StandHandler instance = STAND_TYPE.getDefaultInstance();

    public static void register() {
        CapabilityManager.INSTANCE.register(StandHandler.class, new Capability.IStorage<StandHandler>() {
            @Override
            public void readNBT(Capability<StandHandler> capability, StandHandler instance, EnumFacing side, NBTBase nbt) {
                instance.setStand(((NBTTagString) nbt).getString());
            }

            @Override
            public NBTBase writeNBT(Capability<StandHandler> capability, StandHandler instance, EnumFacing side) {
                return new NBTTagString(instance.getStand());
            }
        }, StandHandler.FACTORY);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == STAND_TYPE;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == STAND_TYPE ? STAND_TYPE.cast(this.instance) : null;
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        STAND_TYPE.getStorage().readNBT(STAND_TYPE, this.instance, null, nbt);
    }

    @Override
    public NBTBase serializeNBT() {
        return STAND_TYPE.getStorage().writeNBT(STAND_TYPE, this.instance, null);
    }
}
