package com.lh_lshen.mcbbs.huajiage.init.sound;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;

import com.lh_lshen.mcbbs.huajiage.stand.StandStates;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.entity.EntityStandBase;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
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
        if(ENTITY.isDead) {
        	this.stop();
        }
        if(ENTITY instanceof EntityStandBase){
            EntityStandBase base = (EntityStandBase) ENTITY;
            EntityLivingBase user = base.getUser();
            if(user !=null){
                IExposedData data = StandUtil.getStandData(user);
                StandStateBase state = StandStates.getStandState(data.getStand(),data.getState());
                if (this.repeat&& state!=null) {
                    if(data!=null&&!state.isSoundLoop()){
                        this.setVolume(0f);
                    }else{
                        this.setVolume(0.7f);
                    }
                }
                if(state==null){
                    this.stop();
                }
            }
        }
    	
    }

}