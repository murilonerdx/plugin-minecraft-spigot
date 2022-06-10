package com.murilonerdx.kindof;

import com.murilonerdx.kindof.commands.HealCommand;
import com.murilonerdx.kindof.commands.MessageCommand;
import com.murilonerdx.kindof.commands.ReplyCommand;
import com.murilonerdx.kindof.config.ConfigCommand;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class MainRecicle extends JavaPlugin implements Listener {

    private HashMap<UUID, UUID> recentMessages;

    private BossBar bossBar;

    @Override
    public void onEnable() {
//        getCommand("message").setExecutor(new MessageCommand(this));
//        getCommand("reply").setExecutor(new ReplyCommand(this));
//
//        recentMessages = new HashMap<>();
//
//        Bukkit.getPluginManager().registerEvents(this,this);

//        Entity entity = Objects.requireNonNull(Bukkit.getWorld("world"))
//                .spawnEntity(new Location(Bukkit.getWorld("word"), 0, 61, 0 )
//                        , EntityType.POLAR_BEAR);
//
//        ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(Bukkit.getWorld("world"))
//                .spawnEntity(new Location(Bukkit.getWorld("word"), 0, 61, 0 )
//                        , EntityType.ARMOR_STAND);

//        Block block = Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(15,60,42);
//
//        Bukkit.getWorld("world").getBlockAt(1,1,1)
//                .setType(Material.LAVA);
//
//        ItemStack itemStack = new ItemStack(Material.ANDESITE, 3);
//        ItemMeta meta = itemStack.getItemMeta();
//        itemStack.setItemMeta(meta);

        pluginManager();
        bossBar();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    public void item(PlayerJoinEvent e) {
        ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
        helmetMeta.setColor(Color.fromRGB(51, 204, 255));

        helmet.setItemMeta(helmetMeta);

        e.getPlayer().getInventory().addItem(helmet);


        ItemStack chestPlate = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestPlate.getItemMeta();
        chestplateMeta.setColor(Color.fromRGB(255, 255, 204));
        chestPlate.setItemMeta(chestplateMeta);

        e.getPlayer().getInventory().addItem(chestPlate);
    }

    @EventHandler
    public void onjoin(PlayerJoinEvent e) {
        joinListPlayers(e);
        pottionEffect(e);
        playerHeaderFooter(e);
        e.getPlayer().sendTitle(ChatColor.RED + "Ola!",
                ChatColor.GREEN + "Bem vindo ao servidor",
                20,
                100,
                20);
    }

    public HashMap<UUID, UUID> getRecentMessages() {
        return recentMessages;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        recentMessages.remove(e.getPlayer().getUniqueId());
    }

    public void pluginManager() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent e) {
        seak(e);
    }

    public void entityManager() {
        Entity entity = Objects.requireNonNull(Bukkit.getWorld("world"))
                .spawnEntity(new Location(Bukkit.getWorld("word"), 0, 61, 0)
                        , EntityType.POLAR_BEAR);

        ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(Bukkit.getWorld("world"))
                .spawnEntity(new Location(Bukkit.getWorld("word"), 0, 61, 0)
                        , EntityType.ARMOR_STAND);
    }

    @EventHandler
    public void onPing(ServerListPingEvent e) throws Exception {
        e.setMaxPlayers(42000);
        e.setMotd(ChatColor.AQUA + " Bem vindo ao servidor\n" + "Se divirta!");
        e.setServerIcon(Bukkit.loadServerIcon(new File(System.getProperty("user.dir") + "minecraft-block.png")));
    }

    public void joinListPlayers(PlayerJoinEvent e) {
        e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR,
                TextComponent.fromLegacyText("HELLO MY FRIEND"));
    }

    public void playerHeaderFooter(PlayerJoinEvent e) {
        e.getPlayer().setPlayerListHeaderFooter(ChatColor.RED + "Hello",
                "First Line\nSecond Line!");
    }

    public void config() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        Objects.requireNonNull(getCommand("config")).setExecutor(new ConfigCommand(this));
        Objects.requireNonNull(getCommand("heal")).setExecutor(new HealCommand());
    }

    public void bossBar() {
        bossBar = Bukkit.createBossBar(
                ChatColor.LIGHT_PURPLE + "Esse servidor é incrivel",
                BarColor.PINK,
                BarStyle.SEGMENTED_6);

        bossBar.setProgress(0.5);

        pluginManager();
    }

    @EventHandler
    public void onJoin2(PlayerJoinEvent e) {
        bossBar.addPlayer(e.getPlayer());
    }

    public void seak(PlayerToggleSneakEvent e) {
        Firework firework = e.getPlayer().getWorld()
                .spawn(e.getPlayer().getLocation(), Firework.class);
        FireworkMeta meta = (FireworkMeta) firework.getFireworkMeta();
        meta.addEffect(FireworkEffect.builder().withColor(Color.RED).
                withColor(Color.LIME)
                .with(FireworkEffect.Type.CREEPER)
                .withFlicker().build());

        meta.setPower(1);
        firework.setFireworkMeta(meta);

        sneakEffectSound(e);
        songSneak(e);
    }

    public void pottionEffect(PlayerJoinEvent e) {
        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
                1000, 100));

    }

    public void removeEffect(PlayerJoinEvent e) {
        for (PotionEffect effect : e.getPlayer().getActivePotionEffects()) {
            e.getPlayer().removePotionEffect(effect.getType());
        }
    }

    public void changeTimeWorld() {
        Player player = null;
        player.getWorld().setTime(6000);
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

    public void songSneak(PlayerToggleSneakEvent e) {
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ARMOR_STAND_BREAK, 1.0F, 1.0F);
    }

    public void sneakEffectSound(PlayerToggleSneakEvent e) {
        if (e.isCancelled()) {
            e.getPlayer().playEffect(e.getPlayer().getLocation(), Effect.RECORD_PLAY, Material.MUSIC_DISC_11);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        projectile(e);
    }

    public void projectile(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (e.getHand().equals(EquipmentSlot.HAND)) {
            if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                player.getInventory().getItemInMainHand();
                if (player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND_HOE)) {
                    player.launchProjectile(Egg.class, player.getLocation().getDirection());
                }
            }
        }

    }

}
