package work.torp.loreset.alerts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import work.torp.loreset.Main;

public class Alert {
	public static void Sender(String message, CommandSender sender, boolean includeHeader) {
		String header = "";
		if (includeHeader)
		{
			header = ChatColor.BLUE + "[LoreSet] ";
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
			header = ChatColor.BLUE + "[LoreSet] ";
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
		ConsoleCommandSender clogger = Main.getInstance().getServer().getConsoleSender();
		clogger.sendMessage(ChatColor.DARK_PURPLE + "[LoreSet]" + ChatColor.GOLD + "[" + function + "] " + ChatColor.WHITE + message);
	}
	public static void VerboseLog(String function, String message)
	{
		String vlog = Main.getInstance().getConfig().getString("verbose_logging"); // Get the value assigned to verbose logging
		if (vlog != null)
		{
			if (vlog.toLowerCase() == "true") {
				ConsoleCommandSender clogger = Main.getInstance().getServer().getConsoleSender();
				clogger.sendMessage(ChatColor.DARK_RED + "[LoreSet.Verbose]" + ChatColor.GOLD + "[" + function + "] " + ChatColor.WHITE + message);
			}	
		}
	}
}
