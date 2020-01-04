package com.lh_lshen.mcbbs.huajiage.init.playsound;

import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public class StandMovingSound extends HuajiMovingSound {

	private float backSound;  
	private int ticks;
	public StandMovingSound(Entity s_entity, SoundEvent sound, SoundCategory category) {
		super(s_entity, sound, category);
	}
	public  void setSound(SoundEvent sound) {
		this.positionedSoundLocation = sound.getSoundName();
	}
	
	public float getBackSound() {
		return backSound;
	}
	public void setBackSound(float backSound) {
		this.backSound = backSound;
	}
	
	@Override
    public void update() {
        this.xPosF = (float) ENTITY.posX;
        this.yPosF = (float) ENTITY.posY;
        this.zPosF = (float) ENTITY.posZ;
        if(ENTITY instanceof EntityLivingBase) {
        	EntityLivingBase entity = (EntityLivingBase) ENTITY;
        	if(!entity.isPotionActive(PotionLoader.potionStand)) {
        		this. ticks++;
        		setVolume(0f);
        		if(ticks>=5){
        			this.stop();
//        			System.out.println("stoped!!!");
//        			System.out.println(""+ticks);
        		}
        	}else {
        		if(backSound>0) {
        		setVolume(backSound);
        		}else {
        		setVolume(1f);
        		}
        	}
        }
    	
    }
	
}
