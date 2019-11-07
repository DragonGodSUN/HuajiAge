package com.lh_lshen.mcbbs.huajiage.jei.poly_furnace;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawableAnimated;
import mezz.jei.api.gui.IDrawableStatic;
import mezz.jei.api.recipe.IRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.util.ResourceLocation;

public abstract class HuajiPolyCategoryBase  <T extends IRecipeWrapper> implements IRecipeCategory<T> {
	protected static final int inputSlot = 0;
	protected static final int fuelSlot = 1;
	protected static final int outputSlot = 2;

	protected final IDrawableStatic staticFlame;
	protected final IDrawableAnimated animatedFlame;
	protected final IDrawableAnimated arrow;
	protected final IDrawableAnimated pool;
    protected final ResourceLocation RL = new ResourceLocation("huajiage", "textures/jei/gui_huaji_poly_jei.png");
	public HuajiPolyCategoryBase (IGuiHelper guiHelper) {
		
	
		staticFlame = guiHelper.createDrawable(RL,233,5,6, 31);
		animatedFlame = guiHelper.createAnimatedDrawable(staticFlame, 300, IDrawableAnimated.StartDirection.TOP, true);

		arrow = guiHelper.drawableBuilder(RL, 2, 62, 37, 1)
			.buildAnimated(100, IDrawableAnimated.StartDirection.LEFT, false);
		pool = guiHelper.drawableBuilder(RL, 202, 6,23, 19)
				.buildAnimated(500, IDrawableAnimated.StartDirection.BOTTOM,false);
	}
}
