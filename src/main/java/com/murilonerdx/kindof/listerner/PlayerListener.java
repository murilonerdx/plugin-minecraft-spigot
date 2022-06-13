package com.murilonerdx.kindof.listerner;

import org.bukkit.*;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashMap;
import java.util.UUID;

public class PlayerListener implements Listener {

    @EventHandler()
    public void onSeak(PlayerToggleSneakEvent e) {
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



    public void songSneak(PlayerToggleSneakEvent e) {
        e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.ENTITY_ARMOR_STAND_BREAK, 1.0F, 1.0F);
    }

    public void sneakEffectSound(PlayerToggleSneakEvent e) {
        if (e.isCancelled()) {
            e.getPlayer().playEffect(e.getPlayer().getLocation(), Effect.RECORD_PLAY, Material.MUSIC_DISC_11);
        }
    }

    public void particles(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.spawnParticle(Particle.LAVA, player.getLocation(), 5);
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
    public void pottionEffect(PlayerJoinEvent e) {
        e.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED,
                1000,
                100,
                false, false, false));
    }

}
