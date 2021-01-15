package com.lh_lshen.mcbbs.huajiage.stand.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.init.loaders.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandTag;
import com.lh_lshen.mcbbs.huajiage.stand.StandStates;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class EventHermitPurple {

    @SubscribeEvent
    public static void onWaveWalking(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase livingBase = event.getEntityLiving();
        if (livingBase instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) livingBase;
            StandBase stand = StandUtil.getType(player);
            if( stand!=null && player.isPotionActive(PotionLoader.potionStand))
            {
                BlockPos pos = player.getPosition().down();
                World world = player.world;
                boolean isWater = world.getBlockState(pos).getMaterial().isLiquid()
                               && !world.getBlockState(player.getPosition().up()).getMaterial().isLiquid();
                if(stand.getName().equals("huajiage:hermit_purple")){
                    if (isWater && player.motionY <=0 ){
                        if (!player.isSneaking()){
                            if(player.motionY<0){
                                player.motionY+=0.05d;
                            }
//                            player.motionY = 0;
                            player.fallDistance = 0;
                            player.isAirBorne = false;
                            player.onGround = true;
                        }else {
                            player.motionY = -0.32d;
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onWaveAttack(LivingHurtEvent event){
        EntityLivingBase livingBase = event.getEntityLiving();
        Entity entity = event.getSource().getTrueSource();
        if (entity instanceof  EntityLivingBase) {
            EntityLivingBase attacker = (EntityLivingBase) entity;
            StandBase stand = StandUtil.getType(attacker);
            IExposedData data = StandUtil.getStandData(attacker);
            if( stand!=null && data!=null && attacker.isPotionActive(PotionLoader.potionStand))
            {
                String state = StandUtil.getStandState(attacker);
                int level = data.getStage();
                boolean isWake = level>0;
                StandStateBase stateBase = StandStates.getStandState(stand.getName(),state);
                if(stateBase.hasExtraData(EnumStandTag.StateTags.ELEMENT_LIGHT.getName())){
                    if (livingBase.isEntityUndead()){
                        float amount = event.getAmount();
                        livingBase.addPotionEffect(new PotionEffect(MobEffects.GLOWING,60));
                        livingBase.playSound(SoundLoader.WAVE_OVERDRIVE_1,1f,1f);
                        event.setAmount(amount * (1.5f + (isWake?1.5f:0f)));
                    }
                }
            }
        }
    }

}
