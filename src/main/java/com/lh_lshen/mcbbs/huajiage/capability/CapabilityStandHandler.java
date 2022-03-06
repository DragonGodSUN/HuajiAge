package com.lh_lshen.mcbbs.huajiage.capability;

import com.google.common.collect.Maps;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public class CapabilityStandHandler implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(StandHandler.class)
    public static Capability<StandHandler> STAND_HANDLER = null;
    private StandHandler instance = STAND_HANDLER.getDefaultInstance();

    public static void register() {
        CapabilityManager.INSTANCE.register(StandHandler.class, new Storage(), StandHandler.FACTORY);
    }

    public static class Storage implements Capability.IStorage<StandHandler>{
        @Override
        public NBTBase writeNBT(Capability<StandHandler> capability, StandHandler instance, EnumFacing side) {
            NBTTagCompound tag = new NBTTagCompound();
            NBTTagCompound tag_recorder = new NBTTagCompound();
            Map<String,Float> recorder = instance.getRecorder();
            for (Map.Entry<String,Float> entry:recorder.entrySet()){
                tag_recorder.setFloat(entry.getKey(),entry.getValue());
            }
            tag.setInteger("charge", instance.getChargeValue());
            tag.setInteger("max",instance.getMaxValue());
            tag.setInteger("buffer",instance.getBuffer());
            tag.setTag("recorder",tag_recorder);
            return tag;
        }

        @Override
        public void readNBT(Capability<StandHandler> capability, StandHandler instance, EnumFacing side, NBTBase nbt) {
            if (nbt instanceof NBTTagCompound) {
                instance.setChargeValue((((NBTTagCompound) nbt).getInteger("charge")));
                instance.setMaxValue((((NBTTagCompound) nbt).getInteger("max")));
                instance.setBuffer((((NBTTagCompound) nbt).getInteger("buffer")));
                NBTTagCompound tag =((NBTTagCompound) nbt).getCompoundTag("recorder");
                Map<String,Float> recorder = Maps.newHashMap();
                for (String uuid : tag.getKeySet()){
                    recorder.put(uuid,tag.getFloat(uuid));
                }
                instance.setRecorder(recorder);
            }
        }
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == STAND_HANDLER;
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == STAND_HANDLER ? STAND_HANDLER.cast(this.instance) : null;
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        if(nbt instanceof NBTTagCompound){
            NBTTagCompound compound = ((NBTTagCompound)nbt).getCompoundTag("stand_user_data");
            STAND_HANDLER.getStorage().readNBT(STAND_HANDLER, this.instance, null, compound);
        }
    }

    @Override
    public NBTBase serializeNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        NBTBase nbt = STAND_HANDLER.getStorage().writeNBT(STAND_HANDLER, this.instance, null);
        if (nbt != null) {
            compound.setTag("stand_user_data", nbt);
        }
        return compound;

    }
}
