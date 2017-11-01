package com.iskyify.api.user.other;

import com.iskyify.api.user.IUser;
import com.iskyify.core.user.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class UserMapper implements RowMapper<IUser> {
    @Override
    public IUser mapRow(ResultSet resultSet, int i) throws SQLException {
        IUser user = new User(UUID.fromString(resultSet.getString("uuid")));
        user.setBalance(resultSet.getDouble("balance"));
        return user;
    }
}
