package net.rillekk.customitems.timberaxe;


import de.tr7zw.nbtapi.NBTItem;
import net.rillekk.customitems.CustomItems;
import net.rillekk.customitems.items.Item;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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


public class TimberAxe implements Item, Listener {
    private final CustomItems plugin;

    private ItemStack timberAxe;
    private final String name = "TimberAxt";
    private final String nbtTag = "TimberAxt";

    public TimberAxe(CustomItems plugin) {
        this.plugin = plugin;
        timberAxe = new ItemStack(Material.DIAMOND_AXE);
        NBTItem nbtItem = new NBTItem(timberAxe);
        nbtItem.setString(this.nbtTag, "CustomItem");
        ItemMeta timberAxeMeta = nbtItem.getItem().getItemMeta();
        timberAxeMeta.setDisplayName(this.name);
        timberAxe.setItemMeta(timberAxeMeta);
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String nbtTag() {
        return this.nbtTag;
    }


    public ItemStack getTimberAxe() {
        return this.timberAxe;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        if (event.getBlock().getType().equals(Material.LOG) || event.getBlock().getType().equals(Material.LOG_2)) {
            Player player = event.getPlayer();
            ItemStack handItem = player.getItemInHand();

            if (handItem.getType().equals(Material.DIAMOND_AXE)) {
                NBTItem nbtItem = new NBTItem(handItem);

                if (nbtItem.hasKey(nbtTag)) {
                    event.setCancelled(true);
                    new FellingTree(event.getBlock().getLocation());
                }
            }
        }
    }
}