package com.lh_lshen.mcbbs.huajiage.client;

import com.lh_lshen.mcbbs.huajiage.block.BlockLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;

public class ItemRenderLoader
{
    public ItemRenderLoader()
    {
    	ItemLoader.registerRenders();
    	BlockLoader.registerRenders();
    }
}