package me.tweedjt.timedpermissions.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tweedjt.timedpermissions.PermFunctions;
import me.tweedjt.timedpermissions.util.Log;
import me.tweedjt.timedpermissions.util.Message;

public class PermCommandListener implements CommandExecutor {

	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		int iargs = 0;
		if (args != null) {
			if (args.length > 0) {
				StringBuilder fullargs = new StringBuilder();
				for (Object o : args)
				{
					iargs++;
					fullargs.append(o.toString());
					fullargs.append(" ");
				}
				Log.logToConsole("/tperm command run by " + sender.getName() + " with arguments: " + fullargs);
				
				if (iargs != 1) {
					// Either we have no parameters passed, or too many
					Message.toSender("Usage: /tperm give <playername> <permission> <seconds>", sender);
					return true;
				}

				Player recipient = null; // This is the player that will receive the pickaxe

				// It is technically possible you could run this command from console with no players on
				// so check it for null, then loop through
				if (Bukkit.getOnlinePlayers() != null) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						// Check if the display name matches
						if (ChatColor.stripColor(p.getDisplayName()).equalsIgnoreCase(args[0].toString())) {
							// The player exists, try and give the smelting pick
							recipient = p; // Set the recipient
							break;
						}
						// Also check if the player name matches
						if (ChatColor.stripColor(p.getName()).equalsIgnoreCase(args[0].toString())) {
							// The player exists, try and give the smelting pick
							recipient = p; // Set the recipient
							break;
						}
					}
					if (recipient != null) {
						if (sender.hasPermission("timedpermissions.give")) {
							if (PermFunctions.givePermsToPlayer(recipient)) {
								Message.toSender("&a" + recipient.getDisplayName() + " was given permission for", sender);
								return true;
							} else {
								Message.toSender("&cUnable to give " + recipient.getDisplayName() + " That Permission", sender);
								return true;
							}
						} else {
							Message.toSender("&cSorry, you do not have permission to run this command", sender);
							return true;
						}
					} else {
						Message.toSender("&cInvalid player.  Player must be online.  Usage: /perm give <playername> <permission> <seconds>", sender);
						return true;
					}
				} else {
					Message.toSender("&cNo players online.  Unable to give permission", sender);
					return true;
				}

				
			} else {
				// Either we have no parameters passed, or too many
				Message.toSender("Usage: /tperm give <playername> <permission> <seconds>", sender);
				return true;
			}
		} else {
			Message.toSender("Usage: /tperm give <playername> <permission> <seconds>", sender);
			return true;
		}
		
		

	}

}
