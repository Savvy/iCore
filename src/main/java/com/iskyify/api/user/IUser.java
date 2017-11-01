package com.iskyify.api.user;

import org.bukkit.entity.Player;

import java.util.UUID;

public interface IUser {

    double getBalance();
    void setBalance(double balance);
    UUID getUniqueId();

    void updateLastJoin();

    void sendMessage(String s);

    Player getPlayer();
}
