package com.lh_lshen.mcbbs.huajiage.jei.huaji_blader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lh_lshen.mcbbs.huajiage.recipelist.HuajiRecipeList;

import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.recipe.IStackHelper;
import mezz.jei.plugins.vanilla.furnace.SmeltingRecipe;
import net.minecraft.item.ItemStack;


public class HuajiBladerHandler {
	private HuajiBladerHandler() {
	}

	public static List<HuajiWrapper> getHuajiRecipeList(IJeiHelpers helpers) {
		IStackHelper stackHelper = helpers.getStackHelper();
		HuajiRecipeList huajiRecipeList = HuajiRecipeList.instance();
		Map<ItemStack, ItemStack> smeltingMap = huajiRecipeList.getSmeltingList();

		List<HuajiWrapper> recipes = new ArrayList<>();

		for (Map.Entry<ItemStack, ItemStack> entry : smeltingMap.entrySet()) {
			ItemStack input = entry.getKey();
			ItemStack output = entry.getValue();

			List<ItemStack> inputs = stackHelper.getSubtypes(input);
			HuajiWrapper recipe = new HuajiWrapper(inputs, output);
			recipes.add(recipe);
		}

		return recipes;
	}
}
