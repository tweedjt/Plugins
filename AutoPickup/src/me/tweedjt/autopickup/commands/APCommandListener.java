package me.tweedjt.autopickup.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tweedjt.autopickup.AutoPickup;
import me.tweedjt.autopickup.util.Log;
import me.tweedjt.autopickup.util.Message;

public class APCommandListener implements CommandExecutor {

	// This class handles the /as command

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		//Log.logToConsole("/as command run"); // Comment this out for release

		// Put all the code in a try/catch block to avoid console errors
		try {
			if (sender != null) {
				// Check if a player or console ran the command
				if (sender instanceof Player) {
					//Log.logToConsole("Sender is a player"); // Comment this out for release
					// A player called it
					Player player = (Player) sender;

					// Check if the player has permission
					if (!player.hasPermission("autopickup.pickup")) {
						Message.toPlayer("Sorry, you do not have permission to run that command", player);
						return true;
					}
					// Check if the player has auto-smelt turned on
					if (AutoPickup.getInstance().hasPickup(player.getUniqueId())) {
						//Log.logToConsole("Player has smelt on - turn it off"); // Comment this out for release
						// Player already has smelt on - turn it off
						AutoPickup.getInstance().removePickup(player.getUniqueId());
						Message.toPlayer(AutoPickup.getInstance().getAutoPickupConfig().getAutoPickupOnMessage(), player);
					} else {
						//Log.logToConsole("Player has smelt off - turn it on"); // Comment this out for release
						// Player already has smelt on - turn it off
						AutoPickup.getInstance().putPickup(player.getUniqueId());
						Message.toPlayer(AutoPickup.getInstance().getAutoPickupConfig().getAutoPickupOffMessage(), player);
					}
				} else {
					// A non-player (console) called the command
					//Log.logToConsole("Command was run by console"); // Comment this out for release
					Message.toSender("This command cannot be called by console", sender);
				}
				// Return true
				return true;
			} else {
				Log.logToConsole("Sender is null");
				return false;
			}
		} catch (Exception ex) {
			// Output the error and return false
			Log.logToConsole(ex.getMessage());
			return false;
		}
	}
}