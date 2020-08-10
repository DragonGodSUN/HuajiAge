package com.lh_lshen.mcbbs.huajiage.stand.custom.script;

import net.minecraft.world.World;

public class WorldWrapper {
    private World world;

    public WorldWrapper(World world) {
        this.world = world;
    }

    public World getWorld() {
        return this.world;
    }
}
