package com.murilonerdx.kindof.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VanishCommand implements CommandExecutor {
    List<UUID> vanished = new ArrayList<>();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            if(vanished.contains(player.getUniqueId())){
                vanished.remove(player.getUniqueId());
                for(Player target : Bukkit.getOnlinePlayers()){
                    target.showPlayer(player);
                }
                player.sendMessage("Você está visivel");
            }else{
                vanished.add(player.getUniqueId());

                for(Player target : Bukkit.getOnlinePlayers()){
                    target.hidePlayer(player);
                }
                player.sendMessage("Você está invisivel");
            }
        }
        return false;
    }
}
