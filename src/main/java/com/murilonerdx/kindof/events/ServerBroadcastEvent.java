package com.murilonerdx.kindof.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class ServerBroadcastEvent extends Event {

    private static final HandlerList HANDLERS = new HandlerList();

    private boolean cancellable;

    private final Player player;
    private final String message;

    public ServerBroadcastEvent(Player player, String message) {
        this.player = player;
        this.message = message;
    }

    public Player getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public boolean isCancellable() {
        return cancellable;
    }

    public void setCancellable(boolean cancellable) {
        this.cancellable = cancellable;
    }

    public static HandlerList getHandlerList(){
        return HANDLERS;
    }
}
