package net.rillekk.customitems.customdrops;

import de.tr7zw.nbtapi.NBTItem;
import net.rillekk.customitems.CustomItems;
import net.rillekk.customitems.items.Item;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Random;

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


public class CustomdropsPickaxe implements Listener, Item {
    private final CustomItems plugin;
    private ItemStack customdropsPickaxe;

    public CustomdropsPickaxe(CustomItems plugin) {
        this.plugin = plugin;
        customdropsPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        NBTItem nbtItem = new NBTItem(customdropsPickaxe);
        nbtItem.setString(this.nbtTag, "CustomItem");
        ItemMeta customdropsPickaxeMeta = nbtItem.getItem().getItemMeta();
        customdropsPickaxeMeta.setDisplayName(this.name);
        customdropsPickaxe.setItemMeta(customdropsPickaxeMeta);
    }

    private final String name = "CustomdropsPickaxe";
    private final String nbtTag = "CustomdropsPickaxe";

    public ItemStack getCustomdrpsPickaxe() {
        return this.customdropsPickaxe;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String nbtTag() {
        return this.nbtTag;
    }

    private Integer dropChance = 90;

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack handItem = event.getPlayer().getItemInHand();

        if (handItem.getType().equals(Material.DIAMOND_PICKAXE)) {
            NBTItem nbtItem = new NBTItem(handItem);

            if (nbtItem.hasKey(nbtTag)) {
                Block block = event.getBlock();

                Location blockLocation = block.getLocation();

                Random random = new Random();
                int chance = random.nextInt(100);


                switch (block.getType()) {
                    case IRON_ORE:
                    case GOLD_ORE:
                    case COAL_ORE:
                    case DIAMOND_ORE:
                    case EMERALD_ORE:
                        if (chance < dropChance) {
                            ItemStack ironIngot = new ItemStack(Material.IRON_INGOT);
                            ItemStack goldIngot = new ItemStack(Material.GOLD_ORE);
                            ItemStack diamondIngot = new ItemStack(Material.DIAMOND_ORE);
                            ItemStack coalIngot = new ItemStack(Material.COAL_ORE);

                            player.getWorld().dropItemNaturally(blockLocation, goldIngot);
                            player.getWorld().dropItemNaturally(blockLocation, diamondIngot);
                            player.getWorld().dropItemNaturally(blockLocation, coalIngot);
                            player.getWorld().dropItemNaturally(blockLocation, ironIngot);
                        }
                        break;
                }
            }
        }
    }
}
