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

public class CapabilityStandSkillHandler implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(StandSkillHandler.class)
    public static Capability<StandSkillHandler> STAND_SKILL = null;
    private StandSkillHandler instance = STAND_SKILL.getDefaultInstance();

    public static void register() {
        CapabilityManager.INSTANCE.register(StandSkillHandler.class, new Capability.IStorage<StandSkillHandler>() {
            @Override
            public void readNBT(Capability<StandSkillHandler> capability, StandSkillHandler instance, EnumFacing side, NBTBase nbt) {
            	instance.setStage(((NBTTagInt)nbt).getInt());
            }

            @Override
            public NBTBase writeNBT(Capability<StandSkillHandler> capability, StandSkillHandler instance, EnumFacing side) {
            	return new NBTTagInt(instance.getStage());
            }
        }, StandSkillHandler.FACTORY);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == STAND_SKILL;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == STAND_SKILL ? STAND_SKILL.cast(this.instance) : null;
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        STAND_SKILL.getStorage().readNBT(STAND_SKILL, this.instance, null, nbt);
    }

    @Override
    public NBTBase serializeNBT() {
        return STAND_SKILL.getStorage().writeNBT(STAND_SKILL, this.instance, null);
    }
}
