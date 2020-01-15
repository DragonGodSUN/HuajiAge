package com.lh_lshen.mcbbs.huajiage.stand;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStandBase;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelStarPlatinum;
import com.lh_lshen.mcbbs.huajiage.client.model.stand.ModelTheWorld;
import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public enum EnumStandtype {
	THE_WORLD("the_world",1.2f,10f,200,2f,60000,21),
	STAR_PLATINUM("star_platinum",1.5f,15f,225,2f,50000,17);
//	,STAR_PLATINUM("star_platinum");
	private EnumStandtype(String name ,float speed ,float damage ,int duration ,float distance ,int cost,int id) {
		this.name=name;
		this.speed=speed;
		this.damage=damage;
		this.duration=duration;
		this.distance=distance;
		this.cost=cost;
		this.id = id;
		
	}
	public static final String EMPTY = "empty";
	private String name;
	private float speed;
	private float damage;
	private int duration;
	private float distance;
	private int cost;
	private int id;
	
	
	public ModelStandBase newModel() {
		switch(name) {
		case("the_world"):
		return new ModelTheWorld();
		case("star_platinum"):
		return new ModelStarPlatinum();
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

	public int getCost() {
		return cost;
	}

	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTexPath() {
		switch(name) {
		case("the_world"):
		return "textures/entity/entity_the_world.png";
		case("star_platinum"):
			return "textures/entity/entity_star_platinum.png";
		}
		return "textures/entity/entity_the_world.png";
	}
	
	public ResourceLocation getTex() {
		return new ResourceLocation(HuajiAge.MODID, getTexPath());
	}

	public static String getLocalName(String name) {
		switch(name){
		case "the_world":
			return HuajiConstant.STAND_THE_WORLD;
		case "star_platinum":
			return HuajiConstant.STAND_STAR_PLATINUM;
		}
		return HuajiConstant.STAND_EMPTY;
	}
	
	public static String getDisplayName(String name,boolean isLoacal) {
		return isLoacal? I18n.format(getLocalName(name)):getLocalName(name);
	}
	
    public static EnumStandtype getType(String string) {
        switch(string) {
        case "the_world":
            return THE_WORLD;
        case "star_platinum":
            return STAR_PLATINUM;
        }
        return null;
    }
    
    public static EnumStandtype getTypeById(int id) {
        switch(id) {
        case 21:
            return THE_WORLD;
        case 17:
            return STAR_PLATINUM;
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
