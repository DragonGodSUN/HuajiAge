package com.lh_lshen.mcbbs.huajiage.stand.custom;


import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.HAModelFactory;
import com.lh_lshen.mcbbs.huajiage.common.CommonProxy;
import com.lh_lshen.mcbbs.huajiage.stand.custom.script.EntityLivingBaseWrapper;
import com.lh_lshen.mcbbs.huajiage.stand.custom.script.StandDadaWrapper;
import com.lh_lshen.mcbbs.huajiage.stand.custom.script.WorldWrapper;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import javax.script.Invocable;
import javax.script.ScriptException;

public class StandStateCustom extends StandStateBase {
    private StandStateInfo stateInfo;

    public StandStateCustom(){
    }

    public StandStateCustom(StandStateInfo stateInfo) {
        this.stateInfo = stateInfo;
        this.stand = stateInfo.getStand();
        this.stateName = stateInfo.getStateId();
        this.extraDatas = stateInfo.getStateTags();
        this.isHandPlay = stateInfo.isHand();
        this.stage = stateInfo.getStage();
        this.soundLoop = stateInfo.isSoundRepeat();
        HuajiAge.LOGGER.info("Loaded Stand Info: {} ", isHandPlay);
    }

    public StandStateInfo getStateInfo() {
        return stateInfo;
    }

    @Override
    public String getModelID() {
        return stateInfo.getModelId();
    }

    @Override
    public ResourceLocation getTex() {

        return HAModelFactory.getTexture(stateInfo.getModelId());
    }

    @Override
    public void doTask(EntityLivingBase user) {
        try {
            if (stateInfo == null || stateInfo.getScript() == null) {
                return;
            }
            Invocable invocable = (Invocable) CommonProxy.NASHORN_ENGINE;
            if(stateInfo.getStateId().equals(stateName)){
            invocable.invokeMethod(stateInfo.getScript(), "update",
                    new WorldWrapper(user.getEntityWorld()), new EntityLivingBaseWrapper(user), new StandDadaWrapper(user));
            }
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doTaskOutOfTime(EntityLivingBase user) {
        try {
            if (stateInfo.getScript() == null) {
                return;
            }
            Invocable invocable = (Invocable) CommonProxy.NASHORN_ENGINE;
            invocable.invokeMethod(stateInfo.getScript(), "timeOut",
                    new WorldWrapper(user.getEntityWorld()), new EntityLivingBaseWrapper(user), new StandDadaWrapper(user));
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void doTaskCapability(EntityLivingBase user){
        try {
            if (stateInfo.getScript() == null) {
                return;
            }
            Invocable invocable = (Invocable) CommonProxy.NASHORN_ENGINE;
            invocable.invokeMethod(stateInfo.getScript(), "capability",
                    new WorldWrapper(user.getEntityWorld()), new EntityLivingBaseWrapper(user), new StandDadaWrapper(user));
        } catch (ScriptException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

}
