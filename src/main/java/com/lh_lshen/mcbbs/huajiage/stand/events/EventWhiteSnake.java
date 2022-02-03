package com.lh_lshen.mcbbs.huajiage.stand.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.damage_source.DamageDiscDeprive;
import com.lh_lshen.mcbbs.huajiage.damage_source.DamageStandHit;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemDiscMemory;
import com.lh_lshen.mcbbs.huajiage.item.ItemDiscMind;
import com.lh_lshen.mcbbs.huajiage.item.ItemDiscStand;
import com.lh_lshen.mcbbs.huajiage.stand.EnumStandTag;
import com.lh_lshen.mcbbs.huajiage.stand.StandStates;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import com.lh_lshen.mcbbs.huajiage.util.HAMathHelper;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Random;

@Mod.EventBusSubscriber(modid = HuajiAge.MODID)
public class EventWhiteSnake {

    @SubscribeEvent
    public static void onOverdriveAttack(LivingAttackEvent event){
        EntityLivingBase livingBase = event.getEntityLiving();
        Entity entity = event.getSource().getTrueSource();
        float amount = event.getAmount();
        if (entity instanceof  EntityLivingBase) {
            EntityLivingBase attacker = (EntityLivingBase) entity;
            StandBase stand = StandUtil.getType(attacker);
            IExposedData data = StandUtil.getStandData(attacker);
            if( stand!=null && data!=null && attacker.isPotionActive(PotionLoader.potionStand))
            {
                String state = StandUtil.getStandState(attacker);
                int stage = data.getStage();
                boolean isWake = stage>0;
                StandStateBase stateBase = StandStates.getStandState(stand.getName(),state);
                if(stateBase != null && stateBase.hasExtraData(EnumStandTag.StateTags.DISC_DEPRIVE.getName())){
                    if (!livingBase.isDead && HAMathHelper.getDistance(livingBase.getPosition(),attacker.getPosition())<=5 ){
                        boolean isDeprived = NBTHelper.getEntityBoolean(livingBase,"disc_deprive");
                        if (!isDeprived){
                            IExposedData data_hurt = StandUtil.getStandData(livingBase);

                            ItemStack discMind = ItemDiscMind.getDiscMind(livingBase);
                            ItemStack discMemory = ItemDiscMemory.getDiscMemory(livingBase);
                                boolean isStandUser =data_hurt != null && !data_hurt.getStand().equals(StandLoader.EMPTY);
                                if (!isStandUser) {
                                    livingBase.playSound(SoundEvents.BLOCK_COMPARATOR_CLICK, 1f, 1f);
                                    NBTHelper.setEntityBoolean(livingBase,"disc_deprive",true);
                                    if (!livingBase.world.isRemote) {
                                        livingBase.entityDropItem(discMind,0.25f);
                                        livingBase.entityDropItem(discMemory,0.25f);
                                    }
                                    if (attacker.world.isRemote){
                                        attacker.sendMessage(new TextComponentTranslation("stand.huajiage.skill.huajiage.white_snake.mind_deprive.success",livingBase.getName()));
                                    }
                                    playSounds(livingBase);
                                }else {
                                    if (!data_hurt.getStand().equals(StandLoader.EMPTY)) {
                                        String hurtStand = data_hurt.getStand();
                                        int hurtStage = data_hurt.getStage();
                                        String hurtModel = data_hurt.getModel();
                                        boolean hasStand = !hurtStand.equals(StandLoader.EMPTY);
                                        if (hasStand){
                                            if (hurtStage<=stage){
                                                if (!attacker.world.isRemote) {
                                                    NBTHelper.setEntityBoolean(livingBase,"disc_deprive",true);
                                                    livingBase.entityDropItem(ItemDiscStand.getItemData(new ItemStack(ItemLoader.disc),hurtStand,hurtStage,hurtModel),-0.5f);
                                                    livingBase.entityDropItem(ItemDiscMemory.getDiscMemory(livingBase),-0.25f);
                                                    data_hurt.setTrigger(false);
                                                    data_hurt.setStand("empty");
                                                    data_hurt.setStage(0);
                                                    data_hurt.setState("default");
                                                    data_hurt.setModel(HuajiConstant.StandModels.DEFAULT_MODEL_ID);
                                                }
                                                playSounds(livingBase);
                                            }else {
                                                if (attacker.world.isRemote){
                                                    attacker.sendMessage(new TextComponentTranslation("stand.huajiage.skill.huajiage.white_snake.mind_deprive.stand_block"));
                                                }
                                            }
                                        }
                                    }
                                }

                        }else {
                            if (attacker instanceof EntityPlayer && attacker.world.isRemote
                                && !(event.getSource() instanceof DamageStandHit)){
                                attacker.sendMessage(new TextComponentTranslation("stand.huajiage.skill.huajiage.white_snake.mind_deprive.fail"));
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
        boolean isDiscDeprived = NBTHelper.getEntityBoolean(livingBase,"disc_deprive");
        if (isDiscDeprived) {
            if (!livingBase.isPotionActive(MobEffects.WITHER)
                    && !livingBase.isPotionActive(MobEffects.NAUSEA)) {
                livingBase.addPotionEffect(new PotionEffect(MobEffects.WITHER, 60));
                livingBase.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 60));
            }
            if (livingBase.getHealth() > 0 && livingBase.ticksExisted % 20 == 0) {
                livingBase.attackEntityFrom(new DamageDiscDeprive(), livingBase.getMaxHealth() / 10);
            }
        }
    }

    public static void playSounds(EntityLivingBase entity){
        float i = MathHelper.nextFloat(new Random(),0,10);
        if (i<3.3){
            entity.playSound(SoundLoader.STAND_WHITE_SNAKE_HIT_1,1f,1f);
        }else if (i>3.3&&i<6.6){
            entity.playSound(SoundLoader.STAND_WHITE_SNAKE_HIT_2,1f,1f);
        }else {
            entity.playSound(SoundLoader.STAND_WHITE_SNAKE_HIT_3,1f,1f);
        }
    }
}
