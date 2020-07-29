package com.lh_lshen.mcbbs.huajiage.stand.custom;

import com.google.common.collect.Lists;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.List;

public class StandCustomInfo {
    @SerializedName("stand")
    private String stand;

    @SerializedName("model_id")
    private String modelId;

    @SerializedName("states")
    private List<String> states;

    @SerializedName("disc")
    private ResourceLocation disc;

    @SerializedName("stages")
    private int stages;

    @SerializedName("attributes")
    private List<Number> attributes;//速度，力量，耐久，射程，充能速度

    @SerializedName("hand_show_default")
    private boolean handShow;

    public String getStand() {
        return stand;
    }

    public String getModelId() {
        return modelId;
    }

    public List<String> getStates() {
        return states;
    }

    public ResourceLocation getDisc() {
        return disc;
    }

    public int getStages() {
        return stages;
    }

    public List<Number> getAttributes() {
        return attributes;
    }

    public boolean isHandShow() {
        return handShow;
    }

    @SuppressWarnings("unchecked")
    public StandCustomInfo decorate() {
        // 如果 stand 为空，抛出异常
        if (stand == null) {
            throw new JsonSyntaxException("Expected \"stand\" in stand loading");
        }
        // 如果 model_id 为空，抛出异常
        if (modelId == null) {
            throw new JsonSyntaxException("Expected \"model_id\" in stand loading");
        }
        // 如果 state 为空，生成默认状态
        if (states == null) {
            states = Arrays.asList(new String[]{"default"});
        }
        //如果 disc 材质地址为空，生成默认材质路径
        if (disc == null) {
            disc = new ResourceLocation(new ResourceLocation(modelId).getNamespace(), "textures/entity/" + new ResourceLocation(modelId).getPath() + ".png");
        }
        // 如果stages为0，生成默认值
        if (stages == 0) {
            stages = 1;
        }
        if (attributes == null || attributes.size() == 0) {
            attributes = Lists.newArrayList(1.2f,10f,200,2f,60000,75);
        }

        return this;
    }

}
