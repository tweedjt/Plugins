package me.tweedjt.autopickup.util;

import me.tweedjt.autopickup.AutoPickup;

public class AutoPickupConfig {

	private boolean autoPickup = false;
	private boolean expDrop = false;
	private boolean autoMonster = false;
	private boolean autoAnimals = false;
	private boolean pvpDrops = false;
	private boolean dropGlass = false;
	private String messagePrefix = "&6[&fAutoPickup&6] &r";
	private String autoPickupOffMessage = "AutoPickup has been turned off";
	private String autoPickupOnMessage = "AutoPickup has been turned on";

	public boolean getAutoPickup() {
		return this.autoPickup;
	}

	public boolean getExp() {
		return this.expDrop;
	}

	public boolean getAutoMonster() {
		return this.autoMonster;
	}

	public boolean getAutoAnimals() {
		return this.autoAnimals;
	}

	public boolean getPvpDrops() {
		return this.pvpDrops;
	}

	public boolean getDropGlass() {
		return this.dropGlass;
	}

	public String getMessagePrefix() {
		return this.messagePrefix;
	}

	public String getAutoPickupOnMessage() {
		return this.autoPickupOffMessage;
	}

	public String getAutoPickupOffMessage() {
		return this.autoPickupOnMessage;
	}

	public AutoPickupConfig(AutoPickup plugin) {

		// AutoPickup default
		try {
			if (plugin.getConfig().contains("auto_pickup")) {
				if (plugin.getConfig().isBoolean("auto_pickup")) {
					this.autoPickup = plugin.getConfig().getBoolean("auto_pickup");
				} else {
					autoPickup = false;
				}
			}
		} catch (Exception ex) {
			autoPickup = true;
		}

		try {
			if (plugin.getConfig().contains("auto_monster_drops")) {
				if (plugin.getConfig().isBoolean("auto_monster_drops")) {
					this.autoMonster = plugin.getConfig().getBoolean("auto_monster_drops");
				} else {
					autoMonster = false;
				}
			}
		} catch (Exception ex) {
			autoMonster = true;
		}

		try {
			if (plugin.getConfig().contains("auto_animal_drops")) {
				if (plugin.getConfig().isBoolean("auto_animal_drops")) {
					this.autoAnimals = plugin.getConfig().getBoolean("auto_animal_drops");
				} else {
					autoAnimals = false;
				}
			}
		} catch (Exception ex) {
			autoAnimals = true;
		}

		try {
			if (plugin.getConfig().contains("pvp_drops")) {
				if (plugin.getConfig().isBoolean("pvp_drops")) {
					this.pvpDrops = plugin.getConfig().getBoolean("pvp_drops");
				} else {
					pvpDrops = false;
				}
			}
		} catch (Exception ex) {
			pvpDrops = true;
		}

		try {
			if (plugin.getConfig().contains("Drop_Glass")) {
				if (plugin.getConfig().isBoolean("Drop_Glass")) {
					this.dropGlass = plugin.getConfig().getBoolean("Drop_Glass");
				} else {
					dropGlass = false;
				}
			}
		} catch (Exception ex) {
			dropGlass = true;
		}

		try {
			if (plugin.getConfig().contains("Drop_EXP")) {
				if (plugin.getConfig().isBoolean("Drop_EXP")) {
					this.expDrop = plugin.getConfig().getBoolean("Drop_EXP");
				} else {
					expDrop = false;
				}
			}
		} catch (Exception ex) {
			expDrop = true;
		}
		// message_prefix
		try {
			if (plugin.getConfig().contains("message_prefix")) {
				if (plugin.getConfig().isString("message_prefix")) {
					this.messagePrefix = plugin.getConfig().getString("message_prefix");
				} else {
					messagePrefix = "&d[&fAutoPickup&d] &r";
					// message_prefix is not a string value
				}
			} else {
				messagePrefix = "&d[&fAutoPickup&d] &r";
				// message_prefix does not exist in config file
			}
		} catch (Exception ex) {
			messagePrefix = "&d[&fAutoPickup&d] &r";
			// Unexpected error getting message_prefix. (Use ex.getMessage() to get error)

		}

		try {
			if (plugin.getConfig().contains("autopickup_off_message")) {
				if (plugin.getConfig().isString("autopickup_off_message")) {
					this.autoPickupOffMessage = plugin.getConfig().getString("autopickup_off_message");
				} else {
					autoPickupOffMessage = "AutoPickup has been turned off";
					// message_prefix is not a string value
				}
			} else {
				autoPickupOffMessage = "AutoPickup has been turned off";
				// message_prefix does not exist in config file
			}
		} catch (Exception ex) {
			autoPickupOffMessage = "AutoPickup has been turned off";
			// Unexpected error getting message_prefix. (Use ex.getMessage() to get error)

		}

		try {
			if (plugin.getConfig().contains("autopickup_on_message")) {
				if (plugin.getConfig().isString("autopickup_on_message")) {
					this.autoPickupOnMessage = plugin.getConfig().getString("autopickup_on_message");
				} else {
					autoPickupOnMessage = "AutoPickup has been turned on";
					// message_prefix is not a string value
				}
			} else {
				autoPickupOnMessage = "AutoPickup has been turned on";
				// message_prefix does not exist in config file
			}
		} catch (Exception ex) {
			autoPickupOnMessage = "AutoPickup has been turned on";
			// Unexpected error getting message_prefix. (Use ex.getMessage() to get error)

		}
	}
}
