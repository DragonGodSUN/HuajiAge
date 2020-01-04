package com.lh_lshen.mcbbs.huajiage.util;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class MotionHelper {
	public static Vec3d getVectorEntity(Entity source , Entity target) {
		  BlockPos eater_pos=source.getPosition();
		  BlockPos target_pos=target.getPosition();
		  Vec3d vec = new Vec3d(target_pos.getX()-eater_pos.getX(), target_pos.getY()-eater_pos.getY(), target_pos.getZ()-eater_pos.getZ()).normalize();
		  return vec;
	}
	public static Vec3d getVectorEntityEye(Entity source , Entity target) {
		  Vec3d eater_pos=source.getPositionEyes(source.ticksExisted);
		  BlockPos target_pos=target.getPosition();
		  Vec3d vec = new Vec3d(target_pos.getX()-eater_pos.x, target_pos.getY()-eater_pos.y, target_pos.getZ()-eater_pos.z).normalize();
		  return vec;
	}
}
