package com.murilonerdx.kindof;

import com.murilonerdx.kindof.commands.*;
import com.murilonerdx.kindof.config.ConfigCommand;
import com.murilonerdx.kindof.events.EffectEvent;
import com.murilonerdx.kindof.events.ProjectileEvent;
import com.murilonerdx.kindof.events.ServerEvent;
import com.murilonerdx.kindof.listerner.MenuListener;
import com.murilonerdx.kindof.listerner.PlayerListener;
import com.murilonerdx.kindof.listerner.ToggleListener;
import org.bukkit.*;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public final class MainRecicle extends JavaPlugin implements Listener {
    HashMap<UUID, UUID> recentMessages = new HashMap<>();

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Objects.requireNonNull(getCommand("config")).setExecutor(new ConfigCommand(this));
        Objects.requireNonNull(getCommand("heal")).setExecutor(new HealCommand());
        Objects.requireNonNull(getCommand("message")).setExecutor(new MessageCommand(this));
        Objects.requireNonNull(getCommand("reply")).setExecutor(new ReplyCommand(this));
        Objects.requireNonNull(getCommand("punish")).setExecutor(new PunishCommand());
        Objects.requireNonNull(getCommand("menu")).setExecutor(new MenuCommand());

        pluginManager();

//        Bukkit.getScheduler().runTaskLater(this, () -> {
//            for(Player player : Bukkit.getOnlinePlayers()){
//                player.sendMessage("Servidor está ativo a 10 segundos");
//            }
//        }, 200);
//
//        Bukkit.getScheduler().runTaskTimer(this, () -> {
//            for(Player player : Bukkit.getOnlinePlayers()){
//                player.sendMessage("Servidor está ativo a 10 segundos");
//            }
//        }, 300, 20);

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Bukkit.getPluginManager().registerEvents(new ToggleListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new EffectEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ProjectileEvent(), this);
        Bukkit.getPluginManager().registerEvents(new ServerEvent(this), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
    }

    public void pluginManager() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onResourcePack(PlayerJoinEvent e) {
        e.getPlayer().setResourcePack("https://file.io/8QVkhJTQtF1D");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        recentMessages.remove(e.getPlayer().getUniqueId());
    }

    public HashMap<UUID, UUID> getRecentMessages() {
        return recentMessages;
    }
}
