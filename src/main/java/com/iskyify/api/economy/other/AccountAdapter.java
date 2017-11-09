package com.iskyify.api.economy.other;

import com.iskyify.api.economy.Account;
import lombok.Getter;

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
