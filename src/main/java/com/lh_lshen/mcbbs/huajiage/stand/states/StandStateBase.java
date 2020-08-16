package com.lh_lshen.mcbbs.huajiage.stand.states;

import com.google.common.collect.Lists;
import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.api.IStandState;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public abstract class StandStateBase implements IStandState {
    protected String stand;
    protected String stateName;
    protected String ID = HuajiConstant.StandModels.DEFAULT_MODEL_ID;
    protected ResourceLocation tex= new ResourceLocation(HuajiAge.MODID,"textures/entity/entity_the_world_default.png");
    protected List<String> extraDatas = Lists.newArrayList();
    protected int stage;
    protected boolean isHandPlay;
    protected boolean soundLoop;


    public StandStateBase() {
    }

    public StandStateBase(String stand, String stateName, boolean isHandPlay, boolean soundLoop) {
        this.stand = stand;
        this.stateName = stateName;
        this.isHandPlay = isHandPlay;
        this.soundLoop = soundLoop;
        this.stage = 0;
        ID = HuajiAge.MODID+":"+stand+"_"+stateName;
        tex= new ResourceLocation(HuajiAge.MODID,"textures/entity/entity_"+stand+"_"+stateName+".png");
    }

    public StandStateBase(String stand, String stateName, boolean isHandPlay, boolean soundLoop, int stage) {
        this.stand = stand;
        this.stateName = stateName;
        this.isHandPlay = isHandPlay;
        this.soundLoop = soundLoop;
        this.stage = stage;
        ID = HuajiAge.MODID+":"+stand+"_"+stateName;
        tex= new ResourceLocation(HuajiAge.MODID,"textures/entity/entity_"+stand+"_"+stateName+".png");
    }

    public String getStand() {
        return stand;
    }

    public void setStand(String stand) {
        this.stand = stand;
    }

    public String getStateName() {
        return stateName;
    }

    @Override
    public String getModelID() {
        return ID;
    }

    @Override
    public ResourceLocation getTex() {
        return tex;
    }

    @Override
    public int getStage() {
        return stage;
    }

    public List<String> getExtraDatas() {
        return extraDatas;
    }

    public void addExtraData(String extraData) {
        this.extraDatas.add(extraData);
    }

    public boolean hasExtraData(String extraData){
        return extraData!=null && extraDatas.contains(extraData);
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setTex(ResourceLocation tex) {
        this.tex = tex;
    }

    public boolean isHandPlay() {
        return isHandPlay;
    }

    public void setHandPlay(boolean handPlay) {
        isHandPlay = handPlay;
    }

    @Override
    public boolean isSoundLoop() {
        return soundLoop;
    }

    public void setSoundLoop(boolean soundLoop) {
        this.soundLoop = soundLoop;
    }

    @Override
    public void doTaskOutOfTime(EntityLivingBase user) {
        user.addPotionEffect(new PotionEffect(PotionLoader.potionStand , 5*20  ));
        user.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS , 5*20, 1 ));
        user.addPotionEffect(new PotionEffect(MobEffects.HUNGER , 5*20 , ConfigHuaji.Stands.allowStandPunish? 24 : 49 ));
        if(ConfigHuaji.Stands.allowStandPunish) {
            user.addPotionEffect(new PotionEffect(MobEffects.WITHER , 5*20 , 1 ));
        }
    }

//    public void loadCustomTex(){
//        if(HAModelFactory.getModel(getModelID()) instanceof EntityModelJson){
//            CustomResourceLoader.STAND_MODEL.getInfo(getModelID()).ifPresent(info->this.tex=info.getTexture());
//            HuajiAge.LOGGER.info(MarkerManager.getMarker("ResourcesLoader"), "Loaded model[texture]: {}",tex);
//            HuajiAge.LOGGER.info(MarkerManager.getMarker("ResourcesLoader"), "Loaded model[texture]: {}",CustomResourceLoader.STAND_MODEL.getInfo(getModelID()).isPresent());
//        }
//    }
}
