package com.lh_lshen.mcbbs.huajiage.stand.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandStates;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

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
            if(stateBase.hasExtraData("undead")){
            event.setCanceled(true);
            entity.setHealth(1f);
               for(String sd : stateBase.getExtraDatas()){
                    if(sd.contains("sound-die:")){
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
}
