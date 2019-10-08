package me.tweedjt.autopickup;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import me.tweedjt.autopickup.AutoPickup;
import me.tweedjt.autopickup.bukkitlisteners.BlockListener;
import me.tweedjt.autopickup.bukkitlisteners.PlayerListener;
import me.tweedjt.autopickup.bukkitlisteners.ReloadCommandListener;
import me.tweedjt.autopickup.commands.APCommandListener;
import me.tweedjt.autopickup.util.AutoPickupConfig;
import me.tweedjt.autopickup.util.Log;

public class AutoPickup extends JavaPlugin {
	
	// This is the main class
	
	// Debug - set to true for debugging
	private final boolean debug = false;
	public boolean getDebug() {
		return debug;
	}
	
	// Create a HashSet of player unique id values, if the player is in this list, auto-smelt is on
	private HashSet<UUID> pickups = new HashSet<UUID>(); 
	
	
	// Create a check to see iff they have auto-smelt, and getters and setters
	public boolean hasPickup(UUID uuid) {
		if (pickups == null) {
			// If for some reason pickups is null, set it as a blank HashSet
			pickups = new HashSet<UUID>();
		}
		return pickups.contains(uuid);
	}
	public void putPickup(UUID uuid) {
		if (pickups == null) {
			// If for some reason pickups is null, set it as a blank HashSet
			pickups = new HashSet<UUID>();
		}
		pickups.add(uuid);
	}
	public void removePickup(UUID uuid) {
		if (pickups == null) {
			// If for some reason pickups is null, set it as a blank HashSet
			pickups = new HashSet<UUID>();
		}
		pickups.remove(uuid);
	}
	
    // Instance
	// Create an instance of the main class so we can call it from other classes 
    private static AutoPickup instance;
    public static AutoPickup getInstance() {
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
		
		instance = this;

		saveDefaultConfig();

		config = new AutoPickupConfig(this);

		if (hookWorldGuard()) {
			Log.logToConsole("WorldGuard functionality added");
		} else {
			Log.logToConsole("WorldGuard functionality NOT added");
		}
		
		Log.logToConsole("Starting AutoPickup");  
		
		instance = this; // Setting the instance to this
		
		// Add command listeners
		try {
	    	getCommand("ap").setExecutor(new APCommandListener()); // as
	    	getCommand("apreload").setExecutor(new ReloadCommandListener());
		} catch (Exception ex) {
			// If we have an error, output it
			Log.logToConsole("Unexpected Error - " + ex.getMessage());  
		}
		

		
		// Register event listeners
		
		// Block Event listener for smelting
		Bukkit.getPluginManager().registerEvents(new BlockListener(), this);
		// Player Event listener for when players join
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		
		
		
	}
	
	private AutoPickupConfig config;
    public AutoPickupConfig getAutoPickupConfig() {
        return this.config;
    }
    public void setAutoPickupConfig(AutoPickupConfig config) {
        this.config = config;
    }
	
	// On shutdown
	@Override
	public void onDisable() {
		Log.logToConsole("Disabling AutoPickup");
	}
	
}
