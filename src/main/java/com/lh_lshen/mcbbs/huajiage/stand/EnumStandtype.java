package com.lh_lshen.mcbbs.huajiage.stand;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.common.HuajiConstant;
import com.lh_lshen.mcbbs.huajiage.stand.helper.instance.IStandPower;
import com.lh_lshen.mcbbs.huajiage.stand.helper.instance.StandHieropantGreen;
import com.lh_lshen.mcbbs.huajiage.stand.helper.instance.StandStarPlatinum;
import com.lh_lshen.mcbbs.huajiage.stand.helper.instance.StandTheWorld;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;

public enum EnumStandtype {
	THE_WORLD("the_world",1.2f,10f,200,2f,60000,new StandTheWorld(),"textures/entity/entity_the_world.png",HuajiConstant.StandType.STAND_THE_WORLD),
	STAR_PLATINUM("star_platinum",1.5f,15f,275,2f,50000,new StandStarPlatinum(),"textures/entity/entity_star_platinum.png",HuajiConstant.StandType.STAND_STAR_PLATINUM),
	HIEROPHANT_GREEN("hierophant_green",1.0f,5f,200,20f,70000,new StandHieropantGreen(),"textures/entity/entity_hierophant_green.png",HuajiConstant.StandType.STAND_HIEROPANT_GREEN);
	
	private EnumStandtype(String name ,float speed ,float damage ,int duration ,float distance ,int cost,
			IStandPower power,String texPath,String localName) {
		this.name=name;
		this.speed=speed;
		this.damage=damage;
		this.duration=duration;
		this.distance=distance;
		this.cost=cost;
		this.power = power;
		this.texPath = texPath;
		this.localName = localName;
		
	}
	public static final String EMPTY = "empty";
	private String name;
	private float speed;
	private float damage;
	private int duration;
	private float distance;
	private int cost;
	private IStandPower power;
	private String texPath;
	private String localName;
	
	
	public IStandPower getStandPower() {
		return power;
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
	
	public String getTexPath() {
		return texPath;
	}
	
	public ResourceLocation getTex() {
		return new ResourceLocation(HuajiAge.MODID, getTexPath());
	}

	public String getLocalName() {
		return localName;
	}
	
	public static String getLocalName(String name) {
		for(EnumStandtype type : values()) {
    		if(name.equals(type.getName())) {
    			return type.getLocalName();
    		}
    	}
		return HuajiConstant.StandType.STAND_EMPTY;
	}
	
	public static String getDisplayName(String name,boolean isLocal) {
		return isLocal? I18n.format(getLocalName(name)):getLocalName(name);
	}
	
    public static EnumStandtype getType(String string) {
    	for(EnumStandtype type : values()) {
    		if(string.equals(type.getName())) {
    			return type;
    		}
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
