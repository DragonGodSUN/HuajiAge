package com.lh_lshen.mcbbs.huajiage.stand.custom;

import com.lh_lshen.mcbbs.huajiage.api.IStandState;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.stand.StandResourceLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandStates;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.resource.ResStandCustom;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandRes;
import net.minecraft.entity.EntityLivingBase;

import java.util.Map;
import java.util.Objects;

public class StandCustom extends StandBase {
    private StandCustomInfo info;

    public StandCustom(StandCustomInfo info) {
        this.info = info;
        this.name = info.getStand();
        //  速度，力量，耐久，射程，充能速度
        this.speed = info.getAttributes().get(0);
        this.damage = info.getAttributes().get(1);
        this.duration = (int) info.getAttributes().get(2).floatValue();
        this.distance = info.getAttributes().get(3);
        this.cost = (int) info.getAttributes().get(4).floatValue();
        this.charge = (int) info.getAttributes().get(5).floatValue();
        this.maxMP = (int) info.getAttributes().get(6).floatValue();

        this.localName = info.getName();

        Map<String, StandStateInfo> CUSTOM_STATE_SERVER = StandResourceLoader.CUSTOM_STATE_SERVER;
        for(String state : info.getStates()){
            String key = info.getStand()+"_"+state;
            StandStateInfo info_state = CUSTOM_STATE_SERVER.get(key);
            if(info_state!=null&&key.equals(info.getStand()+"_"+info_state.getStateId())){
                this.addState(info_state.getStateId(),new StandStateCustom(info_state));
            }
        }
        if(!this.states.isEmpty() && StandStates.getStandState(name, states.get(0))!=null){
            this.displayHand = Objects.requireNonNull(StandStates.getStandState(name, states.get(0))).isHandPlay();
        }

    }

    public StandCustom() {
    }

    public StandCustomInfo getInfo() {
        return info;
    }

    @Override
    public StandRes getBindingRes() {
        return new ResStandCustom(info);
    }

    @Override
    public void doStandPower(EntityLivingBase user) {
        super.doStandPower(user);
    }

    @Override
    public void doStandCapability(EntityLivingBase user) {
        StandBase type = StandUtil.getType(user);
        IExposedData data = StandUtil.getStandData(user);
        if(type == null && data==null) {
            return;
        }
        IStandState standState = StandStates.getStandState(type.getName(),data.getState());
        if (standState instanceof StandStateCustom) {
            ((StandStateCustom)standState).doTaskCapability(user);
        }
    }
}
