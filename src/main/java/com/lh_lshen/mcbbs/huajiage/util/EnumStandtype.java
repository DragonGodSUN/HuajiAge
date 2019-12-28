package com.lh_lshen.mcbbs.huajiage.util;

import com.lh_lshen.mcbbs.huajiage.client.model.ModelTheWorld;

import net.minecraft.client.model.ModelBase;

public enum EnumStandtype {
	THE_WORLD("the_world",1.2f,10f,200,2f);
//	,STAR_PLATINUM("star_platinum");
	private EnumStandtype(String name ,float speed ,float damage ,int duration ,float distance) {
		this.name=name;
		this.speed=speed;
		this.damage=damage;
		this.duration=duration;
		this.distance=distance;
	}
	private String name;
	private float speed;
	private float damage;
	private int duration;
	private float distance;
	
	
	public ModelBase newModel() {
		switch(name) {
		case("the_world"):
		return new ModelTheWorld();
		}
		return new ModelTheWorld();
	}
	
	public float getSpeed() {
		return speed;
	}
	
	public float getDamage() {
		return damage;
	}

	public int getDuration() {
		return duration;
	}

	public float getDistance() {
		return distance;
	}

	public String getName() {
		return name;
	}
	
	public String getTex() {
		switch(name) {
		case("the_world"):
		return "textures/entity/entity_the_world.png";
		}
		return "textures/entity/entity_the_world.png";
	}

    public static EnumStandtype getType(String string) {
        switch(string) {
        case "the_world":
            return THE_WORLD;
        }
        return null;
    }
    public static EnumStandtype getTypeWithIndex(int index) {
    	if (index < 0 || index >= values().length) {
            return THE_WORLD;
        }
        return values()[index];
    }

    public static int getLength() {
        return values().length;
    }

}
