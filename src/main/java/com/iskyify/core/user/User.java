package com.iskyify.core.user;

import com.iskyify.api.user.IUser;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class User implements IUser {

    @Getter private UUID uniqueId;
    @Getter @Setter private double balance;

    public User(UUID uuid) {
        this.uniqueId = uuid;
    }

    @Override
    public void updateLastJoin() {
        // Implement this.
    }

    @Override
    public void sendMessage(String s) {
        getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', s));
    }

    @Override
    public Player getPlayer() {
        return Bukkit.getPlayer(uniqueId);
    }
}
