package com.lh_lshen.mcbbs.huajiage.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class MotionHelper {
	public static Vec3d getVectorEntity(Entity source , Entity target) {
		  BlockPos eater_pos=source.getPosition();
		  BlockPos target_pos=target.getPosition();
		  
		  Vec3d vec = new Vec3d(target_pos.getX()-eater_pos.getX(), target_pos.getY()-eater_pos.getY(), target_pos.getZ()-eater_pos.getZ()).normalize();
		  return vec;
	}
	public static Vec3d getVectorEntityEye(Entity source , Entity target) {
		  Vec3d eater_pos=source.getPositionEyes(0);
		  BlockPos target_pos=target.getPosition();
		  Vec3d vec = new Vec3d(target_pos.getX()-eater_pos.x, target_pos.getY()-eater_pos.y, target_pos.getZ()-eater_pos.z).normalize();
		  return vec;
	}
	
	public static float getAABBSize(AxisAlignedBB box) {
		float a =MathHelper.abs( (float) (box.maxX-box.minX));
		float b =MathHelper.abs( (float) (box.maxY-box.minY));
		float c =MathHelper.abs( (float) (box.maxZ-box.minZ));
		
		return a*b*c;
	}
	public static double getDegreeXZ(Vec3d v1 , Vec3d v2) {
		Vec3d vec1 = v1.add(0, -v1.y, 0).normalize();
		Vec3d vec2 = v2.add(0, -v2.y, 0).normalize();
		double cos =( vec1.x*vec2.x + vec1.z*vec2.z)/(vec1.length()*vec2.length()); 
		double degree =Math.round(Math.toDegrees(Math.acos(cos))) ;
		
		return degree;
	}
}
