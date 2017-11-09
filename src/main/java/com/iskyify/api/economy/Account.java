package com.iskyify.api.economy;

import net.milkbowl.vault.economy.EconomyResponse;

import java.util.UUID;

public interface Account {

    boolean exists();

    boolean create();

    boolean delete();

    double getBalance();

    EconomyResponse withdraw(double amount);

    EconomyResponse deposit(double amount);

    UUID getUniqueId();
}
