package com.iskyify.api.event;

import com.iskyify.api.user.IUser;
import lombok.Getter;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class UserUnloadEvent extends Event {

    private static HandlerList handlerList = new HandlerList();

    @Getter
    private IUser user;

    public UserUnloadEvent(IUser user) {
        this.user = user;
    }

    public HandlerList getHandlers() {
        return handlerList;
    }
}