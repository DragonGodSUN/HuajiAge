package com.lh_lshen.mcbbs.huajiage.jei.poly_furnace;

import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.recipelist.HuajiPolyRecipeList;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;

import java.util.Collections;
import java.util.List;

public class HuajiPolyWrapper implements IRecipeWrapper {
	private final List<List<ItemStack>> inputs;
	private final List<ItemStack> input;
	private final ItemStack output;

	public HuajiPolyWrapper(List<ItemStack> inputs, ItemStack output) {
		this.inputs = Collections.singletonList(inputs);
		this.output = output;
		this.input  = inputs;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(VanillaTypes.ITEM, inputs);
		ingredients.setOutput(VanillaTypes.ITEM, output);
	}

	@Override
	public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
		HuajiPolyRecipeList furnaceRecipes = HuajiPolyRecipeList.instance();
		for(int i=0;i<input.size();i++) {
		    ItemStack item=input.get(i);
		    	if(item!=null) {
		    		int pool = furnaceRecipes.getPoint(item)+furnaceRecipes.getPool(item);
		if (pool > 0) {
			String poolString = Translator.translateToLocalFormatted("gui.jei.category.huajiage.poly.pool", pool);
			String points = Translator.translateToLocalFormatted("gui.jei.category.huajiage.poly.full", ConfigHuaji.Huaji.point_star);
			FontRenderer fontRenderer = minecraft.fontRenderer;
			int stringWidthP = fontRenderer.getStringWidth(poolString);
			int stringWidthN = fontRenderer.getStringWidth(points);
			fontRenderer.drawString(TextFormatting.BOLD+poolString, recipeWidth/2-stringWidthP/2,recipeHeight-15, TextFormatting.AQUA.getColorIndex());
			fontRenderer.drawString(TextFormatting.BOLD+points, recipeWidth - stringWidthN -8,recipeHeight - 53, TextFormatting.GRAY.getColorIndex());
		}       
				}
			}
		}

	
	
	
		
}
