package com.lh_lshen.mcbbs.huajiage.client.model.stand;

import com.google.common.collect.Maps;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.api.HuajiAgeAPI;
import com.lh_lshen.mcbbs.huajiage.api.IStandState;
import com.lh_lshen.mcbbs.huajiage.client.model.pojo.EntityModelJson;
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
        initModel();
        initCustomModel();
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
//                if(getModel(id) instanceof EntityModelJson){
                    return Objects.requireNonNull(getModel(id)).clone();
//                }
//                return Objects.requireNonNull(getModel(id)).getClass().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void reloadStandModels(){
        STAND_MODEL.clear();
        ID_TEX_MAP.clear();
        initModel();
        initCustomModel();
    }

    private static void initModel(){
        addStandModel("huajiage:the_world_default",new ModelTheWorld());
        addStandModel("huajiage:star_platinum_default",new ModelStarPlatinum());
        addStandModel("huajiage:hierophant_green_default",new ModelHierophantGreen());
        addStandModel("huajiage:killer_queen_default",new ModelKillerQueen());
        addStandModel("huajiage:orga_requiem_default",new ModelOrgaRequiem());

        addStandModel("huajiage:the_world_idle",new ModelTheWorldIdle());
        addStandModel("huajiage:star_platinum_idle",new ModelStarPlatinumIdle());
        addStandModel("huajiage:hierophant_green_idle",new ModelHierophantGreenIdle());
        addStandModel("huajiage:killer_queen_punch",new ModelKillerQueenPunch());
        addStandModel("huajiage:orga_requiem_fly",new ModelOrgaFly());

        List<IStandState> list = HuajiAgeAPI.getStandStateList();
        if (list!=null) {
            for(int i = 0; i< list.size(); i++) {
                StandStateBase state = StandStates.getStateByIndex(i);
                addTexture(state.getModelID(),state.getTex());
            }
        }

    }

    private static void initCustomModel(){
        CustomResourceLoader.reloadResources();
        Set<String> CUSTOM_MODEL = CustomResourceLoader.STAND_MODEL.getModelIdSet();
        if (!CUSTOM_MODEL.isEmpty()) {
            for(String id : CUSTOM_MODEL){
                if (CustomResourceLoader.STAND_MODEL.getInfo(id).isPresent() && CustomResourceLoader.STAND_MODEL.getModel(id).isPresent()) {
                    StandModelInfo info = CustomResourceLoader.STAND_MODEL.getInfo(id).get();
                    EntityModelJson json = CustomResourceLoader.STAND_MODEL.getModel(id).get();
                    String id1 = info.getModelId().toString();
                    String id2 = info.getState();
                    addStandModel(id1+"_"+id2,json);
                    addTexture(id1+"_"+id2,info.getTexture());
                }
            }
        }
        for(String id:STAND_MODEL.keySet()){
        HuajiAge.LOGGER.info(MarkerManager.getMarker("ResourcesLoader"), "Loaded model: {}",id);
        }
    }

}
