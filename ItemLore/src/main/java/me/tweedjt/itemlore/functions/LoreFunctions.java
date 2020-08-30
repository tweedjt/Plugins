package me.tweedjt.itemlore.functions;

import java.util.ArrayList;
import java.util.List;

import me.tweedjt.itemlore.util.Log;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LoreFunctions {
	public static boolean clearLore(ItemStack istack)
	{
		boolean ret = false;
		try {
			ItemMeta meta = istack.getItemMeta(); // Create a new ItemMeta object
			Log.logToConsole("Functions.clearLore" + "Clearing the Lore from the meta data");
			meta.setLore(null);
			Log.logToConsole("Functions.clearLore" + "Assigning the cleared meta data back to the itemstack");
			istack.setItemMeta(meta); // Set the ItemMeta object onto our ItemStack
			ret = true;
		} 
		catch (Exception ex) {
			Log.logToConsole("Clearing Lore" + "Unexpected error: " + ex.getMessage());
		}
		return ret;
	}
	public static boolean addLore(ItemStack istack, String loreParam)
	{
		boolean ret = false;
		try {
			ItemMeta meta = istack.getItemMeta(); // Create a new ItemMeta object
			List<String> lore = new ArrayList<String>(); 
			lore.add(loreParam);
			if (meta.getLore() != null)
			{
				for (Object o : meta.getLore())
				{
					Log.logToConsole("Functions.addLore" + "Adding existing lore entry to list: " + o.toString());
					lore.add(0, (String) o);
				}
			}
			Log.logToConsole("Functions.addLore" + "Adding lore to meta data");
			meta.setLore(lore); // Set the List containing our Lore to the ItemMeta object
			Log.logToConsole("Functions.addLore" + "Adding meta data to itemstack");
			istack.setItemMeta(meta); // Set the ItemMeta object onto our ItemStack
			ret = true;
		} 
		catch (Exception ex) {
			Log.logToConsole("Adding Lore" + "Unexpected error: " + ex.getMessage());
		}
		return ret;
	}
	public static boolean setName(ItemStack istack, String loreParam)
	{
		boolean ret = false;
		try {
			ItemMeta meta = istack.getItemMeta(); // Create a new ItemMeta object
			Log.logToConsole("Functions.setName" + "Adding display name to meta data - " + loreParam);
			meta.setDisplayName(loreParam);
			Log.logToConsole("Functions.setName" + "Adding meta data to itemstack");
			istack.setItemMeta(meta); // Set the ItemMeta object onto our ItemStack
			ret = true;
		} 
		catch (Exception ex) {
			Log.logToConsole("Setting Name" + "Unexpected error: " + ex.getMessage());
		}
		return ret;
	}
}
