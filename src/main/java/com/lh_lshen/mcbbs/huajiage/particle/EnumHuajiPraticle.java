package com.lh_lshen.mcbbs.huajiage.particle;

import net.minecraft.client.particle.IParticleFactory;

public enum EnumHuajiPraticle {
	HUAJI("huaji",2333,ParticleHuaji.FACTORY),
	HUAJISPLASH("huaji_splash",233333,ParticleHuajiSplash.FACTORY),
	EMERALD_SPLAH("emerald_plash",55555,ParticleEmeraldSplash.FACTORY)
	;
	private String name;
    private int id;
    private IParticleFactory particle;

    EnumHuajiPraticle(String name, int id, IParticleFactory particle) {
        this.name = name;
        this.id = id;
        this.particle = particle;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public IParticleFactory getParticle() {
        return particle;
    }
}
