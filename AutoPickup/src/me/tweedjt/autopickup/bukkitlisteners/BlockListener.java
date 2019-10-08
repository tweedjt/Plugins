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
import me.tweedjt.autopickup.util.Log;

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

		if (event.isCancelled()) {
			// Check if some other plugin cancelled the event, if so, do nothing
			// Log.logToConsole("Event is already cancelled");
			return;
		}

		Player player = event.getPlayer();
		Block block = event.getBlock(); // Get the block being broken
		block.setType(Material.AIR);
		boolean dropItem = false; // Default to false
		ItemStack hand = event.getPlayer().getInventory().getItemInMainHand();

		boolean allowAutoPickup = false; // We'll use this to decide if we want to allow auto-smelting
		boolean hasPickaxe = false;
		switch (hand.getType()) {
		case DIAMOND_PICKAXE:
		case GOLDEN_PICKAXE:
		case IRON_PICKAXE:
		case STONE_PICKAXE:
		case WOODEN_PICKAXE:
			hasPickaxe = true;
			// Log.debugToConsole("Tool in hand is pickaxe");
			break;
		default:
			// Log.debugToConsole("Tool in hand is not pickaxe");
			break;
		}

		if (event.getPlayer().hasPermission("autosmelt.mine") && hasPickaxe) {
			// Log.debugToConsole("Player has permission and has a pickaxe");
			// We have a pick and permission
			if (AutoPickup.getInstance().hasPickup(event.getPlayer().getUniqueId())) {
				// We have pick and permission, and autosmelting is turned on for this player
				// Log.debugToConsole("AutoSmelting is turned on");
				allowAutoPickup = true;
			} else {
				// We have pick and permission, but autosmelting is turned off for this player
				// Log.debugToConsole("AutoSmelting is turned off");
			}

			// Player is not in Survival mode
			Log.debugToConsole("Player is not in survival mode");
			allowAutoPickup = false;

			if (block.getDrops(hand).isEmpty()) {
				// There are no drops
				Log.debugToConsole("There are no drops");
				allowAutoPickup = false;
			}

			if (allowAutoPickup) {

				Log.debugToConsole("Allowing Auto-Pickup");

				ItemStack itemStack = new ItemStack(block.getType(), 1);

				if (autoPickup) {

					if (player.getInventory().firstEmpty() == -1) {

						dropItem = true;

					} else {
						if (block != null) {
							if (block.getType() != null) {

								if (itemStack != null) {
									player.getInventory().addItem(itemStack);

								}
							}
						}
					}

				} else {

					// auto-pickup is off, so drop the item
					dropItem = true;
				}

				if (dropItem) {
					// drop the item
					block.getWorld().dropItemNaturally(block.getLocation().add(0.2D, 0.2D, 0.2D),
							new ItemStack(itemStack)); // Drops the Item

				} else {
					return;
				}

				event.setCancelled(true);

				// CODE END FOR AUTOPICKUP

				if (this.dropGlass) {

					if ((block.getType() == Material.GLASS) && this.dropGlass) {

						ItemStack item = new ItemStack(block.getType(), 1);

						block.getWorld().dropItemNaturally(block.getLocation(), item);
					}

					event.setExpToDrop(0);
				}
			}
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