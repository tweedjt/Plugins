package me.tweedjt.goodbyemobs.util;

import me.tweedjt.goodbyemobs.GoodbyeMobs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Alert {
    public static void Sender(String message, CommandSender sender, boolean includeHeader) {
        String header = "";
        if (includeHeader)
        {
            header = ChatColor.BLUE + "[GoodByeMobs] ";
        }
        if (sender != null)
        {
            if (message != null) {
                sender.sendMessage(header + ChatColor.WHITE + message);
            }
        }
    }
    public static void Player(String message, Player player, boolean includeHeader) {
        String header = "";
        if (includeHeader)
        {
            header = ChatColor.BLUE + "[GoodByeMobs] ";
        }
        if (player != null)
        {
            Player p = Bukkit.getPlayer(player.getName());
            if (p != null) {
                if (message != null) {
                    player.sendMessage(header + ChatColor.WHITE + message);
                }
            }
        }
    }
    public static void Log(String function, String message)
    {
        ConsoleCommandSender clogger = GoodbyeMobs.getInstance().getServer().getConsoleSender();
        clogger.sendMessage(ChatColor.DARK_PURPLE + "[GoodByeMobs]" + ChatColor.GOLD + "[" + function + "] " + ChatColor.WHITE + message);
    }
    public static void Bypass(Player player, String message)
    {
        ConsoleCommandSender clogger = GoodbyeMobs.getInstance().getServer().getConsoleSender();
        clogger.sendMessage(ChatColor.DARK_PURPLE + "[GoodByeMobs]" + ChatColor.RED + "[BYPASS]" + ChatColor.AQUA + "[" + ChatColor.stripColor(player.getDisplayName()) + "] " + ChatColor.WHITE + message);
    }
    public static void DebugLog(String function, String subfunction, String message)
    {
        if (GoodbyeMobs.getInstance().getConfig().contains("debug_logging")) {
            if (GoodbyeMobs.getInstance().getConfig().contains("debug_logging")) {
                if (GoodbyeMobs.getInstance().getConfig().getBoolean("debug_logging")) {
                    ConsoleCommandSender clogger = GoodbyeMobs.getInstance().getServer().getConsoleSender();
                    clogger.sendMessage(ChatColor.LIGHT_PURPLE + "[GoodByeMobs.Debug]" + ChatColor.AQUA + "[" + function + "." + subfunction + "] " + ChatColor.WHITE + message);
                }
            }
        }
    }

}
