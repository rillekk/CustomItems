package net.rillekk.customitems.shopgui;

import fr.minuskube.inv.SmartInventory;
import net.rillekk.customitems.CustomItems;
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
    private CustomItems plugin;
    public ShopGUI(CustomItems plugin) {
        this.plugin = plugin;
    }


    public final SmartInventory INVENTORY = SmartInventory.builder()
            .provider(new ShopGUIProvider(plugin))
            .size(5, 9)
            .title("ShopGUI")
            .build();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ShopGUIOpener shopGUIOpener = new ShopGUIOpener();
            INVENTORY.open(player);
        }

        return false;
    }
}
