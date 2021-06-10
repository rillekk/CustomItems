package net.rillekk.customitems.repairgui;


import net.rillekk.customitems.CustomItems;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

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


public class RepairGUIListener implements Listener {
    private CustomItems plugin;

    private final Integer maxRepairCost;
    private final Economy economy = CustomItems.getEconomy();

    public RepairGUIListener(CustomItems plugin) {
        this.plugin = plugin;
        this.maxRepairCost = this.plugin.getConfig().getInt("maxRepairCost");
    }


    @EventHandler
    private void onClickSlot(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();

            if (player.getOpenInventory().getTitle().equalsIgnoreCase(plugin.getGuiName())) {
                Inventory repairInventory = this.plugin.getRepairInventory();
                ItemStack repairItem = repairInventory.getItem(13);

                ItemStack clickedItem = event.getCurrentItem();

                if (clickedItem.getType().equals(Material.ANVIL) || clickedItem.getType().equals(Material.INK_SACK) || clickedItem.getType().equals(Material.STAINED_GLASS_PANE))
                    event.setCancelled(true);


                if (clickedItem.getType().equals(Material.ANVIL)) {

                    if (repairItem.getDurability() == 0) {
                        return;
                    }

                    int prozent = 100 / repairItem.getType().getMaxDurability() * repairItem.getDurability();
                    int totalRepairCost = maxRepairCost / 100 * prozent;

                    EconomyResponse resp = this.economy.withdrawPlayer(player, totalRepairCost);
                    if (resp.transactionSuccess()) {
                        repairItem.setDurability((short) 0);
                    } else {
                        player.sendMessage(plugin.getPrefix() + "§dDu hast nicht genügend Geld dafür!");
                    }

                } else if (clickedItem.getType().equals(Material.INK_SACK)) {
                    plugin.getShopInventory().open(player);
                }
            }

        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (event.getPlayer() instanceof Player) {
            Player player = (Player) event.getPlayer();

            if (event.getInventory().getTitle().equalsIgnoreCase(plugin.getGuiName())) {
                Inventory repairInventory = this.plugin.getRepairInventory();
                ItemStack repairItem = repairInventory.getItem(13);
                if (repairItem != null) {
                    repairInventory.removeItem(repairItem);
                    player.getInventory().addItem(repairItem);
                }
            }
        }
    }
}
