package com.lh_lshen.mcbbs.huajiage.stand.helper;

import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.util.HAMathHelper;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class StandPowerHelper {
    public static void rangePunchAttack(EntityLivingBase user, StandBase type,int stage, float degree, float distance){
        List<Entity> entityCollection = user.getEntityWorld().getEntitiesWithinAABB(Entity.class, user.getEntityBoundingBox().grow(distance));
        if(entityCollection.size()<=0) {
            return;
        }
        for(Entity i:entityCollection) {
            Vec3d back = HAMathHelper.getVectorEntityEye(user, i);
            boolean flag_player = false;
            boolean flag_degree = HAMathHelper.getDegreeXZ(user.getLookVec(),HAMathHelper.getVectorEntityEye(user, i))>degree;
            if(flag_degree) {
                continue;
            }
            if(user instanceof EntityPlayer) {
                flag_player=true;
            }
            if(i instanceof EntityDragon) {
                EntityDragon dragon =(EntityDragon)i;
                if(flag_player) {
                    dragon.attackEntityFromPart(dragon.dragonPartHead,new EntityDamageSource(HuajiConstant.DamageSource.STAND_PUNCH_DAMAGE, user).setExplosion(), type.getDamage());
                }else {
                    dragon.attackEntityFromPart(dragon.dragonPartHead, DamageSource.GENERIC, type.getDamage());
                }
            }
            if(i instanceof EntityLivingBase) {
                EntityLivingBase target=(EntityLivingBase)i;
                if(target!=user) {
                    if(NBTHelper.getEntityInteger(target, HuajiConstant.Tags.TIME_STOP)>0&&NBTHelper.getEntityInteger(target, HuajiConstant.Tags.DIO_HIT)<60) {
                        NBTHelper.setEntityInteger(target, HuajiConstant.Tags.DIO_HIT, 60);
                    }else {
                        if(flag_player) {
                            EntityPlayer player =(EntityPlayer) user;
                            target.attackEntityFrom(DamageSource.causePlayerDamage(player), type.getDamage());
                        }else {
                            target.attackEntityFrom(DamageSource.ANVIL, type.getDamage());
                        }
                    }
                    if(user.ticksExisted%2==0) {
                        user.world.playEvent(2001, target.getPosition().add(0, target.getPositionEyes(target.ticksExisted).y-target.getPosition().getY(), 0), Block.getStateId(Blocks.OBSIDIAN.getDefaultState()));
                    }
                    target.motionX=back.x;
                    target.motionY=back.y;
                    target.motionZ=back.z;
                }
            }else if(i instanceof EntityItem || i instanceof EntityXPOrb){
                continue;
            }else if(HAMathHelper.getAABBSize(i.getEntityBoundingBox())>2){
                user.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,50,1));
                continue;
            }else {
                i.motionX=(type.getDamage()/10)*back.x;
                i.motionY=(type.getDamage()/10)*back.y;
                i.motionZ=(type.getDamage()/10)*back.z;
            }
        }
    }
}
