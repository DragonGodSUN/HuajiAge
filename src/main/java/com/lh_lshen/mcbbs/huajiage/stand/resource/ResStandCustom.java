package com.lh_lshen.mcbbs.huajiage.stand.resource;

import com.google.common.collect.Lists;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.client.model.custom.ModelStandJson;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.HAModelFactory;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.client.resources.CustomResourceLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;
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
            if(SoundLoader.getSound(s)!=null){
            sounds.add(SoundLoader.getSound(s));
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
        ResourceLocation STAND_TEX = HAModelFactory.getTexture(data.getModel());
        ModelStandBase model = StandClientUtil.getModelByID(data.getModel());
        Minecraft.getMinecraft().getTextureManager().bindTexture(STAND_TEX);
        if(model instanceof ModelStandJson){
            this.mainAnimations = null;
            CustomResourceLoader.STAND_MODEL.getAnimation(this.getName()).ifPresent(animations -> this.mainAnimations = animations);
            if (this.mainAnimations != null) {
                ((ModelStandJson) model).setAnimations(this.mainAnimations);
            }
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 0.5F);
            GlStateManager.rotate(180,0,0,1);
            model.setPosition();
            GlStateManager.translate(0,0,-5);
            model.setRotationAngles( entity.limbSwing, entity.limbSwingAmount, entity.ticksExisted, entity.rotationYaw, entity.rotationPitch, 1f, entity, 1f ,1.5f);
//            model.render(entity, entity.limbSwing, entity.limbSwingAmount, entity.ticksExisted, entity.rotationYaw, entity.rotationPitch, 0.06f);
            model.renderFirst(0,0,0,1f,1f);
            model.effect(entity, entity.limbSwing, entity.limbSwingAmount, entity.ticksExisted, entity.rotationYaw, entity.rotationPitch, 1f);
            if(data.getStage()>0) {
                model.extraEffect(entity,  entity.limbSwing, entity.limbSwingAmount, entity.ticksExisted, entity.rotationYaw, entity.rotationPitch, 1f);
            }
        }
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
    }

}
