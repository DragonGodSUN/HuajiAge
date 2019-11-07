package com.lh_lshen.mcbbs.huajiage.client.gui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.inventroy.ContainerHuajiBlader;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityHuajiBlader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiHuajiBlader extends GuiContainer {

	// This is the resource location for the background image
	private static final ResourceLocation texture = new ResourceLocation("huajiage", "textures/gui/container/gui_huaji_blader.png");
	private TileEntityHuajiBlader tileEntity;

	public GuiHuajiBlader(InventoryPlayer invPlayer, TileEntityHuajiBlader tileInventoryFurnace) {
		super(new ContainerHuajiBlader(invPlayer, tileInventoryFurnace));

		// Set the width and height of the gui
		xSize = 176;
		ySize = 156;

		this.tileEntity = tileInventoryFurnace;
	}

	// some [x,y] coordinates of graphical elements
	final int COOK_BAR_XPOS = 76;
	final int COOK_BAR_YPOS = 29;
	final int COOK_BAR_ICON_U = 0;   // texture position of white arrow icon
	final int COOK_BAR_ICON_V = 156;
	final int COOK_BAR_WIDTH = 30;
	final int COOK_BAR_HEIGHT = 17;

	final int FLAME_XPOS = 12;
	final int FLAME_YPOS = 29;
	final int FLAME_ICON_U = 37;   // texture position of flame icon
	final int FLAME_ICON_V = 156;
	final int FLAME_WIDTH = 2;
	final int FLAME_HEIGHT = 18;
	final int FLAME_X_SPACING = 18;

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int x, int y) {
		// Bind the image texture
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		// Draw the image
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		// get cook progress as a double between 0 and 1
		double cookProgress = tileEntity.fractionOfCookTimeComplete();
		// draw the cook progress bar
		drawTexturedModalRect(guiLeft + COOK_BAR_XPOS, guiTop + COOK_BAR_YPOS, COOK_BAR_ICON_U, COOK_BAR_ICON_V,
						              (int)(cookProgress * COOK_BAR_WIDTH), COOK_BAR_HEIGHT);

		// draw the fuel remaining bar for each fuel slot flame
		for (int i = 0; i < tileEntity.FUEL_SLOTS_COUNT; ++i) {
			double burnRemaining = tileEntity.fractionOfFuelRemaining(i);
			int yOffset = (int)((1.0 - burnRemaining) * FLAME_HEIGHT);
			drawTexturedModalRect(guiLeft + FLAME_XPOS + FLAME_X_SPACING * i, guiTop + FLAME_YPOS + yOffset,
														FLAME_ICON_U, FLAME_ICON_V + yOffset, FLAME_WIDTH, FLAME_HEIGHT - yOffset);
		}
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		  
		final int LABEL_XPOS = 5;
		final int LABEL_YPOS = 5;
        fontRenderer.drawString(tileEntity.getDisplayName().getUnformattedText(), LABEL_XPOS, LABEL_YPOS, Color.darkGray.getRGB());

		List<String> hoveringText = new ArrayList<String>();

		// If the mouse is over the progress bar add the progress bar hovering text
		// If the mouse is over one of the burn time indicator add the burn time indicator hovering text
		// If hoveringText is not empty draw the hovering text
		if (!hoveringText.isEmpty()){
			drawHoveringText(hoveringText, mouseX - guiLeft, mouseY - guiTop, fontRenderer);
		}
//		// You must re bind the texture and reset the colour if you still need to use it after drawing a string
//		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
//		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

	}
	 public void drawScreen(int mouseX, int mouseY, float partialTicks)
	    {
	        this.drawDefaultBackground();
	        super.drawScreen(mouseX, mouseY, partialTicks);
	        this.renderHoveredToolTip(mouseX, mouseY);
	    }
	// Returns true if the given x,y coordinates are within the given rectangle
	public static boolean isInRect(int x, int y, int xSize, int ySize, int mouseX, int mouseY){
		return ((mouseX >= x && mouseX <= x+xSize) && (mouseY >= y && mouseY <= y+ySize));
	}
}