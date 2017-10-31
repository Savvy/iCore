package com.iskyify.core.users;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.iskyify.core.Core;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Used to load player data
 *
 * @author Majrly
 * @since 0.0.1
 */
public class User {

    private static final String SELECT = "SELECT * FROM `users` WHERE `id`=?";
    private static final String UPDATE = "UPDATE `users` SET `name`=?, `money`=? WHERE `id`=?;";
    private static final String INSERT = "INSERT INTO `users`(`id`, `name`, `money`) VALUES(?, ?, ?)";

    @Getter
    private UUID id;
    @Getter
    @Setter
    private double money;

    public User(UUID id) {
        this.id = id;
    }

    public void loadData() {
        try {
            PreparedStatement select = Core.getInstance().getConnection().prepareStatement(SELECT);
            select.setString(0, id.toString());
            ResultSet resultSet = select.executeQuery();
            if (resultSet.next()) {
                setMoney(resultSet.getDouble("money"));
            } else {
                setMoney(0.0D);
            }
            resultSet.close();
            select.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveData() {
        try {
            PreparedStatement select = Core.getInstance().getConnection().prepareStatement(SELECT);
            select.setString(0, id.toString());
            ResultSet resultSet = select.executeQuery();
            if (resultSet.next()) {
                PreparedStatement update = Core.getInstance().getConnection().prepareStatement(UPDATE);
                update.setString(0, getPlayer().getName());
                update.setDouble(1, getMoney());
                update.setString(2, getId().toString());
                update.executeUpdate();
                update.close();
            } else {
                PreparedStatement update = Core.getInstance().getConnection().prepareStatement(INSERT);
                update.setString(0, getId().toString());
                update.setString(1, getPlayer().getName());
                update.setDouble(2, getMoney());
                update.executeUpdate();
                update.close();
            }
            resultSet.close();
            select.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(getId());
    }

    public void connect(String server) {
        getPlayer().sendMessage(ChatColor.GOLD + "Sending you to " + server);
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        getPlayer().sendPluginMessage(Core.getInstance(), "BungeeCord", out.toByteArray());
    }

    public void sendMessage(String message) {
        getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }
}