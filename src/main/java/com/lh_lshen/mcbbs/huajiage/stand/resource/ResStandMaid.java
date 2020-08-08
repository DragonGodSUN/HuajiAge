package com.lh_lshen.mcbbs.huajiage.stand.resource;

import com.github.tartaricacid.touhoulittlemaid.init.MaidSoundEvent;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.client.model.custom.ModelStandJson;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.HAModelFactory;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.client.resources.CustomResourceLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.Optional;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ResStandMaid extends StandRes {
    private List<Object> mainAnimations;

    public ResStandMaid() {
    }

    public ResStandMaid(String name) {
        super(name);
    }

    @Override
    public ModelStandBase getStandModelByData(EntityLivingBase user) {
        IExposedData data = StandUtil.getStandData(user);
        if(data!=null){
            return StandClientUtil.getModelByID(data.getModel());
        }
        return super.getStandModelByData(user);
    }

    @Override
    public ResourceLocation getTextureByData(EntityLivingBase user) {
        return super.getTextureByData(user);
    }

    @Override
    @Optional.Method(modid = "touhou_little_maid")
    public void doSoundPlay(Minecraft mc, Entity entity, EntityLivingBase user) {
        List<SoundEvent> sounds = Arrays.asList(MaidSoundEvent.MAID_IDLE, MaidSoundEvent.MAID_ATTACK, MaidSoundEvent.MAID_DANMAKU_ATTACK,
                MaidSoundEvent.MAID_FIND_TARGET,MaidSoundEvent.MAID_SNOW,MaidSoundEvent.MAID_FIND_TARGET);
        int size = sounds.size();
        int index = (int) MathHelper.nextFloat(new Random(), 0, size);
        if (index < size) {
            SoundEvent sound = sounds.get(index);
            mc.getSoundHandler().playSound(HuajiSoundPlayer.getMovingSound(entity, sound, SoundCategory.NEUTRAL, 2f));
        }
    }

    @Override
    @Optional.Method(modid = "touhou_little_maid")
    public void doStandRender(EntityLivingBase entity) {
//        GlStateManager.pushMatrix();
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
            GlStateManager.translate(0,0,-30);
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
