package com.murilonerdx.kindof;

import com.murilonerdx.kindof.commands.*;
import com.murilonerdx.kindof.config.ConfigCommand;
import com.murilonerdx.kindof.events.EffectEvent;
import com.murilonerdx.kindof.events.ProjectileEvent;
import com.murilonerdx.kindof.events.ServerBroadcastEvent;
import com.murilonerdx.kindof.events.ServerEvent;
import com.murilonerdx.kindof.listerner.MenuListener;
import com.murilonerdx.kindof.listerner.NametagListener;
import com.murilonerdx.kindof.listerner.PlayerListener;
import com.murilonerdx.kindof.listerner.ToggleListener;
import org.bukkit.*;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public final class MainRecicle extends JavaPlugin implements Listener {
    HashMap<UUID, UUID> recentMessages = new HashMap<>();
    HashMap<UUID, Integer> blockBroken = new HashMap<>();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new NametagListener(), this);
        Objects.requireNonNull(getCommand("book")).setExecutor(new BookCommand());
        Objects.requireNonNull(getCommand("broadcast")).setExecutor(new BroadcastCommand());


//        getConfig().options().copyDefaults();
//        saveDefaultConfig();
//        Objects.requireNonNull(getCommand("config")).setExecutor(new ConfigCommand(this));
//        Objects.requireNonNull(getCommand("heal")).setExecutor(new HealCommand());
//        Objects.requireNonNull(getCommand("message")).setExecutor(new MessageCommand(this));
//        Objects.requireNonNull(getCommand("reply")).setExecutor(new ReplyCommand(this));
//        Objects.requireNonNull(getCommand("punish")).setExecutor(new PunishCommand());
//        Objects.requireNonNull(getCommand("menu")).setExecutor(new MenuCommand());
//        Objects.requireNonNull(getCommand("hologram")).setExecutor(new SpawnHologramCommand());
//        Objects.requireNonNull(getCommand("perms")).setExecutor(new PermsCommand(this));
//
//        pluginManager();

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

//    @EventHandler
//    public void onJoin(PlayerJoinEvent e) {
//        Bukkit.getPluginManager().registerEvents(new ToggleListener(), this);
//        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
//        Bukkit.getPluginManager().registerEvents(new EffectEvent(), this);
//        Bukkit.getPluginManager().registerEvents(new ProjectileEvent(), this);
//        Bukkit.getPluginManager().registerEvents(new ServerEvent(this), this);
//        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
//    }

    @EventHandler
    public void onBroadcast(ServerBroadcastEvent e){
        System.out.println("Event run: " + e.getMessage());
    }

    @EventHandler
    public void onJoinScoreBoard(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = board.registerNewObjective("testboard", "dummy");
        obj.setDisplayName(ChatColor.GREEN.toString() + ChatColor.BOLD + "Test Board!");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score website = obj.getScore(ChatColor.YELLOW + "www.github.com/murilonerdx");
        website.setScore(1);

        Score space = obj.getScore(" ");
        space.setScore(2);

        Score name = obj.getScore(ChatColor.BLUE + "Name: " + player.getName());
        name.setScore(3);

        Team blocksBroken = board.registerNewTeam("blocksbroken");
        blocksBroken.addEntry(ChatColor.BOLD.toString());
        blocksBroken.setPrefix(ChatColor.BLUE + "Blocks borken: ");
        blocksBroken.setSuffix(ChatColor.YELLOW + "0");
        obj.getScore(ChatColor.BOLD.toString()).setScore(3);

        player.setScoreboard(board);

        this.blockBroken.put(player.getUniqueId(), 0);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();

        int amount = blockBroken.get(player.getUniqueId());
        amount++;

        blockBroken.put(player.getUniqueId(), amount);
        player.getScoreboard().getTeam("blocksbroken").setSuffix(ChatColor.YELLOW.toString() + amount);
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

    public void customCraftingRecipes() {
        ItemStack is = new ItemStack(Material.STICK);
        is.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
        ItemMeta isMeta = is.getItemMeta();
        Objects.requireNonNull(isMeta).setDisplayName(ChatColor.BLUE.toString() + ChatColor.BOLD + "Stick customizado");
        is.setItemMeta(isMeta);

        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(this, "custom_diamond_sword"), is);
        recipe.shape(
                "R R",
                " R ",
                "R R"
        );

        recipe.setIngredient('R', Material.GOLD_INGOT);
        recipe.setIngredient('G', Material.STICK);
        Bukkit.addRecipe(recipe);
    }
}
