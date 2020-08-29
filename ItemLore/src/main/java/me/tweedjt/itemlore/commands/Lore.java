package me.tweedjt.itemlore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import work.torp.loreset.alerts.Alert;
import work.torp.loreset.helpers.Functions;
import net.md_5.bungee.api.ChatColor;

public class Lore implements CommandExecutor {
	@Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		boolean returnVal = true;

		Alert.VerboseLog("Lore", "/lore command sent by " + sender.getName());
		
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
				
				Alert.VerboseLog("Lore", "/lore full arguments: " + fullargs.toString());
				Alert.VerboseLog("Lore", "/lore command arguments: " + paramargs);
				
				if (args[0] != null)
				{
					loreType = args[0];
				}
				
				if (sender instanceof Player) { // Check if the sender is console or a player
					Player player = (Player) sender; // Cast the sender to a player
					if (player.hasPermission("loreset.command"))
					{
						Alert.VerboseLog("Lore",  "/lore permissions accepted for " + player.getName());

						// Check if we have an item in hand, if so, assign the lore
						ItemStack istack = player.getInventory().getItemInMainHand();	
						Alert.VerboseLog("Lore",  "Got item stack of main hand item");
						if (istack != null)
						{
							//ItemMeta meta = istack.getItemMeta(); // Create a new ItemMeta object
							Alert.VerboseLog("Lore",  "Got meta data for item stack of main hand item");

							boolean name = false;
							boolean add = false;
							boolean clear = false;
							switch (loreType)
							{
							case "name":
								Alert.VerboseLog("Lore.Name",  "Command = Name");
								name = true;
								add = false;
								clear = false;
								break;
							case "add":
								Alert.VerboseLog("Lore.Add",  "Command = Add");
								name = false;
								add = true;
								break;
							case "clear":
								Alert.VerboseLog("Lore.Clear",  "Command = Clear");
								name = false;
								clear = true;
								break;
							case "set":
								Alert.VerboseLog("Lore.Set",  "Command = Set");
								name = false;
								add = true;
								clear = true;
								break;
								default:
									break;
							}
							Alert.VerboseLog("Lore", "name: " + Boolean.toString(name) + " | add: " + Boolean.toString(add) + " | clear: " + Boolean.toString(clear));
							if (name) // Set name
							{
								if (Functions.setName(istack, loreParam))
								{
									Alert.Player(ChatColor.GREEN + "SUCCESS! - " + ChatColor.RESET + "You have set the Name of this item", player, true);
								} else {
									Alert.Player(ChatColor.RED + "Unable to set name for this item", player, true);
								}
							}
							if (clear) // Clear first, if we're setting, we'll clear then add
							{
								if (Functions.clearLore(istack))
								{
									if (clear && !add)
									{
										Alert.Player(ChatColor.GREEN + "SUCCESS! - " + ChatColor.RESET + "You have cleared the Lore of this item", player, true);
									}
								} else {
									Alert.Player(ChatColor.RED + "Unable to clear Lore for this item", player, true);
								}
							}
							if (add) // Used if adding or setting
							{
								if (Functions.addLore(istack, loreParam))
								{
									if (add && !clear)
									{
										Alert.Player(ChatColor.GREEN + "SUCCESS! - " + ChatColor.RESET + "You have added to the Lore of this item", player, true);
									} else {
										Alert.Player(ChatColor.GREEN + "SUCCESS! - " + ChatColor.RESET + "You have set the Lore of this item", player, true);
									}
								} else {
									Alert.Player(ChatColor.RED + "Unable to add Lore for this item", player, true);
								}
							}
							

						} else {
							Alert.VerboseLog("Lore",  player.getName() + " tried to use /lore without an item in hand");
						}
					} else {
						Alert.Player(ChatColor.RED + "You do not have permission to run this command", player, true);
						Alert.VerboseLog("Lore", "/lore attempted by " + player.getName() + " for " + args[0] + " - Refused due to lack of permissions");
						return true;
					}

				} else {
					Alert.VerboseLog("Lore",  "/lore cannot called by Console");
				}
			} else {
				Alert.VerboseLog("Lore", "/lore command sent without arguments");
				returnVal = false;
				Alert.Sender("Usage: /lore " + ChatColor.GREEN + "add" + ChatColor.RESET + "|" + ChatColor.RED + "clear" + ChatColor.RESET + "|" + ChatColor.DARK_PURPLE + "set" + ChatColor.AQUA + " <Lore text to add>", sender, true);
			}			
		}
		catch (Exception ex)
		{
			Alert.Log("Lore", "Unexpected error in /lore - " + ex.getMessage());
			Alert.Sender(ChatColor.RED + " - Error using /lore", sender, true);
			returnVal = false;
		}

		return returnVal;
	}
}
