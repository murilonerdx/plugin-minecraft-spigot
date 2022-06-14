package com.murilonerdx.kindof.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class SkullCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            ItemStack is = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) is.getItemMeta();
            Objects.requireNonNull(meta).setOwningPlayer(player);

            is.setItemMeta(meta);
            player.getInventory().addItem(is);

        }

        return false;
    }
}
