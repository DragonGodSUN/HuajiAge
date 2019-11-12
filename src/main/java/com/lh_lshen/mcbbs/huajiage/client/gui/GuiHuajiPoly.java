package com.lh_lshen.mcbbs.huajiage.client.gui;

import java.awt.Color;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.inventroy.ContainerHuajiBlender;
import com.lh_lshen.mcbbs.huajiage.inventroy.ContainerHuajiPolyfurnace;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityHuajiPolyfurnace;
import com.lh_lshen.mcbbs.huajiage.tileentity.TileEntityHuajiPolyfurnace;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class GuiHuajiPoly extends GuiContainer {

	// This is the resource location for the background image
	private static final ResourceLocation texture = new ResourceLocation("huajiage", "textures/gui/container/gui_huaji_poly_furnace.png");
	private TileEntityHuajiPolyfurnace tileEntity;

	public GuiHuajiPoly(InventoryPlayer invPlayer, TileEntityHuajiPolyfurnace tileInventoryFurnace) {
		super(new ContainerHuajiPolyfurnace(invPlayer, tileInventoryFurnace));

		// Set the width and height of the gui
		xSize = 192;
		ySize = 156;

		this.tileEntity = tileInventoryFurnace;
	}

	// some [x,y] coordinates of graphical elements
	final int COOK_BAR_XPOS = 62;
	final int COOK_BAR_YPOS = 33;
	final int COOK_BAR_ICON_U = 0;   // texture position of white arrow icon
	final int COOK_BAR_ICON_V = 158;
	final int COOK_BAR_WIDTH = 87;
	final int COOK_BAR_HEIGHT = 1;
	
	final int POOL_XPOS = 147;
	final int POOL_YPOS = 19;
	final int POOL_ICON_U = 192;   // texture position of white arrow icon
	final int POOL_ICON_V = 2;
	final int POOL_WIDTH = 28;
	final int POOL_HEIGHT = 19;
	
	final int ENERGY_XPOS = 27;
	final int ENERGY_YPOS = 4;
	final int ENERGY_ICON_U = 246;   // texture position of white arrow icon
	final int ENERGY_ICON_V = 0;
	final int ENERGY_WIDTH = 3;
	final int ENERGY_HEIGHT = 62;

	final int FLAME_XPOS = 10;
	final int FLAME_YPOS = 6;
	final int FLAME_ICON_U = 224;   // texture position of flame icon
	final int FLAME_ICON_V = 0;
	final int FLAME_WIDTH = 16;
	final int FLAME_HEIGHT = 58;


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

			double burnRemaining = tileEntity.fractionOfFuelRemaining();
			int yOffset = (int)((1.0 - burnRemaining) * FLAME_HEIGHT);
			drawTexturedModalRect(guiLeft + FLAME_XPOS, guiTop + FLAME_YPOS + yOffset,
														FLAME_ICON_U, FLAME_ICON_V + yOffset, FLAME_WIDTH, FLAME_HEIGHT - yOffset);
			double energy = MathHelper.clamp((double)tileEntity.getEnergyCurrent()/(double)tileEntity.getEnergyMax(), 0.0, 1.0);
			int energy_yOffset = (int)((1.0 - energy) * ENERGY_HEIGHT);
			drawTexturedModalRect(guiLeft + ENERGY_XPOS, guiTop + ENERGY_YPOS + energy_yOffset,
														ENERGY_ICON_U, ENERGY_ICON_V + energy_yOffset, ENERGY_WIDTH, ENERGY_HEIGHT - energy_yOffset);

			double pool = tileEntity.fractionOfPool();
			int pool_yOffset = (int)((1.0 - pool) * POOL_HEIGHT);
			drawTexturedModalRect(guiLeft + POOL_XPOS, guiTop + POOL_YPOS+ pool_yOffset,
														POOL_ICON_U, POOL_ICON_V + pool_yOffset,POOL_WIDTH, POOL_HEIGHT - pool_yOffset);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
		String name=tileEntity.getDisplayName().getUnformattedText();
		String cookName="gui.huajiage.poly.cook";
		final int LABEL_XPOS = (xSize -ENERGY_XPOS-ENERGY_WIDTH)/2 +ENERGY_XPOS+ENERGY_WIDTH - fontRenderer.getStringWidth(name)/2 -9;
		final int LABEL_YPOS = 10;
		int cookPercentage =(int)(tileEntity.fractionOfCookTimeComplete() * 100);
		String proess=I18n.format(cookName)+"  "+TextFormatting.DARK_AQUA +""+TextFormatting.BOLD +""+ cookPercentage + "%";
		final int cook_x=(xSize -ENERGY_XPOS-ENERGY_WIDTH)/2 +ENERGY_XPOS+ENERGY_WIDTH - fontRenderer.getStringWidth(proess)/2;
        fontRenderer.drawString(TextFormatting.BOLD+name, LABEL_XPOS, LABEL_YPOS, TextFormatting.BLACK.getColorIndex());
        fontRenderer.drawString(proess,cook_x,COOK_BAR_YPOS+9, Color.darkGray.getRGB());
        
		List<String> hoveringText = new ArrayList<String>();

		// If the mouse is over the progress bar add the progress bar hovering text
		// If the mouse is over one of the burn time indicator add the burn time indicator hovering text
		// If hoveringText is not empty draw the hovering text
		if (isInRect(guiLeft + FLAME_XPOS, guiTop + FLAME_YPOS, FLAME_WIDTH, FLAME_HEIGHT, mouseX, mouseY)) {
			hoveringText.add(I18n.format("gui.huajiage.poly.fuel"));
			BigDecimal energy=BigDecimal.valueOf((double)tileEntity.getBurnTime()/1000);
			if(tileEntity.getBurnTime()>1000) {
				hoveringText.add(TextFormatting.AQUA +""+energy+" k");
			}else {
			hoveringText.add(TextFormatting.AQUA +""+tileEntity.getBurnTime()+"");
			}
		}
		if (isInRect(guiLeft + POOL_XPOS, guiTop + POOL_YPOS, POOL_WIDTH, POOL_HEIGHT, mouseX, mouseY)) {
			hoveringText.add(I18n.format("gui.huajiage.poly.pool"));
			hoveringText.add(TextFormatting.YELLOW+""+TextFormatting.BOLD +""+tileEntity.getPool()+"/"+81*9*3);
		}
		if (isInRect(guiLeft + ENERGY_XPOS, guiTop + ENERGY_YPOS, ENERGY_WIDTH, ENERGY_HEIGHT, mouseX, mouseY)) {
			hoveringText.add(I18n.format("gui.huajiage.poly.energy"));
			hoveringText.add(tileEntity.getEnergyCurrent()+"/"+tileEntity.getEnergyMax());
		}
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