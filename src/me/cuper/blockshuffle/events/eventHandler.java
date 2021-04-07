package me.cuper.blockshuffle.events;

import me.cuper.blockshuffle.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class eventHandler implements Listener {
    private final Main plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onPlayerUse(PlayerInteractEvent event) {
        return;
    }
}