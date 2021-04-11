package me.cuper.blockshuffle;

import me.cuper.blockshuffle.commands.Commands;
import me.cuper.blockshuffle.commands.Shuffle;
import me.cuper.blockshuffle.events.eventHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    public Logger logger = getLogger();

    // variables
    public boolean gameState = false;
    public int time = 600;

    // Player list
    public Set<Gamer> players = new HashSet<>();

    @Override
    public void onEnable() {
        // ------ COMMANDS -------
        Commands commands = new Commands();
        getCommand("test").setExecutor(commands);
        getCommand("blockshuffle").setExecutor( new Shuffle() );

        // ------ EVENTS ----
        getServer().getPluginManager().registerEvents(new eventHandler(),this);

        // ------- MISC -----
        logger.info( "BlockShuffle plugin has been enabled!");

    }

    @Override
    public void onDisable() {
        return;
    }

    // add player to set by id
    public void addPlayer(UUID id) {
        players.add( new Gamer(id) );
    }
    // remove player from set by id
    public boolean removePlayer( UUID id ) {
        for( Gamer gamer : players ) {
            if(gamer.id == id) {
                players.remove( gamer );
                return true; // found & deleted
            }
        }
        return false; // not found & not deleted
    }
    // If UUID in players
    public boolean contains(UUID id ) {
        for(Gamer gamer : players) {
            if( gamer.id == id ) return true;
        }
        return false;
    }
    // returns gamer
    @Nullable
    public Gamer getGamer(UUID id) {
        for( Gamer gamer : players ) {
            if( gamer.id == id ) return gamer;
        }
        return null;
    }
    // returns all elements from players
    public String playersToString() {
        String ans = "";
        for( Gamer g : players) {
            ans+= Bukkit.getPlayer(g.id).getName()+" ";
        }
        return ans;
    }
}