package com.lh_lshen.mcbbs.huajiage.stand.resource;

import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStarPlatinum;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiMovingSound;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundStand;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;

import java.util.List;
import java.util.Random;

public class ResStandStarPlatinum extends StandRes {
    public ResStandStarPlatinum() {
    }

    public ResStandStarPlatinum(String name) {
        super(name);
    }

    @Override
    public ResourceLocation getTexture() {
        return HuajiConstant.StandTex.TEXTRUE_STAR_PLATINUM;
    }

    @Override
    public void doSoundPlay(Minecraft mc, Entity entity, EntityLivingBase user) {
        List<SoundEvent> sounds = SoundStand.STAR_SOUND_LIST;
        int size = sounds.size();
        int index = (int) MathHelper.nextFloat(new Random(), 0, size);
        if (index < size) {
            SoundEvent sound = sounds.get(index);
            Minecraft.getMinecraft().getSoundHandler().playSound(HuajiSoundPlayer.getMovingSound(entity, sound, SoundCategory.NEUTRAL, 1f));
        }
        HuajiMovingSound back = new HuajiMovingSound(entity, SoundEvents.ENTITY_PLAYER_ATTACK_STRONG, SoundCategory.NEUTRAL);
        back.setVolume(0.7f);
        back.setLoop();
        mc.getSoundHandler().playSound(back);
    }

    @Override
    public void doStandRender(EntityLivingBase entity) {
        ResourceLocation STAND_TEX = getTexture();
        ModelStandBase model = getStandModelByData(entity);
        Minecraft.getMinecraft().getTextureManager().bindTexture(STAND_TEX);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240f, 240f);
        model.setRotationAngles(0, 0, entity.ticksExisted, 0, -1, 1, entity, 0.5f, (float) (StandLoader.THE_WORLD.getSpeed() * 1.5));
        model.effect(entity, 0, 0, entity.ticksExisted, 0, 0, 1f);
        if (entity.getActivePotionEffect(PotionLoader.potionStand).getDuration() < 40) {
            model.doHandRender(0, -1f, -0.75f, 1f, 0.3f);
        } else {
            model.doHandRender(0, -1f, -0.75f, 1f, 0.6f);
        }
    }

    @Override
    public ModelStandBase getStandModel() {
        return new ModelStarPlatinum();
    }

}
