package com.lh_lshen.mcbbs.huajiage.block.mutiblock;

import com.lh_lshen.mcbbs.huajiage.api.HuajiAgeAPI;

public class MutiBlockLoader {
	public MutiBlockLoader() {
		HuajiAgeAPI.registerMultiBlock(new MutiBlockNewLandMan());
	}
}
