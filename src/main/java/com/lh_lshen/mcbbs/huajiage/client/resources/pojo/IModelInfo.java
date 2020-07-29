package com.lh_lshen.mcbbs.huajiage.client.resources.pojo;
/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */
import net.minecraft.util.ResourceLocation;

import java.util.List;

public interface IModelInfo {
    ResourceLocation getModelId();

    String getName();

    String getState();

    ResourceLocation getModel();

    List<Number> getTransfer();

    List<Number> getRotation();

    List<ResourceLocation> getAnimation();

    ResourceLocation getTexture();

    List<String> getDescription();

    <T extends IModelInfo> T decorate();
}
