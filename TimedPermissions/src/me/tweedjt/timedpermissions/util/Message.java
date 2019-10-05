package me.tweedjt.timedpermissions.util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.tweedjt.timedpermissions.TimedPermissions;
import me.tweedjt.timedpermissions.util.Misc;

public class Message {
	// Send a message to a commandsender (player or console)
	public static void toSender(String message, CommandSender sender) {
        if (sender != null)
        {
        	sender.sendMessage(Misc.colorToString(TimedPermissions.getInstance().getTimedPermissionsConfig().getMessagePrefix() + "&r" + message));
        }
    }
	// Send a message to a player (no console)
    public static void toPlayer(String message, Player player) {
        if (player != null)
        {
        	player.sendMessage(Misc.colorToString(TimedPermissions.getInstance().getTimedPermissionsConfig().getMessagePrefix() + "&r" + message));
        } 
    }
}
