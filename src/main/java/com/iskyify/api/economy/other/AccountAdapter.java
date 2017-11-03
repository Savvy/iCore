package com.iskyify.api.economy.other;

import com.iskyify.api.economy.Account;
import com.iskyify.api.user.IUser;
import com.iskyify.api.user.other.UserAdapter;
import lombok.Getter;
import net.milkbowl.vault.economy.EconomyResponse;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountAdapter {

    private static AccountAdapter instance;

    @Getter
    private Map<UUID, Account> accountMap;

    public AccountAdapter() {
        this.accountMap = new HashMap<>();
    }

    public void add(Account account) {
        this.accountMap.put(account.getUniqueId(), account);
    }

    public Account get(UUID id) {
        return accountMap.get(id);
    }

    public static AccountAdapter getInstance() {
        return ((instance == null) ? instance = new AccountAdapter() : instance);
    }
}
