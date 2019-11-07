package com.lh_lshen.mcbbs.huajiage.jei.huaji_blader;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public abstract class HuajiBladerCategoryBase <T extends IRecipeWrapper> implements IRecipeCategory<T> {
	protected static final int inputSlot = 0;
	protected static final int fuelSlot = 1;
	protected static final int outputSlot = 2;

	protected final IDrawableStatic staticFlame;
	protected final IDrawableAnimated animatedFlame;
	protected final IDrawableAnimated arrow;
    protected final ResourceLocation RL = new ResourceLocation("huajiage", "textures/jei/gui_huaji_blader_jei.png");
	public HuajiBladerCategoryBase (IGuiHelper guiHelper) {
		
	
		staticFlame = guiHelper.createDrawable(RL,37,156,2, 18);
		animatedFlame = guiHelper.createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);

		arrow = guiHelper.drawableBuilder(RL, 0, 156, 30, 17)
			.buildAnimated(200, IDrawableAnimated.StartDirection.LEFT, false);
	}
	
}