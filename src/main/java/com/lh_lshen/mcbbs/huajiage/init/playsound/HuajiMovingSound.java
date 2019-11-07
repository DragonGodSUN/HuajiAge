package com.lh_lshen.mcbbs.huajiage.init.playsound;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class HuajiMovingSound extends MovingSound {
    
    public final Entity ENTITY;

    public HuajiMovingSound(Entity s_entity, SoundEvent sound, SoundCategory category) {
        super(sound, category);
        ENTITY = s_entity;
        
        update();
    }
    
    public HuajiMovingSound setVolume(float volume) {
        this.volume = volume;
        return this;
    }
    
    public HuajiMovingSound setLoop() {
        this.repeat = true;
        return this;
    }
    
    public void stop() {
        this.donePlaying = true;
    }

    @Override
    public void update() {
        this.xPosF = (float) ENTITY.posX;
        this.yPosF = (float) ENTITY.posY;
        this.zPosF = (float) ENTITY.posZ;
    }

}