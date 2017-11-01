package com.iskyify.core.commands;

import com.iskyify.api.user.IUser;
import com.iskyify.api.user.other.UserAdapter;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public abstract class CommandBase extends Command {

    private String name;
    private String permission;

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
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("This is a player-only command!");
            return true;
        } else if (!commandSender.hasPermission(permission)) {
            commandSender.sendMessage(ChatColor.RED + "Not enough permissions!");
            return true;
        }
        Player player = (Player) commandSender;
        IUser user = UserAdapter.getInstance().get(player.getUniqueId());
        if (user == null) {
            player.sendMessage(ChatColor.RED + "Could not find user data in database.!");
            return true;
        }
        return execute(user, label, args);
    }

    public abstract boolean execute(IUser user, String label, String[] arguments);
}