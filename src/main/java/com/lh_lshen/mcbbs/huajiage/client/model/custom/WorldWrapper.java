package com.lh_lshen.mcbbs.huajiage.client.model.custom;

import net.minecraft.world.World;

/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */
public class WorldWrapper {
    private final World world;

    public WorldWrapper(World world) {
        this.world = world;
    }

    public long getWorldTime() {
        return world.getWorldTime();
    }

    public boolean isDay() {
        return world.isDaytime();
    }

    public boolean isNight() {
        return !world.isDaytime();
    }

    public boolean isRaining() {
        return world.isRaining();
    }

    public boolean isThundering() {
        return world.isThundering();
    }
}
