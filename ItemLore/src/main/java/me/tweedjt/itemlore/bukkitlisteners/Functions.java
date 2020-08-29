package me.tweedjt.itemlore.bukkitlisteners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import work.torp.loreset.alerts.Alert;

public class Functions {
	public static boolean clearLore(ItemStack istack)
	{
		boolean ret = false;
		try {
			ItemMeta meta = istack.getItemMeta(); // Create a new ItemMeta object
			Alert.VerboseLog("Functions.clearLore",  "Clearing the Lore from the meta data");
			meta.setLore(null);
			Alert.VerboseLog("Functions.clearLore", "Assigning the cleared meta data back to the itemstack");
			istack.setItemMeta(meta); // Set the ItemMeta object onto our ItemStack
			ret = true;
		} 
		catch (Exception ex) {
			Alert.Log("Clearing Lore", "Unexpected error: " + ex.getMessage());
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
					Alert.VerboseLog("Functions.addLore",  "Adding existing lore entry to list: " + o.toString());
					lore.add(0, (String) o);
				}
			}
			Alert.VerboseLog("Functions.addLore",  "Adding lore to meta data");
			meta.setLore(lore); // Set the List containing our Lore to the ItemMeta object
			Alert.VerboseLog("Functions.addLore",  "Adding meta data to itemstack");
			istack.setItemMeta(meta); // Set the ItemMeta object onto our ItemStack
			ret = true;
		} 
		catch (Exception ex) {
			Alert.Log("Adding Lore", "Unexpected error: " + ex.getMessage());
		}
		return ret;
	}
	public static boolean setName(ItemStack istack, String loreParam)
	{
		boolean ret = false;
		try {
			ItemMeta meta = istack.getItemMeta(); // Create a new ItemMeta object
			Alert.VerboseLog("Functions.setName",  "Adding display name to meta data - " + loreParam);
			meta.setDisplayName(loreParam);
			Alert.VerboseLog("Functions.setName",  "Adding meta data to itemstack");
			istack.setItemMeta(meta); // Set the ItemMeta object onto our ItemStack
			ret = true;
		} 
		catch (Exception ex) {
			Alert.Log("Setting Name", "Unexpected error: " + ex.getMessage());
		}
		return ret;
	}
}
