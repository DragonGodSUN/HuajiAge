package com.lh_lshen.mcbbs.huajiage.stand.helper.instance;

import com.lh_lshen.mcbbs.huajiage.api.IStandPower;

import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLivingBase;

public class StandBase implements IStandPower{
	private String name;
	private float speed;
	private float damage;
	private int duration;
	private float distance;
	private int cost;
	private String texPath;
	private String localName;
	public StandBase() {
	}
	public StandBase(String name ,float speed ,float damage ,int duration ,float distance ,int cost,
			String texPath,String localName) {
			this.name=name;
			this.speed=speed;
			this.damage=damage;
			this.duration=duration;
			this.distance=distance;
			this.cost=cost;
			this.texPath = texPath;
			this.localName = localName;
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
	@Override
	public void doStandPower(EntityLivingBase user) {
		
	}
	@Override
	public void doStandCapability(EntityLivingBase user) {
		
	}
	@Override
	public void doStandCapabilityClient(WorldClient world, EntityLivingBase user) {
		
	}
}
