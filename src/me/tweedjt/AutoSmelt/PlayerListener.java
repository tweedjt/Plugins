package me.tweedjt.AutoSmelt;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		// This event is called when a player joins the server
		
		if (event.getPlayer() != null) {
			// Check if the player has auto-smelt on
			if (AutoSmelt.getInstance().hasSmelt(event.getPlayer().getUniqueId())) {
				// Player already has auto-smelt (connect/disconnect?) - turn off
				AutoSmelt.getInstance().removeSmelt(event.getPlayer().getUniqueId());
			}
		} else {
			Util.logToConsole("Player is null");
		}
	}
}