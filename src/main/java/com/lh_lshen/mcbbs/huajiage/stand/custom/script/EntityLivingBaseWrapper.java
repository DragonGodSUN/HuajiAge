package com.lh_lshen.mcbbs.huajiage.stand.custom.script;

import com.github.tartaricacid.touhoulittlemaid.danmaku.script.Vec3dWrapper;
import net.minecraft.entity.EntityLivingBase;

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

    public Vec3dWrapper getPos() {
        return new Vec3dWrapper(this.livingBase.getPositionVector());
    }
}
