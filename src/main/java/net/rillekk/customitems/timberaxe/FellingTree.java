package net.rillekk.customitems.timberaxe;


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
    private int i;

    public FellingTree(Location loc) {
        this.i = 0;
        checkTree(loc);
    }

    private void checkTree(Location location) {

        if(i >= 50) return;
        if(location.clone().add(0, 0, 0).getBlock().getType().equals(Material.LOG) || location.clone().add(0, 0, 0).getBlock().getType().equals(Material.LOG_2)) {
            location.getBlock().breakNaturally();
            i++;
        }

        if(location.clone().add(0, 1, 0).getBlock().getType().equals(Material.LOG) || location.clone().add(0, 1, 0).getBlock().getType().equals(Material.LOG_2)) {
            location.clone().add(0, 1, 0).getBlock().breakNaturally();
            i++;
            checkTree(location.clone().add(0, 1, 0));
        }

        if(location.clone().add(0, -1, 0).getBlock().getType().equals(Material.LOG) || location.clone().add(0, -1, 0).getBlock().getType().equals(Material.LOG_2)) {
            location.clone().add(0, -1, 0).getBlock().breakNaturally();
            i++;
            checkTree(location.clone().add(0, -1, 0));
        }

        if(location.clone().add(0, 0, 1).getBlock().getType().equals(Material.LOG) || location.clone().add(0, 0, 1).getBlock().getType().equals(Material.LOG_2)) {
            location.clone().add(0, 0, 1).getBlock().breakNaturally();
            i++;
            checkTree(location.clone().add(0, 0, 1));
        }

        if(location.clone().add(0, 0, -1).getBlock().getType().equals(Material.LOG) || location.clone().add(0, 0, -1).getBlock().getType().equals(Material.LOG_2)) {
            location.clone().add(0, 0, -1).getBlock().breakNaturally();
            i++;
            checkTree(location.clone().add(0, 0, -1));
        }

        if(location.clone().add(1, 0, 0).getBlock().getType().equals(Material.LOG) || location.clone().add(1, 0, 0).getBlock().getType().equals(Material.LOG_2)) {
            location.clone().add(1, 0, 0).getBlock().breakNaturally();
            i++;
            checkTree(location.clone().add(1, 0, 0));
        }

        if(location.clone().add(-1, 0, 0).getBlock().getType().equals(Material.LOG) || location.clone().add(-1, 0, 0).getBlock().getType().equals(Material.LOG_2)) {
            location.clone().add(-1, 0, 0).getBlock().breakNaturally();
            i++;
            checkTree(location.clone().add(-1, 0, 0));
        }

        if(location.clone().add(0, 1, 1).getBlock().getType().equals(Material.LOG) || location.clone().add(0, 1, 1).getBlock().getType().equals(Material.LOG_2)) {
            location.clone().add(0, 1, 1).getBlock().breakNaturally();
            i++;
            checkTree(location.clone().add(0, 1, 1));
        }

        if(location.clone().add(0, 1, -1).getBlock().getType().equals(Material.LOG) || location.clone().add(0, 1, -1).getBlock().getType().equals(Material.LOG_2)) {
            location.clone().add(0, 1, -1).getBlock().breakNaturally();
            i++;
            checkTree(location.clone().add(0, 1, -1));
        }

        if(location.clone().add(1, 1, 0).getBlock().getType().equals(Material.LOG) || location.clone().add(1, 1, 0).getBlock().getType().equals(Material.LOG_2)) {
            location.clone().add(1, 1, 0).getBlock().breakNaturally();
            i++;
            checkTree(location.clone().add(1, 1, 0));
        }

        if(location.clone().add(-1, 1, 0).getBlock().getType().equals(Material.LOG) || location.clone().add(-1, 1, 0).getBlock().getType().equals(Material.LOG_2)) {
            location.clone().add(-1, 1, 0).getBlock().breakNaturally();
            i++;
            checkTree(location.clone().add(-1, 1, 0));
        }
    }
}
