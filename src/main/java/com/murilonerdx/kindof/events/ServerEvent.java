package com.murilonerdx.kindof.events;

import com.murilonerdx.kindof.MainRecicle;
import com.murilonerdx.kindof.commands.HealCommand;
import com.murilonerdx.kindof.config.ConfigCommand;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;

import java.io.File;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerEvent implements Listener {
    private BossBar bossBar;
    private final MainRecicle mainRecicle;

    public ServerEvent(MainRecicle mainRecicle){
        this.mainRecicle = mainRecicle;
    }

    @EventHandler
    public void onjoin(PlayerJoinEvent e) {
        bossBar();
        joinListPlayers(e);
        playerHeaderFooter(e);
        e.getPlayer().sendTitle(ChatColor.RED + "Ola!",
                ChatColor.GREEN + "Bem vindo ao servidor",
                20,
                100,
                20);
    }

    @EventHandler
    public void onPing(ServerListPingEvent e) throws Exception {
        e.setMaxPlayers(42000);
        e.setMotd(ChatColor.AQUA + " Bem vindo ao servidor\n" + "Se divirta!");
        e.setServerIcon(Bukkit.loadServerIcon(new File(System.getProperty("user.dir") + "minecraft-block.png")));
    }

    @EventHandler
    public void onJoin2(PlayerJoinEvent e) {
        bossBar.addPlayer(e.getPlayer());
    }

    @EventHandler
    public void onChatEffect(AsyncPlayerChatEvent e) {
        e.setMessage(translate(e.getMessage()));
    }

    //   #b122a3T #b936a4e #c04aa5s #c85ea6t #cf72a7e  #d787a8m #df9ba9u #e6afaar #eec3abi #f5d7acl #fdebado
    private String translate(String input) {
        Matcher matcher = Pattern.compile("#[a-zfA-ZF0-9.,-_]{6}").matcher(input);
        while (matcher.find()) {
            String color = input.substring(matcher.start(), matcher.end());
            input = input.replace(color, net.md_5.bungee.api.ChatColor.of(color) + "");
            matcher = Pattern.compile("#[a-zfA-ZF0-9.,-_]{6}").matcher(input);
        }

        return input;
    }

    public void bossBar() {
        bossBar = Bukkit.createBossBar(
                ChatColor.LIGHT_PURPLE + "Esse servidor é incrivel",
                BarColor.PINK,
                BarStyle.SEGMENTED_6);

        bossBar.setProgress(0.5);

        Bukkit.getPluginManager().registerEvents(this, mainRecicle);
    }

    public void joinListPlayers(PlayerJoinEvent e) {
        e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR,
                TextComponent.fromLegacyText("HELLO MY FRIEND"));
    }

    public void playerHeaderFooter(PlayerJoinEvent e) {
        e.getPlayer().setPlayerListHeaderFooter(ChatColor.RED + "Hello",
                "First Line\nSecond Line!");
    }

}
