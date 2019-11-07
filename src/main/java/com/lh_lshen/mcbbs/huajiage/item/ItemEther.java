package com.lh_lshen.mcbbs.huajiage.item;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.input.Keyboard;

import com.lh_lshen.mcbbs.huajiage.crativetab.CreativeTabLoader;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemEther extends Item {
	public ItemEther()
	{
		 super();
		  this.setCreativeTab(CreativeTabLoader.tabhuaji);
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, @Nullable final World world, final List<String> tooltip, final ITooltipFlag flag) {
		super.addInformation(stack, world, tooltip, flag);
		tooltip.add( I18n.format("item.ether:unicode_tooltips.2.desc"));
		
	}
 

}
