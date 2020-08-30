package me.tweedjt.itemlore.commands;

import me.tweedjt.itemlore.ItemLore;
import me.tweedjt.itemlore.util.Log;
import me.tweedjt.itemlore.util.Message;
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
                    if (!player.hasPermission("itemlore.reload")) {
                        Message.toPlayer("Sorry, you do not have permission to run that command", player);
                        return true;
                    }else{
                        ItemLore plugin = ItemLore.getInstance();
                        plugin.reloadConfig();
                        plugin.getItemLoreConfig();
                        plugin.loadItemLoreConfig();
                        Message.toPlayer("ItemLore reloaded.", player);
                        Log.logToConsole("ItemLore Reloaded");
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
