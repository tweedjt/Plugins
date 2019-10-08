package me.tweedjt.autopickup.bukkitlisteners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.tweedjt.autopickup.AutoPickup;
import me.tweedjt.autopickup.util.Log;

public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // This event is called when a player joins the server
        
        if (event.getPlayer() != null) {
            // Check if the player has auto-smelt auto-on
            if (AutoPickup.getInstance().getAutoPickupConfig().getAutoPickup()) {
                // Auto-On is on, check if the player is already in the list, if not, add them
                if (!AutoPickup.getInstance().hasPickup(event.getPlayer().getUniqueId())) {
                    AutoPickup.getInstance().putPickup(event.getPlayer().getUniqueId());
                }
            } else {
                // Check if the player has auto-smelt on
                if (AutoPickup.getInstance().hasPickup(event.getPlayer().getUniqueId())) {
                    // Player already has auto-smelt (connect/disconnect?) - turn off
                    AutoPickup.getInstance().removePickup(event.getPlayer().getUniqueId());
                }            
            }
        } else {
            Log.logToConsole("Player is null");
        }
    }
}
