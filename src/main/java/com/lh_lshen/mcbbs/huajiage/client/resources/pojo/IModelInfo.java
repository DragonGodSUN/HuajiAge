package com.lh_lshen.mcbbs.huajiage.client.resources.pojo;

import net.minecraft.util.ResourceLocation;

import java.util.List;

/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */
public interface IModelInfo {
    ResourceLocation getModelId();

    String getName();

    String getState();

    ResourceLocation getModel();

    List<Float> getTransfer();

    List<Float> getRotation();

    List<Float> getTransferFirst();

    List<Float> getRotationFirst();

    Float getRotationFactorFirst();

    List<ResourceLocation> getAnimation();

    ResourceLocation getTexture();

    List<String> getDescription();

    <T extends IModelInfo> T decorate();
}
