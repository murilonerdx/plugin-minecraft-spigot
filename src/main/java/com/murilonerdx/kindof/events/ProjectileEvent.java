package com.murilonerdx.kindof.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Objects;

public class ProjectileEvent implements Listener {
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
    public void projectile6(PlayerInteractEvent e) {
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
