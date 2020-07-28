package com.lh_lshen.mcbbs.huajiage.api;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public interface IStandState {
    String getStand();

    String getStateName();

    int getStage();

    String getModelID();

    ResourceLocation getTex();

    boolean isSoundLoop();

    void doTask(EntityLivingBase user);

    void doTaskOutOfTime(EntityLivingBase user);

}
