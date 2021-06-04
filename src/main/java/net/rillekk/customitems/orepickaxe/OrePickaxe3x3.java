package net.rillekk.customitems.orepickaxe;


import net.rillekk.customitems.CustomItems;
import net.rillekk.customitems.items.Item;
import net.rillekk.customitems.utils.BlockfaceCheck;

import de.tr7zw.nbtapi.NBTItem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;


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


public class OrePickaxe3x3 implements Listener, Item {
    private final CustomItems plugin;
    private ItemStack orePickaxe3x3;

    public OrePickaxe3x3(CustomItems plugin) {
        this.plugin = plugin;
        orePickaxe3x3 = new ItemStack(Material.DIAMOND_PICKAXE);
        NBTItem nbtItem = new NBTItem(orePickaxe3x3);
        nbtItem.setString(this.nbtTag, "CustomItem");
        ItemMeta orePickaxe3x3Meta = nbtItem.getItem().getItemMeta();
        orePickaxe3x3Meta.setDisplayName(this.name);
        orePickaxe3x3.setItemMeta(orePickaxe3x3Meta);
    }

    private final String name = "OrePickaxe3x3";
    private final String nbtTag = "OrePickaxe3x3";
    private final HashMap<String, BlockFace> faces = new HashMap<>();

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String nbtTag() {
        return this.nbtTag;
    }


    public ItemStack getOrePickaxe3x3() {
        return this.orePickaxe3x3;
    }

    public BlockFace getBlockFaceByPlayerName(String name) {
        return faces.get(name);
    }

    @EventHandler
    public void saveBlockFace(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        BlockFace bf = event.getBlockFace();

        if (player != null && bf != null) {
            String name = player.getName();
            this.faces.put(name, bf);
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack handItem = event.getPlayer().getItemInHand();

        if (handItem.getType().equals(Material.DIAMOND_PICKAXE)) {
            NBTItem nbtItem = new NBTItem(handItem);

            if (nbtItem.hasKey(nbtTag)) {
                Block block = event.getBlock();
                event.setCancelled(true);
                block.breakNaturally();

                BlockfaceCheck blockfaceCheck = new BlockfaceCheck(this.plugin);
                BlockFace blockFace = getBlockFaceByPlayerName(player.getName());
                ArrayList<Block> blocks = blockfaceCheck.getSurroundingBlocks(blockFace, block);

                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                    for (Block b : blocks) {
                        if (b.getType() == Material.BEDROCK) {
                            b.breakNaturally();
                        }
                    }
                });
            }
        }
    }
}

