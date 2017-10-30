package com.iskyify.core;

import com.iskyify.core.commands.CommandBase;
import com.iskyify.core.commands.ServerCommand;
import com.iskyify.core.listeners.JoinListener;
import com.iskyify.core.timer.Timer;
import com.iskyify.core.users.UserManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Core extends JavaPlugin {

    @Getter
    private static Core instance;
    @Getter
    private CommandMap commandMap;
    @Getter
    private UserManager userManager;
    @Getter
    private Connection connection;

    @Override
    public void onEnable() {
        instance = this;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + getConfig().getString("mysql.hostname")
                            + ":" + getConfig().getInt("mysql.port") + "/"
                            + getConfig().getString("mysql.database")
                            + "?autoReconnect=true",
                    getConfig().getString("username"),
                    getConfig().getString("password"));

            userManager = new UserManager();
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            this.commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
        } catch (SQLException | IllegalAccessException | NoSuchFieldException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        register(this, new ServerCommand());
        register(this, new JoinListener());
    }

    public void register(Plugin plugin, Listener... listeners) {
        for (Listener listener: listeners) {
            Bukkit.getPluginManager().registerEvents(listener, this);
        }
    }

    public void register(Plugin plugin, CommandBase...commands) {
        for (CommandBase commandBase: commands) {
            Core.getInstance().getCommandMap().register(commandBase.getName(), commandBase);
        }
    }
}