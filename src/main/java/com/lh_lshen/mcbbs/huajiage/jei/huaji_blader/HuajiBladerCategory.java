package com.lh_lshen.mcbbs.huajiage.jei.huaji_blader;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.init.loaders.BlockLoader;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.util.Translator;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;

public class HuajiBladerCategory extends HuajiBladerCategoryBase<HuajiWrapper>{
	private final  IDrawableStatic background;
	private final String title;
	private final IDrawable icon;

   public HuajiBladerCategory(IGuiHelper guiHelper){
	   super(guiHelper);
        title = Translator.translateToLocal(BlockLoader.huajiBlender.getLocalizedName());
        background = guiHelper.createDrawable(RL,0, 0, 106, 54);
        icon = guiHelper.createDrawableIngredient(new ItemStack(BlockLoader.huajiBlender));
     
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
