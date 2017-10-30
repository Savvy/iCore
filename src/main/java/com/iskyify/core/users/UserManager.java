package com.iskyify.core.users;

import lombok.Getter;

import java.util.HashMap;
import java.util.UUID;

public class UserManager {

    @Getter
    private HashMap<UUID, User> users = new HashMap<UUID, User>();

    public void addUser(User user) {
        this.users.put(user.getId(), user);
    }

    public User getUser(UUID id) {
        return this.users.get(id);
    }
}