var Helper = Java.type("com.lh_lshen.mcbbs.huajiage.stand.helper.StandPowerHelper");

Java.asJSONCompatible({
//  替身ID-推荐格式："[作者/作品id]：替身ID"
    stand:"huajiage:bike_wheel",
//  替身的状态ID,不写时默认为“default”，必须存在一个id为“default”的state
//  若stateId带有'%custom'的后缀，模型ID不需要给json文件加上后缀
    stateId:"default",
//  替身状态名称,若只有一个状态，可不写 -推荐格式：“stand.[替身ID].[替身状态]”
    stateKey:"stand.huajiage.bike_wheel.default",
//  模型ID
//  如果stateId不为空或“default”，需为模型json文件加上'_stateId'的后缀,如：maid_x_idle.json
//  若stateId带有'%custom'的后缀，可无视state变化自定义模型 注：自定义模型载入游戏后的modelId都是[模型ID]+"_"+[stateId]
//  如：huajiage:star_platinum_idle,所有车万女仆模型id均为"[模型ID]_default"
    modelId:"huajiage:bike_standard",
//  替身放出时，第一人称是否显示手臂，不写时默认为true
    hand:true,
//  可解锁该状态的替身等级，不写时默认为0
    stage:0,
//  有关属性的标签
    stateTags:["ride"],

/**
 * 替身放出时始终执行的方法
 * @param world 当前所处的世界
 * @param entity 替身使者
 */
    update: function (worldWrapper,entityWrapper,dataWrapper) {
        //范围攻击 参数（玩家，可攻击角度，伤害，攻击距离）
        Helper.rangePunchAttack(entityWrapper.getLivingBase(),25,10,1);
        Helper.MPCharge(entityWrapper.getLivingBase(),30);
    },

/**
 * 替身放出超时执行的方法
 * @param world 当前所处的世界
 * @param entity 替身使者
 */
    timeOut: function (worldWrapper,entityWrapper,dataWrapper) {
        //默认超时效果
        Helper.potionDefaultOutOfTime(entityWrapper.getLivingBase());


    },
    /**
     * 替身技能
     * @param world 当前所处的世界
     * @param entity 替身使者
     */
    capability: function (worldWrapper,entityWrapper,dataWrapper) {
//        var pos = entityWrapper.getPos();
        var vec = entityWrapper.getLookVec();
//        //时间暂停 参数（玩家，时停时间（单位：tick)，时停范围）
//        Helper.setTimeStop(entityWrapper.getLivingBase(),100,100);
        Helper.removeBadPotion(entityWrapper.getLivingBase());
        Helper.increaseStandTime(entityWrapper.getLivingBase(),500);
        Helper.potionEffectAdd(entityWrapper.getLivingBase(),"minecraft:luck",250,2);
        Helper.potionEffectAdd(entityWrapper.getLivingBase(),"minecraft:regeneration",250,1);
        Helper.potionEffectAdd(entityWrapper.getLivingBase(),"minecraft:resistance",250,1);
        Helper.playSound(entityWrapper.getLivingBase(), "huajiage:bike_ring_ear0", 5, 1);
    }

});
