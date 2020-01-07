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

public class CapabilityStandStageHandler implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(StandStageHandler.class)
    public static Capability<StandStageHandler> STAND_STAGE = null;
    private StandStageHandler instance = STAND_STAGE.getDefaultInstance();

    public static void register() {
        CapabilityManager.INSTANCE.register(StandStageHandler.class, new Capability.IStorage<StandStageHandler>() {
            @Override
            public void readNBT(Capability<StandStageHandler> capability, StandStageHandler instance, EnumFacing side, NBTBase nbt) {
            	instance.setStage(((NBTTagInt)nbt).getInt());
            }

            @Override
            public NBTBase writeNBT(Capability<StandStageHandler> capability, StandStageHandler instance, EnumFacing side) {
            	return new NBTTagInt(instance.getStage());
            }
        }, StandStageHandler.FACTORY);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == STAND_STAGE;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == STAND_STAGE ? STAND_STAGE.cast(this.instance) : null;
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        STAND_STAGE.getStorage().readNBT(STAND_STAGE, this.instance, null, nbt);
    }

    @Override
    public NBTBase serializeNBT() {
        return STAND_STAGE.getStorage().writeNBT(STAND_STAGE, this.instance, null);
    }
}
