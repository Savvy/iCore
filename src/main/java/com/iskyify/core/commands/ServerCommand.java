package com.iskyify.core.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.iskyify.core.Core;
import com.iskyify.core.users.User;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ServerCommand extends CommandBase {

    public ServerCommand() {
        super("server");
    }

    public boolean execute(User user, String label, String[] args) {
        if (args.length <= 0) {
            user.sendMessage(ChatColor.RED + "Wrong usage:");
            user.sendMessage(ChatColor.RED + "/server <server>");
        } else if (args.length == 1) {
            user.connect(args[0]);
        }
        return true;
    }
}