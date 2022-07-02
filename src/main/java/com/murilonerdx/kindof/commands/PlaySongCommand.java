package com.murilonerdx.kindof.commands;

import com.murilonerdx.kindof.MainRecicle;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class PlaySongCommand implements CommandExecutor {
    private final MainRecicle main;

    public PlaySongCommand(MainRecicle main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            RadioSongPlayer rsp = new RadioSongPlayer(NBSDecoder.parse(new File(main.getDataFolder(), "Alan Walker - Fade.nbs")));
            rsp.addPlayer(player);
            rsp.setPlaying(true);
//            rsp.destroy();
        }
        return false;
    }
}
