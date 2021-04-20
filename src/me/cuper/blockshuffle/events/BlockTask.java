package me.cuper.blockshuffle.events;

import me.cuper.blockshuffle.Main;
import org.bukkit.scheduler.BukkitRunnable;

public class BlockTask extends BukkitRunnable {
    public final Main plugin = Main.getPlugin(Main.class);

    /*
    TODO:
    - robienie tasków
    - sprawdzanie bloków
    - szukanie nowego bloku
     */
    @Override
    public void run() {
        plugin.logger.info("Task");
    }
}
