package com.lh_lshen.mcbbs.huajiage.jei;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.init.loaders.BlockLoader;
import com.lh_lshen.mcbbs.huajiage.client.gui.GuiHuajiBlender;
import com.lh_lshen.mcbbs.huajiage.client.gui.GuiHuajiPoly;
import com.lh_lshen.mcbbs.huajiage.inventroy.ContainerHuajiBlender;
import com.lh_lshen.mcbbs.huajiage.inventroy.ContainerHuajiPolyfurnace;
import com.lh_lshen.mcbbs.huajiage.jei.huaji_blader.HuajiBladerCategory;
import com.lh_lshen.mcbbs.huajiage.jei.huaji_blader.HuajiBladerHandler;
import com.lh_lshen.mcbbs.huajiage.jei.poly_furnace.HuajiPolyCategory;
import com.lh_lshen.mcbbs.huajiage.jei.poly_furnace.HuajiPolyHandler;

import mezz.jei.Internal;
import mezz.jei.api.IJeiHelpers;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientRegistry;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import mezz.jei.api.recipe.IVanillaRecipeFactory;
import mezz.jei.api.recipe.transfer.IRecipeTransferRegistry;
import mezz.jei.gui.GuiHelper;
import mezz.jei.runtime.JeiHelpers;
import net.minecraft.item.ItemStack;

@JEIPlugin
public class HuajiAgeJeiPlugin implements IModPlugin {


	@Override
	public void registerCategories(IRecipeCategoryRegistration registry) {
		JeiHelpers jeiHelpers = Internal.getHelpers();
		GuiHelper guiHelper = jeiHelpers.getGuiHelper();
		registry.addRecipeCategories(
			new HuajiBladerCategory(guiHelper),
			new HuajiPolyCategory(guiHelper));
		
	}
	

	@Override
	public void register(IModRegistry registry) {
		IIngredientRegistry ingredientRegistry = registry.getIngredientRegistry();
		IJeiHelpers jeiHelpers = registry.getJeiHelpers();
		IVanillaRecipeFactory vanillaRecipeFactory = jeiHelpers.getVanillaRecipeFactory();
		registry.addRecipes(HuajiBladerHandler.getHuajiRecipeList(jeiHelpers),HuajiAge.NAME+".blader");
		registry.addRecipes(HuajiPolyHandler.getHuajiRecipeList(jeiHelpers),HuajiAge.NAME+".poly");

		registry.addRecipeClickArea(GuiHuajiBlender.class, 86, 30, 10, 13, HuajiAge.NAME+".blader");
		registry.addRecipeClickArea(GuiHuajiPoly.class, 77, 32, 62, 3, HuajiAge.NAME+".poly");

		IRecipeTransferRegistry recipeTransferRegistry = registry.getRecipeTransferRegistry();

		recipeTransferRegistry.addRecipeTransferHandler(ContainerHuajiBlender.class,HuajiAge.NAME+".blader", 37, 1, 0, 35);
		recipeTransferRegistry.addRecipeTransferHandler(ContainerHuajiPolyfurnace.class,HuajiAge.NAME+".poly", 0, 1, 3, 35);

		registry.addRecipeCatalyst(new ItemStack(BlockLoader.huajiBlender),HuajiAge.NAME+".blader");
		registry.addRecipeCatalyst(new ItemStack(BlockLoader.huajiPolyFurnace),HuajiAge.NAME+".poly");


	}
}
