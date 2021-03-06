package me.cuper.blockshuffle.commands;

import me.cuper.blockshuffle.Gamer;
import me.cuper.blockshuffle.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Shuffle implements CommandExecutor {
    public Main plugin;// = Main.getPlugin(Main.class);

    public Shuffle( Main main ) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender , Command cmd , String label , String[] args) {

        if( cmd.getName().equalsIgnoreCase("blockshuffle")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only player can execute this command you muffin head!");
                return true;
            } else if( args.length==0 ) return false;

            Player p = (Player) sender;
            UUID pid = p.getUniqueId();

            // join payer executing command to the game
            if (args[0].equalsIgnoreCase("join")) {
                if (plugin.contains(pid))
                    p.sendMessage(ChatColor.BLUE + "" + ChatColor.ITALIC + "You was added to the game before.");
                else {
                    plugin.addPlayer(pid);
                    success(p, "You have successfully joined the game.");
                }
            }

            // print all players in game
            else if(args[0].equalsIgnoreCase("list")) {
                String txt = plugin.playersToString();
                success(p,"Players participating in the game: "+ChatColor.BLUE+""+ChatColor.BOLD+txt );
            }

            // adding player to the game
            else if (args[0].equalsIgnoreCase("add")) {
                if (args.length == 1) error(p, "You must specify player nick!");
                else {
                    Player dod = Bukkit.getPlayer(args[1]);

                    if (dod == null) error(p, "Player doesn`t exist. You may have misstyped nick.");
                    else if(plugin.contains(dod.getUniqueId())) error(p,"Player has already been added.");
                    else {
                        plugin.addPlayer(dod.getUniqueId());
                        success(p, "Successfully added"+ChatColor.BLUE+args[1]+ChatColor.GREEN+ " to the game.");
                    }
                }
            }

            // return time or change it
            else if (args[0].equalsIgnoreCase("time")) {
                int newTime = plugin.time;
                if( args.length==1 ) info(p, "Time for the game is (20 = 1second): " + ChatColor.BOLD+""+ChatColor.GREEN + newTime);
                else if( args.length>1 ){
                    try {
                        newTime = Integer.parseInt(args[2]);
                    } catch (NumberFormatException e ) {
                        error(p,"Time must be an integer!");
                    }
                    plugin.time = newTime;
                    info(p, "Game time set to: " + ChatColor.BOLD+""+ChatColor.GREEN + newTime);
                }
            }

            // prepare and start the game
            else if (args[0].equalsIgnoreCase("start")) {
                for(int i=0;i<plugin.players.size();i++) {
                    Gamer gamer = plugin.players.get(i);

                    gamer.active = true;
                    gamer.score = 0;
                    gamer.timeLeft = plugin.time;
                    gamer.genNewBlock();

                    plugin.players.set(i,gamer);
                }

                plugin.gameState = true;
                Bukkit.broadcastMessage(ChatColor.BOLD+""+ChatColor.BLUE+"BlockShuffle game has started!");
            }

            // end the game manually
            else if (args[0].equalsIgnoreCase("end")) {
                plugin.gameState = false;

                for(int i=0;i<plugin.players.size();i++) {
                    Gamer gamer = plugin.players.get(i);
                    gamer.active = false;
                    plugin.players.set(i,gamer);
                }
            }

            // skip the actual block when it is hard or bugged
            else if ( args[0].equalsIgnoreCase("skip") ) {
                for(int i=0;i<plugin.players.size();i++) {
                    Gamer gamer = plugin.players.get(i);

                    if( gamer.id == p.getUniqueId() ) gamer.genNewBlock();

                    plugin.players.set(i,gamer);
                }
            }
        }
        return true;
    }

    // simple voids to shorten code a bit
    void error(Player p, String msg ) {
        p.sendMessage(ChatColor.RED+msg);
    }

    void success(Player p , String msg) {
        p.sendMessage(ChatColor.GREEN+msg);
    }

    void info( Player p , String msg ) {
        p.sendMessage(ChatColor.AQUA +msg);
    }


}
