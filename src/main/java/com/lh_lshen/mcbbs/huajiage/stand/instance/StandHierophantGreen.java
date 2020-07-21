package com.lh_lshen.mcbbs.huajiage.stand.instance;

import com.lh_lshen.mcbbs.huajiage.capability.CapabilityExposedData;
import com.lh_lshen.mcbbs.huajiage.entity.EntityEmeraldBullet;
import com.lh_lshen.mcbbs.huajiage.init.sound.SoundLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.messages.MessageDoStandPowerClient;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandRes;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandResLoader;
import com.lh_lshen.mcbbs.huajiage.stand.states.default_set.StateHierophantGreenDefault;
import com.lh_lshen.mcbbs.huajiage.stand.states.idle.StateHierophantGreenIdle;
import com.lh_lshen.mcbbs.huajiage.util.HAMathHelper;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;
import com.lh_lshen.mcbbs.huajiage.util.ServerUtil;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

import java.util.List;
import java.util.Random;

public class StandHierophantGreen extends StandBase {

    public StandHierophantGreen() {
        super();
    }

    public StandHierophantGreen(String name, float speed, float damage, int duration, float distance, int cost, int charge,
                                String texPath, String localName, boolean displayHand) {
        super(name, speed, damage, duration, distance, cost, charge, texPath, localName, displayHand);
        initState(new StateHierophantGreenDefault(name, CapabilityExposedData.States.DEFAULT.getName(), isHandDisplay(), true));
        addState(CapabilityExposedData.States.IDLE.getName(), new StateHierophantGreenIdle(name, CapabilityExposedData.States.IDLE.getName(), true, false));
    }

    @Override
    public StandRes getBindingRes() {
        return StandResLoader.HIEROPHANT_GREEN_RES;
    }

    @Override
    public void doStandPower(EntityLivingBase user) {
        super.doStandPower(user);
    }

    @Override
    public void doStandCapability(EntityLivingBase user) {
        Vec3d look = user.getLookVec();
        Vec3d dist = new Vec3d(user.posX + 5 * look.x, user.posY + 5 * look.y, user.posZ + 5 * look.z);
        List<EntityLivingBase> entityCllection = user.getEntityWorld().getEntitiesWithinAABB(EntityLivingBase.class, user.getEntityBoundingBox().grow(StandLoader.HIEROPHANT_GREEN.getDistance()));
        if (entityCllection.size() > 0) {
            for (EntityLivingBase i : entityCllection) {
                if (i != user) {
                    Vec3d vec = HAMathHelper.getVectorEntityEye(user, i);
                    float degree1 = (float) HAMathHelper.getDegreeXZ(look, vec);
                    float degree2 = (float) HAMathHelper.getDegreeXY(look, vec);
                    float degree3 = (float) HAMathHelper.getDegreeXZ(look, vec);
                    if (degree1 < 15 && degree2 < 15 && degree3 < 15) {
                        dist = i.getPositionVector();
                        doEmeraldSlash(dist, user);
                        break;
                    }
                }
            }
        }
        if (dist != null) {
            doEmeraldSlash(dist, user);
        }

        if (user instanceof EntityPlayer) {
            ServerUtil.sendPacketToNearbyPlayersStand(user, new MessageDoStandPowerClient((EntityPlayer) user, StandLoader.HIEROPHANT_GREEN.getName()));
        }
    }

    @Override
    public void doStandCapabilityClient(WorldClient world, EntityLivingBase user) {

        world.playSound(user.getPositionVector().x, user.getPositionVector().y, user.getPositionVector().z, SoundLoader.STAND_HIEROPHANT_GREEN_EMERALD_SPLASH, SoundCategory.PLAYERS, 5f, 1f, true);

    }

    private void doEmeraldSlash(Vec3d dist, EntityLivingBase user) {
        if (dist != null) {
            for (int i = 0; i < 100; i++) {
                float rx = MathHelper.nextFloat(new Random(), -20, 20);
                float ry = MathHelper.nextFloat(new Random(), 0, 20);
                float rz = MathHelper.nextFloat(new Random(), -20, 20);
                Vec3d pos = new Vec3d(user.posX + rx, user.posY + ry, user.posZ + rz);
                Vec3d v = HAMathHelper.getVector(pos, dist);
//			if(!isBlocked(user.world, pos)) {

                EntityEmeraldBullet bullet = new EntityEmeraldBullet(user.world, user);
                bullet.setPosition(pos.x, pos.y, pos.z);
                NBTHelper.setEntityFloat(bullet, "huajiage.motion.x", (float) v.x);
                NBTHelper.setEntityFloat(bullet, "huajiage.motion.y", (float) v.y);
                NBTHelper.setEntityFloat(bullet, "huajiage.motion.z", (float) v.z);
                bullet.setSplashHuge(true);
                bullet.setLife(360f);
                bullet.setStayTime(50);
                bullet.rotationYaw = 360 * rx / 20;
                bullet.rotationPitch = 360 * ry / 20;
                bullet.setRotation(bullet.rotationYaw);
                bullet.setPitch(bullet.rotationPitch);
                bullet.setDamage(5f);
                user.world.spawnEntity(bullet);
//			}
            }
        }
    }
}
