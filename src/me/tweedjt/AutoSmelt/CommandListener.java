package me.tweedjt.AutoSmelt;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandListener implements CommandExecutor {
	
	// This class handles the /as command
	
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		//Util.logToConsole("/as command run"); // Comment this out for release
		
		// Put all the code in a try/catch block to avoid console errors
		try {
			if (sender != null) {
				// Check if a player or console ran the command
				if (sender instanceof Player) {
					//Util.logToConsole("Sender is a player"); // Comment this out for release
					// A player called it
					Player player = (Player) sender;
					
                    // Check if the player has permission
                    if (!player.hasPermission("autosmelt.mine")) {
                        Util.toPlayer("Sorry, you do not have permission to run that command", player);
                        return true;
                    }
					
					// Check if the player has auto-smelt turned on
					if (AutoSmelt.getInstance().hasSmelt(player.getUniqueId())) {
						//Util.logToConsole("Player has smelt on - turn it off"); // Comment this out for release
						// Player already has smelt on - turn it off
						AutoSmelt.getInstance().removeSmelt(player.getUniqueId());
						Util.toPlayer("AutoSmelt has been turned off", player);
					} else {
						//Util.logToConsole("Player has smelt off - turn it on"); // Comment this out for release
						// Player already has smelt on - turn it off
						AutoSmelt.getInstance().putSmelt(player.getUniqueId());
						Util.toPlayer("AutoSmelt has been turned on", player);
					}
				} else {
					// A non-player (console) called the command
					//Util.logToConsole("Command was run by console"); // Comment this out for release
					Util.toSender("This command cannot be called by console", sender);
				}
				// Return true 
				return true;
			} else {
				Util.logToConsole("Sender is null");
				return false;
			}
		} catch (Exception ex) {
			// Output the error and return false
			Util.logToConsole(ex.getMessage());
			return false;
		}
	}
}