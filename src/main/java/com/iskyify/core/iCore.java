package com.iskyify.core;

import com.iskyify.core.commands.CommandBase;
import com.iskyify.core.commands.ServerCommand;
import com.iskyify.core.database.DatabaseAdapter;
import com.iskyify.core.database.queries.DatabaseQueries;
import com.iskyify.core.listeners.JoinListener;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class iCore extends JavaPlugin {

    @Getter private static iCore instance;
    private CommandMap commandMap;

    public void onEnable() {
        instance = getPlugin(this.getClass());
        saveDefaultConfig();
        load();
    }

    private void load() {
        DatabaseAdapter.getInstance().check();
        DatabaseAdapter.getInstance().get().update(DatabaseQueries.CREATE_USERS_TABLE.getQuery());
        DatabaseAdapter.getInstance().get().update(DatabaseQueries.CREATE_USER_DATA_TABLE.getQuery());
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            this.commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
        } catch ( IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        register(this, new ServerCommand());
        register(this, new JoinListener());
    }

    public void register(Plugin plugin, Listener... listeners) {
        for (Listener listener : listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }

    public void register(Plugin plugin, CommandBase... commands) {
        for (CommandBase commandBase : commands) {
            commandMap.register(commandBase.getName(), commandBase);
        }
    }
}
