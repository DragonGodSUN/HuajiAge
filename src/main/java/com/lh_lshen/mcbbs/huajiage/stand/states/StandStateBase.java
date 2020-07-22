package com.lh_lshen.mcbbs.huajiage.stand.states;

import com.google.common.collect.Lists;
import com.lh_lshen.mcbbs.huajiage.api.IStandState;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

import java.util.List;

public abstract class StandStateBase implements IStandState {
    private String stand;
    private String stateName;
    private List<String> extraDatas = Lists.newArrayList();
    private int stage;
    private boolean isHandPlay;
    private boolean soundLoop;

    public StandStateBase() {
    }

    public StandStateBase(String stand, String stateName, boolean isHandPlay, boolean soundLoop) {
        this.stand = stand;
        this.stateName = stateName;
        this.isHandPlay = isHandPlay;
        this.soundLoop = soundLoop;
        this.stage = 0;
    }

    public StandStateBase(String stand, String stateName, boolean isHandPlay, boolean soundLoop, int stage) {
        this.stand = stand;
        this.stateName = stateName;
        this.isHandPlay = isHandPlay;
        this.soundLoop = soundLoop;
        this.stage = stage;
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    public String getStateName() {
        return stateName;
    }

    @Override
    public int getStage() {
        return stage;
    }

    public List<String> getExtraDatas() {
        return extraDatas;
    }

    public void addExtraData(String extraData) {
        this.extraDatas.add(extraData);
    }

    public boolean hasExtraData(String extraData){
        return extraDatas.contains(extraData);
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public boolean isHandPlay() {
        return isHandPlay;
    }

    public void setHandPlay(boolean handPlay) {
        isHandPlay = handPlay;
    }

    @Override
    public boolean isSoundLoop() {
        return soundLoop;
    }

    public void setSoundLoop(boolean soundLoop) {
        this.soundLoop = soundLoop;
    }

    @Override
    public void doTaskOutOfTime(EntityLivingBase user) {
        user.addPotionEffect(new PotionEffect(PotionLoader.potionStand , 5*20  ));
        user.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS , 5*20, 1 ));
        user.addPotionEffect(new PotionEffect(MobEffects.HUNGER , 5*20 , ConfigHuaji.Stands.allowStandPunish? 24 : 49 ));
        if(ConfigHuaji.Stands.allowStandPunish) {
            user.addPotionEffect(new PotionEffect(MobEffects.WITHER , 5*20 , 1 ));
        }
    }
}
