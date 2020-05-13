package com.lh_lshen.mcbbs.huajiage.client.render.model;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IModelRegister {
	
	@SideOnly(Side.CLIENT)
	void registerModels();
}
