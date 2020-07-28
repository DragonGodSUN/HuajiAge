package com.lh_lshen.mcbbs.huajiage.client.resources.pojo;
/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */

import com.google.gson.JsonSyntaxException;
import com.google.gson.annotations.SerializedName;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Collections;
import java.util.List;

public class CustomModelPack <T extends IModelInfo>{
    @SerializedName("date")
    private String date;

    @SerializedName("model_list")
    private List<T> modelList;

    @SerializedName("pack_name")
    private String packName;

    @SerializedName("author")
    private List<String> author;

    @SerializedName("description")
    private List<String> description;

    @SerializedName("version")
    private String version;

    @SerializedName("icon")
    private ResourceLocation icon;

    @Nullable
    public String getDate() {
        return date;
    }

    public List<T> getModelList() {
        return modelList;
    }

    public String getPackName() {
        return packName;
    }

    public List<String> getAuthor() {
        return author;
    }

    public List<String> getDescription() {
        return description;
    }

    @Nullable
    public String getVersion() {
        return version;
    }

    @Nullable
    public ResourceLocation getIcon() {
        return icon;
    }

    @SuppressWarnings("unchecked")
    public CustomModelPack<T> decorate() {
        // 包名和 model list 不能为空
        if (packName == null) {
            throw new JsonSyntaxException("Expected \"pack_name\" in pack");
        }
        if (modelList == null || modelList.isEmpty()) {
            throw new JsonSyntaxException("Expected \"model_list\" in pack");
        }
        if (description == null) {
            description = Collections.EMPTY_LIST;
        }
        if (author == null) {
            author = Collections.EMPTY_LIST;
        }
        // 为此包的模型对象进行二次修饰
        modelList.forEach(T::decorate);
        return this;
    }
}
