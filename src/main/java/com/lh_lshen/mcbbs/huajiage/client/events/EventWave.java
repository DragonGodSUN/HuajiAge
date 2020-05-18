package com.lh_lshen.mcbbs.huajiage.client.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.item.ItemWaveKnife;
import com.lh_lshen.mcbbs.huajiage.util.NBTHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = HuajiAge.MODID, value = Side.CLIENT)
public class EventWave {
	    @SubscribeEvent
	    public static void onRenderOverlay(RenderGameOverlayEvent event) {
	        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
	        	EntityPlayer player = Minecraft.getMinecraft().player;
	            if((player.getHeldItemMainhand().getItem() instanceof ItemWaveKnife)) {
	            	ItemStack stack = player.getHeldItemMainhand();
	            	NBTTagCompound tags = NBTHelper.getTagCompoundSafe(stack);
	            	FontRenderer fontRenderer = Minecraft.getMinecraft().fontRenderer;
	                int x = (int) (ConfigHuaji.Huaji.WaveHUDx * Minecraft.getMinecraft().displayWidth/2);
	                int y = (int) (ConfigHuaji.Huaji.WaveHUDy * Minecraft.getMinecraft().displayHeight/2);
//	                GlStateManager.pushMatrix();
//	                GlStateManager.enableBlend();
//	                GlStateManager.translate( (float) (ConfigHuaji.Huaji.WaveHUDx<0.5?x:x-16), y, 0);
//	                GlStateManager.scale(1.5, 1.5, 1.5);
//	                Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(HuajiAge.MODID,"textures/items/wave_knife.png"));
//	                Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 16, 16, 16, 128);
//	                GlStateManager.disableBlend();
//	                GlStateManager.popMatrix();
	                String wave = TextFormatting.BOLD+I18n.format("item.huajiage.wave_knife.hotbar.wave",NBTHelper.getTagCompoundSafe(stack).getInteger("wave_point"));
	                String reset = TextFormatting.BOLD+I18n.format("item.huajiage.wave_knife.hotbar.wave_reset",(1000-player.ticksExisted%1000)/20);
	                String charge = TextFormatting.BOLD+I18n.format("item.huajiage.wave_knife.hotbar.wave_charge",1+NBTHelper.getTagCompoundSafe(stack).getInteger("wave_charge"));
	                fontRenderer.drawString( wave   , ConfigHuaji.Huaji.WaveHUDx<0.5?x:x-fontRenderer.getStringWidth(wave),  10 + 16 + y, 0xffffff, true);
	                fontRenderer.drawString( reset   ,  ConfigHuaji.Huaji.WaveHUDx<0.5?x:x-fontRenderer.getStringWidth(reset),  20 + 16 + y, 0xffffff, true);
	                fontRenderer.drawString( charge,  ConfigHuaji.Huaji.WaveHUDx<0.5?x:x-fontRenderer.getStringWidth(charge),  30 + 16 + y, 0xffffff, true);
                }
	            
	        }
	                
        }
	        
    }
