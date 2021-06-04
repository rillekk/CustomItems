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
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

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


public class ShopGUIProvider implements InventoryProvider {
    private final CustomItems plugin;

    public ShopGUIProvider(CustomItems plugin) {
        this.plugin = plugin;
    }

    private final Random random = new Random();
    private final Economy economy = CustomItems.getEconomy();
    public Integer areaHoeCost = -10;

    @Override
    public void init(Player player, InventoryContents contents) {

        boolean economyCheck = plugin.setupEconomy();
        System.out.println("Das ist der ecoCheck: " + economyCheck);
        contents.fillBorders(ClickableItem.empty(new ItemStack(Material.STAINED_GLASS_PANE)));
        OfflinePlayer offlinePlayer = player;

        AreaHoe areaHoe = new AreaHoe(this.plugin);
        contents.set(1, 1, ClickableItem.of(areaHoe.getAreaHoe(),
                e -> {
                    EconomyResponse resp = this.economy.depositPlayer(offlinePlayer, areaHoeCost);
                    if (resp.transactionSuccess()) {
                        player.getInventory().addItem(areaHoe.getAreaHoe());
                    } else {
                        player.sendMessage(plugin.getPrefix() + "§d Du hast nicht genügend Geld dafür!");
                    }
                }));

        BedrockPickaxe bedrockPickaxe = new BedrockPickaxe(this.plugin);
        contents.set(1, 4, ClickableItem.of(bedrockPickaxe.getBedrockPickaxe(),
                e -> player.getInventory().addItem(bedrockPickaxe.getBedrockPickaxe())));


        CustomdropsPickaxe customdropsPickaxe = new CustomdropsPickaxe(this.plugin);
        contents.set(1, 7, ClickableItem.of(customdropsPickaxe.getCustomdrpsPickaxe(),
                e -> player.getInventory().addItem(customdropsPickaxe.getCustomdrpsPickaxe())));

        OrePickaxe3x3 orePickaxe3x3 = new OrePickaxe3x3(this.plugin);
        contents.set(3, 1, ClickableItem.of(orePickaxe3x3.getOrePickaxe3x3(),
                e -> player.getInventory().addItem(orePickaxe3x3.getOrePickaxe3x3())));

        SmeltPickaxe smeltPickaxe = new SmeltPickaxe(this.plugin);
        contents.set(3, 4, ClickableItem.of(smeltPickaxe.getSmeltPickaxe(),
                e -> player.getInventory().addItem(smeltPickaxe.getSmeltPickaxe())));


        TimberAxe timberAxe = new TimberAxe(this.plugin);
        contents.set(3, 7, ClickableItem.of(timberAxe.getTimberAxe(),
                e -> player.getInventory().addItem(timberAxe.getTimberAxe())));


        contents.set(4, 8, ClickableItem.of(new ItemStack(Material.BARRIER),
                e -> player.closeInventory()));
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
