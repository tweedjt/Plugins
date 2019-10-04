package me.tweedjt.autosmelt;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import me.tweedjt.autosmelt.bukkitlisteners.BlockListener;
import me.tweedjt.autosmelt.bukkitlisteners.PlayerListener;
import me.tweedjt.autosmelt.commands.ASCommandListener;
import me.tweedjt.autosmelt.commands.SmeltCommandListener;
import me.tweedjt.autosmelt.util.AutoSmeltConfig;
import me.tweedjt.autosmelt.util.Log;
import me.tweedjt.autosmelt.util.Misc;


public class AutoSmelt extends JavaPlugin {
	
	// This is the main class
	
	// Debug - set to true for debugging
	private final boolean debug = false;
	public boolean getDebug() {
		return debug;
	}
	
	// Create a HashSet of player unique id values, if the player is in this list, auto-smelt is on
	private HashSet<UUID> smelters = new HashSet<UUID>(); 
	
	// This is the name of the smelting pick
	private final String smeltingPickName = "Smelting Pick";
	public String getSmeltingPickaxeName() {
		return smeltingPickName;
	}
	// This is the lore for the smelting pick - note that it uses SmeltFunctions.stringToLore,
	// this allows using | as a line break.  Also, note the use of &d - this is a color code
	// that we will convert to a color.  This is done through SmeltFunctions.colorToString,
	// but we don't need to call that here as SmeltFunctions.stringToLore calls it.
	private final List<String> smeltingLore = Misc.stringToLore("&dAutomatically smelts ores!|Fortune will increase yield") ;
	public List<String> getSmeltingPickAxeLore() {
		return smeltingLore;
	}
	// This is the smelting pick material
	private final Material smeltingPickAxeMaterial = Material.DIAMOND_PICKAXE;
	public Material getSmeltingPickAxeMaterial() {
		return smeltingPickAxeMaterial;
	}
	// This is the amount of damage to cause to the pick each use
	private final int smeltingDamage = 1;
	public int getSmeltingDamage() {
		return smeltingDamage;
	}
	
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
		
		instance = this;

		saveDefaultConfig();

		config = new AutoSmeltConfig(this);

		if (hookWorldGuard()) {
			Log.logToConsole("WorldGuard functionality added");
		} else {
			Log.logToConsole("WorldGuard functionality NOT added");
		}
		
		Log.logToConsole("Starting AutoSmelt");  
		
		instance = this; // Setting the instance to this
		
		// Add command listeners
		try {
	    	getCommand("as").setExecutor(new ASCommandListener()); // as
	    	getCommand("smelt").setExecutor(new SmeltCommandListener()); // smelt <playername>
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
	
	private AutoSmeltConfig config;
    public AutoSmeltConfig getAutoSmeltConfig() {
        return this.config;
    }
    public void setAutoSmeltConfig(AutoSmeltConfig config) {
        this.config = config;
    }
	
	// On shutdown
	@Override
	public void onDisable() {
		Log.logToConsole("Disabling AutoSmelt");
	}
}