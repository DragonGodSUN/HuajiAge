package com.lh_lshen.mcbbs.huajiage.stand.custom.script;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.Vec3d;
/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */
public class EntityLivingBaseWrapper {
    private EntityLivingBase livingBase;

    public EntityLivingBaseWrapper(EntityLivingBase livingBase) {
        this.livingBase = livingBase;
    }

    public EntityLivingBase getLivingBase() {
        return this.livingBase;
    }

    public float getYaw() {
        return this.livingBase.rotationYaw;
    }

    public float getPitch() {
        return this.livingBase.rotationPitch;
    }

    public Vec3d getPos() {
        return this.livingBase.getPositionVector();
    }

    public Vec3d getRotationVector(double x, double y, double z, float yawIn, double yOffset) {
        EntityLivingBase entity = this.getLivingBase();
        float yaw = (entity.rotationYaw + yawIn) * -0.01745329251f;
        Vec3d pos = entity.getPositionVector();
        return (new Vec3d(x, y, z)).rotateYaw(yaw).add(pos.x, pos.y + entity.getEyeHeight() + yOffset, pos.z);
    }

    public Vec3d getLookVec(){
       return this.livingBase.getLookVec();
    }
}
