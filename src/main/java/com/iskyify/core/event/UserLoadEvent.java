package com.iskyify.core.event;

import com.iskyify.core.users.User;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class UserLoadEvent extends Event implements Cancellable {

    private static HandlerList handlerList = new HandlerList();

    @Getter
    private UUID id;
    @Getter
    private String reason;
    @Getter
    @Setter
    private boolean cancelled;

    public UserLoadEvent(UUID id) {
        this.id = id;
    }

    public UUID getUser() {
        return id;
    }

    public HandlerList getHandlers() {
        return handlerList;
    }

    public void disallow(String reason) {
        setCancelled(true);
        this.reason = reason;
    }
}