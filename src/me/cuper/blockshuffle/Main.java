package me.cuper.blockshuffle;

import me.cuper.blockshuffle.commands.Commands;
import me.cuper.blockshuffle.commands.Shuffle;
import me.cuper.blockshuffle.events.BlockTask;
import me.cuper.blockshuffle.events.eventHandler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import javax.annotation.Nullable;
import java.util.*;
import java.util.logging.Logger;

public class Main extends JavaPlugin {
    public Logger logger = getLogger();

    // variables
    public boolean gameState = false;
    public int time = 300;
    public int refreshTime = 5;

    // Player list
    public ArrayList<Gamer> players = new ArrayList<Gamer>();

    @Override
    public void onEnable() {
        // ------ COMMANDS -------
        Commands commands = new Commands();
        getCommand("test").setExecutor(commands);
        getCommand("blockshuffle").setExecutor( new Shuffle(this) );

        // ------ EVENTS ----
        getServer().getPluginManager().registerEvents(new eventHandler(),this);

        // ------ TASKS ----
        BukkitTask blockCheckingTask = new BlockTask(this).runTaskTimerAsynchronously(this,0,refreshTime);

        // ------- MISC -----
        logger.info( "BlockShuffle plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        return;
    }

    // add player to list by id
    public void addPlayer(UUID id) {
        players.add( new Gamer(id) );
    }
    // remove player from list by id
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