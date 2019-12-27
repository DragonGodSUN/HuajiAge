package com.lh_lshen.mcbbs.huajiage.util;

import com.lh_lshen.mcbbs.huajiage.client.model.ModelTheWorld;

import net.minecraft.client.model.ModelBase;

public enum EnumStandtype {
	THE_WORLD("the_world");
//	,STAR_PLATINUM("star_platinum");
	private EnumStandtype(String name) {
		this.name=name;
	}
	private String name;
	
	public String getname() {
		return name;
	}
	public ModelBase newModel() {
		switch(name) {
		case("the_world"):
		return new ModelTheWorld();
		}
		return new ModelTheWorld();
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

	    public static int getLength() {
	        return values().length;
	    }
	
}
