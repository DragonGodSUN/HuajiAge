package com.lh_lshen.mcbbs.huajiage.stand.states.default_set;

import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.helper.StandPowerHelper;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import net.minecraft.entity.EntityLivingBase;

public class StateKillerQueenDefault extends StandStateBase {
    public StateKillerQueenDefault() {
    }

    public StateKillerQueenDefault(String stand, String stateName, boolean isHandPlay, boolean soundLoop) {
        super(stand, stateName, isHandPlay, soundLoop);
    }

    @Override
    public void doTask(EntityLivingBase user) {
        StandPowerHelper.MPCharge(user,(int)(StandLoader.KILLER_QUEEN.getCharge()/3));
    }
}
