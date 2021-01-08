package com.lh_lshen.mcbbs.huajiage.item;

import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemExpendedView extends Item {   

public ItemExpendedView()
    {
        super();
        this.setCreativeTab(CreativeTabLoader.tabhuaji);

         }
@SideOnly(Side.CLIENT)
public void tex(String view ,Item item,Entity entity) {
	view=entity.getName();
    ModelResourceLocation model = new ModelResourceLocation(view);
    ModelLoader.setCustomModelResourceLocation(item, 0, model);
}
}

