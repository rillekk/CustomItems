package net.rillekk.customitems;


import net.rillekk.customitems.areahoe.AreaHoe;
import net.rillekk.customitems.customdrops.CustomdropsPickaxe;
import net.rillekk.customitems.orepickaxe.OrePickaxe3x3;
import net.rillekk.customitems.repairgui.RepairGUI;
import net.rillekk.customitems.repairgui.RepairGUIListener;
import net.rillekk.customitems.shopgui.ShopGUI;
import net.rillekk.customitems.shopgui.ShopGUIProvider;
import net.rillekk.customitems.smeltpickaxe.SmeltPickaxe;
import net.rillekk.customitems.utils.BlockfaceCheck;
import net.rillekk.customitems.bedrockpickaxe.BedrockPickaxe;
import net.rillekk.customitems.timberaxe.TimberAxe;

import fr.minuskube.inv.SmartInventory;

import net.milkbowl.vault.economy.Economy;

import lombok.Getter;
import lombok.Setter;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.Inventory;
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
    private RepairGUI repairGUI;
    public String guiName;
    public Inventory repairInventory;
    public SmartInventory shopInventory;

    @Override
    public void onEnable() {
        INSTANCE = this;

        getConfig().addDefault("Prefix", "§6§lTTools| ");
        getConfig().addDefault("CustomdropsChance", 10);
        getConfig().addDefault("areaHoeCost", 100);
        getConfig().addDefault("bedrockPickaxeCost", 100);
        getConfig().addDefault("customdropsPickaxeCost", 100);
        getConfig().addDefault("smeltPickaxeCost", 100);
        getConfig().addDefault("orePickaxe3x3Cost", 100);
        getConfig().addDefault("timberAxeCost", 100);
        getConfig().options().copyDefaults(true);
        saveConfig();

        if (!setupEconomy()) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        this.prefix = getConfig().getString("Prefix");
        this.blockfaceCheck = new BlockfaceCheck(this);
        this.cachedThreadExecutor = Executors.newCachedThreadPool();
        this.repairGUI = new RepairGUI(this);


        super.getCommand("shopgui").setExecutor(new ShopGUI(this));

        Bukkit.getPluginManager().registerEvents(new AreaHoe(this), this);
        Bukkit.getPluginManager().registerEvents(new BedrockPickaxe(this), this);
        Bukkit.getPluginManager().registerEvents(new CustomdropsPickaxe(this), this);
        Bukkit.getPluginManager().registerEvents(new OrePickaxe3x3(this), this);
        Bukkit.getPluginManager().registerEvents(new SmeltPickaxe(this), this);
        Bukkit.getPluginManager().registerEvents(new TimberAxe(this), this);
        Bukkit.getPluginManager().registerEvents(new BlockfaceCheck(this), this);
        Bukkit.getPluginManager().registerEvents(new RepairGUIListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ShopGUIProvider(this), this);

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
