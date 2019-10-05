package me.tweedjt.timedpermissions;

import me.tweedjt.timedpermissions.commands.ReloadCommandListener;

import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.tweedjt.timedpermissions.bukkitlisteners.PlayerListener;
import me.tweedjt.timedpermissions.commands.PermCommandListener;
import me.tweedjt.timedpermissions.util.TimedPermissionsConfig;
import me.tweedjt.timedpermissions.TimedPermissions;
import me.tweedjt.timedpermissions.util.Log;

public class TimedPermissions extends JavaPlugin {
	
	private final boolean debug = false;
	public boolean getDebug() {
		return debug;
	}
	// Create a HashSet of player unique id values, if the player is in this list, auto-smelt is on
	private HashSet<UUID> perms = new HashSet<UUID>(); 
	
	
	
	// Create a check to see iff they have auto-smelt, and getters and setters
	public boolean hasPerms(UUID uuid) {
		if (perms == null) {
			// If for some reason perms is null, set it as a blank HashSet
			perms = new HashSet<UUID>();
		}
		return perms.contains(uuid);
	}
	public void putPerms(UUID uuid) {
		if (perms == null) {
			// If for some reason perms is null, set it as a blank HashSet
			perms = new HashSet<UUID>();
		}
		perms.add(uuid);
	}
	public void removePerms(UUID uuid) {
		if (perms == null) {
			// If for some reason perms is null, set it as a blank HashSet
			perms = new HashSet<UUID>();
		}
		perms.remove(uuid);
	}
	
	
	
    private static TimedPermissions instance;
    public static TimedPermissions getInstance() {
        return instance;
    }
    

    @Override
	public void onEnable() {
		
		instance = this;

		saveDefaultConfig();

		config = new TimedPermissionsConfig(this);
		
		Log.logToConsole("Starting TimedPermissions");  
		
		instance = this; // Setting the instance to this
		
		// Add command listeners
		try {
	    	getCommand("tperm").setExecutor(new PermCommandListener()); // /perm give <Player> <Perm> <TIME(SEC)>
	    	getCommand("tpreload").setExecutor(new ReloadCommandListener());
		} catch (Exception ex) {
			// If we have an error, output it
			Log.logToConsole("Unexpected Error - " + ex.getMessage());  
		}
		

		
		// Register event listeners
		// Player Event listener for when players join
		Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
		
		
		
	}
	
	private TimedPermissionsConfig config;
    public TimedPermissionsConfig getTimedPermissionsConfig() {
        return this.config;
    }
    public void setTimedPermissionsConfig(TimedPermissionsConfig config) {
        this.config = config;
    }
	
	// On shutdown
	@Override
	public void onDisable() {
		Log.logToConsole("Disabling TimedPermissions");
	}
	
}