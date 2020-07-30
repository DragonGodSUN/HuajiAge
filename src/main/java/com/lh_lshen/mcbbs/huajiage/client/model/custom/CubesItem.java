package com.lh_lshen.mcbbs.huajiage.client.model.custom;
/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CubesItem {
    @SerializedName("uv")
    private List<Float> uv;

    @SerializedName("mirror")
    private boolean mirror;

    @SerializedName("inflate")
    private float inflate;

    @SerializedName("size")
    private List<Float> size;

    @SerializedName("origin")
    private List<Float> origin;

    public List<Float> getUv() {
        return uv;
    }

    public boolean isMirror() {
        return mirror;
    }

    public float getInflate() {
        return inflate;
    }

    public List<Float> getSize() {
        return size;
    }

    public List<Float> getOrigin() {
        return origin;
    }

    @Override
    public String toString() {
        return
                "CubesItem{" +
                        "uv = '" + uv + '\'' +
                        ",inflate = '" + inflate + '\'' +
                        ",mirror = '" + mirror + '\'' +
                        ",size = '" + size + '\'' +
                        ",origin = '" + origin + '\'' +
                        "}";
    }
}