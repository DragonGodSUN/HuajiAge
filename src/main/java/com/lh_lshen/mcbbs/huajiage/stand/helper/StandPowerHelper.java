package com.lh_lshen.mcbbs.huajiage.stand.helper;

import com.google.common.collect.Lists;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.entity.EntityEmeraldBullet;
import com.lh_lshen.mcbbs.huajiage.init.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.potion.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.stand.custom.script.EntityLivingBaseWrapper;
import com.lh_lshen.mcbbs.huajiage.stand.custom.script.WorldWrapper;
import com.lh_lshen.mcbbs.huajiage.stand.entity.EntityStandBase;
import com.lh_lshen.mcbbs.huajiage.util.HAMathHelper;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class StandPowerHelper {
    /**
     * 范围内攻击
     * @param user 玩家
     * @param degree 可攻击角度
     * @param damage 伤害
     * @param distance 可攻击距离
     */
    public static void rangePunchAttack(EntityLivingBase user,float degree, float damage, float distance){
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
                    dragon.attackEntityFromPart(dragon.dragonPartHead,new EntityDamageSource(HuajiConstant.DamageSource.STAND_PUNCH_DAMAGE, user).setExplosion(), damage);
                }else {
                    dragon.attackEntityFromPart(dragon.dragonPartHead, DamageSource.GENERIC, damage);
                }
            }
            if(i instanceof EntityLivingBase && !(i instanceof EntityStandBase)) {
                EntityLivingBase target=(EntityLivingBase)i;
                if(target!=user) {
                    if(NBTHelper.getEntityInteger(target, HuajiConstant.Tags.TIME_STOP)>0&&NBTHelper.getEntityInteger(target, HuajiConstant.Tags.DIO_HIT)<60) {
                        NBTHelper.setEntityInteger(target, HuajiConstant.Tags.DIO_HIT, 60);
                    }else {
                        if(flag_player) {
                            EntityPlayer player =(EntityPlayer) user;
                            target.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
                        }else {
                            target.attackEntityFrom(DamageSource.ANVIL, damage);
                        }
                    }
                    if(user.ticksExisted%2==0) {
                        user.world.playEvent(2001, target.getPosition().add(0, target.getPositionEyes(target.ticksExisted).y-target.getPosition().getY(), 0), Block.getStateId(Blocks.OBSIDIAN.getDefaultState()));
                    }
                    target.motionX=back.x;
                    target.motionY=back.y;
                    target.motionZ=back.z;
                }
            }else if(i instanceof EntityItem || i instanceof EntityXPOrb || i instanceof EntityStandBase){
                continue;
            }else if(HAMathHelper.getAABBSize(i.getEntityBoundingBox())>2){
                user.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE,50,1));
                continue;
            }else {
                i.motionX=(damage/10)*back.x;
                i.motionY=(damage/10)*back.y;
                i.motionZ=(damage/10)*back.z;
            }
        }
    }

    /**
     * 充能
     * @param user 玩家
     * @param points 点数
     */
    public static void MPCharge(EntityLivingBase user, int points){
        StandChargeHandler chargeHandler = user.getCapability(CapabilityStandChargeHandler.STAND_CHARGE, null);
        if( null !=chargeHandler )
        {
            chargeHandler.charge(points);
        }
    }

    /**
     * 生成粒子特效
     * @param entity 实体
     * @param type 类型
     */
    public static void createParticleEffect(Entity entity, int type){
        switch (type){
            case 1:
                entity.world.playEvent(2005,entity.getPosition(),1);
                entity.playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,0.5f,1f);
                break;
            case 2:
                break;
            default:
        }
    }

    public static void playEvent(Entity entity, int event_type, int data){

    }

    public static void playEvent(World world, BlockPos blockPos, int event_type, int data){

    }

