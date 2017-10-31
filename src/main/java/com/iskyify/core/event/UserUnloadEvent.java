package com.iskyify.core.event;

import com.iskyify.core.users.User;
import lombok.Getter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class UserUnloadEvent extends Event {

    private static HandlerList handlerList = new HandlerList();

    @Getter
    private User user;

    public UserUnloadEvent(User user) {
        this.user = user;
    }

    public HandlerList getHandlers() {
        return handlerList;
    }
}