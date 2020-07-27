package com.lh_lshen.mcbbs.huajiage.client.model.pojo;
/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */
import com.google.gson.annotations.SerializedName;

public class CustomModelPOJO {
    @SerializedName("format_version")
    private String formatVersion;

    @SerializedName("geometry.model")
    private GeometryModel geometryModel;

    public String getFormatVersion() {
        return formatVersion;
    }

    public GeometryModel getGeometryModel() {
        return geometryModel;
    }

    @Override
    public String toString() {
        return
                "CustomModelPOJO{" +
                        "format_version = '" + formatVersion + '\'' +
                        ",geometry.model = '" + geometryModel + '\'' +
                        "}";
    }
}
