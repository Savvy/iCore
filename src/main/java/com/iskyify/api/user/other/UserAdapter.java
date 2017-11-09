package com.iskyify.api.user.other;

import com.iskyify.api.user.IUser;
import com.iskyify.core.database.DatabaseAdapter;
import com.iskyify.core.database.queries.DatabaseQueries;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserAdapter {

    private static UserAdapter instance;
    private Map<UUID, IUser> userMap;

    private UserAdapter() {
        userMap = new HashMap<>();
    }

    public boolean has(UUID uuid) {
        return userMap.containsKey(uuid);
    }

    private IUser add(Player player) {
        return add(player.getUniqueId());
    }

    private IUser add(OfflinePlayer player) {
        return add(player.getUniqueId());
    }

    private IUser add(UUID uuid) {
        if (has(uuid)) {
            return get(uuid);
        }
        JdbcTemplate jdbcTemplate = DatabaseAdapter.getInstance().get();
        String sql = DatabaseQueries.SELECT_USER.getQuery();

        IUser user = jdbcTemplate.queryForObject(
                sql, new Object[] { uuid.toString() }, new UserMapper());
        userMap.put(uuid, user);
        return user;
    }

    public IUser get(Player player) {
        return get(player.getUniqueId());
    }

    public IUser get(OfflinePlayer player) {
        return get(player.getUniqueId());
    }

    public IUser get(UUID uuid) {
        return has(uuid) ? userMap.get(uuid) :add(uuid);
    }

    public static UserAdapter getInstance() {
        return ((instance == null) ? instance = new UserAdapter() : instance);
    }
}
