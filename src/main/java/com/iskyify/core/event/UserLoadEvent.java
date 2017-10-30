package com.iskyify.core.event;

import com.iskyify.core.users.User;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class UserLoadEvent extends Event implements Cancellable {

    private static HandlerList handlerList = new HandlerList();

    private UUID id;
    private String reason;
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

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void disallow(String reason) {
        setCancelled(true);
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }
}