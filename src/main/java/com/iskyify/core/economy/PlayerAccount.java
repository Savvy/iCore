package com.iskyify.core.economy;

import com.iskyify.api.economy.Account;
import com.iskyify.api.user.other.UserAdapter;
import lombok.Getter;
import net.milkbowl.vault.economy.EconomyResponse;
import org.apache.commons.lang.NotImplementedException;

import java.util.UUID;

public class PlayerAccount implements Account {

    private UUID id;

    public PlayerAccount(UUID id) {
        this.id = id;
    }

    @Override
    public boolean exists() {
        return true;
    }

    @Override
    public boolean create() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }

    @Override
    public double getBalance() {
        return UserAdapter.getInstance().get(getUniqueId()).getBalance();
    }

    @Override
    public EconomyResponse withdraw(double amount) {
        return null;
    }

    @Override
    public EconomyResponse deposit(double amount) {
        return null;
    }

    @Override
    public UUID getUniqueId() {
        return id;
    }
}