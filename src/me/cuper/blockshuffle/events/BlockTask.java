package me.cuper.blockshuffle.events;

import me.cuper.blockshuffle.Gamer;
import me.cuper.blockshuffle.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;

public class BlockTask extends BukkitRunnable {
    public Main plugin;
    public BlockTask( Main main ) {
        this.plugin = main;
    }

    /*
    TODO:
    - robienie tasków
    - sprawdzanie bloków
    - szukanie nowego bloku
     */
    @Override
    public void run() {
        if( plugin.gameState ){//&& plugin.players.size() != 0 ) {
            for(int i=0;i<plugin.players.size();i++) {
                Gamer gamer = plugin.players.get(i);
                Player p = Bukkit.getPlayer(gamer.id);

                if( p==null || !p.isOnline() ) continue; // fixing the bug when a player lefts the game while playing

                gamer.timeLeft -= plugin.refreshTime; // substruct time
                if( gamer.active ) p.setLevel( (int)gamer.timeLeft/20 );

                if( gamer.timeLeft <= 0 && gamer.active) {
                    p.sendMessage(ChatColor.RED+"You`ve lost!" );
                    Bukkit.broadcastMessage(ChatColor.BLUE+Bukkit.getPlayer(gamer.id).getName()+ChatColor.RED+" has lost the game with score "+gamer.score+ ". Players left: "+(plugin.players.size()-1) );

                    gamer.active = false;
                    plugin.players.set(i,gamer);
//                    if( plugin.players.size() < 2 ) {
//                        Bukkit.broadcastMessage(ChatColor.GREEN+""+ ChatColor.BOLD+"Game has ended!");
//                        plugin.gameState = false;
//                    }
                } if( gamer.check() & gamer.active) {
                    p.sendMessage(ChatColor.BOLD+""+ChatColor.YELLOW+"YOU HAVE FOUND YOUR BLOCK!" );
                    p.playSound( p.getLocation(), Sound.UI_TOAST_CHALLENGE_COMPLETE, 3f , 2f );
                    gamer.score += 1;
                    gamer.timeLeft = plugin.time;
                    gamer.genNewBlock();

                    plugin.players.set(i,gamer);
                }
            }
        }
    }
}
