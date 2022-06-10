package com.murilonerdx.kindof.commands;

import com.murilonerdx.kindof.MainRecicle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class ReplyCommand implements CommandExecutor {
    private MainRecicle main;

    public ReplyCommand(MainRecicle main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length >= 1) {
                if (main.getRecentMessages().containsKey(player.getUniqueId())) {
                    UUID uuid = main.getRecentMessages().get(player.getUniqueId());
                    if (Bukkit.getPlayer(uuid) != null) {
                        Player target = Bukkit.getPlayer(uuid);

                        StringBuilder sb = new StringBuilder();

                        for(int i = 0; i < args.length; i++){
                            sb.append(args[i]).append(" ");
                        }

                        player.sendMessage("You -> "+ Objects.requireNonNull(target).getName() + ": " + sb);
                        target.sendMessage(ChatColor.RED + player.getName() + " -> You: " + ChatColor.AQUA + sb);
                    } else {
                        player.sendMessage(ChatColor.RED + "Player está offline");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Você não tem nenhuma mensagem");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Use /reply <player> <mensagem>");
            }
        }
        return false;
    }
}