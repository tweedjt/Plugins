package me.tweedjt.autopickup.bukkitlisteners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.ExpBottleEvent;
import org.bukkit.inventory.ItemStack;
import me.tweedjt.autopickup.AutoPickup;

public class BlockListener implements Listener {

	boolean autoPickup = AutoPickup.getInstance().getAutoPickupConfig().getAutoPickup();
	boolean autoMonsterDrops = AutoPickup.getInstance().getAutoPickupConfig().getAutoMonster();
	boolean autoAnimalsDrops = AutoPickup.getInstance().getAutoPickupConfig().getAutoAnimals();
	boolean pvpDrops = AutoPickup.getInstance().getAutoPickupConfig().getPvpDrops();
	boolean dropGlass = AutoPickup.getInstance().getAutoPickupConfig().getDropGlass();
	boolean dropExp = AutoPickup.getInstance().getAutoPickupConfig().getExp();

	@EventHandler
	public void onEntityDeath(EntityDeathEvent event) {

		if (event.getEntity().getKiller() instanceof Player
				&& ((this.autoMonsterDrops && event.getEntity() instanceof org.bukkit.entity.Monster)
						|| (this.pvpDrops && event.getEntity() instanceof Player)
						|| (this.autoAnimalsDrops && event.getEntity() instanceof org.bukkit.entity.Animals))) {

			Player killer = event.getEntity().getKiller();
			killer.giveExp(event.getDroppedExp());

			if (event.getDroppedExp() > 0) {
				killer.playSound(killer.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.1F, 0.7F);
			}
			for (ItemStack item : event.getDrops()) {
				killer.getInventory().addItem(new ItemStack[] { item });
			}
			event.setDroppedExp(0);
			event.getDrops().clear();
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		Block block = event.getBlock(); // Get the block being broken
	    Material drop = Material.AIR; // The material to drop
	    int dropAmount = 1; // The amount to drop
		 block.setType(Material.AIR); 
		 boolean dropItem = false; // Default to false
		
		if (autoPickup) {
			
			 if(player.getInventory().firstEmpty() == -1) {

                 dropItem = true;
             } else {
             	
                 // empty slot found, place item in inventory
                 player.getInventory().addItem(new ItemStack(drop, dropAmount)); // Places the ore/ingot directly into player inventory
                 
                 	}
             }else {
         	
             // auto-pickup is off, so drop the item
             dropItem = true;
         }
         
         if (dropItem) {
             // drop the item
             block.getWorld().dropItemNaturally(block.getLocation().add(0.2D, 0.2D, 0.2D), new ItemStack(drop, dropAmount)); // Drops the Item
             
         }else {
         		return;
         	}
         

	 event.setCancelled(true); 
 	
	 //CODE END FOR AUTOPICKUP
		
		if (this.dropGlass) {

			if ((block.getType() == Material.GLASS) && this.dropGlass) {

				ItemStack item = new ItemStack(block.getType(), 1);

				block.getWorld().dropItemNaturally(block.getLocation(), item);
			}

			event.setExpToDrop(0);
		}
	}

	@EventHandler
	public void onExpBottle(ExpBottleEvent event) {
		if (this.dropExp && event.getEntity().getShooter() instanceof Player) {
			Player player = (Player) event.getEntity().getShooter();
			player.giveExp(event.getExperience());

			if (event.getExperience() > 0) {
				player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.1F, 0.7F);
			}
			event.setExperience(0);
		}
	}
}