package me.tweedjt.autosmelt;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.tweedjt.autosmelt.util.Log;
import me.tweedjt.autosmelt.util.Message;
import me.tweedjt.autosmelt.util.Misc;


public class SmeltFunctions {


    public static boolean isSmeltingPick(ItemStack itemStack) {
        // Get the real pick
        ItemStack realPick = getSmeltingPick();

        // Cloning the provided pick as I'll be playing with the meta
        // and we don't want it accidentally carrying back over
        ItemStack clonedThisPick = itemStack.clone();

        // Get the item meta from the item we're checking
        ItemMeta itemMeta = clonedThisPick.getItemMeta();
        if (itemMeta == null) {
            return false;
        }

        // Create a new item based on what we're checking
        // we're doing it this way so we won't take into
        // account any damage or enchantments when comparing
        // instead we're just checking name and lore

        ItemStack thisPick = new ItemStack(clonedThisPick.getType(), 1);
        ItemMeta thisPickMeta = thisPick.getItemMeta();
        thisPickMeta.setDisplayName(itemMeta.getDisplayName());
        thisPickMeta.setLore(itemMeta.getLore());
        thisPick.setItemMeta(thisPickMeta);

        // Get the string blob for the real smelting pick
        String realSmeltingPickBlob = Misc.itemToStringBlob(realPick);

        // Get the string blob for the item we're checking
        String itemBlob = Misc.itemToStringBlob(thisPick);



        Log.debugToConsole("Real Pick: " + realSmeltingPickBlob);
        Log.debugToConsole("This Pick: " + itemBlob);
        // Compare them
        if (realSmeltingPickBlob.equals(itemBlob)) {
            return true;
        } else {
            return false;
        }
    }
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

    public static boolean giveSmeltingPickToPlayer(Player player) {
        if (player != null) {
            int itemSlot = Misc.firstEmptySlot(player.getUniqueId());
            if (itemSlot >= 0) {
                Message.toPlayer("&aEnjoy your new smelting pick!", player);
                player.getInventory().setItem(itemSlot, getSmeltingPick());
                return true;
            } else {
                Log.logToConsole("No free inventory slot found");
                return false;
            }
        } else {
            Log.logToConsole("Player is null, unable to give Smelting pickaxe");
            return false;
        }
    }
}
