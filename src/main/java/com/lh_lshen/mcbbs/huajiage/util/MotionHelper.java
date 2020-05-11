package com.lh_lshen.mcbbs.huajiage.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
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
		  Vec3d target_pos=target.getPositionEyes(0);
		  Vec3d vec = new Vec3d(target_pos.x-eater_pos.x, target_pos.y-eater_pos.y, target_pos.z-eater_pos.z).normalize();
		  return vec;
	}
	public static Vec3d getVector(Vec3d source_pos , Vec3d target_pos) {
		  Vec3d vec = new Vec3d(target_pos.x-source_pos.x, target_pos.y-source_pos.y, target_pos.z-source_pos.z).normalize();
		  return vec;
	}
	public static double getDistance(Vec3d source_pos , Vec3d target_pos) {
		  double vec = new Vec3d(target_pos.x-source_pos.x, target_pos.y-source_pos.y, target_pos.z-source_pos.z).length();
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
	
	public static double getDegreeXY(Vec3d v1 , Vec3d v2) {
		Vec3d vec1 = v1.add(0, 0, -v1.z).normalize();
		Vec3d vec2 = v2.add(0, 0, -v2.z).normalize();
		double cos =( vec1.x*vec2.x + vec1.y*vec2.y)/(vec1.length()*vec2.length()); 
		double degree =Math.round(Math.toDegrees(Math.acos(cos))) ;
		
		return degree;
	}
	
	public static double getDegreeZY(Vec3d v1 , Vec3d v2) {
		Vec3d vec1 = v1.add(-v1.x, 0, 0).normalize();
		Vec3d vec2 = v2.add(-v2.x, 0, 0).normalize();
		double cos =( vec1.z*vec2.z + vec1.y*vec2.y)/(vec1.length()*vec2.length()); 
		double degree =Math.round(Math.toDegrees(Math.acos(cos))) ;
		
		return degree;
	}
	
	public static Vec3d getVecPlus(Vec3d v1 , Vec3d v2 ,double l1 , double l2 ) {
		
		return new Vec3d(l1*v1.x+l2*v2.x , l1*v1.y+l2*v2.y , l1*v1.z+l2*v2.z);
		
	}
	
	public static Vec3d getVecPlus(Vec3d v1 , Vec3d v2 ,Vec3d v3,double l1 , double l2 , double l3) {
			
			return new Vec3d(l1*v1.x+l2*v2.x+l3*v3.x , l1*v1.y+l2*v2.y+l3*v3.y , l1*v1.z+l2*v2.z+l3*v3.z);
			
		}
	
	public static Vec3d getPostionRelative2D(Entity entity , float x , float z ) {
		float yaw = entity.rotationYaw;
		if(entity instanceof EntityLivingBase) {
			yaw = ((EntityLivingBase)entity).renderYawOffset;
		}
		Vec3d forward = getVectorForRotation(0, yaw);
		Vec3d vertical = getVectorForRotation(0,yaw + 90);

		return getVecPlus(vertical, forward ,x ,z);
		
	}
	
	public static Vec3d getVectorForRotation(float pitch, float yaw)
    {
        float f = MathHelper.cos(-yaw * 0.017453292F - (float)Math.PI);
        float f1 = MathHelper.sin(-yaw * 0.017453292F - (float)Math.PI);
        float f2 = -MathHelper.cos(-pitch * 0.017453292F);
        float f3 = MathHelper.sin(-pitch * 0.017453292F);
        return new Vec3d((double)(f1 * f2), (double)f3, (double)(f * f2));
    }
}
