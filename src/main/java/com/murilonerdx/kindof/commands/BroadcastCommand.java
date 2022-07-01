package com.murilonerdx.kindof.commands;

import com.murilonerdx.kindof.events.ServerBroadcastEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BroadcastCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length >= 1) {
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < args.length; i++) {
                    builder.append(args[i]).append(" ");
                }

                ServerBroadcastEvent event = new ServerBroadcastEvent(player, builder.toString());
                Bukkit.getPluginManager().callEvent(event);

                if (!event.isCancellable()) {
                    Bukkit.broadcastMessage(builder.toString());
                }
            } else {
                player.sendMessage(ChatColor.RED + "Uso invalido! Por favor use /broadcast <message>.");
            }
        }
        return false;
    }
}
