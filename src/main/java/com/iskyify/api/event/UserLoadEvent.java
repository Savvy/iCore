package com.iskyify.api.event;

import com.iskyify.api.user.IUser;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import java.util.UUID;

public class UserLoadEvent extends Event implements Cancellable {

    private static HandlerList handlerList = new HandlerList();
    @Getter private final IUser user;
    @Getter private UUID uniqueId;
    @Getter private String reason;
    @Getter @Setter private boolean cancelled;

    public UserLoadEvent(IUser user) {
        this.user = user;
        this.uniqueId = user.getUniqueId();
    }

    public HandlerList getHandlers() {
        return handlerList;
    }

    public void disallow(String reason) {
        setCancelled(true);
        this.reason = reason;
    }
}