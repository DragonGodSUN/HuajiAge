package com.lh_lshen.mcbbs.huajiage.client.resources.pojo;

import com.github.tartaricacid.touhoulittlemaid.TouhouLittleMaid;
import com.google.common.collect.Lists;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import com.lh_lshen.mcbbs.huajiage.common.CommonProxy;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.Collections;
import java.util.List;

/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */
public class StandModelInfo implements IModelInfo {
    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private List<String> description;

    @SerializedName("model")
    private String model;

    @SerializedName("texture")
    private String texture;

    @SerializedName("model_id")
    private String modelId;

    @SerializedName("state")
    private String state;

    @SerializedName("transfer")
    private List<String> transfer;

    @SerializedName("rotation")
    private List<String> rotation;

    @SerializedName("render_entity_scale")
    private float renderEntityScale = 1.0f;

    @SerializedName("animation")
    private List<String> animation;

//    private ResourceLocation ID = new ResourceLocation(modelId!=null?modelId:"huajiage:the_world");


    @Override
    public ResourceLocation getTexture() {
        return new ResourceLocation(texture);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public List<Float> getTransfer() {
        if(transfer!=null && !transfer.isEmpty()){
            List<Float> fl = Lists.newArrayList();
            for(String s : transfer){
            float f = NumberUtils.createFloat(s);
            fl.add(f);
            }
            return fl;
        }
        return null;
    }

    @Override
    public List<Float> getRotation() {
        if(rotation!=null && !rotation.isEmpty()){
            List<Float> fl = Lists.newArrayList();
            for(String s : rotation){
            float f = NumberUtils.createFloat(s);
            fl.add(f);
            }
            return fl;
        }
        return null;
    }


    @Override
    public List<String> getDescription() {
        return description;
    }

    @Override
    public List<ResourceLocation> getAnimation() {
        List<ResourceLocation> list = Lists.newArrayList();
        if(animation!=null&&!animation.isEmpty()){
            for(String s : animation){
                list.add(new ResourceLocation(s));
            }
        }
        return list;
    }

    @Override
    public ResourceLocation getModelId() {
        return new ResourceLocation(modelId+"_"+ state);
    }

    @Override
    public ResourceLocation getModel() {
        return new ResourceLocation(model);
    }

    public float getRenderEntityScale() {
        return renderEntityScale;
    }


    @SuppressWarnings("unchecked")
    @Override
    public StandModelInfo decorate() {
        // description 设置为空列表
        if (description == null) {
            description = Collections.EMPTY_LIST;
        }
        // 如果 model_id 为空，抛出异常
        if (modelId == null) {
            throw new JsonSyntaxException("Expected \"model_id\" in model");
        }
        // 如果 state 为空，生成默认状态
        if (state == null) {
            state = "default";
        }
        // 如果 model 或 texture 为空，自动生成默认位置的模型
        if (model == null) {
            model = new ResourceLocation(new ResourceLocation(modelId).getNamespace(), "models/entity/" + new ResourceLocation(modelId).getPath() +(state.equals("default")? ".json":("_"+ state) + ".json")).toString();
        }
        if (texture == null) {
            texture = new ResourceLocation(new ResourceLocation(modelId).getNamespace(), "textures/entity/" + new ResourceLocation(modelId).getPath() +(state.equals("default")? ".png":("_"+ state) + ".png")).toString();
        }
        // 如果名称为空，自动生成本地化名称
        if (name == null) {
            name = String.format("{model.%s.%s.name}", new ResourceLocation(modelId).getNamespace(), new ResourceLocation(modelId).getPath());
        }
        if (animation == null || animation.size() == 0) {
            animation = Collections.EMPTY_LIST;
            if(CommonProxy.ModsLoader.isTouhouMaidLoaded()){
                animation = Lists.newArrayList(
                        new ResourceLocation(TouhouLittleMaid.MOD_ID, "animation/maid/default/head/default.js").toString(),
                        new ResourceLocation(TouhouLittleMaid.MOD_ID, "animation/maid/default/head/blink.js").toString(),
                        new ResourceLocation(TouhouLittleMaid.MOD_ID, "animation/maid/default/head/beg.js").toString(),
                        new ResourceLocation(TouhouLittleMaid.MOD_ID, "animation/maid/default/head/music_shake.js").toString(),
                        new ResourceLocation(TouhouLittleMaid.MOD_ID, "animation/maid/default/leg/default.js").toString(),
                        new ResourceLocation(TouhouLittleMaid.MOD_ID, "animation/maid/default/arm/default.js").toString(),
                        new ResourceLocation(TouhouLittleMaid.MOD_ID, "animation/maid/default/arm/swing.js").toString(),
                        new ResourceLocation(TouhouLittleMaid.MOD_ID, "animation/maid/default/arm/vertical.js").toString(),
                        new ResourceLocation(TouhouLittleMaid.MOD_ID, "animation/maid/default/sit/default.js").toString(),
                        new ResourceLocation(TouhouLittleMaid.MOD_ID, "animation/maid/default/armor/default.js").toString(),
                        new ResourceLocation(TouhouLittleMaid.MOD_ID, "animation/maid/default/armor/reverse.js").toString(),
                        new ResourceLocation(TouhouLittleMaid.MOD_ID, "animation/maid/default/wing/default.js").toString(),
                        new ResourceLocation(TouhouLittleMaid.MOD_ID, "animation/maid/default/tail/default.js").toString(),
                        new ResourceLocation(TouhouLittleMaid.MOD_ID, "animation/maid/default/sit/skirt_rotation.js").toString(),
                        new ResourceLocation(TouhouLittleMaid.MOD_ID, "animation/base/float/default.js").toString()
                );
            }
        }
        if (transfer == null || transfer.isEmpty()) {
            transfer = Lists.newArrayList("0f","-1.0f","0.75f");//x,y,z
        }
        if (rotation == null || rotation.isEmpty()) {
            rotation = Lists.newArrayList("0f","0f","0f","0f");//degree,y,p,w
        }
        renderEntityScale = MathHelper.clamp(renderEntityScale, 0.7f, 1.3f);
        return this;
    }
}
