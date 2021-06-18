package net.rillekk.customitems.areahoe;


import net.rillekk.customitems.CustomItems;
import net.rillekk.customitems.items.Item;

import de.tr7zw.nbtapi.NBTItem;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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


public class AreaHoe implements Item, Listener {
    private final CustomItems plugin;

    private ItemStack areaHoe;
    private ItemStack areaHoeWithNBT;

    private String name = "§6§lAreaHoe";
    private String nbtTag = "§6§lAreaHoe";

    public AreaHoe(CustomItems plugin) {
        this.plugin = plugin;
        areaHoe = new ItemStack(Material.DIAMOND_HOE);
        NBTItem nbtItem = new NBTItem(areaHoe);
        nbtItem.setString(this.nbtTag, "CustomItem");
        ItemMeta areaHoeMeta = nbtItem.getItem().getItemMeta();
        areaHoeMeta.setDisplayName(this.name);
        areaHoe.setItemMeta(areaHoeMeta);
    }

    private AreaHoe(CustomItems plugin, String nbtTag) {
        this.plugin = plugin;
        areaHoeWithNBT = new ItemStack(Material.DIAMOND_HOE);
        NBTItem nbtItem = new NBTItem(areaHoeWithNBT);
        nbtItem.setString(this.nbtTag + nbtTag, "CustomItem");
        ItemMeta areaHoeWithNBTMeta = nbtItem.getItem().getItemMeta();
        areaHoeWithNBTMeta.setDisplayName(this.name);
        areaHoeWithNBT.setItemMeta(areaHoeWithNBTMeta);
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

        if (handItem.getType().equals(Material.DIAMOND_HOE)) {
            NBTItem nbtItem = new NBTItem(handItem);

            if (nbtItem.hasKey(nbtTag) || nbtItem.hasKey(nbtTag + ".1") || nbtItem.hasKey(nbtTag + ".2")) {
                Block block = event.getBlock();
                event.setCancelled(true);

                int x = block.getX();
                int y = block.getY();
                int z = block.getZ();


                switch (block.getType()) {
                    case CROPS:
                        for (int i = x - 2; i < x + 3; i++) {
                            for (int k = z - 2; k < z + 3; k++) {
                                Block newBlock = block.getWorld().getBlockAt(i, y, k);
                                newBlock.breakNaturally();
                                newBlock.setType(Material.CROPS);
                            }
                        }
                        break;

                    case CARROT:
                        for (int i = x - 2; i < x + 3; i++) {
                            for (int k = z - 2; k < z + 3; k++) {
                                Block newBlock = block.getWorld().getBlockAt(i, y, k);
                                newBlock.breakNaturally();
                                newBlock.setType(Material.CARROT);
                            }
                        }
                        break;

                    case POTATO:
                        for (int i = x - 2; i < x + 3; i++) {
                            for (int k = z - 2; k < z + 3; k++) {
                                Block newBlock = block.getWorld().getBlockAt(i, y, k);
                                newBlock.breakNaturally();
                                newBlock.setType(Material.POTATO);
                            }
                        }
                        break;

                    case NETHER_WARTS:
                        for (int i = x - 2; i < x + 3; i++) {
                            for (int k = z - 2; k < z + 3; k++) {
                                Block newBlock = block.getWorld().getBlockAt(i, y, k);
                                newBlock.breakNaturally();
                                newBlock.setType(Material.NETHER_WARTS);
                            }
                        }
                        break;
                }
            }
        }
    }

    @EventHandler
    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType().equals(Material.DIAMOND_HOE)) {
            if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {

                ItemStack handItem = event.getPlayer().getItemInHand();
                NBTItem nbtItem = new NBTItem(handItem);

                if (nbtItem.hasKey(nbtTag) || nbtItem.hasKey(nbtTag + ".1") || nbtItem.hasKey(nbtTag + ".2")) {
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

                            if (handItem.getDurability() != handItem.getType().getMaxDurability()) {
                                handItem.setDurability((short) (handItem.getDurability() + 1));
                                player.updateInventory();

                            } else if (handItem.getDurability() <= handItem.getType().getMaxDurability() || handItem.getDurability() == 0) {
                                if (player.getInventory().getItemInHand().getAmount() == 1) {
                                    player.getInventory().setItemInHand(new ItemStack(Material.AIR));
                                } else {
                                    player.getInventory().getItemInHand().setAmount(player.getInventory().getItemInHand().getAmount()-1);
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

                    if (slotOneItemStack.equals(this.getAreaHoe()) ||
                            nbtItemSlotOne.hasKey(this.nbtTag + ".1") ||
                            nbtItemSlotOne.hasKey(this.nbtTag + ".2") ||
                            slotTwoItemStack.equals(this.getAreaHoe()) ||
                            nbtItemSlotTwo.hasKey(this.nbtTag + ".1") ||
                            nbtItemSlotTwo.hasKey(this.nbtTag + ".2")) {

                        if (player.hasPermission("ttools.enchant.2")) {
                            if (nbtItemSlotOne.hasKey(this.nbtTag() + ".1") || nbtItemSlotTwo.hasKey(this.nbtTag() + ".1")) {
                                AreaHoe resultItem = new AreaHoe(this.plugin, ".2");
                                anvilInventory.setItem(2, resultItem.getAreaHoeWithNBT());

                                player.sendMessage(this.plugin.getPrefix() + "§dDu kannst die " + resultItem.getAreaHoeWithNBT().getItemMeta().getDisplayName() + " §dnicht mehr im Amboss benutzen!");

                            } else if (nbtItemSlotOne.hasKey(this.nbtTag()) || nbtItemSlotTwo.hasKey(this.nbtTag())) {
                                AreaHoe resultItem = new AreaHoe(this.plugin, ".1");
                                anvilInventory.setItem(2, resultItem.getAreaHoeWithNBT());

                                player.sendMessage(this.plugin.getPrefix() + "§dDu kannst die " + resultItem.getAreaHoeWithNBT().getItemMeta().getDisplayName() + " §dnoch ein Mal im Amboss benutzen!");
                            } else {
                                event.setCancelled(true);
                                player.closeInventory();
                                player.sendMessage(plugin.getPrefix() + "§dDu kannst dieses Item nicht im Amboss benutzen!");
                            }


                        } else if (player.hasPermission("ttools.enchant.1")) {
                            if (nbtItemSlotOne.hasKey(this.nbtTag()) || nbtItemSlotTwo.hasKey(this.nbtTag())) {
                                AreaHoe resultItem = new AreaHoe(this.plugin, ".1");
                                anvilInventory.setItem(2, resultItem.getAreaHoeWithNBT());

                                player.sendMessage(this.plugin.getPrefix() + "§dDu kannst die " + resultItem.getAreaHoeWithNBT().getItemMeta().getDisplayName() + " §dnicht mehr im Amboss benutzen!");

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

    public ItemStack getAreaHoe() {
        return this.areaHoe;
    }

    public ItemStack getAreaHoeWithNBT() {
        return areaHoeWithNBT;
    }
}