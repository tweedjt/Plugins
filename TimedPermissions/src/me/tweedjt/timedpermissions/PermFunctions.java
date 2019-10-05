package me.tweedjt.timedpermissions;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.tweedjt.timedpermissions.TimedPermissions;
import me.tweedjt.timedpermissions.util.Log;
import me.tweedjt.timedpermissions.util.Message;
import me.tweedjt.timedpermissions.util.Misc;

public class PermFunctions {
	
	public static ItemStack getSmeltingPick() {
		// Create a new ItemStack for the smelting pickaxe
		ItemStack itemStack = new ItemStack(AutoSmelt.getInstance().getSmeltingPickAxeMaterial(), 1);
		// Get the item meta
		ItemMeta itemMeta = itemStack.getItemMeta();
		// Set the display name
		itemMeta.setDisplayName(AutoSmelt.getInstance().getSmeltingPickaxeName());
		// Set the lore
		itemMeta.setLore(AutoSmelt.getInstance().getSmeltingPickAxeLore());
		// Set the updated ItemMeta back to the ItemStack
		itemStack.setItemMeta(itemMeta);
		// return the item
		return itemStack;
	}
    
}

	public static boolean givePermsToPlayer(Player player) {
		if (player != null) {
			int itemSlot = Misc.firstEmptySlot(player.getUniqueId());
			if (itemSlot >= 0) {
				Message.toPlayer("&aYou have been given the permission ===blah blah blah", player);
				player.getInventory().setItem(itemSlot, getPerms());
				return true;
			} else {
				Log.logToConsole("Perm Failed to receive");
				return false;
			}
		} else {
			Log.logToConsole("Player is null, unable to give Permissions");
			return false;
		}
	
	
	

