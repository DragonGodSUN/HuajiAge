package com.lh_lshen.mcbbs.huajiage.api;
//A test structure leaned from @Tartaric_Acid and Sonwnee, follow the MIT License
//Learn More : https://github.com/TartaricAcid/TouhouLittleMaid
import java.util.List;

import com.google.common.collect.Lists;
import com.lh_lshen.mcbbs.huajiage.api.HuajiAgeAPI.IHuajiAgeAPI;

public class HuajiAgeAPIImpl implements IHuajiAgeAPI{
	
	 private final List<IMultiBlock> multiBlockList = Lists.newArrayList();
	 private final List<IStandPower> standList = Lists.newArrayList();
	
	 @Override
	public void registerMultiBlock(IMultiBlock multiBlock) {
		multiBlockList.add(multiBlock);
	}

	@Override
	public List<IMultiBlock> getMultiBlockList() {
		return multiBlockList;
	}

	@Override
	public void registerStand(IStandPower stand) {
		standList.add(stand);
		
	}

	@Override
	public List<IStandPower> getStandList() {
		return standList;
	}

}
