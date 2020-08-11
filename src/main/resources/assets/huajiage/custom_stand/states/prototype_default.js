var Helper = Java.type("com.lh_lshen.mcbbs.huajiage.stand.helper.StandPowerHelper");

Java.asJSONCompatible({
//  替身ID-推荐格式："[作者/作品id]：替身ID"
    stand:"huajiage:prototype",
//  替身的状态ID
    stateId:"default",
//  替身状态名称,若只有一个状态，可不写-推荐格式：“stand.[替身ID].[替身状态].name”
    stateKey:"stand.huajiage.prototype.default.name",
//  模型ID
    modelId:"huajiage:star_platinum",
//  替身放出时，第一人称是否显示手臂
    hand:true,
//  可解锁该状态的替身等级
    stage:0,

/**
 * 替身放出时始终执行的方法
 * @param world 当前所处的世界
 * @param entity 替身使者
 */
    update: function (world,entity) {
//        helper.rangePunchAttack(entity,90f,10f,2f);
          Helper.MPCharge(entity.getLivingBase(),300);
    },

/**
 * 替身放出超时执行的方法
 * @param world 当前所处的世界
 * @param entity 替身使者
 */
    timeOut: function (world,entity) {
        Helper.potionDefaultOutOfTime(entity.getLivingBase());
    },
    /**
     * 替身技能
     * @param world 当前所处的世界
     * @param entity 替身使者
     */
    capability: function (world,entity) {

    }

});
