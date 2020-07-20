package com.lh_lshen.mcbbs.huajiage.stand.states.punch;

import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.helper.StandPowerHelper;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import net.minecraft.entity.EntityLivingBase;

public class StateKillerQueenPunch extends StandStateBase {
    public StateKillerQueenPunch() {
    }

    public StateKillerQueenPunch(String stand, String stateName, boolean isHandPlay, boolean soundLoop) {
        super(stand, stateName, isHandPlay, soundLoop);
    }

    @Override
    public void doTask(EntityLivingBase user) {
        StandBase type = StandUtil.getType(user);
        int stage = StandUtil.getStandStage(user);
        if(type!=null){
        StandPowerHelper.rangePunchAttack(user,45,type.getDamage()*(1+(float)(stage/2)),2);
        }
    }

}
