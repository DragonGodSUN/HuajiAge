package com.lh_lshen.mcbbs.huajiage.stand.custom.script;

import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import net.minecraft.entity.EntityLivingBase;

public class StandDadaWrapper {
    private IExposedData data;
    private StandChargeHandler chargeHandler;

    public StandDadaWrapper(IExposedData data, StandChargeHandler chargeHandler) {
        this.data = data;
        this.chargeHandler = chargeHandler;
    }

    public StandDadaWrapper(EntityLivingBase livingBase) {
        this.data = StandUtil.getStandData(livingBase);
        this.chargeHandler = StandUtil.getChargeHandler(livingBase);
    }

    public IExposedData getData() {
        return data;
    }

    public String getStandName(){
        return data.getStand();
    }

    public int getStage(){
        return data.getStage();
    }

    public String getModel(){
        return data.getModel();
    }

    public String getState(){
        return data.getState();
    }

    public boolean isTriggered(){
        return data.isTriggered();
    }

    public int getMP(){
        return chargeHandler.getChargeValue();
    }

    public int getMaxMP(){
        return chargeHandler.getMaxValue();
    }

    public int getBuffer(){
        return chargeHandler.getBuffer();
    }

    public String getBufferTag(){
        return chargeHandler.getBuffTag();
    }

    public void setMP(int mp){
        chargeHandler.setChargeValue(mp);
    }

    public void setMaxMP(int mp){
        chargeHandler.setMaxValue(mp);
    }

    public void setBuffer(int buffer){
        chargeHandler.setBuffer(buffer);
    }

    public void setBufferTag(String tag){
        chargeHandler.setBuffTag(tag);
    }
}
