package com.lh_lshen.mcbbs.huajiage.client.model.custom;
/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */
import net.minecraft.client.model.ModelRenderer;

public class ModelRendererWrapper {
    private ModelRenderer modelRenderer;

    public ModelRendererWrapper(ModelRenderer modelRenderer) {
        this.modelRenderer = modelRenderer;
    }

    public void setModelRenderer(ModelRenderer modelRenderer) {
        this.modelRenderer = modelRenderer;
    }

    public float getRotateAngleX() {
        return modelRenderer.rotateAngleX;
    }

    public void setRotateAngleX(float rotateAngleX) {
        modelRenderer.rotateAngleX = rotateAngleX;
    }

    public float getRotateAngleY() {
        return modelRenderer.rotateAngleY;
    }

    public void setRotateAngleY(float rotateAngleY) {
        modelRenderer.rotateAngleY = rotateAngleY;
    }

    public float getRotateAngleZ() {
        return modelRenderer.rotateAngleZ;
    }

    public void setRotateAngleZ(float rotateAngleZ) {
        modelRenderer.rotateAngleZ = rotateAngleZ;
    }

    public float getOffsetX() {
        return modelRenderer.offsetX;
    }

    public void setOffsetX(float offsetX) {
        modelRenderer.offsetX = offsetX;
    }

    public float getOffsetY() {
        return modelRenderer.offsetY;
    }

    public void setOffsetY(float offsetY) {
        modelRenderer.offsetY = offsetY;
    }

    public float getOffsetZ() {
        return modelRenderer.offsetZ;
    }

    public void setOffsetZ(float offsetZ) {
        modelRenderer.offsetZ = offsetZ;
    }

    public boolean isHidden() {
        return modelRenderer.isHidden;
    }

    public void setHidden(boolean hidden) {
        modelRenderer.isHidden = hidden;
    }

    public ModelRenderer getModelRenderer() {
        return modelRenderer;
    }
}