//  药水效果========================================================================================
    /**
     * 添加药水效果
     * @param user 玩家
     * @param potions 药水效果列表
     */
    public static void potionEffect(EntityLivingBase user,List<PotionEffect> potions){
    for(PotionEffect potion : potions){
        user.addPotionEffect(potion);
        }
    }

    /**
     * 添加药水效果
     * @param user 玩家
     * @param potions 多种药水效果
     */
    public static void potionEffectAdd(EntityLivingBase user,PotionEffect... potions){
        potionEffect(user, Arrays.asList(potions));
    }

    /**
     * 添加药水效果
     * @param user
     * @param type
     * @param duration
     * @param level
     */
    public static void potionEffectAdd(EntityLivingBase user,String type, int duration,int level ){
        List<PotionEffect> potionEffects = Lists.newArrayList();
        PotionEffect potionEffect = newPotion(type,duration,level);
        potionEffects.add(potionEffect);
        potionEffect(user, potionEffects);
    }

    /**
     * 延长替身释放时间
     * @param user
     * @param ticks
     */
    public static void increaseStandTime(EntityLivingBase user, int ticks){
        potionEffectAdd(user,new PotionEffect(PotionLoader.potionStand,ticks));
    }

    /**
     * 移除指定药水效果
     * @param user
     * @param type
     */
    public static void removePotion(EntityLivingBase user,String type){
        Potion potion = getPotion(type);
        if(potion!=null){
            if(user.isPotionActive(potion)){
                user.removePotionEffect(potion);
            }
        }
    }

    /**
     * 移除不好的药水效果
     * @param user
     */
    public static void removeBadPotion(EntityLivingBase user){
        Collection<Potion> potions = ForgeRegistries.POTIONS.getValuesCollection();
        for(Potion p : potions){
            if(p.isBadEffect()){
                removePotion(user,p.getName());
            }
        }
    }

    /**
     * 获取药水类型
     * @param type
     * @return
     */
    public static Potion getPotion(String type){
        return ForgeRegistries.POTIONS.getValue(new ResourceLocation(type));
    }

    /**
     * 获得指定药水
     * @param type 药水ID
     * @param duration 持续时长
     * @param level 等级
     * @return  药水
     */
    public static PotionEffect newPotion(String type,int duration,int level){
        Potion potion = getPotion(type);
        if(potion!=null){
            return new PotionEffect(potion,duration, Math.max(level-1,0));
        }
        return null;
    }
    /**
     * 获得指定药水
     * @param type 药水ID
     * @param duration 持续时长
     * @return  药水
     */
    public static PotionEffect newPotion(String type,int duration){
        Potion potion = ForgeRegistries.POTIONS.getValue(new ResourceLocation(type));
        if(potion!=null){
            return new PotionEffect(potion,duration, 0);
        }
        return null;
    }

    /**
     * 默认替身药水效果
     * @param user 玩家
     */
    public static void potionDefaultOutOfTime(EntityLivingBase user){
        List<PotionEffect> potions = Lists.newArrayList();
        potions.add(new PotionEffect(PotionLoader.potionStand , 5*20  ));
        potions.add(new PotionEffect(MobEffects.SLOWNESS , 5*20, 1 ));
        potions.add(new PotionEffect(MobEffects.HUNGER , 5*20 , ConfigHuaji.Stands.allowStandPunish? 24 : 49 ));
        if(ConfigHuaji.Stands.allowStandPunish) {
            potions.add(new PotionEffect(MobEffects.WITHER, 5 * 20, 1));
        }
        if( !user.isPotionActive(PotionLoader.potionStand) || user.isPotionActive(PotionLoader.potionStand)&&user.getActivePotionEffect(PotionLoader.potionStand).getDuration()<=10) {
            potionEffect(user, potions);
        }
    }

