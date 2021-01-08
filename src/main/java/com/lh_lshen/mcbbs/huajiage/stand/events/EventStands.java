package com.lh_lshen.mcbbs.huajiage.stand.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.init.loaders.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandTag;
import com.lh_lshen.mcbbs.huajiage.stand.StandStates;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.entity.EntityStandBase;
import com.lh_lshen.mcbbs.huajiage.stand.helper.StandPowerHelper;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.List;

@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class EventStands {

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        EntityLivingBase entity=event.getEntityLiving();
        StandBase stand = StandUtil.getType(entity);
        if( stand!=null && stand!= StandLoader.ORGA_REQUIEM && entity.isPotionActive(PotionLoader.potionStand))
        {
            String state = StandUtil.getStandState(entity);
            StandStateBase stateBase = StandStates.getStandState(stand.getName(),state);
            if(stateBase.hasExtraData(EnumStandTag.StateTags.UNDEAD.getName())){
            event.setCanceled(true);
            entity.setHealth(1f);
               for(String sd : stateBase.getExtraDatas()){
                    if(sd.contains(EnumStandTag.StateTags.SOUND_DIE.getName())){
                        String s = sd.substring(sd.indexOf(":"));
                        if(ForgeRegistries.SOUND_EVENTS.containsKey(new ResourceLocation(s))){
                            SoundEvent soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(s));
                            HuajiSoundPlayer.playToNearbyClient(entity, soundEvent, 1f);
                        }
                    }
               }
            }
        }
    }

    @SubscribeEvent
    public static void onStandDeath(LivingDeathEvent event) {
        EntityLivingBase entity=event.getEntityLiving();
        if(entity instanceof EntityStandBase && ((EntityStandBase)entity).getUser()!=null){
            event.setCanceled(true);
            entity.setHealth(10f);
        }
    }

    @SubscribeEvent
    public static void onPlayLiving(LivingEvent.LivingUpdateEvent event){
        EntityLivingBase entity=event.getEntityLiving();
        StandBase stand = StandUtil.getType(entity);
        if( stand!=null &&  entity.isPotionActive(PotionLoader.potionStand))
        {
            String state = StandUtil.getStandState(entity);
            StandStateBase stateBase = StandStates.getStandState(stand.getName(),state);
            if(stateBase!=null && stateBase.hasExtraData(EnumStandTag.StateTags.RIDE.getName())){
                List<Entity> entityList = StandPowerHelper.getListEntityIncludingStand(entity,1);
                if (entityList.size()>0) {
                    for (Entity standR:entityList) {
                        if(standR instanceof EntityStandBase &&
                                ((EntityStandBase) standR).getUser()!=null &&
                                ((EntityStandBase) standR).getUser().equals(entity)){
                            if (!entity.isRiding()) {
                                System.out.println("ride on");
                                entity.startRiding(standR,true);
                            }
                        }
                    }
                }
            }
        }
    }
}
