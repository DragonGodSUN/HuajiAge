package com.lh_lshen.mcbbs.huajiage.stand.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.capability.IExposedData;
import com.lh_lshen.mcbbs.huajiage.capability.StandChargeHandler;
import com.lh_lshen.mcbbs.huajiage.client.KeyLoader;
import com.lh_lshen.mcbbs.huajiage.client.resources.CustomResourceLoader;
import com.lh_lshen.mcbbs.huajiage.client.resources.pojo.StandModelInfo;
import com.lh_lshen.mcbbs.huajiage.config.ConfigHuaji;
import com.lh_lshen.mcbbs.huajiage.init.loaders.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.PotionLoader;
import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import com.lh_lshen.mcbbs.huajiage.stand.StandClientUtil;
import com.lh_lshen.mcbbs.huajiage.stand.StandUtil;
import com.lh_lshen.mcbbs.huajiage.stand.instance.StandBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.input.Keyboard;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = HuajiAge.MODID, value = Side.CLIENT)
public class EventStandOverlatRender {
//	    private static final ResourceLocation STAND = new ResourceLocation(HuajiAge.MODID, "textures/items/tarot.png");
//	    private static final ResourceLocation STAND_THE_WORLD = new ResourceLocation(HuajiAge.MODID, "textures/items/disc_the_world.png");
//	    private static final ResourceLocation STAND_STAR_PLATINUM = new ResourceLocation(HuajiAge.MODID, "textures/items/disc_star_platinum.png");
//	    private static final ResourceLocation STAND_HIEROPANT_GREEN = new ResourceLocation(HuajiAge.MODID, "textures/items/disc_hierophant_green.png");

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void renderStandFirst(RenderHandEvent event)
	{
		World world = Minecraft.getMinecraft().world;
		EntityPlayer player = Minecraft.getMinecraft().player;
		ItemStack stack = player.getHeldItemMainhand();
		StandBase stand =StandUtil.getType(player);
		int perspective = Minecraft.getMinecraft().gameSettings.thirdPersonView;
		boolean f1 = Minecraft.getMinecraft().gameSettings.hideGUI;
		IExposedData data = StandUtil.getStandData(player);

		if (stand!=null && stack.getItem() != ItemLoader.roadRoller &&player.isPotionActive(PotionLoader.potionStand) && perspective == 0 && !f1)
		{
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.disableLighting();

			StandClientUtil.standRender(player);

			GlStateManager.enableLighting();

			if (!data.isHandDisplay())
			{
				event.setCanceled(true);
			}

			GlStateManager.scale(1, 1, 1);
			GlStateManager.disableBlend();
			GlStateManager.popMatrix();

		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onRenderOverlay(RenderGameOverlayEvent event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
			EntityPlayer player = Minecraft.getMinecraft().player;
//			StandHandler standHandler = player.getCapability(CapabilityStandHandler.STAND_TYPE, null);
//			StandStageHandler standStageHandler = player.getCapability(CapabilityStandStageHandler.STAND_STAGE, null);
			StandChargeHandler chargeHandler = StandUtil.getChargeHandler(player);
			IExposedData data = StandUtil.getStandData(player);
			StandBase stand = StandUtil.getType(player);
			if (data == null || stand == null || chargeHandler == null) {
				return;
			}
			int stage = data.getStage();
			int charge = chargeHandler.getChargeValue();
			int maxCharge = chargeHandler.getMaxValue();

			if (!data.getStand().equals(StandLoader.EMPTY)) {
				boolean isAuto = Minecraft.getMinecraft().gameSettings.guiScale == 0 && ( Minecraft.getMinecraft().displayWidth>1280 && Minecraft.getMinecraft().displayHeight>720 || Minecraft.getMinecraft().isFullScreen() ) ;
				int x = (int) (ConfigHuaji.Stands.standHUDx * Minecraft.getMinecraft().displayWidth / (isAuto?4:2) );
				int y = (int) (ConfigHuaji.Stands.standHUDy * Minecraft.getMinecraft().displayHeight / (isAuto?4:2));
				GlStateManager.pushMatrix();
				GlStateManager.enableBlend();
				GlStateManager.translate(x, y, 0);
				GlStateManager.scale(1.5, 1.5, 1.5);

				Minecraft.getMinecraft().renderEngine.bindTexture(StandUtil.getDiscTex(stand));
				Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, 16, 16, 16, 16);
				GlStateManager.disableBlend();
				GlStateManager.popMatrix();

				if (!data.getModel().equals(StandLoader.EMPTY)) {
					if (CustomResourceLoader.STAND_MODEL.getInfo(data.getModel()).isPresent()) {
						StandModelInfo info = CustomResourceLoader.STAND_MODEL.getInfo(data.getModel()).get();
						StringBuilder builder = new StringBuilder(info.getName());
						builder.deleteCharAt(0);
						builder.deleteCharAt(builder.indexOf("}"));
						Minecraft.getMinecraft().fontRenderer.drawString(TextFormatting.BOLD + I18n.format(builder.toString()), 13 + x, 10 + 16 + y, 0xffffff, true);
					}
				} else {
					Minecraft.getMinecraft().fontRenderer.drawString(TextFormatting.BOLD + I18n.format(StandUtil.getLocalName(data.getStand())), 13 + x, 10 + 16 + y, 0xffffff, true);
				}
				Minecraft.getMinecraft().fontRenderer.drawString(TextFormatting.BOLD + I18n.format("stand.huajiage.name"), 8 + x, 2 + 16 + y, 0xffffff, true);
				Minecraft.getMinecraft().fontRenderer.drawString(TextFormatting.BOLD + I18n.format("stand.huajiage.stage") + "  " + stage, 8 + x, 20 + 16 + y, 0xffffff, true);
				Minecraft.getMinecraft().fontRenderer.drawString(TextFormatting.BOLD + I18n.format("stand.huajiage.mp") + "  " + charge + "/" + maxCharge, 8 + x, 30 + 16 + y, chargeHandler.canBeCost(stand.getCost()) ? 0x00fffc : 0xffffff, true);
				if (ConfigHuaji.Stands.allowStandTip) {
					Minecraft.getMinecraft().fontRenderer.drawString(TextFormatting.BOLD + I18n.format("stand.huajiage.tip", KeyLoader.standUp.getKeyModifier() + "+" + Keyboard.getKeyName(Math.max(KeyLoader.standUp.getKeyCode(), 0))), 5, 0, 0xffffff, true);
					if (stage > 0) {
						Minecraft.getMinecraft().fontRenderer.drawString(TextFormatting.BOLD + I18n.format("stand.huajiage.tip.skill", KeyLoader.standSkill.getKeyModifier() + "+" + Keyboard.getKeyName(Math.max(KeyLoader.standSkill.getKeyCode(), 0))), 5, 10, 0xffffff, true);
					}
					if (data.isTriggered()) {
						Minecraft.getMinecraft().fontRenderer.drawString(TextFormatting.BOLD + I18n.format("stand.huajiage.tip.mode", KeyLoader.standSwitch.getKeyModifier() + "+" + Keyboard.getKeyName(Math.max(KeyLoader.standSwitch.getKeyCode(), 0))), 5, stage > 0 ? 20 : 10, 0xffffff, true);
					}
				}
				if (stage > 0) {
					Minecraft.getMinecraft().fontRenderer.drawString(TextFormatting.BOLD + I18n.format("stand.huajiage.tip.cost", stand.getCost()), 8 + x, 40 + 16 + y, 0xffffff, true);
				}
				GlStateManager.scale(1, 1, 1);

			}

		}

	}
	    
}
