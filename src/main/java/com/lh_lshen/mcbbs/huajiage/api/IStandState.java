package com.lh_lshen.mcbbs.huajiage.api;

import net.minecraft.entity.EntityLivingBase;
import sun.reflect.generics.tree.VoidDescriptor;

public interface IStandState {
    String getStand();

    String getStateName();

    boolean isSoundLoop();

    void doTask(EntityLivingBase user);

}
