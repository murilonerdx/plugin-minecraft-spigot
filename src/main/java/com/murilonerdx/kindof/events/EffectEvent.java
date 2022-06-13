package com.murilonerdx.kindof.events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EffectEvent implements Listener {
    boolean active = true;

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
