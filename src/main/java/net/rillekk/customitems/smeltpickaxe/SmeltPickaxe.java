package net.rillekk.customitems.smeltpickaxe;


import de.tr7zw.nbtapi.NBTItem;
import net.rillekk.customitems.CustomItems;
import net.rillekk.customitems.items.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
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


public class SmeltPickaxe extends ItemStack implements Item, Listener {
    private final CustomItems plugin;
    private ItemStack smeltPickaxe;

    public SmeltPickaxe(CustomItems plugin) {
        this.plugin = plugin;
        smeltPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        NBTItem nbtItem = new NBTItem(smeltPickaxe);
        nbtItem.setString(this.nbtTag, "CustomItem");
        ItemMeta smeltPickaxeMeta = nbtItem.getItem().getItemMeta();
        smeltPickaxeMeta.setDisplayName(this.name);
        smeltPickaxe.setItemMeta(smeltPickaxeMeta);
    }

    private String name = "SmeltPickaxe";
    private String nbtTag = "SmeltPickaxe";


    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String nbtTag() {
        return this.nbtTag;
    }


    public ItemStack getSmeltPickaxe() {
        return this.smeltPickaxe;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack handItem = player.getItemInHand();

        if (handItem.getType().equals(Material.DIAMOND_PICKAXE)) {
            NBTItem nbtItem = new NBTItem(handItem);

            if (nbtItem.hasKey(nbtTag)) {
                Block block = event.getBlock();

                switch (block.getType()) {
                    case IRON_ORE:
                        ItemStack ironIngot = new ItemStack(Material.IRON_INGOT);
                        player.getWorld().dropItemNaturally(event.getBlock().getLocation(), ironIngot);
                        break;
                    case GOLD_ORE:
                        ItemStack goldIngot = new ItemStack(Material.GOLD_INGOT);
                        player.getWorld().dropItemNaturally(event.getBlock().getLocation(), goldIngot);
                        break;
                }

            }
        }
    }
}
