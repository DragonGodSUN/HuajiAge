package com.lh_lshen.mcbbs.huajiage.stand.states.various;

import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.init.loaders.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.helper.StandPowerHelper;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class StateOrgaRequiemFly extends StandStateBase {
    public StateOrgaRequiemFly() {
    }

    public StateOrgaRequiemFly(String stand, String stateName, boolean isHandPlay, boolean soundLoop) {
        super(stand, stateName, isHandPlay, soundLoop);
        this.addExtraData("undead");
        this.addExtraData("fly");
    }

    @Override
    public void doTask(EntityLivingBase user) {
        StandBase type = StandUtil.getType(user);
        if(type!=null){
        StandPowerHelper.rangePunchAttack(user,45,user.getMaxHealth()/2,2);
        }
        if(!user.isPotionActive(MobEffects.INVISIBILITY)||user.isPotionActive(MobEffects.INVISIBILITY)&&user.getActivePotionEffect(MobEffects.INVISIBILITY).getDuration()<10){
            user.addPotionEffect(new PotionEffect(MobEffects.INVISIBILITY, 20*5));
        }
    }

    @Override
    public void doTaskOutOfTime(EntityLivingBase user) {
        user.addPotionEffect(new PotionEffect(PotionLoader.potionStand , 5*20  ));
        user.addPotionEffect(new PotionEffect(MobEffects.SPEED , 5*20, 2 ));
        user.addPotionEffect(new PotionEffect(MobEffects.HUNGER , 5*20 , ConfigHuaji.Stands.allowStandPunish? 24 : 49 ));
    }
}
