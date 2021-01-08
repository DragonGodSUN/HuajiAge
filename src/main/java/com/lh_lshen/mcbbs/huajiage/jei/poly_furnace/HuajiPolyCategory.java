package com.lh_lshen.mcbbs.huajiage.jei.poly_furnace;

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

public class HuajiPolyCategory extends HuajiPolyCategoryBase<HuajiPolyWrapper>{
	private final  IDrawableStatic background;
	private final String title;
	private final IDrawable icon;

   public HuajiPolyCategory(IGuiHelper guiHelper){
	   super(guiHelper);
        title = Translator.translateToLocal(BlockLoader.huajiPolyFurnace.getLocalizedName());
        background = guiHelper.createDrawable(RL,0, 0, 109, 55);
        icon = guiHelper.createDrawableIngredient(new ItemStack(BlockLoader.huajiPolyFurnace));
     
    }

  
   @Override
   public String getUid() {
       return HuajiAge.NAME+".poly";
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
		animatedFlame.draw(minecraft, 7, 8);
		arrow.draw(minecraft, 39, 27);
		pool.draw(minecraft,77,13);
	}
	
	@Override
	public String getModName() {
		return HuajiAge.NAME;
	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, HuajiPolyWrapper recipeWrapper, IIngredients ingredients) {
		IGuiItemStackGroup guiItemStacks = recipeLayout.getItemStacks();

		guiItemStacks.init(inputSlot, true, 17, 18);
		guiItemStacks.init(outputSlot, false, 77,18);

		guiItemStacks.set(ingredients);
		
	}


		
	

}
