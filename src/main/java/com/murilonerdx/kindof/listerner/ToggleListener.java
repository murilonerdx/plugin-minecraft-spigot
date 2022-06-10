package com.murilonerdx.kindof.listerner;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ToggleListener implements Listener {
    private boolean enabled = true;

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player player = e.getPlayer();

        if(e.getHand().equals(EquipmentSlot.HAND)){
            if(e.getPlayer().getInventory().getItemInMainHand().getType().equals(Material.NETHER_STAR)
            ){
                if(enabled){
                    enabled = false;
                    player.sendMessage("Você desativou o chat");
                }else{
                    enabled = true;
                    player.sendMessage("Você ativou o chat");
                }
            }
        }

    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e){
        if(!enabled){
           e.setCancelled(true);
           e.getPlayer().sendMessage("O chat está desativado");
        }
    }
}
