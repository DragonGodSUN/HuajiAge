package com.lh_lshen.mcbbs.huajiage.command;

import com.lh_lshen.mcbbs.huajiage.init.loaders.StandLoader;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandStandReload extends CommandBase {
    @Override
    public String getName() {
        return "reloadStand";
    }

    @Override
    public String getUsage(ICommandSender iCommandSender) {
        return "/reloadStand";
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] strings) throws CommandException {
        StandLoader.reloadStands();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
