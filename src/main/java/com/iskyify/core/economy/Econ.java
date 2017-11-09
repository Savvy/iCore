package com.iskyify.core.economy;

import com.iskyify.api.economy.other.AccountAdapter;
import com.iskyify.api.utils.Utils;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.OfflinePlayer;

import java.util.List;

public class Econ implements Economy {

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "iConomy";
    }

    @Override
    public boolean hasBankSupport() {
        return true;
    }

    @Override
    public int fractionalDigits() {
        return 2;
    }

    @Override
    public String format(double v) {
        return null;
    }

    @Override
    public String currencyNamePlural() {
        return "coins";
    }

    @Override
    public String currencyNameSingular() {
        return "coin";
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer) {
        return AccountAdapter.getInstance().get(offlinePlayer.getUniqueId()).exists();
    }

    @Override
    public boolean hasAccount(OfflinePlayer offlinePlayer, String world) {
        return AccountAdapter.getInstance().get(offlinePlayer.getUniqueId()).exists();
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return AccountAdapter.getInstance().get(offlinePlayer.getUniqueId()).getBalance();
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer, String world) {
        return AccountAdapter.getInstance().get(offlinePlayer.getUniqueId()).getBalance();
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double amount) {
        return AccountAdapter.getInstance().get(offlinePlayer.getUniqueId()).getBalance() >= amount;
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, String world, double amount) {
        return AccountAdapter.getInstance().get(offlinePlayer.getUniqueId()).getBalance() >= amount;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, double amount) {
        return AccountAdapter.getInstance().get(offlinePlayer.getUniqueId()).withdraw(amount);
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer offlinePlayer, String world, double amount) {
        return AccountAdapter.getInstance().get(offlinePlayer.getUniqueId()).withdraw(amount);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, double amount) {
        return AccountAdapter.getInstance().get(offlinePlayer.getUniqueId()).deposit(amount);
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer offlinePlayer, String world, double amount) {
        return AccountAdapter.getInstance().get(offlinePlayer.getUniqueId()).deposit(amount);
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer offlinePlayer) {
        return Utils.createBank(name, offlinePlayer);
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return Utils.deleteBank(name);
    }

    @Override
    public EconomyResponse bankBalance(String s) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String s, OfflinePlayer offlinePlayer) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer offlinePlayer, String s) {
        return false;
    }

    // DEPRECATED
    // DO NOT USE

    @Override
    public EconomyResponse withdrawPlayer(String s, double v) {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String s) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(String s, String s1) {
        return false;
    }

    @Override
    public EconomyResponse withdrawPlayer(String s, String s1, double v) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String s, double v) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String s, String s1) {
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String s, String s1, double v) {
        return null;
    }

    @Override
    public boolean has(String name, double world) {
        return false;
    }

    @Override
    public boolean hasAccount(String name, String world) {
        return false;
    }

    @Override
    public double getBalance(String name) {
        return 0.0;
    }

    @Override
    public boolean has(String s, String s1, double v) {
        return false;
    }

    @Override
    public double getBalance(String name, String world) {
        return 0.0;
    }

    @Override
    public boolean hasAccount(String name) {
        return false;
    }
}
