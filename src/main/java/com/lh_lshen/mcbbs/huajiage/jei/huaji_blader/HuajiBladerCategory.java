package com.lh_lshen.mcbbs.huajiage.jei.huaji_blader;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.apache.http.util.TextUtils;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.block.BlockLoader;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class HuajiBladerCategory extends HuajiBladerCategoryBase<HuajiWrapper>{
	private final  IDrawableStatic background;
	private final String title;
	private final IDrawable icon;

   public HuajiBladerCategory(IGuiHelper guiHelper){
	   super(guiHelper);
        title = Translator.translateToLocal(BlockLoader.huajiBlader.getLocalizedName());
        background = guiHelper.createDrawable(RL,0, 0, 106, 54);
        icon = guiHelper.createDrawableIngredient(new ItemStack(BlockLoader.huajiBlader));
     
    }

  
   @Override
   public String getUid() {
       return HuajiAge.NAME+".blader";
   }

  
   @Override
   public String getTitle() {
       return title;
   }
   @Override
	public IDrawable getIcon() {
		return icon;
	}
   
   @Override
   public IDrawable getBackground() {
       return background;
   }
  
   @Override
	public void drawExtras(Minecraft minecraft) {
		animatedFlame.draw(minecraft, 13, 18);
		arrow.draw(minecraft, 38, 18);
	}
	
	@Override
	public String getModName() {
		return HuajiAge.NAME;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, HuajiWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(inputSlot, true, 17, 18);
		guiItemStacks.init(outputSlot, false, 71,18);

		guiItemStacks.set(ingredients);
		
	}


		
	

}
