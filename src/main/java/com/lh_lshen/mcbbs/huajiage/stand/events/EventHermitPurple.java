package com.lh_lshen.mcbbs.huajiage.stand.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.damage_source.DamageOverdrive;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
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

import java.util.List;

@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class EventHermitPurple {

    @SubscribeEvent
    public static void onWaveWalking(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase livingBase = event.getEntityLiving();
        if (livingBase instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) livingBase;
            IExposedData data = StandUtil.getStandData(player);
            StandBase stand = StandUtil.getType(player);
            if( stand!=null && data!= null && player.isPotionActive(PotionLoader.potionStand))
            {
                BlockPos pos = player.getPosition().down();
                World world = player.world;
                boolean isWater = world.getBlockState(pos).getMaterial().isLiquid()
                               && !world.getBlockState(player.getPosition().up()).getMaterial().isLiquid();
                boolean isOverDrive = data.getState().equals(CapabilityExposedData.States.OVERDRIVE.getName());
                if(stand.getName().equals("huajiage:hermit_purple")){
                    if (isWater && player.motionY <=0 ){
                        if (!player.isSneaking()){
                         if (isOverDrive){
                             if (player.isInWater()){
                                 player.motionY+=0.02d;
                            }
                             if (player.isAirBorne){
                                 player.setPosition(player.posX,player.posY-0.3f,player.posZ);
                             }
                                player.motionY = 0;
                        }else{
                            if(player.motionY<0){
                                player.motionY+=0.05d;
                            }
                        }
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
    public static void onOverdriveAttack(LivingHurtEvent event){
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
                    if (attacker.isPotionActive(PotionLoader.potionOverdrive)){
                        livingBase.playSound(SoundLoader.WAVE_OVERDRIVE_1,0.1f,1f);
                        event.setAmount(event.getAmount()+attacker.getHealth());
                        livingBase.world.createExplosion(livingBase,livingBase.posX,livingBase.posY+livingBase.getEyeHeight(),livingBase.posZ,0.25f,false);
                    }
                    if (livingBase.isEntityUndead() ){
                        float amount = event.getAmount();
                        livingBase.addPotionEffect(new PotionEffect(MobEffects.GLOWING,60));
                        livingBase.playSound(SoundLoader.WAVE_OVERDRIVE_1,1f,1f);
                        event.setAmount(amount * (1.5f + (isWake?1.5f:0f)));
                        livingBase.setFire(5);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onOverdrive(LivingEvent.LivingUpdateEvent event){
        EntityLivingBase livingBase = event.getEntityLiving();
        IExposedData data = StandUtil.getStandData(livingBase);
        StandChargeHandler chargeHandler = StandUtil.getChargeHandler(livingBase);
        if (data!=null && chargeHandler!=null){
            boolean isMatch = data.getStand().equals("huajiage:hermit_purple") && chargeHandler.getBuffTag().equals(HuajiConstant.BuffTags.OVER_DRIVE);
            if (isMatch && livingBase.isPotionActive(PotionLoader.potionOverdrive) && chargeHandler.getBuffer()>0){
                List<EntityLivingBase> targets = livingBase.world.getEntitiesWithinAABB(EntityLivingBase.class,livingBase.getEntityBoundingBox().grow(1));
                for (EntityLivingBase e : targets){
                    if (e != livingBase) {
                        e.attackEntityFrom(new DamageOverdrive(livingBase),livingBase.getHealth()/2);
                    }
                }
            livingBase.fallDistance = 0;
            }
        }
    }
}
