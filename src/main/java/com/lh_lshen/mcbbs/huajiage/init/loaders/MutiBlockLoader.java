package com.lh_lshen.mcbbs.huajiage.init.loaders;

import com.lh_lshen.mcbbs.huajiage.api.HuajiAgeAPI;
import com.lh_lshen.mcbbs.huajiage.block.mutiblock.MutiBlockNewLandMan;

public class MutiBlockLoader {
	public MutiBlockLoader() {
		HuajiAgeAPI.registerMultiBlock(new MutiBlockNewLandMan());
	}
}
