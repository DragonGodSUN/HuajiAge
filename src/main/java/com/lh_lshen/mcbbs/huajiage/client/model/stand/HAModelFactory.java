package com.lh_lshen.mcbbs.huajiage.client.model.stand;

import com.google.common.collect.Maps;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.states.ModelHierophantGreenIdle;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.states.ModelKillerQueenPunch;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.states.ModelStarPlatinumIdle;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.states.ModelTheWorldIdle;

import java.util.HashMap;

public class HAModelFactory {
    public static HashMap<String,ModelStandBase> STAND_MODEL = Maps.newHashMap();

    public HAModelFactory() {
        addStandModel("huajiage:the_world",new ModelTheWorld());
        addStandModel("huajiage:star_platinum",new ModelStarPlatinum());
        addStandModel("huajiage:hierophant_green",new ModelHierophantGreen());
        addStandModel("huajiage:killer_queen",new ModelKillerQueen());
        addStandModel("huajiage:orga_requiem",new ModelOrgaRequiem());

        addStandModel("huajiage:the_world_idle",new ModelTheWorldIdle());
        addStandModel("huajiage:star_platinum_idle",new ModelStarPlatinumIdle());
        addStandModel("huajiage:hierophant_green_idle",new ModelHierophantGreenIdle());
        addStandModel("huajiage:killer_queen_punch",new ModelKillerQueenPunch());
        addStandModel("huajiage:orga_requiem_fly",new ModelOrgaFly());
        System.out.println("====================model load==================");
    }

    public static void addStandModel(String id, ModelStandBase model){
        HAModelFactory.STAND_MODEL.put(id,model);
    }

    public static ModelStandBase get(String id){
        return has(id)?STAND_MODEL.get(id):null;
    }

    public static boolean has(String id){
        return STAND_MODEL.containsKey(id)&&STAND_MODEL.get(id)!=null;
    }

    public static ModelStandBase newInstance(String id){
        if (has(id)) {
            try {
                return get(id).getClass().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
