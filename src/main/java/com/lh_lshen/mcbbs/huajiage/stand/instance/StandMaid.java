package com.lh_lshen.mcbbs.huajiage.stand.instance;

import com.github.tartaricacid.touhoulittlemaid.init.MaidSoundEvent;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityExposedData;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandRes;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandResLoader;
import com.lh_lshen.mcbbs.huajiage.stand.states.default_set.StateMaidDefault;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class StandMaid extends StandBase {
    public StandMaid() {
    }

    public StandMaid(String name, float speed, float damage, int duration, float distance, int cost, int charge, String texPath, String localName, boolean displayHand) {
        super(name, speed, damage, duration, distance, cost, charge, texPath, localName, displayHand);
        initState(new StateMaidDefault(name, CapabilityExposedData.States.DEFAULT.getName(),isHandDisplay(),true));
    }

    @Override
    public StandRes getBindingRes() {
        return StandResLoader.MAID_RES;
    }

    @Override
    public void doStandCapability(EntityLivingBase user) {
        user.addPotionEffect(new PotionEffect(MobEffects.SPEED,300,2));
        user.addPotionEffect(new PotionEffect(MobEffects.REGENERATION,300));
        user.addPotionEffect(new PotionEffect(MobEffects.GLOWING,300));

        List<PotionEffect> potions = Arrays.asList(new PotionEffect(MobEffects.JUMP_BOOST,300,1),
                                     new PotionEffect(MobEffects.JUMP_BOOST,300,2),
                                     new PotionEffect(MobEffects.SATURATION,300,1),
                                     new PotionEffect(MobEffects.ABSORPTION,300,2),
                                     new PotionEffect(MobEffects.ABSORPTION,300,3),
                                     new PotionEffect(MobEffects.HEALTH_BOOST,300,1),
                                     new PotionEffect(MobEffects.RESISTANCE,300,3));
        int size = potions.size();
        int index = (int) MathHelper.nextFloat(new Random(), 0, size);
        if (index < size) {
            PotionEffect potion = potions.get(index);
            user.addPotionEffect(potion);
        }

        if(user instanceof EntityPlayerMP) {
            ((EntityPlayerMP)user).connection.sendPacket(new SPacketSoundEffect(MaidSoundEvent.MAID_TAMED, SoundCategory.NEUTRAL,
                    user.posX,user.posY,user.posZ,2f,1f));
            ((EntityPlayerMP)user).connection.sendPacket(new SPacketSoundEffect(SoundEvents.ENTITY_PLAYER_LEVELUP, SoundCategory.NEUTRAL,
                    user.posX,user.posY,user.posZ,2f,1f));
        }
    }
}
