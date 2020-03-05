package com.lh_lshen.mcbbs.huajiage.client.events;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(modid = HuajiAge.MODID, value = Side.CLIENT)
public class EventPlayerLoggedIn {
	private static boolean notFirst = false;

    @SubscribeEvent
    public static void onEnterGame(PlayerEvent.PlayerLoggedInEvent event) {
        boolean missingPatchouli = !Loader.isModLoaded("patchouli");
        if (notFirst) {
            return;
        }
        if (missingPatchouli) {
            String json = I18n.format("message.huajiage.patchouli_missing");
            ITextComponent component = ITextComponent.Serializer.jsonToComponent(json);
            event.player.sendMessage(component);
        }
       
        notFirst = true;
    }
}
