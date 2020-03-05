package com.lh_lshen.mcbbs.huajiage.stand;

public enum EnumStandSkillType {
		TIME_STOP("time_stop"),
		SHOOT("shoot"),
		HIT("hit"),
		AUTO("auto"),
		MAGIC("magic");
	private String id;
	private EnumStandSkillType(String id) {
		this.id=id;
	}
	public String getId() {
		return id;
	}
	public static EnumStandSkillType getTypeWithStringId(String id) {
		switch(id) {
		case("time_stop"):return TIME_STOP;
		case("shoot"):return SHOOT;
		case("hit"):return HIT;
		case("auto"):return AUTO;
		case("magic"):return MAGIC;
		}
		return null;
	}
    public static EnumStandSkillType getTypeWithIndex(int index) {
    	if (index < 0 || index >= values().length) {
            return TIME_STOP;
        }
        return values()[index];
    }

    public static int getLength() {
        return values().length;
    }	
    
}
