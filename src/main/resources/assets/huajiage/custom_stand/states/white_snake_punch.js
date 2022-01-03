var Helper = Java.type("com.lh_lshen.mcbbs.huajiage.stand.helper.StandPowerHelper");

Java.asJSONCompatible({
//  替身ID-推荐格式："[作者/作品id]：替身ID"
    stand:"huajiage:white_snake",
//  替身的状态ID,不写时默认为“default”，必须存在一个id为“default”的state
//  若stateId带有'%custom'的后缀，模型ID不需要给json文件加上后缀
    stateId:"punch",
//  替身状态名称,若只有一个状态，可不写 -推荐格式：“stand.[替身ID].[替身状态]”
    stateKey:"stand.huajiage.white_snake.punch",
//  模型ID
    modelId:"huajiage:white_snake",
//  一些属性标签，便于使用mod内置的能力
    stateTags:["disc_deprive"],
//  替身放出时，第一人称是否显示手臂，不写时默认为true
    hand:false,
//  替身放出时，是否有重复音乐播放
    soundRepeat:true,
//  可解锁该状态的替身等级，不写时默认为0
    stage:0,

/**
 * 替身放出时始终执行的方法
 * @param world 当前所处的世界
 * @param entity 替身使者
 */
    update: function (worldWrapper,entityWrapper,dataWrapper) {
    var level = dataWrapper.getStage();
          //范围攻击 参数（玩家，可攻击角度，伤害，攻击距离）
          Helper.rangePunchAttack(entityWrapper.getLivingBase(),60,5 + 5*dataWrapper.getStage(),1 + dataWrapper.getStage());
    },

/**
 * 替身放出超时执行的方法
 * @param world 当前所处的世界
 * @param entity 替身使者
 */
    timeOut: function (worldWrapper,entityWrapper,dataWrapper) {
       Helper.increaseStandTime(entityWrapper.getLivingBase(),200);
       Helper.potionEffectAdd(entityWrapper.getLivingBase(),"minecraft:hunger",200,5);
       Helper.potionEffectAdd(entityWrapper.getLivingBase(),"minecraft:slowness",200,2);

    },
    /**
     * 替身技能
     * @param world 当前所处的世界
     * @param entity 替身使者
     */
    capability: function (worldWrapper,entityWrapper,dataWrapper) {

        var item_main_hand = Helper.getPlayerHoldItem(entityWrapper.getLivingBase(),true);
        var item_off_hand = Helper.getPlayerHoldItem(entityWrapper.getLivingBase(),false);


    }

});
