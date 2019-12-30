package com.lh_lshen.mcbbs.huajiage.init.events;

import org.lwjgl.input.Keyboard;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.CapabilityStandSkillHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandHandler;
import com.lh_lshen.mcbbs.huajiage.capability.StandSkillHandler;
import com.lh_lshen.mcbbs.huajiage.client.ItemRenderLoader;
import com.lh_lshen.mcbbs.huajiage.client.KeyLoader;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.item.ItemDiscStand;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.util.EnumStandtype;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//@Mod.EventBusSubscriber(modid = HuajiAge.MODID, value = Side.CLIENT)
public class EventStandOverlatRender {
	    private static final ResourceLocation STAND = new ResourceLocation(HuajiAge.MODID, "textures/items/tarot.png");
	    private static final ResourceLocation STAND_THE_WORLD = new ResourceLocation(HuajiAge.MODID, "textures/items/disc_the_world.png");
	    @SideOnly(Side.CLIENT)
	    @SubscribeEvent
	    public static void onRenderOverlay(RenderGameOverlayEvent event) {
	        if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
	            StandHandler standHandler = Minecraft.getMinecraft().player.getCapability(CapabilityStandHandler.STAND_TYPE, null);
	            StandSkillHandler standSkillHandler = Minecraft.getMinecraft().player.getCapability(CapabilityStandSkillHandler.STAND_SKILL, null);
	            ItemStack disc = ItemDiscStand.getItemData(new ItemStack(ItemLoader.disc),standHandler.getStand(), standSkillHandler.getStage());
	            EnumStandtype stand = EnumStandtype.getType(standHandler.getStand());
	            if(stand == null) {
	            	return;
	            }
	            
	            if (!standHandler.getStand().equals(EnumStandtype.EMPTY)) {
	                int x = (int) (ConfigHuaji.Stands.StandHUDx * Minecraft.getMinecraft().displayWidth/2);
	                int y = (int) (ConfigHuaji.Stands.StandHUDy * Minecraft.getMinecraft().displayHeight/2);
	                GlStateManager.pushMatrix();
	                GlStateManager.enableBlend();
	                GlStateManager.translate(x, y, 0);
	                GlStateManager.scale(1.5, 1.5, 1.5);
//	                Minecraft.getMinecraft().player.getCooldownTracker().getCooldown(disc.getItem(), Minecraft.getMinecraft().getRenderPartialTicks());
	                switch(stand) {
	                case THE_WORLD :Minecraft.getMinecraft().renderEngine.bindTexture(STAND_THE_WORLD);
	                break;
	                }
	                Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 16, 16, 16, 16);
	                GlStateManager.popMatrix();
	                Minecraft.getMinecraft().fontRenderer.drawString( TextFormatting.BOLD+I18n.format("stand.huajiage.name"), 8+ x,  2 + 16 + y, 0xffffff, true);
	                Minecraft.getMinecraft().fontRenderer.drawString( TextFormatting.BOLD+I18n.format(EnumStandtype.getDisplayName(standHandler.getStand())), 13+ x,  10 + 16 + y, 0xffffff, true);
	                Minecraft.getMinecraft().fontRenderer.drawString( TextFormatting.BOLD+I18n.format("stand.huajiage.stage")+"  "+standSkillHandler.getStage(), 8+ x,  20 + 16 + y, 0xffffff, true);
	               if(ConfigHuaji.Stands.allowStandTip) {
	                Minecraft.getMinecraft().fontRenderer.drawString( TextFormatting.BOLD+I18n.format("stand.huajiage.tip",KeyLoader.standUp.getKeyModifier()+"+"+Keyboard.getKeyName(KeyLoader.standUp.getKeyCode())), 5,0, 0xffffff, true);
	               }
	            }
	        }
	    }

}
