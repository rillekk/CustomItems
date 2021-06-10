package net.rillekk.customitems.timberaxe;


import net.rillekk.customitems.CustomItems;
import net.rillekk.customitems.items.Item;

import de.tr7zw.nbtapi.NBTItem;

import org.bukkit.Material;
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


public class TimberAxe implements Item, Listener {
    private final CustomItems plugin;

    private ItemStack timberAxe;
    private ItemStack timberAxeWithNBT;

    private final String name = "§6§lTimberAxt";
    private final String nbtTag = "§6§lTimberAxt";

    public TimberAxe(CustomItems plugin) {
        this.plugin = plugin;
        timberAxe = new ItemStack(Material.DIAMOND_AXE);
        NBTItem nbtItem = new NBTItem(timberAxe);
        nbtItem.setString(this.nbtTag, "CustomItem");
        ItemMeta timberAxeMeta = nbtItem.getItem().getItemMeta();
        timberAxeMeta.setDisplayName(this.name);
        timberAxe.setItemMeta(timberAxeMeta);
    }

    private TimberAxe(CustomItems plugin, String nbtTag) {
        this.plugin = plugin;
        timberAxeWithNBT = new ItemStack(Material.DIAMOND_AXE);
        NBTItem nbtItem = new NBTItem(timberAxeWithNBT);
        nbtItem.setString(this.nbtTag + nbtTag, "CustomItem");
        ItemMeta timberAxeWithNBTMeta = nbtItem.getItem().getItemMeta();
        timberAxeWithNBTMeta.setDisplayName(this.name);
        timberAxeWithNBT.setItemMeta(timberAxeWithNBTMeta);
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

        if (event.getBlock().getType().equals(Material.LOG) || event.getBlock().getType().equals(Material.LOG_2)) {
            Player player = event.getPlayer();
            ItemStack handItem = player.getItemInHand();

            if (handItem.getType().equals(Material.DIAMOND_AXE)) {
                NBTItem nbtItem = new NBTItem(handItem);

                if (nbtItem.hasKey(nbtTag) || nbtItem.hasKey(nbtTag + ".1") || nbtItem.hasKey(nbtTag + ".2")) {
                    event.setCancelled(true);
                    new FellingTree(event.getBlock().getLocation());
                }
            }
        }
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

                    if (slotOneItemStack.equals(this.getTimberAxe()) ||
                            nbtItemSlotOne.hasKey(this.nbtTag + ".1") ||
                            nbtItemSlotOne.hasKey(this.nbtTag + ".2") ||
                            slotTwoItemStack.equals(this.getTimberAxe()) ||
                            nbtItemSlotTwo.hasKey(this.nbtTag + ".1") ||
                            nbtItemSlotTwo.hasKey(this.nbtTag + ".2")) {

                        if (player.hasPermission("ttools.enchant.2")) {
                            if (nbtItemSlotOne.hasKey(this.nbtTag() + ".1") || nbtItemSlotTwo.hasKey(this.nbtTag() + ".1")) {
                                TimberAxe resultItem = new TimberAxe(this.plugin, ".2");
                                anvilInventory.setItem(2, resultItem.getTimberAxeWithNBT());

                                player.sendMessage(this.plugin.getPrefix() + "§dDu kannst die " + resultItem.getTimberAxeWithNBT().getItemMeta().getDisplayName() + " §dnicht mehr im Amboss benutzen!");

                            } else if (nbtItemSlotOne.hasKey(this.nbtTag()) || nbtItemSlotTwo.hasKey(this.nbtTag())) {
                                TimberAxe resultItem = new TimberAxe(this.plugin, ".1");
                                anvilInventory.setItem(2, resultItem.getTimberAxeWithNBT());

                                player.sendMessage(this.plugin.getPrefix() + "§dDu kannst die " + resultItem.getTimberAxeWithNBT().getItemMeta().getDisplayName() + " §dnoch ein Mal im Amboss benutzen!");
                            } else {
                                event.setCancelled(true);
                                player.closeInventory();
                                player.sendMessage(plugin.getPrefix() + "§dDu kannst dieses Item nicht im Amboss benutzen!");
                            }


                        } else if (player.hasPermission("ttools.enchant.1")) {
                            if (nbtItemSlotOne.hasKey(this.nbtTag()) || nbtItemSlotTwo.hasKey(this.nbtTag())) {
                                TimberAxe resultItem = new TimberAxe(this.plugin, ".1");
                                anvilInventory.setItem(2, resultItem.getTimberAxeWithNBT());

                                player.sendMessage(this.plugin.getPrefix() + "§dDu kannst die " + resultItem.getTimberAxeWithNBT().getItemMeta().getDisplayName() + " §dnicht mehr im Amboss benutzen!");

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

    public ItemStack getTimberAxe() {
        return this.timberAxe;
    }

    public ItemStack getTimberAxeWithNBT() {
        return timberAxeWithNBT;
    }
}
