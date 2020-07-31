package com.lh_lshen.mcbbs.huajiage.client.model.stand;

import com.google.common.collect.Maps;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.api.HuajiAgeAPI;
import com.lh_lshen.mcbbs.huajiage.api.IStandState;
import com.lh_lshen.mcbbs.huajiage.client.model.custom.ModelStandJson;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.states.ModelHierophantGreenIdle;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.states.ModelKillerQueenPunch;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.states.ModelStarPlatinumIdle;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.states.ModelTheWorldIdle;
import com.lh_lshen.mcbbs.huajiage.client.resources.CustomResourceLoader;
import com.lh_lshen.mcbbs.huajiage.client.resources.pojo.StandModelInfo;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.stand.StandStates;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.MarkerManager;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class HAModelFactory {
    public static HashMap<String,ModelStandBase> STAND_MODEL = Maps.newHashMap();
    public static HashMap<String, ResourceLocation> ID_TEX_MAP = Maps.newHashMap();

    public HAModelFactory() {
        loadModel();
        loadCustomModel();
//    if(CommonProxy.ModsLoader.isTouhouMaidLoaded()){
//        loadMaidModel();
//    }
        for(String id:STAND_MODEL.keySet()){
            HuajiAge.LOGGER.info(MarkerManager.getMarker("ResourcesLoader"), "Loaded model: {}",id);
        }
    }

    public static void addStandModel(String id, ModelStandBase model){
        HAModelFactory.STAND_MODEL.put(id,model);
    }

    public static void addTexture(String id, ResourceLocation res){
        HAModelFactory.ID_TEX_MAP.put(id,res);
    }

    public static ModelStandBase getModel(String id){
        return hasModel(id)?STAND_MODEL.get(id):null;
    }

    public static boolean hasModel(String id){
        return STAND_MODEL.containsKey(id)&&STAND_MODEL.get(id)!=null;
    }

    public static ResourceLocation getTexture(String id){
        return ID_TEX_MAP.getOrDefault(id, HuajiConstant.StandTex.TEXTRUE_THE_WORLD);
    }

    public static ModelStandBase newInstance(String id){
        if (hasModel(id)&&getModel(id)!=null) {
            try {
                if(getModel(id) instanceof ModelStandJson){
                return Objects.requireNonNull(getModel(id)).clone();
                }
                return Objects.requireNonNull(getModel(id)).getClass().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void reloadStandModels(){
        STAND_MODEL.clear();
        ID_TEX_MAP.clear();

        loadModel();
        loadCustomModel();

        for(String id:STAND_MODEL.keySet()){
            HuajiAge.LOGGER.info(MarkerManager.getMarker("ResourcesLoader"), "Loaded model: {}",id);
        }
    }

    private static void loadModel(){
        addStandModel("huajiage:the_world_default",new ModelTheWorld().setModelID("huajiage:the_world_default"));
        addStandModel("huajiage:star_platinum_default",new ModelStarPlatinum().setModelID("huajiage:star_platinum_default"));
        addStandModel("huajiage:hierophant_green_default",new ModelHierophantGreen().setModelID("huajiage:hierophant_green_default"));
        addStandModel("huajiage:killer_queen_default",new ModelKillerQueen().setModelID("huajiage:killer_queen_default"));
        addStandModel("huajiage:orga_requiem_default",new ModelOrgaRequiem().setModelID("huajiage:orga_requiem_default"));

        addStandModel("huajiage:the_world_idle",new ModelTheWorldIdle().setModelID("huajiage:the_world_idle"));
        addStandModel("huajiage:star_platinum_idle",new ModelStarPlatinumIdle().setModelID("huajiage:star_platinum_idle"));
        addStandModel("huajiage:hierophant_green_idle",new ModelHierophantGreenIdle().setModelID("huajiage:hierophant_green_idle"));
        addStandModel("huajiage:killer_queen_punch",new ModelKillerQueenPunch().setModelID("huajiage:killer_queen_punch"));
        addStandModel("huajiage:orga_requiem_fly",new ModelOrgaFly().setModelID("huajiage:orga_requiem_fly"));

        List<IStandState> list = HuajiAgeAPI.getStandStateList();
        if (list!=null) {
            for(int i = 0; i< list.size(); i++) {
                StandStateBase state = StandStates.getStateByIndex(i);
                if (state.getModelID()!=null) {
                    addTexture(state.getModelID(),state.getTex());
                }
            }
        }

    }

    private static void loadCustomModel(){
        CustomResourceLoader.reloadResources();
        Set<String> CUSTOM_MODEL = CustomResourceLoader.STAND_MODEL.getModelIdSet();
        if (!CUSTOM_MODEL.isEmpty()) {
            for(String id : CUSTOM_MODEL){
                if (CustomResourceLoader.STAND_MODEL.getInfo(id).isPresent() && CustomResourceLoader.STAND_MODEL.getModel(id).isPresent()) {
                    StandModelInfo info = CustomResourceLoader.STAND_MODEL.getInfo(id).get();
                    ModelStandJson json = CustomResourceLoader.STAND_MODEL.getModel(id).get();
                    String modelID = info.getModelId().toString();
                    addStandModel(modelID,json.setModelID(modelID));
                    addTexture(modelID,info.getTexture());
                }
            }
        }

    }

//    @Optional.Method(modid = "touhou_little_maid")
//    private static void loadMaidModel(){
//        CustomResourceLoader.reloadResources();
//        Set<String> CUSTOM_MODEL = CustomResourcesLoader.MAID_MODEL.getModelIdSet();
//        if (!CUSTOM_MODEL.isEmpty()) {
//            for(String id : CUSTOM_MODEL){
//                if (CustomResourcesLoader.MAID_MODEL.getInfo(id).isPresent() && CustomResourcesLoader.MAID_MODEL.getModel(id).isPresent()) {
//                    MaidModelInfo info = CustomResourcesLoader.MAID_MODEL.getInfo(id).get();
//                    EntityModelJson json = CustomResourcesLoader.MAID_MODEL.getModel(id).get();
//                    String modelID = info.getModelId().toString()+"_default";
//                    addStandModel(modelID,new ModelStandJson(json).setModelID(modelID));
//                    addTexture(modelID,info.getTexture());
//                }
//            }
//        }
//    }

}
