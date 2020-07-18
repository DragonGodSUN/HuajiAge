package com.lh_lshen.mcbbs.huajiage.stand.states;

import com.lh_lshen.mcbbs.huajiage.api.IStandState;

public abstract class StandStateBase implements IStandState {
    private String stand;
    private String stateName;
    private boolean isHandPlay;
    private boolean soundLoop;

    public StandStateBase() {
    }

    public StandStateBase(String stand, String stateName, boolean isHandPlay, boolean soundLoop) {
        this.stand = stand;
        this.stateName = stateName;
        this.isHandPlay = isHandPlay;
        this.soundLoop = soundLoop;
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
}
