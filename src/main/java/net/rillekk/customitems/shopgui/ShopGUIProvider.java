package net.rillekk.customitems.shopgui;


import net.rillekk.customitems.CustomItems;
import net.rillekk.customitems.areahoe.AreaHoe;
import net.rillekk.customitems.bedrockpickaxe.BedrockPickaxe;
import net.rillekk.customitems.customdrops.CustomdropsPickaxe;
import net.rillekk.customitems.orepickaxe.OrePickaxe3x3;
import net.rillekk.customitems.smeltpickaxe.SmeltPickaxe;
import net.rillekk.customitems.timberaxe.TimberAxe;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
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


public class ShopGUIProvider implements InventoryProvider, Listener {
    private final CustomItems plugin;

    public ShopGUIProvider(CustomItems plugin) {
        this.plugin = plugin;
    }

    private final Random random = new Random();
    private final Economy economy = CustomItems.getEconomy();

    public Integer areaHoeCost = 10;
    public Integer bedrockPickaxeCost = 10;
    public Integer customdropsPickaxeCost = 10;
    public Integer smeltPickaxeCost = 10;
    public Integer orePickaxe3x3Cost = 10;
    public Integer timberAxeCost = 10;

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.fillBorders(ClickableItem.empty(new ItemStack(Material.STAINED_GLASS_PANE)));

        AreaHoe areaHoe = new AreaHoe(this.plugin);
        contents.set(3, 1, ClickableItem.of(areaHoe.getAreaHoe(),
                e -> {
                    EconomyResponse resp = this.economy.withdrawPlayer(player, areaHoeCost);
                    if (resp.transactionSuccess()) {
                        player.getInventory().addItem(areaHoe.getAreaHoe());
                    } else {
                        player.sendMessage(plugin.getPrefix() + "§dDu hast nicht genügend Geld dafür!");
                    }
                }));

        BedrockPickaxe bedrockPickaxe = new BedrockPickaxe(this.plugin);
        contents.set(3, 2, ClickableItem.of(bedrockPickaxe.getBedrockPickaxe(),
                e -> {
                    EconomyResponse resp = this.economy.withdrawPlayer(player, bedrockPickaxeCost);
                    if (resp.transactionSuccess()) {
                        player.getInventory().addItem(bedrockPickaxe.getBedrockPickaxe());
                    } else {
                        player.sendMessage(plugin.getPrefix() + "§dDu hast nicht genügend Geld dafür!");
                    }
                }));


        CustomdropsPickaxe customdropsPickaxe = new CustomdropsPickaxe(this.plugin);
        contents.set(3, 3, ClickableItem.of(customdropsPickaxe.getCustomdrpsPickaxe(),
                e -> {
                    EconomyResponse resp = this.economy.withdrawPlayer(player, customdropsPickaxeCost);
                    if (resp.transactionSuccess()) {
                        player.getInventory().addItem(customdropsPickaxe.getCustomdrpsPickaxe());
                    } else {
                        player.sendMessage(plugin.getPrefix() + "§dDu hast nicht genügend Geld dafür!");
                    }
                }));

        OrePickaxe3x3 orePickaxe3x3 = new OrePickaxe3x3(this.plugin);
        contents.set(3, 5, ClickableItem.of(orePickaxe3x3.getOrePickaxe3x3(),
                e -> {
                    EconomyResponse resp = this.economy.withdrawPlayer(player, orePickaxe3x3Cost);
                    if (resp.transactionSuccess()) {
                        player.getInventory().addItem(orePickaxe3x3.getOrePickaxe3x3());
                    } else {
                        player.sendMessage(plugin.getPrefix() + "§dDu hast nicht genügend Geld dafür!");
                    }
                }));

        SmeltPickaxe smeltPickaxe = new SmeltPickaxe(this.plugin);
        contents.set(3, 6, ClickableItem.of(smeltPickaxe.getSmeltPickaxe(),
                e -> {
                    EconomyResponse resp = this.economy.withdrawPlayer(player, smeltPickaxeCost);
                    if (resp.transactionSuccess()) {
                        player.getInventory().addItem(smeltPickaxe.getSmeltPickaxe());
                    } else {
                        player.sendMessage(plugin.getPrefix() + "§dDu hast nicht genügend Geld dafür!");
                    }
                }));


        TimberAxe timberAxe = new TimberAxe(this.plugin);
        contents.set(3, 7, ClickableItem.of(timberAxe.getTimberAxe(),
                e -> {
                    EconomyResponse resp = this.economy.withdrawPlayer(player, timberAxeCost);
                    if (resp.transactionSuccess()) {
                        player.getInventory().addItem(timberAxe.getTimberAxe());
                    } else {
                        player.sendMessage(plugin.getPrefix() + "§dDu hast nicht genügend Geld dafür!");
                    }
                }));


        ItemStack backItem = new ItemStack(Material.INK_SACK, 1, (short) 1);
        ItemMeta backMeta = backItem.getItemMeta();
        backMeta.setDisplayName("§c§lZurück");
        backItem.setItemMeta(backMeta);

        contents.set(1, 1, ClickableItem.of(new ItemStack(backItem),
                e -> player.closeInventory()));


        ItemStack repairItem = new ItemStack(Material.ANVIL);
        ItemMeta repairItemMeta = repairItem.getItemMeta();
        repairItemMeta.setDisplayName("§6§lReparieren");
        repairItem.setItemMeta(repairItemMeta);

        contents.set(1, 7, ClickableItem.of(new ItemStack(repairItem),
                e -> {
                    if (player.hasPermission("ttools.repair")) {
                        player.openInventory(plugin.getRepairInventory());
                    } else {
                        player.sendMessage(plugin.getPrefix() + "§dDazu hast du keine Berechtigung!");
                    }
                }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {
        int state = contents.property("state", 0);
        contents.setProperty("state", state + 1);

        if (state % 5 != 0)
            return;

        short durability = (short) random.nextInt(15);

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, durability);
        contents.fillBorders(ClickableItem.empty(glass));
    }
}
