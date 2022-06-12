package com.murilonerdx.kindof.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class BookCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            ItemStack book = new ItemStack(Material.WRITABLE_BOOK);
            BookMeta meta = (BookMeta) book.getItemMeta();

            meta.setTitle(ChatColor.RED + " My Epic Book");
            meta.setAuthor(ChatColor.YELLOW + "Murilo");
            meta.addPage(
                    ChatColor.BLUE  + "Welcome to my book " + player +
                            "\n\n" + ChatColor.GREEN + "Este livro é sobre...."
            );
            book.setItemMeta(meta);
            player.getInventory().addItem(book);

        }
        return false;
    }
}
