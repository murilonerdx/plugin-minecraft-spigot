package com.murilonerdx.kindof.events;

import com.murilonerdx.kindof.MainRecicle;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.Objects;

public class BlockEvent implements Listener {
    private MainRecicle mainRecicle;

    public BlockEvent(MainRecicle mainRecicle) {
        this.mainRecicle = mainRecicle;
    }

    public void entityManager() {
        Entity entity = Objects.requireNonNull(Bukkit.getWorld("world"))
                .spawnEntity(new Location(Bukkit.getWorld("word"), 0, 61, 0)
                        , EntityType.POLAR_BEAR);

        ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(Bukkit.getWorld("world"))
                .spawnEntity(new Location(Bukkit.getWorld("word"), 0, 61, 0)
                        , EntityType.ARMOR_STAND);
    }

    public void block(){
        Bukkit.getPluginManager().registerEvents(this, mainRecicle);

        Entity entity = Objects.requireNonNull(Bukkit.getWorld("world"))
                .spawnEntity(new Location(Bukkit.getWorld("word"), 0, 61, 0)
                        , EntityType.POLAR_BEAR);

        ArmorStand armorStand = (ArmorStand) Objects.requireNonNull(Bukkit.getWorld("world"))
                .spawnEntity(new Location(Bukkit.getWorld("word"), 0, 61, 0)
                        , EntityType.ARMOR_STAND);

        Block block = Objects.requireNonNull(Bukkit.getWorld("world")).getBlockAt(15, 60, 42);

        Bukkit.getWorld("world").getBlockAt(1, 1, 1)
                .setType(Material.LAVA);

        ItemStack itemStack = new ItemStack(Material.ANDESITE, 3);
        ItemMeta meta = itemStack.getItemMeta();
        itemStack.setItemMeta(meta);
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
}
