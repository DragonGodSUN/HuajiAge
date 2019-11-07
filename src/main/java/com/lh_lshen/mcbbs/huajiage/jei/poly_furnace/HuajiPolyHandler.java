package com.lh_lshen.mcbbs.huajiage.jei.poly_furnace;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lh_lshen.mcbbs.huajiage.recipelist.HuajiPolyRecipeList;
import com.lh_lshen.mcbbs.huajiage.recipelist.HuajiRecipeList;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.plugins.vanilla.furnace.SmeltingRecipe;
import net.minecraft.item.ItemStack;


public class HuajiPolyHandler {
	private HuajiPolyHandler() {
	}

	public static List<HuajiPolyWrapper> getHuajiRecipeList(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
		HuajiPolyRecipeList polyRecipeList = HuajiPolyRecipeList.instance();
		Map<ItemStack, ItemStack> smeltingMap = polyRecipeList.getSmeltingList();

		List<HuajiPolyWrapper> recipes = new ArrayList<>();

		for (Map.Entry<ItemStack, ItemStack> entry : smeltingMap.entrySet()) {
			ItemStack input = entry.getKey();
			ItemStack output = entry.getValue();

			List<ItemStack> inputs = stackHelper.getSubtypes(input);
			HuajiPolyWrapper recipe = new HuajiPolyWrapper(inputs, output);
			recipes.add(recipe);
		}

		return recipes;
	}
}
