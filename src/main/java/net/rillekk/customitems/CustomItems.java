package net.rillekk.customitems;


import lombok.Getter;
import lombok.Setter;
import net.milkbowl.vault.economy.Economy;
import net.rillekk.customitems.areahoe.AreaHoe;
import net.rillekk.customitems.customdrops.CustomdropsPickaxe;
import net.rillekk.customitems.orepickaxe.OrePickaxe3x3;
import net.rillekk.customitems.shopgui.ShopGUI;
import net.rillekk.customitems.smeltpickaxe.SmeltPickaxe;
import net.rillekk.customitems.utils.BlockfaceCheck;
import net.rillekk.customitems.bedrockpickaxe.BedrockPickaxe;
import net.rillekk.customitems.timberaxe.TimberAxe;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

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

@Getter
@Setter
public class CustomItems extends JavaPlugin {

    private static CustomItems INSTANCE;

    private final Logger log = Logger.getLogger("Minecraft");
    private String prefix;
    private BlockfaceCheck blockfaceCheck;
    private ExecutorService cachedThreadExecutor;
    public static Economy economy = null;

    @Override
    public void onEnable() {

        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        INSTANCE = this;
        this.prefix = "§eToastItems§e| ";
        this.blockfaceCheck = new BlockfaceCheck(this);
        this.cachedThreadExecutor = Executors.newCachedThreadPool();


        super.getCommand("axt").setExecutor(new BedrockPickaxe(this));
        super.getCommand("shopgui").setExecutor(new ShopGUI(this));

        Bukkit.getPluginManager().registerEvents(new TimberAxe(this), this);
        Bukkit.getPluginManager().registerEvents(new BedrockPickaxe(this), this);
        Bukkit.getPluginManager().registerEvents(new SmeltPickaxe(this), this);
        Bukkit.getPluginManager().registerEvents(new OrePickaxe3x3(this), this);
        Bukkit.getPluginManager().registerEvents(new CustomdropsPickaxe(this), this);
        Bukkit.getPluginManager().registerEvents(new AreaHoe(this), this);
        Bukkit.getPluginManager().registerEvents(new BlockfaceCheck(this), this);
    }

    public boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public static Economy getEconomy() {
        return economy;
    }
}
