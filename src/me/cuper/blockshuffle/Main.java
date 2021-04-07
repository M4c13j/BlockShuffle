package me.cuper.blockshuffle;

import me.cuper.blockshuffle.commands.Commands;
import me.cuper.blockshuffle.events.eventHandler;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class Main extends JavaPlugin {
    public Logger logger = getLogger();

    // variables
    public boolean gameState = false;

    @Override
    public void onEnable() {
        // ------ COMMANDS -------
        Commands commands = new Commands();
        getCommand("test").setExecutor(commands);

        // ------ EVENTS ----
        getServer().getPluginManager().registerEvents(new eventHandler(),this);

        // ------- MISC -----
        logger.info( "BlockShuffle plugin has been enabled!");
    }

    @Override
    public void onDisable() {
        return;
    }
}