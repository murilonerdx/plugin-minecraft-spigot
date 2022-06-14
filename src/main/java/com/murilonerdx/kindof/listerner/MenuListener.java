package com.murilonerdx.kindof.listerner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.Random;

public class MenuListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (ChatColor.translateAlternateColorCodes('&', e.getView().getTitle())
                .equals(ChatColor.RED.toString() + ChatColor.BOLD + "Tool Menu") && e.getCurrentItem() != null) {
            switch (e.getRawSlot()) {
                case 0:
                    break;
                case 20:
                    Random random = new Random();
                    Player target = (Player) Bukkit.getOnlinePlayers().toArray()[random.nextInt(Bukkit.getOnlinePlayers().size())];
                    player.teleport(target);
                    player.sendMessage(ChatColor.RED + "Você teletransportou para " + player.getName());
                    break;
                case 22:
                    player.setHealth(0);
                    player.sendMessage(ChatColor.RED + "Você acabou de se matar");
                    break;
                default:
                    return;
            }
        }
        player.closeInventory();
    }
}
