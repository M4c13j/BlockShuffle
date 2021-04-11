package me.cuper.blockshuffle;

import org.bukkit.block.Block;

import java.util.UUID;

public class Gamer {
    public UUID id;
    public int score;
    public Block block;

    public Gamer( UUID _id ) {
        this.id = _id;
    }
}
