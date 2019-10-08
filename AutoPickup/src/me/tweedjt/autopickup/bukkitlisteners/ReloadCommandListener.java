package me.tweedjt.autopickup.bukkitlisteners;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tweedjt.autopickup.AutoPickup;
import me.tweedjt.autopickup.util.AutoPickupConfig;
import me.tweedjt.autopickup.util.Log;
import me.tweedjt.autopickup.util.Message;

public class ReloadCommandListener implements CommandExecutor {
	

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		try {
            if (sender != null) {
                // Check if a player or console ran the command
                if (sender instanceof Player) {
                   // Log.logToConsole("Sender is a player"); // Comment this out for release
                    // A player called it
                    Player player = (Player) sender;
                    // Check if the player has permission
                    if (!player.hasPermission("autopickup.reload")) {
                        Message.toPlayer("Sorry, you do not have permission to run that command", player);
                        return true;
                    }else{
                    	AutoPickup plugin = AutoPickup.getInstance();
                        plugin.reloadConfig();
                    	plugin.setAutoPickupConfig(new AutoPickupConfig(plugin));
                        Message.toPlayer("AutoPickup reloaded.", player);
                        Log.logToConsole("AutoPickup Reloaded"); 
                    }
                    return true;
                    
                    }else {
                    	Log.logToConsole("Sender is null");
                    	return false;

                }
            }
            return true;
    }
	catch (Exception e) {
		Log.logToConsole(e.getMessage());
		//Log.logToConsole("Catch Exception");
		return false;
	}
		
	}
}
