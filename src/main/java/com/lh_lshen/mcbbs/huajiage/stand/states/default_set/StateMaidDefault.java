package com.lh_lshen.mcbbs.huajiage.stand.states.default_set;

import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class StateMaidDefault extends StandStateBase {
    public StateMaidDefault() {
    }

    public StateMaidDefault(String stand, String stateName, boolean isHandPlay, boolean soundLoop) {
        super(stand, stateName, isHandPlay, soundLoop);
    }

    @Override
    public void doTask(EntityLivingBase user) {
        if(!user.isPotionActive(MobEffects.LUCK)||user.isPotionActive(MobEffects.LUCK)&&user.getActivePotionEffect(MobEffects.LUCK).getDuration()<10){
            user.addPotionEffect(new PotionEffect(MobEffects.LUCK, 20*5));
        }
    }

    @Override
    public void doTaskOutOfTime(EntityLivingBase user) {
        user.addPotionEffect(new PotionEffect(PotionLoader.potionStand , 5*20  ));
        user.addPotionEffect(new PotionEffect(MobEffects.HUNGER , 5*20 , 1));
    }
}
