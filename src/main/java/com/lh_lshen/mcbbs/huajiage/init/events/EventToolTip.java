package com.lh_lshen.mcbbs.huajiage.init.events;

import java.util.List;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.item.ItemLoader;
import com.lh_lshen.mcbbs.huajiage.item.ItemSingularity;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//@SideOnly(Side.CLIENT)
//@Mod.EventBusSubscriber(modid = HuajiAge.MODID, value = Side.CLIENT)
public class EventToolTip {
	private static int tick = 0;
	private static int curColor = 0;
	
	private static TextFormatting[] colors = {TextFormatting.RED,TextFormatting.GOLD,TextFormatting.YELLOW,TextFormatting.GREEN,TextFormatting.BLUE,TextFormatting.AQUA };

	@SubscribeEvent
	public static void onSingularityTooltip(ItemTooltipEvent event) {
		if (!event.getItemStack().isEmpty() && event.getItemStack().getItem() instanceof ItemSingularity) {
			List<String> tooltip = event.getToolTip();

					String str = I18n.format("item.singularity.tooltip.warning");
					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < str.length(); i++) {
						sb.append(colors[(curColor + i) % colors.length].toString());
						sb.append(str.charAt(i));
					}
					
					tooltip.add( sb.toString());

		}
	}

	@SubscribeEvent
	public static void onClientTick(ClientTickEvent event) {
		if (event.phase == Phase.START) {
			if (++tick >= 2) {
				tick = 0;
				if (--curColor < 0) {
					curColor = colors.length - 1;
				}
			}
		}
	}
	
}
