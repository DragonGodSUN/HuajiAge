package com.lh_lshen.mcbbs.huajiage.stand;

import com.google.common.collect.Lists;
import com.lh_lshen.mcbbs.huajiage.api.HuajiAgeAPI;
import com.lh_lshen.mcbbs.huajiage.api.IStandState;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;

import java.util.List;

public class StandStates {
//    public static final List<StandStateBase> STAND_STATE_LIST = new ArrayList<>();
//    public static final StandStateBase STATE_DEFAULT_THE_WORLD = new StateTheWorldDefault();
//
//    public StandStates() {
//        registerState(STATE_DEFAULT_THE_WORLD);
//        for(StandStateBase state: STAND_STATE_LIST) {
//            HuajiAgeAPI.registerStandState(state);
//        }
//    }
//
//    private static void registerState(StandStateBase stand){
//        STAND_STATE_LIST.add(stand);
//    }

    public static StandStateBase getStateByIndex(int index)
    {
        List<IStandState> list = HuajiAgeAPI.getStandStateList();
        if (list.get(index) instanceof StandStateBase) {
            StandStateBase state = (StandStateBase) list.get(index);
            return state;
        } else {
            return null;
        }

    }
    public static List<StandStateBase> getStandStateListByStand(String name)
    {
        List<StandStateBase> s= Lists.newArrayList();
        List<IStandState> list = HuajiAgeAPI.getStandStateList();
        if (list!=null) {
            for(int i = 0; i< list.size(); i++) {
                if(getStateByIndex(i).getStand().equals(name)) {
                    s.add(getStateByIndex(i));
                }
            }
        }
        return s;
    }

    public static StandStateBase getStandState(String stand,String state)
    {
        List<IStandState> list = HuajiAgeAPI.getStandStateList();
        if (list!=null) {
            for(int i = 0; i< list.size(); i++) {
                if(getStateByIndex(i).getStand().equals(stand) &&
                        getStateByIndex(i).getStateName().equals(state)) {
                    return (getStateByIndex(i));
                }
            }
        }
        return null;
    }

//    public static void reloadTexture(){
//        List<IStandState> list = HuajiAgeAPI.getStandStateList();
//        if (list!=null) {
//            for(int i = 0; i< list.size(); i++) {
//                StandStateBase state = getStateByIndex(i);
//                state.loadCustomTex();
//            }
//        }
//    }

}
