package net.rillekk.customitems.shopgui;


import net.rillekk.customitems.CustomItems;

import fr.minuskube.inv.SmartInventory;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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


public class ShopGUI implements CommandExecutor {
    private final CustomItems plugin;
    private final String guiName = "§6§lToastTools";

    public ShopGUI(CustomItems plugin) {
        this.plugin = plugin;
        plugin.setShopInventory(SmartInventory.builder()
                .provider(new ShopGUIProvider(this.plugin))
                .size(5, 9)
                .title(guiName)
                .build());
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("ttools.use")) {
                plugin.getShopInventory().open(player);
            } else {
                player.sendMessage(plugin.getPrefix() + "§dDazu hast du keine Berechtigung!");
            }
        }

        return false;
    }
}
