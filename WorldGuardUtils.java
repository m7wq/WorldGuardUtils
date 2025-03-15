package dev.m7wq.rspvp.utils;


import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;


import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;

import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import lombok.experimental.UtilityClass;

import java.util.Map;


@UtilityClass
public class WorldGuardUtils {

    public static boolean isInRegion(Player player, String regionName){
        return isInRegion(player.getLocation(), regionName);
    }


    public static boolean isInRegion(Location loc, String regionName) {


  
        World world = loc.getWorld();

        
        
        RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(
            // Adapt bukkit world to worldguard world
            BukkitAdapter.adapt(world)
        );

   
        if (regionManager == null) {
            return false;
        }


        // get all the regions
        Map<String, ProtectedRegion> regions = regionManager.getRegions();

        // for each region -> check if the region contains the location cords
                        //  -> then check for name match
        for (ProtectedRegion region : regions.values()) {

            if (region.contains(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ())) {

                if (region.getId().equalsIgnoreCase(regionName)) {

                    return true;
                }
            }
        }
        return false;
    }
}
