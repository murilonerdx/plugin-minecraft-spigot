package com.murilonerdx.kindof.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Events extends JavaPlugin implements Listener {
    public Events(){
        Bukkit.getPluginManager().registerEvents(new EventKindof(), this);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e){
        e.setCancelled(true);
        e.getPlayer().sendMessage(ChatColor.AQUA+"Pare ai, você está congelado");
    }

    @EventHandler
    public void onPlayerEggThrow(PlayerEggThrowEvent e){
        e.getPlayer().sendMessage("Você pisou em um ovo");
    }
}
