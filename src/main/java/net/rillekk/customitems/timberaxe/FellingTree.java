package net.rillekk.customitems.timberaxe;


import net.rillekk.customitems.CustomItems;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;

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


public class FellingTree {
    private final CustomItems plugin;

    private int i;

    public FellingTree(CustomItems plugin, Location loc) throws InterruptedException {
        this.plugin = plugin;
        this.i = 0;
        checkTree(loc);
    }

    private void checkTree(Location location) throws InterruptedException {

        if(i >= 50) return;
        Bukkit.getScheduler().runTaskLater(this.plugin, () -> {
            if(location.clone().add(0, 0, 0).getBlock().getType().equals(Material.LOG) || location.clone().add(0, 0, 0).getBlock().getType().equals(Material.LOG_2)) {
                location.getBlock().breakNaturally();
                i++;
            }
        }, 5L);


        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(location.clone().add(0, 1, 0).getBlock().getType().equals(Material.LOG) || location.clone().add(0, 1, 0).getBlock().getType().equals(Material.LOG_2)) {
                location.clone().add(0, 1, 0).getBlock().breakNaturally();
                i++;
                try {
                    checkTree(location.clone().add(0, 1, 0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 5L);


        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(location.clone().add(0, -1, 0).getBlock().getType().equals(Material.LOG) || location.clone().add(0, -1, 0).getBlock().getType().equals(Material.LOG_2)) {
                location.clone().add(0, -1, 0).getBlock().breakNaturally();
                i++;
                try {
                    checkTree(location.clone().add(0, -1, 0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 5L);


        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(location.clone().add(0, 0, 1).getBlock().getType().equals(Material.LOG) || location.clone().add(0, 0, 1).getBlock().getType().equals(Material.LOG_2)) {
                location.clone().add(0, 0, 1).getBlock().breakNaturally();
                i++;
                try {
                    checkTree(location.clone().add(0, 0, 1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 5L);


        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(location.clone().add(0, 0, -1).getBlock().getType().equals(Material.LOG) || location.clone().add(0, 0, -1).getBlock().getType().equals(Material.LOG_2)) {
                location.clone().add(0, 0, -1).getBlock().breakNaturally();
                i++;
                try {
                    checkTree(location.clone().add(0, 0, -1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 5L);


        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(location.clone().add(1, 0, 0).getBlock().getType().equals(Material.LOG) || location.clone().add(1, 0, 0).getBlock().getType().equals(Material.LOG_2)) {
                location.clone().add(1, 0, 0).getBlock().breakNaturally();
                i++;
                try {
                    checkTree(location.clone().add(1, 0, 0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 5L);


        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(location.clone().add(-1, 0, 0).getBlock().getType().equals(Material.LOG) || location.clone().add(-1, 0, 0).getBlock().getType().equals(Material.LOG_2)) {
                location.clone().add(-1, 0, 0).getBlock().breakNaturally();
                i++;
                try {
                    checkTree(location.clone().add(-1, 0, 0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 5L);


        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(location.clone().add(0, 1, 1).getBlock().getType().equals(Material.LOG) || location.clone().add(0, 1, 1).getBlock().getType().equals(Material.LOG_2)) {
                location.clone().add(0, 1, 1).getBlock().breakNaturally();
                i++;
                try {
                    checkTree(location.clone().add(0, 1, 1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 5L);


        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(location.clone().add(0, 1, -1).getBlock().getType().equals(Material.LOG) || location.clone().add(0, 1, -1).getBlock().getType().equals(Material.LOG_2)) {
                location.clone().add(0, 1, -1).getBlock().breakNaturally();
                i++;
                try {
                    checkTree(location.clone().add(0, 1, -1));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 5L);


        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(location.clone().add(1, 1, 0).getBlock().getType().equals(Material.LOG) || location.clone().add(1, 1, 0).getBlock().getType().equals(Material.LOG_2)) {
                location.clone().add(1, 1, 0).getBlock().breakNaturally();
                i++;
                try {
                    checkTree(location.clone().add(1, 1, 0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 5L);


        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if(location.clone().add(-1, 1, 0).getBlock().getType().equals(Material.LOG) || location.clone().add(-1, 1, 0).getBlock().getType().equals(Material.LOG_2)) {
                location.clone().add(-1, 1, 0).getBlock().breakNaturally();
                i++;
                try {
                    checkTree(location.clone().add(-1, 1, 0));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 5L);
    }
}
