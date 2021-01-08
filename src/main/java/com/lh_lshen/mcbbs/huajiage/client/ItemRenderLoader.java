package com.lh_lshen.mcbbs.huajiage.client;

import com.lh_lshen.mcbbs.huajiage.init.loaders.BlockLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;

public class ItemRenderLoader
{
    public ItemRenderLoader()
    {
    	ItemLoader.registerRenders();
    	BlockLoader.registerRenders();
    }
}