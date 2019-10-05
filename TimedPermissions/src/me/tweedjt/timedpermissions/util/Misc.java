package me.tweedjt.timedpermissions.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

public class Misc {
	
	
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
	}