//  发射物==========================================================================================
    /**
     * 每个几个ticks射出弹射物
     * @param user 玩家
     * @param world 世界
     * @param projectile 发射实体（箭矢）
     * @param position 放出位置
     * @param vector 射出矢量
     * @param ticks 每隔几个tick射出一下
     */
    public static void shootProjectile(EntityLivingBase user, World world, Entity projectile, Vec3d position, Vec3d vector, int ticks){
        if(user.ticksExisted%ticks==0){
            shoot(user,world,projectile,position,vector);
        }
    }

    /**
     * 发射出投掷物
     * @param user 玩家
     * @param world 世界
     * @param projectile 发射实体（箭矢）
     * @param x 发射点的x坐标
     * @param y 发射点的y坐标
     * @param z 发射点的z坐标
     * @param motionX x轴方向的矢量
     * @param motionY y轴方向的矢量
     * @param motionZ z轴方向的矢量
     * @param speed 发射速度
     */
    public static  void shootProjectile(EntityLivingBase user, World world, Entity projectile, float x, float y, float z, float motionX, float motionY, float motionZ, float speed){
        int ticks = (int) (5/speed);
        Vec3d pos = new Vec3d(x,y,z);
        Vec3d vector = new Vec3d(motionX,motionY,motionZ);
        shootProjectile(user,world,projectile,pos,vector,ticks);
    }

    /**
     * 发射出投掷物
     * @param user 玩家
     * @param world 世界
     * @param projectile 发射实体（箭矢）
     * @param position 发射点坐标
     * @param vector 箭矢矢量
     */
    public static void shoot(EntityLivingBase user, World world, Entity projectile, Vec3d position, Vec3d vector){
        projectile.setPosition(position.x,position.y,position.z);
        projectile.motionX = vector.x;
        projectile.motionY = vector.y;
        projectile.motionZ = vector.z;
        if(!world.isRemote){
            world.spawnEntity(projectile);
        }

    }

    /**
     * 发射出投掷物
     * @param user 玩家
     * @param worldWrapper 世界代理
     * @param projectile 发射实体（箭矢）
     * @param position 发射点坐标
     * @param vector 箭矢矢量
     */
    public static void shoot(EntityLivingBase user, WorldWrapper worldWrapper, Entity projectile, Vec3d position, Vec3d vector){
        projectile.setPosition(position.x,position.y,position.z);
        projectile.motionX = vector.x;
        projectile.motionY = vector.y;
        projectile.motionZ = vector.z;
        if(!user.world.isRemote){
            worldWrapper.getWorld().spawnEntity(projectile);
        }

    }

    /**
     * 发射绿宝石
     * @param user 玩家
     * @param world 世界
     * @param x 发射点x坐标
     * @param y 发射点y坐标
     * @param z 发射点z坐标
     * @param motionX X轴矢量
     * @param motionY Y轴矢量
     * @param motionZ Z轴矢量
     * @param speed 发射速度
     */
    public static void shootEmerald(EntityLivingBase user, World world, float x, float y, float z, float motionX, float motionY, float motionZ, float speed){
        EntityEmeraldBullet emeraldBullet = new EntityEmeraldBullet(user.world,user);
        emeraldBullet.setLife(300);
        emeraldBullet.setRotation(user.rotationYaw);
        emeraldBullet.setPitch(user.rotationPitch);
        emeraldBullet.setDamage(5f);
        emeraldBullet.setRotationRandom((float) Math.random()*360);
        shootProjectile(user,world,emeraldBullet,x,y,z,motionX,motionY,motionZ,speed);
    }
    /**
     * 发射绿宝石
     * @param user 玩家
     * @param x 发射点x坐标
     * @param y 发射点y坐标
     * @param z 发射点z坐标
     * @param motionX X轴矢量
     * @param motionY Y轴矢量
     * @param motionZ Z轴矢量
     * @param speed 发射速度
     */
    public static void shootEmerald(EntityLivingBase user, float x, float y, float z, float motionX, float motionY, float motionZ, float speed){
        shootEmerald(user,user.world,x,y,z,motionX,motionY,motionZ,speed);
    }

    /**
     * 发射钻石
     * @param user 玩家
     * @param x 发射点x坐标
     * @param y 发射点y坐标
     * @param z 发射点z坐标
     * @param motionX X轴矢量
     * @param motionY Y轴矢量
     * @param motionZ Z轴矢量
     * @param speed 发射速度
     */
    public static void shootDiamond(EntityLivingBase user,float x, float y, float z, float motionX, float motionY, float motionZ, float speed){
        EntityEmeraldBullet emeraldBullet = new EntityEmeraldBullet(user.world,user);
        emeraldBullet.setLife(300);
        emeraldBullet.setType("diamond");
        emeraldBullet.setRotation(user.rotationYaw);
        emeraldBullet.setPitch(user.rotationPitch);
        emeraldBullet.setDamage(5f);
        emeraldBullet.setRotationRandom((float) Math.random()*360);
        shootProjectile(user,user.world,emeraldBullet,x,y,z,motionX,motionY,motionZ,speed);
    }

    /**
     * 发射物品
     * @param user 玩家
     * @param x 发射点x坐标
     * @param y 发射点y坐标
     * @param z 发射点z坐标
     * @param motionX X轴矢量
     * @param motionY Y轴矢量
     * @param motionZ Z轴矢量
     * @param speed 发射速度
     * @param itemId 物品ID
     */
    public static void shootItems(EntityLivingBase user,float x, float y, float z, float motionX, float motionY, float motionZ, float speed, String itemId){
        EntityEmeraldBullet emeraldBullet = new EntityEmeraldBullet(user.world,user);
        emeraldBullet.setLife(300);
        emeraldBullet.setType(itemId);
        emeraldBullet.setRotation(user.rotationYaw);
        emeraldBullet.setPitch(user.rotationPitch);
        emeraldBullet.setDamage(5f);
        emeraldBullet.setRotationRandom((float) Math.random()*360);
        shootProjectile(user,user.world,emeraldBullet,x,y,z,motionX,motionY,motionZ,speed);
    }

    /**
     * 发射物品
     * @param user 玩家
     * @param x 发射点x坐标
     * @param y 发射点y坐标
     * @param z 发射点z坐标
     * @param motionX X轴矢量
     * @param motionY Y轴矢量
     * @param motionZ Z轴矢量
     * @param speed 发射速度
     * @param itemId 物品ID
     * @param damage 伤害
     */
    public static void shootItems(EntityLivingBase user,float x, float y, float z, float motionX, float motionY, float motionZ, float speed, String itemId, float damage){
        EntityEmeraldBullet emeraldBullet = new EntityEmeraldBullet(user.world,user);
        emeraldBullet.setLife(300);
        emeraldBullet.setType(itemId);
        emeraldBullet.setRotation(user.rotationYaw);
        emeraldBullet.setPitch(user.rotationPitch);
        emeraldBullet.setDamage(damage);
        emeraldBullet.setRotationRandom((float) Math.random()*360);
        shootProjectile(user,user.world,emeraldBullet,x,y,z,motionX,motionY,motionZ,speed);
    }

