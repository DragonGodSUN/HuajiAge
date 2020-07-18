package com.lh_lshen.mcbbs.huajiage.stand.states.default_set;

import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.helper.StandPowerHelper;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import net.minecraft.entity.EntityLivingBase;

public class StateOrgaRequiemDefault extends StandStateBase {
    public StateOrgaRequiemDefault() {
    }

    public StateOrgaRequiemDefault(String stand, String stateName, boolean isHandPlay, boolean soundLoop) {
        super(stand, stateName, isHandPlay, soundLoop);
    }

    @Override
    public void doTask(EntityLivingBase user) {
        StandPowerHelper.MPCharge(user,(int)(StandLoader.ORGA_REQUIEM.getCharge()/3));
    }
}
