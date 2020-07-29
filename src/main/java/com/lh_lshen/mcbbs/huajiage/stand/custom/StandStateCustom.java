package com.lh_lshen.mcbbs.huajiage.stand.custom;

import com.lh_lshen.mcbbs.huajiage.client.resources.pojo.StandModelInfo;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class StandStateCustom extends StandStateBase {
    private StandModelInfo modelInfo;
//    private StandCustomInfo customInfo;

    public StandStateCustom() {
    }

    public StandStateCustom(String stand, String stateName, boolean isHandPlay, boolean soundLoop) {
        super(stand, stateName, isHandPlay, soundLoop);
    }

    public StandStateCustom(String stand, String stateName, boolean isHandPlay, boolean soundLoop, int stage) {
        super(stand, stateName, isHandPlay, soundLoop, stage);
    }

    @Override
    public String getModelID() {
        return modelInfo.getModelId().toString();
    }

    @Override
    public ResourceLocation getTex() {
        return modelInfo.getTexture();
    }

    @Override
    public void doTask(EntityLivingBase user) {

    }

    @Override
    public void doTaskOutOfTime(EntityLivingBase user) {
        super.doTaskOutOfTime(user);
    }
}
