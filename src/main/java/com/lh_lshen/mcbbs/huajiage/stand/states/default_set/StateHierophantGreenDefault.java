package com.lh_lshen.mcbbs.huajiage.stand.states.default_set;

import com.lh_lshen.mcbbs.huajiage.entity.EntityEmeraldBullet;
import com.lh_lshen.mcbbs.huajiage.stand.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import com.lh_lshen.mcbbs.huajiage.util.HAMathHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;

public class StateHierophantGreenDefault extends StandStateBase {
    public StateHierophantGreenDefault() {
    }

    public StateHierophantGreenDefault(String stand, String stateName, boolean isHandPlay, boolean soundLoop) {
        super(stand, stateName, isHandPlay, soundLoop);
    }

    @Override
    public void doTask(EntityLivingBase user) {
        StandBase type = StandUtil.getType(user);
        int stage = StandUtil.getStandStage(user);
        if(type == null) {
            return;
        }
        if(!user.world.isRemote) {
            if(stage>0&&user.ticksExisted%5==0||user.ticksExisted%8==0) {
                Vec3d look = user.getLookVec();
                Vec3d shoot_point = HAMathHelper.getPostionRelative2D(user, -0.55f, -0.6f);
                EntityEmeraldBullet bullet = new EntityEmeraldBullet(user.world, user);
                bullet.setPosition(user.posX+shoot_point.x, user.posY+2.2f, user.posZ+shoot_point.z);
                bullet.setRotation(user.rotationYaw);
                bullet.setPitch(user.rotationPitch);
                float r =(float) Math.random()*360;
                bullet.setRotationRandom(r);
                bullet.setLife(10*20);
                bullet.setDamage(stage>0? StandLoader.HIEROPHANT_GREEN.getDamage()+2:StandLoader.HIEROPHANT_GREEN.getDamage());
                bullet.shoot(user, user.rotationPitch, user.rotationYaw, 0, 1.5f, 0.2f);
                user.getEntityWorld().spawnEntity(bullet);
            }
        }
    }
}
