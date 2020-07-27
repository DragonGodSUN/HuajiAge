package com.lh_lshen.mcbbs.huajiage.client.model.pojo;
/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeometryModel {
    @SerializedName("bones")
    private List<BonesItem> bones;

    @SerializedName("textureheight")
    private int textureheight;

    @SerializedName("texturewidth")
    private int texturewidth;

    @SerializedName("visible_bounds_height")
    private int visibleBoundsHeight;

    @SerializedName("visible_bounds_width")
    private int visibleBoundsWidth;

    @SerializedName("visible_bounds_offset")
    private List<Float> visibleBoundsOffset;

    public List<BonesItem> getBones() {
        return bones;
    }

    public int getTextureheight() {
        return textureheight;
    }

    public int getTexturewidth() {
        return texturewidth;
    }

    public int getVisibleBoundsHeight() {
        return visibleBoundsHeight;
    }

    public int getVisibleBoundsWidth() {
        return visibleBoundsWidth;
    }

    public List<Float> getVisibleBoundsOffset() {
        return visibleBoundsOffset;
    }

    @Override
    public String toString() {
        return
                "GeometryModel{" +
                        "bones = '" + bones + '\'' +
                        ",textureheight = '" + textureheight + '\'' +
                        ",texturewidth = '" + texturewidth + '\'' +
                        ",visible_bounds_height = '" + visibleBoundsHeight + '\'' +
                        ",visible_bounds_width = '" + visibleBoundsWidth + '\'' +
                        ",visible_bounds_offset = '" + visibleBoundsOffset + '\'' +
                        "}";
    }
}
