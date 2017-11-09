package com.iskyify.core.listeners;

import com.iskyify.api.user.IUser;
import com.iskyify.api.event.UserLoadEvent;
import com.iskyify.core.iCore;
import com.iskyify.api.user.other.UserAdapter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        IUser user = UserAdapter.getInstance().get(event.getPlayer().getUniqueId());
        UserLoadEvent loadEvent = new UserLoadEvent(user);
        Bukkit.getPluginManager().callEvent(loadEvent);
        if (loadEvent.isCancelled()) {
            event.getPlayer().kickPlayer(loadEvent.getReason().isEmpty() ? "An error occurred while trying to load your data. Please contact an admin if this persists!" : loadEvent.getReason());
            return;
        }
        user.updateLastJoin();

        if (event.getPlayer().getWorld().getName().equalsIgnoreCase(iCore.getInstance().getConfig().getString("spawn.world"))) {
            event.getPlayer().teleport(new Location(event.getPlayer().getWorld(), iCore.getInstance().getConfig().getDouble("spawn.coords.X"),
                    iCore.getInstance().getConfig().getDouble("spawn.coords.Y"), iCore.getInstance().getConfig().getDouble("spawn.coords.Z"),
                    (float) iCore.getInstance().getConfig().get("spawn.coords.P"), (float) iCore.getInstance().getConfig().get("spawn.coords.YW")));
        }
    }
}