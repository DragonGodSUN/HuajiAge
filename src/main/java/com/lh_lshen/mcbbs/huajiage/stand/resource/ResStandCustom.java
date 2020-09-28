package com.lh_lshen.mcbbs.huajiage.stand.resource;

import com.google.common.collect.Lists;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.client.model.custom.ModelStandJson;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.client.resources.CustomResourceLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.custom.StandCustomInfo;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.List;
import java.util.Random;

public class ResStandCustom extends StandRes {
    private StandCustomInfo info;
    private List<Object> mainAnimations;

    public ResStandCustom() {
        super();
    }

    public ResStandCustom(StandCustomInfo info) {
        this.name = info.getStand();
        this.info = info;
    }

    @Override
    public void doSoundPlay(Minecraft mc, Entity entity, EntityLivingBase user) {
        List<SoundEvent> sounds = Lists.newArrayList();
        for(String s : info.getSounds()){
            if(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(s)) !=null){
            sounds.add(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(s)));
            }
        }
        if(sounds.isEmpty()){
            return;
        }
        IExposedData data = StandUtil.getStandData(user);
        int size = sounds.size();
        int index = (int) MathHelper.nextFloat(new Random(), 0, size);
        if(null!=data&&index<size) {
            SoundEvent sound = sounds.get(index);
            mc.getSoundHandler().playSound(HuajiSoundPlayer.getMovingSound(entity, sound, SoundCategory.NEUTRAL, 1f));
        }
    }

    @Override
    public void doStandRender(EntityLivingBase entity) {
        IExposedData data = StandUtil.getStandData(entity);
        if(data==null){
            return;
        }
        ResourceLocation STAND_TEX = StandClientUtil.getTexByID(entity);
        ModelStandBase model = StandClientUtil.getModelByData(entity, StandLoader.getStand(data.getStand()));
        if(model instanceof ModelStandJson){
            this.mainAnimations = null;
            CustomResourceLoader.STAND_MODEL.getAnimation(model.getModelID()).ifPresent(animations -> this.mainAnimations = animations);
            if (this.mainAnimations != null) {
                ((ModelStandJson) model).setAnimations(this.mainAnimations);
            }
        }
        Minecraft.getMinecraft().getTextureManager().bindTexture(STAND_TEX);
        model.setRotationAngles( entity.limbSwing, entity.limbSwingAmount, entity.ticksExisted, 0, 0, 1f, entity, 0.5f ,1.5f);
        GlStateManager.rotate(180,0,0,1);
        if(model instanceof ModelStandJson){
            List<Float> pos = ((ModelStandJson) model).info.getTransferFirst();
            model.renderFirst(pos.get(0),pos.get(1),pos.get(2),1f,0.6f);
        }else {
              model.renderFirst(0,-4,-3,1f,0.6f);
        }
            model.effect(entity, entity.limbSwing, entity.limbSwingAmount, entity.ticksExisted, entity.rotationYaw, entity.rotationPitch, 1f);
    }

}
