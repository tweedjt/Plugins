package me.tweedjt.autosmelt.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import me.tweedjt.autosmelt.AutoSmelt;

public class Misc {
	public static boolean worldGuardPreventBreakAtLocation(Block block, Player player) {
		if (AutoSmelt.getInstance().hasWorldGuard()) {
			org.bukkit.Location location = block.getLocation(); // Get the block location
			RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer(); // Get the region container
			RegionQuery query = container.createQuery(); // Create a  query from the RegionContainer
			com.sk89q.worldedit.util.Location loc = BukkitAdapter.adapt(location); // Convert a location to a worldedit location
			LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player); // Create a LocalPlayer from player
			State a = query.queryState(loc, localPlayer, Flags.BLOCK_BREAK); // Get the BLOCK_BREAK state for location and player
			if (a != null) {
				if (a.equals(StateFlag.State.DENY)) {
					// Stop!
					Log.logToConsole("WorldGuard preventing breaking of a block");
					return true;
				}
			}
		}
		return false;
	}
    public static String colorToString(String input) {
    	// &0 = black, &1 = dark blue, &2 = dark green, &3 = dark aqua, &4 = dark red, &5 = dark purple
    	// &6 = gold, &7 = gray, &8 = dark gray, &9 = blue, &a = green, &b = aqua, &c = red
    	// &d = light purple, &e = yellow, &f = white, &k = magic, &l = bold, &m = strikethrough
    	// &n = underline, &o = italic, &r = reset
    	String returnValue = ChatColor.translateAlternateColorCodes('&', input);
        return returnValue;
    }
    public static List<String> stringToLore(String loreString) {
    	// Create a new list
    	List<String> newLore = new ArrayList<String>();
    	// Check if the string is not blank
    	if (!loreString.equals("")) {
    		// Split the string on |
	        String[] new_lore = loreString.split("\\|");
	        // Loop through the list, adding to our return list
	        for (String s : new_lore)
	        {
	            newLore.add(colorToString(s));
	        }
    	} 
        return newLore;
    }
    public static OfflinePlayer offlinePlayerFromUUID(UUID uuid)
    {
    	// Returns the OfflinePlayer from a UUID
        OfflinePlayer offlinePlayer = null;
        offlinePlayer = Bukkit.getOfflinePlayer(uuid);
        return offlinePlayer;
    }
	public static int firstEmptySlot(UUID uuid)
    {
		// Gets the first empty inventory slot from an OfflinePlayer
        int slot = -1;
        OfflinePlayer offlinePlayer = offlinePlayerFromUUID(uuid);
        if (offlinePlayer != null) {
            if (offlinePlayer.getPlayer() != null) {
                if (offlinePlayer.getPlayer().getInventory().firstEmpty() >= 0) {
                    slot = offlinePlayer.getPlayer().getInventory().firstEmpty();
                }
            }
        }
        return slot;
    }
    public static String itemToStringBlob(ItemStack itemStack) {
    	// Converts an ItemStack to a string blob
        YamlConfiguration config = new YamlConfiguration();
        config.set("i", itemStack);
        return config.saveToString();
    }
    public static boolean xpDrops(Material drop, World w, Location loc) {
    	
    	
    	
    	if (AutoSmelt.getInstance().getAutoSmeltConfig().getExpDrops()) {
    		
    		if (drop.equals(Material.IRON_INGOT)) { 
    			
    		w.spawn(loc, ExperienceOrb.class).setExperience(AutoSmelt.getInstance().getAutoSmeltConfig().getIronExp());
    		
    		} if (drop.equals(Material.GOLD_INGOT)) {
    			
    			w.spawn(loc, ExperienceOrb.class).setExperience(AutoSmelt.getInstance().getAutoSmeltConfig().getGoldExp());
    		}
    		if (drop.equals(Material.NETHERITE_SCRAP)) {
    			w.spawn(loc, ExperienceOrb.class).setExperience(AutoSmelt.getInstance().getAutoSmeltConfig().getNetherScrapExp());
    		}
    	}
    	else {
    		return false;
    	}
    	return true;
    	
    	
    }
}