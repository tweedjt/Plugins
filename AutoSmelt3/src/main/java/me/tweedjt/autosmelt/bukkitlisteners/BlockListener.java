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

    @EventHandler
    public void onInteract(BlockBreakEvent event) {

        Player player = event.getPlayer();

        if (event.isCancelled()) {
            // Check if some other plugin cancelled the event, if so, do nothing
            //Log.logToConsole("Event is already cancelled");
            return;
        }
        Block block = event.getBlock(); // Get the block being broken
        Material drop = Material.AIR; // The material to drop
        int dropAmount = 1; // The amount to drop
        ItemStack hand = event.getPlayer().getInventory().getItemInMainHand(); // Item in the main hand
        boolean allowAutoSmelt = false; // We'll use this to decide if we want to allow auto-smelting
        Random rand = new Random();


        // START NEW CODE HERE

        int DropMaxAmount = AutoSmelt.getInstance().getAutoSmeltConfig().getMaxDropAmount(); //Max amount to drop from config
        int DropMinAmount = AutoSmelt.getInstance().getAutoSmeltConfig().getMinDropAmount(); //Min amount to drop from config
        
         //int finalDropAmount = dropAmount;

        AutoSmelt.getInstance().getConfig().getConfigurationSection("blocks").getKeys(false).forEach(key -> {

            if (key.equalsIgnoreCase(event.getBlock().getType().toString())) {
                Log.logToConsole("List Found");
                ItemStack[] items = new ItemStack[AutoSmelt.getInstance().getConfig().getStringList("blocks" + key).size()];
                ItemStack item = null;
                int position = 0;
                for (String i : AutoSmelt.getInstance().getConfig().getStringList("blocks" + key)) { //Reads down the blocks option in config
                    Log.logToConsole("reading block list");
                    try {
                        item = new ItemStack(Material.matchMaterial(i), 1); //drops item they chose
                        Log.logToConsole("Dropping item");
                    } catch(Exception e) {
                        item = new ItemStack(Material.matchMaterial(key)); //give back item they mined if they spell wrong
                    Log.logToConsole("Error couldnt drop item");
                    }
                    items[position] = item;
                    position++;
                }
            }

        });

             /*   if(AutoSmelt.getInstance().getAutoSmeltConfig().getDropAmount() == true) {
                Log.logToConsole("doin teh mafs");
                   dropAmount = rand.nextInt(DropMaxAmount + DropMinAmount + 1);
                } else {
                    dropAmount = 1;
                    Log.logToConsole("Math too hard");
                } */

        /*
        Check for blocks section
        Check BlockList in blocks section
        if player mines block stated in config then check droplist for drops from corresponding block, set original drop to air, drop item
        blocks:
            IRON_ORE:
                - IRON_INGOT
         */
        //create command to add blocks and drops to list/config in game



        // END CODE HERE






        /* Check the block being broken, if it isn't gold ore or iron ore, return out
        switch (event.getBlock().getType()) {
            case GOLD_ORE:
                //Log.logToConsole("Block at location is Gold Ore");
                drop = Material.GOLD_INGOT;
                break;
            case IRON_ORE:
                //Log.logToConsole("Block at location is Iron Ore");
                drop = Material.IRON_INGOT;
                break;
            case ANCIENT_DEBRIS:
                drop = Material.NETHERITE_SCRAP;
                break;
            case DEEPSLATE_GOLD_ORE:
                //Log.logToConsole("Block at location is Deepslate Gold Ore");
                drop = Material.GOLD_INGOT;
                break;
            case DEEPSLATE_IRON_ORE:
                drop = Material.IRON_INGOT;
                break;
            case COPPER_ORE:
                drop = Material.COPPER_INGOT;
                dropAmount = rand.nextInt(1 + 3) + 1;
                break;
            case DEEPSLATE_COPPER_ORE:
                drop = Material.COPPER_INGOT;
                dropAmount = rand.nextInt(1 + 3) + 1;
                break;
            default:
                // It isn't gold or iron ore, exit out
                return;
        } */

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
                // Log.debugToConsole("Tool in hand is pickaxe");
                break;
            default:
                //	Log.debugToConsole("Tool in hand is not pickaxe");
                break;
        }

        if (SmeltFunctions.isSmeltingPick(hand)) {
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
                if (AutoSmelt.getInstance().hasSmelt(event.getPlayer().getUniqueId())) {
                    // We have pick and permission, and autosmelting is turned on for this player
                    //Log.debugToConsole("AutoSmelting is turned on");
                    allowAutoSmelt = true;
                } else {
                    // We have pick and permission, but autosmelting is turned off for this player
                    //Log.debugToConsole("AutoSmelting is turned off");
                }
            }

        }


        // Check the players gamemode, if they aren't in survival, we will deny auto-smelting even
        // if they have a smelting pick or smelting permission
        if (!event.getPlayer().getGameMode().equals(GameMode.SURVIVAL)) {
            // Player is not in Survival mode
            Log.debugToConsole("Player is not in survival mode");
            allowAutoSmelt = false;
            block.setType(Material.AIR); // Clear the block
        }

        if (block.getDrops(hand).isEmpty()) {
            // There are no drops
            Log.debugToConsole("There are no drops");
            allowAutoSmelt = false;
        }

        if (allowAutoSmelt) {

            Log.debugToConsole("Allowing Auto-Smelt");

            // Check if the pickaxe has fortune, if so, get a random amount to get
            if (AutoSmelt.getInstance().getAutoSmeltConfig().getFortuneDrops()) {

                if (hand.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
                    dropAmount = rand.nextInt(hand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1) + 1;
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
                        dropAmount = rand.nextInt(1 + 3) + 1;

                        break;
                    case DEEPSLATE_GOLD_ORE:
                        drop = Material.DEEPSLATE_GOLD_ORE;

                        break;
                    case DEEPSLATE_COPPER_ORE:
                        drop = Material.DEEPSLATE_COPPER_ORE;
                        dropAmount = rand.nextInt(1 + 3) + 1;

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
            if (handMeta instanceof Damageable) {
                Damageable d = (Damageable) handMeta; // Create the damageable (make sure its org.bukkit.inventory.meta.Damageable and not Entity.Damageable)
                d.setDamage(d.getDamage() + AutoSmelt.getInstance().getSmeltingDamage()); // Set the damage
                hand.setItemMeta(handMeta); // Set the meta back to the itemstack
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