//  时间停止========================================================================================
    /**
     * 时间停止
     * @param user 玩家
     * @param ticks 时停时间（tick为单位）
     * @param range 时停范围
     */
    public static void setTimeStop(EntityLivingBase user, int ticks, float range){
        TimeStopHelper.setTimeStop(user,ticks);
        TimeStopHelper.setEntityTimeStopRange(user,range);
    }

//  音效播放========================================================================================
    /**
     * 播放音效
     * @param user 玩家
     * @param soundEvent 音效事件
     * @param volume 响度
     * @param pitch 声调
     */
    public static void playSound(EntityLivingBase user, SoundEvent soundEvent, float volume, float pitch){
        if(user instanceof EntityPlayerMP){
            ((EntityPlayerMP) user).connection.
                sendPacket(new SPacketSoundEffect(soundEvent,SoundCategory.PLAYERS,user.posX,user.posY,user.posZ, volume,pitch));
        }
    }

    /**
     * 播放音效
     * @param user 玩家
     * @param soundId 音效Id
     * @param volume 响度
     * @param pitch 声调
     */
    public static void playSound(EntityLivingBase user, String soundId, float volume, float pitch){
        if (ForgeRegistries.SOUND_EVENTS.containsKey(new ResourceLocation(soundId))) {
            SoundEvent soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(soundId));
            if (soundEvent!=null) {
                playSound(user,soundEvent,volume,pitch);
            }
        }
    }

