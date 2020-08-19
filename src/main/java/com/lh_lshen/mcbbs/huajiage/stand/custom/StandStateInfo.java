package com.lh_lshen.mcbbs.huajiage.stand.custom;

import java.util.List;

public class StandStateInfo {
    private String stand;
    private String stateId;
    private String stateKey;
    private String modelId;
    private List<String> stateTags;
    private Object script;
    private boolean hand;
    private int stage;

    public StandStateInfo(String stand, String stateId, String stateKey, String modelId, List<String> stateTags,
                          Object script, boolean isHandDisplay, int stage) {
        this.stand = stand;
        this.stateId = stateId;
        this.stateKey = stateKey;
        this.modelId = modelId;
        this.stateTags = stateTags;
        this.script = script;
        this.hand = isHandDisplay;
        this.stage = stage;
    }

    public String getStand() {
        return stand;
    }

    public String getStateId() {
        return stateId;
    }

    public String getStateKey() {
        return stateKey;
    }

    public String getModelId() {
        return modelId;
    }

    public Object getScript() {
        return script;
    }

    public boolean isHand() {
        return hand;
    }

    public int getStage() {
        return stage;
    }

    public List<String> getStateTags() {
        return stateTags;
    }
}
