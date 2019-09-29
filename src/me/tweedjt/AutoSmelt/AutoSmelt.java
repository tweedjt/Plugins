package me.tweedjt.AutoSmelt;

import java.util.HashSet;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;


public class AutoSmelt extends JavaPlugin {
	
	// This is the main class
	
	// Create a HashSet of player unique id values, if the player is in this list, auto-smelt is on
	private HashSet<UUID> smelters = new HashSet<UUID>(); 
	
	// Create a check to see iff they have auto-smelt, and getters and setters
	public boolean hasSmelt(UUID uuid) {
		if (smelters == null) {
			// If for some reason smelters is null, set it as a blank HashSet
			smelters = new HashSet<UUID>();
		}
		return smelters.contains(uuid);
	}
	public void putSmelt(UUID uuid) {
		if (smelters == null) {
			// If for some reason smelters is null, set it as a blank HashSet
			smelters = new HashSet<UUID>();
		}
		smelters.add(uuid);
	}
	public void removeSmelt(UUID uuid) {
		if (smelters == null) {
			// If for some reason smelters is null, set it as a blank HashSet
			smelters = new HashSet<UUID>();
		}
		smelters.remove(uuid);
	}
	
    // Instance
	// Create an instance of the main class so we can call it from other classes 
    private static AutoSmelt instance;
    public static AutoSmelt getInstance() {
        return instance;
    }
    
    
    private WorldGuardPlugin wg;
    private WorldEditPlugin we;
    private boolean hasWorldGuard = false;
    public boolean hookWorldGuard()
    {
    	Plugin wgPlug = getServer().getPluginManager().getPlugin("WorldGuard");
    	Plugin wePlug = getServer().getPluginManager().getPlugin("WorldEdit");
    	
        if (wgPlug != null && wePlug != null)
        {
            wg = ((WorldGuardPlugin) wgPlug);
            we = ((WorldEditPlugin) wePlug);
            
            hasWorldGuard = true;
            return true;
        } else {
        	return false;
        }
    }
    public boolean hasWorldGuard() {
    	return hasWorldGuard;
    }
    
    
    public WorldGuardPlugin getWorldGuard() {
    	return wg;
    }
    public WorldEditPlugin getWorldEdit() {
    	return we;
    }
    
    // On startup 
	@Override
	public void onEnable() {

		if (hookWorldGuard()) {
			Util.logToConsole("WorldGuard functionality added");
		} else {
			Util.logToConsole("WorldGuard functionality NOT added");
		}
		
		Util.logToConsole("Starting AutoSmelt");  
		
		instance = this; // Setting the instance to this
		
		// Add command listener for /as
		try {
	    	getCommand("as").setExecutor(new CommandListener());
		} catch (Exception ex) {
			// If we have an error, output it
			Util.logToConsole("Unexpected Error - " + ex.getMessage());  
		}
		
		
		// Register event listeners
		
		// Block Event listener for smelting
		Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
		// Player Event listener for when players join
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		
	}
	
	// On shutdown
	@Override
	public void onDisable() {
		Util.logToConsole("Disabling AutoSmelt");
	}
}