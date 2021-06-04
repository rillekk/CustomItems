package net.rillekk.customitems.utils;


import net.rillekk.customitems.CustomItems;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Listener;

import java.util.*;

/***************************************************************
 *                                                             *
 *   @author Rillekk                                           *
 *   @Instagram: rillekk                                       *
 *   @Discord: Rillekk#8642                                    *
 *                                                             *
 *                                                             *
 *   Jede Art der Vervielfältigung, Verbreitung, Verleihung,   *
 *   öffentlich Zugänglichmachung oder andere Nutzung bedarf   *
 *   der ausdrücklichen, schriftlichen Zustimmung von Rillekk. *
 *                                                             *
 ***************************************************************/

@Getter
@Setter
public class BlockfaceCheck implements Listener {

    private final CustomItems plugin;
    public BlockfaceCheck(CustomItems plugin) {
        this.plugin = plugin;
    }

    private final HashMap<String, BlockFace> faces = new HashMap<>();


    public ArrayList<Block> getSurroundingBlocks(BlockFace blockFace, Block targetBlock) {
        ArrayList<Block> blocks = new ArrayList<>();
        World world = targetBlock.getWorld();
        int x = targetBlock.getX();
        int y = targetBlock.getY();
        int z = targetBlock.getZ();
        if (blockFace == null) {
            blocks.add(world.getBlockAt(x + 1, y, z));
            blocks.add(world.getBlockAt(x - 1, y, z));
            blocks.add(world.getBlockAt(x, y, z + 1));
            blocks.add(world.getBlockAt(x, y, z - 1));
            blocks.add(world.getBlockAt(x + 1, y, z + 1));
            blocks.add(world.getBlockAt(x - 1, y, z - 1));
            blocks.add(world.getBlockAt(x + 1, y, z - 1));
            blocks.add(world.getBlockAt(x - 1, y, z + 1));
        }
        switch (Objects.requireNonNull(blockFace)) {
            case UP:
                blocks.add(world.getBlockAt(x + 1, y, z));
                blocks.add(world.getBlockAt(x - 1, y, z));
                blocks.add(world.getBlockAt(x, y, z + 1));
                blocks.add(world.getBlockAt(x, y, z - 1));
                blocks.add(world.getBlockAt(x + 1, y, z + 1));
                blocks.add(world.getBlockAt(x - 1, y, z - 1));
                blocks.add(world.getBlockAt(x + 1, y, z - 1));
                blocks.add(world.getBlockAt(x - 1, y, z + 1));
                break;
            case EAST:
            case WEST:
                blocks.add(world.getBlockAt(x, y, z + 1));
                blocks.add(world.getBlockAt(x, y, z - 1));
                blocks.add(world.getBlockAt(x, y + 1, z));
                blocks.add(world.getBlockAt(x, y - 1, z));
                blocks.add(world.getBlockAt(x, y + 1, z + 1));
                blocks.add(world.getBlockAt(x, y - 1, z - 1));
                blocks.add(world.getBlockAt(x, y - 1, z + 1));
                blocks.add(world.getBlockAt(x, y + 1, z - 1));
                break;
            case NORTH:
            case SOUTH:
                blocks.add(world.getBlockAt(x + 1, y, z));
                blocks.add(world.getBlockAt(x - 1, y, z));
                blocks.add(world.getBlockAt(x, y + 1, z));
                blocks.add(world.getBlockAt(x, y - 1, z));
                blocks.add(world.getBlockAt(x + 1, y + 1, z));
                blocks.add(world.getBlockAt(x - 1, y - 1, z));
                blocks.add(world.getBlockAt(x + 1, y - 1, z));
                blocks.add(world.getBlockAt(x - 1, y + 1, z));
                break;
        }

        blocks.removeAll(Collections.singleton(null));
        return blocks;

    }
}
