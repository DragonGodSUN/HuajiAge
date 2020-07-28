package com.lh_lshen.mcbbs.huajiage.client.resources;

import com.lh_lshen.mcbbs.huajiage.client.model.pojo.EntityModelJson;
import com.lh_lshen.mcbbs.huajiage.client.resources.pojo.StandModelInfo;

import javax.annotation.Nullable;
import java.util.List;

public class ModelData {
    private EntityModelJson model;
    private StandModelInfo info;
    private List<Object> animations;

    public ModelData(EntityModelJson model, StandModelInfo info, @Nullable List<Object> animations) {
        this.model = model;
        this.info = info;
        this.animations = animations;
    }

    public void setModel(EntityModelJson model) {
        this.model = model;
    }

    public void setInfo(StandModelInfo info) {
        this.info = info;
    }

    public void setAnimations(List<Object> animations) {
        this.animations = animations;
    }

    public EntityModelJson getModel() {
        return model;
    }

    public StandModelInfo getInfo() {
        return info;
    }

    @Nullable
    public List<Object> getAnimations() {
        return animations;
    }
}
