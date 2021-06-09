package net.rillekk.customitems.repairGUI;


import net.rillekk.customitems.CustomItems;

import org.bukkit.Bukkit;
import org.bukkit.Material;
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


public class RepairGUI {
    private CustomItems plugin;
    private String guiName = "§6§lReparieren";

    public RepairGUI(CustomItems plugin) {
        this.plugin = plugin;
        this.plugin.setGuiName(guiName);
        this.plugin.setRepairInventory(Bukkit.createInventory(null, 27, plugin.getGuiName()));
        glassFiller();
        itemFiller();
    }


    private void itemFiller() {
        Inventory repairInventory = this.plugin.getRepairInventory();
        ItemStack backItem = new ItemStack(Material.INK_SACK, 1, (short) 1);
        ItemMeta backMeta = backItem.getItemMeta();
        backMeta.setDisplayName("§c§lZurück");
        backItem.setItemMeta(backMeta);
        repairInventory.setItem(10, backItem);

        ItemStack repairItem = new ItemStack(Material.ANVIL);
        ItemMeta repairItemMeta = repairItem.getItemMeta();
        repairItemMeta.setDisplayName("§6§lReparieren");
        repairItem.setItemMeta(repairItemMeta);
        repairInventory.setItem(15, repairItem);
    }

    private void glassFiller() {
        Inventory repairInventory = this.plugin.getRepairInventory();
        //obere Reihe
        for (int i = 0; i < 9; i += 2) {
            repairInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15));
        }

        for (int i = 1; i < 8; i += 2) {
            repairInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7));
        }


        //mittlere Reihe
        for (int i = 9; i < 18; i += 2) {
            if (i == 13)
                continue;
            repairInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7));
        }

        for (int i = 8; i < 17; i += 2) {
            repairInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15));
        }


        //untere Reihe
        for (int i = 18; i < 27; i += 2) {
            repairInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15));
        }

        for (int i = 19; i < 26; i += 2) {
            repairInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7));
        }
    }

}
