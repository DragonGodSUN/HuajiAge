package com.lh_lshen.mcbbs.huajiage.stand.custom.script;

import com.lh_lshen.mcbbs.huajiage.stand.entity.EntityStandBase;
import com.lh_lshen.mcbbs.huajiage.stand.helper.StandPowerHelper;
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

    public int ticksExisted() {
        return this.livingBase.ticksExisted;
    }

    public float getYaw() {
        return this.livingBase.rotationYaw;
    }

    public float getPitch() {
        return this.livingBase.rotationPitch;
    }

    public Vec3dWrapper getPos() {
        return new Vec3dWrapper(this.livingBase.getPositionVector());
    }

    public Vec3dWrapper getEyePos() {
        return new Vec3dWrapper(this.livingBase.getPositionEyes(0f));
    }

    public Vec3dWrapper getLookVec(){
       return new Vec3dWrapper(this.livingBase.getLookVec());
    }

    public float getSpeed(){
        EntityLivingBase standEntity = StandPowerHelper.getUserStand(this.livingBase);
        float s = (float) new Vec3d(livingBase.motionX, livingBase.motionY, livingBase.motionZ).length();
        if (standEntity!=null) {
            s = (float) (new Vec3d(standEntity.motionX, standEntity.motionY, standEntity.motionZ).length()-0.784);
        }
        return s;
    }

    public EntityStandBase getStandEntity(){
        return StandPowerHelper.getUserStand(this.livingBase);
    }
}
