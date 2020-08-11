package com.lh_lshen.mcbbs.huajiage.stand.custom.script;

import net.minecraft.world.World;
/**
 * 此类代码基于酒石酸团队“车万女仆”模组代码，依据MIT协议进行编写
 * 更多内容请转至：https://github.com/TartaricAcid/TouhouLittleMaid
 */
public class WorldWrapper {
    private World world;

    public WorldWrapper(World world) {
        this.world = world;
    }

    public World getWorld() {
        return this.world;
    }
}
