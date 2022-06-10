package com.murilonerdx.kindof.config;

import com.murilonerdx.kindof.MainRecicle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class ConfigCommand implements CommandExecutor {
    private MainRecicle main;

    public ConfigCommand(MainRecicle main){
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            player.sendMessage(Objects.requireNonNull(main.getConfig().getString("Word")));

            for(String string : Objects.requireNonNull(main.getConfig().getStringList("String-list"))){
                player.sendMessage(string);
            }
        }



        return false;
    }
}
