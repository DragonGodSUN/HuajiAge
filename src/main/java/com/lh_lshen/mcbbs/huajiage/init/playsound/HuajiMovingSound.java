package com.lh_lshen.mcbbs.huajiage.init.playsound;

import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;

import net.minecraft.client.audio.MovingSound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class HuajiMovingSound extends MovingSound {
    
    public final Entity ENTITY;
//    private boolean isPlaying = true; 

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

//    public boolean isPlaying() {
//		return isPlaying;
//	}
//
//	public void setPlaying(boolean isPlaying) {
//		this.isPlaying = isPlaying;
//	}

	@Override
    public void update() {
        this.xPosF = (float) ENTITY.posX;
        this.yPosF = (float) ENTITY.posY;
        this.zPosF = (float) ENTITY.posZ;
    	
    }

}