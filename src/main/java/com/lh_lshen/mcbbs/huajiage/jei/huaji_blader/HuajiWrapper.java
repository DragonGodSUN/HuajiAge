package com.lh_lshen.mcbbs.huajiage.jei.huaji_blader;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import com.lh_lshen.mcbbs.huajiage.recipelist.HuajiRecipeList;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.Translator;

public class HuajiWrapper implements IRecipeWrapper {
	private final List<List<ItemStack>> inputs;
	private final ItemStack output;

	public HuajiWrapper(List<ItemStack> inputs, ItemStack output) {
		this.inputs = Collections.singletonList(inputs);
		this.output = output;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(VanillaTypes.ITEM, inputs);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		HuajiRecipeList furnaceRecipes = HuajiRecipeList.instance();
		float experience = furnaceRecipes.getSmeltingExperience(output);
		if (experience > 0) {
			String experienceString = Translator.translateToLocalFormatted("gui.jei.category.smelting.experience", experience);
			FontRenderer fontRenderer = minecraft.fontRenderer;
			int stringWidth = fontRenderer.getStringWidth(experienceString);
			fontRenderer.drawString(experienceString, recipeWidth - stringWidth, 0, Color.gray.getRGB());
		}
	}
}
