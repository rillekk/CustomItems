package net.rillekk.customitems.bedrockpickaxe;


import net.rillekk.customitems.CustomItems;
import net.rillekk.customitems.items.Item;

import de.tr7zw.nbtapi.*;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
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

    private ItemStack bedrockPickaxe;
    private ItemStack bedrockPickaxeWithNBT;

    private final String name = "§6§lBedrockPickaxe";
    private final String nbtTag = "§6§lBedrockPickaxe";

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

    private BedrockPickaxe(CustomItems plugin, String nbtTag) {
        this.plugin = plugin;
        bedrockPickaxeWithNBT = new ItemStack(Material.DIAMOND_PICKAXE);
        NBTItem nbtItem = new NBTItem(bedrockPickaxeWithNBT);
        nbtItem.setString(this.nbtTag + nbtTag, "CustomItem");
        ItemMeta bedrockPickaxeWithNBTMeta = nbtItem.getItem().getItemMeta();
        bedrockPickaxeWithNBTMeta.setDisplayName(this.name);
        bedrockPickaxeWithNBT.setItemMeta(bedrockPickaxeWithNBTMeta);
    }



    public ItemStack getBedrockPickaxe() {
        return bedrockPickaxe;
    }
    public ItemStack getBedrockPickaxeWithNBT() {
        return bedrockPickaxeWithNBT;
    }


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

            if (nbtItem.hasKey(nbtTag) || nbtItem.hasKey(nbtTag + ".1") || nbtItem.hasKey(nbtTag + ".2")) {
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

            this.economy.depositPlayer(player, 1000);

            player.sendMessage("Das ist dein Money: " + this.economy.getBalance(offlinePlayer));
        }
        return false;
    }

    @EventHandler
    public void onAnvilInventoryClick(InventoryClickEvent event) {
        HumanEntity humanEntity = event.getWhoClicked();

        if (humanEntity instanceof Player) {
            Inventory clickedInventory = event.getClickedInventory();

            if (clickedInventory instanceof AnvilInventory) {
                Player player = (Player) humanEntity;

                if (event.getSlot() == 2) {
                    AnvilInventory anvilInventory = (AnvilInventory) clickedInventory;

                    ItemStack slotOneItemStack = anvilInventory.getItem(0);
                    ItemStack slotTwoItemStack = anvilInventory.getItem(1);
                    NBTItem nbtItemSlotOne = new NBTItem(slotOneItemStack);
                    NBTItem nbtItemSlotTwo = new NBTItem(slotTwoItemStack);

                    if (slotOneItemStack.equals(this.getBedrockPickaxe()) ||
                            nbtItemSlotOne.hasKey(this.nbtTag + ".1") ||
                            nbtItemSlotOne.hasKey(this.nbtTag + ".2") ||
                            slotTwoItemStack.equals(this.getBedrockPickaxe()) ||
                            nbtItemSlotTwo.hasKey(this.nbtTag + ".1") ||
                            nbtItemSlotTwo.hasKey(this.nbtTag + ".2")) {

                        if (player.hasPermission("ttools.enchant.2")) {
                            if (nbtItemSlotOne.hasKey(this.nbtTag() + ".1") || nbtItemSlotTwo.hasKey(this.nbtTag() + ".1")) {
                                BedrockPickaxe resultItem = new BedrockPickaxe(this.plugin, ".2");
                                anvilInventory.setItem(2, resultItem.getBedrockPickaxeWithNBT());

                                player.sendMessage(this.plugin.getPrefix() + "§dDu kannst die " + resultItem.getBedrockPickaxeWithNBT().getItemMeta().getDisplayName() + " nicht mehr im Amboss benutzen!");

                            } else if (nbtItemSlotOne.hasKey(this.nbtTag()) || nbtItemSlotTwo.hasKey(this.nbtTag())) {
                                BedrockPickaxe resultItem = new BedrockPickaxe(this.plugin, ".1");
                                anvilInventory.setItem(2, resultItem.getBedrockPickaxeWithNBT());

                                player.sendMessage(this.plugin.getPrefix() + "§dDu kannst die " + resultItem.getBedrockPickaxeWithNBT().getItemMeta().getDisplayName() + " noch ein Mal im Amboss benutzen!");
                            } else {
                                event.setCancelled(true);
                                player.closeInventory();
                                player.sendMessage(plugin.getPrefix() + "§dDu kannst dieses Item nicht im Amboss benutzen!");
                            }


                        } else if (player.hasPermission("ttools.enchant.1")) {
                            if (nbtItemSlotOne.hasKey(this.nbtTag()) || nbtItemSlotTwo.hasKey(this.nbtTag())) {
                                BedrockPickaxe resultItem = new BedrockPickaxe(this.plugin, ".1");
                                anvilInventory.setItem(2, resultItem.getBedrockPickaxeWithNBT());

                                player.sendMessage(this.plugin.getPrefix() + "§dDu kannst die " + resultItem.getBedrockPickaxeWithNBT().getItemMeta().getDisplayName() + " nicht mehr im Amboss benutzen!");

                            } else if (nbtItemSlotOne.hasKey(this.nbtTag() + ".2") || nbtItemSlotTwo.hasKey(this.nbtTag() + ".2")) {
                                event.setCancelled(true);
                                player.closeInventory();
                                player.sendMessage(plugin.getPrefix() + "§dDu kannst dieses Item nicht nochmal im Amboss benutzen!");

                            } else {
                                event.setCancelled(true);
                                player.closeInventory();
                                player.sendMessage(plugin.getPrefix() + "§dDu kannst dieses Item nicht im Amboss benutzen!");
                            }

                        } else {
                            event.setCancelled(true);
                            player.closeInventory();
                            player.sendMessage(plugin.getPrefix() + "§dDu hast keine Berechtigung dazu, dieses Item im Amboss zu benutzen!");
                        }
                    }
                }
            }
        }
    }
}