//  获得实体=========================================================================================

    /**
     * 获取玩家周边实体
     * @param user 玩家
     * @param distance 距离
     * @param entity 实体类型
     * @return 实体列表
     */
    public static List<Entity> getListEntity(EntityLivingBase user, float distance, Entity entity){
        List<Entity> list = Lists.newArrayList();
        List<Entity> entities = user.world.getEntitiesWithinAABB(entity.getClass(),user.getEntityBoundingBox().grow(distance));
        if(entities!=null && !entities.isEmpty()){
        entities.remove(user);
        list.addAll(entities);
        }
        return list;
    }

    /**
     * 获取玩家周边实体
     * @param user 玩家
     * @param distance 距离
     * @return 实体列表
     */
    public static List<Entity> getListEntity(EntityLivingBase user, float distance){
        List<Entity> list = Lists.newArrayList();
        List<Entity> entities = user.world.getEntitiesWithinAABB(Entity.class,user.getEntityBoundingBox().grow(distance));
        if(entities!=null && !entities.isEmpty()){
            entities.remove(user);
            entities.removeIf(entity -> entity instanceof EntityStandBase);
            list.addAll(entities);
        }
        return list;
    }

    /**
     * 获取玩家周边实体（包括替身在内）
     * @param user 玩家
     * @param distance 距离
     * @return 实体列表
     */
    public static List<Entity> getListEntityIncludingStand(EntityLivingBase user, float distance){
        List<Entity> list = Lists.newArrayList();
        List<Entity> entities = user.world.getEntitiesWithinAABB(Entity.class,user.getEntityBoundingBox().grow(distance));
        if(entities!=null && !entities.isEmpty()){
            entities.remove(user);
            list.addAll(entities);
        }
        return list;
    }

    /**
     * 获取玩家周围生物实体
     * @param user 玩家
     * @param distance 距离
     * @return 实体列表
     */
    public static List<EntityLivingBase> getListEntityLiving(EntityLivingBase user, float distance){
        List<Entity> list = getListEntity(user,distance);
        List<EntityLivingBase> list_living = Lists.newArrayList();
        if (list.size()>0) {
            for (Entity entity : list) {
                if(entity instanceof EntityLivingBase){
                    list_living.add((EntityLivingBase) entity);
                }
            }
        }
        return list_living;
    }

    /**
     * 获取一定范围内的生物实体
     * @param user 玩家
     * @param distance 距离
     * @param degree 角度（以玩家面朝方向起始，朝两边延伸）
     * @return 实体列表
     */
    public static List<EntityLivingBase> getRangeLiving(EntityLivingBase user, float distance, float degree){
        List<EntityLivingBase> list = getListEntityLiving(user,distance);
        List<EntityLivingBase> livingBaseList = Lists.newLinkedList();
        for (EntityLivingBase livingBase : list) {
            boolean flag_degree = HAMathHelper.getDegreeXZ(user.getLookVec(),HAMathHelper.getVectorEntityEye(user, livingBase))>degree;
            if(!flag_degree){
                livingBaseList.add(livingBase);
            }
        }
        return livingBaseList;
    }

    /**
     * 获取玩家的替身实体
     * @param user
     * @return
     */
    public static EntityStandBase getUserStand(EntityLivingBase user){
        List<Entity> entities = getListEntity(user,1);
        if(entities.size()>0){
            for (Entity e : entities) {
                if(e instanceof EntityStandBase){
                    if(((EntityStandBase) e).getUser()!=null && ((EntityStandBase) e).getUser().equals(user)){
                        return (EntityStandBase) e;
                    }
                }
            }
        }
        return null;
    }
