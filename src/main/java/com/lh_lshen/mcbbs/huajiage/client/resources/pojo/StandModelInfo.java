package com.lh_lshen.mcbbs.huajiage.client.resources.pojo;
/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */

import com.google.common.collect.Lists;
import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import java.util.Collections;
import java.util.List;

public class StandModelInfo implements IModelInfo {
    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private List<String> description;

    @SerializedName("model")
    private ResourceLocation model;

    @SerializedName("texture")
    private ResourceLocation texture;

    @SerializedName("model_id")
    private String modelId;

    @SerializedName("state")
    private String state;

    @SerializedName("transfer")
    private List<Number> transfer;

    @SerializedName("rotation")
    private List<Number> rotation;

    @SerializedName("render_entity_scale")
    private float renderEntityScale = 1.0f;

    @SerializedName("animation")
    private List<ResourceLocation> animation;

//    private ResourceLocation ID = new ResourceLocation(modelId!=null?modelId:"huajiage:the_world");


    @Override
    public ResourceLocation getTexture() {
        return texture;
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
    public List<Number> getTransfer() {
        return transfer;
    }

    @Override
    public List<Number> getRotation() {
        return rotation;
    }

    @Override
    public List<String> getDescription() {
        return description;
    }

    @Override
    public List<ResourceLocation> getAnimation() {
        return animation;
    }

    @Override
    public ResourceLocation getModelId() {
        return new ResourceLocation(modelId);
    }

    @Override
    public ResourceLocation getModel() {
        return model;
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
            model = new ResourceLocation(new ResourceLocation(modelId).getNamespace(), "models/entity/" + new ResourceLocation(modelId).getPath() + "_"+ state + ".json");
        }
        if (texture == null) {
            texture = new ResourceLocation(new ResourceLocation(modelId).getNamespace(), "textures/entity/" + new ResourceLocation(modelId).getPath() + "_"+ state + ".png");
        }
        // 如果名称为空，自动生成本地化名称
        if (name == null) {
            name = String.format("{model.%s.%s.name}", new ResourceLocation(modelId).getNamespace(), new ResourceLocation(modelId).getPath());
        }
        if (animation == null || animation.size() == 0) {
            animation = Collections.EMPTY_LIST;
        }
        if (transfer == null || transfer.isEmpty()) {
            transfer = Lists.newArrayList(0f,-0.5f,-0.75f);//x,y,z
        }
        if (rotation == null || rotation.isEmpty()) {
            rotation = Lists.newArrayList(0f,0f,0f,0f);//degree,y,p,w
        }
        renderEntityScale = MathHelper.clamp(renderEntityScale, 0.7f, 1.3f);
        return this;
    }
}
