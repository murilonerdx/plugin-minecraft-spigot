package com.murilonerdx.kindof.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collections;

public class MenuCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            Inventory inv = Bukkit.createInventory(player, 45, ChatColor.RED.toString() + ChatColor.BOLD + "Tool Menu");
            ItemStack teleport = new ItemStack(Material.ENDER_PEARL);
            ItemMeta teleportMeta = teleport.getItemMeta();
            teleportMeta.setDisplayName(ChatColor.BLUE + "Teleporte aleatorio");
            teleportMeta.setLore(Collections.singletonList(ChatColor.GRAY + "Teleportar um player para um lugar randomico"));
            teleport.setItemMeta(teleportMeta);
            inv.setItem(20, teleport);

            ItemStack kys = new ItemStack(Material.DIAMOND_SWORD);
            ItemMeta kysMeta = kys.getItemMeta();
            kysMeta.setDisplayName(ChatColor.RED + "Matar a si mesmo");
            kysMeta.setLore(Collections.singletonList(ChatColor.GRAY + "Matar a si mesmo"));
            kys.setItemMeta(kysMeta);
            inv.setItem(22, kys);

            ItemStack close = new ItemStack(Material.BARRIER);
            ItemMeta closeMeta = close.getItemMeta();
            closeMeta.setDisplayName(ChatColor.RED + "Fechar Menu");
            closeMeta.setLore(Collections.singletonList(ChatColor.GRAY + "Fechar o menu item"));
            close.setItemMeta(closeMeta);
            inv.setItem(0, close);

            ItemStack frame = new ItemStack(Material.LIME_STAINED_GLASS_PANE);
            for(int i : new int[]{1,2,3,4,5,6,7,8,9,17,18,26,27,35,36,37,38,39,40,41,42,43,44}){
                inv.setItem(i, frame);
            }

            player.openInventory(inv);
        }
        return false;
    }
}
