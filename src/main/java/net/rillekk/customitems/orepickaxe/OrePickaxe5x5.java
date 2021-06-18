package net.rillekk.customitems.orepickaxe;


import net.rillekk.customitems.CustomItems;
import net.rillekk.customitems.items.Item;
import net.rillekk.customitems.utils.BlockfaceCheck;

import de.tr7zw.nbtapi.NBTItem;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
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


public class OrePickaxe5x5 implements Listener, Item {
    private final CustomItems plugin;

    private ItemStack orePickaxe5x5;
    private ItemStack orePickaxe5x5WithNBT;

    private final String name = "§6§lOrePickaxe5x5";
    private final String nbtTag = "§6§lOrePickaxe5x5";

    private final HashMap<String, BlockFace> faces = new HashMap<>();

    public OrePickaxe5x5(CustomItems plugin) {
        this.plugin = plugin;
        orePickaxe5x5 = new ItemStack(Material.DIAMOND_PICKAXE);
        NBTItem nbtItem = new NBTItem(orePickaxe5x5);
        nbtItem.setString(this.nbtTag, "CustomItem");
        ItemMeta orePickaxe5x5Meta = nbtItem.getItem().getItemMeta();
        orePickaxe5x5Meta.setDisplayName(this.name);
        orePickaxe5x5.setItemMeta(orePickaxe5x5Meta);
    }

    private OrePickaxe5x5(CustomItems plugin, String nbtTag) {
        this.plugin = plugin;
        orePickaxe5x5WithNBT = new ItemStack(Material.DIAMOND_PICKAXE);
        NBTItem nbtItem = new NBTItem(orePickaxe5x5WithNBT);
        nbtItem.setString(this.nbtTag + nbtTag, "CustomItem");
        ItemMeta orePickaxe5x5WithNBTMeta = nbtItem.getItem().getItemMeta();
        orePickaxe5x5WithNBTMeta.setDisplayName(this.name);
        orePickaxe5x5WithNBT.setItemMeta(orePickaxe5x5WithNBTMeta);
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

            if (nbtItem.hasKey(nbtTag) || nbtItem.hasKey(nbtTag + ".1") || nbtItem.hasKey(nbtTag + ".2")) {
                Block block = event.getBlock();
                event.setCancelled(true);
                block.breakNaturally();

                for (int x = -2; x <= 2; x++) {
                    for (int y = -2; y <= 2; y++) {
                        for (int z = -2; z <= 2; z++) {
                            Block newBlock = block.getRelative(x, y, z);
                            newBlock.breakNaturally();

                            if (handItem.getDurability() != handItem.getType().getMaxDurability()) {
                                handItem.setDurability((short) (handItem.getDurability() + 1));
                                player.updateInventory();

                            } else if (handItem.getDurability() <= handItem.getType().getMaxDurability() || handItem.getDurability() == 0) {
                                if (player.getInventory().getItemInHand().getAmount() == 1) {
                                    player.getInventory().setItemInHand(new ItemStack(Material.AIR));
                                } else {
                                    player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount() - 1);
                                }
                                player.updateInventory();

                                player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1F, 1F);
                            }
                        }
                    }
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

                    if (slotOneItemStack.equals(this.getOrePickaxe5x5()) ||
                            nbtItemSlotOne.hasKey(this.nbtTag + ".1") ||
                            nbtItemSlotOne.hasKey(this.nbtTag + ".2") ||
                            slotTwoItemStack.equals(this.getOrePickaxe5x5()) ||
                            nbtItemSlotTwo.hasKey(this.nbtTag + ".1") ||
                            nbtItemSlotTwo.hasKey(this.nbtTag + ".2")) {

                        if (player.hasPermission("ttools.enchant.2")) {
                            if (nbtItemSlotOne.hasKey(this.nbtTag() + ".1") || nbtItemSlotTwo.hasKey(this.nbtTag() + ".1")) {
                                OrePickaxe5x5 resultItem = new OrePickaxe5x5(this.plugin, ".2");
                                anvilInventory.setItem(2, resultItem.getOrePickaxe5x5WithNBT());

                                player.sendMessage(this.plugin.getPrefix() + "§dDu kannst die " + resultItem.getOrePickaxe5x5WithNBT().getItemMeta().getDisplayName() + " §dnicht mehr im Amboss benutzen!");

                            } else if (nbtItemSlotOne.hasKey(this.nbtTag()) || nbtItemSlotTwo.hasKey(this.nbtTag())) {
                                OrePickaxe5x5 resultItem = new OrePickaxe5x5(this.plugin, ".1");
                                anvilInventory.setItem(2, resultItem.getOrePickaxe5x5WithNBT());

                                player.sendMessage(this.plugin.getPrefix() + "§dDu kannst die " + resultItem.getOrePickaxe5x5WithNBT().getItemMeta().getDisplayName() + " §dnoch ein Mal im Amboss benutzen!");
                            } else {
                                event.setCancelled(true);
                                player.closeInventory();
                                player.sendMessage(plugin.getPrefix() + "§dDu kannst dieses Item nicht im Amboss benutzen!");
                            }


                        } else if (player.hasPermission("ttools.enchant.1")) {
                            if (nbtItemSlotOne.hasKey(this.nbtTag()) || nbtItemSlotTwo.hasKey(this.nbtTag())) {
                                OrePickaxe5x5 resultItem = new OrePickaxe5x5(this.plugin, ".1");
                                anvilInventory.setItem(2, resultItem.getOrePickaxe5x5WithNBT());

                                player.sendMessage(this.plugin.getPrefix() + "§dDu kannst die " + resultItem.getOrePickaxe5x5WithNBT().getItemMeta().getDisplayName() + " §dnicht mehr im Amboss benutzen!");

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

    public ItemStack getOrePickaxe5x5() {
        return this.orePickaxe5x5;
    }

    public ItemStack getOrePickaxe5x5WithNBT() {
        return orePickaxe5x5WithNBT;
    }


    public BlockFace getBlockFaceByPlayerName(String name) {
        return faces.get(name);
    }
}

