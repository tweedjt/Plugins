package me.tweedjt.autosmelt.bukkitlisteners;

import java.util.*;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import me.tweedjt.autosmelt.AutoSmelt;
import me.tweedjt.autosmelt.SmeltFunctions;
import me.tweedjt.autosmelt.util.Log;
import me.tweedjt.autosmelt.util.Misc;

@SuppressWarnings("ALL")
public class BlockListener implements Listener {

    private AutoSmelt plugin;

    @EventHandler
    public void onInteract(BlockBreakEvent event) {

        // Create a variable to hold our instance
        this.plugin = AutoSmelt.getInstance();
        // Create a local copy of SmeltFunctions with the instance passed to it
        // we'll use this copy (smeltFunctions) to call our functions, NOT SmeltFunctions
        SmeltFunctions smeltFunctions = new SmeltFunctions(plugin);

        Player player = event.getPlayer();

        if (event.isCancelled()) {
            // Check if some other plugin cancelled the event, if so, do nothing
            //Log.logToConsole("Event is already cancelled"); //Disable on release
            return;
        }
        Block block = event.getBlock(); // Get the block being broken
        Material drop = Material.AIR; // The material to drop
        int dropAmount = 1; // The amount to drop
        ItemStack hand = event.getPlayer().getInventory().getItemInMainHand(); // Item in the main hand
        boolean allowAutoSmelt = false; // We'll use this to decide if we want to allow auto-smelting
        Random rand = new Random();

        // NOTE: We have a local copy of "plugin" now (our instantiated copy of AutoSmelt)
        boolean randDrops = plugin.getAutoSmeltConfig().getDropAmount();
        int dropMaxAmount = plugin.getAutoSmeltConfig().getMaxDropAmount(); // Max amount to drop from config
        int dropMinAmount = plugin.getAutoSmeltConfig().getMinDropAmount(); // Min amount to drop from config


        // TODO: Create command to add blocks and drops to their own class
        // TODO: Add option to add blocks to mine in config with drops
        // Look at the block being broken, based on the type, check the drops from the config
        // if they exist, we will do a random entry from the list, if not, we'll default to
        // a value.  If it isn't a block we're expeciting, we'll simply break out
        switch (event.getBlock().getType()) {
            case GOLD_ORE:
                List<Material> goldDrops = new ArrayList<>(plugin.getAutoSmeltConfig().goldDrops());
                if (goldDrops.size() > 0) {
                    if (randDrops) { // Checks if Random drops is true
                        drop = goldDrops.get((rand.nextInt(goldDrops.size())));// This gets a random entry from our drops list
                        dropAmount = rand.nextInt(dropMaxAmount - dropMinAmount) + dropMinAmount; // Randomly drops a number of ingots based on min&max
                        //Log.logToConsole("GOLD ORE BROKEN"); //Disable on release
                    } else {
                        drop = goldDrops.get((rand.nextInt(goldDrops.size()))); // Random entry from our drops list and drops 1 ingot
                    }
                } else {
                    // None listed, default
                    drop = Material.GOLD_INGOT;
                    //Log.logToConsole("Return Statment for GOLD_ORE"); //Disable on release
                }
                break;
            case DEEPSLATE_GOLD_ORE:
                List<Material> deepSlateGoldDrops = new ArrayList<>(plugin.getAutoSmeltConfig().deepSlateGoldDrops());
                if (deepSlateGoldDrops.size() > 0) {
                    if (randDrops) { // Checks if Random drops is true
                        drop = deepSlateGoldDrops.get(rand.nextInt(deepSlateGoldDrops.size())); // NOTE: Test this, we might need to do a -1.  This should get a random entry from our drops list
                        dropAmount = rand.nextInt(dropMaxAmount - dropMinAmount) + dropMinAmount; // Randomly drops a number of ingots based on min&max
                        //Log.logToConsole("DEEPSLATE GOLD ORE BROKEN"); //Disable on release
                    } else {
                        drop = deepSlateGoldDrops.get(rand.nextInt(deepSlateGoldDrops.size()));
                    }
                } else {
                    // None listed, default
                    drop = Material.GOLD_INGOT;
                    //Log.logToConsole("Return Statment for DEEPSLATE_GOLD_ORE"); //Disable on release
                }
                break;
            case IRON_ORE:
                List<Material> ironDrops = new ArrayList<>(plugin.getAutoSmeltConfig().ironDrops());
                if (ironDrops.size() > 0) {
                    if (randDrops) { // Checks if Random drops is true
                        drop = ironDrops.get(rand.nextInt(ironDrops.size())); // NOTE: Test this, we might need to do a -1.  This should get a random entry from our drops list
                        dropAmount = rand.nextInt(dropMaxAmount - dropMinAmount) + dropMinAmount; // Randomly drops a number of ingots based on min&max
                        //Log.logToConsole("IRON ORE BROKEN"); //Disable on release
                    } else {
                        drop = ironDrops.get(rand.nextInt(ironDrops.size()));
                    }
                } else {
                    // None listed, default
                    drop = Material.IRON_INGOT;
                    //Log.logToConsole("Return Statment for IRON_ORE"); //Disable on release
                }
                break;
            case DEEPSLATE_IRON_ORE:
                List<Material> deepSlateIronDrops = new ArrayList<>(plugin.getAutoSmeltConfig().deepSlateIronDrops());
                if (deepSlateIronDrops.size() > 0) {
                    if (randDrops) { // Checks if Random drops is true
                        drop = deepSlateIronDrops.get(rand.nextInt(deepSlateIronDrops.size())); // NOTE: Test this, we might need to do a -1.  This should get a random entry from our drops list
                        dropAmount = rand.nextInt(dropMaxAmount - dropMinAmount) + dropMinAmount; // Randomly drops a number of ingots based on min&max
                        //Log.logToConsole("DEEPSLATE IRON ORE BROKEN"); //Disable on release
                    } else {
                        drop = deepSlateIronDrops.get(rand.nextInt(deepSlateIronDrops.size()));
                    }
                } else {
                    // None listed, default
                    drop = Material.IRON_INGOT;
                    //Log.logToConsole("Return Statment for DEEPSLATE_IRON_ORE"); //Disable on release
                }
                break;
            case COPPER_ORE:
                List<Material> copperDrops = new ArrayList<>(plugin.getAutoSmeltConfig().copperDrops());
                if (copperDrops.size() > 0) {
                    if (randDrops) { // Checks if Random drops is true
                        drop = copperDrops.get(rand.nextInt(copperDrops.size())); // NOTE: Test this, we might need to do a -1.  This should get a random entry from our drops list
                        dropAmount = rand.nextInt(dropMaxAmount - dropMinAmount) + dropMinAmount; // Randomly drops a number of ingots based on min&max
                        //Log.logToConsole("COPPER ORE BROKEN"); //Disable on release
                    } else {
                        drop = copperDrops.get(rand.nextInt(copperDrops.size()));
                        dropAmount = rand.nextInt(1 + 3) + 1;
                    }
                } else {
                    // None listed, default
                    drop = Material.COPPER_INGOT;
                    //Log.logToConsole("Return Statment for COPPER_ORE"); //Disable on release
                }
                break;
            case DEEPSLATE_COPPER_ORE:
                List<Material> deepSlateCopperDrops = new ArrayList<>(plugin.getAutoSmeltConfig().deepSlateCopperDrops());
                if (deepSlateCopperDrops.size() > 0) {
                    if (randDrops) { // Checks if Random drops is true
                        drop = deepSlateCopperDrops.get(rand.nextInt(deepSlateCopperDrops.size())); // NOTE: Test this, we might need to do a -1.  This should get a random entry from our drops list
                        dropAmount = rand.nextInt(dropMaxAmount - dropMinAmount) + dropMinAmount; // Randomly drops a number of ingots based on min&max
                        //Log.logToConsole("DEEPSLATE COPPER ORE BROKEN"); //Disable on release
                    } else {
                        drop = deepSlateCopperDrops.get(rand.nextInt(deepSlateCopperDrops.size()));
                        dropAmount = rand.nextInt(1 + 3) + 1;
                    }
                } else {
                    // None listed, default
                    drop = Material.COPPER_INGOT;
                    //Log.logToConsole("Return Statment for DEEPSLATE_COPPER_ORE"); //Disable on release
                }
                break;
            case ANCIENT_DEBRIS:
                List<Material> ancientDebrisDrops = new ArrayList<>(plugin.getAutoSmeltConfig().ancientDebrisDrops());
                if (ancientDebrisDrops.size() > 0) {
                    if (randDrops) { // Checks if Random drops is true
                        drop = ancientDebrisDrops.get(rand.nextInt(ancientDebrisDrops.size())); // NOTE: Test this, we might need to do a -1.  This should get a random entry from our drops list
                        dropAmount = rand.nextInt(dropMaxAmount - dropMinAmount) + dropMinAmount; // Randomly drops a number of ingots based on min&max
                        //Log.logToConsole("ANCIENT DEBRIS BROKEN"); //Disable on release
                    } else {
                        drop = ancientDebrisDrops.get(rand.nextInt(ancientDebrisDrops.size()));
                    }
                } else {
                    // None listed, default
                    drop = Material.NETHERITE_SCRAP;
                    //Log.logToConsole("Return Statment for ANCIENT_DEBRIS"); //Disable on release
                }
                break;
            default:
                //Log.logToConsole("DEFAULT STATEMENT FOR ORE SWITCH"); //Disable on release
                return;
        }

        if (Misc.worldGuardPreventBreakAtLocation(event.getBlock(), event.getPlayer())) {
            // If WorldGuard is blocking the breaking here, exit out
            //Log.debugToConsole("WorldGuard is preventing breaking here");
            return;
        }


        // Check if the player is holding a pickaxe
        boolean hasPickaxe = false;
        switch (hand.getType()) {
            case DIAMOND_PICKAXE:
            case GOLDEN_PICKAXE:
            case IRON_PICKAXE:
            case STONE_PICKAXE:
            case WOODEN_PICKAXE:
            case NETHERITE_PICKAXE:
                hasPickaxe = true;
                //Log.debugToConsole("Tool in hand is pickaxe"); //Disable on release
                break;
            default:
                //Log.debugToConsole("Tool in hand is not pickaxe"); //Disable on release
                break;
        }

        if (smeltFunctions.isSmeltingPick(hand)) {
            // We have a smelting pick, we don't need permissions
            //Log.debugToConsole("Player has a Smelting Pick");
            allowAutoSmelt = true;
        } else {
            //Log.debugToConsole("Player does not have a Smelting Pick");
            // Check if we haven't already approved the auto-smelting due to a smelting pick, and that
            // we have permission and we have a pickaxe in hand
            if (event.getPlayer().hasPermission("autosmelt.mine") && hasPickaxe) {
                //Log.debugToConsole("Player has permission and has a pickaxe");
                // We have a pick and permission
                if (plugin.getSmeltData().hasSmelt(event.getPlayer().getUniqueId())) {
                    // We have pick and permission, and autosmelting is turned on for this player
                    //Log.debugToConsole("AutoSmelting is turned on");
                    allowAutoSmelt = true;
                } else {
                    // We have pick and permission, but autosmelting is turned off for this player
                    //Log.debugToConsole("AutoSmelting is turned off");
                }
            }

        }

        boolean autoSmeltDefault = plugin.getAutoSmeltConfig().getAutoSmelt();
        // Check the players gamemode, if they aren't in survival, we will deny auto-smelting even
        // if they have a smelting pick or smelting permission
        if (!event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            // Player is not in Survival mode
            Log.debugToConsole("Player is not in survival mode");
            allowAutoSmelt = false;
            block.setType(Material.AIR); // Clear the block
        }

        if (autoSmeltDefault) {
            if (event.getPlayer().hasPermission("autosmelt.mine"))
            allowAutoSmelt = true;
        }

        if (block.getDrops(hand).isEmpty()) {
            // There are no drops
           // Log.debugToConsole("There are no drops");
            allowAutoSmelt = false;
        }

        if (allowAutoSmelt) {

           // Log.debugToConsole("Allowing Auto-Smelt");

            // Check if the pickaxe has fortune, if so, get a random amount to get
            if (AutoSmelt.getInstance().getAutoSmeltConfig().getFortuneDrops()) {

                if (hand.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {

                if (drop == Material.NETHERITE_SCRAP) {
                    List<Material> ancientDebrisDrops = new ArrayList<>(plugin.getAutoSmeltConfig().ancientDebrisDrops());
                        drop = ancientDebrisDrops.get(rand.nextInt(ancientDebrisDrops.size()));
                        dropAmount = 1;
                        // If Ancient Debris is Mined cancel fortune drop (Fortune is not a vanilla feature for Netherite Scrap)
                } else {

                    dropAmount = rand.nextInt(hand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1) + 1;

                    // Drop a random amount for fortune mining

                }


                }
            }
            if (hand.containsEnchantment(Enchantment.SILK_TOUCH)) {


                switch (event.getBlock().getType()) {
                    case GOLD_ORE:
                        drop = Material.GOLD_ORE;

                        break;
                    case IRON_ORE:
                        drop = Material.IRON_ORE;

                        break;
                    case ANCIENT_DEBRIS:
                        drop = Material.ANCIENT_DEBRIS;

                        break;
                    case COPPER_ORE:
                        drop = Material.COPPER_ORE;
                        dropAmount = 1;

                        break;
                    case DEEPSLATE_GOLD_ORE:
                        drop = Material.DEEPSLATE_GOLD_ORE;

                        break;
                    case DEEPSLATE_COPPER_ORE:
                        drop = Material.DEEPSLATE_COPPER_ORE;
                        dropAmount = 1;

                        break;
                    case DEEPSLATE_IRON_ORE:
                        drop = Material.DEEPSLATE_IRON_ORE;

                        break;
                    default:
                        //System.out.println("Some other item");
                        break;

                }

                //return;
            }

            // Clear the block
            block.setType(Material.AIR); // Sets the block to an AIR block

            // Damage the pickaxe manually as we're cancelling the events, so otherwise
            // it never takes damage.
            ItemMeta handMeta = hand.getItemMeta();
            if (plugin.getAutoSmeltConfig().getPickDamage()) {
                
                if (handMeta instanceof Damageable) {
                    Damageable d = (Damageable) handMeta; // Create the damageable (make sure its org.bukkit.inventory.meta.Damageable and not Entity.Damageable)
                    d.setDamage(d.getDamage() + plugin.getSmeltData().getSmeltingDamage()); // Set the damage
                    hand.setItemMeta(handMeta); // Set the meta back to the itemstack
                }
            }

            boolean dropItem = false; // Default to false

            block.setType(Material.AIR); // Clear the block
            Location loc = block.getLocation(); //whatever your location is
            World w = block.getWorld();


            // Check if auto-pickup is on
            if (AutoSmelt.getInstance().getAutoSmeltConfig().getAutoPickup()) {

                // Auto-Pickup is on, so check the players first empty slot
                if (player.getInventory().firstEmpty() == -1) {

                    // No empty slots - drop the item
                    dropItem = true;
                } else {

                    // empty slot found, place item in inventory
                    player.getInventory().addItem(new ItemStack(drop, dropAmount)); // Places the ore/ingot directly into player inventory

                    if (!Misc.xpDrops(drop, w, loc)) {

                        return;
                    }
                }
            } else {

                // auto-pickup is off, so drop the item
                dropItem = true;
            }

            if (dropItem) {
                // drop the item
                block.getWorld().dropItemNaturally(block.getLocation().add(0.2D, 0.2D, 0.2D), new ItemStack(drop, dropAmount)); // Drops the Item

                if (!Misc.xpDrops(drop, w, loc)) {


                    return;
                }

            }

            event.setCancelled(true); // Cancels the event which stops the block from naturally breaking

        }

    }

}


