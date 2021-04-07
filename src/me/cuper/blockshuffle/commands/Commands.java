package me.cuper.blockshuffle.commands;

import me.cuper.blockshuffle.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Commands implements CommandExecutor {
    // constructor
    private final Main plugin = Main.getPlugin( Main.class );

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        /*
        TODO:
         */
        if( !(sender instanceof Player) ) {
            sender.sendMessage("Penis");
        } else {
            Player p = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("manhunt")) {
                p.sendMessage("lolz");
            } else if (args[0].equalsIgnoreCase("stop")) {
                Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.BLUE + "Manhunt has been stopped!");
            }
        }
        return true;
    }
}