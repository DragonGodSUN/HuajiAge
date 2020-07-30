package com.lh_lshen.mcbbs.huajiage.client.resources;
/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */

import com.lh_lshen.mcbbs.huajiage.client.model.custom.ModelStandJson;
import com.lh_lshen.mcbbs.huajiage.client.resources.pojo.CustomModelPack;
import com.lh_lshen.mcbbs.huajiage.client.resources.pojo.StandModelInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class CustomStandModelResources {
    private String jsonFileName;
    private List<CustomModelPack<StandModelInfo>> packList;
    private HashMap<String, ModelStandJson> idModelMap;
    private HashMap<String, StandModelInfo> idInfoMap;
    private HashMap<String, List<Object>> idAnimationMap;

    public CustomStandModelResources(String jsonFileName, List<CustomModelPack<StandModelInfo>> packList,
                                     HashMap<String, ModelStandJson> idToModel,
                                     HashMap<String, StandModelInfo> idToInfo,
                                     HashMap<String, List<Object>> idAnimationMap) {
        this.jsonFileName = jsonFileName;
        this.packList = packList;
        this.idModelMap = idToModel;
        this.idInfoMap = idToInfo;
        this.idAnimationMap = idAnimationMap;
    }

    public void clearAll() {
        this.packList.clear();
        this.idModelMap.clear();
        this.idInfoMap.clear();
        this.idAnimationMap.clear();
    }

    public String getJsonFileName() {
        return jsonFileName;
    }

    public List<CustomModelPack<StandModelInfo>> getPackList() {
        return packList;
    }

    public Set<String> getModelIdSet() {
        return idInfoMap.keySet();
    }

    public void addPack(CustomModelPack<StandModelInfo> pack) {
        this.packList.add(pack);
    }

    public void putModel(String modelId, ModelStandJson modelJson) {
        this.idModelMap.put(modelId, modelJson);
    }

    public void putInfo(String modelId, StandModelInfo maidModelItem) {
        this.idInfoMap.put(modelId, maidModelItem);
    }

    public void putAnimation(String modelId, List<Object> animationJs) {
        this.idAnimationMap.put(modelId, animationJs);
    }


    public Optional<ModelStandJson> getModel(String modelId) {
        return Optional.ofNullable(idModelMap.get(modelId));
    }


    public Optional<List<Object>> getAnimation(String modelId) {
        return Optional.ofNullable(idAnimationMap.get(modelId));
    }

    public void removeAnimation(String modelId) {
        idAnimationMap.remove(modelId);
    }

    public Optional<StandModelInfo> getInfo(String modelId) {
        return Optional.ofNullable(idInfoMap.get(modelId));
    }

}
