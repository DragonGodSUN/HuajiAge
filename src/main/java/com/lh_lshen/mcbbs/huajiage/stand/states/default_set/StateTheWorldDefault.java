package com.lh_lshen.mcbbs.huajiage.stand.states.default_set;

import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.init.sound.HuajiSoundPlayer;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.entity.EntityStandBase;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import com.lh_lshen.mcbbs.huajiage.util.HAMathHelper;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.Vec3d;

import java.util.List;
import java.util.Random;

public class StateTheWorldDefault extends StandStateBase {

    public StateTheWorldDefault() {
    }

    public StateTheWorldDefault(String stand, String stateName, boolean isHandPlay, boolean soundLoop) {
        super(stand, stateName, isHandPlay, soundLoop);
    }

    @Override
    public void doTask(EntityLivingBase user) {
        StandBase type = StandUtil.getType(user);
        int stage = StandUtil.getStandStage(user);
        if(type == null ) {
            return;
        }

        List<Entity> entityCollection = user.getEntityWorld().getEntitiesWithinAABB(Entity.class, user.getEntityBoundingBox().grow(stage>0?type.getDistance()+1f:type.getDistance()));
        if(entityCollection.size()<=0) {
            return;
        }

        for(Entity i:entityCollection) {

            Vec3d back = HAMathHelper.getVectorEntityEye(user, i);
            boolean flag_player = false;
            boolean flag_degree = HAMathHelper.getDegreeXZ(user.getLookVec(),HAMathHelper.getVectorEntityEye(user, i))>(type.getName().equals(StandLoader.STAR_PLATINUM.getName())?120:90);

            if(flag_degree) {
                continue;
            }

            if(user instanceof EntityPlayer) {
                flag_player=true;
            }

            if(i instanceof EntityDragon) {
                EntityDragon dragon =(EntityDragon)i;
                if(flag_player) {
                    dragon.attackEntityFromPart(dragon.dragonPartHead,new EntityDamageSource(HuajiConstant.DamageSource.STAND_PUNCH_DAMAGE, user).setExplosion(), type.getDamage()*type.getSpeed());
                }else {
                    dragon.attackEntityFromPart(dragon.dragonPartHead, DamageSource.GENERIC, type.getDamage()*type.getSpeed());
                }
            }

            if(i instanceof EntityLivingBase && !(i instanceof EntityStandBase)) {

                EntityLivingBase target=(EntityLivingBase)i;


                if(target!=user) {
                    float random = new Random().nextFloat()*100;
                    if(random<20&&target.hurtTime <= 0&&stage>0) {
                        HuajiSoundPlayer.playToNearbyClient(target, SoundEvents.ENTITY_GENERIC_EXPLODE, 0.25f);
                        HuajiSoundPlayer.playToNearbyClient(target, SoundLoader.DIO_HIT, 0.75f);
                        if(NBTHelper.getEntityInteger(target, HuajiConstant.Tags.DIO_HIT)<120)
                        {
                            NBTHelper.setEntityInteger(target, HuajiConstant.Tags.DIO_HIT, 120);
                        }

                    }

                    if(NBTHelper.getEntityInteger(target, HuajiConstant.Tags.TIME_STOP)>0&&NBTHelper.getEntityInteger(target, HuajiConstant.Tags.DIO_HIT)<60) {
                        NBTHelper.setEntityInteger(target, HuajiConstant.Tags.DIO_HIT, 60);
                    }else {
                        float health = target.getHealth();
                        if(flag_player) {
                            EntityPlayer player =(EntityPlayer) user;
                            target.attackEntityFrom(DamageSource.causePlayerDamage(player), type.getDamage());
                        }else {
                            target.attackEntityFrom(DamageSource.ANVIL, type.getDamage());
                        }
                    }


                    if(user.ticksExisted%2==0) {
                        user.world.playEvent(2001, target.getPosition().add(0, target.getPositionEyes(target.ticksExisted).y-target.getPosition().getY(), 0), Blocks.OBSIDIAN.getStateId(Blocks.OBSIDIAN.getStateFromMeta(0)));
                    }
                    if(HAMathHelper.getVectorEntityEye(user,target).length()<type.getDistance()){
                        target.motionX=back.x;
                        target.motionY=back.y;
                        target.motionZ=back.z;
                    }
                }

            }else if(i instanceof EntityItem || i instanceof EntityXPOrb || i instanceof EntityStandBase){

                continue;

            }else if(HAMathHelper.getAABBSize(i.getEntityBoundingBox())>2){

                user.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,50,5));
                continue;
            }else {
                i.motionX=(type.getDamage()/10)*back.x;
                i.motionY=(type.getDamage()/10)*back.y;
                i.motionZ=(type.getDamage()/10)*back.z;
            }
        }
    }


}
