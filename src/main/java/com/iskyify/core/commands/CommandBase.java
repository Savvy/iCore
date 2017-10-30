package com.iskyify.core.commands;

import com.iskyify.core.Core;
import com.iskyify.core.users.User;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public abstract class CommandBase extends Command {

    private String name;
    private String permission;
    private boolean playerOnly;

    public CommandBase(String name) {
        this(name, "general.*", "Default Command Description");
    }

    public CommandBase(String name, String permission, String description, String... aliases) {
        super(name);
        this.name = name;
        this.permission = permission;

        setAliases(Arrays.asList(aliases));
        setDescription(description);
    }

    @Override
    public boolean execute(CommandSender commandSender, String label, String[] args) {
        if (!(commandSender instanceof Player) && playerOnly) {
            commandSender.sendMessage("This is a player-only command!");
            return true;
        } else if (!commandSender.hasPermission(permission)) {
            commandSender.sendMessage(ChatColor.RED + "Not enough permissions!");
            return true;
        }
        Player player = (Player) commandSender;
        User user = Core.getInstance().getUserManager().getUser(player.getUniqueId());
        if (user == null) {
            player.sendMessage(ChatColor.RED + "Couldn't find MySQL Information!");
            return true;
        }
        return execute(user, label, args);
    }

    public abstract boolean execute(User user, String label, String[] arguments);
}