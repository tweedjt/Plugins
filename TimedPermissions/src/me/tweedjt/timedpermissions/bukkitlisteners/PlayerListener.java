package me.tweedjt.timedpermissions.bukkitlisteners;

	import org.bukkit.event.EventHandler;
	import org.bukkit.event.Listener;
	import org.bukkit.event.player.PlayerJoinEvent;

	import me.tweedjt.timedpermissions.TimedPermissions;
	import me.tweedjt.timedpermissions.util.Log;


	public class PlayerListener implements Listener {
	    @EventHandler
	    public void onPlayerJoin(PlayerJoinEvent event) {
	        // This event is called when a player joins the server
	        
	        if (event.getPlayer() != null) {
	            // Check if the player has auto-smelt auto-on
	            if (TimedPermissions.getInstance().getTimedPermissionsConfig().getTimedPermissions()) {
	                // Auto-On is on, check if the player is already in the list, if not, add them
	                if (!TimedPermissions.getInstance().hasPerms(event.getPlayer().getUniqueId())) {
	                    TimedPermissions.getInstance().putPerms(event.getPlayer().getUniqueId());
	                }
	            } else {
	                // Check if the player has auto-smelt on
	                if (TimedPermissions.getInstance().hasPerms(event.getPlayer().getUniqueId())) {
	                    // Player already has auto-smelt (connect/disconnect?) - turn off
	                    TimedPermissions.getInstance().removePerms(event.getPlayer().getUniqueId());
	                }            
	            }
	        } else {
	            Log.logToConsole("Player is null");
	        }
	    }
	}
