package com.lh_lshen.mcbbs.huajiage.stand.custom;

import com.google.common.collect.Lists;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class StandCustomInfo {
//  替身ID
    @SerializedName("stand")
    private String stand;

//  替身名称
    @SerializedName("name")
    private String name;

//  模型ID
    @SerializedName("model_id")
    private String modelId;

//  替身拥有状态
    @SerializedName("states")
    private List<String> states;

//  光盘材质路径
    @SerializedName("disc")
    private ResourceLocation disc;

//  替身拥有阶段
    @SerializedName("stages")
    private int stages;

//  速度，力量，耐久，射程，充能速度
    @SerializedName("attributes")
    private List<Number> attributes;

//  默认是否隐藏手臂
    @SerializedName("hand_hide_default")
    private boolean handHiden;

//  放出状态下的行为
    @SerializedName("update")
    private List<Object> update;

//  替身技能
    @SerializedName("capability")
    private List<Object> capability;

    public String getStand() {
        return stand;
    }

    public String getName() {
        return name;
    }

    public String getModelId() {
        return modelId;
    }

    public String getModelId(String state) {
        return modelId+"_"+state;
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

    public boolean isHandHiden() {
        return handHiden;
    }

    public List<Object> getUpdate() {
        return update;
    }

    public List<Object> getCapability() {
        return capability;
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

        // 如果 name 为空，生成默认状态
        if(name == null){
            name = getModelId("default");
        }

        // 如果 state 为空，生成默认状态
        if (states == null) {
            states = Collections.singletonList("default");
        }
        //如果 disc 材质地址为空，生成默认材质路径
        if (disc == null) {
            disc = new ResourceLocation(new ResourceLocation(modelId).getNamespace(), "textures/entity/" + new ResourceLocation(modelId).getPath() + ".png");
        }
        // 如果stages为0，生成默认值
        if (stages == 0) {
            stages = 1;
        }
        // 如果属性为空，生成默认值
        if (attributes == null || attributes.size() == 0) {
            attributes = Lists.newArrayList(1.2f,10f,200,2f,60000,75);
        }

        // 如果更新AI为空，生成默认值
        if(update == null || update.size() == 0){
            update = Lists.newArrayList();
        }

        // 如果技能为空，生成默认值
        if(capability == null || capability.size() == 0){
            capability = Lists.newArrayList();
        }

        return this;
    }

}
