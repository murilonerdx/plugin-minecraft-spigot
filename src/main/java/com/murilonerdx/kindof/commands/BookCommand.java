package com.murilonerdx.kindof.commands;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
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

            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta meta = (BookMeta) book.getItemMeta();

            meta.setTitle(ChatColor.RED + " My Epic Book");
            meta.setAuthor(ChatColor.YELLOW + "Murilo");

            TextComponent clickable = new TextComponent("§c§Clickable");
            clickable.setClickEvent(new ClickEvent(ClickEvent.Action.CHANGE_PAGE, "2"));

            TextComponent none = new TextComponent("\n\n§3None");

            TextComponent hoverable = new TextComponent("\n\n§bHoverable");
            hoverable.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("Woo! This is hoverable")));

            BaseComponent[] page = new BaseComponent[]{clickable, none, hoverable};

            meta.spigot().addPage(page);
            meta.addPage("Primeira\nSegunda\nTerceira");

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
