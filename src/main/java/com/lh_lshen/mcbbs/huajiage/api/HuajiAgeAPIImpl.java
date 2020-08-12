package com.lh_lshen.mcbbs.huajiage.api;
//A test structure leaned from @Tartaric_Acid and Sonwnee, follow the MIT License
//Learn More : https://github.com/TartaricAcid/TouhouLittleMaid
import com.google.common.collect.Lists;
import com.lh_lshen.mcbbs.huajiage.api.HuajiAgeAPI.IHuajiAgeAPI;

import java.util.List;

public class HuajiAgeAPIImpl implements IHuajiAgeAPI{
	
	 private final List<IMultiBlock> multiBlockList = Lists.newArrayList();
	 private final List<IStand> standList = Lists.newArrayList();
	private final List<IStandState> standStateList = Lists.newArrayList();
	
	 @Override
	public void registerMultiBlock(IMultiBlock multiBlock) {
		multiBlockList.add(multiBlock);
	}

	@Override
	public List<IMultiBlock> getMultiBlockList() {
		return multiBlockList;
	}

	@Override
	public void registerStand(IStand stand) {
		standList.add(stand);
	}

	@Override
	public List<IStand> getStandList() {
		return standList;
	}

	@Override
	public void registerStandState(IStandState stand) {
		standStateList.add(stand);

	}

	@Override
	public List<IStandState> getStandStateList() {
		return standStateList;
	}

	@Override
	public void standClear() {
		standList.clear();
	}

	@Override
	public void statesClear() {
		standStateList.clear();
	}
}
