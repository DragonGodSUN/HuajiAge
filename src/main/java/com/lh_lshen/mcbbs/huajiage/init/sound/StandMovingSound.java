package com.lh_lshen.mcbbs.huajiage.init.sound;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;

public class StandMovingSound extends HuajiMovingSound {

//	private float backSound;  
//	private int ticks;
//	private int delay;
//	private int delayTicks;
	public StandMovingSound(Entity s_entity, SoundEvent sound, SoundCategory category) {
		super(s_entity, sound, category);
	}
	public  void setSound(SoundEvent sound) {
		this.positionedSoundLocation = sound.getSoundName();
	}
	
//	public float getBackSound() {
//		return backSound;
//	}
//	public void setBackSound(float backSound) {
//		this.backSound = backSound;
//	}

	
	@Override
    public void update() {
        this.xPosF = (float) ENTITY.posX;
        this.yPosF = (float) ENTITY.posY;
        this.zPosF = (float) ENTITY.posZ;
        if(ENTITY.isDead) {
        	this.stop();
        }
//        if(!(ENTITY instanceof EntityPlayer)) {
//        }
//        else{
//        	EntityPlayer entity = (EntityPlayer) ENTITY;
//        	this. ticks++;
//        	if(!entity.isPotionActive(PotionLoader.potionStand)) {
//        		setVolume(0f);
//        		if(ticks>=10){
//        			this.stop();
//        		}
//        	}else {
//        		if(backSound>0) {
//	        		if(delayTicks<delay) {
//	        			setVolume(0f);
//	        			this.delayTicks++;
//		        		}else {
//		        			setVolume(backSound);
//		        		}
//        		}else {
//        			setVolume(1f);
//        		}
//        	}
//        }
    	
    }
	
}
