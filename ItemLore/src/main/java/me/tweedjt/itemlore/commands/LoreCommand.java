package me.tweedjt.itemlore.commands;

import me.tweedjt.itemlore.functions.LoreFunctions;
import me.tweedjt.itemlore.util.Log;
import me.tweedjt.itemlore.util.Message;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import net.md_5.bungee.api.ChatColor;

public class LoreCommand implements CommandExecutor {

	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		boolean returnVal = true;

		Log.logToConsole("Lore - /lore command sent by " + sender.getName());
		
		try
		{
			String loreType = "";
			String loreParam = "";
			if (args.length > 0) // Check to make sure we have some arguments
			{
				StringBuilder fullargs = new StringBuilder();
				StringBuilder paramargs = new StringBuilder();
				int iargs = 0;
				for (Object o : args)
				{
					if (iargs > 0)
					{
						paramargs.append(o.toString());
						paramargs.append(" ");
					}
					fullargs.append(o.toString());
					fullargs.append(" ");
					iargs++;
				}
				if (paramargs != null)
				{
					loreParam = paramargs.toString();
				}

				Log.logToConsole("Lore - /lore full arguments: " + fullargs.toString());
				Log.logToConsole("Lore - /lore command arguments: " + paramargs);
				
				if (args[0] != null)
				{
					loreType = args[0];
				}
				
				if (sender instanceof Player) { // Check if the sender is console or a player
					Player player = (Player) sender; // Cast the sender to a player
					if (player.hasPermission("loreset.command"))
					{
						Log.logToConsole("Lore - /lore permissions accepted for " + player.getName());

						// Check if we have an item in hand, if so, assign the lore
						ItemStack istack = player.getInventory().getItemInMainHand();
						Log.logToConsole("Lore - Got item stack of main hand item");
						if (istack != null)
						{
							//ItemMeta meta = istack.getItemMeta(); // Create a new ItemMeta object
							Log.logToConsole("Lore - Got meta data for item stack of main hand item");

							boolean name = false;
							boolean add = false;
							boolean clear = false;
							switch (loreType)
							{
							case "name":
								Log.logToConsole("Lore.Name - Command = Name");
								name = true;
								add = false;
								clear = false;
								break;
							case "add":
								Log.logToConsole("Lore.Add - Command = Add");
								name = false;
								add = true;
								break;
							case "clear":
								Log.logToConsole("Lore.Clear - Command = Clear");
								name = false;
								clear = true;
								break;
							case "set":
								Log.logToConsole("Lore.Set - Command = Set");
								name = false;
								add = true;
								clear = true;
								break;
								default:
									break;
							}
							Log.logToConsole("Lore - name: " + Boolean.toString(name) + " | add: " + Boolean.toString(add) + " | clear: " + Boolean.toString(clear));
							if (name) // Set name
							{
								if (LoreFunctions.setName(istack, loreParam))
								{
									Message.toPlayer(ChatColor.GREEN + "SUCCESS! - You have set the Name of this item", player);
								} else {
									Message.toPlayer(ChatColor.RED + "Unable to set name for this item", player);
								}
							}
							if (clear) // Clear first, if we're setting, we'll clear then add
							{
								if (LoreFunctions.clearLore(istack))
								{
									if (clear && !add)
									{
										Message.toPlayer(ChatColor.GREEN + "SUCCESS! - You have cleared the Lore of this item", player);
									}
								} else {
									Message.toPlayer(ChatColor.RED + "Unable to clear Lore for this item", player);
								}
							}
							if (add) // Used if adding or setting
							{
								if (LoreFunctions.addLore(istack, loreParam))
								{
									if (add && !clear)
									{
										Message.toPlayer(ChatColor.GREEN + "SUCCESS! - You have added to the Lore of this item", player);
									} else {
										Message.toPlayer(ChatColor.GREEN + "SUCCESS! - You have set the Lore of this item", player);
									}
								} else {
									Message.toPlayer(ChatColor.RED + "Unable to add Lore for this item", player);
								}
							}
							

						} else {
							Log.logToConsole(player.getName() + " tried to use /lore without an item in hand");
						}
					} else {
						Message.toPlayer(ChatColor.RED + "You do not have permission to run this command", player);
						Log.logToConsole("/lore attempted by " + player.getName() + " for " + args[0] + " - Refused due to lack of permissions");
						return true;
					}

				} else {
					Log.logToConsole("Lore - /lore cannot called by Console");
				}
			} else {
				Log.logToConsole("Lore - /lore command sent without arguments");
				returnVal = false;
				Message.toSender("Usage: /lore " + ChatColor.GREEN + "add" + ChatColor.RESET + "|" + ChatColor.RED + "clear" + ChatColor.RESET + "|" + ChatColor.DARK_PURPLE + "set" + ChatColor.AQUA + " <Lore text to add>", sender);
			}			
		}
		catch (Exception ex)
		{
			Log.logToConsole("Lore - Unexpected error in /lore - " + ex.getMessage());
			Message.toSender(ChatColor.RED + " - Error using /lore", sender);
			returnVal = false;
		}

		return returnVal;
	}
}
