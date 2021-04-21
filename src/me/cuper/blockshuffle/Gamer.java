package me.cuper.blockshuffle;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.util.UUID;

public class Gamer {
    public UUID id;
    public int score;
    public int timeLeft;
    public boolean active;
    public Material block;

    public Gamer( UUID _id ) {
        this.id = _id;
        this.score = 0;
        this.active = false;
    }

    public Material randomBlock() {
        Random random = new Random();
        int sizeOfMaterials = Material.values().length;

        do {
            this.block = Material.values()[random.nextInt(sizeOfMaterials-1)];
        } while( !this.block.isSolid() || !this.block.isBlock() );

        Bukkit.getPlayer(id).sendMessage( ChatColor.GREEN+"Your new block is: "+ChatColor.BLUE+this.block.toString().replace("_"," ").toLowerCase() );
        return this.block;
    }

    public boolean check() {
        Player p = Bukkit.getPlayer(this.id);
        if( p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType() == block ) return true;
        return false;
    }

    public boolean genNewBlock() {
        Main plugin = Main.getPlugin(Main.class);

        randomBlock();

        this.active = true;
        this.timeLeft = plugin.time;

        return true;
    }
}
