package com.lh_lshen.mcbbs.huajiage.stand.states.default_set;

import com.google.common.collect.Lists;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.init.loaders.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.helper.StandPowerHelper;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

import java.util.List;

public class StateOrgaRequiemDefault extends StandStateBase {
    public StateOrgaRequiemDefault() {
    }

    public StateOrgaRequiemDefault(String stand, String stateName, boolean isHandPlay, boolean soundLoop) {
        super(stand, stateName, isHandPlay, soundLoop);
        this.addExtraData("undead");
    }

    @Override
    public void doTask(EntityLivingBase user) {
        StandPowerHelper.MPCharge(user,(int)(StandLoader.ORGA_REQUIEM.getCharge()/3));
    }

    @Override
    public void doTaskOutOfTime(EntityLivingBase user) {
        List<PotionEffect> effects = Lists.newArrayList();
        effects.add(new PotionEffect(PotionLoader.potionStand,5*20));
        effects.add(new PotionEffect(MobEffects.HUNGER,5*20,5));
        if(ConfigHuaji.Stands.allowStandGlow){
            effects.add(new PotionEffect(MobEffects.GLOWING,5*20));
        }
        StandPowerHelper.potionEffect(user,effects);
    }
}
