package com.murilonerdx.kindof.commands;

import com.murilonerdx.kindof.MainRecicle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class MessageCommand implements CommandExecutor {
    private MainRecicle main;

    public MessageCommand(MainRecicle main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length >= 2) {
                if (Bukkit.getPlayerExact(args[0]) !=null) {
                    Player target = Bukkit.getPlayerExact(args[0]);

                    StringBuilder sb = new StringBuilder();

                    for(int i = 1; i < args.length; i++){
                        sb.append(args[i]).append(" ");
                    }

                    player.sendMessage("You -> "+ Objects.requireNonNull(target).getName() + ": " + sb);
                    target.sendMessage(ChatColor.BLUE + player.getName() + " -> You: " + ChatColor.AQUA +  sb);

                    main.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                }else{
                    player.sendMessage(ChatColor.RED + "Player não encontrado");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Use /message <player> <mensagem>");
            }
        }
        return false;
    }
}
