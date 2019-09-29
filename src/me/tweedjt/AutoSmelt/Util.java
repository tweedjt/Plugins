package me.tweedjt.AutoSmelt;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Util {
	
	// This is a utility class - put reusable functions in here
	
	// Send a message to a commandsender (player or console)
	public static void toSender(String message, CommandSender sender) {
        if (sender != null)
        {
        	sender.sendMessage(ChatColor.LIGHT_PURPLE + "[AutoSmelt] " + ChatColor.WHITE + message);
        }
    }
	// Send a message to a player (no console)
    public static void toPlayer(String message, Player player) {
        if (player != null)
        {
        	player.sendMessage(ChatColor.LIGHT_PURPLE + "[AutoSmelt] " + ChatColor.WHITE + message);
        } 
    }
    // Send a message to the console
    public static void logToConsole(String message) {
    	System.out.println(ChatColor.AQUA + "[AutoSmelt Log] " + ChatColor.WHITE + message);  
    }
}