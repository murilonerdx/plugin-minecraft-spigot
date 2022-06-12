package com.murilonerdx.kindof.commands;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Calendar;
import java.util.Objects;

public class PunishCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length == 2){
                Player target = Bukkit.getPlayer(args[0]);
                if(Bukkit.getPlayer(args[0]) != null){
                    switch(args[1].toLowerCase()){
                        case "kick":
                            Objects.requireNonNull(target).kickPlayer(ChatColor.RED + "Você vou expulso por ser um player ruim");
                            break;
                        case "ban":
                            Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(),
                                    ChatColor.RED + "Você foi um player muito ruim e recebeu um banimento" +
                                            "\n\n" + "Por favor seja uma pessoa melhor", null, null);
                            break;
                        case "tempban":
                            Calendar cal = Calendar.getInstance();
                            cal.add(Calendar.HOUR, 12);

                            Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(),
                                    ChatColor.RED + "Você foi um player muito ruim e recebeu um banimento temporario" +
                                            "\n\n" + "Por favor seja uma pessoa melhor", cal.getTime(), null);
                            break;
                        default:
                            player.sendMessage(ChatColor.RED + "Use algum comando, ban, kick ou tempban");
                    }
                }else{
                    player.sendMessage(ChatColor.RED + "Player não está online");
                }
            }else{
                player.sendMessage(ChatColor.RED + "Uso invalido, tente usar /punish <player name> <kick/ban/tempban>");
            }
        }

        return false;
    }
}
