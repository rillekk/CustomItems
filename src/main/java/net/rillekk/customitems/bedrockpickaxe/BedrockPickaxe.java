package net.rillekk.customitems.bedrockpickaxe;


import net.rillekk.customitems.CustomItems;
import net.rillekk.customitems.items.Item;

import de.tr7zw.nbtapi.*;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;


import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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


public class BedrockPickaxe implements Listener, Item, CommandExecutor {
    private final CustomItems plugin;
    private final ItemStack bedrockPickaxe;
    Economy economy = CustomItems.getEconomy();



    public BedrockPickaxe(CustomItems plugin) {
        this.plugin = plugin;
        bedrockPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        NBTItem nbtItem = new NBTItem(bedrockPickaxe);
        nbtItem.setString(this.nbtTag, "CustomItem");
        ItemMeta bedrockPickaxeMeta = nbtItem.getItem().getItemMeta();
        bedrockPickaxeMeta.setDisplayName(this.name);
        bedrockPickaxe.setItemMeta(bedrockPickaxeMeta);
    }


    public ItemStack getBedrockPickaxe() {
        return bedrockPickaxe;
    }

    private final String name = "BedrockPickaxe";
    private final String nbtTag = "BedrockPickaxe";


    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String nbtTag() {
        return this.nbtTag;
    }


    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        ItemStack handItem = player.getItemInHand();

        if (handItem.getType().equals(Material.DIAMOND_PICKAXE)) {
            NBTItem nbtItem = new NBTItem(handItem);

            if (nbtItem.hasKey(nbtTag)) {
                Block block = event.getBlock();

                event.setCancelled(true);
                block.breakNaturally();

                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {

                    for (int x = -1; x <= 1; x++) {
                        for (int z = -1; z <= 1; z++) {
                            for (int y = 256; y >= 0; y--) {
                                World blockWorld = block.getWorld();
                                Block blockUnten = blockWorld.getBlockAt(block.getX() + x, block.getY() - y, block.getZ() + z);

                                if (blockUnten.getType() != Material.BEDROCK) {
                                    blockUnten.breakNaturally();
                                }
                            }
                        }
                    }
                });
            }
        }
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            OfflinePlayer offlinePlayer = (Player) sender;
            ItemStack itemStack = player.getItemInHand();

            NBTItem nbtItem = new NBTItem(itemStack);
            nbtItem.setString(args[0], "CustomItem");
            player.sendMessage(plugin.getPrefix() + ChatColor.BLUE +"Dem Item wurde erfolgreich, der NBTTag: " + ChatColor.LIGHT_PURPLE + args[0].toString() + ChatColor.BLUE + " hinzugefügt.");

            ItemMeta bedrockPicke = nbtItem.getItem().getItemMeta();
            player.getItemInHand().setItemMeta(bedrockPicke);

            EconomyResponse resp = this.economy.depositPlayer(offlinePlayer, 1000);
        }
        return false;
    }
}
