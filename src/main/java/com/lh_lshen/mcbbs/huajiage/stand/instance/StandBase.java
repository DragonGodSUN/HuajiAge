package com.lh_lshen.mcbbs.huajiage.stand.instance;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.lh_lshen.mcbbs.huajiage.api.HuajiAgeAPI;
import com.lh_lshen.mcbbs.huajiage.api.IStand;
import com.lh_lshen.mcbbs.huajiage.api.IStandState;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.stand.StandStates;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandRes;
import com.lh_lshen.mcbbs.huajiage.stand.resource.StandResLoader;
import com.lh_lshen.mcbbs.huajiage.stand.states.StandStateBase;
import com.lh_lshen.mcbbs.huajiage.stand.states.default_set.StateTheWorldDefault;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLivingBase;

import java.util.*;

public class StandBase implements IStand {
	protected String name;
	protected float speed;
	protected float damage;
	protected int duration;
	protected int charge;
	protected float distance;
	protected int cost;
	protected int maxMP;
	protected String texPath;
	protected String localName;
	protected List<String> states = new ArrayList<>();
	protected Map<String,StandStateBase> statesMap = Maps.newHashMap();
	protected boolean displayHand;
	public StandBase() {
//		loadStates();
	}
	public StandBase(String name ,float speed ,float damage ,int duration ,float distance ,int cost ,int charge,
			String texPath,String localName, boolean displayHand) {
			this.name=name;
			this.speed=speed;
			this.damage=damage;
			this.duration=duration;
			this.distance=distance;
			this.cost=cost;
			this.charge=charge;
			this.texPath = texPath;
			this.localName = localName;
			this.displayHand = displayHand;
			this.maxMP = this.charge*1200;
//			loadStates();
	}

	public StandBase(String name ,float speed ,float damage ,int duration ,float distance ,int cost ,int charge,
					 int maxMP, String texPath,String localName, boolean displayHand) {
		this.name=name;
		this.speed=speed;
		this.damage=damage;
		this.duration=duration;
		this.distance=distance;
		this.cost=cost;
		this.charge=charge;
		this.maxMP = maxMP;
		this.texPath = texPath;
		this.localName = localName;
		this.displayHand = displayHand;
//			loadStates();
	}


//	public static void main(String[] args) {
//		StandBase s = new StandBase();
//		List<String> list = new ArrayList<>();
//		s.loadStates();
//		list.add("punch");
//		list.add("biu-biu-biu!!!");
//		list.add("punch");
//		s.addStates(list);
//		for(String state : s.states){
//			System.out.println(state);
//		}
//		System.out.println(s.chaeckState("punch"));
//		System.out.println(s.chaeckState("p"));
//	}
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

	public int getCharge() {
		return charge;
	}

	public int getMaxMP() {
		return maxMP;
	}

	public String getName() {
		return name;
	}

	public String getTexPath() {
		return texPath;
	}

	public String getLocalName() {
		return localName;
	}

	public boolean isHandDisplay() {
		return displayHand;
	}

	public StandRes getBindingRes() {
		return StandResLoader.getStand(name)!=null?StandResLoader.getStand(name):StandResLoader.THE_WORLD_RES;
	}

	public Map<String, StandStateBase> getStatesMap() {
		return statesMap;
	}

	public List<String> getStates() {
		return states;
	}

//	public void loadStates() {
//		List<String> list = new ArrayList<>();
//		list.add(CapabilityExposedData.States.DEFAULT.getName());
//
//		this.states = list;
//	}

	public void addStates(List<String> states) {
		this.states.addAll(states);
	}

	public void addStates(Map<String,StandStateBase> states) {
		this.states.addAll(states.keySet());
		this.statesMap.putAll(states);
	}

	public void addState(String state , StandStateBase state_base) {
		this.states.add(state);
		this.statesMap.put(state,state_base);
		HuajiAgeAPI.registerStandState(state_base);

	}

	public void initState(StandStateBase state_base){
		addState(CapabilityExposedData.States.DEFAULT.getName(),state_base);
	}

	public void putInternalStandStates(){
		for(String key : statesMap.keySet()){
			StandStateBase stateBase = statesMap.get(key);
			HuajiAgeAPI.registerStandState(stateBase);
		}
	}

	public boolean chaeckState(String state) {
		return this.states.contains(state);
	}

	@Override
	public void doStandPower(EntityLivingBase user) {
		StandBase type = StandUtil.getType(user);
		IExposedData data = StandUtil.getStandData(user);
		if(type == null && data==null) {
			return;
		}
		IStandState standState = StandStates.getStandState(type.getName(),data.getState());
		if (standState!=null) {
			standState.doTask(user);
		}
	}
	@Override
	public void doStandCapability(EntityLivingBase user) {

	}
	@Override
	public void doStandCapabilityClient(WorldClient world, EntityLivingBase user) {

	}

}
