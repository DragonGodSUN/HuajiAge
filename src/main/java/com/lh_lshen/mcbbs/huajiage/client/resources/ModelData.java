package com.lh_lshen.mcbbs.huajiage.client.resources;

import com.lh_lshen.mcbbs.huajiage.client.model.custom.ModelStandJson;
import com.lh_lshen.mcbbs.huajiage.client.resources.pojo.StandModelInfo;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */
public class ModelData {
    private ModelStandJson model;
    private StandModelInfo info;
    private List<Object> animations;

    public ModelData(ModelStandJson model, StandModelInfo info, @Nullable List<Object> animations) {
        this.model = model;
        this.info = info;
        this.animations = animations;
    }

    public void setModel(ModelStandJson model) {
        this.model = model;
    }

    public void setInfo(StandModelInfo info) {
        this.info = info;
    }

    public void setAnimations(List<Object> animations) {
        this.animations = animations;
    }

    public ModelStandJson getModel() {
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
