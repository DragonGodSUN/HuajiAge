package com.lh_lshen.mcbbs.huajiage.stand.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.damage_source.DamageDiscDeprive;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemDiscMind;
import com.lh_lshen.mcbbs.huajiage.item.ItemDiscStand;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandTag;
import com.lh_lshen.mcbbs.huajiage.stand.StandStates;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class EventWhiteSnake {

    @SubscribeEvent
    public static void onOverdriveAttack(LivingAttackEvent event){
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
                if(stateBase.hasExtraData(EnumStandTag.StateTags.DISC_DEPRIVE.getName())){
                    if (! livingBase.isDead ){
                        float amount = event.getAmount();
                        boolean isDeprived = NBTHelper.getEntityBoolean(livingBase,"disc_deprive");
                        if (!isDeprived){
                            ItemStack discMind = new ItemStack(ItemLoader.discMind);
                            ItemDiscMind.setOwner(discMind,livingBase.getName(),livingBase.getUniqueID().toString());

                            livingBase.playSound(SoundEvents.BLOCK_COMPARATOR_CLICK, 1f, 1f);
                            NBTHelper.setEntityBoolean(livingBase,"disc_deprive",true);
                            if (!livingBase.world.isRemote) {
                                livingBase.entityDropItem(discMind,0.25f);
                            }

                        }else {
                            if (attacker instanceof EntityPlayer && attacker.world.isRemote){
                                attacker.sendMessage(new TextComponentTranslation("This creature's mind had been deprived"));
                            }
                        }

//                        livingBase.playSound(SoundLoader.WAVE_OVERDRIVE_1,1f,1f);

                        if (livingBase instanceof EntityPlayer){
                            StandBase stand_hurt = StandUtil.getType(livingBase);
                            IExposedData data_hurt = StandUtil.getStandData(livingBase);
                            if (stand_hurt != null && data_hurt != null){
                                String type = data_hurt.getStand();
                                int stage = data_hurt.getStage();
                                String model = data_hurt.getModel();
                                if (!livingBase.world.isRemote) {
                                    ((EntityPlayer)(livingBase)).dropItem(ItemDiscStand.getItemData(new ItemStack(ItemLoader.disc),type,stage,model),true);
                                    data.setStand("empty");
                                    data.setStage(0);
                                    data.setState("default");
                                    data.setModel(HuajiConstant.StandModels.DEFAULT_MODEL_ID);
                                    data.setTrigger(false);
                                }
//                                livingBase.playSound(SoundEvents.BLOCK_COMPARATOR_CLICK, 1f, 1f);
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onDiscDeprive(LivingEvent.LivingUpdateEvent event){
        EntityLivingBase livingBase = event.getEntityLiving();
        if (!livingBase.getEntityWorld().isRemote){
            boolean isDiscDeprived = NBTHelper.getEntityBoolean(livingBase,"disc_deprive");
            if (isDiscDeprived){
                if (!livingBase.isPotionActive(MobEffects.WITHER)
                 && !livingBase.isPotionActive(MobEffects.NAUSEA)){
                    livingBase.addPotionEffect(new PotionEffect(MobEffects.WITHER,60));
                    livingBase.addPotionEffect(new PotionEffect(MobEffects.NAUSEA,60));
                }
                if (livingBase.getHealth()>0 && livingBase.ticksExisted%20==0){
                    livingBase.attackEntityFrom(new DamageDiscDeprive(),livingBase.getMaxHealth()/10);
                }
        }

        }

    }
}
