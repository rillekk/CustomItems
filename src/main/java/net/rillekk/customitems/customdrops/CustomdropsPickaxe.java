package net.rillekk.customitems.customdrops;


import net.rillekk.customitems.CustomItems;
import net.rillekk.customitems.items.Item;

import de.tr7zw.nbtapi.NBTItem;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
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
    private ItemStack customdropsPickaxeWithNBT;

    private final String name = "§6§lCustomdropsPickaxe";
    private final String nbtTag = "§6§lCustomdropsPickaxe";

    public CustomdropsPickaxe(CustomItems plugin) {
        this.plugin = plugin;
        customdropsPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        NBTItem nbtItem = new NBTItem(customdropsPickaxe);
        nbtItem.setString(this.nbtTag, "CustomItem");
        ItemMeta customdropsPickaxeMeta = nbtItem.getItem().getItemMeta();
        customdropsPickaxeMeta.setDisplayName(this.name);
        customdropsPickaxe.setItemMeta(customdropsPickaxeMeta);
    }

    private CustomdropsPickaxe(CustomItems plugin, String nbtTag) {
        this.plugin = plugin;
        customdropsPickaxeWithNBT = new ItemStack(Material.DIAMOND_PICKAXE);
        NBTItem nbtItem = new NBTItem(customdropsPickaxeWithNBT);
        nbtItem.setString(this.nbtTag + nbtTag, "CustomItem");
        ItemMeta customdropsPickaxeWithNBTMeta = nbtItem.getItem().getItemMeta();
        customdropsPickaxeWithNBTMeta.setDisplayName(this.name);
        customdropsPickaxeWithNBT.setItemMeta(customdropsPickaxeWithNBTMeta);
    }

    public ItemStack getCustomdrpsPickaxe() {
        return this.customdropsPickaxe;
    }
    public ItemStack getCustomdropsPickaxeWithNBT() {
        return customdropsPickaxeWithNBT;
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

            if (nbtItem.hasKey(nbtTag) || nbtItem.hasKey(nbtTag + ".1") || nbtItem.hasKey(nbtTag + ".2")) {
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

                    if (slotOneItemStack.equals(this.getCustomdrpsPickaxe()) ||
                            nbtItemSlotOne.hasKey(this.nbtTag + ".1") ||
                            nbtItemSlotOne.hasKey(this.nbtTag + ".2") ||
                            slotTwoItemStack.equals(this.getCustomdrpsPickaxe()) ||
                            nbtItemSlotTwo.hasKey(this.nbtTag + ".1") ||
                            nbtItemSlotTwo.hasKey(this.nbtTag + ".2")) {

                        if (player.hasPermission("ttools.enchant.2")) {
                            if (nbtItemSlotOne.hasKey(this.nbtTag() + ".1") || nbtItemSlotTwo.hasKey(this.nbtTag() + ".1")) {
                                CustomdropsPickaxe resultItem = new CustomdropsPickaxe(this.plugin, ".2");
                                anvilInventory.setItem(2, resultItem.getCustomdropsPickaxeWithNBT());

                                player.sendMessage(this.plugin.getPrefix() + "§dDu kannst die " + resultItem.getCustomdropsPickaxeWithNBT().getItemMeta().getDisplayName() + " nicht mehr im Amboss benutzen!");

                            } else if (nbtItemSlotOne.hasKey(this.nbtTag()) || nbtItemSlotTwo.hasKey(this.nbtTag())) {
                                CustomdropsPickaxe resultItem = new CustomdropsPickaxe(this.plugin, ".1");
                                anvilInventory.setItem(2, resultItem.getCustomdropsPickaxeWithNBT());

                                player.sendMessage(this.plugin.getPrefix() + "§dDu kannst die " + resultItem.getCustomdropsPickaxeWithNBT().getItemMeta().getDisplayName() + " noch ein Mal im Amboss benutzen!");
                            } else {
                                event.setCancelled(true);
                                player.closeInventory();
                                player.sendMessage(plugin.getPrefix() + "§dDu kannst dieses Item nicht im Amboss benutzen!");
                            }


                        } else if (player.hasPermission("ttools.enchant.1")) {
                            if (nbtItemSlotOne.hasKey(this.nbtTag()) || nbtItemSlotTwo.hasKey(this.nbtTag())) {
                                CustomdropsPickaxe resultItem = new CustomdropsPickaxe(this.plugin, ".1");
                                anvilInventory.setItem(2, resultItem.getCustomdropsPickaxeWithNBT());

                                player.sendMessage(this.plugin.getPrefix() + "§dDu kannst die " + resultItem.getCustomdropsPickaxeWithNBT().getItemMeta().getDisplayName() + " nicht mehr im Amboss benutzen!");

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
