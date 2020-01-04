package com.lh_lshen.mcbbs.huajiage.util;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
public class NBTHelper {
//*****************实体NBT调用*****************************
	public static int getEntityInteger(Entity entity,String key) {
		return entity.getEntityData().getInteger(key);
	}
	public static boolean getEntityBoolean(Entity entity,String key) {
		return entity.getEntityData().getBoolean(key);
	}
	public static float getEntityFloat(Entity entity,String key) {
		return entity.getEntityData().getFloat(key);
	}
	public static double getEntityDouble(Entity entity,String key) {
		return entity.getEntityData().getDouble(key);
	}
	public static String getEntityString(Entity entity,String key) {
		return entity.getEntityData().getString(key);
	}
	public static void setEntityInteger(Entity entity,String key,int value) {
		entity.getEntityData().setInteger(key, value);
	}
	public static void setEntityBoolean(Entity entity,String key,boolean value) {
		entity.getEntityData().setBoolean(key, value);
	}
	public static void setEntityFloat(Entity entity,String key,float value) {
		entity.getEntityData().setFloat(key, value);
	}
	public static void setEntityDouble(Entity entity,String key,double value) {
		entity.getEntityData().setDouble(key, value);;
	}
	public static void setEntityString(Entity entity,String key,String value) {
		entity.getEntityData().setString(key, value);
	}
//	public static boolean checkEntityNBT(Entity entity,String key) {
//		boolean flag = false ;
//		flag = entity.getEntityData().hasKey(key);
//		return flag;
//	}
//	public static void removeEntityNBT(Entity entity,String key) {
//		entity.getEntityData().removeTag(key);
//	}
//	*****************物品NBT调用*****************************
	public static NBTTagCompound getTagCompoundSafe(ItemStack stack) {
	    NBTTagCompound tagCompound = stack.getTagCompound();
	    if (tagCompound == null) {
	        tagCompound = new NBTTagCompound();
	        stack.setTagCompound(tagCompound);
	    }
	    return tagCompound;
	}
	
}
