package me.tweedjt.reputation.commands;

import me.tweedjt.reputation.Reputation;
import me.tweedjt.reputation.util.Log;
import me.tweedjt.reputation.util.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                    if (!player.hasPermission("reputation.reload")) {
                        Message.toPlayer("Sorry, you do not have permission to run that command", player);
                        return true;
                    }else{
                        Reputation plugin = Reputation.getInstance();
                        plugin.reloadConfig();
                        plugin.getReputationConfig();
                        plugin.loadReputationConfig();
                        Message.toPlayer("Reputation reloaded.", player);
                        Log.logToConsole("Reputation Reloaded");
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
