package com.murilonerdx.kindof;

import com.murilonerdx.kindof.commands.HealCommand;
import com.murilonerdx.kindof.commands.MessageCommand;
import com.murilonerdx.kindof.commands.PunishCommand;
import com.murilonerdx.kindof.commands.ReplyCommand;
import com.murilonerdx.kindof.config.ConfigCommand;
import com.murilonerdx.kindof.listerner.ToggleListener;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
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
    boolean active = true;
    private BossBar bossBar;

    @Override
    public void onEnable() {
//        getCommand("message").setExecutor(new MessageCommand(this));
//        getCommand("reply").setExecutor(new ReplyCommand(this));
        Objects.requireNonNull(getCommand("punish")).setExecutor(new PunishCommand());
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
        Bukkit.getPluginManager().registerEvents(new ToggleListener(), this);
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
        particles(e);
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

    public void particles(PlayerJoinEvent e){
        Player player = e.getPlayer();
        player.spawnParticle(Particle.LAVA, player.getLocation(), 5);
    }

    public void projectiles(PlayerInteractEvent e) {
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



    public void sneak(PlayerToggleSneakEvent e) {
        Firework firework = e.getPlayer()
                .getWorld().spawn(e.getPlayer().getLocation(), Firework.class);

        FireworkMeta metaFirework = firework.getFireworkMeta();
        metaFirework.addEffect(FireworkEffect.builder()
                .withColor(Color.RED)
                .withColor(Color.LIME)
                .with(FireworkEffect.Type.CREEPER)
                .withFlicker().build());

        metaFirework.setPower(1);
        firework.setFireworkMeta(metaFirework);
    }

//    @EventHandler
//    public void pottionEffect(PlayerJoinEvent e){
//        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
//                1000,
//                100,
//                false, false,false));
//    }

    @EventHandler
    public void projectile(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (Objects.equals(e.getHand(), EquipmentSlot.HAND)) {
            if (e.getAction().equals(Action.LEFT_CLICK_AIR)) {
                if (player.getInventory().getItemInMainHand().getType().equals(Material.NETHER_STAR)) {
                    player.launchProjectile(EnderPearl.class, player.getLocation().getDirection());
                }
            }
        }
    }

    @EventHandler
    public void projectile2(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (Objects.equals(e.getHand(), EquipmentSlot.HAND)) {
            if (e.getAction().equals(Action.LEFT_CLICK_AIR) && player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                Arrow shot = player.launchProjectile(Arrow.class, player.getLocation().getDirection());
                shot.setVelocity(shot.getVelocity().multiply(20.0));
            }
        }
    }

    @EventHandler
    public void projectile3(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (Objects.equals(e.getHand(), EquipmentSlot.HAND)) {
            if (e.getAction().equals(Action.LEFT_CLICK_AIR) && player.getInventory().getItemInMainHand().getType().equals(Material.GOLD_INGOT)) {
                player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SHOOT, 1.0f, 1.0f);
                SmallFireball shot = player.launchProjectile(SmallFireball.class, player.getLocation().getDirection());
                shot.setVelocity(shot.getVelocity().multiply(20.0));
            }
        }
    }

    @EventHandler
    public void projectile4(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (Objects.equals(e.getHand(), EquipmentSlot.HAND)) {
            if (e.getAction().equals(Action.LEFT_CLICK_AIR) && player.getInventory().getItemInMainHand().getType().equals(Material.IRON_INGOT)) {
                ShulkerBullet shot = player.launchProjectile(ShulkerBullet.class, player.getEyeLocation().getDirection());
                shot.setVelocity(shot.getVelocity().multiply(20.0));
            }
        }
    }

    @EventHandler
    public void projectile5(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (Objects.equals(e.getHand(), EquipmentSlot.HAND)) {
            if (e.getAction().equals(Action.LEFT_CLICK_AIR) && player.getInventory().getItemInMainHand().getType().equals(Material.DIAMOND)) {
                SpectralArrow arrow = player.launchProjectile(SpectralArrow.class);
                arrow.setPickupStatus(Arrow.PickupStatus.DISALLOWED);
                arrow.setPersistent(false);
            }
        }
    }

    @EventHandler
    public void playerAction(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (e.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            if (player.getInventory().getItemInMainHand().getType().equals(Material.EMERALD) && this.active) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
                        Integer.MAX_VALUE,
                        100,
                        false, false, false));
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,
                        Integer.MAX_VALUE,
                        5000,
                        false, false, false));
                player.sendMessage(ChatColor.AQUA + " Velocidade ativada");
                this.active = false;
            } else if (player.getInventory().getItemInMainHand().getType().equals(Material.EMERALD)) {
                for (PotionEffect pe : player.getActivePotionEffects()) {
                    player.removePotionEffect(pe.getType());
                }
                player.sendMessage(ChatColor.AQUA + " Velocidade desativada");
                this.active = true;
            }
        }
    }

    @EventHandler
    public void icyBlockWater(PlayerMoveEvent water) {
        Player player = water.getPlayer();
        Block waterblock = player.getLocation().getBlock().getRelative(BlockFace.DOWN);

        Block water2 = waterblock.getRelative(BlockFace.NORTH);
        Block water3 = waterblock.getRelative(BlockFace.SOUTH);
        Block water4 = waterblock.getRelative(BlockFace.EAST);
        Block water5 = waterblock.getRelative(BlockFace.WEST);

        Block water6 = water2.getRelative(BlockFace.NORTH);
        Block water7 = water3.getRelative(BlockFace.SOUTH);
        Block water8 = water4.getRelative(BlockFace.EAST);
        Block water9 = water5.getRelative(BlockFace.WEST);
        if (waterblock.isLiquid() && !this.active) {
            waterblock.setType(Material.ICE);
            water2.setType(Material.ICE);
            water3.setType(Material.ICE);
            water4.setType(Material.ICE);
            water5.setType(Material.ICE);

            water6.setType(Material.ICE);
            water7.setType(Material.ICE);
            water8.setType(Material.ICE);
            water9.setType(Material.ICE);
        }
    }

}
