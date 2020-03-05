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

public class CapabilityStandBuffHandler implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(StandBuffHandler.class)
    public static Capability<StandBuffHandler> STAND_BUFF = null;
    private StandBuffHandler instance = STAND_BUFF.getDefaultInstance();

    public static void register() {
        CapabilityManager.INSTANCE.register(StandBuffHandler.class, new Capability.IStorage<StandBuffHandler>() {
            @Override
            public void readNBT(Capability<StandBuffHandler> capability, StandBuffHandler instance, EnumFacing side, NBTBase nbt) {
            	instance.setTime(((NBTTagInt)nbt).getInt());
            }

            @Override
            public NBTBase writeNBT(Capability<StandBuffHandler> capability, StandBuffHandler instance, EnumFacing side) {
            	return new NBTTagInt(instance.getTime());
            }
        }, StandBuffHandler.FACTORY);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == STAND_BUFF;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == STAND_BUFF ? STAND_BUFF.cast(this.instance) : null;
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        STAND_BUFF.getStorage().readNBT(STAND_BUFF, this.instance, null, nbt);
    }

    @Override
    public NBTBase serializeNBT() {
        return STAND_BUFF.getStorage().writeNBT(STAND_BUFF, this.instance, null);
    }
}
