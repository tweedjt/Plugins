package me.tweedjt.AutoSmelt;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
//import org.bukkit.command.Command;
//import org.bukkit.command.CommandExecutor;
//import org.bukkit.command.CommandSender;
//import org.bukkit.command.TabCompleter;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
//import org.bukkit.inventory.meta.ItemMeta;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.Flags;
import com.sk89q.worldguard.protection.flags.StateFlag;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;

//import net.md_5.bungee.api.ChatColor;

public class BlockListener implements Listener {
	@EventHandler
	public void onInteract(BlockBreakEvent event) {
		

		Player player = event.getPlayer(); // Get the event player (we don't really need to keep a copy of this, we could just use event.getPlayer())

		if (event.isCancelled()) {
			Util.logToConsole("Event is already cancelled");
			return;
		}
		if (AutoSmelt.getInstance().hasWorldGuard()) {
			org.bukkit.Location location = event.getBlock().getLocation(); // Get the block location
			RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer(); // Get the region container
			RegionQuery query = container.createQuery(); // Create a  query from the RegionContainer
			com.sk89q.worldedit.util.Location loc = BukkitAdapter.adapt(location); // Convert a location to a worldedit location
			LocalPlayer localPlayer = WorldGuardPlugin.inst().wrapPlayer(player); // Create a LocalPlayer from player
			State a = query.queryState(loc, localPlayer, Flags.BLOCK_BREAK); // Get the BLOCK_BREAK state for location and player
			if (a != null) {
				if (a.equals(StateFlag.State.DENY)) {
					// Stop!
					Util.logToConsole("WorldGuard preventing breaking of a block");
					return;
				}
			}
		}

	    Block block = event.getBlock(); // Get the block being broken (again, we don't have to set this)
	    Material drop = Material.AIR; // The material to drop
	    int dropAmount = 1; // The amount to drop
	    
	    ItemStack hand = player.getInventory().getItemInMainHand(); // Item in the main hand
	    
	    //START CODE SMELT PICK
	    /*
	   boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) { 
	    	
	    	if(sender instanceof Player) {
	    		Player player = (Player) sender;
	    		
	    		
	    		
	             Custom Pickaxe
	    		ItemStack smeltPick = new ItemStack(Material.DIAMOND_PICKAXE, 1); 
	  		  if (hand != null) { 
	  			  ItemMeta itemMeta = hand.getItemMeta(); if (itemMeta != null) { 
	  			  boolean loreMatch = false; List<String> lore = itemMeta.getLore();
	  		  
	  		  	if (lore != null) { for (String s : lore) { if(s.equalsIgnoreCase(ChatColor.DARK_PURPLE + "Automagically smelts ores!")) {
	  			  
	  		  loreMatch = true; break; 
	  		  }
	  		  } 
	  		  } 
	  		  if(itemMeta.getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Smelt Pick") && loreMatch) {
	  		  
	  		  } 
	  		  }
	  		  }
	    		
		        switch (event.getBlock().getType()) { //but all this down is the method of the pickaxe
		            case GOLD_ORE:
		                drop = Material.GOLD_INGOT;
		                	
		                break;
		            case IRON_ORE:
		                drop = Material.IRON_INGOT;

		                break;
		            default:
		                break;
		        }
	  		  
	            if(player.getInventory().firstEmpty() == -1) {
                	block.setType(Material.AIR); // Clear the block
                	block.getWorld().dropItemNaturally(block.getLocation().add(0.5D, 0.5D, 0.5D), new ItemStack(drop, dropAmount)); // Drops the Item
                	event.setCancelled(true); // Cancels the event which stops the block from naturally breaking
	            	}
	            else {
                	block.setType(Material.AIR);
                	player.getInventory().addItem(new ItemStack(drop, dropAmount)); // Places the ore/ingot directly into player inventory
                }
	  		  
	  		  
	   // 		player.getInventory(smeltPick); //ending here
	    		
	    		
	    		
	    		
	   // 	}
	   // 	return true;
	 //   }
	
	
	//END CODE SMELT PICK

		*/ 



	    
           
	    // Check if the player has permission
	    if (player.hasPermission("autosmelt.mine")) {
	     

	    	
	    	// Check the type of block
	        switch (event.getBlock().getType()) {
	            case GOLD_ORE:
	                //System.out.println("Gold ore found - set drop to gold ingot");
	                drop = Material.GOLD_INGOT;

	            		
	                break;
	            case IRON_ORE:
	                //System.out.println("Iron ore found - set drop to iron ingot");
	                drop = Material.IRON_INGOT;
	                

	                break;
	            default:
	                //System.out.println("Some other item");
	                break;
	        }

	        // Check if the player is holding a pickaxe
	        boolean hasPickaxe = false;
	        switch (hand.getType()) {
	            case DIAMOND_PICKAXE:
	            case GOLDEN_PICKAXE:
	            case IRON_PICKAXE:
	            case STONE_PICKAXE:
	            case WOODEN_PICKAXE:
	                hasPickaxe = true;
	                break;
	            default:
	                break;
	                
	                
	                
	        }
	        
	        // Check if the player is in survival, there is a drop and they are using a pickaxe
	        if (player.getGameMode().equals(GameMode.SURVIVAL) && hasPickaxe && !drop.equals(Material.AIR) && !block.getDrops(hand).isEmpty()) {
	        
	        	
	            if (!AutoSmelt.getInstance().hasSmelt(player.getUniqueId())) {
	            	// smelting is not enabled for this player, so don't do anything
	                return;
	            }
	        
	            // Check if the pickaxe has fortune, if so, get a random amount to get
	            if (hand.containsEnchantment(Enchantment.LOOT_BONUS_BLOCKS)) {
	                Random rand = new Random();
	                dropAmount = rand.nextInt(hand.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1) + 1;
	            }
	            
	            //SILK TOUCH ENCHANTMENT
	            
	                else if (hand.containsEnchantment(Enchantment.SILK_TOUCH)) {
	            	
	                	               	
	        	        switch (event.getBlock().getType()) {
	    	            case GOLD_ORE:
	    	                drop = Material.GOLD_ORE;
	    	              
	    	                break;
	    	            case IRON_ORE:
	    	                drop = Material.IRON_ORE;
	    	                
	    	                break;
	    	            case COAL_ORE:
	    	                drop = Material.COAL_ORE;
	    	                
	    	                break;
	    	            case DIAMOND_ORE:
	    	                drop = Material.DIAMOND_ORE;
	    	                
	    	                break;
	    	            case EMERALD_ORE:
	    	                drop = Material.EMERALD_ORE;
	    	                
	    	                break;
	    	            case REDSTONE_ORE:
	    	                drop = Material.REDSTONE_ORE;
	    	                
	    	                break;
	    	            case LAPIS_ORE:
	    	                drop = Material.LAPIS_ORE;
	    	                
	    	                
	    	                break;
	    	            default:
	    	                //System.out.println("Some other item");
	    	                break;
	    	                
	    	            
	                }
	                }
	        
	           
	            if(player.getInventory().firstEmpty() == -1) {
                	block.setType(Material.AIR); // Clear the block
                	block.getWorld().dropItemNaturally(block.getLocation().add(0.5D, 0.5D, 0.5D), new ItemStack(drop, dropAmount)); // Drops the Item
                	event.setCancelled(true); // Cancels the event which stops the block from naturally breaking
	            	}
	            else {
                	block.setType(Material.AIR);
                	player.getInventory().addItem(new ItemStack(drop, dropAmount)); // Places the ore/ingot directly into player inventory
                }
	        }
	        
	    else {
	    	// Player does not have permission
	    	}
        } 

     }
}
