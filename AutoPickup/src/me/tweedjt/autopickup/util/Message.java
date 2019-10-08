package me.tweedjt.autopickup.util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tweedjt.autopickup.AutoPickup;
import me.tweedjt.autopickup.util.Misc;

public class Message {
	// Send a message to a commandsender (player or console)
	public static void toSender(String message, CommandSender sender) {
        if (sender != null)
        {
        	sender.sendMessage(Misc.colorToString(AutoPickup.getInstance().getAutoPickupConfig().getMessagePrefix() + "&r" + message));
        }
    }
	// Send a message to a player (no console)
    public static void toPlayer(String message, Player player) {
        if (player != null)
        {
        	player.sendMessage(Misc.colorToString(AutoPickup.getInstance().getAutoPickupConfig().getMessagePrefix() + "&r" + message));
        } 
    }
}