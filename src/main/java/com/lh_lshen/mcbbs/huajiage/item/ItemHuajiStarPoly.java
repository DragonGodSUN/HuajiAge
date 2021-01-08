package com.lh_lshen.mcbbs.huajiage.item;

import java.util.List;

import com.lh_lshen.mcbbs.huajiage.init.loaders.CreativeTabLoader;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemHuajiStarPoly extends Item {
	public ItemHuajiStarPoly()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
	}
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(I18n.format("message.huajiage.poly_huaji")+TextFormatting.WHITE+""+NBTHelper.getTagCompoundSafe(stack).getInteger("poly"));
	}

}
