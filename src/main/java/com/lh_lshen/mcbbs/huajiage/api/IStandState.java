package com.lh_lshen.mcbbs.huajiage.api;

import net.minecraft.entity.EntityLivingBase;

public interface IStandState {
    String getStand();

    String getStateName();

    boolean isSoundLoop();

    void doTask(EntityLivingBase user);

    void doTaskOutOfTime(EntityLivingBase user);

}