//  物品============================================================================================
    /**
     * 给与玩家物体
     * @param user 玩家
     * @param item 物体
     * @param amount 数量
     */
    public static void addItemToPlayer(EntityLivingBase user, Item item, int amount){
        if(user instanceof EntityPlayer){
            ((EntityPlayer) user).inventory.addItemStackToInventory(new ItemStack(item,amount));
        }
    }

    /**
     * 给与玩家物品
     * @param user 玩家
     * @param itemId 物体ID
     * @param amount 数量
     */
    public static void addItemToplayer(EntityLivingBase user, String itemId, int amount){
        ResourceLocation key = new ResourceLocation(itemId);
        if (ForgeRegistries.ITEMS.containsKey(key)) {
            Item item = ForgeRegistries.ITEMS.getValue(key);
            addItemToPlayer(user,item,amount);
        }
    }

    /**
     * 玩家是否携带该物品
     * @param user 玩家
     * @param item 物品
     * @return 是或否
     */
    public static boolean isPlayerHasItem(EntityLivingBase user, Item item){
        if(user instanceof EntityPlayer){
            return ((EntityPlayer) user).inventory.hasItemStack(new ItemStack(item));
        }
        return false;
    }

    /**
     * 玩家是否携带该物品
     * @param user 玩家
     * @param itemId 物品ID
     * @return 是或否
     */
    public static boolean isPlayerHasItem(EntityLivingBase user, String itemId){
        ResourceLocation key = new ResourceLocation(itemId);
        if (ForgeRegistries.ITEMS.containsKey(key)) {
            Item item = ForgeRegistries.ITEMS.getValue(key);
            return isPlayerHasItem(user,item);
        }
        return false;
    }

    /**
     * 获得玩家手持的物品
     * @param user 玩家
     * @param isMainHand 是否为主手
     * @return 物品
     */
    public static ItemStack getPlayerHoldItem(EntityLivingBase user, boolean isMainHand){
        return isMainHand?user.getHeldItemMainhand():user.getHeldItemOffhand();
    }

    /**
     * 设置物品耐久度
     * @param stack 物品
     * @param duration 耐久
     */
    public static void setItemDuration(ItemStack stack, int duration){
        if (stack.getMaxDamage()>=duration) {
            stack.setItemDamage(stack.getMaxDamage()-duration);
        }else {
            stack.setItemDamage(0);
        }
    }

    /**
     * 完全修复物品
     * @param stack 物品栈
     */
    public static void repairItem(ItemStack stack){
        stack.setItemDamage(0);
    }

    /**
     * 修复物品点数
     * @param stack 物品栈
     * @param duration 修复点数
     */
    public static void repairItem(ItemStack stack, int duration){
        if (!stack.isEmpty()&&stack.getItemDamage()>0) {
            int result = stack.getItemDamage()-duration;
            stack.setItemDamage(Math.max(result, 0));
        }
    }

//  动作=============================================================================================

    /**
     * 设置生物的运动矢量
     * @param entityLivingBase
     * @param vec3d
     */
    public static void setMotion(EntityLivingBase entityLivingBase, Vec3d vec3d){
        if(entityLivingBase!=null){
            entityLivingBase.motionX = vec3d.x;
            entityLivingBase.motionY = vec3d.y;
            entityLivingBase.motionZ = vec3d.z;
        }
    }

    /**
     * 给生物添加一个运动矢量
     * @param entityLivingBase
     * @param vec3d
     */
    public static void addMotion(EntityLivingBase entityLivingBase, Vec3d vec3d){
        if(entityLivingBase!=null){
            entityLivingBase.motionX += vec3d.x;
            entityLivingBase.motionY += vec3d.y;
            entityLivingBase.motionZ += vec3d.z;
            entityLivingBase.fallDistance = 0;
            if(entityLivingBase.isBeingRidden()){
                List<Entity> passengers= entityLivingBase.getPassengers();
                if (passengers.size() > 0) {
                    for(Entity e : passengers){
                        e.fallDistance = 0;
                    }
                }
            }
        }
    }

    /**
     * 给生物添加一个运动矢量
     * @param user
     * @param x
     * @param y
     * @param z
     */
    public static void addMotion(EntityLivingBase user, float x, float y, float z){
        addMotion(user,new Vec3d(x,y,z));
    }

    /**
     * 治疗生物
     * @param entityLivingBase 生物实体
     * @param point 治疗点数s
     */
    public static void healEntity(EntityLivingBase entityLivingBase, int point){
        entityLivingBase.heal(point);
    }

    /**
     * 获取一个便于操作实体的wrapper
     * @param entityLivingBase 生物实体
     * @return wrapper
     */
    public static EntityLivingBaseWrapper getEntityLivingWrapper(EntityLivingBase entityLivingBase){
        return new EntityLivingBaseWrapper(entityLivingBase);
    }

}
