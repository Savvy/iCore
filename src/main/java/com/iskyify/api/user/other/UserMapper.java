package com.iskyify.api.user.other;

import com.iskyify.api.economy.other.AccountAdapter;
import com.iskyify.api.user.IUser;
import com.iskyify.core.economy.PlayerAccount;
import com.iskyify.core.user.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserMapper implements RowMapper<IUser> {
    @Override
    public IUser mapRow(ResultSet resultSet, int i) throws SQLException {
        IUser user = new User(UUID.fromString(resultSet.getString("unique_id")));
        user.updateLastJoin(resultSet.getLong("last_joined"));
        user.setBalance(resultSet.getDouble("balance"));
        AccountAdapter.getInstance().add(new PlayerAccount(user.getUniqueId()));
        return user;
    }
}