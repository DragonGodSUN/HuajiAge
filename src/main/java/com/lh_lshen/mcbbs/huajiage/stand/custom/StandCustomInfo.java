package com.lh_lshen.mcbbs.huajiage.stand.custom;

import com.google.common.collect.Lists;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import net.minecraft.util.ResourceLocation;

import java.util.Collections;
import java.util.List;

public class StandCustomInfo {
    // 替身ID
    @SerializedName("stand")
    private String stand;

    // 替身名称
    @SerializedName("name")
    private String name;

    // 替身拥有状态
    @SerializedName("states")
    private List<String> states;

    // 光盘材质路径
    @SerializedName("disc")
    private String disc;

    // 替身拥有阶段
    @SerializedName("stages")
    private int stages;

    // 速度，力量，耐久，射程，消耗，充能速度,总MP量
    @SerializedName("attributes")
    private List<Number> attributes;

    // 替身发出音效
    @SerializedName("sounds")
    private List<String> sounds;


    public String getStand() {
        return stand;
    }

    public String getName() {
        return name;
    }


    public List<String> getStates() {
        return states;
    }

    public ResourceLocation getDisc() {
        return new ResourceLocation(disc);
    }


    public int getStages() {
        return stages;
    }

    public List<Float> getAttributes() {
        List<Float> afs = Lists.newArrayList();
        for(Number s : attributes){
            afs.add(s.floatValue());
        }
        return afs;
    }

    public List<String> getSounds() {
        return sounds;
    }


    @SuppressWarnings("unchecked")
    public StandCustomInfo decorate() {
        // 如果 stand 为空，抛出异常
        if (stand == null) {
            throw new JsonSyntaxException("Expected \"stand\" in stand loading");
        }

        if(name == null){
            name = "stand"+"."+stand.replace(":",".")+"."+"name";
        }

        // 如果 state 为空，生成默认状态
        if (states == null) {
            states = Collections.singletonList("default");
        }
        if(!states.contains("default")){
            states.add("default");
        }

        //如果 disc 材质地址为空，生成默认材质路径
        if (disc == null) {
            disc = HuajiAge.MODID +":"+"disc/" + "disc_"+ new ResourceLocation(stand).getNamespace()+ "_" + new ResourceLocation(stand).getPath();
        }

        // 如果stages为0，生成默认值
        if (stages == 0) {
            stages = 1;
        }

        // 如果属性为空，生成默认值
        if (attributes == null || attributes.size() == 0) {
            attributes = Lists.newArrayList(1.2f,10f,200f,2f,60000,75,100000);
        }

        if (sounds == null){
            sounds = Lists.newArrayList();
        }

        return this;
    }

}
