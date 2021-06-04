package net.rillekk.customitems.areahoe;

import de.tr7zw.nbtapi.NBTItem;
import net.rillekk.customitems.CustomItems;
import net.rillekk.customitems.items.Item;
import org.bukkit.Bukkit;
import org.bukkit.CropState;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Crops;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;

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


public class AreaHoe implements Item, Listener {
    private final CustomItems plugin;

    private ItemStack areaHoe;

    public AreaHoe(CustomItems plugin) {
        this.plugin = plugin;
        areaHoe = new ItemStack(Material.DIAMOND_HOE);
        NBTItem nbtItem = new NBTItem(areaHoe);
        nbtItem.setString(this.nbtTag, "CustomItem");
        ItemMeta areaHoeMeta = nbtItem.getItem().getItemMeta();
        areaHoeMeta.setDisplayName(this.name);
        areaHoe.setItemMeta(areaHoeMeta);
    }

    private String name = "AreaHoe";
    private String nbtTag = "AreaHoe";

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String nbtTag() {
        return this.nbtTag;
    }

    public ItemStack getAreaHoe() {
        return this.areaHoe;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack handItem = player.getItemInHand();

        if (handItem.getType().equals(Material.DIAMOND_HOE)) {
            NBTItem nbtItem = new NBTItem(handItem);

            if (nbtItem.hasKey(nbtTag)) {
                Block block = event.getBlock();
                event.setCancelled(true);

                int x = block.getX();
                int y = block.getY();
                int z = block.getZ();


                switch (block.getType()) {
                    case CROPS:
                    case CARROT:
                    case POTATO:
                    case NETHER_WARTS:
                        for (int i = x - 2; i < x + 3; i++) {
                            for (int k = z - 2; k < z + 3; k++) {
                                Block newBlock = block.getWorld().getBlockAt(i, y, k);
                                newBlock.breakNaturally();
                            }
                        }
                        break;
                }
            }
        }
    }

    @EventHandler
    public void rightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType().equals(Material.DIAMOND_HOE)) {
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                ItemStack handItem = event.getPlayer().getItemInHand();
                NBTItem nbtItem = new NBTItem(handItem);

                if (nbtItem.hasKey(nbtTag)) {
                    Block block = event.getClickedBlock();

                    int x = block.getX();
                    int y = block.getY();
                    int z = block.getZ();

                    for (int i = x - 2; i < x + 3; i++) {
                        for (int k = z - 2; k < z + 3; k++) {
                            Block newBlock = block.getWorld().getBlockAt(i, y, k);

                            if (newBlock.getType() == Material.DIRT || newBlock.getType() == Material.GRASS) {
                                newBlock.setType(Material.SOIL);
                            }
                        }
                    }
                }
            }
        }
    }
}