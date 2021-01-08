package com.lh_lshen.mcbbs.huajiage.init.loaders;

import com.lh_lshen.mcbbs.huajiage.HuajiAge;
import com.lh_lshen.mcbbs.huajiage.command.CommandStandReload;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class HACommands {
    public static void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandStandReload());
        HuajiAge.LOGGER.info("command load--------------------");
    }
}
