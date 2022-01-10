package me.tweedjt.autosmelt.bukkitlisteners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.tweedjt.autosmelt.AutoSmelt;
import me.tweedjt.autosmelt.util.Log;


public class PlayerListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // This event is called when a player joins the server

        if (event.getPlayer() != null) {
            // Check if the player has auto-smelt auto-on
            if (AutoSmelt.getInstance().getAutoSmeltConfig().getAutoSmelt()) {
                // Auto-On is on, check if the player is already in the list, if not, add them
                if (!AutoSmelt.getInstance().hasSmelt(event.getPlayer().getUniqueId())) {
                    AutoSmelt.getInstance().putSmelt(event.getPlayer().getUniqueId());
                }
            } else {
                // Check if the player has auto-smelt on
                if (AutoSmelt.getInstance().hasSmelt(event.getPlayer().getUniqueId())) {
                    // Player already has auto-smelt (connect/disconnect?) - turn off
                    AutoSmelt.getInstance().removeSmelt(event.getPlayer().getUniqueId());
                }
            }
        } else {
            Log.logToConsole("Player is null");
        }
    }
}
