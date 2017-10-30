package com.iskyify.core.listeners;

import com.iskyify.core.Core;
import com.iskyify.core.event.UserUnloadEvent;
import com.iskyify.core.event.UserLoadEvent;
import com.iskyify.core.users.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        UserLoadEvent loadEvent = new UserLoadEvent(event.getPlayer().getUniqueId());
        Bukkit.getPluginManager().callEvent(loadEvent);
        if (loadEvent.isCancelled()) {
            event.getPlayer().kickPlayer("An error occurred while trying to load your data. Please contact an admin if this persists!");
            return;
        }

        if (event.getPlayer().getWorld().getName().equalsIgnoreCase(Core.getInstance().getConfig().getString("spawn.world"))) {
            event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), Core.getInstance().getConfig().getDouble("spawn.coords.X"), Core.getInstance().getConfig().getDouble("spawn.coords.Y"), Core.getInstance().getConfig().getDouble("spawn.coords.Z"), (float) Core.getInstance().getConfig().getInt("spawn.coords.P"), (float) Core.getInstance().getConfig().getInt("spawn.coords.YW")));
        }

        User user = new User(event.getPlayer().getUniqueId());
        user.loadData();
        UserUnloadEvent joinEvent = new UserUnloadEvent(user);
        Bukkit.getPluginManager().callEvent(loadEvent);
    }
}