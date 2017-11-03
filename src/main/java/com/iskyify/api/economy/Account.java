package com.iskyify.api.economy;

import net.milkbowl.vault.economy.EconomyResponse;

import java.util.UUID;

public interface Account {

    public boolean exists();

    public boolean create();

    public boolean delete();

    public double getBalance();

    public EconomyResponse withdraw(double amount);

    public EconomyResponse deposit(double amount);

    UUID getUniqueId();
}
